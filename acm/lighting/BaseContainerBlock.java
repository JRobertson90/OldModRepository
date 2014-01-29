//------------------------------------------------------------------------------------------------
//
//   Greg's Mod Base - Generic Block with Tile Entity
//
//------------------------------------------------------------------------------------------------

package acm.lighting;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import cpw.mods.fml.common.registry.GameRegistry;

public class BaseContainerBlock<TE extends TileEntity>
	extends BlockContainer implements BaseMod.IBlock
{

	static Random random = new Random();

	public int renderID = 0;
	Class<? extends TileEntity> tileEntityClass = null;
	String[] iconNames = null;
	Icon[] icons;

	public BaseContainerBlock(int id, Material material) {
		this(id, material, null);
	}

	public BaseContainerBlock(int id, Material material, Class<TE> teClass) {
		super(id, material);
		tileEntityClass = teClass;
		if (teClass != null) {
			try {
				GameRegistry.registerTileEntity(teClass, teClass.getName());
			}
			catch (IllegalArgumentException e) {
				// Ignore redundant registration
			}
		}
	}

	// -------------------------- Icons and Rendering -----------------------------
	
	public static Icon getIcon(Block block, IconRegister reg, String name) {
		//String modPackage = block.getClass().getPackage().getName();
		String assetKey = block.getClass().getPackage().getName().replace(".", "_");
		return reg.registerIcon(assetKey + ":" + name);
	}
	
	public static Icon[] getIcons(Block block, IconRegister reg, String... names) {
		Icon[] result = new Icon[names.length];
		for (int i = 0; i < names.length; i++)
			result[i] = getIcon(block, reg, names[i]);
		return result;
	}
	
	void setIconNames(String... names) {
		iconNames = names;
	}
	
	void setPrefixedIconNames(String prefix, String... suffixes) {
		String[] names = new String[suffixes.length];
		for (int i = 0; i < names.length; i++)
			names[i] = prefix + "-" + suffixes[i];
		setIconNames(names);
	}

	@Override
	public int getRenderType() {
		return renderID;
	}

	@Override
	public void setRenderType(int id) {
		renderID = id;
	}
	
	@Override
	public String getQualifiedRendererClassName() {
		String name = getRendererClassName();
		if (name != null)
			name = getClass().getPackage().getName() + "." + name;
		return name;
	}
	
	String getRendererClassName() {
		return null;
	}
	
	@Override
	public boolean renderAsNormalBlock() {
		return renderID == 0;
	}
	
	@Override
	public void registerIcons(IconRegister reg) {
		if (iconNames != null)
			icons = getIcons(reg, iconNames);
		else
			icons = getIcons(reg, getUnlocalizedName().substring(5));
	}
	
	protected Icon getIcon(IconRegister reg, String name) {
		return getIcon(this, reg, name);
	}

	protected Icon[] getIcons(IconRegister reg, String... names) {
		return getIcons(this, reg, names);
	}
	
	@Override
	public Icon getIcon(int side, int data) {
		return getLocalIcon(side, data);
	}
	
	Icon getLocalIcon(int side, int data) {
		if (icons != null) {
			if (side < icons.length)
				return icons[side];
			else
				return icons[icons.length - 1];
		}
		else
			return null;
	}
	
	// -------------------------- Tile Entity -----------------------------
	
	@Override
	public boolean hasTileEntity(int metadata) {
		return tileEntityClass != null;
	}
	
	public TE getTileEntity(IBlockAccess world, int x, int y, int z) {
		if (hasTileEntity())
			return (TE)world.getBlockTileEntity(x, y, z);
		else
			return null;
	}
	
	@Override
	public TileEntity createNewTileEntity(World world) {
		if (tileEntityClass != null) {
			try {
				return tileEntityClass.newInstance();
			}
			catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
		else
			return null;
	}
	
	@Override
	public void onBlockAdded(World world, int x, int y, int z) {
		super.onBlockAdded(world, x, y, z);
		TileEntity te = getTileEntity(world, x, y, z);
		if (te instanceof BaseMod.ITileEntity)
			((BaseMod.ITileEntity)te).onAddedToWorld();
	}

	public void setMetadata(World world, int x, int y, int z, int data, boolean notify) {
		if (notify)
			world.setBlockMetadataWithNotify(x, y, z, data, 0x1);
		else
			world.setBlockMetadataWithNotify(x, y, z, data, 0x0);
	}

	@Override
	public void breakBlock(World world, int x, int y, int z, int par5, int par6) {
		TileEntity te = world.getBlockTileEntity(x, y, z);
		if (te instanceof IInventory) {
			IInventory var7 = (IInventory)te;
			if (var7 != null) {
				for (int var8 = 0; var8 < var7.getSizeInventory(); ++var8) {
					ItemStack var9 = var7.getStackInSlot(var8);
					if (var9 != null) {
						float var10 = this.random.nextFloat() * 0.8F + 0.1F;
						float var11 = this.random.nextFloat() * 0.8F + 0.1F;
						EntityItem var14;
						for (float var12 = this.random.nextFloat() * 0.8F + 0.1F; var9.stackSize > 0; world.spawnEntityInWorld(var14)) {
							int var13 = this.random.nextInt(21) + 10;
							if (var13 > var9.stackSize)
								var13 = var9.stackSize;
							var9.stackSize -= var13;
							var14 = new EntityItem(world, (double)((float)x + var10), (double)((float)y + var11), (double)((float)z + var12), new ItemStack(var9.itemID, var13, var9.getItemDamage()));
							float var15 = 0.05F;
							var14.motionX = (double)((float)this.random.nextGaussian() * var15);
							var14.motionY = (double)((float)this.random.nextGaussian() * var15 + 0.2F);
							var14.motionZ = (double)((float)this.random.nextGaussian() * var15);
							if (var9.hasTagCompound())
								var14.getEntityItem().setTagCompound((NBTTagCompound)var9.getTagCompound().copy());
						}
					}
				}
			}
		}
		super.breakBlock(world, x, y, z, par5, par6);
	}

}

