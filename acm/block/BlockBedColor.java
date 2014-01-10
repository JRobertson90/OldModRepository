package acm.block;

import java.util.Random;

import net.minecraft.block.BlockBed;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.World;
import acm.item.ItemBedColor;

public class BlockBedColor extends BlockBed {

	// See the ItemBedColor class for these values.
	int color;
	
	public BlockBedColor(int par1, int color) {
		super(par1);
		this.color = color;
	}

	/**
     * Returns the ID of the items to drop on destruction.
     */
	@Override
    public int idDropped(int par1, Random par2Random, int par3)
    {
        return isBlockHeadOfBed(par1) ? 0 : ItemBedColor.items[color].itemID;
    }
	
    /**
     * only called by clickMiddleMouseButton , and passed to inventory.setCurrentItem (along with isCreative)
     */
    @Override
	public int idPicked(World par1World, int par2, int par3, int par4)
    {
        return ItemBedColor.items[color].itemID;
    }
    
    @Override
    public boolean isBed(World world, int x, int y, int z, EntityLivingBase player)
    {
    	return true;
    }
}
