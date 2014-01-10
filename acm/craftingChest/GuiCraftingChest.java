package acm.craftingChest;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

import org.lwjgl.opengl.GL11;

public class GuiCraftingChest extends GuiContainer {

	private static final ResourceLocation craftingChestResourceLocation = new ResourceLocation("acm:textures/gui/container/crafting_chest.png");
	private int color = 0;
	private int changecolor = 0;
	
	public GuiCraftingChest (InventoryPlayer inventoryPlayer, TileEntityCraftingChest tileEntity, World world, int x, int y, int z) {
		super(new ContainerCraftingChest(inventoryPlayer, tileEntity, world, x, y, z));
		this.ySize = 236;
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int param1, int param2) {
		// the parameters for drawString are: text, x, y, color
		fontRenderer.drawString("Crafting Chest", 8, 8, 0);
		fontRenderer.drawString(StatCollector.translateToLocal("container.inventory"), 8, ySize - 93 + 2, 0);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3) {
		this.mc.renderEngine.bindTexture(craftingChestResourceLocation );
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		int x = (width - xSize) / 2;
		int y = (height - (ySize)) / 2;
		this.drawTexturedModalRect(x, y, 0, 0, xSize, ySize);
	}

}
