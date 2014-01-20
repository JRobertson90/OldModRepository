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
import acm.wearable.ItemCamo;

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
				ItemCamo.hideNamePlate(event);
			}
		}
	}
	
	@ForgeSubscribe
	public void onPlaySound(PlaySoundAtEntityEvent event)
	{
		if (event.entity instanceof EntityPlayer && event.isCancelable())
		{
			EntityPlayer player = (EntityPlayer) event.entity;
			if(ACM.playerIsWearingItem(player, ACMItem.camoBoots))
			{
				ItemCamo.silenceFootStep(event);
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
		ItemCamo.silenceTunneling(event);
	}
}
