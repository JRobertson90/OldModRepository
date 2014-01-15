package acm.network;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import acm.ACM;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.Player;
import cpw.mods.fml.relauncher.Side;

public class ACMPacketHandler implements IPacketHandler{

	@Override
	public void onPacketData(INetworkManager manager, Packet250CustomPayload packet, Player player) {
		if(packet.channel.equals(ACM.packetChannel))
		{
			String packetMessage = ACM.readStringPacket(packet);
			//Server side
			if(FMLCommonHandler.instance().getEffectiveSide().equals(Side.SERVER))
			{
				if(packetMessage.startsWith("jump:"))
				{
					ACMPacketInfluence.superJump(ACM.getPlayerFromUsername(packetMessage.split(":")[1]));
				}
				else if(packetMessage.startsWith("scubaswim:"))
				{
					ACMPacketInfluence.scubaSwim(ACM.getPlayerFromUsername(packetMessage.split(":")[1]));
				}
			}
//			//Client side
//			else
//			{
//				if(packetMessage.equals("flashStamina"))
//				{
//					ACM.proxy.flashStamina();
//				}
//			}
		}
	}

}
