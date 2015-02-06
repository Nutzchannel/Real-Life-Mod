package de.ItsAMysterious.mods.reallifemod.core.Gui;

import java.awt.Color;

import net.minecraft.block.Block;
import net.minecraft.client.gui.Gui;

public class GuiCardTile extends Gui{
	public int posX, posY, sizeX, sizeY, mouseX, mouseY;
	private boolean mouseOver, selected;
	private Block theBlock;
	GuiCardTile(){
		
	}
	public GuiCardTile(int mX, int mY, int x, int y , int width, int height,Block block){
		this.posX=x;
		this.posY=y;
		this.sizeX=width;
		this.sizeY=height;
		this.mouseX=mY;
		this.mouseY=mY;
		this.theBlock=block;
	}
	
	
	public boolean mouseOver(){
		if(mouseX>posX && mouseX<posX+sizeX && mouseY<posY+sizeY&& mouseY>posY){
			return true;
		}
		return false;
	}
	
    public void draw()
    {
    		//if(mouseOver==true){
    		//}else
            //GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            //GL11.glEnable(GL11.GL_BLEND);
           // OpenGlHelper.glBlendFunc(770, 771, 1, 0);
           // GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
            if(this.mouseOver){
               drawRect(posX, posY, posX+sizeX, posY+sizeY,Color.pink.getRGB());
            }
            drawRect(posX, posY, posX+sizeX, posY+sizeY,Color.green.getRGB());
    }
}
