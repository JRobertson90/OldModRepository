package acm.network;

import net.minecraft.entity.player.EntityPlayer;
import acm.ACM;
import acm.item.ACMItem;
import acm.player.ExtendedPlayer;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.relauncher.Side;

public class ACMPacketInfluence {
	
	public static void superJump(EntityPlayer player)
	{
		if(ACM.playerIsWearingItem(player, ACMItem.camoLegs) /*&& ACM.playerIsNotWearingMetal(player)*/)
		{
			ExtendedPlayer props = ExtendedPlayer.get(player);
//			if(props.checkAndUseStamina(22))
//			{
				player.motionY += 0.3D;
				double horizontalMultiplier = player.isSprinting() ? 3.0D : 3.0D;
				player.motionX *= horizontalMultiplier;
				player.motionZ *= horizontalMultiplier;
				if(FMLCommonHandler.instance().getEffectiveSide().equals(Side.CLIENT))
				{
					PacketDispatcher.sendPacketToServer(ACM.buildStringPacket("jump:"+player.username));
				}
//			}
		}
	}
	
//	public static void manuallyFlashStamina(EntityPlayer player)
//	{
//		ExtendedPlayer props = ExtendedPlayer.get(player);
//		props.flashingTicksRemaining = props.flashingTicksMax;
//	}
}
