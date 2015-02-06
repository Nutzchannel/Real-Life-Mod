package de.ItsAMysterious.mods.reallifemod.core.Renderers.entity;

import org.lwjgl.opengl.GL11;
import de.ItsAMysterious.mods.reallifemod.core.entitys.Particles.EntityAdvancedFlameFX;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;

public class RenderAdvancedFlame extends Render{

	private ResourceLocation particleTextures=new ResourceLocation("reallifemod:textures/Particles/Particles.png");
	private int f;
	@Override
	public void doRender(Entity entity, double p_76986_2_,
			double p_76986_4_, double p_76986_6_, float p_76986_8_,
			float p_76986_9_) {
		this.renderFlame((EntityAdvancedFlameFX) entity, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
	}

	private void renderFlame(EntityAdvancedFlameFX entity,double x,
			double y, double z, float rotationYaw, float rotationPitch) {
		 GL11.glDisable(GL11.GL_LIGHTING);
	        IIcon iicon = Blocks.fire.getFireIcon(0);
	        IIcon iicon1 = Blocks.fire.getFireIcon(1);
	        GL11.glPushMatrix();
	        GL11.glTranslatef((float)x, (float)y, (float)z);
	        float f1 = 1 * 1.4F;
	        GL11.glScalef(f1, f1, f1);
	        Tessellator tessellator = Tessellator.instance;
	        float f2 = 0.5F;
	        float f3 = 0.0F;
	        float f4 = entity.height / f1;
	        float f5 = (float)(entity.posY - entity.boundingBox.minY);
	        GL11.glRotatef(-this.renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
	        GL11.glTranslatef(0.0F, 0.0F, -0.3F + ((int)f4) * 0.02F);
	        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
	        float f6 = 0.0F;
	        int i = 0;
	        tessellator.startDrawingQuads();

	        while (f4 > 0.0F)
	        {
	            IIcon iicon2 = i % 2 == 0 ? iicon : iicon1;
	            this.bindTexture(TextureMap.locationBlocksTexture);
	            float f7 = iicon2.getMinU();
	            float f8 = iicon2.getMinV();
	            float f9 = iicon2.getMaxU();
	            float f10 = iicon2.getMaxV();

	            if (i / 2 % 2 == 0)
	            {
	                float f11 = f9;
	                f9 = f7;
	                f7 = f11;
	            }

	            tessellator.addVertexWithUV(f2 - f3, 0.0F - f5, f6, f9, f10);
	            tessellator.addVertexWithUV(-f2 - f3, 0.0F - f5, f6, f7, f10);
	            tessellator.addVertexWithUV(-f2 - f3, 1.4F - f5, f6, f7, f8);
	            tessellator.addVertexWithUV(f2 - f3, 1.4F - f5, f6, f9, f8);
	            f4 -= 0.45F;
	            f5 -= 0.45F;
	            f2 *= 0.9F;
	            f6 += 0.03F;
	            ++i;
	        }

	        tessellator.draw();
	        GL11.glPopMatrix();
	        GL11.glEnable(GL11.GL_LIGHTING);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity p_110775_1_) {
		return null;
	}

}
