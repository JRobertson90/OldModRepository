package acm.wearable;

import acm.item.ACMItem;
import net.minecraft.entity.Entity;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;

public class ItemStoneArmor extends ItemArmor {

	public ItemStoneArmor(int par1, EnumArmorMaterial par2EnumArmorMaterial, int par3, int par4) {
		super(par1, par2EnumArmorMaterial, par3, par4);
	}

	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type)
    {
		if(stack.itemID == ACMItem.stoneLegs.itemID)
		{
			return "acm:textures/models/armor/stone_layer_2.png";
		}
		return "acm:textures/models/armor/stone_layer_1.png";
	}
	
}