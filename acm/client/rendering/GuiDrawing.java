package acm.client.rendering;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.event.ForgeSubscribe;

import org.lwjgl.opengl.GL11;

import acm.player.ExtendedPlayer;

public class GuiDrawing extends Gui{
	
	@ForgeSubscribe
    public void drawInGame(RenderGameOverlayEvent.Pre event)
	{
//		if(event.type.equals(ElementType.EXPERIENCE) || event.type.equals(ElementType.JUMPBAR))
//		{
//			Minecraft mc = Minecraft.getMinecraft();
//			ExtendedPlayer props = ExtendedPlayer.get(mc.thePlayer);
//			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
//			if(mc.thePlayer.capabilities.isCreativeMode)
//			{
//				return;
//			}
//			if(props.getFlashingTicksRemaining() % 4 == 1)
//			{
//				GL11.glColor4f(1.0F, 0.0F, 0.0F, 1.0F);
//			}
//			if(props.getFlashingTicksRemaining() > 0)
//			{
//				props.reduceFlashingTicksRemaining();
//			}
//			ScaledResolution res = event.resolution;
//			int width = res.getScaledWidth();
//	        int height = res.getScaledHeight();
//			int left = width / 2 - 90;
//	        short barWidth = 180;
//	        float normalizedStamina = (float)(props.getStamina())/((float) ExtendedPlayer.maxStamina);
//	        int filled = (int) (normalizedStamina * (float)(barWidth + 1));
//	        int top = height - 32 + 2;
//	        mc.renderEngine.bindTexture(new ResourceLocation("acm:textures/gui/acm_gui.png"));
//	        drawTexturedModalRect(left, top, 0, 0, (int) barWidth, 1);
//
//	        if (filled > 0)
//	        {
//	            drawTexturedModalRect(left, top, 0, 1, filled, 1);
//	        }
//	        mc.renderEngine.bindTexture(new ResourceLocation("textures/gui/icons.png"));
//		}
	}

}
