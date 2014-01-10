package acm.client;

import java.util.Iterator;

import cpw.mods.fml.common.network.PacketDispatcher;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.client.event.sound.PlaySoundEvent;
import net.minecraftforge.client.event.sound.SoundLoadEvent;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.PlaySoundAtEntityEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingJumpEvent;
import acm.ACM;
import acm.item.ACMItem;
import acm.network.ACMPacketInfluence;

public class ClientEventHandler {
	
	@ForgeSubscribe
	public void loadSounds(SoundLoadEvent event)
    {
		for(int i = 1;i<=6;i++)
		{
			event.manager.soundPoolSounds.addSound("acm:boom/boom"+i+".ogg");
		}
    }
	
	@ForgeSubscribe
	public void onRenderLiving(RenderLivingEvent.Specials.Pre event)
	{
		if (event.entity instanceof EntityPlayer && event.isCancelable())
		{
			EntityPlayer player = (EntityPlayer) event.entity;
			if(ACM.playerIsWearingItem(player, ACMItem.camoHelm))
			{
				//Cancelling this event will hide the name plate of the player
				event.setCanceled(true);
			}
		}
	}
	
	@ForgeSubscribe
	public void onPlaySound(PlaySoundAtEntityEvent event)
	{
		if (event.entity instanceof EntityPlayer && event.isCancelable())
		{
			EntityPlayer player = (EntityPlayer) event.entity;
			if(ACM.playerIsWearingItem(player, ACMItem.camoBoots) && event.name.startsWith("step."))
			{
				//Cancelling this event will prevent the sound from playing
				event.setCanceled(true);
			}
		}
	}
	
	@ForgeSubscribe
	public void onLivingJumpEvent(LivingJumpEvent event)
	{
		if(event.entity instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer) event.entity;
			ACMPacketInfluence.superJump(player);
		}
	}
	
	@ForgeSubscribe
	public void onSoundMade(PlaySoundEvent event)
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
}
