package de.ItsAMysterious.mods.reallifemod.core.rendering.entitys;

import static org.lwjgl.opengl.GL11.GL_ALPHA_TEST;
import static org.lwjgl.opengl.GL11.GL_CULL_FACE;
import static org.lwjgl.opengl.GL11.glDisable;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushMatrix;
import static org.lwjgl.opengl.GL11.glRotated;
import static org.lwjgl.opengl.GL11.glRotatef;
import static org.lwjgl.opengl.GL11.glScalef;
import static org.lwjgl.opengl.GL11.glShadeModel;
import static org.lwjgl.opengl.GL11.glTranslated;
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

import de.ItsAMysterious.mods.reallifemod.core.streets.entitys.EntityCherokee;

/**
 * 
 * @author MO
 *
 */

public class renderjeep extends Render{
	protected IModelCustom model,interior,wheels_back,wheel_right,wheel_left,lights, steeringwheel;
	ResourceLocation MainTexture=new ResourceLocation("reallifemod:textures/entity/VehicleTextures/jeeptexture.png");

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
    
	public renderjeep()  
	{
		try {
			this.model=AdvancedModelLoader.loadModel(new ResourceLocation("reallifemod:models/streets/jeep.obj"));
			this.interior=AdvancedModelLoader.loadModel(new ResourceLocation("reallifemod:models/streets/jeep_Interior.obj"));
			this.wheels_back=AdvancedModelLoader.loadModel(new ResourceLocation("reallifemod:models/streets/jeep_Wheelsback.obj"));
			this.lights=AdvancedModelLoader.loadModel(new ResourceLocation("reallifemod:models/streets/jeep_lights.obj"));
			this.wheel_left=AdvancedModelLoader.loadModel(new ResourceLocation("reallifemod:models/streets/jeep_wheelleft.obj"));
			this.wheel_right=AdvancedModelLoader.loadModel(new ResourceLocation("reallifemod:models/streets/jeep_wheelright.obj"));
			this.steeringwheel=AdvancedModelLoader.loadModel(new ResourceLocation("reallifemod:models/streets/jeep_steeringwheel.obj"));


		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}	
	
	public void renderLowry(EntityCherokee entity, double x, double y, double z,float rotatedYaw,float rotatedPitch ){
	    	glPushMatrix();
	    	glShadeModel(7425);
			glDisable(GL_CULL_FACE);
		    glEnable(GL_ALPHA_TEST);
	        double d8 = entity.lastTickPosX + (entity.posX - entity.lastTickPosX) * x;
	        double d3 = entity.lastTickPosY + (entity.posY - entity.lastTickPosY) * y;
	        double d4 = entity.lastTickPosZ + (entity.posZ - entity.lastTickPosZ) * z;
	        glTranslated(x,y+0.574,z);	
			glScalef(1.2F, 1.2F, 1.2F);
				glPushMatrix();
					glRotatef(rotatedYaw+entity.rotationYaw-entity.prevRotationYaw,0.0F,1.0F,0.0F);
					glRotatef(entity.rotationPitch-entity.prevRotationPitch, 1.0F, 0.0F, 0.0F);
					glPushMatrix();
						renderManager.renderEngine.bindTexture(new ResourceLocation("reallifemod:textures/entity/VehicleTextures/jeepLights.png"));
						lights.renderAll();
					glPopMatrix();
					
				    renderManager.renderEngine.bindTexture(MainTexture);
					model.renderAll();
					interior.renderAll();
					glPushMatrix();
					glRotated(entity.wheelRotation*20, 1.0F, 0.0F, 0.0F);
						wheels_back.renderAll();
					glPopMatrix();
					glPushMatrix();
						glTranslatef(0.8F,0,3.625F);
						glRotated(entity.DeltaSteer, 0.0F,1.0F,0.0F);
						wheel_left.renderAll();
					glPopMatrix();
					
					glPushMatrix();
						glTranslatef(-0.8F,0,3.625F);
						glRotated(entity.DeltaSteer, 0.0F,1.0F,0.0F);
						wheel_right.renderAll();
					glPopMatrix();
					
					glPushMatrix();
						glTranslatef(0.5F, 0.875F, 2.38F);
						glRotatef(15, 1.0F, 0, 0);
						glRotated(-entity.DeltaSteer, 0, 0, 1);
						steeringwheel.renderAll();
					glPopMatrix();
				glPopMatrix();
		glPopMatrix();
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
	
	   public FloatBuffer floatBuffer(float[]data)
       {
       FloatBuffer fb = BufferUtils.createFloatBuffer(data.length);
       fb.put(data);
       fb.flip();
       return fb;
        }

	@Override
	public void doRender(Entity entity, double x,double y, double z, float rotationYaw,float rotationPitch) {
		renderLowry((EntityCherokee)entity, x, y, z, rotationYaw, rotationPitch);
	}	
}