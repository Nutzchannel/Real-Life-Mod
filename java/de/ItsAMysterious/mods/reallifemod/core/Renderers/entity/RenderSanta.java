package de.ItsAMysterious.mods.reallifemod.core.Renderers.entity;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

import cpw.mods.fml.client.FMLClientHandler;
import de.ItsAMysterious.mods.reallifemod.core.entitys.NPCs.EntityLanz;
import de.ItsAMysterious.mods.reallifemod.core.entitys.seasonalstuff.entitySanta;



public class RenderSanta extends Render{

	public static ModelBiped model;
	static ResourceLocation MainTexture;
	
	static
	{
		MainTexture=new ResourceLocation("reallifemod:textures/entity/santa-claus.png");
	}

	public RenderSanta()
	{
		model=new ModelBiped();
		
	}
	
	public ResourceLocation getTexture(EntityLanz lanz){
		return MainTexture;
	}
	
	@Override
	public ResourceLocation getEntityTexture(Entity entity){
		return getEntityTexture(entity);
	}

	public void renderLanz(entitySanta entity, double x, double y, double z, float rotationYaw,float rotationPitch )
	{
		GL11.glPushMatrix();
			FMLClientHandler.instance().getClient().renderEngine.bindTexture(MainTexture);
			RenderSanta.model.render(entity, (float)x, (float)y, (float)z, rotationYaw, rotationPitch, 1.0f);
		GL11.glPopMatrix();
	}
	@Override
	public void doRender(Entity entity, double x,double y, double z, float rotationYaw,float rotationPitch) {
		renderLanz((entitySanta)entity, x,y,z,rotationYaw,rotationPitch);
	}   


}