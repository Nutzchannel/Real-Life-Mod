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
import net.minecraft.block.Block;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;

public class pissoirTER extends TileEntitySpecialRenderer{
    
    private IModelCustom model;
    
    public pissoirTER() {
            model =AdvancedModelLoader.loadModel(new ResourceLocation("reallifemod:models/Bathroom/blocks/Pissoir.obj"));
    }
    
    @Override
    public void renderTileEntityAt(TileEntity te, double x, double y, double z, float scale) {
    	glPushMatrix();
        glDisable(GL_CULL_FACE);
        glEnable(GL_ALPHA_TEST);
        glTranslatef((float)x+0.5f, (float)y, (float)z+0.5f);
		glRotatef(te.getBlockMetadata() * 90, 0.0F, 1.0F, 0.0F);
        glScalef(1.0F, 1.0F, 1.0F);
        this.bindTexture(new ResourceLocation("reallifemod:textures/tiles/pissoir.png"));
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