package de.ItsAMysterious.mods.reallifemod.core.rendering.tiles;

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

public class drinksmachineTER extends TileEntitySpecialRenderer{

private IModelCustom model;    
public drinksmachineTER(){
this.model=AdvancedModelLoader.loadModel(new ResourceLocation("reallifemod:models/furniture/DrinksMachine.obj"));	
}

@Override
public void renderTileEntityAt(TileEntity te, double x, double y, double z, float scale) {
	glPushMatrix(); 
	    glDisable(GL11.GL_CULL_FACE);
	    glEnable(GL11.GL_ALPHA_TEST);
	    glTranslatef((float)x, (float)y, (float)z);
		glRotatef(te.getBlockMetadata() * 90, 0.0F, 1.0F, 0.0F);
	    glScalef(0.5F, 0.5F, 0.5F);
	    this.bindTexture(new ResourceLocation("reallifemod:textures/tiles/DrinksMachine.png"));
	   model.renderAll();
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

