package acm;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraft.item.crafting.ShapelessRecipes;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.oredict.ShapedOreRecipe;
import acm.block.ACMBlock;
import acm.network.ACMPacketHandler;
import acm.server.ServerProxy;
import acm.tickhandler.ACMTickHandler;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;

@Mod(modid=ACM.modid, name=ACM.modname, version = ACM.modversion)
@NetworkMod(clientSideRequired=true, serverSideRequired=true, packetHandler = ACMPacketHandler.class, channels={ACM.packetChannel})
public class ACM {
	public static final String modid = "xaeroxe_jayperdu_ACM";
	public static final String modname = "ACM";
	public static final String modversion = "1.2";
	public static final String packetChannel = "xjACM"+modversion;
	
	@Instance(value = ACM.modid)
	public static ACM instance;
	
	@SidedProxy(clientSide="acm.client.ClientProxy", serverSide="acm.server.ServerProxy")
	public static ServerProxy proxy;
	
	@EventHandler
    public void preInit(FMLPreInitializationEvent event) {
		proxy.registerEventHandlers();
	}
	
	@EventHandler
	public void load(FMLInitializationEvent event)
	{
		ACMRegistry.load();
		ACMRecipes.load();
		
		TickRegistry.registerTickHandler(new ACMTickHandler(), Side.SERVER);
		TickRegistry.registerTickHandler(new ACMTickHandler(), Side.CLIENT);
		
		proxy.registerRenderers();
		Item.egg.setMaxStackSize(64);
		Item.snowball.setMaxStackSize(64);
		Item.doorWood.setMaxStackSize(64);
		Item.doorIron.setMaxStackSize(64);
		Item.sign.setMaxStackSize(64);
		final int woodOrGold = 0;
		final int stone = 1;
		final int iron = 2;
		final int diamond = 3;
		MinecraftForge.setBlockHarvestLevel(ACMBlock.potassiumOre, "pickaxe", woodOrGold);
		MinecraftForge.setBlockHarvestLevel(ACMBlock.sulfurOre, "pickaxe", woodOrGold);
		MinecraftForge.setBlockHarvestLevel(ACMBlock.netheraniumOre, "pickaxe", iron);
	}
	
	@EventHandler
    public void postInit(FMLPostInitializationEvent event) {
		
	}
	
	public static void RemoveRecipe(ItemStack resultItem) //Code by yope_fried inspired by pigalot, modified by jayperdu
	{
	    ItemStack recipeResult = null;
	    ArrayList recipes = (ArrayList) CraftingManager.getInstance().getRecipeList();

	    for (int i = 0; i < recipes.size(); i++)
	    {
	        IRecipe tmpRecipe = (IRecipe) recipes.get(i);
	        if (tmpRecipe instanceof ShapedRecipes)
	        {
	            ShapedRecipes recipe = (ShapedRecipes)tmpRecipe;
	            recipeResult = recipe.getRecipeOutput();
	        }

	        if (tmpRecipe instanceof ShapelessRecipes)
	        {
	            ShapelessRecipes recipe = (ShapelessRecipes)tmpRecipe;
	            recipeResult = recipe.getRecipeOutput();
	        }
	        
	        if (tmpRecipe instanceof ShapedOreRecipe)
	        {
	        	ShapedOreRecipe recipe = (ShapedOreRecipe)tmpRecipe;
	            recipeResult = recipe.getRecipeOutput();
	        }
	        
	        if (ItemStack.areItemStacksEqual(resultItem, recipeResult))
	        {
	            recipes.remove(i);
	        }
	    }
	}
	
	public static EntityPlayer getPlayerFromUsername(String username)
	{
		Iterator players;
		if(FMLCommonHandler.instance().getEffectiveSide().equals(Side.SERVER))
		{
			players = MinecraftServer.getServer().getConfigurationManager().playerEntityList.iterator();
		}
		else
		{
			players = Minecraft.getMinecraft().theWorld.playerEntities.iterator();
		}
		while(players.hasNext())
		{
			EntityPlayer player = (EntityPlayer) players.next();
			if(player.username.equals(username))
			{
				return player;
			}
		}
		return null;
	}
	
	public static Packet buildStringPacket(String input)
	{
		ByteArrayOutputStream bos = new ByteArrayOutputStream(8);
		DataOutputStream outputStream = new DataOutputStream(bos);
		try {
		        outputStream.writeChars(input);
		} catch (Exception ex) {
		        ex.printStackTrace();
		}

		Packet250CustomPayload packet = new Packet250CustomPayload();
		packet.channel = ACM.packetChannel;
		packet.data = bos.toByteArray();
		packet.length = bos.size();
		return packet;
	}
	
	public static String readStringPacket(Packet250CustomPayload packet)
	{
		DataInputStream inputStream = new DataInputStream(new ByteArrayInputStream(packet.data));
		String message = "";
		boolean endOfStringReached = false;
		while(!endOfStringReached)
		{
			try {
				message = message+inputStream.readChar();
			}
			catch(EOFException e) {
				endOfStringReached = true;
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}
		return message;
	}
	
	//This code is redundant on being called, but it may come in handy later if addRecipe
	//stops adding mirrored recipes
	/*public static void addMirroredRecipe(ItemStack output, Object... params)
	{
		GameRegistry.addRecipe(output, params);
		Object[] mirroredParams = new Object[params.length];
		for(int i = 0;i<params.length;i++)
		{
			if(params[i] instanceof String)
			{
				mirroredParams[i] = new StringBuilder((String) params[i]).reverse().toString();
			}
			else
			{
				mirroredParams[i] = params[i];
			}
		}
		GameRegistry.addRecipe(output, mirroredParams);
	}*/
	
	public static boolean playerIsWearingItem(EntityPlayer player, Item item)
	{
		for(int i = 0;i<player.inventory.armorInventory.length; i++)
		{
			if(player.inventory.armorInventory[i]!=null)
			{
				if(item.itemID == player.inventory.armorInventory[i].itemID)
				{
					return true;
				}
			}
		}
		return false;
	}

}
