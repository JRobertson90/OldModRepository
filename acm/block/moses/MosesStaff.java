package acm.block.moses;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

import acm.block.ACMBlock;
import net.minecraft.block.Block;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class MosesStaff {

	//------------------------
	//  Fields
	//------------------------
	private static final int FORWARD_LENGTH = 64;
	private static final int SIDE_LENGTH = 2;
	private static final int BACKWARD_LENGTH = 2;
	
	private static ChunkCoordinates staffLocation;
	private static Map<ChunkCoordinates, Boolean> canPlaceBlock;
	private static Queue<ChunkCoordinates> diffusionQueue;
	
	private static int boundXPos;
	private static int boundXNeg;
	private static int boundZPos;
	private static int boundZNeg;
	
	private static int greatestHeightSoFar;
	
	//------------------------
	//  Initialize
	//------------------------
	private static void initialize(World world, int x, int y, int z, boolean placed, int direction) {
		
		staffLocation = new ChunkCoordinates(x,y,z);
		canPlaceBlock = new HashMap<ChunkCoordinates, Boolean>();
		diffusionQueue = new LinkedList<ChunkCoordinates>();
		canPlaceBlock.put(new ChunkCoordinates(x,y,z), false);
		greatestHeightSoFar = 0;
		addNeighborsToQueue(x,y,z);
		adjustBoundaries(direction);
	}

	//------------------------
	//  Replace Water
	//------------------------
	// Replaces some air blocks nearby the staff with water to ensure the water caves in all the way.
	private static void replaceWater(World world) {

		for(ChunkCoordinates cc : canPlaceBlock.keySet()) {
			if(cc.posY == greatestHeightSoFar && world.isAirBlock(cc.posX, cc.posY, cc.posZ) &&
					cc.posX > cc.posX - SIDE_LENGTH && cc.posX < cc.posX + SIDE_LENGTH &&
					cc.posZ > cc.posZ - SIDE_LENGTH && cc.posZ < cc.posZ + SIDE_LENGTH) {
				world.setBlock(cc.posX, cc.posY, cc.posZ, 8, 0, 0x0);
				world.markBlockForUpdate(cc.posX, cc.posY, cc.posZ);
			}
		}
		world.notifyBlocksOfNeighborChange(staffLocation.posX, staffLocation.posY, staffLocation.posZ, world.getBlockMetadata(staffLocation.posX, staffLocation.posY, staffLocation.posZ));
	}

	//------------------------
	//  Adjust Boundaries
	//------------------------
	// Directions: 0 = south, 1 = west, 2 = north, 3 = east
	// South = Pos Z, West = Neg X, North = Neg Z, East = Pos X
	private static void adjustBoundaries(int direction) {
		
		switch(direction) {
		case 0: {
			boundZPos = FORWARD_LENGTH;
			boundZNeg = BACKWARD_LENGTH;
			boundXNeg = SIDE_LENGTH;
			boundXPos = SIDE_LENGTH;
			break;
		}
		case 1: {
			boundXNeg = FORWARD_LENGTH;
			boundXPos = BACKWARD_LENGTH;
			boundZNeg = SIDE_LENGTH;
			boundZPos = SIDE_LENGTH;
			break;
		}
		case 2: {
			boundZNeg = FORWARD_LENGTH;
			boundZPos = BACKWARD_LENGTH;
			boundXNeg = SIDE_LENGTH;
			boundXPos = SIDE_LENGTH;
			break;
		}
		case 3: {
			boundXPos = FORWARD_LENGTH;
			boundXNeg = BACKWARD_LENGTH;
			boundZNeg = SIDE_LENGTH;
			boundZPos = SIDE_LENGTH;
			break;
		}
		}
	}

	//------------------------
	//  Diffuse Air
	//------------------------
	public static void diffuseAir(World world, int x, int y, int z, boolean placed, int direction) {

		if(!world.isRemote) {
			
			initialize(world,x,y,z,placed,direction);
			
			while(diffusionQueue.size() > 0) {
				
				ChunkCoordinates c = diffusionQueue.remove();
				diffuseAirRec(world, c.posX, c.posY, c.posZ, placed, direction);
			}
			
			if(!placed) {
				replaceWater(world);
			}
		}
	}

	//--------------------------
	//  Diffuse Air Recursive
	//--------------------------
	private static void diffuseAirRec(World world, int x, int y, int z, boolean placed, int direction) {

		if( ! insideBoundaries(world,x,y,z) || alreadyVisited(x,y,z)) {
			return;
		}

		Block block = Block.blocksList[world.getBlockId(x, y, z)];
		
		if (block != null && canPlaceBlock(world,x,y,z)) {
			canPlaceBlock.put(new ChunkCoordinates(x,y,z), true);
			if(y > greatestHeightSoFar) {
				greatestHeightSoFar = y;
			}
		}
		else {
			canPlaceBlock.put(new ChunkCoordinates(x,y,z), false);
			return;
		}
		
		placeInvisMosesBlock(world, x, y, z, placed);
		addNeighborsToQueue(x,y,z);
	}

	//------------------------
	//  Already Visited
	//------------------------
	private static boolean alreadyVisited(int x, int y, int z) {
		
		return canPlaceBlock.get(new ChunkCoordinates(x,y,z)) != null;
	}

	//------------------------
	//  Inside Boundaries
	//------------------------
	private static boolean insideBoundaries(World world,int x, int y, int z) {

		if(x < staffLocation.posX - boundXNeg ||
			x > staffLocation.posX + boundXPos ||
			z < staffLocation.posZ - boundZNeg ||
			z > staffLocation.posZ + boundZPos ||
			y < 0 ||
			y >= world.getHeight() ) {
			return false;
		}
		return true;
	}

	//------------------------
	//  Can Place Block
	//------------------------
	private static boolean canPlaceBlock(World world, int x, int y, int z) {
		
		int id = world.getBlockId(x, y, z);
		if(id == Block.waterMoving.blockID || id == Block.waterStill.blockID || id == ACMBlock.mosesAirBlock.blockID) {
			return true;
		}
		return false;
	}

	//----------------------------
	//  Add Neighbors to Queue
	//----------------------------
	private static void addNeighborsToQueue(int x, int y, int z) {
		
		diffusionQueue.add(new ChunkCoordinates(x,y,z+1));
		diffusionQueue.add(new ChunkCoordinates(x,y,z-1));
		diffusionQueue.add(new ChunkCoordinates(x,y+1,z));
		diffusionQueue.add(new ChunkCoordinates(x,y-1,z));
		diffusionQueue.add(new ChunkCoordinates(x+1,y,z));
		diffusionQueue.add(new ChunkCoordinates(x-1,y,z));
	}

	//------------------------
	//  Place Moses Block
	//------------------------
	private static void placeInvisMosesBlock(World world, int x, int y, int z, boolean blockPlaced) {
		
		if (blockPlaced) {
			int blockID = ACMBlock.mosesAirBlock.blockID;
			world.setBlock(x, y, z, blockID, 0, 0x0);
			world.markBlockForUpdate(x, y, z);
		}
		else {
			world.setBlock(x, y, z, 0, 0, 0x0);
			world.markBlockForUpdate(x, y, z);
			world.notifyBlocksOfNeighborChange(x, y, z, 0);
		}
	}
	
}