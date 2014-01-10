package acm.melee;

import java.util.List;

import acm.item.ACMItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;

public class ItemNetherSword extends ItemSword{

	public ItemNetherSword(int par1) {
		super(par1, ACMItem.netherTool);
	}

	@Override
	public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4)
	{
		par3List.add("Attack lights enemy on fire");
	}
}
