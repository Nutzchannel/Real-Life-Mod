package de.ItsAMysterious.mods.reallifemod.api.Renderer;

import org.lwjgl.opengl.GL11;

import de.ItsAMysterious.mods.reallifemod.core.Renderers.blocks.models.modelCupboard;
import de.ItsAMysterious.mods.reallifemod.core.rendering.TileEntitys.cupboardTE;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class cupboardTER extends TileEntitySpecialRenderer{

	private final modelCupboard model;
    
    public cupboardTER() {
            this.model = new modelCupboard(); 	
    }
    

@Override
public void renderTileEntityAt(TileEntity te, double x, double y, double z, float scale) {
	GL11.glPushMatrix();
    GL11.glDisable(GL11.GL_CULL_FACE);
    GL11.glEnable(GL11.GL_ALPHA_TEST);
    GL11.glTranslatef((float) x + 0.5F, (float) y + 1.5F, (float) z + 0.5F);
    int facing = ((cupboardTE) te).getFacingDirection();
    int k = 0;
    //South
    if (facing == 2) {k = 180;}
    //North
    if (facing == 3) {k = 0;}
    //East
    if (facing == 4) {k = -90;}
    //West
    if (facing == 5) {k = 90;}
    //Rotates model on the spot, depending on direction, making the front always to player) (k = angle, 1.0F in middle = about y axis)
    GL11.glRotatef(k, 0.0F, 1.0F, 0.0F);
    GL11.glRotatef(180,1.0F, 0.0F, 0.0F);
    this.bindTexture(new ResourceLocation("reallifemod:models/everydaylife/textures/modelCupboard.png"));
    this.model.render((Entity)null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
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

