package de.ItsAMysterious.mods.reallifemod.api.Gui;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.client.gui.Gui;

public class guiRadioGroup extends Gui{
	
	public static Map<Integer, guiRadiobutton>buttonList=new HashMap<Integer, guiRadiobutton>();
	public int xPos, yPos;
	public boolean singleChoice=true;
	public static guiRadiobutton currentButton;
	
	public guiRadioGroup(int xp, int yp){
		this.xPos=xp;
		this.yPos=yp;
	}
	
	public void draw(){
		if(guiRadioGroup.buttonList.size()!=0){
			for(int i=0;i<guiRadioGroup.buttonList.size();i++){
				guiRadiobutton bt=guiRadioGroup.buttonList.get(i);
				bt.draw();
			}
		}
	}
	
	public void doUpdate(){
		if(guiRadioGroup.buttonList.size()!=0){
			for(int i=0;i<guiRadioGroup.buttonList.size();i++){
				guiRadiobutton bt=guiRadioGroup.buttonList.get(i);
				if(bt!=guiRadioGroup.currentButton)bt.checked=false;
			}
		}
	}
	
	public void onMouseClicked(int x, int y,int id){
		if(guiRadioGroup.buttonList.size()!=0){
			for(int i=0;i<guiRadioGroup.buttonList.size();i++){
				guiRadiobutton bt=guiRadioGroup.buttonList.get(i);
				if(bt.isMouseHoovering(x, y)){
					guiRadioGroup.currentButton=bt;
					bt.onMouseClicked(x, y, id);
				}
			}
		}	}

	public guiRadiobutton getSelectedbutton() {
			return guiRadioGroup.currentButton;
	}
			
}
