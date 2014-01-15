package acm.tickhandler;

import java.util.EnumSet;

import acm.wearable.ItemFins;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;

public class ACMTickHandler2 implements ITickHandler {

	@Override
	public void tickStart(EnumSet<TickType> type, Object... tickData) {

	}

	@Override
	public void tickEnd(EnumSet<TickType> type, Object... tickData) {

		if ((type.equals(EnumSet.of(TickType.CLIENT))) && (!type.equals(EnumSet.of(TickType.RENDER))))
		{
			GuiScreen guiscreen = Minecraft.getMinecraft().currentScreen;
			if (guiscreen == null)
			{
				onClientTickInGame(tickData);
			}
		}
	}

	private void onClientTickInGame(Object[] tickData)
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
	}

	public EnumSet<TickType> ticks()
	{
		return EnumSet.of(TickType.RENDER, TickType.CLIENT);
	}

	public String getLabel()
	{
		return null;
	}

}