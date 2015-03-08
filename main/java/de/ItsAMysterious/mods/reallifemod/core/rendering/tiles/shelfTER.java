package de.ItsAMysterious.mods.reallifemod.core.rendering.tiles;

import static org.lwjgl.opengl.GL11.GL_ALPHA_TEST;
import static org.lwjgl.opengl.GL11.GL_CULL_FACE;
import static org.lwjgl.opengl.GL11.glDisable;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushMatrix;
import static org.lwjgl.opengl.GL11.glRotatef;
import static org.lwjgl.opengl.GL11.glTranslated;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;

import org.lwjgl.opengl.GL11;

import de.ItsAMysterious.mods.reallifemod.core.blocks.outdoor.bankBlock;
import de.ItsAMysterious.mods.reallifemod.core.tiles.shelfTE;

public class shelfTER extends TileEntitySpecialRenderer{
	private IModelCustom model;
	private bankBlock theblock;
	
	public shelfTER(){
		model=AdvancedModelLoader.loadModel(new ResourceLocation("reallifemod:models/everydaylife/shelf.obj"));
	}

	@Override
	public void renderTileEntityAt(TileEntity te, double x, double y, double z, float scale) {
		this.doRender((shelfTE) te, x, y, z, scale);
	}
	
	public void doRender(shelfTE te, double x, double y, double z, float scale) {
		glPushMatrix();
		    glDisable(GL_CULL_FACE);
		    glEnable(GL_ALPHA_TEST);
		    glTranslated((float)x+0.5f, (float)y, (float)z+0.5f);
			glRotatef(te.getBlockMetadata() * 90, 0.0F, 1.0F, 0.0F);
		    this.bindTexture(new ResourceLocation("reallifemod:textures/tiles/shelf.png"));
		    this.model.renderAll();
		    this.renderContents(te);
		    glEnable(GL_CULL_FACE);
		    glDisable(GL_ALPHA_TEST);
	    glPopMatrix();
	}

	private void renderContents(shelfTE te) {
		for(int i=0;i<te.items.size();i++){
			EntityItem entity=new EntityItem(null);
			entity.setEntityItemStack(te.items.get(i));
			entity.setPosition(te.xCoord, te.yCoord, te.zCoord);
			glPushMatrix();
			this.renderEntity(entity);
			glPopMatrix();
		}
	}
	
    private void renderEntity(Entity entity) {
    	GL11.glPushMatrix();
        GL11.glTranslatef(0, 0, 0);
        GL11.glRotatef(180, 0.0F, 1.0F, 0.0F);
        RenderHelper.enableStandardItemLighting();
    	GL11.glPushMatrix();
	        GL11.glRotatef(180, 0.0F,0.0F, 1.0F);
	        GL11.glPushMatrix();
	        	RenderManager.instance.renderEntityWithPosYaw(entity, 0.0D, 0.0D, 0.0D, 0.0F, 1.0F);
	        GL11.glPopMatrix();
        GL11.glPopMatrix();
        RenderHelper.disableStandardItemLighting();
        GL11.glPopMatrix();
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
