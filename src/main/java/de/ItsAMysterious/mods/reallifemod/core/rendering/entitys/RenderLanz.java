package de.ItsAMysterious.mods.reallifemod.core.rendering.entitys;

import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushMatrix;
import static org.lwjgl.opengl.GL11.glRotated;
import static org.lwjgl.opengl.GL11.glRotatef;
import static org.lwjgl.opengl.GL11.glScalef;
import static org.lwjgl.opengl.GL11.glTranslated;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;
import cpw.mods.fml.client.FMLClientHandler;
import de.ItsAMysterious.mods.reallifemod.core.entitys.npcs.EntityLanz;



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