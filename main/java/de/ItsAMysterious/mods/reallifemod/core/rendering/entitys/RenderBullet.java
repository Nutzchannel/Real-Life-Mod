package de.ItsAMysterious.mods.reallifemod.core.rendering.entitys;

import static org.lwjgl.opengl.GL11.GL_ALPHA_TEST;
import static org.lwjgl.opengl.GL11.GL_CULL_FACE;
import static org.lwjgl.opengl.GL11.glDisable;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushMatrix;
import static org.lwjgl.opengl.GL11.glRotatef;
import static org.lwjgl.opengl.GL11.glScalef;
import static org.lwjgl.opengl.GL11.glTranslatef;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;

import org.lwjgl.BufferUtils;

import de.ItsAMysterious.mods.reallifemod.core.entitys.bullets.EntityBullet;

/**
 * 
 * @author MO
 *
 */

public class RenderBullet extends Render{
	protected IModelCustom model;
	//protected objModel delta;
	ResourceLocation MainTexture=new ResourceLocation("reallifemod:textures/general/iron-messing.png");

    public static FloatBuffer allocFloats(int howmany)
    {
        return ByteBuffer.allocateDirect(howmany * 4).order(ByteOrder.nativeOrder()).asFloatBuffer();
    }

    public static FloatBuffer allocFloats(float floatarray[])
    {
        FloatBuffer fb = ByteBuffer.allocateDirect(floatarray.length * 4).order(ByteOrder.nativeOrder()).asFloatBuffer();
        fb.put(floatarray).flip();
        return fb;
    }
    
	public RenderBullet() 
	{
		this.model=AdvancedModelLoader.loadModel(new ResourceLocation("reallifemod:models/weapons/Bullet.obj"));
	}
	
	protected void renderLowry(EntityBullet entity, double x, double y, double z,float rotatedYaw,float rotatedPitch ){
	    	glPushMatrix();
			//Rendering the whole thing(everything)
	    	//Farbe oder Textur aendern wenn kaputt
			glDisable(GL_CULL_FACE);
		    glEnable(GL_ALPHA_TEST);
				glTranslatef((float) x, (float)y, (float)z);
				glRotatef(rotatedYaw-90.0F,0.0F,1.0F,0.0F);
				glRotatef(rotatedPitch, 1.0F, 0.0F, 0.0F);
				glScalef(0.1F,0.1F,0.1F);
				this.renderManager.renderEngine.bindTexture(MainTexture);
				this.model.renderAll();
		glPopMatrix();
	}
	@Override
	protected ResourceLocation getEntityTexture(Entity p_110775_1_) {
		return null;
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

	@Override
	public void doRender(Entity entity, double x,double y, double z, float rotationYaw,float rotationPitch) {
		renderLowry((EntityBullet) entity, x, y, z, rotationYaw, rotationPitch);
	}
}