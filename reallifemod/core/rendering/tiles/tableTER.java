package de.ItsAMysterious.mods.reallifemod.core.rendering.tiles;

import static org.lwjgl.opengl.GL11.GL_ALPHA_TEST;
import static org.lwjgl.opengl.GL11.GL_CULL_FACE;
import static org.lwjgl.opengl.GL11.glDisable;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushMatrix;
import static org.lwjgl.opengl.GL11.glRotatef;
import static org.lwjgl.opengl.GL11.glScalef;
import static org.lwjgl.opengl.GL11.glTranslated;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;

import org.lwjgl.opengl.GL11;

import de.ItsAMysterious.mods.reallifemod.client.ClientProxy;
import de.ItsAMysterious.mods.reallifemod.core.tiles.tableTE;

public class tableTER extends TileEntitySpecialRenderer{
	private IModelCustom model;
	Tessellator tessellator;
	
	public tableTER(){
		tessellator=Tessellator.instance;
		model=AdvancedModelLoader.loadModel(new ResourceLocation("reallifemod:models/furniture/tableleg.obj"));
	}

	@Override
	public void renderTileEntityAt(TileEntity te, double x, double y, double z, float scale) {
		this.doRender((tableTE) te, x, y, z, scale);
	}
	
	public void doRender(tableTE tile, double x, double y, double z, float scale) {
		glPushMatrix();
		glPushMatrix();
	    tessellator.startDrawing(GL11.GL_POLYGON);
		glDisable(GL_CULL_FACE);
		for(int i=0; i<tile.edges.size()-1;i++){
			tessellator.addVertexWithUV(tile.edges.get(i).x,tile.edges.get(i).y , tile.edges.get(i).z, 0,0);
		}
		tessellator.draw();
		glPopMatrix();
	    glTranslated(x+0.5f, y, z+0.5f);
		glRotatef(tile.getBlockMetadata() * 90, 0.0F, 1.0F, 0.0F);
	    this.bindTexture(new ResourceLocation("minecraft:textures/blocks/planks_oak.png"));
	    this.model.renderAll();
	    this.bindTexture(new ResourceLocation("minecraft:textures/blocks/planks_oak.png"));
		tessellator.startDrawing(GL11.GL_POLYGON);
			for(int i=0; i<tile.edges.size();i++){
				tessellator.addVertexWithUV(tile.edges.get(i).x,tile.edges.get(i).y , tile.edges.get(i).z, 0,0);
			}
		tessellator.draw();
		glPopMatrix();
	}
	
	public void drawTheLine(double x, double y, double z){
		glPushMatrix();
	    Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation("minecraft:textures/blocks/planks_oak.png"));
	    tessellator.startDrawing(GL11.GL_QUADS);
		glDisable(GL_CULL_FACE);
	    glEnable(GL_ALPHA_TEST);
	    tessellator.addVertexWithUV(x,y+1,z,x,z);
	    tessellator.addVertexWithUV(x+1,y+1,z,x+1,z);
		tessellator.addVertexWithUV(0.5, y+1,0,1,1);
		tessellator.addVertexWithUV(-0.5, y+1,0,0,1);
	    tessellator.addVertexWithUV(x,y+0.9,z,x,z);
	    tessellator.addVertexWithUV(x+1,y+0.9,z,x+1,z);
		tessellator.addVertexWithUV(0.5, y+0.9,0,1,1);
		tessellator.addVertexWithUV(-0.5, y+0.9,0,0,1);
		
		tessellator.addVertexWithUV(x+1, y+1, z,x+1, y+1);
		tessellator.addVertexWithUV(0.5, y+1, 0,0,y+1);
		tessellator.addVertexWithUV(0.5, y+0.9, 0,x,y+0.9);
		tessellator.addVertexWithUV(x+1,y+0.9,z,0,1);
		
		tessellator.addVertexWithUV(x, y+1, z,x,y);
		tessellator.addVertexWithUV(-0.5, y+1, 0,x,0);
		tessellator.addVertexWithUV(-0.5, y+0.9, 0,x,0);
		tessellator.addVertexWithUV(x,y+0.9,z,x,y);
		tessellator.draw();
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
