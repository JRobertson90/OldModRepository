package acm.tickhandler;

import java.util.EnumSet;
import java.util.UUID;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.MathHelper;
import acm.item.ACMItem;
import acm.melee.ItemShield;
import acm.player.ExtendedPlayer;
import acm.wearable.ItemFins;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;
import cpw.mods.fml.relauncher.ReflectionHelper;
import cpw.mods.fml.relauncher.Side;

public class ACMTickHandler implements ITickHandler{

	private static final UUID movementSpeedUID = UUID.fromString("206a89dc-ae78-4c4d-b42c-3b31db3f5a7c");
	
	@Override
	public void tickStart(EnumSet<TickType> type, Object... tickData) {
		
		EntityPlayer player = (EntityPlayer) tickData[0];
		ExtendedPlayer props = ExtendedPlayer.get(player);
        
		if(player.inventory.inventoryChanged)
		{
			props.onInventoryChanged();
			player.inventory.inventoryChanged = false;
		}
		if(!props.wasInitialized)
		{
			props.tickInitialize();
		}
		if(props.netherArmorCount>0)
		{
        	double randomValue = Math.random();
        	if(randomValue <= (double) props.netherArmorCount * 0.125)
        	{

        		int facing = getFacingDirectionWithDiagonals(player);
//                System.out.println("Facing " + facing);
        		
        		// This code makes sure that particles spawn behind
        		// the player so as to not obscure vision.
        		int xSide = 0;
        		int zSide = 0;
        		
        		switch(facing) {
        		case 0: { xSide = 0; zSide = -1; break;}
        		case 1: { xSide = 1; zSide = -1; break;}
        		case 2: { xSide = 1; zSide = 0; break;}
        		case 3: { xSide = 1; zSide = 1; break;}
        		case 4: { xSide = 0; zSide = 1; break;}
        		case 5: { xSide = -1; zSide = 1; break;}
        		case 6: { xSide = -1; zSide = 0; break;}
        		case 7: { xSide = -1; zSide = -1; break;}
        		}
        		
        		final double DIST_FROM_PLAYER = 0.75; 
        		
        		player.worldObj.spawnParticle("flame",
        				player.posX + Math.random() * .5 + xSide * DIST_FROM_PLAYER,
        				player.posY - 0.5,
        				player.posZ + Math.random() * .5 + zSide * DIST_FROM_PLAYER,
        				0,
        				0.15*Math.random(),
        				0);
//        		player.worldObj.spawnParticle("smoke", player.posX + xSide, player.posY-0.5-Math.random()*0.5, player.posZ + zSide, 0, 0.15*Math.random(), 0);
        	}
        	if(props.netherArmorCount == 4)
        	{
        		player.extinguish();
        	}
        }
		
        if(player.inventory.getCurrentItem() != null)
        {
        	
        	//Wooden shield burning code
        	if(player.isBurning() && player.inventory.getCurrentItem().itemID == ACMItem.woodShield.itemID && !(player.capabilities.isCreativeMode))
            {
            	player.inventory.mainInventory[player.inventory.currentItem] = null;
            	if(FMLCommonHandler.instance().getEffectiveSide().equals(Side.CLIENT))
            	{
            		player.addChatMessage("Your wooden shield burnt up!");
            	}
            }
        }
        //If player is holding nothing
        else
        {
        	//Code to automatically switch block stacks
        	if(props.lastStack != null && props.lastStack.getItem() instanceof ItemBlock && player.inventory.currentItem == props.lastHotbarSlot)
        	{
        		boolean leftFlag = false;
    			boolean rightFlag = false;
    			for(int i = 1;!leftFlag && !rightFlag && i < 9; i++)
    			{
    				ItemStack left = null;
        			ItemStack right = null;
        			if(player.inventory.currentItem+i < 9)
        			{
        				left = player.inventory.getStackInSlot(player.inventory.currentItem+i);
        			}
        			if(player.inventory.currentItem-i >= 0)
        			{
        				right = player.inventory.getStackInSlot(player.inventory.currentItem-i);
        			}
        			try
        			{
        				leftFlag = left.itemID == props.lastStack.itemID && left.getItemDamage() == props.lastStack.getItemDamage();
        			}
        			catch(NullPointerException e)
        			{
        				;
        			}
        			try
        			{
        				rightFlag = right.itemID == props.lastStack.itemID && right.getItemDamage() == props.lastStack.getItemDamage();
        			}
        			catch(NullPointerException e)
        			{
        				;
        			}
        	        if(leftFlag || rightFlag)
        	        {
        	        	if(leftFlag)
        	        	{
        	        		player.inventory.currentItem = player.inventory.currentItem+i;
        	        	}
        	        	else
        	        	{
        	        		player.inventory.currentItem = player.inventory.currentItem-i;
        	        	}
        	        	Minecraft.getMinecraft().playerController.sendUseItem(player, player.worldObj, player.inventory.getStackInSlot(player.inventory.currentItem));
        	        }
    			}
        	}
        }
        if(FMLCommonHandler.instance().getEffectiveSide().equals(Side.CLIENT) && player.isUsingItem())
        {
        	ItemStack itemInUse = player.inventory.getCurrentItem();
        	if(itemInUse != null)
        	{
        		if(itemInUse.getItem() instanceof ItemSword)
            	{
        			int leftValue = 0;
        			int rightValue = 0;
        			for(int i = 1;leftValue==0 && rightValue==0 && i < 9; i++)
        			{
        				ItemStack left = null;
            			ItemStack right = null;
            			if(player.inventory.currentItem+i < 9)
            			{
            				left = player.inventory.getStackInSlot(player.inventory.currentItem+i);
            			}
            			if(player.inventory.currentItem-i >= 0)
            			{
            				right = player.inventory.getStackInSlot(player.inventory.currentItem-i);
            			}
            	        leftValue = getShieldStrengthValue(left);
            	        rightValue = getShieldStrengthValue(right);
            	        if(leftValue != 0 || rightValue != 0)
            	        {
            	        	props.weaponToReturnTo = player.inventory.currentItem;
            	        	if(leftValue>=rightValue)
            	        	{
            	        		player.inventory.currentItem = player.inventory.currentItem+i;
            	        	}
            	        	else
            	        	{
            	        		player.inventory.currentItem = player.inventory.currentItem-i;
            	        	}
            	        	Minecraft.getMinecraft().playerController.sendUseItem(player, player.worldObj, player.inventory.getStackInSlot(player.inventory.currentItem));
            	        }
        			}
        			
            	}
        	}
        }
	}
	
	private int getFacingDirectionWithDiagonals(EntityPlayer player) {
		
		// Code taken from user flipflop: http://www.minecraftforge.net/forum/index.php?topic=6514.0
		int yaw = (int)player.rotationYaw;

        if (yaw<0)      //due to the yaw running a -360 to positive 360
           yaw+=360;    //not sure why it's that way

        yaw+=22;     //centers coordinates you may want to drop this line
        yaw%=360;  //and this one if you want a strict interpretation of the zones

        int facing = yaw/45;   //  360degrees divided by 45 == 8 zones
		return facing;
	}

	public int getShieldStrengthValue(ItemStack input)
	{
		if(input != null)
		{
			if(input.getItem() instanceof ItemShield)
			{
				int maxUses = -1;
				for(int i=0;maxUses == -1; i++)
				{
					try
					{
						maxUses = ((EnumToolMaterial) ReflectionHelper.getPrivateValue(ItemShield.class, (ItemShield) input.getItem(), i)).getMaxUses();
					}
					catch(Exception e)
					{
						;
					}
					
				}
				if(input.itemID == ACMItem.netherShield.itemID)
				{
					//Because the nether shield lights the enemy on fire we want it to
					//have preference over an equal strength shield.
					maxUses++;
				}
				return maxUses;
			}
		}
		return 0;
	}

	@Override
	public void tickEnd(EnumSet<TickType> type, Object... tickData)
	{
	
		EntityPlayer player = (EntityPlayer) tickData[0];
		int[] armorInventory = new int[4];

		if (player == null) {
			return;
		}
		for (int i = 0; i < armorInventory.length; i++)
		{
			ItemStack item = player.inventory.armorItemInSlot(i);
			if (item == null)
				continue;
			if (!(item.getItem() instanceof ItemFins))
				continue;
			((ItemFins)item.getItem()).action(player);
		}
		ExtendedPlayer props = ExtendedPlayer.get(player);
		props.lastStack = player.inventory.getCurrentItem();
        props.lastHotbarSlot = player.inventory.currentItem;
	}

	@Override
	public EnumSet<TickType> ticks() {
		//This makes sure that we receive PLAYER type ticks which are thrown any time player processing happens
		return EnumSet.of(TickType.PLAYER);
	}

	@Override
	public String getLabel() {
		return null;
	}

}
