package de.ItsAMysterious.mods.reallifemod.api.Renderer;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;

import org.lwjgl.opengl.GL11;

import de.ItsAMysterious.mods.reallifemod.core.rendering.TileEntitys.blastfurnaceTE;

public class blastfurnaceTER extends TileEntitySpecialRenderer{
        
        private final IModelCustom model;
		private ResourceLocation ovenTexture=new ResourceLocation("reallifemod:textures/blocks/OvenTexture.png");
        
        public blastfurnaceTER() {
                this.model =AdvancedModelLoader.loadModel(new ResourceLocation("reallifemod:models/industrial/blastfurnace.obj"));
        }
        
        private void adjustRotatePivotViaMeta(World world, int x, int y, int z) {
            int meta = world.getBlockMetadata(x, y, z);
            GL11.glPushMatrix();
            GL11.glRotatef(meta * (-90), 0.0F, 0.0F, 1.0F);
            GL11.glPopMatrix();
    }
        
        public void renderModelAt(blastfurnaceTE tileEntity, double x, double y, double z, 
                float f)
        {
        	GL11.glPushMatrix(); 
            GL11.glDisable(GL11.GL_CULL_FACE);
            GL11.glEnable(GL11.GL_ALPHA_TEST);
            GL11.glTranslated( x, y,  z);
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
            //Rotates model on the spot, depending on direction, making the front always to player) (k = angle, 1.0F in middle = about y axis)
            GL11.glRotatef(k, 0.0F, 1.0F, 0.0F);
            this.bindTexture(ovenTexture);
            model.renderAll();
            GL11.glPopMatrix();
        }

		@Override
		public void renderTileEntityAt(TileEntity tileEntity,double var1, double var2, double var3,float var4) {
            renderModelAt((blastfurnaceTE)tileEntity, var1, var2, var3, var4);;
		}
}


