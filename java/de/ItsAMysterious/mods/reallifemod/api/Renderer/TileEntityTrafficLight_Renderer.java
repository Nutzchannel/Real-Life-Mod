package de.ItsAMysterious.mods.reallifemod.api.Renderer;

import java.nio.FloatBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;
import static org.lwjgl.opengl.GL11.*;
import de.ItsAMysterious.mods.reallifemod.core.rendering.TileEntitys.trafficlightTE;

public class TileEntityTrafficLight_Renderer extends TileEntitySpecialRenderer{
        
        private final IModelCustom model;
		ResourceLocation green=new ResourceLocation("reallifemod:textures/blocks/streets/TrafficLightGreen.png");
		ResourceLocation orange=new ResourceLocation("reallifemod:textures/blocks/streets/TrafficLightOrange.png");
		ResourceLocation red=new ResourceLocation("reallifemod:textures/blocks/streets/TrafficLightRed.png");
		ResourceLocation inactive=new ResourceLocation("reallifemod:textures/blocks/streets/TrafficLightInactive.png");
		private FloatBuffer color;

        public TileEntityTrafficLight_Renderer() {
        	this.model =AdvancedModelLoader.loadModel(new ResourceLocation("reallifemod:models/streets/TrafficLight.obj"));
        }
        
        public void renderModelAt(trafficlightTE tileEntity, double x, double y, double z, 
                float f)
        {
        	Tessellator tessellator=Tessellator.instance;
		    glDisable(GL_TEXTURE_2D);
		    glDisable(GL_LIGHTING);
		    glEnable(GL_BLEND);
		    glBlendFunc(GL_SRC_ALPHA, GL_ONE);
		    glEnable(GL12.GL_RESCALE_NORMAL);
        	glPushMatrix();
			    glDisable(GL_CULL_FACE);
			    glEnable(GL_ALPHA_TEST);
			    glTranslatef((float)x+0.5F, (float)y, (float)z+0.5F);
	    		int facing = tileEntity.getFacingDirection();
	    	    int k = 0;
	    	    //South
	    	    if (facing == 2) {k = 180;}
	    	    //North
	    	    if (facing == 3) {k = 0;}
	    	    //East
	    	    if (facing == 4) {k = -90;}
	    	    //West
	    	    if (facing == 5) {k = 90;}
				glRotatef(k, 0.0F, 1.0F, 0.0F);
			    glScalef(0.3F, 0.3F, 0.3F);
				switch (tileEntity.state){
	        	case GREEN:
		            this.bindTexture(green);
		            glPushMatrix();
		            	glTranslated(x-0.0f,y+1.3F,z-10.0F);
	            		//glLight(GL_LIGHT0, GL_DIFFUSE, asFloatBuffer(new float[]{0.0f, 1.0f,0f,0.5f}));
	                	tessellator.setColorRGBA(0,255,0,120);
	            	glPopMatrix();
		            break;
	        	case ORANGE:
	        		this.bindTexture(orange);
	        		glPushMatrix();
	            		glTranslated(x+0.5F,y+1.5F,z-0.5F);
	            		//glLight(GL_LIGHT0, GL_DIFFUSE, asFloatBuffer(new float[]{0.9f,1f,0.0f,0.5f}));
	            	glPopMatrix();
	        		break;
	        	case RED:
	        		this.bindTexture(red);
	        		glPushMatrix();
	        			glTranslated(x+0.5F,y+1.7F,z+0.5F);
	            		//glLight(GL_LIGHT0, GL_DIFFUSE, asFloatBuffer(new float[]{1.0f,0.0f,0f,0.5f}));
	            	glPopMatrix();
	        		break;
	        	case INACTIVE:
	        		this.bindTexture(inactive);
	            	//glLight(GL_LIGHT0, GL_DIFFUSE, asFloatBuffer(new float[]{0.0f,0.0f,0.0f,-0.5F}));
				default:
					break;
				}
				 GL11.glDisable(GL11.GL_BLEND);
				 GL11.glEnable(GL11.GL_LIGHTING);
				 GL11.glEnable(GL11.GL_TEXTURE_2D);
	        	this.model.renderAll();
        	glPopMatrix();

        }

		@Override
		public void renderTileEntityAt(TileEntity tileEntity,double var1, double var2, double var3,float var4) {
            renderModelAt((trafficlightTE)tileEntity, var1, var2, var3, var4);;
		}
		
		private static FloatBuffer asFloatBuffer(float[] values){
			FloatBuffer buffer= BufferUtils.createFloatBuffer(values.length);
			buffer.put(values);
			buffer.flip();
			return buffer;
		}

}


