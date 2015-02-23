package de.ItsAMysterious.mods.reallifemod.core.rendering.tiles;

import static org.lwjgl.opengl.GL11.GL_ALPHA_TEST;
import static org.lwjgl.opengl.GL11.GL_CULL_FACE;
import static org.lwjgl.opengl.GL11.GL_LIGHTING;
import static org.lwjgl.opengl.GL11.glDisable;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushMatrix;
import static org.lwjgl.opengl.GL11.glRotatef;
import static org.lwjgl.opengl.GL11.glTranslatef;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;

import org.lwjgl.opengl.GL11;

import de.ItsAMysterious.mods.reallifemod.core.tiles.fishtankTE;

public class fishtankTER extends TileEntitySpecialRenderer{
	private IModelCustom model;
	private RenderManager manager;

	public fishtankTER(){
		this.manager=RenderManager.instance;
		model=AdvancedModelLoader.loadModel(new ResourceLocation("reallifemod:models/everydaylife/fishtank.obj"));
	}

	@Override
	public void renderTileEntityAt(TileEntity te, double x, double y, double z, float scale) {
		this.doRender((fishtankTE) te, x, y, z, scale);
	}
	
	public void doRender(fishtankTE te, double x, double y, double z, float scale) {
		glPushMatrix();
	    glDisable(GL_LIGHTING);
		glPushMatrix();
	    glDisable(GL_CULL_FACE);
	    glEnable(GL_ALPHA_TEST);
		  glTranslatef((float)x+0.5F, (float)y, (float)z+0.5F);
			glRotatef(te.getBlockMetadata() * 90, 0.0F, 1.0F, 0.0F);
	    Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation("reallifemod:textures/tiles/fishtank.png"));
	    this.model.renderAll();
    glPopMatrix();
		if(te.isFilled==true){
		glDisable(GL_CULL_FACE);
		glDisable(GL_ALPHA_TEST);
        IIcon iicon = Blocks.water.getIcon(0, 0);
        IIcon iicon1 = Blocks.water.getIcon(1, 10);
        GL11.glPushMatrix();
        GL11.glTranslatef((float)x+0.5F, (float)y, (float)z+0.5F);
        float f1 =0.5F;
        Tessellator tessellator = Tessellator.instance;
        float f2 = 0.5F;
        float f3 = 0.0F;
        float f4 = 0.1F/ f1;
        float f5 =0.0F;
        GL11.glTranslatef(0.0F, 0.0F, 0.0F);
        float f6 = 0.0F;
        int i = 0;
        tessellator.startDrawingQuads();
        while (f4 > 0.0F)
        {
            IIcon iicon2 = i % 2 == 0 ? iicon : iicon1;
            this.bindTexture(TextureMap.locationBlocksTexture);
            float f7 = iicon2.getMinU();
            float f8 = iicon2.getMinV();
            float f9 = iicon2.getMaxU();
            float f10 = iicon2.getMaxV();

            if (i / 2 % 2 == 0)
            {
                float f11 = f9;
                f9 = f7;
                f7 = f11;
            }
            if(te.getBlockMetadata()==1||te.getBlockMetadata()==3){
            tessellator.setColorOpaque(140, 140, 140);
            //left side
            tessellator.addVertexWithUV(-0.5, 0.8, -1.0, f9, f10);
            tessellator.addVertexWithUV(0.5F, 0.8, -1.0, f7, f10);
            tessellator.addVertexWithUV(0.5F, 0, -1.0, f7, f8);
            tessellator.addVertexWithUV(-0.5, 0, -1.0, f9, f8);
            
            //right side
            tessellator.addVertexWithUV(-0.5, 0.8, 1.0, f9, f10);
            tessellator.addVertexWithUV(0.5F, 0.8, 1.0, f7, f10);
            tessellator.addVertexWithUV(0.5F, 0, 1.0, f7, f8);
            tessellator.addVertexWithUV(-0.5, 0, 1.0, f9, f8);
            
            //front
            tessellator.addVertexWithUV(0.5, 0.8, -1.0, f9, f10);
            tessellator.addVertexWithUV(0.5F, 0.8, 1, f7, f10);
            tessellator.addVertexWithUV(0.5F, 0, 1, f7, f8);
            tessellator.addVertexWithUV(0.5, 0, -1, f9, f8);
            
            //back
            tessellator.addVertexWithUV(-0.5, 0.8, -1.0, f9, f10);
            tessellator.addVertexWithUV(-0.5, 0.8, 1, f7, f10);
            tessellator.addVertexWithUV(-0.5, 0, 1, f7, f8);
            tessellator.addVertexWithUV(-0.5, 0, -1, f9, f8);
            
            //top
            tessellator.addVertexWithUV(-0.5, 0.8, -1.0, f9, f10);
            tessellator.addVertexWithUV(-0.5, 0.8, 1, f7, f10);
            tessellator.addVertexWithUV(0.5, 0, 1, f7, f8);
            tessellator.addVertexWithUV(0.5, 0, -1, f9, f8);
            
            }
            
            if(te.getBlockMetadata()==0||te.getBlockMetadata()==2){
            tessellator.setColorOpaque(140, 140, 140);
            //left side
            tessellator.addVertexWithUV(-1.0, 0.8, -0.5, f9, f10);
            tessellator.addVertexWithUV(-1.0, 0.8, 0.5, f7, f10);
            tessellator.addVertexWithUV(-1.0F, 0, 0.5, f7, f8);
            tessellator.addVertexWithUV(-1.0, 0, -0.5, f9, f8);
            
            //right side
            tessellator.addVertexWithUV(1.0, 0.8, -0.5, f9, f10);
            tessellator.addVertexWithUV(1.0, 0.8, 0.5F, f7, f10);
            tessellator.addVertexWithUV(1.0, 0, 0.5F, f7, f8);
            tessellator.addVertexWithUV(1.0, 0, -0.5, f9, f8);
            
            //front
            tessellator.addVertexWithUV(-1.0, 0.8, 0.5, f9, f10);
            tessellator.addVertexWithUV(1, 0.8, 0.5, f7, f10);
            tessellator.addVertexWithUV(1, 0, 0.5, f7, f8);
            tessellator.addVertexWithUV(-1, 0, 0.5, f9, f8);
            
            //back
            tessellator.addVertexWithUV(-1, 0.8, -0.5, f9, f10);
            tessellator.addVertexWithUV(1, 0.8, -0.5, f7, f10);
            tessellator.addVertexWithUV(1, 0, -0.5, f7, f8);
            tessellator.addVertexWithUV(-1, 0, -0.5, f9, f8);
            
            //top
            tessellator.addVertexWithUV(-1.0, 0.8, -0.5, f9, f10);
            tessellator.addVertexWithUV(1, 0.8, -0.5, f7, f10);
            tessellator.addVertexWithUV(1, 0.8, 0.5, f7, f8);
            tessellator.addVertexWithUV(-1, 0.8, 0.5, f9, f8);
            
            }
            f4 -= 0.45F;
            f5 -= 0.45F;
            f2 *= 0.9F;
            f6 += 0.03F;
            ++i;
        }
        tessellator.draw();
        GL11.glPopMatrix();
		}
	    glEnable(GL_LIGHTING);
		glPopMatrix();
	}

}
