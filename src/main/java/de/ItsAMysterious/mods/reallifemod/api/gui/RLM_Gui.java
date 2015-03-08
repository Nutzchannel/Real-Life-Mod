package de.ItsAMysterious.mods.reallifemod.api.gui;

import static org.lwjgl.opengl.GL11.GL_BLEND;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.glColor3f;
import static org.lwjgl.opengl.GL11.glColor4f;
import static org.lwjgl.opengl.GL11.glDisable;
import static org.lwjgl.opengl.GL11.glEnable;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;

public class RLM_Gui extends GuiScreen{
	
	public void drawTexturedModalRectRotate(double left, double top, double width, double height, double uLeft, double vTop, double uWidth, 
            double vHeight, float rot)
    {
        GL11.glPushMatrix();
        GL11.glTranslated(left + width / 2D, top + height / 2D, 0.0D);
        GL11.glRotatef(rot, 0.0F, 0.0F, 1.0F);
        RenderHelper.enableStandardItemLighting();
        float f = 0.00390625F;
        Tessellator tessellator = Tessellator.instance;
        tessellator.startDrawingQuads();
        tessellator.addVertexWithUV(-width / 2D, height / 2D, -11.0F, uLeft * 0.00390625D, (vTop + vHeight) * 0.00390625D);
        tessellator.addVertexWithUV(width / 2D, height / 2D, -11.0F, (uLeft + uWidth) * 0.00390625D, (vTop + vHeight) * 0.00390625D);
        tessellator.addVertexWithUV(width / 2D, -height / 2D, -11.0F, (uLeft + uWidth) * 0.00390625D, vTop * 0.00390625D);
        tessellator.addVertexWithUV(-width / 2D, -height / 2D, -11.0F, uLeft * 0.00390625D, vTop * 0.00390625D);
        tessellator.draw();
        GL11.glPopMatrix();
    }
	
	 public void drawRect(int x1, int y1, int x2, int y2,float value, int color,float zPos)
	    {
	        int j1;

	        if (x1 < x2)
	        {
	            j1 = x1;
	            x1 = x2;
	            x2 = j1;
	        }

	        if (y1 < y2)
	        {
	            j1 = y1;
	            y1 = y2;
	            y2 = j1;
	        }

	        float f3 = (color >> 24 & 255) / 255.0F;
	        float f = (color >> 16 & 255) / 255.0F;
	        float f1 = (color >> 8 & 255) / 255.0F;
	        float f2 = (color & 255) / 255.0F;
	        Tessellator tessellator = Tessellator.instance;
	        glEnable(GL_BLEND);
	        glDisable(GL_TEXTURE_2D);
	        OpenGlHelper.glBlendFunc(770, 771, 1, 0);
	        glColor3f(1.0F, 1.0F, 1.0F);
	        glColor4f(f, f1, f2, f3);
	        tessellator.startDrawingQuads();
	        	tessellator.addVertex(x1, y1, zPos);
	        	tessellator.addVertex((double)x2-this.mc.displayWidth-(x1+x2)/100*value,y1, zPos);
	        	tessellator.addVertex((double)x2-this.mc.displayWidth-(x1+x2)/100*value, y2, zPos);
	        	tessellator.addVertex(x1, y2, zPos);
	        tessellator.draw();
	        //Hintergrund
	        glEnable(GL_TEXTURE_2D);
	        glDisable(GL_BLEND);
	    }
	 
	 public void drawProgressBar(int x1, int y1, float position,float min, float max, int color,float zPos)
	    {
	        Tessellator tessellator = Tessellator.instance;
	        glEnable(GL_BLEND);
	        glDisable(GL_TEXTURE_2D);
	        OpenGlHelper.glBlendFunc(770, 771, 1, 0);
	        glColor3f(1.0F, 1.0F, 1.0F);
	        tessellator.setColorRGBA(color,color, color,255);
	        tessellator.startDrawingQuads();
	        	tessellator.addVertex(x1, y1, zPos);
	        	tessellator.addVertex(x1+100, y1, zPos);
	        	tessellator.addVertex(x1+100, y1+10D, zPos);
	        	tessellator.addVertex(x1, y1+10, zPos);
	        tessellator.draw();
	        //Hintergrund
	        glEnable(GL_TEXTURE_2D);
	    }
	
    /**public void drawTexturedModalRect(int p_73729_1_, int p_73729_2_, int p_73729_3_, int p_73729_4_, int p_73729_5_, int p_73729_6_)
    {
        float f = 0.00390625F;
        Tessellator tessellator = Tessellator.instance;
        tessellator.startDrawingQuads();
        tessellator.addVertexWithUV((double)(p_73729_1_ + 0), (double)(p_73729_2_ + p_73729_6_), (double)this.zLevel, (double)((float)(p_73729_3_ + 0) * f), (double)((float)(p_73729_4_ + p_73729_6_) ));
        tessellator.addVertexWithUV((double)(p_73729_1_ + p_73729_5_), (double)(p_73729_2_ + p_73729_6_), (double)this.zLevel, (double)((float)(p_73729_3_ + p_73729_5_) * f), (double)((float)(p_73729_4_ + p_73729_6_)));
        tessellator.addVertexWithUV((double)(p_73729_1_ + p_73729_5_), (double)(p_73729_2_ + 0), (double)this.zLevel, (double)((float)(p_73729_3_ + p_73729_5_) * f), (double)((float)(p_73729_4_ + 0) ));
        tessellator.addVertexWithUV((double)(p_73729_1_ + 0), (double)(p_73729_2_ + 0), (double)this.zLevel, (double)((float)(p_73729_3_ + 0) * f), (double)((float)(p_73729_4_ + 0)));
        tessellator.draw();
    }*/
	
    public void drawLineStipple(double line[], int color, int factor, int pattern)
    {
        GL11.glEnable(2852);
        GL11.glLineStipple(factor, (short)pattern);
        drawLine(line, color);
        GL11.glDisable(2852);
    }

    public void drawLine(double line[], int color)
    {
        drawLine(line, color, 1);
    }

    public void drawString(String s, int x, int y, int color)
    {
        drawString(fontRendererObj, s, x, y, color);
    }

    public void drawCenteredString(String s, int x, int y, int color)
    {
        drawCenteredString(fontRendererObj, s, x, y, color);
    }
	
    public void drawLine(double line[], int color, int mode)
    {
        GL11.glPushMatrix();
        GL11.glEnable(3042);
        GL11.glDisable(3553);
        GL11.glBlendFunc(770, 771);
        GL11.glColor4ub((byte)(color >> 16 & 0xff), (byte)(color >> 8 & 0xff), (byte)(color >> 0 & 0xff), (byte)(color >> 24 & 0xff));
        Tessellator tessellator = Tessellator.instance;
        tessellator.setBrightness(mode);
        tessellator.startDrawing(GL11.GL_LINES);
        for(int i = 0; i < line.length; i += 2)
            tessellator.addVertex(line[i + 0], line[i + 1], color);
        tessellator.draw();
        GL11.glEnable(3553);
        GL11.glDisable(3042);
        GL11.glColor4b((byte)-1, (byte)-1, (byte)-1, (byte)-1);
        GL11.glPopMatrix();
    }
    
    public void drawPoints(double points[], int color, int pointWidth)
    {
        int prevWidth = GL11.glGetInteger(2833);
        GL11.glPushMatrix();
        GL11.glEnable(3042);
        GL11.glDisable(3553);
        GL11.glBlendFunc(770, 771);
        GL11.glColor4ub((byte)(color >> 16 & 0xff), (byte)(color >> 8 & 0xff), (byte)(color >> 0 & 0xff), (byte)(color >> 24 & 0xff));
        GL11.glPointSize(pointWidth);
        Tessellator tessellator = Tessellator.instance;
        tessellator.setBrightness(0);
        for(int i = 0; i < points.length; i += 2)
            tessellator.addVertex(points[i], points[i + 1], 0.0D);

        tessellator.draw();
        GL11.glEnable(3553);
        GL11.glDisable(3042);
        GL11.glPopMatrix();
        GL11.glColor4b((byte)-1, (byte)-1, (byte)-1, (byte)-1);
        GL11.glPointSize(prevWidth);
    }
	
	@Override
	public void confirmClicked(boolean b1, int id)
    {
        super.confirmClicked(b1, id);
    }
	
	  @Override
	protected void drawGradientRect(int p_73733_1_, int p_73733_2_, int p_73733_3_, int p_73733_4_, int p_73733_5_, int p_73733_6_)
	    {
	        float f = (p_73733_5_ >> 24 & 255) / 255.0F;
	        float f1 = (p_73733_5_ >> 16 & 255) / 255.0F;
	        float f2 = (p_73733_5_ >> 8 & 255) / 255.0F;
	        float f3 = (p_73733_5_ & 255) / 255.0F;
	        float f4 = (p_73733_6_ >> 24 & 255) / 255.0F;
	        float f5 = (p_73733_6_ >> 16 & 255) / 255.0F;
	        float f6 = (p_73733_6_ >> 8 & 255) / 255.0F;
	        float f7 = (p_73733_6_ & 255) / 255.0F;
	        GL11.glDisable(GL11.GL_TEXTURE_2D);
	        GL11.glEnable(GL11.GL_BLEND);
	        GL11.glEnable(GL11.GL_ALPHA_TEST);
	        OpenGlHelper.glBlendFunc(770, 771, 1, 0);
	        GL11.glShadeModel(GL11.GL_SMOOTH);
	        Tessellator tessellator = Tessellator.instance;
	        tessellator.startDrawingQuads();
	        tessellator.setColorRGBA_F(f1, f2, f3, f);
	        tessellator.addVertex(p_73733_3_, p_73733_2_, this.zLevel);
	        tessellator.addVertex(p_73733_1_, p_73733_2_, this.zLevel);
	        tessellator.setColorRGBA_F(f5, f6, f7, f4);
	        tessellator.addVertex(p_73733_1_, p_73733_4_, this.zLevel);
	        tessellator.addVertex(p_73733_3_, p_73733_4_, this.zLevel);
	        tessellator.draw();
	        GL11.glShadeModel(GL11.GL_FLAT);
	        GL11.glDisable(GL11.GL_BLEND);
	        GL11.glEnable(GL11.GL_TEXTURE_2D);
	    }
	
}