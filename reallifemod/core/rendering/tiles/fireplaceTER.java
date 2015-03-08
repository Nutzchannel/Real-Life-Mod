package de.ItsAMysterious.mods.reallifemod.core.rendering.tiles;

import static org.lwjgl.opengl.GL11.GL_ALPHA_TEST;
import static org.lwjgl.opengl.GL11.GL_CULL_FACE;
import static org.lwjgl.opengl.GL11.GL_LIGHTING;
import static org.lwjgl.opengl.GL11.glDisable;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushMatrix;
import static org.lwjgl.opengl.GL11.glScalef;
import static org.lwjgl.opengl.GL11.glTranslated;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;

import org.lwjgl.opengl.GL11;

import de.ItsAMysterious.mods.reallifemod.core.tiles.fireplaceTE;

public class fireplaceTER extends TileEntitySpecialRenderer{
	private IModelCustom model;
	private RenderManager manager;
	
	
	public fireplaceTER(){
		this.manager=RenderManager.instance;
		model=AdvancedModelLoader.loadModel(new ResourceLocation("reallifemod:models/outdoor/fireplace.obj"));
	}

	@Override
	public void renderTileEntityAt(TileEntity te, double x, double y, double z, float scale) {
		this.doRender((fireplaceTE) te, x, y, z, scale);
	}
	
	public void doRender(fireplaceTE te, double x, double y, double z, float scale) {
		glPushMatrix();
		if(te.burning==true){
		GL11.glDisable(GL11.GL_LIGHTING);
        IIcon iicon = Blocks.fire.getFireIcon(0);
        IIcon iicon1 = Blocks.fire.getFireIcon(1);
        GL11.glPushMatrix();
        GL11.glTranslatef((float)x+0.5F, (float)y, (float)z+0.5F);
        float f1 =0.5F;
        GL11.glScalef(f1, f1, f1);
        Tessellator tessellator = Tessellator.instance;
        float f2 = 0.5F;
        float f3 = 0.0F;
        float f4 = 0.1F/ f1;
        float f5 =0.0F;
        GL11.glTranslatef(0.0F, 0.0F, 0.0F);
        GL11.glRotatef(-this.manager.playerViewY, 0.0F, 1.0F, 0.0F);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
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

            tessellator.addVertexWithUV(f2 - f3, 0.0F - f5, f6, f9, f10);
            tessellator.addVertexWithUV(-f2 - f3, 0.0F - f5, f6, f7, f10);
            tessellator.addVertexWithUV(-f2 - f3, 1.4F - f5, f6, f7, f8);
            tessellator.addVertexWithUV(f2 - f3, 1.4F - f5, f6, f9, f8);
            f4 -= 0.45F;
            f5 -= 0.45F;
            f2 *= 0.9F;
            f6 += 0.03F;
            ++i;
        }
        tessellator.draw();
        glEnable(GL_LIGHTING);
        GL11.glPopMatrix();
		}
        glPushMatrix();
		    glDisable(GL_CULL_FACE);
		    glEnable(GL_ALPHA_TEST);
		    glTranslated((float)x+0.5f, (float)y-te.getHeightToSubstract(), (float)z+0.5f);
		    glScalef(0.2F, 0.2F, 0.2F);
		    this.bindTexture(new ResourceLocation("reallifemod:textures/tiles/fireplace.png"));
		    this.model.renderAll();
		    glEnable(GL_CULL_FACE);
	    glPopMatrix();
	    glPopMatrix();
	    
	    
	}
	
	

	private void adjustLightFixture(World world, int i, int j, int k, Block block) {
	        Tessellator tess = Tessellator.instance;
	        float brightness = block.getMixedBrightnessForBlock(world, i, j, k);
	        int skyLight = world.getLightBrightnessForSkyBlocks(i, j, k, 0);
	        int modulousModifier = skyLight % 65536;
	        int divModifier = skyLight / 65536;
	        tess.setColorOpaque_F(brightness, brightness, brightness);
	        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit,  modulousModifier,  divModifier);
	}
	

}
