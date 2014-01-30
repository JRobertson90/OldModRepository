package acm.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockStairs;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class BlockStairsWool extends BlockStairs {

	protected BlockStairsWool(int par1, Block par2Block, int par3) {
		super(par1, par2Block, par3);
	}

	private final static int START_ID = 3252;
	public final static String colors[] = { "White", "Orange", "Magenta", "Light Blue", "Yellow", "Lime",
		"Pink", "Gray", "Light Gray", "Cyan", "Purple", "Blue", "Brown", "Green", "Red", "Black"
	};

	public static BlockStairs[] woolStairs = new BlockStairs[colors.length];

	static {
		
		for(int i = 0; i < colors.length; i++) {
			woolStairs[i] = new BlockStairsWool(START_ID + i, Block.cloth, i);
			woolStairs[i].setUnlocalizedName(colors[i]+"WoolStairs");
			woolStairs[i].setHardness(0.8F).setStepSound(Block.soundClothFootstep);
		}
	}

	//----------------------
	//  Register
	//----------------------
	public static void register() {
		
		for(int i = 0; i < colors.length; i++) {
			GameRegistry.registerBlock(woolStairs[i], colors[i] + "WoolStairs");
			LanguageRegistry.addName(woolStairs[i], colors[i] + " Wool Stairs");
		}
	}

	//----------------------
	//  Recipes
	//----------------------
	public static void loadRecipes() {
		
		for(int i = 0; i < colors.length; i++) {
			GameRegistry.addRecipe(new ItemStack(woolStairs[i], 4), new Object[]{ "  #", " ##", "###", '#', new ItemStack(Block.cloth, 1 ,i) });
		}
	}
	
}
