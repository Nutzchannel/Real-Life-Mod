package de.ItsAMysterious.mods.reallifemod.core.rendering.entitys;

import static org.lwjgl.opengl.GL11.GL_ALPHA_TEST;
import static org.lwjgl.opengl.GL11.GL_CULL_FACE;
import static org.lwjgl.opengl.GL11.glDisable;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushMatrix;
import static org.lwjgl.opengl.GL11.glRotatef;
import static org.lwjgl.opengl.GL11.glTranslatef;

import java.nio.FloatBuffer;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;

import org.lwjgl.BufferUtils;

import de.ItsAMysterious.mods.reallifemod.core.streets.entitys.EntityTrailer;

/**
 * 
 * @author MO
 *
 */

public class RenderTrailer extends Render{
	protected IModelCustom model,wheels;
	static ResourceLocation MainTexture;
	
	static
	{
		MainTexture=new ResourceLocation("reallifemod:models/streets/trailer.png");
	}

	public RenderTrailer()
	{
		this.model=AdvancedModelLoader.loadModel(new ResourceLocation("reallifemod:models/streets/Trailer.obj"));
		this.wheels=AdvancedModelLoader.loadModel(new ResourceLocation("reallifemod:models/streets/TrailerWheels.obj"));

	}
	
	public void renderLowry(EntityTrailer entity, double x, double y, double z,float rotatedYaw,float rotatedPitch )
	{	
    	glPushMatrix();
		glDisable(GL_CULL_FACE);
	    glEnable(GL_ALPHA_TEST);
		glTranslatef((float) x, (float)y, (float)z);
		glRotatef(entity.rotationYaw,0.0F,1.0F,0.0F);
		glRotatef(entity.rotationPitch, 1.0F, 0.0F, 0.0F);
		this.bindTexture(MainTexture);;
			wheels.renderAll();
		model.renderAll();
		glPopMatrix();
	}
	
	@Override
	public void doRender(Entity entity, double x,double y, double z, float rotatedYaw,float rotatedPitch) {
		renderLowry((EntityTrailer) entity, x,y,z,rotatedYaw,rotatedPitch);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity p_110775_1_) {
		return MainTexture;
	}
	
    @Override
	public boolean isStaticEntity()
    {
        return false;
    }
	
	private static FloatBuffer asFloatBuffer(float[] values){
		FloatBuffer buffer= BufferUtils.createFloatBuffer(values.length);
		buffer.put(values);
		buffer.flip();
		return buffer;
	}
	
	
}