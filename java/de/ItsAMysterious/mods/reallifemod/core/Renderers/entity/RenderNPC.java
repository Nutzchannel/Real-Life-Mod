package de.ItsAMysterious.mods.reallifemod.core.Renderers.entity;

import de.ItsAMysterious.mods.reallifemod.TLM;
import de.ItsAMysterious.mods.reallifemod.core.entitys.NPCs.reallifemodNPC;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

public class RenderNPC extends RenderBiped
{
	protected ResourceLocation NPCTexture;
	protected static ModelBiped model=new ModelBiped();
    
	public RenderNPC(float shadowsize) {
		super(model, shadowsize);
	}

    @Override
    protected void preRenderCallback(EntityLivingBase entity, float f)
    {
        preRenderCallbackSerpent((reallifemodNPC) entity, f);
    }
  
    protected void preRenderCallbackSerpent(reallifemodNPC entity, float f)
    {
        // some people do some G11 transformations or blends here, like you can do
        // GL11.glScalef(2F, 2F, 2F); to scale up the entity
        // which is used for Slime entities.  I suggest having the entity cast to
        // your custom type to make it easier to access fields from your 
        // custom entity, eg. GL11.glScalef(entity.scaleFactor, entity.scaleFactor, 
        // entity.scaleFactor); 
    }

    /**
    * Returns the location of an entity's texture. Doesn't seem to be called 
    * unless you call Render.bindEntityTexture.
    */
    @Override
    protected ResourceLocation getEntityTexture(Entity par1Entity)
    {
        return new ResourceLocation(TLM.ID+":textures/entity/"+((reallifemodNPC)par1Entity).currentJob+".png");
    }
}