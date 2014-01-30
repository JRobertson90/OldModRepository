package acm.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import acm.christmasChest.BlockChristmasChest;
import acm.craftingChest.BlockCraftingChest;
import acm.lighting.BlockLight;
import acm.lighting.BlockLightAir;

public class ACMBlock {
	
	public static final Block christmasChest = new BlockChristmasChest(3209,0).setHardness(2.5F).setUnlocalizedName("christmas_chest").setCreativeTab(CreativeTabs.tabBlock);
	public static Block craftingChest = new BlockCraftingChest(3210).setHardness(2.5F).setUnlocalizedName("crafting_chest").setCreativeTab(CreativeTabs.tabBlock);
	public static final Block lightBlock = new BlockLight(3211).setHardness(1.5F).setUnlocalizedName("lightBlock").setCreativeTab(CreativeTabs.tabBlock).setTextureName("acm:light_block");
	public static BlockLightAir floodlightBeam = new BlockLightAir(3212);
	public static final Block doorGlass = new BlockGlassDoor(3250).setHardness(1.5F).setUnlocalizedName("glassDoor").setTextureName("acm:door_glass");
	
	public static BlockStairs glassStairs = (BlockStairs) new BlockStairsAlt(3251,Block.glass,0).setUnlocalizedName("glassStairs").setLightOpacity(0).setHardness(0.3F).setStepSound(Block.soundGlassFootstep);
	public static BlockStairs dirtStairs = (BlockStairs) new BlockStairsAlt(3268, Block.dirt, 0).setUnlocalizedName("dirtStairs").setHardness(0.5F).setStepSound(Block.soundGravelFootstep);
	
	public static BlockStairs woodOakStairs = (BlockStairs) new BlockStairsAlt(3269, Block.wood, 0).setUnlocalizedName("woodOakStairs").setHardness(2.0F).setStepSound(Block.soundWoodFootstep);
	public static BlockStairs woodSpruceStairs = (BlockStairs) new BlockStairsAlt(3270, Block.wood, 1).setUnlocalizedName("spruceOakStairs").setHardness(2.0F).setStepSound(Block.soundWoodFootstep);
	public static BlockStairs woodBirchStairs = (BlockStairs) new BlockStairsAlt(3271, Block.wood, 2).setUnlocalizedName("birchOakStairs").setHardness(2.0F).setStepSound(Block.soundWoodFootstep);
	public static BlockStairs woodJungleStairs = (BlockStairs) new BlockStairsAlt(3272, Block.wood, 3).setUnlocalizedName("jungleOakStairs").setHardness(2.0F).setStepSound(Block.soundWoodFootstep);
	
	public static BlockStairs leavesOakStairs = (BlockStairs) new BlockStairsAlt(3273, Block.leaves, 0).setUnlocalizedName("leavesOakStairs").setHardness(0.2F).setLightOpacity(1).setStepSound(Block.soundGrassFootstep);
	public static BlockStairs leavesSpruceStairs = (BlockStairs) new BlockStairsAlt(3274, Block.leaves, 1).setUnlocalizedName("leavesSpruceStairs").setHardness(0.2F).setLightOpacity(1).setStepSound(Block.soundGrassFootstep);
	public static BlockStairs leavesBirchStairs = (BlockStairs) new BlockStairsAlt(3275, Block.leaves, 2).setUnlocalizedName("leavesBirchStairs").setHardness(0.2F).setLightOpacity(1).setStepSound(Block.soundGrassFootstep);
	public static BlockStairs leavesJungleStairs = (BlockStairs) new BlockStairsAlt(3276, Block.leaves, 3).setUnlocalizedName("leavesJungleStairs").setHardness(0.2F).setLightOpacity(1).setStepSound(Block.soundGrassFootstep);
	
	public static final Block sulfurOre = new Block(4095, Material.ground).setHardness(3.0F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("oreSulfur").setCreativeTab(CreativeTabs.tabBlock).setTextureName("acm:sulfur_ore");
	public static final Block potassiumOre = new Block(4094, Material.ground).setHardness(3.0F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("orePotassium").setCreativeTab(CreativeTabs.tabBlock).setTextureName("acm:potassium_ore");
	public static final Block netheraniumOre = new Block(4093, Material.ground).setHardness(3.0F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("oreNetheranium").setCreativeTab(CreativeTabs.tabBlock).setTextureName("acm:netheranium_ore");

}