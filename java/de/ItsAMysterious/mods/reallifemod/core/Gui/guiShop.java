package de.ItsAMysterious.mods.reallifemod.core.Gui;

import java.awt.Color;

import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Container;

public class guiShop extends GuiContainer{

	public guiShop(Container container) {
		super(container);
	}
	
	@Override
	public void initGui(){
	}
	
	@Override
	public void drawScreen(int x, int y,float f){
		Gui.drawRect(0, 20, this.width, this.height, Color.white.getRGB());
		super.drawScreen(x, y, f);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float p_146976_1_,
			int p_146976_2_, int p_146976_3_) {
		
	}
	
	@Override
	public void actionPerformed(GuiButton button){
		if (button.id==0){
			mc.displayGuiScreen(new GuiInternet());
		}
		if (button.id==1){
			mc.displayGuiScreen(new GuiEMail());
		}
		if (button.id==2){
			mc.displayGuiScreen(new GuiHelp());
		}
		if (button.id==3){
			mc.displayGuiScreen(new GuiSettings());
		}
		if (button.id==4){
			mc.displayGuiScreen(new GuiManagement());
		}
		
	}

}
