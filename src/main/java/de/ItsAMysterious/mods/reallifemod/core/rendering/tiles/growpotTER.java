package de.ItsAMysterious.mods.reallifemod.core.rendering.tiles;

import static org.lwjgl.opengl.GL11.GL_ALPHA_TEST;
import static org.lwjgl.opengl.GL11.GL_CULL_FACE;
import static org.lwjgl.opengl.GL11.GL_POLYGON;
import static org.lwjgl.opengl.GL11.glDisable;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushMatrix;
import static org.lwjgl.opengl.GL11.glRotatef;
import static org.lwjgl.opengl.GL11.glScalef;
import static org.lwjgl.opengl.GL11.glTranslatef;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;

import org.lwjgl.opengl.GL11;

import de.ItsAMysterious.mods.reallifemod.core.tiles.growpotTE;

public class growpotTER extends TileEntitySpecialRenderer{

private IModelCustom model;
private Tessellator tesselator;    
public growpotTER(){
	tesselator=Tessellator.instance;
	model=AdvancedModelLoader.loadModel(new ResourceLocation("reallifemod:models/furniture/growpot.obj"));	
}

@Override
public void renderTileEntityAt(TileEntity te, double x, double y, double z, float scale) {
	
	glPushMatrix(); 
	    glDisable(GL11.GL_CULL_FACE);
	    glEnable(GL11.GL_ALPHA_TEST);
	    glTranslatef((float)x+0.5F, (float)y+0.01F, (float)z+0.5F);
	    glScalef(1.5F,1.55F, 1.5F);
		glRotatef(te.getBlockMetadata() * 90, 0.0F, 1.0F, 0.0F);
		 if(te instanceof growpotTE&&((growpotTE)te).isFilled){
				glPushMatrix();
			    glEnable(GL_CULL_FACE);
			    glDisable(GL_ALPHA_TEST);
				this.bindTexture(new ResourceLocation("minecraft:textures/blocks/dirt.png"));
				tesselator.startDrawing(GL_POLYGON);
				for(float i=0;i<360;i++){
					tesselator.addVertexWithUV((Math.sin(i)*Math.PI/180)*22.5, 0.58F,(Math.cos(i)*Math.PI/180)*22.5,(Math.sin(i)*Math.PI/180)*22.5,(Math.cos(i)*Math.PI/180)*22.5);
				}
				tesselator.draw();
				glPopMatrix();
			}
	    this.bindTexture(new ResourceLocation("reallifemod:textures/tiles/growpot.png"));
	   this.model.renderAll();
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

