package de.ItsAMysterious.mods.reallifemod.core.rendering.tiles;

import static org.lwjgl.opengl.GL11.GL_ALPHA_TEST;
import static org.lwjgl.opengl.GL11.GL_CULL_FACE;
import static org.lwjgl.opengl.GL11.glDisable;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushMatrix;
import static org.lwjgl.opengl.GL11.glRotatef;
import static org.lwjgl.opengl.GL11.glScalef;
import static org.lwjgl.opengl.GL11.glTranslatef;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;
import de.ItsAMysterious.mods.reallifemod.core.tiles.heatingTE;

public class heatingTER extends TileEntitySpecialRenderer{
	private IModelCustom model;
	
	public heatingTER(){
		model=AdvancedModelLoader.loadModel(new ResourceLocation("reallifemod:models/everydaylife/heating.obj"));
	}

	@Override
	public void renderTileEntityAt(TileEntity te, double x, double y, double z, float scale) {
		this.doRender((heatingTE) te, x, y, z, scale);
	}
	
	public void doRender(heatingTE te, double x, double y, double z, float scale) {
		glPushMatrix();
		    glDisable(GL_CULL_FACE);
		    glEnable(GL_ALPHA_TEST);
		    glTranslatef((float)x+0.5F, (float)y, (float)z+0.5F);
			glRotatef(te.getBlockMetadata() * 90, 0.0F, 1.0F, 0.0F);
		    glScalef(0.5F, 0.5F, 0.5F);
		    this.bindTexture(new ResourceLocation("reallifemod:textures/tiles/heating.png"));
		    this.model.renderAll();
	    glPopMatrix();
	}

}
