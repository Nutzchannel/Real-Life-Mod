package de.ItsAMysterious.mods.reallifemod.core.Renderers.entity;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

//static imports
import static org.lwjgl.opengl.GL11.*;




public class RenderFlagEffect extends Render{
	private float wavetime;
	private float waveWidth;
	private float waveHeight;

	@Override
	public void doRender(Entity entity, double x, double y, double z, float rotationYaw,float rotationPitch) {
		
	}
	
	public void doWaving(Entity entity, double x, double y, double z, float rotationYaw,float rotationPitch) 
	{
		float TS = 1.0F / 40F; //0.025;

		glBegin(GL_QUADS);
		for (int i = -20; i < 20; i++)
			for (int j = -20; j < 20; j++)
			{
				float startX=  TS*(i+20);
				float startY = TS*(j+20);
				glTexCoord2f( startX + 0.0f, startY + 0 );  glVertex2f(i, j);
				glTexCoord2f( startX + TS, startY + 0 );  glVertex2f(i + 1, j);
				glTexCoord2f( startX + TS, startY + TS );  glVertex2f(i + 1, j + 1);
				glTexCoord2f( startX + 0.0f, startY + TS );  glVertex2f(i, j + 1);
			}
		glEnd();
	}
	

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return null;
	}

}
