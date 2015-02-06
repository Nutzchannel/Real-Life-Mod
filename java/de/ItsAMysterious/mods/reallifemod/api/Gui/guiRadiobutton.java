package de.ItsAMysterious.mods.reallifemod.api.Gui;

import java.awt.Color;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.util.ResourceLocation;

public class guiRadiobutton extends Gui{
	private FontRenderer fontrenderObj;
	int xPos,yPos,width;
	public boolean checked;
	private ResourceLocation buttonTextures=new ResourceLocation("reallifemod:textures/gui/gradient.png");
	private int mouseX;
	private int mouseY;
	private int height;
	private String text;
	
	public guiRadiobutton(int xPos,int yPos, String text)
	{
		this.xPos=xPos;
		this.yPos=yPos;
		this.text=text;
		this.fontrenderObj=Minecraft.getMinecraft().fontRenderer;
	}
	
	public String getText(){
		return this.text;
	}
	
	public void draw(){
			Gui.drawRect(this.xPos+(fontrenderObj.getStringWidth(text)+1), this.yPos,this.xPos+(fontrenderObj.getStringWidth(text)+13),this.yPos+12, Color.gray.getRGB());
			if(this.checked)
			Gui.drawRect(this.xPos+(fontrenderObj.getStringWidth(text)+2), this.yPos+1,this.xPos+(fontrenderObj.getStringWidth(text)+12),this.yPos+11, Color.green.getRGB());
			else
			Gui.drawRect(this.xPos+(fontrenderObj.getStringWidth(text)+2), this.yPos+1,this.xPos+(fontrenderObj.getStringWidth(text)+12),this.yPos+11, Color.black.getRGB());
			this.drawString(fontrenderObj, this.text, this.xPos+1, this.yPos+1, Color.white.getRGB());	
		}
	
	public void onMouseClicked(int x, int y, int id){
		if(isMouseHoovering(x, y)==true){
			this.checked=!this.checked;
		}
	}

	public boolean isMouseHoovering(int x, int y) {
		return x>this.xPos&&x<this.xPos+fontrenderObj.getStringWidth(text)+13&&y>this.yPos&&y<this.yPos+12;
	}
	
}
