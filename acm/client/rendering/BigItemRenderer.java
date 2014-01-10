package acm.client.rendering;

import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraftforge.client.IItemRenderer;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import acm.item.ACMItem;

public class BigItemRenderer implements IItemRenderer {

	@Override
	public boolean handleRenderType(ItemStack item, ItemRenderType type) {
		
		switch(type) {
		case ENTITY: return false;
		case EQUIPPED: return true;
		case EQUIPPED_FIRST_PERSON: return true;
		case INVENTORY: return false;
		case FIRST_PERSON_MAP: return false;
		default:
			break;
		}
		return false;
	}

	@Override
	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item,
			ItemRendererHelper helper) {
		
		switch(helper) {
		case ENTITY_ROTATION: return true;
		case ENTITY_BOBBING: return true;
		case EQUIPPED_BLOCK: return false;
		case BLOCK_3D: return false;
		case INVENTORY_BLOCK: return false;
		default:
			break;
		}
		return false;
	}

	@Override
	public void renderItem(ItemRenderType type, ItemStack item, Object... data) {

		EntityLivingBase par1EntityLivingBase = (EntityLivingBase) data[1];
		ItemStack par2ItemStack = item;
		
		Icon icon = par1EntityLivingBase.getItemIcon(par2ItemStack, 0);

        if (icon == null)
        {
            GL11.glPopMatrix();
            return;
        }

        GL11.glEnable(GL12.GL_RESCALE_NORMAL);
        
        
        // This is here for reference:
//        GL11.glTranslatef(0.0F, -0.3F, 0.0F);
//        GL11.glScalef(1.5F, 1.5F, 1.5F);
//        GL11.glRotatef(50.0F, 0.0F, 1.0F, 0.0F);
//        GL11.glRotatef(335.0F, 0.0F, 0.0F, 1.0F); // Up and down tilt
//        GL11.glTranslatef(-0.9375F, -0.0625F, 0.0F);
        
        GL11.glScalef(2.0F, 2.0F, 2.0F);
        GL11.glTranslatef(-0.4F, -0.2F, 0.0F);
        GL11.glRotatef(10.0F, 0.0F, 0.0F, 1.0F);
        
        if(item.itemID == ACMItem.blueShovel.itemID) {
//        	GL11.glRotatef(10.0F, 0.0F, 0.0F, 1.0F);
//        	GL11.glTranslatef(-0.4F, -0.2F, 0.0F);
        }
        
        Tessellator tessellator = Tessellator.instance;
        float f = icon.getMinU();
        float f1 = icon.getMaxU();
        float f2 = icon.getMinV();
        float f3 = icon.getMaxV();
        ItemRenderer.renderItemIn2D(tessellator, f1, f2, f, f3, icon.getIconWidth(), icon.getIconHeight(), 0.0625F);
        GL11.glDisable(GL12.GL_RESCALE_NORMAL);
	}

}
