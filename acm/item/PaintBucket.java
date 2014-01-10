package acm.item;

import net.minecraft.block.Block;
import net.minecraft.world.World;

public class PaintBucket {
	
	public PaintBucket(World world) {
		
		int x = 0;
		int y = 0;
		int z = 0;
		int id = world.getBlockId(x, y, z);
		Block block = Block.blocksList[id];
	}
}