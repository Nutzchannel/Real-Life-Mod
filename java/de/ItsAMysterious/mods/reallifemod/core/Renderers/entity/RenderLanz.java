package de.ItsAMysterious.mods.reallifemod.core.Renderers.entity;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;

import static org.lwjgl.opengl.GL11.*;

import cpw.mods.fml.client.FMLClientHandler;
import de.ItsAMysterious.mods.reallifemod.core.entitys.NPCs.EntityLanz;



public class RenderLanz extends Render{

	public static IModelCustom modelLanz;
	static ResourceLocation MainTexture=new ResourceLocation("reallifemod:textures/entity/LanzTexture.png");

	public RenderLanz()
	{
		modelLanz=AdvancedModelLoader.loadModel(new ResourceLocation("reallifemod:models/agriculture/entitys/lanz/modelLanz8Bit.obj"));
		
	}
	
	public ResourceLocation getTexture(EntityLanz lanz){
		return MainTexture;
	}
	
	@Override
	public ResourceLocation getEntityTexture(Entity entity){
		return getEntityTexture(entity);
	}

	public void renderLanz(EntityLanz entity, double x, double y, double z )
	{
		glPushMatrix();
		glTranslated(x, y, z);
			glScalef(1.0F, 1.0F, 1.0F);
			glRotatef((entity.rotationYaw), 0.0F, 1.0F, 0.0F);
			glRotated((entity.rotationPitch),1.0F,0.0F,1.0F);
			glPushMatrix();
				FMLClientHandler.instance().getClient().getTextureManager().bindTexture(MainTexture);
				RenderLanz.modelLanz.renderAll();
			glPopMatrix();
		glPopMatrix();
	}
	@Override
	public void doRender(Entity entity, double x,double y, double z, float scaleX,float scaleY) {
		renderLanz((EntityLanz)entity, x,y,z);
	}   


}