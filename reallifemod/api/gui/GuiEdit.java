package de.ItsAMysterious.mods.reallifemod.api.gui;

import org.lwjgl.input.Keyboard;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiTextField;

public class GuiEdit extends GuiTextField{

	private int ID;

	public GuiEdit(FontRenderer fontRenderer, int posX, int posY,
			int width, int height) {
		super(fontRenderer, posX, posY, width, height);
	}
	
	public void setID(GuiEdit edit,int id){
		this.ID=id;
	}
	
	public int getID(GuiEdit edit){
		return edit.ID;
	}
	
	public void setNextTextBox(int id){
		
	}
	
    public boolean textboxKeyTyped(char taste, int id,GuiEdit edit)
    {
    	if(id==Keyboard.KEY_TAB){
    	}
    	return true;
    }

}
