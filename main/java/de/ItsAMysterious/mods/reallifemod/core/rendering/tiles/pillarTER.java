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
import net.minecraft.block.Block;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;
import de.ItsAMysterious.mods.reallifemod.core.blocks.outdoor.pillarBlock;
import de.ItsAMysterious.mods.reallifemod.core.tiles.pillarTE;

public class pillarTER extends TileEntitySpecialRenderer{
	private IModelCustom model;
	
	public pillarTER(){
		model=AdvancedModelLoader.loadModel(new ResourceLocation("reallifemod:models/outdoor/pillar.obj"));
	}

	@Override
	public void renderTileEntityAt(TileEntity te, double x, double y, double z, float scale) {
		this.doRender((pillarTE) te, x, y, z, scale);
	}
	
	public void doRender(pillarTE te, double x, double y, double z, float scale) {
		pillarBlock pillar=(pillarBlock) te.blockType;
		float s= te.scale;
		glPushMatrix();
		    glDisable(GL_CULL_FACE);
		    glEnable(GL_ALPHA_TEST);
			glRotatef(te.getBlockMetadata() * 90, 0.0F, 1.0F, 0.0F);
		    glTranslatef((float)x+0.5f, (float)y, (float)z+0.5f);
		    glScalef(s, 1.0f,s);
		    switch(pillar.Type){
		    case MARMOR:
		    	this.bindTexture(new ResourceLocation("reallifemod:textures/blocks/Marmor.png"));
		    	break;
		    case ROCK:
			    this.bindTexture(new ResourceLocation("minecraft:textures/blocks/brick.png"));
			    break;
		    case IRON:
			    this.bindTexture(new ResourceLocation("minecraft:textures/blocks/iron_block.png"));
			    break;
			default:
				break;
		    }
		    this.model.renderAll();
	    glPopMatrix();
	}

	private void adjustLightFixture(World world, int i, int j, int k, Block block) {
	        Tessellator tess = Tessellator.instance;
	        float brightness = block.getMixedBrightnessForBlock(world, i, j, k);
	        int skyLight = world.getLightBrightnessForSkyBlocks(i, j, k, 0);
	        int modulousModifier = skyLight % 65536;
	        int divModifier = skyLight / 65536;
	        tess.setColorOpaque_F(brightness, brightness, brightness);
	        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit,  modulousModifier,  divModifier);
	}
}
