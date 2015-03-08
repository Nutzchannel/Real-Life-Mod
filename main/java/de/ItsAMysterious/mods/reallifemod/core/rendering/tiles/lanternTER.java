package de.ItsAMysterious.mods.reallifemod.core.rendering.tiles;

import static org.lwjgl.opengl.GL11.GL_ALPHA_TEST;
import static org.lwjgl.opengl.GL11.GL_CULL_FACE;
import static org.lwjgl.opengl.GL11.glDisable;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushMatrix;
import static org.lwjgl.opengl.GL11.glRotatef;
import static org.lwjgl.opengl.GL11.glScalef;
import static org.lwjgl.opengl.GL11.glTranslatef;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;
import de.ItsAMysterious.mods.reallifemod.core.tiles.lanteernTE;

public class lanternTER extends TileEntitySpecialRenderer{
        private final IModelCustom lantern;
		
        public lanternTER() {
        	this.lantern=AdvancedModelLoader.loadModel(new ResourceLocation("reallifemod:models/streets/Lantern.obj"));
        }
        
        public void renderModelAt(lanteernTE tileEntity, double x, double y, double z, 
                float f)
        {	
        	glPushMatrix();
			    glDisable(GL_CULL_FACE);
			    glEnable(GL_ALPHA_TEST);
			    glTranslatef((float)x, (float)y, (float)z);
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
			    glScalef(0.3F, 0.3F, 0.3F);
			    glPushMatrix();
					glRotatef(k, 0.0F, 1.0F, 0.0F);
				glPopMatrix();
				if(tileEntity.active==true){
					this.bindTexture(new ResourceLocation("reallifemod:textures/tiles/LanternActive.png"));
				}else{
					this.bindTexture(new ResourceLocation("reallifemod:textures/tiles/LanternInActive.png"));
				}
	        	this.lantern.renderAll();
        	glPopMatrix();
        }

		@Override
		public void renderTileEntityAt(TileEntity tileEntity,double var1, double var2, double var3,float var4) {
            renderModelAt((lanteernTE)tileEntity, var1, var2, var3, var4);;
		}
}


