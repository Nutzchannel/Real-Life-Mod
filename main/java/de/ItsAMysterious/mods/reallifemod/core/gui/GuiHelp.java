package de.ItsAMysterious.mods.reallifemod.core.gui;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiYesNoCallback;
import de.ItsAMysterious.mods.reallifemod.api.gui.RLM_Gui;

public class GuiHelp extends RLM_Gui implements GuiYesNoCallback{
	int gray=Color.darkGray.getRGB();
	public String infoText="temperature";
	public String[]lines;
	public guiDropdownMenu menu;
	public static enum texts{
		temperatur, paramedics;
	}
	public texts text=texts.temperatur;
	public static  String heat=
		"HEAT AND COLD: "+
		"From now, the world in minecraft will have a new attribute: TEMPERATURE!\n It will have influence on your energy and your strength. If the temperature "+
		"gets higher than 40° you will have to drink something and also you will have to seek"+ 
		" shelter in the shadows! If it gets to cold you have to dress warmer, otherwise"+
		"you will freeze to death. And be carefull:"+
		"DO NOT STAY NEAR LAVA FOR TOO LONG! IT WILL BURN YOU!";
	public static  String param=
			"PARAMEDICS: "+
			"When you get hurt, paramedics will arrive and bring you to the next village or "+
			"the next hospital.";
		

	@Override
	public void updateScreen(){
		super.updateScreen();
		this.menu.onUpdate();
	}
	
	@Override
	public void drawScreen(int par1,int par2,float f1){
		Gui.drawRect(0, 20, this.width, this.height, Color.gray.getRGB());
		Gui.drawRect(2, 45, this.width-2, this.height/2+10, Color.GRAY.darker().getRGB());
		this.drawCenteredString(fontRendererObj, "Information,tips,tricks and more", this.width/2, 25,Color.white.getRGB());
		this.menu.draw();
		super.drawScreen(par1, par2, f1);
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
			mc.displayGuiScreen(new GuiInternet());
		}
		if (button.id==3){
			mc.displayGuiScreen(new GuiSettings());
		}
		if (button.id==4){
			mc.displayGuiScreen(new GuiManagement());
		}
		if(button.id==5){
			this.text=texts.temperatur;
		}
		if(button.id==6){
			this.text=texts.paramedics;
		}
	}
	
	private void displayInfo(String heat) {
		int k;
		for(int i=1; i<heat.length();i++){
			k=i/150;
			this.drawString(GuiHelp.heat.substring(0+i*180).toString(), 0, 180+k*10, Color.green.getRGB());
		}
	}

	@Override
	public void initGui(){
		this.buttonList.clear();
		this.buttonList.add(new GuiButton(0, 0, 0,this.width/5,20, "Shop"));
		this.buttonList.add(new GuiButton(1, this.width/5, 0,this.width/5,20, "E-Mail"));
		this.buttonList.add(new GuiButton(2,this.width/5*2,0,this.width/5,20,"Home"));
		this.buttonList.add(new GuiButton(3,this.width/5*3,0,this.width/5,20,"Settings"));
		this.buttonList.add(new GuiButton(4, this.width/5*4,0,this.width/5, 20,"Management"));
		this.buttonList.add(new GuiButton(5, 0,160,this.width/5, 20,"Heat and Cold"));
		this.buttonList.add(new GuiButton(6, this.width/5,160,this.width/5, 20,"Paramedics"));
		this.menu=new guiDropdownMenu(5, this.height/2+50);
	}
	
    @Override
	protected void mouseClicked(int x, int y, int id)
    {
    	this.menu.onMouseClicked(x, y, id);
    	super.mouseClicked(x, y, id);
    }
	
	public void  loadInfoText(String subject, File file) throws FileNotFoundException, IOException{
		BufferedReader reader=new BufferedReader(new FileReader(file));
		String line;
		while((line = reader.readLine())!=null){
			if(line.startsWith("[INFO]")){
				String Heading=line.split(" ")[1];
				//this.displaydInfo=line;
			}
		}
		reader.close();
	}
    
}
