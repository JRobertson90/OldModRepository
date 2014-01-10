package acm.wearable;

import java.util.List;

import acm.item.ACMItem;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;

public class ItemNetherArmor extends ItemArmor{

	public ItemNetherArmor(int par1, EnumArmorMaterial par2EnumArmorMaterial, int par3, int par4) {
		super(par1, par2EnumArmorMaterial, par3, par4);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type)
    {
		if(stack.itemID == ACMItem.netherLegs.itemID)
		{
			return "acm:textures/models/armor/nether_layer_2.png";
		}
		return "acm:textures/models/armor/nether_layer_1.png";
	}
	
	public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4)
	{
		par3List.add("25% chance to block any fire damage.");
		par3List.add("This effect stacks");
		par3List.add("Full immunity with the complete set");
	}
}
