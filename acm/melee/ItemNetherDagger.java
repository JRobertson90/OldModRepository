package acm.melee;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import acm.item.ACMItem;

public class ItemNetherDagger extends ItemDagger{

	public ItemNetherDagger(int par1, boolean poisonous) {
		super(par1, poisonous, ACMItem.netherTool);
	}
	
	@Override
	public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4)
	{
		par3List.add("Attack lights enemy on fire");
	}

}
