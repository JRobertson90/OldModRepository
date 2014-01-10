package acm.craftingChest;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;

public class GuiHandlerCraftingChest implements IGuiHandler {

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		TileEntity tileEntity = world.getBlockTileEntity(x, y, z);
        if(tileEntity instanceof TileEntityCraftingChest){
                return new ContainerCraftingChest(player.inventory, (TileEntityCraftingChest) tileEntity, world, x, y, z);
        }
        return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		TileEntity tileEntity = world.getBlockTileEntity(x, y, z);
        if(tileEntity instanceof TileEntityCraftingChest){
                return new GuiCraftingChest(player.inventory, (TileEntityCraftingChest) tileEntity, world, x, y, z);
        }
        return null;
	}

	
	
}
