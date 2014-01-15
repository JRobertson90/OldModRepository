package acm.tool;

import java.util.List;

import acm.item.ACMItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemStack;

public class ItemNetherAxe extends ItemAxe implements ItemNetherTool{

	public ItemNetherAxe(int par1) {
		super(par1, ACMItem.netherTool);
	}
	
	@Override
	public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4)
	{
		par3List.add("Attack lights enemy on fire");
	}

}
