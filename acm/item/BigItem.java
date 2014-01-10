package acm.item;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class BigItem extends ItemTool {

	public Block[] blocksEffectiveAgainst;
	
	protected BigItem(int par1, float par2,
			EnumToolMaterial par3EnumToolMaterial, Block[] par4ArrayOfBlock) {
		super(par1, par2, par3EnumToolMaterial, par4ArrayOfBlock);
	}

	public void blastAway(World world, int x, int y, int z) {
		
		for (int i = 0; i < this.blocksEffectiveAgainst.length; ++i)
        {
            if (this.blocksEffectiveAgainst[i].blockID == world.getBlockId(x, y, z))
            {
                world.destroyBlock(x, y, z, true);
            }
        }
	}
	
	@Override
	public boolean onBlockDestroyed(ItemStack par1ItemStack, World par2World, int par3, int par4, int par5, int par6, EntityLivingBase par7EntityLivingBase)
    {
        if (Block.blocksList[par3].getBlockHardness(par2World, par4, par5, par6) != 0.0D)
        {
            par1ItemStack.damageItem(1, par7EntityLivingBase);
        }
        
        //0=north, 1=east, 2=south, 3=west.... according to this code
        int whichDirectionFacing = MathHelper.floor_double(par7EntityLivingBase.rotationYaw * 4.0F / 360.0F + 2.5D) & 3;
        
        if(whichDirectionFacing == 0 || whichDirectionFacing == 2) {

          blastAway(par2World, par4 + 1, par5 - 1, par6);
          blastAway(par2World, par4 - 1, par5 - 1, par6);
          blastAway(par2World, par4 + 1, par5, par6);
          blastAway(par2World, par4 - 1, par5, par6);
          blastAway(par2World, par4 - 1, par5 + 1, par6);
          blastAway(par2World, par4 + 1, par5 + 1, par6);
        }
        else {
        	blastAway(par2World, par4, par5 - 1, par6 + 1);
            blastAway(par2World, par4, par5 - 1, par6 - 1);
            blastAway(par2World, par4, par5, par6 + 1);
            blastAway(par2World, par4, par5, par6 - 1);
            blastAway(par2World, par4, par5 + 1, par6 + 1);
            blastAway(par2World, par4, par5 + 1, par6 - 1);
        }
        
        blastAway(par2World, par4, par5 + 1, par6);
        blastAway(par2World, par4, par5 - 1, par6);
        
        return true;
    }
	
}
