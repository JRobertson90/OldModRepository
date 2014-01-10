package acm.tool;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemStack;
import acm.item.ACMItem;

public class ItemNetherHoe extends ItemHoe {

	public ItemNetherHoe(int par1) {
		super(par1, ACMItem.netherTool);
	}

	@Override
	public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4)
	{
		par3List.add("Attack lights enemy on fire");
	}
}
