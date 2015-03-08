package de.ItsAMysterious.mods.reallifemod.core.gui;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import de.ItsAMysterious.mods.reallifemod.api.gui.RLM_Gui;
import de.ItsAMysterious.mods.reallifemod.client.ClientProxy;
import de.ItsAMysterious.mods.reallifemod.init.TLMItems;

public class GuiATM extends RLM_Gui{
	public int buttonCountY=0;
	public int rowX=0;
	private int timesJumpedToTop=0;
	@Override
	public void updateScreen(){
		this.initGui();
	}
	@Override
	public void actionPerformed(GuiButton button){
		if(ClientProxy.Money.get(mc.thePlayer.getDisplayName())-20>0){
			ClientProxy.Money.replace(mc.thePlayer.getDisplayName(),ClientProxy.Money.get(mc.thePlayer.getDisplayName())-20);
			mc.thePlayer.inventory.addItemStackToInventory(new ItemStack(TLMItems.itemList.get(button.id)));
		}else
			mc.thePlayer.addChatMessage(new ChatComponentText(EnumChatFormatting.RED+"NOT ENOUGH MONEY!"));
	}
	
	@Override
	public void initGui(){
		this.buttonCountY=mc.displayHeight/20;
		for(int i=0; i<TLMItems.itemList.size();i++){
			Item item=TLMItems.itemList.get(i);		
			if(i*20+20>this.buttonCountY*20){
				this.rowX++; this.timesJumpedToTop++;
		}
			this.buttonList.add(new GuiButton(i,rowX*100,i*20-(buttonCountY*20)*timesJumpedToTop,100,20, "buy "+ item.getUnlocalizedName().split("item.")[1]));
		}
	}
	
}
