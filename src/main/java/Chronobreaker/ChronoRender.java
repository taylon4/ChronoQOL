package Chronobreaker;

import java.awt.Color;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.AxisAlignedBB;

public class ChronoRender {
	
	public static void draw(AxisAlignedBB abb, float r, float g, float b) {
		float a = 0.25f;
		
		Tessellator ts = Tessellator.getInstance();
		WorldRenderer wr = ts.getWorldRenderer();
		
		//7 is GL_QUADS
		wr.begin(7, DefaultVertexFormats.POSITION_COLOR);
	    wr.pos(abb.minX, abb.minY, abb.minZ).color(r, g, b, a).endVertex();
	    wr.pos(abb.minX, abb.maxY, abb.minZ).color(r, g, b, a).endVertex();
	    wr.pos(abb.maxX, abb.minY, abb.minZ).color(r, g, b, a).endVertex();
	    wr.pos(abb.maxX, abb.maxY, abb.minZ).color(r, g, b, a).endVertex();
	    wr.pos(abb.maxX, abb.minY, abb.maxZ).color(r, g, b, a).endVertex();
	    wr.pos(abb.maxX, abb.maxY, abb.maxZ).color(r, g, b, a).endVertex();
	    wr.pos(abb.minX, abb.minY, abb.maxZ).color(r, g, b, a).endVertex();
	    wr.pos(abb.minX, abb.maxY, abb.maxZ).color(r, g, b, a).endVertex();
	    ts.draw();
		
		 wr.begin(7, DefaultVertexFormats.POSITION_COLOR);
		    wr.pos(abb.maxX, abb.maxY, abb.minZ).color(r, g, b, a).endVertex();
		    wr.pos(abb.maxX, abb.minY, abb.minZ).color(r, g, b, a).endVertex();
		    wr.pos(abb.minX, abb.maxY, abb.minZ).color(r, g, b, a).endVertex();
		    wr.pos(abb.minX, abb.minY, abb.minZ).color(r, g, b, a).endVertex();
		    wr.pos(abb.minX, abb.maxY, abb.maxZ).color(r, g, b, a).endVertex();
		    wr.pos(abb.minX, abb.minY, abb.maxZ).color(r, g, b, a).endVertex();
		    wr.pos(abb.maxX, abb.maxY, abb.maxZ).color(r, g, b, a).endVertex();
		    wr.pos(abb.maxX, abb.minY, abb.maxZ).color(r, g, b, a).endVertex();
		    ts.draw();
		    wr.begin(7, DefaultVertexFormats.POSITION_COLOR);
		    wr.pos(abb.minX, abb.maxY, abb.minZ).color(r, g, b, a).endVertex();
		    wr.pos(abb.maxX, abb.maxY, abb.minZ).color(r, g, b, a).endVertex();
		    wr.pos(abb.maxX, abb.maxY, abb.maxZ).color(r, g, b, a).endVertex();
		    wr.pos(abb.minX, abb.maxY, abb.maxZ).color(r, g, b, a).endVertex();
		    wr.pos(abb.minX, abb.maxY, abb.minZ).color(r, g, b, a).endVertex();
		    wr.pos(abb.minX, abb.maxY, abb.maxZ).color(r, g, b, a).endVertex();
		    wr.pos(abb.maxX, abb.maxY, abb.maxZ).color(r, g, b, a).endVertex();
		    wr.pos(abb.maxX, abb.maxY, abb.minZ).color(r, g, b, a).endVertex();
		    ts.draw();
		    wr.begin(7, DefaultVertexFormats.POSITION_COLOR);
		    wr.pos(abb.minX, abb.minY, abb.minZ).color(r, g, b, a).endVertex();
		    wr.pos(abb.maxX, abb.minY, abb.minZ).color(r, g, b, a).endVertex();
		    wr.pos(abb.maxX, abb.minY, abb.maxZ).color(r, g, b, a).endVertex();
		    wr.pos(abb.minX, abb.minY, abb.maxZ).color(r, g, b, a).endVertex();
		    wr.pos(abb.minX, abb.minY, abb.minZ).color(r, g, b, a).endVertex();
		    wr.pos(abb.minX, abb.minY, abb.maxZ).color(r, g, b, a).endVertex();
		    wr.pos(abb.maxX, abb.minY, abb.maxZ).color(r, g, b, a).endVertex();
		    wr.pos(abb.maxX, abb.minY, abb.minZ).color(r, g, b, a).endVertex();
		    ts.draw();
		    wr.begin(7, DefaultVertexFormats.POSITION_COLOR);
		    wr.pos(abb.minX, abb.minY, abb.minZ).color(r, g, b, a).endVertex();
		    wr.pos(abb.minX, abb.maxY, abb.minZ).color(r, g, b, a).endVertex();
		    wr.pos(abb.minX, abb.minY, abb.maxZ).color(r, g, b, a).endVertex();
		    wr.pos(abb.minX, abb.maxY, abb.maxZ).color(r, g, b, a).endVertex();
		    wr.pos(abb.maxX, abb.minY, abb.maxZ).color(r, g, b, a).endVertex();
		    wr.pos(abb.maxX, abb.maxY, abb.maxZ).color(r, g, b, a).endVertex();
		    wr.pos(abb.maxX, abb.minY, abb.minZ).color(r, g, b, a).endVertex();
		    wr.pos(abb.maxX, abb.maxY, abb.minZ).color(r, g, b, a).endVertex();
		    ts.draw();
		    wr.begin(7, DefaultVertexFormats.POSITION_COLOR);
		    wr.pos(abb.minX, abb.maxY, abb.maxZ).color(r, g, b, a).endVertex();
		    wr.pos(abb.minX, abb.minY, abb.maxZ).color(r, g, b, a).endVertex();
		    wr.pos(abb.minX, abb.maxY, abb.minZ).color(r, g, b, a).endVertex();
		    wr.pos(abb.minX, abb.minY, abb.minZ).color(r, g, b, a).endVertex();
		    wr.pos(abb.maxX, abb.maxY, abb.minZ).color(r, g, b, a).endVertex();
		    wr.pos(abb.maxX, abb.minY, abb.minZ).color(r, g, b, a).endVertex();
		    wr.pos(abb.maxX, abb.maxY, abb.maxZ).color(r, g, b, a).endVertex();
		    wr.pos(abb.maxX, abb.minY, abb.maxZ).color(r, g, b, a).endVertex();
		    ts.draw();
	}
	public static void bbRender(AxisAlignedBB bb) {

	    int color = Color.BLUE.getRGB();
	    
	    AxisAlignedBB abb = new AxisAlignedBB(bb.minX - 0.05D - (Utils.mc.getRenderManager()).viewerPosX, 
				bb.minY - (Utils.mc.getRenderManager()).viewerPosY, bb.minZ - 0.05D - (Utils.mc.getRenderManager()).viewerPosZ, 
				bb.maxX + 0.05D - (Utils.mc.getRenderManager()).viewerPosX, 
				bb.maxY + 0.1D - (Utils.mc.getRenderManager()).viewerPosY, 
				bb.maxZ + 0.05D- (Utils.mc.getRenderManager()).viewerPosZ);
	    
	    float a = (color >> 24 & 0xFF) / 255.0F;
	    float r = (color >> 16 & 0xFF) / 255.0F;
	    float g = (color >> 8 & 0xFF) / 255.0F;
	    float b = (color & 0xFF) / 255.0F;
		GlStateManager.pushMatrix();
		GL11.glBlendFunc(770, 771);
		GL11.glEnable(3042);
		GL11.glDisable(3553);
		
		GL11.glDisable(2929);
		
		GL11.glDepthMask(false);
		
		GL11.glLineWidth(1.5F);
		 
		GL11.glColor4f(r, g, b, a);
		
		RenderGlobal.drawSelectionBoundingBox(abb);
		
		draw(abb, r, g, b);
		GL11.glEnable(3553);
		GL11.glEnable(2929);
		GL11.glDepthMask(true);
		GL11.glDisable(3042);
		GlStateManager.popMatrix();
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
	  }
	
	
	
}

