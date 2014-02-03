package acm.block.moses;

import java.util.List;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.Icon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import acm.block.ACMBlock;
import acm.item.ACMItem;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemMosesStaff extends Item {
	
	//-------------------------------
	//  Fields
	//-------------------------------
	private Icon icons[] = new Icon[2];
	private String names[] = {"Moses Staff","Recall Moses Staff"};

	//-------------------------------
	//  Constructor
	//-------------------------------
	public ItemMosesStaff(int par1) {
		super(par1);
		this.setCreativeTab(CreativeTabs.tabTools);
		setHasSubtypes(true);
	}

	//-------------------------------
	//  On Created
	//-------------------------------
	public void onCreated(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
		if( par1ItemStack.stackTagCompound == null )
			par1ItemStack.setTagCompound( new NBTTagCompound( ) );
	}

	//-------------------------------
	//  On Item Use
	//-------------------------------
	public boolean onItemUse(ItemStack itemStack, EntityPlayer player, World world, int x, int y, int z, int par7, float par8, float par9, float par10)
	{

		if( itemStack.stackTagCompound == null )
			itemStack.setTagCompound( new NBTTagCompound( ) );
		
		if(itemStack.getItemDamage() == 0) {
			return placeStaff(itemStack,player,world,x,y,z,par7,par8,par9,par10);
		}
		
		return false;
	}
	
	//-------------------------------
	//  Place Staff
	//-------------------------------
	private boolean placeStaff(ItemStack itemStack, EntityPlayer player, World world, int x, int y, int z, int par7, float par8, float par9, float par10) {
		
		int direction = MathHelper.floor_double((double)(player.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
		
		if (player.canPlayerEdit(x, y+1, z, par7, itemStack) && player.canPlayerEdit(x, y+2, z, par7, itemStack)
				&& ! world.isBlockOpaqueCube(x, y+1, z) && ! world.isBlockOpaqueCube(x, y+2, z))
		{
			world.setBlock(x, y+1, z, ACMBlock.mosesStaff.blockID, direction, 3);
			itemStack.stackTagCompound.setInteger( "staff_x", x );
			itemStack.stackTagCompound.setInteger( "staff_y", y+1 );
			itemStack.stackTagCompound.setInteger( "staff_z", z );
			itemStack.setItemDamage(1);
			
			if (world.getBlockId(x, y+1, z) == ACMBlock.mosesStaff.blockID)
			{
				world.setBlock(x, y+2, z, ACMBlock.mosesStaff.blockID, direction + 4, 3);
			}
			
			MosesStaff.diffuseAir(world, x, y+1, z, true, direction);
			
			return true;
		}
		else
		{
			return false;
		}
		
	}
	
	//-------------------------------
	//  On Item Right Click
	//-------------------------------
	/** Called whenever this item is equipped and the right mouse button is pressed. Args: itemStack, world, entityPlayer */
    public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player)
    {
    	if(itemStack.getItemDamage() == 1) {
    		recallStaff(itemStack,player,world);
    	}
    	return itemStack;
    }
    
	//-------------------------------
	//  Recall Staff
	//-------------------------------
	private void recallStaff(ItemStack itemStack, EntityPlayer player, World world) {
		
		int staff_x = itemStack.stackTagCompound.getInteger("staff_x");
		int staff_y = itemStack.stackTagCompound.getInteger("staff_y");
		int staff_z = itemStack.stackTagCompound.getInteger("staff_z");
		
		if(world.getBlockId(staff_x, staff_y, staff_z) == ACMBlock.mosesStaff.blockID) {
			
			int meta = world.getBlockMetadata(staff_x, staff_y, staff_z);
			world.setBlockToAir(staff_x, staff_y, staff_z);
			world.markBlockForUpdate(staff_x, staff_y, staff_z);
			ACMBlock.mosesStaff.onBlockDestroyedByPlayer(world,staff_x,staff_y,staff_z, meta);
		}
		else {
			itemStack.stackSize--;
		}
		
		itemStack.setItemDamage(0);
	}

	@SideOnly(Side.CLIENT)

    /**
     * Gets an icon index based on an item's damage value
     */
    public Icon getIconFromDamage(int par1)
    {
        return icons[par1];
    }
	
	@SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister par1IconRegister)
    {
        icons[0] = par1IconRegister.registerIcon("acm:moses_staff");
        icons[1] = par1IconRegister.registerIcon("acm:moses_staff_recall");
    }
	
	public String getItemDisplayName(ItemStack itemStack)
	{
		return names[itemStack.getItemDamage()];
	}

	@Override
	public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4) {

		par3List.add("Parts the Red Sea");
	}

	public static void addRecipe() {
		
		// This is kind of a shapeless / shaped recipe. Draw a staff plus a book. Mirrored versions happen automatically.
		GameRegistry.addRecipe(new ItemStack(ACMItem.mosesStaff, 1), new Object[] {"S ", "S ", "SB", 'S', Item.stick, 'B', Item.book});
		GameRegistry.addRecipe(new ItemStack(ACMItem.mosesStaff, 1), new Object[] {"S ", "SB", "S ", 'S', Item.stick, 'B', Item.book});
		GameRegistry.addRecipe(new ItemStack(ACMItem.mosesStaff, 1), new Object[] {"SB", "S ", "S ", 'S', Item.stick, 'B', Item.book});
		GameRegistry.addRecipe(new ItemStack(ACMItem.mosesStaff, 1), new Object[] {"S  ", "S  ", "S B", 'S', Item.stick, 'B', Item.book});
		GameRegistry.addRecipe(new ItemStack(ACMItem.mosesStaff, 1), new Object[] {"S  ", "S B", "S  ", 'S', Item.stick, 'B', Item.book});
		GameRegistry.addRecipe(new ItemStack(ACMItem.mosesStaff, 1), new Object[] {"S B", "S  ", "S  ", 'S', Item.stick, 'B', Item.book});		
	}

}