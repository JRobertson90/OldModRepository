package acm.christmasChest;

import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;

public class ItemRendererChristmasChest implements IItemRenderer {

	@Override
	public boolean handleRenderType(ItemStack item, ItemRenderType type) {
		
		return true;
	}

	@Override
	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {
		
		return true;
	}

	@Override
	public void renderItem(ItemRenderType type, ItemStack item, Object... data) {
		TileEntityRenderer.instance.renderTileEntityAt(new TileEntityChristmasChest(), 0.0D, 0.0D, 0.0D, 0.0F);
	}

}
