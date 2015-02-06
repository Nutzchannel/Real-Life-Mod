package de.ItsAMysterious.mods.reallifemod.api.Renderer;

import de.ItsAMysterious.mods.reallifemod.core.rendering.TileEntitys.heatingTE;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;
import static org.lwjgl.opengl.GL11.*;

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
		    glScalef(1.5f, 1.5f, 1.5f);
		    int facing = te.getFacingDirection();
		    int k = 0;
		    //South
		    if (facing == 2) {k = 180;}
		    //North
		    if (facing == 3) {k = 0;}
		    //East
		    if (facing == 4) {k = -90;}
		    //West
		    if (facing == 5) {k = 90;}
		    glRotatef(k, 0.0F, 1.0F, 0.0F);
		    glScalef(0.3F, 0.3F, 0.3F);
		    this.bindTexture(new ResourceLocation("reallifemod:models/everydaylife/textures/heating.png"));
		    this.model.renderAll();
	    glPopMatrix();
	}

}
