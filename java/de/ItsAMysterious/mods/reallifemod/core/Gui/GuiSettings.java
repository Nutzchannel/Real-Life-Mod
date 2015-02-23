package de.ItsAMysterious.mods.reallifemod.core.gui;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;

import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiYesNoCallback;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import de.ItsAMysterious.mods.reallifemod.RealLifeMod;
import de.ItsAMysterious.mods.reallifemod.core.config.TLMSettings;

@SideOnly(Side.CLIENT)
public class GuiSettings extends GuiScreen implements GuiYesNoCallback{
    
	public Map Actions=new HashMap();
    
    public GuiSettings(){}

	@Override
	public void initGui(){
		this.buttonList.clear();
		this.buttonList.add(new GuiButton(4, 0, 0,this.width/5,20, "Shop"));
		this.buttonList.add(new GuiButton(5, this.width/5, 0, this.width/5,20, "E-Mail"));
		this.buttonList.add(new GuiButton(6, this.width/5*2, 0, this.width/5,20,"Help"));
		this.buttonList.add(new GuiButton(7, this.width/5*3, 0, this.width/5,20,"Home"));
		this.buttonList.add(new GuiButton(8, this.width/5*4, 0, this.width/5, 20,"Management"));
	    this.buttonList.add(new GuiButton(10, 40, 40, 150, 20, "RealityMode: "+TLMSettings.realityMode));
	    this.buttonList.add(new GuiButton(11, 40, 70, 150, 20, "Realistic 3D Models: "+TLMSettings.realisticmodels));
	    this.buttonList.add(new GuiButton(12, 40, 100, 150, 20, "3D Inventory blocks: "+TLMSettings.Inventory3D));

		super.initGui();
	}
	
	@Override
	public void drawScreen(int x, int y, float s){
		Gui.drawRect(0, 20, this.width, this.height, Color.WHITE.getRGB());
		super.drawScreen(x, y, s);
	}
	
	@Override
	public void actionPerformed(GuiButton button){
		if(button.id==10){
			TLMSettings.realityMode=!TLMSettings.realityMode;
			confirmClicked(true, button.id);
		}
		
		if(button.id==11){
			TLMSettings.realisticmodels=!TLMSettings.realisticmodels;
			confirmClicked(true, button.id);
		}
		
		if(button.id==12){
			TLMSettings.Inventory3D=!TLMSettings.Inventory3D;
			confirmClicked(true, button.id);
		}
		if(button.id==3){
		}
		if (button.id==4){
			mc.displayGuiScreen(new GuiOnlineShop());
		}
		if (button.id==5){
			mc.displayGuiScreen(new GuiEMail());
		}
		if (button.id==6){
			mc.displayGuiScreen(new GuiHelp());
		}
		if (button.id==7){
			mc.displayGuiScreen(new GuiInternet());
		}
		if (button.id==8){
			mc.displayGuiScreen(new GuiManagement());
		}
		RealLifeMod.syncConfig();
	}
	
    private void accept() {
    	Actions.clear();
	}

	private void reset() {
		if(Actions!=null){
			if(Actions.containsValue(TLMSettings.realityMode)){
				TLMSettings.realityMode=!TLMSettings.realityMode;
			}
		}
	}

	@Override
	public void confirmClicked(boolean b1, int id)
    {
        this.mc.displayGuiScreen(this);
        super.confirmClicked(b1, id);
    }
}
