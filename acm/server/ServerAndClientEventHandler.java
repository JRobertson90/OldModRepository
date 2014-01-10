package acm.server;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.MathHelper;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.EntityEvent.EntityConstructing;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingJumpEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import acm.ACM;
import acm.item.ACMItem;
import acm.melee.ItemShield;
import acm.network.ACMPacketInfluence;
import acm.player.ExtendedPlayer;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.common.network.Player;
import cpw.mods.fml.relauncher.Side;

public class ServerAndClientEventHandler {
	
	
	@ForgeSubscribe
	public void onEntityConstructing(EntityConstructing event)
	{
		if (event.entity instanceof EntityPlayer && ExtendedPlayer.get((EntityPlayer) event.entity) == null)
		{
			ExtendedPlayer.register((EntityPlayer) event.entity);
		}
			
		if (event.entity instanceof EntityPlayer && event.entity.getExtendedProperties(ExtendedPlayer.EXT_PROP_NAME) == null)
		{
			event.entity.registerExtendedProperties(ExtendedPlayer.EXT_PROP_NAME, new ExtendedPlayer((EntityPlayer) event.entity));
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
	    		//Reduce calculated fall distance by 10 blocks.
	    		event.distance -= 10.0F;
	    	}
	    }	    
	}
	
	public float correctOverFlowedAngle(float input)
	{
		if(input>180)
		{
			return input-360;
		}
		else if(input<=-180)
		{
			return input+360;
		}
		return input;
	}
	
	@ForgeSubscribe
	public void onAttack(LivingAttackEvent event)
	{
		if(event.source.isFireDamage() && event.entity instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer) event.entity;
			int netherArmorCount = 0;
	        netherArmorCount += ACM.playerIsWearingItem(player, ACMItem.netherHelm) ? 1 : 0;
	        netherArmorCount += ACM.playerIsWearingItem(player, ACMItem.netherPlate) ? 1 : 0;
			netherArmorCount += ACM.playerIsWearingItem(player, ACMItem.netherLegs) ? 1 : 0;
			netherArmorCount += ACM.playerIsWearingItem(player, ACMItem.netherBoots) ? 1 : 0;
			double randomValue = Math.random();
			if((double) netherArmorCount * 0.25 >= randomValue)
			{
				event.setCanceled(true);
			}
		}
		if(event.source.getSourceOfDamage() != null && event.source.getSourceOfDamage() instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer) event.source.getSourceOfDamage();
			int id = player.inventory.getCurrentItem().getItem().itemID;
			if(player.inventory.getCurrentItem() != null && 
			(id == ACMItem.netherAxe.itemID
			|| id == ACMItem.netherHoe.itemID
			|| id == ACMItem.netherPickaxe.itemID
			|| id == ACMItem.netherShovel.itemID
			|| id == ACMItem.netherSword.itemID
			|| id == ACMItem.netherDagger.itemID
			|| id == ACMItem.netherHammer.itemID
			|| id == ACMItem.netherPoisonDagger.itemID))
			{
				event.entity.setFire(5);
			}
		}
		if(event.entity instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer) event.entity;
			ExtendedPlayer props = ExtendedPlayer.get(player);
			Entity attacker = event.source.getSourceOfDamage();
			if(player.inventory.getCurrentItem() != null && attacker != null)
			{
				Item iteminUse = player.inventory.getCurrentItem().getItem();
				if(player.inventory.getCurrentItem().getItem() instanceof ItemShield && player.isUsingItem())
				{
					float attackedAtYaw = 0;
					if (attacker != null)
	                {
	                    attackedAtYaw = correctOverFlowedAngle(attacker.rotationYaw-180);
	                }
					final float shieldArcDegrees = 60;
					float rangeSideLeft = correctOverFlowedAngle(attackedAtYaw + shieldArcDegrees);
					float rangeSideRight = correctOverFlowedAngle(attackedAtYaw - shieldArcDegrees);
					//This code checks if the values crossed the angle loop and corrects them if
					//they have
					float playerYaw = correctOverFlowedAngle(player.rotationYaw);
					if(attacker instanceof IProjectile)
					{
						playerYaw *= -1;
					}
					boolean inRange;
					inRange = rangeSideLeft >= playerYaw && rangeSideRight <= playerYaw;
					if(rangeSideRight>rangeSideLeft)
					{
						inRange = rangeSideLeft <= playerYaw || rangeSideRight >= playerYaw;
					}
					if(inRange)
					{
//						if(props.checkAndUseStamina(ExtendedPlayer.blockStaminaCost))
//						{
							if(!(player.inventory.getCurrentItem().itemID == ACMItem.woodShield.itemID && attacker.isBurning()))
							{
								//Knockback code!!  Copied from various sections of minecraft source then tweaked some.
						    	double d0 = player.posX - attacker.posX;
						        double d1;

						        for (d1 = player.posZ - attacker.posZ; d0 * d0 + d1 * d1 < 1.0E-4D; d1 = (Math.random() - Math.random()) * 0.01D)
						        {
						            d0 = (Math.random() - Math.random()) * 0.01D;
						        }
						        attacker.isAirBorne = true;
						    	final float knockbackStrengthFactor = 5.0F/1.5F;
						        float f1 = MathHelper.sqrt_double(d0 * d0 + d1 * d1);
						        float f2 = 0.8f;
						        if(!(attacker instanceof IProjectile))
						        {
						        	attacker.motionX /= 2.0D;
							        attacker.motionY /= 2.0D;
							        attacker.motionZ /= 2.0D;
							        attacker.motionX -= d0 / f1 * f2;
							        attacker.motionY += (double)f2/3;
							        attacker.motionZ -= d1 / f1 * f2;
						        }
						        if(player.inventory.getCurrentItem().itemID == ACMItem.netherShield.itemID)
						        {
						        	attacker.setFire(5);
						        }
								event.setCanceled(true);
								player.inventory.getCurrentItem().damageItem(1, player);
							}
//						}
//						else
//						{
//							PacketDispatcher.sendPacketToPlayer(ACM.buildStringPacket("flashStamina"), (Player) player);
//						}
					}
				}
			}
		}
	}
}
