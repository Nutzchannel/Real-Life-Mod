package de.ItsAMysterious.mods.reallifemod.core.rendering.tiles;

import static org.lwjgl.opengl.GL11.glRotatef;
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

public class neonlampTER extends TileEntitySpecialRenderer{

private IModelCustom model;    
public neonlampTER(){
this.model=AdvancedModelLoader.loadModel(new ResourceLocation("reallifemod:models/furniture/Interiour/Lamp.obj"));	
}

@Override
public void renderTileEntityAt(TileEntity te, double x, double y, double z, float scale) {
	GL11.glPushMatrix(); 	
    GL11.glDisable(GL11.GL_CULL_FACE);
    GL11.glEnable(GL11.GL_ALPHA_TEST);
    GL11.glTranslated(x+0.5, y, z+0.5);
	glRotatef(te.getBlockMetadata() * 90, 0.0F, 1.0F, 0.0F);
    this.bindTexture(new ResourceLocation("reallifemod:textures/tiles/Lamp.png"));
    model.renderAll();
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

