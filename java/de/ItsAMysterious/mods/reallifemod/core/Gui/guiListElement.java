package de.ItsAMysterious.mods.reallifemod.core.Gui;

import java.awt.Color;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;

public class guiListElement extends Gui{

	private FontRenderer fontrenderObj;
	private int xPos,yPos,width,rectWidth;
	private String text;
	public boolean selected;
	public Color rectColor;

	public void onUpdate() {
	}
	
	public void draw(int x, int y) {
		if(this.isMouseHoovering(x, y)){
			Gui.drawRect(xPos, yPos, xPos+width, yPos+15, Color.blue.getRGB());
		}else
			Gui.drawRect(xPos, yPos, xPos+width, yPos+15, Color.gray.getRGB());
		Gui.drawRect(xPos+1, yPos+1, xPos+1+rectWidth, yPos+1+13, rectColor.getRGB());
	}
	
	public void onMouseClicked(int x, int y, int id) {
		
	}
	
	public boolean isMouseHoovering(int x, int y) {
		return x>xPos&&x<xPos+fontrenderObj.getStringWidth(text)+rectWidth&&y>yPos&&y<yPos+15;
	}

}
