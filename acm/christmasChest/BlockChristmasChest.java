package acm.christmasChest;

import java.util.Random;

import net.minecraft.block.BlockChest;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import acm.block.ACMBlock;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockChristmasChest extends BlockChest {

	private Icon[] icons = new Icon[2];
    private static String[] sideNames = { "top", "side" };
    private static int[] sideMapping = { 0, 0, 1, 1, 1, 1, 1 };
    
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister par1IconRegister)
	{
		int i = 0;
		for (String s : sideNames) {
			icons[i++] = par1IconRegister.registerIcon(String.format("acm:christmas_chest_%s",s));
		}
	}

    @SideOnly(Side.CLIENT)
    @Override
    public Icon getIcon(int i, int j)
    {
    	return icons[sideMapping[i]];
    }

    public BlockChristmasChest(int par1, int par2) {
    	super(par1, par2);
    }

    /**
     * Returns the ID of the items to drop on destruction.
     */
    @Override
	public int idDropped(int par1, Random par2Random, int par3) {
    	return ACMBlock.christmasChest.blockID;
    }

    /**
     * Returns a new instance of a block's tile entity class. Called on placing the block.
     */
    @Override
	public TileEntity createNewTileEntity(World par1World) {
        return new TileEntityChristmasChest();
    }
    
    @Override
    public boolean isOpaqueCube()
    {
        return false;
    }

    @Override
    public boolean renderAsNormalBlock()
    {
        return false;
    }

    @Override
    public int getRenderType()
    {
        return -1;
    }
    
}
