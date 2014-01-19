package acm.wearable;

import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;

import org.lwjgl.input.Keyboard;

import acm.item.ACMItem;

public class ItemFins extends ItemArmor {

	public ItemFins(int par1, EnumArmorMaterial par2EnumArmorMaterial, int par3, int par4) {
		super(par1, par2EnumArmorMaterial, par3, par4);
	}
	
	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type)
    {
		return "acm:textures/models/armor/scuba_layer_1.png";
	}

	@Override
	public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4)
	{
		if(par1ItemStack.itemID == ACMItem.scubaFins.itemID)
		{
			par3List.add("Swim faster in water");
		}
	}

	// Gets called from the tick handler
	public void action(EntityPlayer player) {

		if( ! player.worldObj.isRemote) {
			return;
		}
		if (Keyboard.isKeyDown(Minecraft.getMinecraft().gameSettings.keyBindForward.keyCode))
		{
			float MAG = 0.325F;
			double angle = player.getRotationYawHead();
			while (angle >= 0.0D) {
				angle -= 360.0D;
			}
			while (angle <= 0.0D) {
				angle += 360.0D;
			}
			angle += 90.0D;
			angle = Math.toRadians(angle);

			if (player.isInWater())
			{
				player.motionX = (Math.cos(angle) * MAG);
				player.motionZ = (Math.sin(angle) * MAG);
			}
			else
			{
				player.motionX = (Math.cos(angle) * 0.1000000014901161D);
				player.motionZ = (Math.sin(angle) * 0.1000000014901161D);
			}
		}
	}
	
}