package acm.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import acm.christmasChest.BlockChristmasChest;
import acm.craftingChest.BlockCraftingChest;

public class ACMBlock {
	public static final Block christmasChest = new BlockChristmasChest(3209,0).setHardness(2.5F).setUnlocalizedName("christmas_chest").setCreativeTab(CreativeTabs.tabBlock);
	public static Block craftingChest = new BlockCraftingChest(3210).setHardness(2.5F).setUnlocalizedName("crafting_chest").setCreativeTab(CreativeTabs.tabBlock);
	public static final Block sulfurOre = new Block(4095, Material.ground).setHardness(3.0F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("oreSulfur").setCreativeTab(CreativeTabs.tabBlock).setTextureName("acm:sulfur_ore");
	public static final Block potassiumOre = new Block(4094, Material.ground).setHardness(3.0F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("orePotassium").setCreativeTab(CreativeTabs.tabBlock).setTextureName("acm:potassium_ore");
	public static final Block netheraniumOre = new Block(4093, Material.ground).setHardness(3.0F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("oreNetheranium").setCreativeTab(CreativeTabs.tabBlock).setTextureName("acm:netheranium_ore");
}
