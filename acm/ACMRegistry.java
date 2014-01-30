package acm;

import net.minecraft.block.BlockStairs;
import acm.block.ACMBlock;
import acm.block.BlockStairsWool;
import acm.christmasChest.TileEntityChristmasChest;
import acm.craftingChest.TileEntityCraftingChest;
import acm.entity.ArrowExplosive;
import acm.entity.ArrowFar;
import acm.entity.EntityArrowPort;
import acm.entity.EntityArrowTorch;
import acm.item.ACMItem;
import acm.item.ItemBedColor;
import acm.oregenerator.OreGenerator;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class ACMRegistry {
	
	public static void load()
	{

		//-------------------
		//   I T E M
		//-------------------
		BlockStairsWool.register();
		
		GameRegistry.registerBlock(ACMBlock.glassStairs, "glass_stairs");
		GameRegistry.registerBlock(ACMBlock.dirtStairs, "dirt_stairs");
		GameRegistry.registerBlock(ACMBlock.woodOakStairs, "wood_oak_stairs");
		GameRegistry.registerBlock(ACMBlock.woodSpruceStairs, "wood_spruce_stairs");
		GameRegistry.registerBlock(ACMBlock.woodBirchStairs, "wood_birch_stairs");
		GameRegistry.registerBlock(ACMBlock.woodJungleStairs, "wood_jungle_stairs");
		GameRegistry.registerBlock(ACMBlock.leavesOakStairs, "leaves_oak_stairs");
		GameRegistry.registerBlock(ACMBlock.leavesSpruceStairs, "leaves_spruce_stairs");
		GameRegistry.registerBlock(ACMBlock.leavesBirchStairs, "leaves_birch_stairs");
		GameRegistry.registerBlock(ACMBlock.leavesJungleStairs, "leaves_jungle_stairs");
		
		LanguageRegistry.addName(ACMBlock.glassStairs, "Glass Stairs");
		LanguageRegistry.addName(ACMBlock.dirtStairs, "Dirt Stairs");
		LanguageRegistry.addName(ACMBlock.woodOakStairs, "Oak Wood Stairs");
		LanguageRegistry.addName(ACMBlock.woodSpruceStairs, "Spruce Wood Stairs");
		LanguageRegistry.addName(ACMBlock.woodBirchStairs, "Birch Wood Stairs");
		LanguageRegistry.addName(ACMBlock.woodJungleStairs, "Jungle Wood Stairs");
		LanguageRegistry.addName(ACMBlock.leavesOakStairs, "Oak Leaf Stairs");
		LanguageRegistry.addName(ACMBlock.leavesSpruceStairs, "Spruce Leaf Stairs");
		LanguageRegistry.addName(ACMBlock.leavesBirchStairs, "Birch Leaf Stairs");
		LanguageRegistry.addName(ACMBlock.leavesJungleStairs, "Jungle Leaf Stairs");
		
		ItemBedColor.register();
		
		GameRegistry.registerBlock(ACMBlock.lightBlock, "light_block");
		LanguageRegistry.addName(ACMBlock.lightBlock, "Light Block");
		
		GameRegistry.registerBlock(ACMBlock.floodlightBeam, "light_block_beam");
		LanguageRegistry.addName(ACMBlock.floodlightBeam, "Light Block Beam");
		
		GameRegistry.registerItem(ACMItem.glassDoor, "glassDoor");
		
		GameRegistry.registerItem(ACMItem.diamondSwordAlt, "diamond_sword_alt");
		GameRegistry.registerItem(ACMItem.diamondSwordAlt2, "diamond_sword_alt_2");
		GameRegistry.registerItem(ACMItem.diamondSwordAlt3, "zelda_sword");
		GameRegistry.registerItem(ACMItem.diamondSwordAlt4, "energy_sword");
		GameRegistry.registerItem(ACMItem.diamondSwordAlt5, "lich_king_sword");
		
		GameRegistry.registerItem(ACMItem.blueSword, "blue_sword");
		GameRegistry.registerItem(ACMItem.bluePickaxe, "blue_pickaxe");
		GameRegistry.registerItem(ACMItem.blueAxe, "blue_axe");
		GameRegistry.registerItem(ACMItem.blueShovel, "blue_shovel");
		GameRegistry.registerItem(ACMItem.blueHoe, "blue_hoe");
		GameRegistry.registerItem(ACMItem.blueHammer, "blue_hammer");
		GameRegistry.registerItem(ACMItem.blueShield, "blue_shield");
		GameRegistry.registerItem(ACMItem.blueDagger, "blue_dagger");
		GameRegistry.registerItem(ACMItem.bluePoisonDagger, "blue_poison_dagger");
		
		GameRegistry.registerItem(ACMItem.redSword, "red_sword");
		GameRegistry.registerItem(ACMItem.redPickaxe, "red_pickaxe");
		GameRegistry.registerItem(ACMItem.redAxe, "red_axe");
		GameRegistry.registerItem(ACMItem.redShovel, "red_shovel");
		GameRegistry.registerItem(ACMItem.redHoe, "red_hoe");
		GameRegistry.registerItem(ACMItem.redHammer, "red_hammer");
		GameRegistry.registerItem(ACMItem.redShield, "red_shield");
		GameRegistry.registerItem(ACMItem.redDagger, "red_dagger");
		GameRegistry.registerItem(ACMItem.redPoisonDagger, "red_poison_dagger");
		
		GameRegistry.registerItem(ACMItem.scubaFins, "blue_fins");
		
		GameRegistry.registerItem(ACMItem.arrowPort, "arrow_port");
		GameRegistry.registerItem(ACMItem.bowPort, "bow_port");
		GameRegistry.registerBlock(ACMBlock.christmasChest, "christmas_chest");
		GameRegistry.registerItem(ACMItem.arrowTorch, "arrow_torch");
		GameRegistry.registerItem(ACMItem.bowTorch, "bow_torch");
//		GameRegistry.registerItem(ACMItem.superBow, "bow_super");
		GameRegistry.registerBlock(ACMBlock.craftingChest, "crafting_chest");
		
		NetworkRegistry.instance().registerGuiHandler(ACM.instance, new ACMGuiHandler());
		
		GameRegistry.registerItem(ACMItem.netherHelm, "netheranium_helmet");
		GameRegistry.registerItem(ACMItem.netherPlate, "netheranium_chestplate");
		GameRegistry.registerItem(ACMItem.netherLegs, "netheranium_leggings");
		GameRegistry.registerItem(ACMItem.netherBoots, "netheranium_boots");
		GameRegistry.registerItem(ACMItem.netherSword, "netheranium_sword");
		GameRegistry.registerItem(ACMItem.netherPickaxe, "netheranium_pickaxe");
		GameRegistry.registerItem(ACMItem.netherAxe, "netheranium_axe");
		GameRegistry.registerItem(ACMItem.netherShovel, "netheranium_shovel");
		GameRegistry.registerItem(ACMItem.netherHoe, "netheranium_hoe");
		GameRegistry.registerItem(ACMItem.netherShield, "netheranium_shield");
		GameRegistry.registerItem(ACMItem.netherDagger, "netheranium_dagger");
		GameRegistry.registerItem(ACMItem.netherPoisonDagger, "netheranium_poison_dagger");
		GameRegistry.registerItem(ACMItem.netherHammer, "netheranium_hammer");
		GameRegistry.registerItem(ACMItem.netheraniumIngot, "netheranium_ingot");
		
		LanguageRegistry.addName(ACMItem.glassDoor, "Glass Door");
		
		LanguageRegistry.addName(ACMItem.diamondSwordAlt, "Diamond Sword");
		LanguageRegistry.addName(ACMItem.diamondSwordAlt2, "Diamond Sword");
		LanguageRegistry.addName(ACMItem.diamondSwordAlt3, "Diamond Sword");
		LanguageRegistry.addName(ACMItem.diamondSwordAlt4, "Diamond Sword");
		LanguageRegistry.addName(ACMItem.diamondSwordAlt5, "Diamond Sword");

		LanguageRegistry.addName(ACMItem.blueSword, "Lapis Lazuli Sword");
		LanguageRegistry.addName(ACMItem.bluePickaxe, "Lapis Lazuli Pickaxe");
		LanguageRegistry.addName(ACMItem.blueAxe, "Lapis Lazuli Axe");
		LanguageRegistry.addName(ACMItem.blueShovel, "Lapis Lazuli Shovel");
		LanguageRegistry.addName(ACMItem.blueHoe, "Lapis Lazuli Hoe");
		LanguageRegistry.addName(ACMItem.blueHammer, "Lapis Lazuli Hammer");
		LanguageRegistry.addName(ACMItem.blueShield, "Lapis Lazuli Shield");
		LanguageRegistry.addName(ACMItem.blueDagger, "Lapis Lazuli Dagger");
		LanguageRegistry.addName(ACMItem.bluePoisonDagger, "Lapis Lazuli Poisonous Dagger");
		
		LanguageRegistry.addName(ACMItem.redSword, "Redstone Sword");
		LanguageRegistry.addName(ACMItem.redPickaxe, "Redstone Pickaxe");
		LanguageRegistry.addName(ACMItem.redAxe, "Redstone Axe");
		LanguageRegistry.addName(ACMItem.redShovel, "Redstone Shovel");
		LanguageRegistry.addName(ACMItem.redHoe, "Redstone Hoe");
		LanguageRegistry.addName(ACMItem.redHammer, "Redstone Hammer");
		LanguageRegistry.addName(ACMItem.redShield, "Redstone Shield");
		LanguageRegistry.addName(ACMItem.redDagger, "Redstone Dagger");
		LanguageRegistry.addName(ACMItem.redPoisonDagger, "Redstone Poisonous Dagger");
		
		LanguageRegistry.addName(ACMItem.scubaFins, "Scuba Fins");
		LanguageRegistry.addName(ACMItem.arrowPort, "Arrow Port");
		LanguageRegistry.addName(ACMItem.bowPort, "Bow Port");
		LanguageRegistry.addName(ACMBlock.christmasChest, "Christmas Chest");
		LanguageRegistry.addName(ACMItem.arrowTorch, "Torch Arrow");
		LanguageRegistry.addName(ACMItem.bowTorch, "Torch Bow");
//		LanguageRegistry.addName(ACMItem.superBow, "Super Bow");
		LanguageRegistry.addName(ACMBlock.craftingChest, "Crafting Chest");

		LanguageRegistry.addName(ACMItem.netherHelm, "Netheranium Helmet");
		LanguageRegistry.addName(ACMItem.netherPlate, "Netheranium  Chestplate");
		LanguageRegistry.addName(ACMItem.netherLegs, "Netheranium  Leggings");
		LanguageRegistry.addName(ACMItem.netherBoots, "Netheranium  Boots");
		LanguageRegistry.addName(ACMItem.netherSword, "Netheranium Sword");
		LanguageRegistry.addName(ACMItem.netherPickaxe, "Netheranium Pickaxe");
		LanguageRegistry.addName(ACMItem.netherAxe, "Netheranium Axe");
		LanguageRegistry.addName(ACMItem.netherShovel, "Netheranium Shovel");
		LanguageRegistry.addName(ACMItem.netherHoe, "Netheranium Hoe");
		LanguageRegistry.addName(ACMItem.netherShield, "Netheranium Shield");
		LanguageRegistry.addName(ACMItem.netherDagger, "Netheranium Dagger");
		LanguageRegistry.addName(ACMItem.netherPoisonDagger, "Poisonous Netheranium Dagger");
		LanguageRegistry.addName(ACMItem.netherHammer, "Netheranium Warhammer");
		LanguageRegistry.addName(ACMItem.netheraniumIngot, "Netheranium Ingot");
		
		EntityRegistry.registerModEntity(EntityArrowPort.class, "Arrow Port", 0, ACM.instance, 128, 1, true);
		EntityRegistry.registerModEntity(EntityArrowTorch.class, "Torch Arrow", 1, ACM.instance, 128, 1, true);
		GameRegistry.registerTileEntity(TileEntityChristmasChest.class, "christmas_chest_tile");
		GameRegistry.registerTileEntity(TileEntityCraftingChest.class, "crafting_chest_tile");
		
		GameRegistry.registerItem(ACMItem.chainCluster, "chain_cluster");
		GameRegistry.registerItem(ACMItem.woodHammer, "wood_warhammer");
		GameRegistry.registerItem(ACMItem.stoneHammer, "stone_warhammer");
		GameRegistry.registerItem(ACMItem.ironHammer, "iron_warhammer");
		GameRegistry.registerItem(ACMItem.goldHammer, "gold_warhammer");
		GameRegistry.registerItem(ACMItem.diamondHammer, "diamond_warhammer");
		GameRegistry.registerItem(ACMItem.gunpowderBow, "gunpowder_bow");
		GameRegistry.registerItem(ACMItem.explosiveArrow, "explosive_arrow");
		GameRegistry.registerItem(ACMItem.explosiveBow, "explosive_bow");
		GameRegistry.registerItem(ACMItem.sulfur, "sulfur");
		GameRegistry.registerItem(ACMItem.potassium, "potassium");
		GameRegistry.registerItem(ACMItem.woodDagger, "wood_dagger");
		GameRegistry.registerItem(ACMItem.stoneDagger, "stone_dagger");
		GameRegistry.registerItem(ACMItem.ironDagger, "iron_dagger");
		GameRegistry.registerItem(ACMItem.goldDagger, "gold_dagger");
		GameRegistry.registerItem(ACMItem.diamondDagger, "diamond_dagger");
		GameRegistry.registerItem(ACMItem.woodPoisonDagger, "wood_poison_dagger");
		GameRegistry.registerItem(ACMItem.stonePoisonDagger, "stone_poison_dagger");
		GameRegistry.registerItem(ACMItem.ironPoisonDagger, "iron_poison_dagger");
		GameRegistry.registerItem(ACMItem.goldPoisonDagger, "gold_poison_dagger");
		GameRegistry.registerItem(ACMItem.diamondPoisonDagger, "diamond_poison_dagger");
		GameRegistry.registerItem(ACMItem.camoHelm, "camo_mask");
		GameRegistry.registerItem(ACMItem.camoShirt, "camo_shirt");
		GameRegistry.registerItem(ACMItem.camoLegs, "camo_leggings");
		GameRegistry.registerItem(ACMItem.camoBoots, "camo_boots");
		GameRegistry.registerItem(ACMItem.woodShield, "wood_shield");
		GameRegistry.registerItem(ACMItem.stoneShield, "stone_shield");
		GameRegistry.registerItem(ACMItem.ironShield, "iron_shield");
		GameRegistry.registerItem(ACMItem.goldShield, "gold_shield");
		GameRegistry.registerItem(ACMItem.diamondShield, "diamond_shield");
		GameRegistry.registerBlock(ACMBlock.sulfurOre, "sulfur_ore");
		GameRegistry.registerBlock(ACMBlock.potassiumOre, "potassium_ore");
		GameRegistry.registerBlock(ACMBlock.netheraniumOre, "netheranium_ore");
		
		LanguageRegistry.addName(ACMItem.chainCluster, "Chain cluster");
		LanguageRegistry.addName(ACMItem.woodHammer, "Wooden Warhammer");
		LanguageRegistry.addName(ACMItem.stoneHammer, "Stone Warhammer");
		LanguageRegistry.addName(ACMItem.ironHammer, "Iron Warhammer");
		LanguageRegistry.addName(ACMItem.goldHammer, "Gold Warhammer");
		LanguageRegistry.addName(ACMItem.diamondHammer, "Diamond Warhammer");
		LanguageRegistry.addName(ACMItem.gunpowderBow, "Arrow Rifle");
		LanguageRegistry.addName(ACMItem.explosiveArrow, "Explosive Arrow");
		LanguageRegistry.addName(ACMItem.explosiveBow, "Siege Bow");
		LanguageRegistry.addName(ACMItem.sulfur, "Sulfur");
		LanguageRegistry.addName(ACMItem.potassium, "Potassium");
		LanguageRegistry.addName(ACMBlock.sulfurOre, "Sulfur Ore");
		LanguageRegistry.addName(ACMBlock.potassiumOre, "Potassium Ore");
		LanguageRegistry.addName(ACMBlock.netheraniumOre, "Netheranium Ore");
		LanguageRegistry.addName(ACMItem.woodDagger, "Wooden Dagger");
		LanguageRegistry.addName(ACMItem.stoneDagger, "Stone Dagger");
		LanguageRegistry.addName(ACMItem.ironDagger, "Iron Dagger");
		LanguageRegistry.addName(ACMItem.goldDagger, "Gold Dagger");
		LanguageRegistry.addName(ACMItem.diamondDagger, "Diamond Dagger");
		LanguageRegistry.addName(ACMItem.woodPoisonDagger, "Poisonous Wooden Dagger");
		LanguageRegistry.addName(ACMItem.stonePoisonDagger, "Poisonous Stone Dagger");
		LanguageRegistry.addName(ACMItem.ironPoisonDagger, "Poisonous Iron Dagger");
		LanguageRegistry.addName(ACMItem.goldPoisonDagger, "Poisonous Gold Dagger");
		LanguageRegistry.addName(ACMItem.diamondPoisonDagger, "Poisonous Diamond Dagger");
		LanguageRegistry.addName(ACMItem.camoHelm, "Stealth Mask");
		LanguageRegistry.addName(ACMItem.camoShirt, "Stealth Shirt");
		LanguageRegistry.addName(ACMItem.camoLegs, "Stealth Leggings");
		LanguageRegistry.addName(ACMItem.camoBoots, "Stealth Boots");
		LanguageRegistry.addName(ACMItem.woodShield, "Wooden Shield");
		LanguageRegistry.addName(ACMItem.stoneShield, "Stone Shield");
		LanguageRegistry.addName(ACMItem.ironShield, "Iron Shield");
		LanguageRegistry.addName(ACMItem.goldShield, "Gold Shield");
		LanguageRegistry.addName(ACMItem.diamondShield, "Diamond Shield");
		
		GameRegistry.registerWorldGenerator(new OreGenerator());
		
		EntityRegistry.registerModEntity(ArrowExplosive.class, "Explosive Arrow", 0, ACM.instance, 128, 1, true);
		EntityRegistry.registerModEntity(ArrowFar.class, "Far Arrow", 1, ACM.instance, 128, 1, true);
	}
}
