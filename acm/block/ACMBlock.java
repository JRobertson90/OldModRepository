package acm.block;

import net.minecraft.block.Block;
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
	
	public static final Block sulfurOre = new Block(4095, Material.ground).setHardness(3.0F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("oreSulfur").setCreativeTab(CreativeTabs.tabBlock).setTextureName("acm:sulfur_ore");
	public static final Block potassiumOre = new Block(4094, Material.ground).setHardness(3.0F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("orePotassium").setCreativeTab(CreativeTabs.tabBlock).setTextureName("acm:potassium_ore");
	public static final Block netheraniumOre = new Block(4093, Material.ground).setHardness(3.0F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("oreNetheranium").setCreativeTab(CreativeTabs.tabBlock).setTextureName("acm:netheranium_ore");
	
}