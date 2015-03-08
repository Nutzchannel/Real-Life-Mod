package de.ItsAMysterious.mods.reallifemod.core.gui;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.client.gui.Gui;

public class guiDropdownMenu extends Gui{
	//Variables
	private int xPos,yPos, width, xM, yM;
	public static Map<Integer, guiListElement> elements=new HashMap<Integer, guiListElement>();
	public boolean isDropped;
	
	public guiDropdownMenu(int xp, int yp){
		this.xPos=xp;
		this.yPos=yp;
	}
	
	public void onUpdate(){
		for(int i=0;i<elements.size();i++){
			guiListElement e=elements.get(i);
			e.onUpdate();
		}
	}

	public void draw() {
		for(int i=0;i<elements.size();i++){
			guiListElement e=elements.get(i);
			e.draw(xM,yM);
		}
	}
	
	public void onMouseClicked(int x, int y, int id) {
			for(int i=0;i<elements.size();i++){
				guiListElement e=elements.get(i);
				e.onMouseClicked(x, y, id);
			}
		if(this.isMouseHoovering(x,y)){
			this.isDropped=!this.isDropped;
		}
	}

	private boolean isMouseHoovering(int x, int y) {
			for(int i=0;i<elements.size();i++){
				guiListElement e=elements.get(i);
				e.isMouseHoovering(x, y);
		}
		return false;
	}
	
	@SuppressWarnings("unused")
	public guiListElement getCurrentElement(){
		if(elements.get(0)!=null){
			for(int i=0;i<elements.size();i++){
				guiListElement e=elements.get(i);
				if(e.selected==true)
					return e;
				else
					return null;
			}
		}else
			return null;
		return null;
	}
	
}