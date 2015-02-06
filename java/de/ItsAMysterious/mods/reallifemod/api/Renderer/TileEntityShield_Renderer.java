package de.ItsAMysterious.mods.reallifemod.api.Renderer;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import org.lwjgl.opengl.GL11;

import de.ItsAMysterious.mods.reallifemod.core.Renderers.blocks.models.modelShield;
import de.ItsAMysterious.mods.reallifemod.core.rendering.TileEntitys.bilboardTE;

public class TileEntityShield_Renderer extends TileEntitySpecialRenderer {
        
        //The model of your block
        private final modelShield model;
        
        public TileEntityShield_Renderer() {
                this.model = new modelShield();
        }
        
        private void adjustRotatePivotViaMeta(World world, int x, int y, int z) {
                int meta = world.getBlockMetadata(x, y, z);
                GL11.glPushMatrix();
                GL11.glRotatef(meta * (-90), 0.0F, 0.0F, 1.0F);
                GL11.glPopMatrix();
        }
        
        public void renderTileEntityAt(bilboardTE te, double x, double y, double z, float scale) {
                GL11.glPushMatrix();
                	GL11.glTranslatef((float) x + 0.5F, (float) y + 1.5F, (float) z + 0.5F);
                	if(te.image.toString()!="")
                		Minecraft.getMinecraft().renderEngine.bindTexture(te.image);
                	else
                	Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation("reallifemod:textures/general/Iron.png"));
                	GL11.glRotatef(180F, 0.0F, 0.0F, 1.0F);
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

		@Override
		public void renderTileEntityAt(TileEntity p_147500_1_,
				double x, double y, double z,
				float s) {
			this.renderTileEntityAt((bilboardTE)p_147500_1_, x, y, z, s);
			
		}
}

