package de.ItsAMysterious.mods.reallifemod.api.Renderer;

import static org.lwjgl.opengl.GL11.*;
import de.ItsAMysterious.mods.reallifemod.core.Renderers.blocks.models.modelChair;
import de.ItsAMysterious.mods.reallifemod.core.rendering.TileEntitys.chairTE;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class chairTER extends TileEntitySpecialRenderer{

	private final modelChair model;
    
    public chairTER() {
            this.model = new modelChair(); 	
    }
    
    private void adjustRotatePivotViaMeta(World world, int x, int y, int z) {
        int meta = world.getBlockMetadata(x, y, z);
        glPushMatrix();
        glRotatef(meta * (-90), 0.0F, 0.0F, 1.0F);
        glPopMatrix();
}

@Override
public void renderTileEntityAt(TileEntity te, double x, double y, double z, float scale) {
	glPushMatrix(); 	
	    glTranslatef((float) x + 0.5F, (float) y+1.5F, (float) z + 0.5F);
	    int facing = ((chairTE) te).getFacingDirection();
	    int k = 0;
	    //1:South 2:North 3:East 4:West
	    if (facing == 2) {k = 0;}
	    if (facing == 3) {k = 180;}
	    if (facing == 4) {k = 90;}
	    if (facing == 5) {k = -90;}
	    //Rotates model on the spot, depending on direction, making the front always to player) (k = angle, 1.0F in middle = about y axis)
	    glRotatef(k, 0.0F, 1.0F, 0.0F);
	    glRotatef(180, 0.0F, 0.0F, 1.0F);
	    this.bindTexture(new ResourceLocation("reallifemod:models/furniture/textures/modelChair.png"));
	    this.model.render((Entity)null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
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

