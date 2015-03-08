package de.ItsAMysterious.mods.reallifemod.core.rendering.entitys;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import de.ItsAMysterious.mods.reallifemod.RealLifeMod;
import de.ItsAMysterious.mods.reallifemod.core.entitys.npcs.EntityRobot;

public class RenderRobot extends RenderBiped
{
	protected ResourceLocation NPCTexture;
	protected static ModelBiped model=new ModelBiped();
	FontRenderer renderer;
	
	public RenderRobot(float shadowsize) {
		super(model, shadowsize);
    	 renderer=Minecraft.getMinecraft().fontRenderer;
	}

    @Override
    protected void preRenderCallback(EntityLivingBase entity, float f)
    {
    	super.preRenderCallback(entity, f);
        preRenderCallbackSerpent((EntityRobot) entity, f);
    }
  
    protected void preRenderCallbackSerpent(EntityRobot entity, float f)
    {
        // some people do some G11 transformations or blends here, like you can do
         //glScalef(2F, 2F, 2F);// to scale up the entity
        // which is used for Slime entities.  I suggest having the entity cast to
        // your custom type to make it easier to access fields from your 
        // custom entity, eg. GL11.glScalef(entity.scaleFactor, entity.scaleFactor, 
        // entity.scaleFactor); 
    }
    @Override
    public void doRender(EntityLivingBase p_76986_1_, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_)
    {
        this.doRender((EntityRobot)p_76986_1_, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
    }

    /**
    * Returns the location of an entity's texture. Doesn't seem to be called 
    * unless you call Render.bindEntityTexture.
    */
    @Override
    protected ResourceLocation getEntityTexture(Entity par1Entity)
    {
        return new ResourceLocation(RealLifeMod.ID+":textures/entity/robot.png");
    }
}