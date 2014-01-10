package acm.item;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemSword;
import net.minecraftforge.common.EnumHelper;
import acm.melee.ItemDagger;
import acm.melee.ItemNetherDagger;
import acm.melee.ItemNetherShield;
import acm.melee.ItemNetherSword;
import acm.melee.ItemNetherWarHammer;
import acm.melee.ItemShield;
import acm.melee.ItemWarHammer;
import acm.ranged.BowPort;
import acm.ranged.BowTorch;
import acm.ranged.ItemExplosiveBow;
import acm.ranged.ItemGunpowderBow;
import acm.tool.ItemNetherAxe;
import acm.tool.ItemNetherHoe;
import acm.tool.ItemNetherPickaxe;
import acm.tool.ItemNetherSpade;
import acm.wearable.ItemCamo;
import acm.wearable.ItemNetherArmor;

public class ACMItem {

	public static Item netheraniumIngot = new Item(3223).setCreativeTab(CreativeTabs.tabMaterials).setUnlocalizedName("nether_ingot").setTextureName("acm:netheranium_ingot");
	
	public static EnumToolMaterial BLUE = EnumHelper.addToolMaterial("BLUE",2, 670, 6.0F, 2.0F, 14);
	public static EnumArmorMaterial CAMO = addArmorMaterialWithBlock("CAMO", 5, new int[]{0, 0, 0, 0}, 9, Block.cloth);
	public static EnumToolMaterial netherTool = addToolMaterialWithItem("NETHER",3, 1561, 8.0F, 3.0F, 10, netheraniumIngot);
	public static EnumArmorMaterial netherArmor = addArmorMaterialWithItem("NETHER", 33, new int[]{3, 8, 6, 3}, 10, netheraniumIngot);
	
	public static ItemSword blueSword = (ItemSword) (new ItemSword(3201, BLUE)).setUnlocalizedName("blueSword").setTextureName("acm:lapis_lazuli_sword");
	public static ItemPickaxe bluePickaxe = (ItemPickaxe) (new ItemPickaxe(3202, BLUE)).setUnlocalizedName("bluePickaxe").setTextureName("acm:lapis_lazuli_pickaxe");
	public static ItemAxe blueAxe = (ItemAxe) (new ItemAxe(3203, BLUE)).setUnlocalizedName("blueAxe").setTextureName("acm:lapis_lazuli_axe");
	public static ItemSpade blueShovel = (ItemSpade) (new ItemSpade(3204, BLUE)).setUnlocalizedName("blueShovel").setTextureName("acm:lapis_lazuli_shovel");
	public static ItemHoe blueHoe = (ItemHoe) (new ItemHoe(3205, BLUE)).setUnlocalizedName("blueHoe").setTextureName("acm:lapis_lazuli_hoe");
	public static ItemWarHammer blueHammer = (ItemWarHammer) new ItemWarHammer(3208, BLUE).setUnlocalizedName("blueHammer").setTextureName("acm:lapis_lazuli_warhammer");
	public static ItemShield blueShield = (ItemShield) new ItemShield(3212, BLUE).setUnlocalizedName("blueShield").setTextureName("acm:lapis_lazuli_shield");
	
	public static BowPort bowPort = (BowPort) new BowPort(3206).setUnlocalizedName("bowPort").setTextureName("acm:bow_port").setFull3D();
	public static Item arrowPort = (new Item(3207)).setUnlocalizedName("arrowPort").setCreativeTab(CreativeTabs.tabCombat).setTextureName("acm:arrow_port");
	
	public static BowTorch bowTorch = (BowTorch) new BowTorch(3210).setUnlocalizedName("bowTorch").setTextureName("acm:bow_torch").setFull3D();
	public static Item arrowTorch = (new Item(3211)).setUnlocalizedName("arrowTorch").setCreativeTab(CreativeTabs.tabCombat).setTextureName("acm:arrow_torch");
	
	static String netherToolInfo = "Right click creates fire on ground, left click sets enemy on fire";
	static String netherHoeInfo = "Left click sets enemy on fire.";
	public static ItemArmor netherHelm = (ItemArmor) new ItemNetherArmor(3213, netherArmor, 0, 0).setUnlocalizedName("nether_helm").setCreativeTab(CreativeTabs.tabCombat).setTextureName("acm:nether_helmet");
	public static ItemArmor netherPlate = (ItemArmor) new ItemNetherArmor(3214, netherArmor, 0, 1).setUnlocalizedName("nether_plate").setCreativeTab(CreativeTabs.tabCombat).setTextureName("acm:nether_chestplate");
	public static ItemArmor netherLegs = (ItemArmor) new ItemNetherArmor(3215, netherArmor, 0, 2).setUnlocalizedName("nether_legs").setCreativeTab(CreativeTabs.tabCombat).setTextureName("acm:nether_leggings");
	public static ItemArmor netherBoots = (ItemArmor) new ItemNetherArmor(3216, netherArmor, 0, 3).setUnlocalizedName("nether_boots").setCreativeTab(CreativeTabs.tabCombat).setTextureName("acm:nether_boots");
	public static ItemSword netherSword = (ItemSword) (new ItemNetherSword(3217)).setUnlocalizedName("netherSword").setTextureName("acm:nether_sword");
	public static ItemPickaxe netherPickaxe = (ItemPickaxe) (new ItemNetherPickaxe(3218)).setUnlocalizedName("netherPickaxe").setTextureName("acm:nether_pickaxe");
	public static ItemAxe netherAxe = (ItemAxe) (new ItemNetherAxe(3219)).setUnlocalizedName("netherAxe").setTextureName("acm:nether_axe");
	public static ItemSpade netherShovel = (ItemSpade) (new ItemNetherSpade(3220)).setUnlocalizedName("netherShovel").setTextureName("acm:nether_shovel");
	public static ItemHoe netherHoe = (ItemHoe) (new ItemNetherHoe(3221)).setUnlocalizedName("netherHoe").setTextureName("acm:nether_hoe");
	public static ItemShield netherShield = new ItemNetherShield(3222);
	public static ItemWarHammer netherHammer = new ItemNetherWarHammer(3224);
	public static ItemDagger netherDagger = new ItemNetherDagger(3225, false);
	public static ItemDagger netherPoisonDagger = new ItemNetherDagger(3226, true);
	
	//Next Available ID = 3227

	public static Item chainCluster = new Item(5000).setCreativeTab(CreativeTabs.tabMaterials).setUnlocalizedName("chainCluster").setTextureName("acm:chaincluster");
	public static ItemWarHammer woodHammer = new ItemWarHammer(5001, EnumToolMaterial.WOOD);
	public static ItemWarHammer stoneHammer = new ItemWarHammer(5002, EnumToolMaterial.STONE);
	public static ItemWarHammer ironHammer = new ItemWarHammer(5003, EnumToolMaterial.IRON);
	public static ItemWarHammer goldHammer = new ItemWarHammer(5004, EnumToolMaterial.GOLD);
	public static ItemWarHammer diamondHammer = new ItemWarHammer(5005, EnumToolMaterial.EMERALD);
	public static Item gunpowderBow = new ItemGunpowderBow(5006);
	public static Item sulfur = new Item(5007).setCreativeTab(CreativeTabs.tabMaterials).setUnlocalizedName("sulfur").setTextureName("acm:sulfur");
	public static Item explosiveArrow = new Item(5008).setUnlocalizedName("explosiveArrow").setCreativeTab(CreativeTabs.tabCombat).setTextureName("acm:arrow_explosive");
	public static Item explosiveBow = new ItemExplosiveBow(5009).setUnlocalizedName("explosiveBow").setCreativeTab(CreativeTabs.tabCombat).setTextureName("acm:bow_explosive");
	public static ItemArmor camoHelm = (ItemArmor) new ItemCamo(5010, CAMO, 0, 0).setUnlocalizedName("camo_helm").setCreativeTab(CreativeTabs.tabCombat).setTextureName("acm:camo_helmet");
	public static ItemArmor camoShirt = (ItemArmor) new ItemCamo(5011, CAMO, 0, 1).setUnlocalizedName("camo_shirt").setCreativeTab(CreativeTabs.tabCombat).setTextureName("acm:camo_chestplate");
	public static ItemArmor camoLegs = (ItemArmor) new ItemCamo(5012, CAMO, 0, 2).setUnlocalizedName("camo_legs").setCreativeTab(CreativeTabs.tabCombat).setTextureName("acm:camo_leggings");
	public static ItemArmor camoBoots = (ItemArmor) new ItemCamo(5013, CAMO, 0, 3).setUnlocalizedName("camo_boots").setCreativeTab(CreativeTabs.tabCombat).setTextureName("acm:camo_boots");
	public static Item potassium = new Item(5014).setCreativeTab(CreativeTabs.tabMaterials).setUnlocalizedName("potassium").setTextureName("acm:potassium");
	public static ItemDagger woodDagger = new ItemDagger(5015, false,  EnumToolMaterial.WOOD);
	public static ItemDagger stoneDagger = new ItemDagger(5016, false,  EnumToolMaterial.STONE);
	public static ItemDagger ironDagger = new ItemDagger(5017, false,  EnumToolMaterial.IRON);
	public static ItemDagger goldDagger = new ItemDagger(5018, false,  EnumToolMaterial.GOLD);
	public static ItemDagger diamondDagger = new ItemDagger(5019, false, EnumToolMaterial.EMERALD);
	public static ItemDagger woodPoisonDagger = new ItemDagger(5020, true,  EnumToolMaterial.WOOD);
	public static ItemDagger stonePoisonDagger = new ItemDagger(5021, true,  EnumToolMaterial.STONE);
	public static ItemDagger ironPoisonDagger = new ItemDagger(5022, true,  EnumToolMaterial.IRON);
	public static ItemDagger goldPoisonDagger = new ItemDagger(5023, true,  EnumToolMaterial.GOLD);
	public static ItemDagger diamondPoisonDagger = new ItemDagger(5024, true, EnumToolMaterial.EMERALD);
	public static ItemShield woodShield = new ItemShield(5025, EnumToolMaterial.WOOD);
	public static ItemShield stoneShield = new ItemShield(5026, EnumToolMaterial.STONE);
	public static ItemShield ironShield = new ItemShield(5027, EnumToolMaterial.IRON);
	public static ItemShield goldShield = new ItemShield(5028, EnumToolMaterial.GOLD);
	public static ItemShield diamondShield = new ItemShield(5029, EnumToolMaterial.EMERALD);
	
	public static EnumToolMaterial addToolMaterialWithItem(String name, int harvestLevel, int maxUses, float efficiency, float damage, int enchantability, Item repairItem)
	{
		EnumToolMaterial returnValue = EnumHelper.addToolMaterial(name, harvestLevel, maxUses, efficiency, damage, enchantability);
		returnValue.customCraftingMaterial = repairItem;
		return returnValue;
	}
	
	public static EnumArmorMaterial addArmorMaterialWithItem(String name, int durability, int[] reductionAmounts, int enchantability, Item repairItem)
	{
		EnumArmorMaterial returnValue = EnumHelper.addArmorMaterial(name, durability, reductionAmounts, enchantability);
		returnValue.customCraftingMaterial = repairItem;
		return returnValue;
	}
	
	public static EnumToolMaterial addToolMaterialWithBlock(String name, int harvestLevel, int maxUses, float efficiency, float damage, int enchantability, Block repairBlock)
	{
		EnumToolMaterial returnValue = EnumHelper.addToolMaterial(name, harvestLevel, maxUses, efficiency, damage, enchantability);
		returnValue.customCraftingMaterial = Item.itemsList[repairBlock.blockID];
		return returnValue;
	}
	
	public static EnumArmorMaterial addArmorMaterialWithBlock(String name, int durability, int[] reductionAmounts, int enchantability, Block repairBlock)
	{
		EnumArmorMaterial returnValue = EnumHelper.addArmorMaterial(name, durability, reductionAmounts, enchantability);
		returnValue.customCraftingMaterial = Item.itemsList[repairBlock.blockID];
		return returnValue;
	}
}


