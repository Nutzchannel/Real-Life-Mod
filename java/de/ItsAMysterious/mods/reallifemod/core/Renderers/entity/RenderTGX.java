package de.ItsAMysterious.mods.reallifemod.core.Renderers.entity;

import static org.lwjgl.opengl.GL11.*;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;

import org.lwjgl.BufferUtils;
import org.lwjgl.util.vector.Vector3f;
import org.lwjgl.util.vector.Vector4f;

import de.ItsAMysterious.mods.reallifemod.core.Streets.Entitys.EntityTruck;
/**
 * 
 * @author MO
 *
 */

public class RenderTGX extends Render{
	protected IModelCustom model,backwheels,LowryWheelRight,LowryWheelLeft,Cabin,Steeringwheel,tacho;
	//protected objModel delta;
	ResourceLocation MainTexture=new ResourceLocation("reallifemod:textures/entity/VehicleTextures/FullTexture.png");
	private IModelCustom backlight;
	public static final float DEFAULT_LIGHT_Z = 0.075f;

	//Light RGB and intensity (alpha)
	public static final Vector4f LIGHT_COLOR = new Vector4f(1f, 0.8f, 0.6f, 1f);

	//Ambient RGB and intensity (alpha)
	public static final Vector4f AMBIENT_COLOR = new Vector4f(0.6f, 0.6f, 1f, 0.2f);

	//Attenuation coefficients for light falloff
	public static final Vector3f FALLOFF = new Vector3f(.4f, 3f, 20f);

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
    
	public RenderTGX()  
	{
		try {
			this.Steeringwheel=AdvancedModelLoader.loadModel(new ResourceLocation("reallifemod:models/streets/LKW_Steeringwheel.obj"));
			this.model=AdvancedModelLoader.loadModel(new ResourceLocation("reallifemod:models/streets/LKW.obj"));
			this.backwheels=AdvancedModelLoader.loadModel(new ResourceLocation("reallifemod:models/streets/LKW_Backwheelsection.obj"));
			this.Cabin=AdvancedModelLoader.loadModel(new ResourceLocation("reallifemod:models/streets/LKW_Cabin.obj"));
			this.LowryWheelLeft=AdvancedModelLoader.loadModel(new ResourceLocation("reallifemod:models/streets/LKW_LeftwheelF.obj"));
			this.LowryWheelRight=AdvancedModelLoader.loadModel(new ResourceLocation("reallifemod:models/streets/LKW_RightwheelF.obj"));
			this.tacho=AdvancedModelLoader.loadModel(new ResourceLocation("reallifemod:models/streets/LKW_Tacho.obj"));
			this.backlight=AdvancedModelLoader.loadModel(new ResourceLocation("reallifemod:models/streets/BackLights.obj"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void renderLowry(EntityTruck entity, double x, double y, double z,float rotatedYaw,float rotatedPitch ){
	    	glPushMatrix();
	    	Tessellator tessellator=Tessellator.instance;
			glDisable(GL_CULL_FACE);
		    glEnable(GL_ALPHA_TEST);    
            glEnable(GL_DEPTH_TEST);
            glEnable(GL_LIGHTING);
            glEnable(GL_LIGHT0); 
            glLightModeli(GL_LIGHT_MODEL_TWO_SIDE,GL_TRUE);
            glEnable(GL_COLOR_MATERIAL);
            glColorMaterial(GL_FRONT_AND_BACK,GL_AMBIENT_AND_DIFFUSE);
		    
	        double d8 = entity.lastTickPosX + (entity.posX - entity.lastTickPosX) * x;
	        double d3 = entity.lastTickPosY + (entity.posY - entity.lastTickPosY) * y;
	        double d4 = entity.lastTickPosZ + (entity.posZ - entity.lastTickPosZ) * z;
				glTranslated( x, y+0.3F, z);
				{
					glScalef(1.25F, 1.25F, 1.25F);
					glRotatef(rotatedYaw,0.0F,1.0F,0.0F);
					glRotatef(entity.rotationPitch-entity.prevRotationPitch, 1.0F, 0.0F, 0.0F);
				}
				glPushMatrix();
				/*if(entity.isBraking==true){
					this.renderManager.renderEngine.bindTexture(new ResourceLocation("reallifemod:textures/entity/VehicleTextures/BacklightLit.png"));
				}else
					if(!entity.isBraking==true)
					this.renderManager.renderEngine.bindTexture(new ResourceLocation("reallifemod:textures/entity/VehicleTextures/BacklightOff.png"));
				this.backlight.renderAll();*/
				glPopMatrix();
				//glRotatef(entity.truckRoll, 0.0F, 0.0F, 1.0F);
				    this.renderManager.renderEngine.bindTexture(MainTexture);
					this.model.renderAll();
					this.Cabin.renderAll();
					tessellator.setColorRGBA(255, 255, 0, 255);
				glPushMatrix();
					glTranslatef(0.9F, 1.9F, 6.35F);
					glRotated(entity.speed*100, 0.0D, 0.0D, 1.0D);
					this.tacho.renderAll();
				glPopMatrix();
				
				glPushMatrix();
					glTranslatef(0.8F, 2.0F, 6.0F);
					glRotated(entity.deltaSteer, 0.0F, 1.0F, 0.0F);
					this.Steeringwheel.renderAll();
				glPopMatrix();
				
				this.renderManager.renderEngine.bindTexture(new ResourceLocation("reallifemod:textures/entity/VehicleTextures/Hinterachse.png"));
				glPushMatrix();
					glTranslatef(0.0F,0.3F, 0.0F);
					glRotated(entity.speed, 1.0F, 0.0F, 0.0F);
					this.backwheels.renderAll();
				glPopMatrix();
				
				glPushMatrix();
					glTranslatef(1.0F, 0.4F, 5.0F);
					glPushMatrix();
						glRotated(entity.deltaSteer, 0.0F, 1.0F, 0.0F);
						glRotated(entity.speed, 1.0d, 0.0d, 0.0d);
						this.LowryWheelLeft.renderAll();
					glPopMatrix();
				glPopMatrix();
				
				glPushMatrix();
				glTranslatef(-1.0F, 0.4F,5.0F);
					glPushMatrix();
						glRotated(entity.deltaSteer, 0.0F, 1.0F, 0.0F);
						glRotated(entity.speed, 1.0D, 0.0D, 0.0D);
						this.LowryWheelRight.renderAll();
				glPopMatrix();
			glPopMatrix();
            glLight(GL_LIGHT0, GL_DIFFUSE, floatBuffer(new float[]{1.0f, 1.0f, 1.0f, 1.0f}));
            glLight(GL_LIGHT0, GL_AMBIENT, floatBuffer(new float[]{0.1f, 0.1f, 0.1f, 1.0f}));
            glLight(GL_LIGHT0, GL_SPECULAR, floatBuffer(new float[]{1.0f, 1.0f, 1.0f, 1.0f}));
            glLight(GL_LIGHT0,GL_POSITION,floatBuffer(new float[]{20,0,-20,1}));

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
		renderLowry((EntityTruck)entity, x, y, z, rotationYaw, rotationPitch);
	}	
}