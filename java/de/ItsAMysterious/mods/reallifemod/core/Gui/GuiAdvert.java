package de.ItsAMysterious.mods.reallifemod.core.gui;

import net.minecraft.client.gui.GuiButton;
import de.ItsAMysterious.mods.reallifemod.api.gui.RLM_Gui;
import de.ItsAMysterious.mods.reallifemod.core.tiles.bilboardTE;

public class GuiAdvert extends RLM_Gui{
	public bilboardTE shield;
	public GuiAdvert(bilboardTE s){
		this.shield=s;
	}
	@Override
	public void initGui(){
		this.buttonList.add(new GuiButton(0, this.width/2-200, this.height/2+100,100,20, "Load Image"));
		this.buttonList.add(new GuiButton(1, this.width/2-90, this.height/2+100,100,20, "OK"));
		super.initGui();
	}
	
	@Override
	public void actionPerformed(GuiButton b){
		super.actionPerformed(b);
		switch(b.id){
			case 0:
					this.shield.imageNum++;
					System.out.println(shield.image.toString());
					break;
				}
	}

}
