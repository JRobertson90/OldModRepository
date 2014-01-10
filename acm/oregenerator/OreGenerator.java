package acm.oregenerator;

import java.util.Random;

import acm.block.ACMBlock;
import acm.item.ACMItem;
import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import cpw.mods.fml.common.IWorldGenerator;

public class OreGenerator implements IWorldGenerator{

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world,
			IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
		switch(world.provider.dimensionId){
		case -1:
		    generateNether(world, random, chunkX * 16, chunkZ * 16);
		    break;
		case 0:
		    generateSurface(world, random, chunkX * 16, chunkZ * 16);
		    break;
		case 1:
		    generateEnd(world, random, chunkX * 16, chunkZ * 16);
		    break;
		default:
			break;
		}
	}
	private static void generateEnd(World world, Random rand, int chunkX, int chunkZ) {/*No end generation happening*/}

	private static void generateSurface(World world, Random rand, int chunkX, int chunkZ) {
		for(int k = 0; k < 20; k++){
        	int firstBlockXCoord = chunkX + rand.nextInt(16);
        	int firstBlockYCoord = rand.nextInt(255);
        	int firstBlockZCoord = chunkZ + rand.nextInt(16);
        	
        	(new WorldGenMinable(ACMBlock.sulfurOre.blockID, 15)).generate(world, rand, firstBlockXCoord, firstBlockYCoord, firstBlockZCoord);
        }
		for(int k = 0; k < 20; k++){
        	int firstBlockXCoord = chunkX + rand.nextInt(16);
        	int firstBlockYCoord = rand.nextInt(255);
        	int firstBlockZCoord = chunkZ + rand.nextInt(16);
        	
        	(new WorldGenMinable(ACMBlock.potassiumOre.blockID, 15)).generate(world, rand, firstBlockXCoord, firstBlockYCoord, firstBlockZCoord);
        }
	}

	private static void generateNether(World world, Random rand, int chunkX, int chunkZ)
	{
		for(int k = 0; k < 5; k++)
		{
			int firstBlockXCoord = chunkX + rand.nextInt(16);
        	int firstBlockYCoord = rand.nextInt(90);
        	int firstBlockZCoord = chunkZ + rand.nextInt(16);
        	
        	(new WorldGenMinable(ACMBlock.netheraniumOre.blockID, 3, Block.netherrack.blockID)).generate(world, rand, firstBlockXCoord, firstBlockYCoord, firstBlockZCoord);
		}
	}

}
