package de.ItsAMysterious.mods.reallifemod.api.Graphics.Renderers;

import org.lwjgl.opengl.GL11;

import de.ItsAMysterious.mods.reallifemod.api.entity.EntityHalo;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class RenderEntityHalo extends Render {

	public void doRender(EntityHalo entity, double x,double y, double z, float fx,float fy) {
		new Tessellator();
		Tessellator tessellator=Tessellator.instance;
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glDisable(GL11.GL_LIGHTING);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE);
        GL11.glLightf(12, 2323, 41231897F);
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glEnable(GL11.GL_TEXTURE_2D);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity p_110775_1_) {
		return null;
	}

	@Override
	public void doRender(Entity entity, double x,double y, double z, float fx,float fy) 
	{
		this.doRender((EntityHalo)entity, x, y, z, fx, fy);
	}

}
