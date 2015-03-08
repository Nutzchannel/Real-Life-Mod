package de.ItsAMysterious.mods.reallifemod.core.rendering.tiles;

import de.ItsAMysterious.mods.reallifemod.config.RealLifeModConfig;
import de.ItsAMysterious.mods.reallifemod.core.tiles.christmaspyramidTE;
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
	private ResourceLocation textures=new ResourceLocation("reallifemod:textures/tiles/christmaspyramid.png");    
	
	public ChristmasPyramidTER(){
		//if(RealLifeModConfig.minecraftstyle==false){
		this.stand=AdvancedModelLoader.loadModel(new ResourceLocation("reallifemod:models/seasonal/christmaspyramid_stand.obj"));
		this.rotating=AdvancedModelLoader.loadModel(new ResourceLocation("reallifemod:models/seasonal/christmaspyramid_rest.obj"));
		//}else{
			//this.stand=AdvancedModelLoader.loadModel(new ResourceLocation("reallifemod:models/techne/ChristmasPyramid.tcn"));
		//}

	}

	@Override
	public void renderTileEntityAt(TileEntity te, double x, double y, double z, float scale) {
		this.doRender((christmaspyramidTE) te, x, y, z, scale);
	}
	
	public void doRender(christmaspyramidTE te, double x, double y, double z, float scale) {
		glPushMatrix();
	    glDisable(GL_CULL_FACE);
	    glEnable(GL_ALPHA_TEST);
	    glTranslatef((float)x+0.5f, (float)y, (float)z+0.5f);
		if(te.isActive()==true){
			te.setRotation(0.5f);
		}

			glRotatef(te.getRotation(), 0, 1.0f, 0);
				this.rotating.renderAll();
    	this.bindTexture(textures);
    	this.stand.renderAll();
		    glPushMatrix();

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
