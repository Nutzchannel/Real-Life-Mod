package de.ItsAMysterious.mods.reallifemod.core.Gui;

import org.lwjgl.input.Keyboard;

import de.ItsAMysterious.mods.reallifemod.core.blocks.furniture.blockCupboard;
import de.ItsAMysterious.mods.reallifemod.core.rendering.TileEntitys.cupboardTE;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;

public class GuiCupboard extends GuiScreen{
	
	blockCupboard theBlock;
	private cupboardTE tile;
	public GuiCupboard(cupboardTE c){
		this.tile=c;
	}
	
	@Override
	public void initGui(){
		this.buttonList.add(new GuiButton(0, width-150, height-40, "close"));
	}
	
	@Override
	public void keyTyped(char taste,int id){
		if(id==Keyboard.KEY_ESCAPE){
			this.tile.open=false;
		}
		super.keyTyped(taste, id);
	}

}
