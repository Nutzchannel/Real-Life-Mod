package de.ItsAMysterious.mods.reallifemod.core.rendering.tiles;

import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushMatrix;
import static org.lwjgl.opengl.GL11.glRotatef;
import static org.lwjgl.opengl.GL11.glTranslatef;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import de.ItsAMysterious.mods.reallifemod.core.renderers.blocks.models.modelComputer;

public class computerTER extends TileEntitySpecialRenderer{

	private final modelComputer model;
    ResourceLocation textures = new ResourceLocation("reallifemod:textures/tiles/computer.png"); 
    
    public computerTER() {
            this.model = new modelComputer();
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
	        glRotatef(180.0F, 1.0F, 0.0F, 0.0F);
			glRotatef(te.getBlockMetadata()*90 , 0.0F, 1.0F, 0.0F);
	        this.bindTexture(textures);
	        this.model.render((Entity)null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
        glPopMatrix();
    }

    private void adjustLightFixture(World world, int i, int j, int k, Block block) {
            Tessellator tess = Tessellator.instance;
            int skyLight = world.getLightBrightnessForSkyBlocks(i, j, k, 0);
            int modulousModifier = skyLight % 65536;
            int divModifier = skyLight / 65536;
            OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit,  modulousModifier,  divModifier);
    }
    

}
