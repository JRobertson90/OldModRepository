package acm.wearable;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class ItemAlternateIronArmor extends ItemArmor {
	
	//-------------------
	//  Instance Fields
	//-------------------
	public int set_number = 0;
	
	//-------------------
	//  Static Fields
	//-------------------
	public static ItemArmor[] armor_alts = new ItemArmor[12];

	private static final int ITEMS_PER_SET = 4; // head, chest, legs, feet
	private static final int NUMBER_OF_SETS = 3; // Blue, pink, purple (for now)
	private static final int START_ID = 4000;
	
	private static final String[] prefixes = new String[] { "Blue", "Pink", "Purple" };
	private static final String[] postfixes = new String[] { "Helmet", "Chestplate", "Leggings", "Boots" };
	
	//-------------------
	//  Constructor
	//-------------------
	public ItemAlternateIronArmor(int par1, EnumArmorMaterial par2EnumArmorMaterial, int par3, int par4, int set_num, String texture) {
		super(par1, par2EnumArmorMaterial, par3, par4);
		set_number = set_num;
		setUnlocalizedName(texture);
		setTextureName(texture);
		setCreativeTab(CreativeTabs.tabCombat);
	}
	
	//-------------------
	//  Register
	//-------------------
	public static void register() {
		
		for(int armorSet = 0; armorSet < NUMBER_OF_SETS; armorSet++) {
			
			for(int armorLocation = 0; armorLocation < ITEMS_PER_SET; armorLocation++) {
				
				int num = armorSet * ITEMS_PER_SET + armorLocation;
				String texture = "acm:" + prefixes[armorSet].toLowerCase() + "_" + postfixes[armorLocation].toLowerCase();
				armor_alts[num] = (ItemArmor) new ItemAlternateIronArmor(START_ID + num ,EnumArmorMaterial.IRON, 2, armorLocation, armorSet, texture);
				GameRegistry.registerItem(armor_alts[num], "iron_armor_alt_" + num);
				LanguageRegistry.addName(armor_alts[num], prefixes[armorSet] + " Iron " + postfixes[armorLocation] );
			}
		}
	}

	//-----------------------
	//  Add Recipes
	//-----------------------
	public static void addRecipes() {

		// This is a cycle recipe similar to the alternate diamond swords.
		for(int armorSet = 0; armorSet < NUMBER_OF_SETS + 1; armorSet++) {

			for(int armorLocation = 0; armorLocation < ITEMS_PER_SET; armorLocation++) {

				int num = armorSet * ITEMS_PER_SET + armorLocation;
				
				// Vanilla items craft into the first set of alternate armor
				if(armorSet == 0) {
					GameRegistry.addShapelessRecipe(new ItemStack(armor_alts[num]), new Object[] { getVanillaItem(armorLocation) });
				}
				
				// Each item crafts into the next set of armor
				else if (armorSet >= 0 && armorSet < NUMBER_OF_SETS) {
					GameRegistry.addShapelessRecipe(new ItemStack(armor_alts[num]), new Object[]{ armor_alts[ (armorSet - 1) * ITEMS_PER_SET + armorLocation] });					
				}
				
				// Wrap around to vanilla items for the last set
				else {
					GameRegistry.addShapelessRecipe(new ItemStack(getVanillaItem(armorLocation)), new Object[] { armor_alts[ (armorSet - 1) * ITEMS_PER_SET + armorLocation ]});
				}
			}
		}
	}

	private static Item getVanillaItem(int armorSet) {
		
		switch(armorSet) {
			case 0: return Item.helmetIron;
			case 1: return Item.plateIron;
			case 2: return Item.legsIron;
			case 3: return Item.bootsIron;
		}
		return null;
	}

	//-----------------------
	//  Get Armor Texture
	//-----------------------
	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type)
    {
		return "acm:textures/models/armor/" + prefixes[set_number].toLowerCase() + "_layer_" + ( ((ItemAlternateIronArmor) stack.getItem()).armorType == 2 ? 2 : 1) + ".png";
	}
	
}
