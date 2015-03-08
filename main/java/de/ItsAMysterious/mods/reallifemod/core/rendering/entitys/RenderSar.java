package de.ItsAMysterious.mods.reallifemod.core.rendering.entitys;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;



public class RenderSar extends Render{

	public static IModelCustom OBJModel;

	@SideOnly(Side.CLIENT)
	public static ResourceLocation textur;
	public RenderSar(String Texture,String OBJFile)
	{
		textur=new ResourceLocation(Texture);
		ResourceLocation objfile=new ResourceLocation(OBJFile);
		OBJModel = AdvancedModelLoader.loadModel(objfile);
	}

	@Override
	public void doRender(Entity var1, double x, double y, double z,
			float var8, float var9) {
		GL11.glTranslated(x, y, z);
		bindTexture(textur);
		GL11.glRotatef(var1.rotationYaw, 0.0F, 1.0F, 0.0F);
		GL11.glRotated(var1.rotationPitch, 1.0F, 0.0F, 1.0F);
		OBJModel.renderAll();
		GL11.glPopMatrix();		
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity var1) {
		return textur;
	}



}