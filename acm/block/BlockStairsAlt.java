package acm.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockStairs;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.world.ColorizerFoliage;
import net.minecraft.world.IBlockAccess;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockStairsAlt extends BlockStairs {

	boolean leaves = false;
	
	protected BlockStairsAlt(int par1, Block par2Block, int par3) {
		
		super(par1, par2Block, par3);
		setCreativeTab(CreativeTabs.tabBlock);
		if(par2Block instanceof BlockLeaves) {
			leaves = true;
		}
	}
	
	@SideOnly(Side.CLIENT)
    public int getBlockColor()
    {
		if(leaves) {
			double d0 = 0.5D;
			double d1 = 1.0D;
			return ColorizerFoliage.getFoliageColor(d0, d1);
		}
		else {
			return super.getBlockColor();
		}
    }
	
	@SideOnly(Side.CLIENT)
    /**
     * Returns the color this block should be rendered. Used by leaves.
     */
    public int getRenderColor(int par1)
    {
		if(leaves) {
			return (par1 & 3) == 1 ? ColorizerFoliage.getFoliageColorPine() : ((par1 & 3) == 2 ? ColorizerFoliage.getFoliageColorBirch() : ColorizerFoliage.getFoliageColorBasic());
		}
		else {
			return 16777215; 
		}
    }
	
	@SideOnly(Side.CLIENT)

    /**
     * Returns a integer with hex for 0xrrggbb with this color multiplied against the blocks color. Note only called
     * when first determining what to render.
     */
    public int colorMultiplier(IBlockAccess par1IBlockAccess, int par2, int par3, int par4)
    {
		if(!leaves) {
			return super.colorMultiplier(par1IBlockAccess, par2, par3, par4);
		}
		else {
			int l = par1IBlockAccess.getBlockMetadata(par2, par3, par4);

	        if ((l & 3) == 1)
	        {
	            return ColorizerFoliage.getFoliageColorPine();
	        }
	        else if ((l & 3) == 2)
	        {
	            return ColorizerFoliage.getFoliageColorBirch();
	        }
	        else
	        {
	            int i1 = 0;
	            int j1 = 0;
	            int k1 = 0;

	            for (int l1 = -1; l1 <= 1; ++l1)
	            {
	                for (int i2 = -1; i2 <= 1; ++i2)
	                {
	                    int j2 = par1IBlockAccess.getBiomeGenForCoords(par2 + i2, par4 + l1).getBiomeFoliageColor();
	                    i1 += (j2 & 16711680) >> 16;
	                    j1 += (j2 & 65280) >> 8;
	                    k1 += j2 & 255;
	                }
	            }

	            return (i1 / 9 & 255) << 16 | (j1 / 9 & 255) << 8 | k1 / 9 & 255;
	        }	
		}
    }

}