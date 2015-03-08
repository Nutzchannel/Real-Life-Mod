package de.ItsAMysterious.mods.reallifemod.api.rendering.items;

import static org.lwjgl.opengl.GL11.*;
import cpw.mods.fml.client.FMLClientHandler;
import de.ItsAMysterious.mods.reallifemod.core.items.AK47Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;

public class AK47Renderer implements IItemRenderer
{
	protected IModelCustom AK;
	protected float rot=0;
        @Override
		public boolean handleRenderType(ItemStack var1, ItemRenderType var2)
        {
                switch (var2)
                {
                        case EQUIPPED:
                        return true;
                        case EQUIPPED_FIRST_PERSON:
                        return true;
                        default:
                        return false;
                }
        }

        @Override
		public boolean shouldUseRenderHelper(ItemRenderType var1, ItemStack var2, ItemRendererHelper data)
        {
                return false;
        }

        @Override
		public void renderItem(ItemRenderType var1, ItemStack var2, Object ... data)
        {
            glPushMatrix();

        	this.AK=AdvancedModelLoader.loadModel(new ResourceLocation("reallifemod:models/weapons/AK47.obj"));
        	FMLClientHandler.instance().getClient().renderEngine.bindTexture(new ResourceLocation("reallifemod:textures/items/AK47Texture.png"));

                switch (var1)
                {
                        case EQUIPPED :
                    	glRotatef(-90, 0.0F, 1.0F, 0.0F);
                    	glRotatef(30F, 1.0F, 0.0F, 0.0F);
                    	glTranslatef(0.0F, 0.0F, -0.56F);
                        float scale=0.5F;
                        glScalef(scale, scale, scale);
                        this.AK.renderAll();
                break;
                        case EQUIPPED_FIRST_PERSON:
                        	AK47Item item=(AK47Item)var2.getItem();
                        	if(item.aiming==false){
                        		glRotatef(-90F, 0.0F, 1.0F, 0.0F);
                        		glRotatef(0.0F,1.0F,0.0F,0.0F);
                        		glTranslatef(-0.85F,-0.1F,0.6F);
                        		glScalef(1.5F, 1.5F, 1.5F);
                        	}else
                        	{
                            	glRotatef(-95.0F, 0.0F, 1.0F, 0.0F);
                            	glRotatef(20.0F,1.0F,0.0F,0.0F);
                            	glTranslatef(-0.975F,-0.1F, 1.85F);
                            	glScalef(1.5F, 1.5F, 1.5F);
                        	}
                        	this.AK.renderAll();
                        	break;
                        case ENTITY:
                        	this.AK.renderAll();
				default:
					break;

                }
                glPopMatrix();
        }
}