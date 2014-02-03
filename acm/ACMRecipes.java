package acm;

import net.minecraft.block.Block;
import net.minecraft.block.BlockStairs;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemTool;
import acm.block.ACMBlock;
import acm.block.BlockStairsWool;
import acm.block.moses.ItemMosesStaff;
import acm.item.ACMItem;
import acm.item.ItemBedColor;
import acm.melee.ItemDagger;
import acm.melee.ItemShield;
import acm.melee.ItemWarHammer;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.ReflectionHelper;

public class ACMRecipes {
	public static void load() {
		
		ItemMosesStaff.addRecipe();
		addStairsRecipe(Block.glass,ACMBlock.glassStairs);
		addStairsRecipe(Block.dirt,ACMBlock.dirtStairs);
		
		GameRegistry.addRecipe(new ItemStack(ACMBlock.woodOakStairs, 8), new Object[]{"  #"," ##","###",'#', new ItemStack(Block.wood,1,0)});
		GameRegistry.addRecipe(new ItemStack(ACMBlock.woodSpruceStairs, 8), new Object[]{"  #"," ##","###",'#', new ItemStack(Block.wood,1,1)});
		GameRegistry.addRecipe(new ItemStack(ACMBlock.woodBirchStairs, 8), new Object[]{"  #"," ##","###",'#', new ItemStack(Block.wood,1,2)});
		GameRegistry.addRecipe(new ItemStack(ACMBlock.woodJungleStairs, 8), new Object[]{"  #"," ##","###",'#', new ItemStack(Block.wood,1,3)});
		
		GameRegistry.addRecipe(new ItemStack(ACMBlock.leavesOakStairs, 4), new Object[]{"  #"," ##","###",'#', new ItemStack(Block.leaves,1,0)});
		GameRegistry.addRecipe(new ItemStack(ACMBlock.leavesSpruceStairs, 4), new Object[]{"  #"," ##","###",'#', new ItemStack(Block.leaves,1,1)});
		GameRegistry.addRecipe(new ItemStack(ACMBlock.leavesBirchStairs, 4), new Object[]{"  #"," ##","###",'#', new ItemStack(Block.leaves,1,2)});
		GameRegistry.addRecipe(new ItemStack(ACMBlock.leavesJungleStairs, 4), new Object[]{"  #"," ##","###",'#', new ItemStack(Block.leaves,1,3)});
		
		GameRegistry.addShapelessRecipe(new ItemStack(Item.silk,4), new Object[]{new ItemStack(Block.cloth,15)});
		GameRegistry.addRecipe(new ItemStack(ACMBlock.lightBlock, 4), new Object[] { "###", "#Q#", "###", '#', Item.glowstone, 'Q', Block.blockNetherQuartz });
		GameRegistry.addRecipe(new ItemStack(ACMItem.glassDoor), new Object[]{"##","##","##",'#',Block.glass});
		
		ItemBedColor.loadRecipes();
		BlockStairsWool.loadRecipes();
		final ItemStack lapis = new ItemStack(Item.dyePowder,1,4);
		GameRegistry.addRecipe(new ItemStack(Block.grass), new Object[] { "L", "D", 'L', new ItemStack(Block.tallGrass,1,1), 'D', Block.dirt });
		GameRegistry.addShapelessRecipe(new ItemStack(Block.sand, 4), new Object[]{Block.sandStone});
		
		addSwordRecipe(lapis, ACMItem.blueSword);
		addPickaxeRecipe(lapis, ACMItem.bluePickaxe);
		addShovelRecipe(lapis, ACMItem.blueShovel);
		addAxeRecipe(lapis, ACMItem.blueAxe);
		addHoeRecipe(lapis, ACMItem.blueHoe);
		addWarhammerRecipe(lapis, ACMItem.blueHammer);
		addShieldRecipe(lapis, ACMItem.blueShield);
		addDaggerRecipe(lapis, ACMItem.blueDagger);
		addPoisonDaggerRecipe(lapis, ACMItem.bluePoisonDagger);
		
		GameRegistry.addShapelessRecipe(new ItemStack(ACMItem.diamondSwordAlt), new Object[]{Item.swordDiamond});
		GameRegistry.addShapelessRecipe(new ItemStack(ACMItem.diamondSwordAlt2), new Object[]{ACMItem.diamondSwordAlt});
		GameRegistry.addShapelessRecipe(new ItemStack(ACMItem.diamondSwordAlt3), new Object[]{ACMItem.diamondSwordAlt2});
		GameRegistry.addShapelessRecipe(new ItemStack(ACMItem.diamondSwordAlt4), new Object[]{ACMItem.diamondSwordAlt3});
		GameRegistry.addShapelessRecipe(new ItemStack(ACMItem.diamondSwordAlt5), new Object[]{ACMItem.diamondSwordAlt4});
		GameRegistry.addShapelessRecipe(new ItemStack(Item.swordDiamond), new Object[]{ACMItem.diamondSwordAlt5});
		
		addSwordRecipe(Item.redstone, ACMItem.redSword);
		addPickaxeRecipe(Item.redstone, ACMItem.redPickaxe);
		addShovelRecipe(Item.redstone, ACMItem.redShovel);
		addAxeRecipe(Item.redstone, ACMItem.redAxe);
		addHoeRecipe(Item.redstone, ACMItem.redHoe);
		addWarhammerRecipe(Item.redstone, ACMItem.redHammer);
		addShieldRecipe(Item.redstone, ACMItem.redShield);
		
		// To avoid clashing with redstone torch, redstone daggers are crafted at an angle
		GameRegistry.addRecipe(new ItemStack(ACMItem.redDagger), new Object[]{" R", "S ", 'R', Item.redstone, 'S', Item.stick});
		addPoisonDaggerRecipe(ACMItem.redDagger, ACMItem.redPoisonDagger);
		
		GameRegistry.addRecipe(new ItemStack(ACMItem.scubaFins, 1), new Object[] { "# #", "# #", '#', lapis });
		
		addSwordRecipe(ACMItem.netherSword);
		addPickaxeRecipe(ACMItem.netherPickaxe);
		addShovelRecipe(ACMItem.netherShovel);
		addAxeRecipe(ACMItem.netherAxe);
		addHoeRecipe(ACMItem.netherHoe);
		addWarhammerRecipe(ACMItem.netherHammer);
		addDaggerRecipe(ACMItem.netherDagger);
		
		GameRegistry.addRecipe(new ItemStack(ACMItem.bowPort, 1), new Object[] {" L#", "B #", " L#", '#', Item.silk, 'B', Item.blazeRod, 'L', new ItemStack(Item.dyePowder,1,4)});
		GameRegistry.addRecipe(new ItemStack(ACMItem.arrowPort, 4), new Object[] {"E", "S", "A", 'E', Item.enderPearl, 'S', Item.slimeBall, 'A', Item.arrow,});
		
		GameRegistry.addRecipe(new ItemStack(ACMItem.bowTorch, 1), new Object[] {" S#", "SC#", " S#", '#', Item.silk, 'S', Item.stick, 'C', new ItemStack(Item.coal,1,0) });
		GameRegistry.addShapelessRecipe(new ItemStack(ACMItem.bowTorch, 1), new Object[] { new ItemStack(Item.bow,1), new ItemStack(Item.coal,1,0) } );
		GameRegistry.addShapelessRecipe(new ItemStack(ACMItem.arrowTorch, 4), new Object[] { new ItemStack(Item.arrow,1), new ItemStack(Item.coal,1,0) } );
		GameRegistry.addShapelessRecipe(new ItemStack(ACMItem.arrowTorch, 1), new Object[] { new ItemStack(Item.arrow,1), new ItemStack(Block.torchWood, 1) } );
		GameRegistry.addRecipe(new ItemStack(ACMItem.arrowTorch, 1), new Object[] {"T", "S", "F", 'T', Block.torchWood, 'S', Item.stick, 'F', Item.feather });
		GameRegistry.addRecipe(new ItemStack(ACMItem.arrowTorch, 4), new Object[] {"C", "S", "F", 'C', new ItemStack(Item.coal,1,0), 'S', Item.stick, 'F', Item.feather });
		
		GameRegistry.addRecipe(new ItemStack(ACMBlock.christmasChest,1), "XXX", "X#X", "XXX" , 'X', new ItemStack(Block.cloth,1,14), '#', Block.chest);
		
		GameRegistry.addRecipe(new ItemStack(ACMBlock.craftingChest,1), "XXX", "X#X", "XXX" , 'X', Block.planks, '#', Block.workbench);
		GameRegistry.addShapelessRecipe(new ItemStack(ACMBlock.craftingChest,1), new Object[]{ Block.chest, Block.workbench});
		
		final ItemStack blackWool = new ItemStack(Block.cloth, 1, 15);
		GameRegistry.addRecipe(new ItemStack(ACMItem.chainCluster,5), " x ", "x x", " x ", 'x', Item.ingotIron);
		GameRegistry.addRecipe(new ItemStack(Item.ingotIron,4), " x ", "xxx", " x ", 'x', ACMItem.chainCluster);
		
		addHelmet(ACMItem.chainCluster, Item.helmetChain);
		addChestPlate(ACMItem.chainCluster, Item.plateChain);
		addLeggings(ACMItem.chainCluster, Item.legsChain);
		addBoots(ACMItem.chainCluster, Item.bootsChain);
		
		addHelmet(blackWool, ACMItem.camoHelm);
		addChestPlate(blackWool, ACMItem.camoShirt);
		addLeggings(blackWool, ACMItem.camoLegs);
		addBoots(blackWool, ACMItem.camoBoots);
		
		addHelmet(ACMItem.netherHelm);
		addChestPlate(ACMItem.netherPlate);
		addLeggings(ACMItem.netherLegs);
		addBoots(ACMItem.netherBoots);
		
		GameRegistry.addRecipe(new ItemStack(Item.saddle), "lll", "lil" , "i i", 'l' , Item.leather, 'i', Item.ingotIron);
		
		addWarhammerRecipe(ACMItem.woodHammer);
		addWarhammerRecipe(ACMItem.stoneHammer);
		addWarhammerRecipe(ACMItem.ironHammer);
		addWarhammerRecipe(ACMItem.goldHammer);
		addWarhammerRecipe(ACMItem.diamondHammer);
		
		addHorseArmorRecipe(Item.ingotIron, Item.horseArmorIron);
		addHorseArmorRecipe(Item.ingotGold, Item.horseArmorGold);
		addHorseArmorRecipe(Item.diamond, Item.horseArmorDiamond);
		GameRegistry.addRecipe(new ItemStack(ACMItem.gunpowderBow), "iii", " ri", 'i', Item.ingotIron, 'r', Item.redstone);
		GameRegistry.addShapelessRecipe(new ItemStack(ACMItem.explosiveArrow), new Object[] {Item.arrow, Item.gunpowder});
		GameRegistry.addShapelessRecipe(new ItemStack(ACMItem.explosiveBow), new Object[] {Item.bow, Item.gunpowder});
		GameRegistry.addSmelting(ACMBlock.sulfurOre.blockID, new ItemStack(ACMItem.sulfur,1), 0.5F);
		GameRegistry.addSmelting(ACMBlock.potassiumOre.blockID, new ItemStack(ACMItem.potassium,1), 0.5F);
		GameRegistry.addSmelting(ACMBlock.netheraniumOre.blockID, new ItemStack(ACMItem.netheraniumIngot,1), 0.5F);
		GameRegistry.addShapelessRecipe(new ItemStack(Item.gunpowder,1), new Object[] {ACMItem.potassium, ACMItem.sulfur, new ItemStack(Item.coal,1,1)});
		
		//This also covers adding the recipes for the poisoned variants
		addDaggerRecipe(ACMItem.woodDagger);
		addDaggerRecipe(ACMItem.stoneDagger);
		addDaggerRecipe(ACMItem.ironDagger);
		addDaggerRecipe(ACMItem.goldDagger);
		addDaggerRecipe(ACMItem.diamondDagger);
		
		addShieldRecipe(ACMItem.woodShield);
		addShieldRecipe(ACMItem.stoneShield);
		addShieldRecipe(ACMItem.ironShield);
		addShieldRecipe(ACMItem.goldShield);
		addShieldRecipe(ACMItem.diamondShield);
		addShieldRecipe(ACMItem.netherShield);
		
		GameRegistry.addRecipe(new ItemStack(Item.nameTag,1), new Object[]{"cii",'c',ACMItem.chainCluster, 'i', Item.ingotIron});
	}
	
	public static Object getToolMaterial(Class itemClass, Item input)
	{
		Item thing = null;
		for(int i=0;thing == null; i++)
		{
			try
			{
				thing = getItemFromID(((EnumToolMaterial) ReflectionHelper.getPrivateValue(itemClass, input, i)).getToolCraftingMaterial());
			}
			catch(Exception e)
			{
				;
			}
			
		}
		return blockCheck(thing);
	}
	
	public static Object getArmorMaterial(ItemArmor input)
	{
		Item thing = null;
		for(int i=0;thing == null; i++)
		{
			try
			{
				thing = getItemFromID(((EnumArmorMaterial) ReflectionHelper.getPrivateValue(ItemArmor.class, input, i)).getArmorCraftingMaterial());
			}
			catch(Exception e)
			{
				;
			}
		}
		return blockCheck(thing);
	}
	
	public static Object blockCheck(Item item)
	{
		if(item instanceof ItemBlock)
		{
			return Block.blocksList[item.itemID];
		}
		return item;
	}

	public static Item getItemFromID(int id)
	{
		return Item.itemsList[id];
	}

	static String[] warhammerRecipe = {"xxx", "xsx", " s "};
	public static void addWarhammerRecipe(ItemWarHammer output)
	{
		GameRegistry.addRecipe(new ItemStack(output , 1), warhammerRecipe[0], warhammerRecipe[1], warhammerRecipe[2], 'x', getToolMaterial(ItemWarHammer.class, output), 's', Item.stick);
	}
	
	public static void addWarhammerRecipe(Object material, ItemWarHammer output)
	{
		GameRegistry.addRecipe(new ItemStack(output , 1), warhammerRecipe[0], warhammerRecipe[1], warhammerRecipe[2], 'x', material, 's', Item.stick);
	}
	
	public static void addHorseArmorRecipe(Item material, Item output)
	{
		GameRegistry.addRecipe(new ItemStack(output, 1), "x  ", "xxx", "xxx", 'x', material);
	}
	
	public static void addDaggerRecipe(ItemDagger output)
	{
		GameRegistry.addRecipe(new ItemStack(output, 1), "x", "s", 'x', getToolMaterial(ItemDagger.class, output), 's', Item.stick);
		ItemDagger poisoned = output.getPoisoned();
		if(poisoned != null)
		{
			addPoisonDaggerRecipe(output, poisoned);
		}
	}
	
	public static void addDaggerRecipe(Object material, ItemDagger output)
	{
		GameRegistry.addRecipe(new ItemStack(output, 1), "x", "s", 'x', material, 's', Item.stick);
	}
	
	private static void addPoisonDaggerRecipe(Object input, Item output)
	{
		GameRegistry.addShapelessRecipe(new ItemStack(output, 1), new Object[] {input, Item.spiderEye});
	}
	
	static String[] shieldRecipe = {"xxx", "xxx", " x "};
	public static void addShieldRecipe(Item output)
	{
		GameRegistry.addRecipe(new ItemStack(output, 1), shieldRecipe[0], shieldRecipe[1], shieldRecipe[2], 'x', getToolMaterial(ItemShield.class, output));
	}
	
	public static void addShieldRecipe(Object material, Item output)
	{
		GameRegistry.addRecipe(new ItemStack(output, 1), shieldRecipe[0], shieldRecipe[1], shieldRecipe[2], 'x', material);
	}
	static String[] helmetRecipe = {"xxx", "x x"};
	static String[] chestplateRecipe = {"x x", "xxx", "xxx"};
	static String[] leggingsRecipe = {"xxx", "x x", "x x"};
	static String[] bootsRecipe = {"x x", "x x"};
	public static void addHelmet(Object material, ItemArmor output)
	{
		GameRegistry.addRecipe(new ItemStack(output, 1), helmetRecipe[0], helmetRecipe[1], 'x', material);
	}
	
	public static void addChestPlate(Object material, ItemArmor output)
	{
		GameRegistry.addRecipe(new ItemStack(output, 1), chestplateRecipe[0], chestplateRecipe[1], chestplateRecipe[2], 'x', material);
	}
	
	public static void addLeggings(Object material, ItemArmor output)
	{
		GameRegistry.addRecipe(new ItemStack(output, 1), leggingsRecipe[0], leggingsRecipe[1], leggingsRecipe[2], 'x', material);
	}
	
	public static void addBoots(Object material, ItemArmor output)
	{
		GameRegistry.addRecipe(new ItemStack(output, 1), bootsRecipe[0], bootsRecipe[1], 'x', material);
	}
	
	public static void addHelmet(ItemArmor output)
	{
		GameRegistry.addRecipe(new ItemStack(output, 1), helmetRecipe[0], helmetRecipe[1], 'x', getArmorMaterial(output));
	}
	
	public static void addChestPlate(ItemArmor output)
	{
		GameRegistry.addRecipe(new ItemStack(output, 1), chestplateRecipe[0], chestplateRecipe[1], chestplateRecipe[2], 'x', getArmorMaterial(output));
	}
	
	public static void addLeggings(ItemArmor output)
	{
		GameRegistry.addRecipe(new ItemStack(output, 1), leggingsRecipe[0], leggingsRecipe[1], leggingsRecipe[2], 'x', getArmorMaterial(output));
	}
	
	public static void addBoots(ItemArmor output)
	{
		GameRegistry.addRecipe(new ItemStack(output, 1), bootsRecipe[0], bootsRecipe[1], 'x', getArmorMaterial(output));
	}
	
	static String[] swordRecipe = {"X", "X", "#"};
	public static void addSwordRecipe(ItemSword output)
	{
		GameRegistry.addRecipe(new ItemStack(output, 1), new Object[] {swordRecipe[0], swordRecipe[1], swordRecipe[2], 'X', getToolMaterial(ItemSword.class, output), '#', Item.stick});
	}
	
	public static void addSwordRecipe(Object material, ItemSword output)
	{
		GameRegistry.addRecipe(new ItemStack(output, 1), new Object[] {swordRecipe[0], swordRecipe[1], swordRecipe[2], 'X', material, '#', Item.stick});
	}
	
	static String[] pickaxeRecipe = {"XXX", " # ", " # "};
	public static void addPickaxeRecipe(ItemPickaxe output)
	{
		GameRegistry.addRecipe(new ItemStack(output, 1), new Object[] {pickaxeRecipe[0], pickaxeRecipe[1], pickaxeRecipe[2], 'X', getToolMaterial(ItemTool.class, output), '#', Item.stick});
	}
	
	public static void addPickaxeRecipe(Object material, ItemPickaxe output)
	{
		GameRegistry.addRecipe(new ItemStack(output, 1), new Object[] {pickaxeRecipe[0], pickaxeRecipe[1], pickaxeRecipe[2], 'X', material, '#', Item.stick});
	}
	
	static String[] shovelRecipe = {"X", "#", "#"};
	public static void addShovelRecipe(ItemSpade output)
	{
		GameRegistry.addRecipe(new ItemStack(output, 1), new Object[] {shovelRecipe[0], shovelRecipe[1], shovelRecipe[2], 'X', getToolMaterial(ItemTool.class, output), '#', Item.stick});
	}
	
	public static void addShovelRecipe(Object material, ItemSpade output)
	{
		GameRegistry.addRecipe(new ItemStack(output, 1), new Object[] {shovelRecipe[0], shovelRecipe[1], shovelRecipe[2], 'X', material, '#', Item.stick});
	}
	static String[] axeRecipe = {"XX ", "X# ", " # "};
	public static void addAxeRecipe(ItemAxe output)
	{
		GameRegistry.addRecipe(new ItemStack(output, 1), new Object[] {axeRecipe[0], axeRecipe[1], axeRecipe[2], 'X', getToolMaterial(ItemTool.class, output), '#', Item.stick});
	}
	
	public static void addAxeRecipe(Object material, ItemAxe blueAxe)
	{
		GameRegistry.addRecipe(new ItemStack(blueAxe, 1), new Object[] {axeRecipe[0], axeRecipe[1], axeRecipe[2], 'X', material, '#', Item.stick});
	}
	
	static String[] hoeRecipe = {"XX ", " # ", " # "};
	public static void addHoeRecipe(ItemHoe output)
	{
		GameRegistry.addRecipe(new ItemStack(output, 1), new Object[] {hoeRecipe[0], hoeRecipe[1], hoeRecipe[2], 'X', getToolMaterial(ItemHoe.class, output), '#', Item.stick});
	}
	
	public static void addHoeRecipe(Object material, ItemHoe output)
	{
		GameRegistry.addRecipe(new ItemStack(output, 1), new Object[] {hoeRecipe[0], hoeRecipe[1], hoeRecipe[2], 'X', material, '#', Item.stick});
	}
	
	public static void addStairsRecipe(Object material, BlockStairs output) {
		
		GameRegistry.addRecipe(new ItemStack(output, 4), new Object[]{"  #"," ##","###",'#', material});
	}
}
