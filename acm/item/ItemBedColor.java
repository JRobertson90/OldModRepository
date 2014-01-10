package acm.item;

import java.awt.Color;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBed;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import acm.ACM;
import acm.block.BlockBedColor;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class ItemBedColor extends ItemBed {

	public final int color;
	public final static String colors[] = {"black", "red", "green", "brown", "blue", "purple", "cyan", "light Gray",
		"gray", "pink", "lime", "yellow", "light Blue", "magenta", "orange", "white"};
	
	public final static Block[] blocks = new Block[colors.length];
	public final static Item[] items = new Item[colors.length];
	public final static Color[] rgbColors = {new Color(221,221,221),//0
												new Color(219,125,62),//1
												new Color(179,80,188),//2
												new Color(107,138,201),//3
												new Color(177,166,39),//4
												new Color(65,174,56),//5
												new Color(208,132,153),//6
												new Color(64,64,64),//7
												new Color(154,161,161),//8
												new Color(46,110,137),//9
												new Color(126,61,181),//10
												new Color(46,56,141),//11
												new Color(79,50,31),//12
												new Color(53,70,27),//13
												new Color(150,52,48),//14
												new Color(25,22,22)//15
												};

	//--------------------------
	// Constructor
	//--------------------------
	public ItemBedColor(int par1, int color) {
		super(par1);
		this.color = color;
	}
	
	public static Color getRGB(int input)
	{
		return input>=0 && input<=15 ?  rgbColors[input] : new Color(0,0,0);
	}
	
	public static int getWoolValueFromColor(Color input)
	{
		int currentClosestMatch = 0;
		int currentSmallestDiff = 765;
		for(int i = 0;i<rgbColors.length;i++)
		{
			int redDiff = Math.abs(input.getRed()-rgbColors[i].getRed());
			int greenDiff = Math.abs(input.getGreen()-rgbColors[i].getGreen());
			int blueDiff = Math.abs(input.getBlue()-rgbColors[i].getBlue());
			int totalDiff = redDiff+greenDiff+blueDiff;
			if(totalDiff<currentSmallestDiff)
			{
				currentClosestMatch = i;
				currentSmallestDiff = totalDiff;
			}
		}
		return currentClosestMatch;
	}
	
	public static void loadRecipes() {
		
		// Remove original recipe for creating beds
		ACM.RemoveRecipe(new ItemStack(Item.bed));
		
		// Recipe for colored beds = an original bed + the colored dye
		for(int i = 0; i < ItemBedColor.colors.length; i++) {
			
			for(int k = 0; k < ItemBedColor.colors.length; k++) {
				
				GameRegistry.addShapelessRecipe(new ItemStack(ItemBedColor.items[i]), new Object[] {ItemBedColor.items[k], new ItemStack(Item.dyePowder,1,i)});
			}
			//This line is no longer needed, Xaeroxe's code below replaces it.
			//GameRegistry.addRecipe(new ItemStack(ItemBedColor.items[i]), new Object[] {"XXX","###",'X',new ItemStack(Block.cloth,1,15-i),'#',Block.planks});
		}
		//Code written by Xaeroxe, adds recipes for beds with
		//every possible combination of wool blocks.
		for(int x = 0; x < ItemBedColor.colors.length; x++)
		{
			for(int y = 0; y < ItemBedColor.colors.length; y++)
			{
				for(int z = 0; z < ItemBedColor.colors.length; z++)
				{
					//The nested for statements will go through every possible combination of x y and z.
					//x y and z represent the colors to be used.
					//Crafting recipe like so:
					//xyz
					//ppp
					//where p is wooden planks
					if(x==y)
					{
						GameRegistry.addRecipe(new ItemStack(ItemBedColor.items[x]), new Object[] {"xyz","ppp",'x',new ItemStack(Block.cloth,1,15-x),'y',new ItemStack(Block.cloth,1,15-y),'z',new ItemStack(Block.cloth,1,15-z),'p',Block.planks});
					}
					else if(y==z)
					{
						GameRegistry.addRecipe(new ItemStack(ItemBedColor.items[y]), new Object[] {"xyz","ppp",'x',new ItemStack(Block.cloth,1,15-x),'y',new ItemStack(Block.cloth,1,15-y),'z',new ItemStack(Block.cloth,1,15-z),'p',Block.planks});
					}
					else if(x==z)
					{
						GameRegistry.addRecipe(new ItemStack(ItemBedColor.items[z]), new Object[] {"xyz","ppp",'x',new ItemStack(Block.cloth,1,15-x),'y',new ItemStack(Block.cloth,1,15-y),'z',new ItemStack(Block.cloth,1,15-z),'p',Block.planks});
					}
					else
					{
						Color xcolor = getRGB(x);
						Color ycolor = getRGB(y);
						Color zcolor = getRGB(z);
						int redValue = (xcolor.getRed() + ycolor.getRed() + zcolor.getRed())/3;
						int greenValue = (xcolor.getGreen() + ycolor.getGreen() + zcolor.getGreen())/3;
						int blueValue = (xcolor.getBlue() + ycolor.getBlue() + zcolor.getBlue())/3;
						Color mixedColor = new Color(redValue, greenValue, blueValue);
						int colorId = getWoolValueFromColor(mixedColor);
						GameRegistry.addRecipe(new ItemStack(ItemBedColor.items[colorId]), new Object[] {"xyz","ppp",'x',new ItemStack(Block.cloth,1,15-x),'y',new ItemStack(Block.cloth,1,15-y),'z',new ItemStack(Block.cloth,1,15-z),'p',Block.planks});
					}
				}
			}
		}
	}
	
	public static void register() {
		
		initialize();
		
		for(int i = 0; i < colors.length; i++) {
			
			GameRegistry.registerBlock(blocks[i], "block_bed_" + colors[i]);
			GameRegistry.registerItem(items[i], "item_bed_" + colors[i]);
			LanguageRegistry.addName(items[i], capitalize(colors[i]) + " Bed");
		}
	}
	
	private static void initialize() {
		
		final int START_ID = 3100;

		for(int i = 0; i < colors.length; i++) {
			
			blocks[i] = new BlockBedColor(START_ID + i,i).setHardness(0.2F).setUnlocalizedName("block_bed_" + colors[i]).setTextureName("acm:bed_" + colors[i]);
			items[i] = new ItemBedColor(START_ID + i + 1, i).setMaxStackSize(1).setUnlocalizedName("item_bed_" + colors[i]).setTextureName("acm:bed_" + colors[i]);
		}
	}
	
	private static String capitalize(String s) {
		
		String first = s.substring(0,1);
		return first.toUpperCase() + s.substring(1);
	}
	
	/**
     * Callback for item usage. If the item does something special on right clicking, he will have one of those. Return
     * True if something happen and false if it don't. This is for ITEMS, not BLOCKS
     */
    @Override
	public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10)
    {
        if (par3World.isRemote)
        {
            return true;
        }
        else if (par7 != 1)
        {
            return false;
        }
        else
        {
            ++par5;
            int i1 = MathHelper.floor_double(par2EntityPlayer.rotationYaw * 4.0F / 360.0F + 0.5D) & 3;
            byte b0 = 0;
            byte b1 = 0;

            if (i1 == 0)
            {
                b1 = 1;
            }

            if (i1 == 1)
            {
                b0 = -1;
            }

            if (i1 == 2)
            {
                b1 = -1;
            }

            if (i1 == 3)
            {
                b0 = 1;
            }

            if (par2EntityPlayer.canPlayerEdit(par4, par5, par6, par7, par1ItemStack) && par2EntityPlayer.canPlayerEdit(par4 + b0, par5, par6 + b1, par7, par1ItemStack))
            {
                if (par3World.isAirBlock(par4, par5, par6) && par3World.isAirBlock(par4 + b0, par5, par6 + b1) && par3World.doesBlockHaveSolidTopSurface(par4, par5 - 1, par6) && par3World.doesBlockHaveSolidTopSurface(par4 + b0, par5 - 1, par6 + b1))
                {
                    par3World.setBlock(par4, par5, par6, blocks[color].blockID, i1, 3);

                    if (par3World.getBlockId(par4, par5, par6) == blocks[color].blockID)
                    {
                        par3World.setBlock(par4 + b0, par5, par6 + b1, blocks[color].blockID, i1 + 8, 3);
                    }

                    --par1ItemStack.stackSize;
                    return true;
                }
				return false;
            }
			return false;
        }
    }    
    
}