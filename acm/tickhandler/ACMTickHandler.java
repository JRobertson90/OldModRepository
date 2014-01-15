package acm.tickhandler;

import java.util.EnumSet;
import java.util.UUID;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.BaseAttributeMap;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import acm.ACM;
import acm.item.ACMItem;
import acm.melee.ItemShield;
import acm.player.ExtendedPlayer;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

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
		
		//Get attribute information from player and get our modifier ready
		BaseAttributeMap attributes = player.getAttributeMap();
		
		//Create our Attribute modifier, and select the value by which to increase the speed based on if they are wearing camo leggings or not.
		AttributeModifier camoPantsModifier = new AttributeModifier(movementSpeedUID, "Camo leggings speed change", ACM.playerIsWearingItem(player, ACMItem.camoLegs) ? 0.07d : 0d, 0);
		AttributeModifier scubaFinsModifier = new AttributeModifier(movementSpeedUID, "Scuba fins speed change", ACM.playerIsWearingItem(player, ACMItem.scubaFins) && ACM.playerIsSwimming(player) ? 0.07d : 0d, 0);
		
        //Add modifier to Multimap list
		Multimap modifiersToAdd = ArrayListMultimap.create();
        modifiersToAdd.put("generic.movementSpeed", camoPantsModifier);
        modifiersToAdd.put("generic.movementSpeed", scubaFinsModifier);
        attributes.applyAttributeModifiers(modifiersToAdd);
        ExtendedPlayer props = ExtendedPlayer.get(player);
        
        int netherArmorCount = 0;
        netherArmorCount += ACM.playerIsWearingItem(player, ACMItem.netherHelm) ? 1 : 0;
        netherArmorCount += ACM.playerIsWearingItem(player, ACMItem.netherPlate) ? 1 : 0;
		netherArmorCount += ACM.playerIsWearingItem(player, ACMItem.netherLegs) ? 1 : 0;
		netherArmorCount += ACM.playerIsWearingItem(player, ACMItem.netherBoots) ? 1 : 0;
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
        		player.worldObj.spawnParticle("flame", player.posX-0.6+Math.random()*1.2, player.posY-0.5-Math.random()*0.5, player.posZ-0.6+Math.random()*1.2, 0, 0.15*Math.random(), 0);
            	player.worldObj.spawnParticle("smoke", player.posX-0.6+Math.random()*1.2, player.posY-0.5-Math.random()*0.5, player.posZ-0.6+Math.random()*1.2, 0, 0.15*Math.random(), 0);
        	}
        	if(props.netherArmorCount == 4)
        	{
        		player.extinguish();
        	}
        }
        if(player.inventory.getCurrentItem() != null)
        {
        	if(player.isBurning() && player.inventory.getCurrentItem().itemID == ACMItem.woodShield.itemID && !(player.capabilities.isCreativeMode))
            {
            	player.inventory.mainInventory[player.inventory.currentItem] = null;
            	if(FMLCommonHandler.instance().getEffectiveSide().equals(Side.CLIENT))
            	{
            		player.addChatMessage("Your wooden shield burnt up!");
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
	
	public int getShieldStrengthValue(ItemStack input)
	{
		if(input != null)
		{
			if(input.itemID == ACMItem.woodShield.itemID)
			{
				return 1;
			}
			else if(input.itemID == ACMItem.goldShield.itemID)
			{
				return 2;
			}
			else if(input.itemID == ACMItem.stoneShield.itemID)
			{
				return 3;
			}
			else if(input.itemID == ACMItem.ironShield.itemID ||
					input.itemID == ACMItem.blueShield.itemID ||
					input.itemID == ACMItem.redShield.itemID)
			{
				return 4;
			}
			else if(input.itemID == ACMItem.diamondShield.itemID)
			{
				return 5;
			}
			else if(input.itemID == ACMItem.netherShield.itemID)
			{
				return 6;
			}

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
	public void tickEnd(EnumSet<TickType> type, Object... tickData) {
		
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
