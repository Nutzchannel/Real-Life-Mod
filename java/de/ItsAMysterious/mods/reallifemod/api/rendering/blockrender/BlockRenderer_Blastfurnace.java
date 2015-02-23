package de.ItsAMysterious.mods.reallifemod.api.rendering.blockrender;

import static org.lwjgl.opengl.GL11.glRotatef;

import org.lwjgl.opengl.GL11;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.world.IBlockAccess;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import de.ItsAMysterious.mods.reallifemod.TLMBlocks;
import de.ItsAMysterious.mods.reallifemod.core.tiles.blastfurnaceTE;

public class BlockRenderer_Blastfurnace implements ISimpleBlockRenderingHandler{

	final int renderID;
	private final blastfurnaceTE tileBlastFurnace=new blastfurnaceTE();
	private float rotation=0;
	
	public BlockRenderer_Blastfurnace(int var1){
		renderID=var1;
	}
	
	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelId,RenderBlocks renderer) {
    	GL11.glScalef(0.4F, 0.4F, 0.4F);
        GL11.glRotatef(180F+rotation, 0.0F, 1.0F, 0.0F);
		glRotatef(90, 0.0F, 1.0F, 0.0F);
        rotation++;
        GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
        renderFurnace(block, metadata, modelId);
        GL11.glEnable(32826);
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
	
	public void renderFurnace(Block block,int par0,float par2){
        if(block == TLMBlocks.blastFurnace)
            TileEntityRendererDispatcher.instance.renderTileEntityAt(tileBlastFurnace, 0.0D, 0.0D, 0.0D, 0.0F);
	}

}
