package acm.block.moses;

import java.util.ArrayList;
import java.util.Random;

import acm.lighting.Floodlight;
import net.minecraft.block.*;
import net.minecraft.block.material.*;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.texture.*;
import net.minecraft.tileentity.*;
import net.minecraft.world.*;
import net.minecraft.util.*;

public class BlockMosesAir extends Block {

	private Icon icon;
	
	private static final boolean debugMosesBlocks = false;
	
	public BlockMosesAir(int id) {
		super(id, Material.vine);
		setLightValue(1.0F);
		setLightOpacity(0);
		setHardness(-1);
		setResistance(6000000);
		if (debugMosesBlocks)
			setBlockBounds(3/8.0F, 3/8.0F, 3/8.0F, 5/8.0F, 5/8.0F, 5/8.0F);
		else
			setBlockBounds(0F, 0F, 0F, 0F, 0F, 0F);
	}
	
	@Override
	public boolean isOpaqueCube() {
		return false;
	}
    
	@Override
	public boolean isAirBlock(World world, int x, int y, int z)  {
		return false;
	}
	
	@Override
	public boolean canBeReplacedByLeaves(World world, int x, int y, int z) {
		return true;
	}
	
	@Override
	public boolean renderAsNormalBlock() {
		return debugMosesBlocks;
	}
	
	@Override
	public int getRenderType() {
		if (debugMosesBlocks)
			return super.getRenderType();
		else
			return -1;
	}

	
	@Override
	public boolean isLeaves(World world, int x, int y, int z) {
		return true;
	}

	@Override
	public void registerIcons(IconRegister reg) {
		if(debugMosesBlocks) {
			icon = reg.registerIcon("acm:moses_staff");
		}
	}
	
	@Override
	public Icon getBlockTexture(IBlockAccess world, int x, int y, int z, int side) {
		if (debugMosesBlocks)
			return icon;
		else
			return null;
	}

	@Override
	public int quantityDropped(Random par1Random) {
		return 0;
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int par2, int par3, int par4) {
		return null;
	}

	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, int neighborBlockID) {
		
	}
	
}
