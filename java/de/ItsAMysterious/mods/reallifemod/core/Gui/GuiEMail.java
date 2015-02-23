package de.ItsAMysterious.mods.reallifemod.core.gui;

import java.awt.Color;

import de.ItsAMysterious.mods.reallifemod.api.gui.RLM_Gui;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiTextField;

public class GuiEMail extends RLM_Gui{
	private GuiTextField to;
	private GuiTextField from;
	@Override
	public void drawScreen(int x, int y,float f){
		Gui.drawRect(0, 20, width, height, Color.WHITE.getRGB());
		this.drawString("TO", 20, 25, Color.LIGHT_GRAY.getRGB());
		this.drawString("FROM", 20, 40, Color.LIGHT_GRAY.getRGB());
		to.drawTextBox();
		from.drawTextBox();
		to.setEnableBackgroundDrawing(true);
		from.setEnableBackgroundDrawing(true);
		super.drawScreen(x, y, f);
	}
	
	@Override
	public void initGui(){
		this.buttonList.clear();
		this.buttonList.add(new GuiButton(0, 0, 0,this.width/5,20, "Shop"));
		this.buttonList.add(new GuiButton(1, this.width/5, 0,this.width/5,20, "Home"));
		this.buttonList.add(new GuiButton(2,this.width/5*2,0,this.width/5,20,"Help"));
		this.buttonList.add(new GuiButton(3,this.width/5*3,0,this.width/5,20,"Settings"));
		this.buttonList.add(new GuiButton(4, this.width/5*4,0,this.width/5, 20,"Management"));
		to=new GuiTextField(fontRendererObj, 50, 25, this.width-80, 10);
		from=new GuiTextField(fontRendererObj, 50, 40, this.width-80, 10);
		to.setCanLoseFocus(true);
		to.setCanLoseFocus(true);
		from.setFocused(true);
		from.setFocused(false);
	}
	
	@Override
	public void actionPerformed(GuiButton button){
		if (button.id==0){
			mc.displayGuiScreen(new GuiOnlineShop());
		}
		if (button.id==1){
			mc.displayGuiScreen(new GuiInternet());
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
	
	public void onUpdate(){
		to.updateCursorCounter();
		from.updateCursorCounter();
	}
	
	@Override
	public void keyTyped(char taste,int id){
		if(to.textboxKeyTyped(taste, id)){};
		if(from.textboxKeyTyped(taste, id)){};
		super.keyTyped(taste, id);
	}
	
	@Override
	public void mouseClicked(int x,int y,int id){
		to.mouseClicked(x, y, id);
		from.mouseClicked(x, y, id);
		super.mouseClicked(x, y, id);
	}
}
