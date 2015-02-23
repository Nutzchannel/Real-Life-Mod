package de.ItsAMysterious.mods.reallifemod.core.gui;

import java.awt.Color;

import de.ItsAMysterious.mods.reallifemod.api.gui.GuiEdit;
import de.ItsAMysterious.mods.reallifemod.api.gui.RLM_Gui;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.gui.GuiYesNoCallback;
import net.minecraft.util.ResourceLocation;

public class GuiInternet extends RLM_Gui implements GuiYesNoCallback{
	
	public GuiTextField SearchBar;
	int x0;
	int y0;
	public ResourceLocation MCSearch=new ResourceLocation("reallifemod:textures/gui/MC.png");

	@Override
	public void drawScreen(int par1,int par2,float f1){
		Gui.drawRect(0,20,width,height,Color.WHITE.getRGB());
		Gui.drawRect(x0-5,y0-5,x0+300,y0+55,Color.GRAY.getRGB());
		this.drawString(fontRendererObj,"Welcome to your new computer! It can be used to ",x0, y0,Color.WHITE.getRGB());
		this.drawString(fontRendererObj,"manage your life, to buy new things and of course ",x0, y0+10,Color.WHITE.getRGB());
		this.drawString(fontRendererObj,"to communicate! Search things in the searchbar",x0, y0+20,Color.WHITE.getRGB());
		this.drawString(fontRendererObj,"above ,to get help! You can also find many of tips and ",x0, y0+30,Color.WHITE.getRGB());
		this.drawString(fontRendererObj,"tricks for the 'real life mod' in the 'Help' tab!",x0, y0+40,Color.WHITE.getRGB());
		this.mc.renderEngine.bindTexture(MCSearch);
		this.drawTexturedModalRect(this.width/2-100,this.height/2-75,0,0,200,50);
		SearchBar.drawTextBox();
		SearchBar.setEnableBackgroundDrawing(true);
		super.drawScreen(par1, par2, f1);
	}
	@Override
	public void updateScreen(){
		SearchBar.updateCursorCounter();
		x0=this.width/2-145;
		y0=this.height/2+20;

		}
	
	@Override
	public void initGui(){
		this.buttonList.clear();
		this.buttonList.add(new GuiButton(0, 0, 0,this.width/5,20, "Shop"));
		this.buttonList.add(new GuiButton(1, this.width/5, 0,this.width/5,20, "E-Mail"));
		this.buttonList.add(new GuiButton(2,this.width/5*2,0,this.width/5,20,"Help"));
		this.buttonList.add(new GuiButton(3,this.width/5*3,0,this.width/5,20,"Settings"));
		this.buttonList.add(new GuiButton(4, this.width/5*4,0,this.width/5, 20,"Management"));
		this.buttonList.add(new GuiButton(6, this.width/2+105, this.height/2-20,40,15,"search"));
		SearchBar=new GuiEdit(fontRendererObj,this.width/2-100, this.height/2-20, 200, 15);
		/**speechThreaded.speechSynth(0,-100,(float)Math.random(),-10, "Welcome to your new computer! It can be used to "+
		"manage your life, to buy new things and of course, "+
		"to communicate! Search things in the searchbar"+
		"above , to get help! You can also find many of tips and "+
		"tricks, for the reallife-mod! in the help-tab!"
		);*/
		
		SearchBar.setCanLoseFocus(true);
		SearchBar.setFocused(true);
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
			mc.displayGuiScreen(new GuiManagement());
		}
	}
	
	@Override
	public void keyTyped(char taste,int id){
		if(SearchBar.textboxKeyTyped(taste, id)){};
		super.keyTyped(taste, id);
	}
	
	@Override
	public void mouseClicked(int x,int y,int id){
		SearchBar.mouseClicked(x, y, id);
		super.mouseClicked(x, y, id);
	}
	
}
