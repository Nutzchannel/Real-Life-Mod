package de.ItsAMysterious.mods.reallifemod.api.Renderer;

import static org.lwjgl.opengl.GL11.*;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import de.ItsAMysterious.mods.reallifemod.core.rendering.TileEntitys.christmaspyramidTE;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.world.IBlockAccess;

	public class AdvancedInventoryBlock implements ISimpleBlockRenderingHandler{

		final int renderID;
		private christmaspyramidTE tile;
		private Block theBlock;
		
		public AdvancedInventoryBlock(int renderID,Block block){
			this.renderID=renderID;
			this.theBlock=block;
			tile=new christmaspyramidTE();
		}
		
		@Override
		public void renderInventoryBlock(Block block, int metadata, int modelId,RenderBlocks renderer) {
	    	glScalef(0.4F, 0.4F, 0.4F);
	        glRotatef(180F, 0.0F, 1.0F, 0.0F);
	        glTranslatef(-0.5F, -0.5F, -0.5F);
	        doRender(block, metadata, modelId);
	        glEnable(32826);
		}

		@Override
		public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z,Block block, int modelId, RenderBlocks renderer) {
			return false;
		}

		@Override
		public boolean shouldRender3DInInventory(int modelId) {
			return true;
		}

		@Override
		public int getRenderId() {
			return renderID;
		}
		
		public void doRender(Block block,int par0,float par2){
	        if(block == this.theBlock)
	            TileEntityRendererDispatcher.instance.renderTileEntityAt(tile, 0.0D, 0.0D, 0.0D, 0.0F);
		}

	}
