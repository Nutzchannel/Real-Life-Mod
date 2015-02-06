package de.ItsAMysterious.mods.reallifemod.api.Renderer;

import de.ItsAMysterious.mods.reallifemod.core.rendering.TileEntitys.christmaspyramidTE;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;
import static org.lwjgl.opengl.GL11.*;

public class ChristmasPyramidTER extends TileEntitySpecialRenderer{
	private IModelCustom stand, rotating;
	private ResourceLocation textures=new ResourceLocation("reallifemod:models/seasonal/christmaspyramid.png");    
	
	public ChristmasPyramidTER(){
		this.stand=AdvancedModelLoader.loadModel(new ResourceLocation("reallifemod:models/seasonal/christmaspyramid_stand.obj"));
		this.rotating=AdvancedModelLoader.loadModel(new ResourceLocation("reallifemod:models/seasonal/christmaspyramid_rest.obj"));	

	}

	@Override
	public void renderTileEntityAt(TileEntity te, double x, double y, double z, float scale) {
		this.doRender((christmaspyramidTE) te, x, y, z, scale);
	}
	
	public void doRender(christmaspyramidTE te, double x, double y, double z, float scale) {
		glPushMatrix();
	    glDisable(GL_CULL_FACE);
	    glEnable(GL_ALPHA_TEST);
		if(te.isActive()==true){
			te.setRotation(0.5f);
		}
	    glTranslatef((float)x+0.5f, (float)y, (float)z+0.5f);
	    int facing = te.getFacingDirection();
	    int k = 0;
	    //South
	    if (facing == 2) {k = 0;}
	    //North
	    if (facing == 3) {k = 180;}
	    //East
	    if (facing == 4) {k = -90;}
	    //West
	    if (facing == 5) {k = 90;}
	    glRotatef(k, 0.0F, 1.0F, 0.0F);
	    glScalef(1.0f, 1.0f, 1.0f);
    	this.bindTexture(textures);
    	this.stand.renderAll();
		    glPushMatrix();
				glRotatef(te.getRotation(), 0, 1.0f, 0);
				this.rotating.renderAll();
	    	glPopMatrix();
		    glDisable(GL_ALPHA_TEST);
		    glEnable(GL_CULL_FACE);
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
