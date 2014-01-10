package acm.wearable;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
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
