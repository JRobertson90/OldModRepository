package acm.craftingChest;

import net.minecraft.block.Block;
import net.minecraft.block.BlockChest;
import net.minecraft.client.model.ModelChest;
import net.minecraft.client.model.ModelLargeChest;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class TileEntityCraftingChestRenderer extends TileEntitySpecialRenderer {

	private static final ResourceLocation RES_CRAFTING_CHEST = new ResourceLocation("acm:textures/entity/chest/crafting.png");

	private static int rotator = 0;

	/** The normal small chest model. */
	private ModelChest chestModel = new ModelChest();

	/** The large double chest model. */
	private ModelChest largeChestModel = new ModelLargeChest();

	public TileEntityCraftingChestRenderer()
	{

	}

	/**
	 * Renders the TileEntity for the chest at a position.
	 */
	public void renderTileEntityChestAt(TileEntityChest par1TileEntityChest, double par2, double par4, double par6, float par8)
	{
		int facing;

		if (!par1TileEntityChest.hasWorldObj()) {
			facing = 0;
		}
		else {
			facing = par1TileEntityChest.getBlockMetadata();
		}

		ModelChest modelchest;

		modelchest = this.chestModel;
		this.bindTexture(RES_CRAFTING_CHEST);

		GL11.glPushMatrix();
		GL11.glEnable(GL12.GL_RESCALE_NORMAL);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		GL11.glTranslatef((float)par2 - 0.06F, (float)par4 + 1.06F, (float)par6 + 1.06F);
		GL11.glScalef(1.12F, -1.06F, -1.12F);
		GL11.glTranslatef(0.5F, 0.5F, 0.5F);

		short rotation = 0;

		switch(facing) {
			case 2: rotation = 180; break;
			case 3: rotation = 0; break;
			case 4: rotation = 90; break;
			case 5: rotation = -90; break;
		}

		GL11.glRotatef(rotation, 0.0F, 1.0F, 0.0F);
		GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
		float f1 = par1TileEntityChest.prevLidAngle + (par1TileEntityChest.lidAngle - par1TileEntityChest.prevLidAngle) * par8;
		float f2;

		f1 = 1.0F - f1;
		f1 = 1.0F - f1 * f1 * f1;
		modelchest.chestLid.rotateAngleX = -(f1 * (float)Math.PI / 2.0F);
		modelchest.renderAll();
		GL11.glDisable(GL12.GL_RESCALE_NORMAL);
		GL11.glPopMatrix();
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
	}

	@Override
	public void renderTileEntityAt(TileEntity par1TileEntity, double par2, double par4, double par6, float par8)
	{
		this.renderTileEntityChestAt((TileEntityChest)par1TileEntity, par2, par4, par6, par8);
	}

}