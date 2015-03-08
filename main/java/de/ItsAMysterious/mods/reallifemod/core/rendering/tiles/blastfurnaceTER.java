package de.ItsAMysterious.mods.reallifemod.core.rendering.tiles;

import static org.lwjgl.opengl.GL11.*;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;

import org.lwjgl.opengl.GL11;

import de.ItsAMysterious.mods.reallifemod.core.tiles.blastfurnaceTE;

public class blastfurnaceTER extends TileEntitySpecialRenderer{
        
        private final IModelCustom model;
		private ResourceLocation ovenTexture=new ResourceLocation("reallifemod:textures/tiles/OvenTexture.png");
        
        public blastfurnaceTER() {
                this.model =AdvancedModelLoader.loadModel(new ResourceLocation("reallifemod:models/industrial/blastfurnace.obj"));
        }
        
        private void adjustRotatePivotViaMeta(World world, int x, int y, int z) {
            int meta = world.getBlockMetadata(x, y, z);
            glPushMatrix();
            glRotatef(meta * (-90), 0.0F, 0.0F, 1.0F);
            glPopMatrix();
    }
        
        public void renderModelAt(blastfurnaceTE tileEntity, double x, double y, double z, 
                float f)
        {
        	glPushMatrix(); 
            glDisable(GL_CULL_FACE);
            glEnable(GL_ALPHA_TEST);
            glTranslated( x, y,  z);
            this.bindTexture(ovenTexture);
            if(tileEntity.getBlockMetadata()!=-1){
			glRotatef(tileEntity.getBlockMetadata() * 90, 0.0F, 1.0F, 0.0F);}else
			glRotatef(0, 0.0F, 1.0F, 0.0F);	
            this.bindTexture(ovenTexture);
            model.renderAll();
            glPopMatrix();
        }

		@Override
		public void renderTileEntityAt(TileEntity tileEntity,double var1, double var2, double var3,float var4) {
            renderModelAt((blastfurnaceTE)tileEntity, var1, var2, var3, var4);;
		}
}


