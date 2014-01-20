package acm.server;

import net.minecraft.entity.Entity;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.EntityEvent.EntityConstructing;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import acm.ACM;
import acm.item.ACMItem;
import acm.melee.ItemShield;
import acm.melee.ItemWarHammer;
import acm.player.ExtendedPlayer;
import acm.tool.ItemNetherTool;
import acm.wearable.ItemCamo;
import acm.wearable.ItemNetherArmor;

public class ServerAndClientEventHandler {
	
	
	@ForgeSubscribe
	public void onEntityConstructing(EntityConstructing event)
	{
		if (event.entity instanceof EntityPlayer)
		{
			if(ExtendedPlayer.get((EntityPlayer) event.entity) == null)
			{
				ExtendedPlayer.register((EntityPlayer) event.entity);
			}
			if(event.entity.getExtendedProperties(ExtendedPlayer.EXT_PROP_NAME) == null)
			{
				event.entity.registerExtendedProperties(ExtendedPlayer.EXT_PROP_NAME, new ExtendedPlayer((EntityPlayer) event.entity));
			}
		}
	}
	

	@ForgeSubscribe
	public void livingFall(LivingFallEvent event)
	{
	    if (event.entityLiving instanceof EntityPlayer)
	    {
	    	EntityPlayer player = (EntityPlayer)event.entityLiving;
	    	if(ACM.playerIsWearingItem(player, ACMItem.camoLegs))
	    	{
	    		ItemCamo.reduceFallDamage(event);
	    	}
	    }	    
	}
	
	@ForgeSubscribe
	public void onAttack(LivingAttackEvent event)
	{
		if(event.source.isFireDamage() && event.entity instanceof EntityPlayer)
		{
			ItemNetherArmor.reduceFireDamage(event);
		}
		if(event.source.getSourceOfDamage() != null && event.source.getSourceOfDamage() instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer) event.source.getSourceOfDamage();
			ItemStack equipped = player.inventory.getCurrentItem();
			if(player.inventory.getCurrentItem() != null) {
				if (equipped.getItem() instanceof ItemNetherTool)
				{
					event.entity.setFire(ItemNetherTool.fireTicksAfterHit);
				}
			}
		}
		if(event.entity instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer) event.entity;
			ExtendedPlayer props = ExtendedPlayer.get(player);
			Entity attacker = event.source.getSourceOfDamage();
			if(player.inventory.getCurrentItem() != null && player.inventory.getCurrentItem().getItem() instanceof ItemShield && player.isUsingItem())
			{
				((ItemShield) player.inventory.getCurrentItem().getItem()).shouldBlock(event);
			}
		}
	}
}
