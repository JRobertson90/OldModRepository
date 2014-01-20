package acm.wearable;

import java.util.Iterator;
import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.client.event.sound.PlaySoundEvent;
import net.minecraftforge.event.entity.PlaySoundAtEntityEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import acm.ACM;
import acm.item.ACMItem;

public class ItemCamo extends ItemArmor {

	public ItemCamo(int par1, EnumArmorMaterial par2EnumArmorMaterial, int par3, int par4) {
		super(par1, par2EnumArmorMaterial, par3, par4);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type)
    {
		if(stack.itemID == ACMItem.camoLegs.itemID)
		{
			return "acm:textures/models/armor/camo_layer_2.png";
		}
		return "acm:textures/models/armor/camo_layer_1.png";
	}
	
	public static void reduceFallDamage(LivingFallEvent event)
	{
		//Reduce calculated fall distance by 10 blocks.
		event.distance -= 10.0F;
	}
	
	public static void superJump(EntityPlayer player)
	{
		player.motionY += 0.3D;
		double horizontalMultiplier = player.isSprinting() ? 3.0D : 3.0D;
		player.motionX *= horizontalMultiplier;
		player.motionZ *= horizontalMultiplier;
	}
	
	public static void silenceTunneling(PlaySoundEvent event)
	{
		//Test for the wide variety of sounds made while tunneling and building
		if(event.name.startsWith("dig.") || event.name.equals("random.break") || event.name.equals("random.pop") || event.name.startsWith("step."))
		{
			//Get the world so we can iterate through the players.
			//Using Minecraft.getMinecraft() would break the code server side but this is only run
			//client side.
			World world = Minecraft.getMinecraft().theWorld;
			//Boolean used to remember if we've attached a footstep sound to a player or not.
			//This is necessary because the footstep sounds also happen to be played while
			//digging a block, so the code below tries to attach footstep sounds to a player
			//If we manage to attach it to a player then we won't silence it.
			boolean isPlayerFootStep = false;
			//used to determine if a sound should be silenced or not.
			boolean shouldSilence = false;
			//This code will be run for every player in the world.
			Iterator players = world.playerEntities.iterator();
			while(players.hasNext() && !isPlayerFootStep)
			{
				EntityPlayer player = (EntityPlayer) players.next();
				float deltaX = (float) player.posX - event.x;
				float deltaY = (float) player.posY - event.y;
				float deltaZ = (float) player.posZ - event.z;

				//Calculate the distance between the sound location and the player location
				float distance = (float) Math.sqrt(deltaX * deltaX + deltaY * deltaY + deltaZ * deltaZ);
				//If the sound is within the range of the players block breaking and placing range
				if(distance<=5.3f)
				{
					//Try to identify a footstep.  Footsteps are a value between 1.6 and 1.7 below the player
					//Have no horizontal displacement and start with step.
					if(deltaY>1.6f && deltaY<1.7f && deltaX == 0.0f && deltaZ == 0f && event.name.startsWith("step."))
					{
						//We found a player that made the foot step sound, so don't silence this sound.
						isPlayerFootStep = true;
						shouldSilence = false;
					}
					//If the sound is not attached to any player
					else if(!isPlayerFootStep)
					{
						if(ACM.playerIsWearingItem(player, ACMItem.camoShirt))
						{
							shouldSilence = true;
						}
					}
				}
			}
			if(shouldSilence)
			{
				//Setting event.result to null will silence the sound.
				event.result = null;
			}
		}
	}
	
	public static void hideNamePlate(RenderLivingEvent.Specials.Pre event)
	{
		//Canceling the event will prevent the nameplate from being drawn
		event.setCanceled(true);
	}
	
	public static void silenceFootStep(PlaySoundAtEntityEvent event)
	{
		if(event.name.startsWith("step."))
		{
			//Cancelling this event will prevent the sound from playing
			event.setCanceled(true);
		}
	}
	
	@Override
	//This will add the blurb of text displayed when you mouse over the camo armor
	public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4)
	{
		if(par1ItemStack.itemID == ACMItem.camoHelm.itemID)
		{
			par3List.add("Hides your name plate");
			par3List.add("and face from other players");
		}
		if(par1ItemStack.itemID == ACMItem.camoShirt.itemID)
		{
			par3List.add("Silences all block breaking and placing");
			par3List.add("in a small radius around you");
		}
		if(par1ItemStack.itemID == ACMItem.camoLegs.itemID)
		{
			par3List.add("Improves jump height and movement speed,");
			par3List.add("Also reduces fall damage by 10 blocks");
		}
		if(par1ItemStack.itemID == ACMItem.camoBoots.itemID)
		{
			par3List.add("Silences footsteps");
		}
	}

}
