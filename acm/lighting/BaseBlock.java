//------------------------------------------------------------------------------------------------
//
//   Greg's Mod Base - Generic Block
//
//------------------------------------------------------------------------------------------------

package acm.lighting;

import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;

public class BaseBlock extends BaseContainerBlock<TileEntity> {

	public BaseBlock(int id, Material material) {
		super(id, material, null);
	}
}