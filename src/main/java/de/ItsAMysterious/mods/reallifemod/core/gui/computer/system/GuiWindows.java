package de.ItsAMysterious.mods.reallifemod.core.gui.computer.system;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.input.Keyboard;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiYesNoCallback;
import net.minecraft.util.ResourceLocation;

public class GuiWindows extends GuiScreen implements GuiYesNoCallback{
	protected List windowList=new ArrayList();
	
	@Override
	public void initGui(){
		this.buttonList.clear();
		this.buttonList.add(new GuiButton(0, 0, this.height-20	,40, 20, "Start"));
		super.initGui();
		this.windowList.clear();
	}
	@Override
	public void drawScreen(int x,int y,float f){
		super.drawScreen(x,y,f);
		int k;
        if(this.windowList.isEmpty()==false){
        	for (k = 0; k < this.windowList.size(); ++k)
        	{((guiExplorerWindow)this.windowList.get(k)).showExplorerWindow(x, y);
        	((guiExplorerWindow)this.windowList.get(k)).initGui();}
        	
        }
		this.drawTaskbar();
	}
	
	@Override
	public void updateScreen(){
		super.updateScreen();
	}
	
	@Override
	public void keyTyped(char taste,int id){
		super.keyTyped(taste, id);
		if(id==Keyboard.KEY_E&&GuiScreen.isShiftKeyDown()){
			this.windowList.add(new guiExplorerWindow(0, this.width/2-150, this.height/2-100, 500, 300, "Minedows Exlporer"));
		}
	}
	
	@Override
	public void mouseClicked(int x,int y,int id){
		super.mouseClicked(x, y, id);
	}
	
    @Override
	protected void actionPerformed(GuiButton button) {
    	if(button.id==0){
    	}
    }
    
    public void drawTaskbar(){
    	mc.getTextureManager().bindTexture(new ResourceLocation("reallifemod:textures/gui/System/taskBar.png"));
		this.drawTexturedModalRect(40,this.height-20, 0, 0, this.width-40, 20);
    }
    
    public void drawStartMenue(int posX,int posY){
    	this.drawTexturedModalRect(posX, posY, 0, 0, 150, 75);
    	this.buttonList.add(new GuiButton(1, posX+50, posY+1,20,10, "Shutdown"));
    }

	
	

}
