package de.ItsAMysterious.mods.reallifemod.core.Gui;

import java.awt.Color;

import cpw.mods.fml.client.FMLClientHandler;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;

public class GuiManagement extends GuiScreen{
	
	@Override
	public void drawScreen(int x, int y,float f){
		Gui.drawRect(0, 20, width, height, Color.WHITE.getRGB());
		super.drawScreen(x, y, f);
	}
	@Override
	public void updateScreen(){
	}
	
	@Override
	public void initGui(){
		this.buttonList.add(new GuiButton(0, 0, 0,this.width/5,20, "Shop"));
		this.buttonList.add(new GuiButton(1, this.width/5, 0,this.width/5,20, "E-Mail"));
		this.buttonList.add(new GuiButton(2,this.width/5*2,0,this.width/5,20,"Help"));
		this.buttonList.add(new GuiButton(3,this.width/5*3,0,this.width/5,20,"Settings"));
		this.buttonList.add(new GuiButton(4, this.width/5*4,0,this.width/5, 20,"Home"));
		this.buttonList.add(new GuiButton(5, 10,50,this.width/5,20,"trafficlights"));
	}
	
	@Override
	public void actionPerformed(GuiButton button){
		if (button.id==0){
			mc.displayGuiScreen(new GuiOnlineShop());
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
			mc.displayGuiScreen(new GuiInternet());
		}
		if(button.id==5){
	    	FMLClientHandler.instance().getClient().displayGuiScreen(new Gui_TrafficLight(mc.objectMouseOver.blockX,mc.objectMouseOver.blockY,mc.objectMouseOver.blockZ));
		}
	}
	

    
	@Override
	public boolean doesGuiPauseGame(){
		return false;
	}
}
