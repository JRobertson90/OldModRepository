package acm.lighting;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.World;
import acm.block.ACMBlock;

public class Floodlight {

	public static final int maxRange = 8;

	// I'm using this coordinate as a marker to know when to increment the distance
	private static final ChunkCoordinates marker = new ChunkCoordinates(Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE);
	
	public static boolean debugBeamBlocks = false;
	public static boolean debugBeamBlockPlacement = false;
	
	private static Map<ChunkCoordinates, Boolean> isAirBlock = new HashMap<ChunkCoordinates, Boolean>();
	private static ChunkCoordinates lightSource;
	private static Queue<ChunkCoordinates> diffusionQueue;
	private static int distance;
	
	static void diffuseLight(World world, int x, int y, int z, boolean placed) {

		if(!world.isRemote) {
			
			initialize(x,y,z);
			
			addNeighborsToQueue(x,y,z);
			diffusionQueue.add(marker);
			isAirBlock.put(new ChunkCoordinates(x,y,z), false);
			
			while(diffusionQueue.size() > 0) {
				
				ChunkCoordinates c = diffusionQueue.remove();
				
				if(c.equals(marker)) {
					distance++;
					if(diffusionQueue.size() > 0) {
						diffusionQueue.add(marker);		
					}
				}
				else {
					diffuseLightRec(world, c.posX, c.posY, c.posZ, placed);
				}
			}
		}
	}

	private static void initialize(int x, int y, int z) {
		
		isAirBlock = new HashMap<ChunkCoordinates, Boolean>();
		lightSource = new ChunkCoordinates(x,y,z);
		diffusionQueue = new LinkedList<ChunkCoordinates>();
		distance = 1;
	}

	static void diffuseLightRec(World world, int x, int y, int z, boolean placed) {

		if(distance > maxRange) {
			return;
		}

		if (y < 0 || y >= world.getHeight()) {
			return;
		}

		Boolean b = isAirBlock.get(new ChunkCoordinates(x,y,z));
		if(b != null) {
			return; // Because it means we've already checked this block
		}

		Block block = Block.blocksList[world.getBlockId(x, y, z)];
		
		if (block != null && ! block.isAirBlock(world, x, y, z)) {
			isAirBlock.put(new ChunkCoordinates(x,y,z), false);
			return;
		}
		else {
			isAirBlock.put(new ChunkCoordinates(x,y,z), true);
		}

		placeInvisLightBlock(world, x, y, z, placed);
		
		addNeighborsToQueue(x,y,z);
	}

	private static void addNeighborsToQueue(int x, int y, int z) {
		
		diffusionQueue.add(new ChunkCoordinates(x,y,z+1));
		diffusionQueue.add(new ChunkCoordinates(x,y,z-1));
		diffusionQueue.add(new ChunkCoordinates(x,y+1,z));
		diffusionQueue.add(new ChunkCoordinates(x,y+1,z+1));
		diffusionQueue.add(new ChunkCoordinates(x,y+1,z-1));
		diffusionQueue.add(new ChunkCoordinates(x,y-1,z));
		diffusionQueue.add(new ChunkCoordinates(x,y-1,z+1));
		diffusionQueue.add(new ChunkCoordinates(x,y-1,z-1));
		
		diffusionQueue.add(new ChunkCoordinates(x+1,y,z));
		diffusionQueue.add(new ChunkCoordinates(x+1,y,z+1));
		diffusionQueue.add(new ChunkCoordinates(x+1,y,z-1));
		diffusionQueue.add(new ChunkCoordinates(x+1,y+1,z));
		diffusionQueue.add(new ChunkCoordinates(x+1,y+1,z+1));
		diffusionQueue.add(new ChunkCoordinates(x+1,y+1,z-1));
		diffusionQueue.add(new ChunkCoordinates(x+1,y-1,z));
		diffusionQueue.add(new ChunkCoordinates(x+1,y-1,z+1));
		diffusionQueue.add(new ChunkCoordinates(x+1,y-1,z-1));
		
		diffusionQueue.add(new ChunkCoordinates(x-1,y,z));
		diffusionQueue.add(new ChunkCoordinates(x-1,y,z+1));
		diffusionQueue.add(new ChunkCoordinates(x-1,y,z-1));
		diffusionQueue.add(new ChunkCoordinates(x-1,y+1,z));
		diffusionQueue.add(new ChunkCoordinates(x-1,y+1,z+1));
		diffusionQueue.add(new ChunkCoordinates(x-1,y+1,z-1));
		diffusionQueue.add(new ChunkCoordinates(x-1,y-1,z));
		diffusionQueue.add(new ChunkCoordinates(x-1,y-1,z+1));
		diffusionQueue.add(new ChunkCoordinates(x-1,y-1,z-1));
	}

	static void placeInvisLightBlock(World world, int x, int y, int z, boolean blockPlaced) {
		
		Block block = Block.blocksList[world.getBlockId(x, y, z)];

		if (block == null && blockPlaced) {

			block = ACMBlock.floodlightBeam;
			world.setBlock(x, y, z, block.blockID, 0, 0x0);
			world.markBlockForUpdate(x, y, z);
			world.updateAllLightTypes(x, y, z);
		}
		
		if(!blockPlaced) {
			world.setBlock(x,y,z, 0, 0, 0x0);
			world.markBlockForUpdate(x,y,z);
		}
		
//		TEBlockLightAir te = getBeamTileEntity(world, x, y, z);
//		if (te != null) {
//
//			if(blockPlaced) {
//				te.numOfSources++;
//			}
//			else {
//				te.numOfSources--;
//				checkIfShouldStay(te);
//			}
//		}
	}

//	public static void checkIfShouldStay(World world, int x, int y, int z) {
//		
//		TEBlockLightAir te = getBeamTileEntity(world, x, y, z);
//		if (te != null) {
//			checkIfShouldStay(te);
//		}
//	}
//	public static void checkIfShouldStay(TEBlockLightAir te) {
//		
//		if (te.numOfSources == 0) {
//			te.worldObj.setBlock(te.xCoord, te.yCoord, te.zCoord, 0, 0, 0x0);
//			te.worldObj.markBlockForUpdate(te.xCoord, te.yCoord, te.zCoord);
//		}	
//	}
//
//	static TEBlockLightAir getBeamTileEntity(World world, int x, int y, int z) {
//		TileEntity te = world.getBlockTileEntity(x, y, z);
//		if (te instanceof TEBlockLightAir)
//			return (TEBlockLightAir)te;
//		else
//			return null;
//	}

}