//------------------------------------------------------
//
//   Greg's Lighting - Floodlight Beam Block
//
//------------------------------------------------------

package acm.lighting;

import java.util.ArrayList;
import java.util.Random;
import net.minecraft.block.*;
import net.minecraft.block.material.*;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.texture.*;
import net.minecraft.tileentity.*;
import net.minecraft.world.*;
import net.minecraft.util.*;

public class BlockLightAir extends Block {

	public BlockLightAir(int id) {
		super(id, Material.vine);
		setLightValue(1.0F);
		setLightOpacity(0);
		setHardness(-1);
		setResistance(6000000);
		if (Floodlight.debugBeamBlocks)
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
		return true;
	}
	
	@Override
	public boolean canBeReplacedByLeaves(World world, int x, int y, int z) {
		return true;
	}
	
	@Override
	public boolean isLeaves(World world, int x, int y, int z) {
		// This is a bit screwy, but it's needed so that trees are not prevented from growing
		// near a floodlight beam.
		return true;
	}

	@Override
	public boolean renderAsNormalBlock() {
		return false;
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
	public int getRenderType() {
		return -1;
	}

	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, int neighborBlockID) {
//		Floodlight.diffuseLight(world, x, y, z);
	}
	
	@Override
	public Icon getBlockTexture(IBlockAccess world, int x, int y, int z, int side) {
		return null;
	}

}
