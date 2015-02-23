package de.ItsAMysterious.mods.reallifemod.api.gui;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.client.gui.Gui;

public class GuiRadiogroup extends Gui{
	
	public static Map<Integer, GuiRadiobutton>buttonList=new HashMap<Integer, GuiRadiobutton>();
	public int xPos, yPos;
	public boolean singleChoice=true;
	public static GuiRadiobutton currentButton;
	
	public GuiRadiogroup(int xp, int yp){
		this.xPos=xp;
		this.yPos=yp;
	}
	
	public void draw(){
		if(GuiRadiogroup.buttonList.size()!=0){
			for(int i=0;i<GuiRadiogroup.buttonList.size();i++){
				GuiRadiobutton bt=GuiRadiogroup.buttonList.get(i);
				bt.draw();
			}
		}
	}
	
	public void doUpdate(){
		if(GuiRadiogroup.buttonList.size()!=0){
			for(int i=0;i<GuiRadiogroup.buttonList.size();i++){
				GuiRadiobutton bt=GuiRadiogroup.buttonList.get(i);
				if(bt!=GuiRadiogroup.currentButton)bt.checked=false;
			}
		}
	}
	
	public void onMouseClicked(int x, int y,int id){
		if(GuiRadiogroup.buttonList.size()!=0){
			for(int i=0;i<GuiRadiogroup.buttonList.size();i++){
				GuiRadiobutton bt=GuiRadiogroup.buttonList.get(i);
				if(bt.isMouseHoovering(x, y)){
					GuiRadiogroup.currentButton=bt;
					bt.onMouseClicked(x, y, id);
				}
			}
		}	}

	public GuiRadiobutton getSelectedbutton() {
			return GuiRadiogroup.currentButton;
	}
			
}
