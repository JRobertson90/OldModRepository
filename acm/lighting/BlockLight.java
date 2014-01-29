package acm.lighting;

import java.util.ArrayList;

import net.minecraft.block.*;
import net.minecraft.block.material.*;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.texture.*;
import net.minecraft.tileentity.*;
import net.minecraft.world.*;
import net.minecraft.util.*;
import net.minecraftforge.common.*;

public class BlockLight<TE extends TileEntity> extends BaseContainerBlock<TE> {

	Icon icon;
	
	public BlockLight(int id) {
		super(id, Material.cloth, null);
	}
	
	@Override
	/**
     * From the specified side and block metadata retrieves the blocks texture. Args: side, metadata
     */
    public Icon getIcon(int par1, int par2)
    {
        return icon;
    }

	@Override
	public void registerIcons(IconRegister reg) {
		icon = reg.registerIcon("acm:light_block");
	}
	
	@Override
	public void onBlockAdded(World world, int x, int y, int z) {
		
		if (!world.isRemote) {
			Floodlight.diffuseLight(world, x, y, z, true);
		}
	}

	@Override
	public void breakBlock(World world, int x, int y, int z, int id, int metadata) {
		
		Floodlight.diffuseLight(world, x, y, z, false);
		super.breakBlock(world, x, y, z, id, metadata);
	}

	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, int neighborBlockID) {
		Floodlight.checkIfShouldStay(world, x, y, z);
	}
	
}
