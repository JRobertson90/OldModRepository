package acm.client;

import net.minecraft.client.renderer.entity.RenderArrow;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.MinecraftForge;
import acm.block.ACMBlock;
import acm.christmasChest.ItemRendererChristmasChest;
import acm.christmasChest.TileEntityChristmasChest;
import acm.christmasChest.TileEntityChristmasChestRenderer;
import acm.client.rendering.BigItemRenderer;
import acm.client.rendering.GuiDrawing;
import acm.client.rendering.RenderArrowPort;
import acm.client.rendering.RenderArrowTorch;
import acm.client.rendering.RenderExplosiveArrow;
import acm.client.rendering.ShieldRenderer;
import acm.craftingChest.ItemRendererCraftingChest;
import acm.craftingChest.TileEntityCraftingChest;
import acm.craftingChest.TileEntityCraftingChestRenderer;
import acm.entity.ArrowExplosive;
import acm.entity.ArrowFar;
import acm.entity.EntityArrowPort;
import acm.entity.EntityArrowTorch;
import acm.item.ACMItem;
import acm.server.ServerAndClientEventHandler;
import acm.server.ServerProxy;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class ClientProxy extends ServerProxy {

	//Code here is only executed for the client
	@Override
    public void registerRenderers() {
		
		MinecraftForgeClient.registerItemRenderer(ACMItem.woodHammer.itemID, new BigItemRenderer());
		MinecraftForgeClient.registerItemRenderer(ACMItem.stoneHammer.itemID, new BigItemRenderer());
		MinecraftForgeClient.registerItemRenderer(ACMItem.ironHammer.itemID, new BigItemRenderer());
		MinecraftForgeClient.registerItemRenderer(ACMItem.blueHammer.itemID, new BigItemRenderer());
		MinecraftForgeClient.registerItemRenderer(ACMItem.redHammer.itemID, new BigItemRenderer());
		MinecraftForgeClient.registerItemRenderer(ACMItem.goldHammer.itemID, new BigItemRenderer());
		MinecraftForgeClient.registerItemRenderer(ACMItem.diamondHammer.itemID, new BigItemRenderer());
		MinecraftForgeClient.registerItemRenderer(ACMItem.netherHammer.itemID, new BigItemRenderer());

		MinecraftForgeClient.registerItemRenderer(ACMItem.woodShield.itemID, new ShieldRenderer());
		MinecraftForgeClient.registerItemRenderer(ACMItem.stoneShield.itemID, new ShieldRenderer());
		MinecraftForgeClient.registerItemRenderer(ACMItem.ironShield.itemID, new ShieldRenderer());
		MinecraftForgeClient.registerItemRenderer(ACMItem.blueShield.itemID, new ShieldRenderer());
		MinecraftForgeClient.registerItemRenderer(ACMItem.redShield.itemID, new ShieldRenderer());
		MinecraftForgeClient.registerItemRenderer(ACMItem.goldShield.itemID, new ShieldRenderer());
		MinecraftForgeClient.registerItemRenderer(ACMItem.diamondShield.itemID, new ShieldRenderer());
		MinecraftForgeClient.registerItemRenderer(ACMItem.netherShield.itemID, new ShieldRenderer());
		
		RenderingRegistry.registerEntityRenderingHandler(ArrowExplosive.class, new RenderExplosiveArrow());
		RenderingRegistry.registerEntityRenderingHandler(ArrowFar.class, new RenderArrow());
		
		RenderingRegistry.registerEntityRenderingHandler(EntityArrowPort.class, new RenderArrowPort());
		RenderingRegistry.registerEntityRenderingHandler(EntityArrowTorch.class, new RenderArrowTorch());
		
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityChristmasChest.class, new TileEntityChristmasChestRenderer());
		MinecraftForgeClient.registerItemRenderer(ACMBlock.christmasChest.blockID, new ItemRendererChristmasChest());
		
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityCraftingChest.class, new TileEntityCraftingChestRenderer());
		MinecraftForgeClient.registerItemRenderer(ACMBlock.craftingChest.blockID, new ItemRendererCraftingChest());
	}
	
	@Override
	public void registerEventHandlers()
    {
    	//Add event handlers specific to sound and rendering
    	MinecraftForge.EVENT_BUS.register(new ClientEventHandler());
    	MinecraftForge.EVENT_BUS.register(new GuiDrawing());
    	MinecraftForge.EVENT_BUS.register(new ServerAndClientEventHandler());
    }
}