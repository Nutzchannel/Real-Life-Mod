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
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;
import de.ItsAMysterious.mods.reallifemod.core.tiles.atmTE;

public class atmTER extends TileEntitySpecialRenderer{
	private IModelCustom model;
	
	public atmTER(){
		model=AdvancedModelLoader.loadModel(new ResourceLocation("reallifemod:models/everydaylife/atm.obj"));
	}
	
    private void adjustRotatePivotViaMeta(World world, int x, int y, int z) {
	    int meta = world.getBlockMetadata(x, y, z);
	    glPushMatrix();
	    glRotatef(meta * (-90), 0.0F, 0.0F, 1.0F);
	    glPopMatrix();
    }


	@Override
	public void renderTileEntityAt(TileEntity te, double x, double y, double z, float scale) {
		this.doRender((atmTE) te, x, y, z, scale);
	}
	
	public void doRender(atmTE te, double x, double y, double z, float scale) {
		Tessellator tesselator=Tessellator.instance;
		glPushMatrix();
		if(te.getWorldObj()!=null)
			//Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation((te.getWorldObj().getBlock((int)x,(int) y+1,(int)z)).getItemIconName()));
		    glDisable(GL_CULL_FACE);
		    glEnable(GL_ALPHA_TEST);
		    glTranslatef((float)x+0.5F, (float)y, (float)z+0.5F);
			tesselator.startDrawingQuads();
			tesselator.addVertex(-0.5, y-0.5, z);
			tesselator.addVertex(0.5, y-0.5, z);
			tesselator.addVertex(0.5, y+0.5, z);
			tesselator.addVertex(-0.5, y-0.5, z);
			tesselator.draw();
		    glScalef(1.5f, 1.5f, 1.5f);
		    glScalef(0.3F, 0.3F, 0.3F);
			glRotatef(te.getBlockMetadata() * 90, 0.0F, 1.0F, 0.0F);
		    this.bindTexture(new ResourceLocation("reallifemod:textures/tiles/atm.png"));
		    this.model.renderAll();
	    glPopMatrix();
	}

}
