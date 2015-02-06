package de.ItsAMysterious.mods.reallifemod.core.Gui.Computer.System;

import java.awt.Color;
import org.lwjgl.opengl.GL11;

import cpw.mods.fml.client.FMLClientHandler;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.util.ResourceLocation;

public class guiExplorerWindow extends GuiScreen {
	
	private static ResourceLocation WindowBackground=new ResourceLocation("reallifemod:textures/gui/System/defaultBackground.png");
	public int posX,posY,id;
	public boolean visible;
	public String caption;
	
	public guiExplorerWindow(int id,int xPos,int yPos,int theWidth,int theHeight,String caption){
		this.id=id;
		this.posX=xPos;
		this.posY=yPos;
		this.width=theWidth;
		this.height=theHeight;
		this.caption=caption;
		this.visible=true;
	}
	
	public void showExplorerWindow(int posX,int posY)
	{
		if(this.visible){
			FontRenderer renderOver=FMLClientHandler.instance().getClient().fontRenderer;
			FMLClientHandler.instance().getClient().getTextureManager().bindTexture(WindowBackground);
	        GL11.glEnable(GL11.GL_BLEND);
			OpenGlHelper.glBlendFunc(770, 771, 1, 0);
	        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
			this.drawTexturedModalRect(this.posX,this.posY,0,0, this.width,this.height);
	        this.drawCenteredString(renderOver, this.caption, this.posX + this.width / 2, this.posY + 8, Color.WHITE.getRGB());

		}
		
	}
	
	@Override
	public void initGui(){
		super.initGui();
	}
	
	@Override
	public void drawScreen(int x,int y,float f){
		this.buttonList.clear();
		this.buttonList.add(new GuiButton(0,this.width-40,1, 15, 15,"X"));
		super.drawScreen(x, y, f);
	}    
}    

