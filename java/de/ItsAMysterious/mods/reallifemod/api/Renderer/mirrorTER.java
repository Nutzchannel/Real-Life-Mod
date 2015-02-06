package de.ItsAMysterious.mods.reallifemod.api.Renderer;

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
import de.ItsAMysterious.mods.reallifemod.core.rendering.TileEntitys.washbasinTE;

public class mirrorTER extends TileEntitySpecialRenderer{
    
    private IModelCustom model;
    
    public mirrorTER() {
            model =AdvancedModelLoader.loadModel(new ResourceLocation("reallifemod:models/Bathroom/blocks/Mirror.obj"));
    }
    
    @Override
    public void renderTileEntityAt(TileEntity te, double x, double y, double z, float scale) {
    	Tessellator t=Tessellator.instance;
    	glPushMatrix(); 	
	        glTranslatef((float)x, (float)y, (float)z);
	        this.bindTexture(new ResourceLocation("reallifemod:textures/blocks/indoor/Mirror.png"));
	        int facing = ((washbasinTE) te).getFacingDirection();
	        int k = 0;
	        //South
	        if (facing == 2) {k = 0;}
	        //North
	        if (facing == 3) {k = 180;}
	        //East
	        if (facing == 4) {k = -90;}
	        //West
	        if (facing == 5) {k = 90;}
	        //Rotates model on the spot, depending on direction, making the front always to player) (k = angle, 1.0F in middle = about y axis)
	        glScalef(1.0F, 1.0F, 1.0F);
	        glRotatef(k, 0.0F, 1.0F, 0.0F);
	        glDisable(GL_CULL_FACE);
	        glEnable(GL_ALPHA_TEST);
	        model.renderAll();
        glPushMatrix();
        	glEnable(GL_DEPTH_RANGE);
        	glDepthRange(0, -30);
        	glMatrixMode(GL_PROJECTION);
        	glPushMatrix();
        	glEnable(GL_QUADS);
        	glVertex2f(0,0);
        	glVertex2f(10, 0);
        	glVertex2f(10,10);
        	glVertex2f(0,10);
        	glPopMatrix();
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