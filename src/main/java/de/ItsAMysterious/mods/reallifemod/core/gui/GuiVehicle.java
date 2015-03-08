package de.ItsAMysterious.mods.reallifemod.core.gui;

import java.awt.Color;

import net.minecraft.client.gui.Gui;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;

import de.ItsAMysterious.mods.reallifemod.api.gui.RLM_Gui;
import de.ItsAMysterious.mods.reallifemod.api.util.LittleFunctions;
import de.ItsAMysterious.mods.reallifemod.core.streets.entitys.EntityBasicVehicle;

public class GuiVehicle extends RLM_Gui {
	EntityBasicVehicle vehicle;
	private boolean steerWithMouse=false;

	public GuiVehicle(EntityBasicVehicle entityJeep) {
		this.vehicle=entityJeep;
	}

	@Override
	public void drawScreen(int x,int y,float f){
		String speed=String.valueOf((LittleFunctions.roundDouble(this.vehicle.speed, 3)));
		this.drawString("speed: "+speed+" km/h", 0, 0, Color.white.getRGB());
		this.drawString("steering angle: "+ -this.vehicle.deltaSteer , 0, 10, Color.white.getRGB());
		this.drawString("rotationangle: "+ this.vehicle.rotationYaw , 0, 20, Color.white.getRGB());
		super.drawScreen(x, y, f);
		Gui.drawRect(0, 30,100-(int) (LittleFunctions.roundDouble(this.vehicle.Damage,3)%100),40, Color.white.getRGB());
		Gui.drawRect(100-(int) (LittleFunctions.roundDouble(this.vehicle.Damage,3)%100), 30,100,40, Color.black.getRGB());
		this.drawString(String.valueOf(LittleFunctions.roundDouble(this.vehicle.Damage,3))+"%" , 50, 30, Color.gray.getRGB());
		if(this.steerWithMouse==true){
	    	//this.vehicle.deltaSteer-=Mouse.getDX();
		}
	}
	
	@Override
	public void initGui(){
		if(Display.isActive()){
			this.mc.inGameHasFocus = true;
			this.mc.mouseHelper.grabMouseCursor();
		}
	}
	
	
    @Override
	public boolean doesGuiPauseGame()
    {
        return false;
    }
    
    @Override
    protected void keyTyped(char keyValue, int ord)
    {
    	super.keyTyped(keyValue, ord);
    	if(ord==Keyboard.KEY_LSHIFT){
    		this.vehicle.riddenByEntity.mountEntity(null);
    		this.mc.displayGuiScreen(null);
    	}
    	
    	if(ord==Keyboard.KEY_C){
    		this.steerWithMouse=!this.steerWithMouse;
    		this.mc.displayGuiScreen(this);
    	}
    }
    
	
}
