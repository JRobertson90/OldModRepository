package acm.christmasChest;

import net.minecraft.block.Block;
import net.minecraft.block.BlockChest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ContainerChest;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import acm.craftingChest.TileEntityCraftingChest;
import cpw.mods.fml.common.network.IGuiHandler;

public class GuiHandlerChristmasChest implements IGuiHandler {

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		
		TileEntity christmas = world.getBlockTileEntity(x, y, z);
        if(christmas instanceof TileEntityChristmasChest) {
        	BlockChest chest = (BlockChest) Block.blocksList[world.getBlockId(x, y, z)];
            return new ContainerChest(player.inventory, chest.getInventory(world, x, y, z));
        }
        return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		
		TileEntity christmas = world.getBlockTileEntity(x, y, z);
        if(christmas instanceof TileEntityChristmasChest) {
        	BlockChest chest = (BlockChest) Block.blocksList[world.getBlockId(x, y, z)];
        	return new GuiChristmasChest(player.inventory, chest.getInventory(world, x, y, z));
        }
        return null;
	}

	
	
}