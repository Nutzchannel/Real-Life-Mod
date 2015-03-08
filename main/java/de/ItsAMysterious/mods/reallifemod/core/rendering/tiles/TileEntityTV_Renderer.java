package de.ItsAMysterious.mods.reallifemod.core.rendering.tiles;

import static org.lwjgl.opengl.GL11.GL_ALPHA_TEST;
import static org.lwjgl.opengl.GL11.GL_CULL_FACE;
import static org.lwjgl.opengl.GL11.glDisable;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushMatrix;
import static org.lwjgl.opengl.GL11.glRotatef;
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
import de.ItsAMysterious.mods.reallifemod.core.tiles.TileEntityTV;

public class TileEntityTV_Renderer extends TileEntitySpecialRenderer{
        
        private final IModelCustom model;
		private ResourceLocation texture=new ResourceLocation("reallifemod:textures/tiles/modelTV.png");
        
        public TileEntityTV_Renderer() {
                this.model =AdvancedModelLoader.loadModel(new ResourceLocation("reallifemod:models/furniture/TV.obj"));
        }
        
        public void renderModelAt(TileEntityTV tileEntity, double x, double y, double z, 
                float f)
        {
    		glPushMatrix();
		        glDisable(GL_CULL_FACE);
		        glEnable(GL_ALPHA_TEST);
				glTranslatef((float) x+0.5f, (float)y, (float) z+0.5f);
				glRotatef(tileEntity.getBlockMetadata() * 90, 0.0F, 1.0F, 0.0F);
		        this.bindTexture(texture);
		        this.model.renderAll();
	        glPopMatrix();
        }

		@Override
		public void renderTileEntityAt(TileEntity tileEntity,double var1, double var2, double var3,float var4) {
            renderModelAt((TileEntityTV)tileEntity, var1, var2, var3, var4);
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


