package de.ItsAMysterious.mods.reallifemod.core.gui;

import java.awt.Color;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.ResourceLocation;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import de.ItsAMysterious.mods.reallifemod.RealLifeMod;
import de.ItsAMysterious.mods.reallifemod.TLMItems;
import de.ItsAMysterious.mods.reallifemod.api.entity.properties.financialProps;

public class GuiEspresso extends GuiScreen{
	public String Displaymessage="";
	public EntityPlayer player;
	public void initGui(){
		super.initGui();
		this.player=this.mc.thePlayer;
		this.buttonList.add(new GuiButton(0, this.width/2+40, this.height/2-45, 60, 20, "Coffee"));
		this.buttonList.add(new GuiButton(1, this.width/2+40, this.height/2-26, 60, 20, "Espresso"));
		this.buttonList.add(new GuiButton(2, this.width/2+40, this.height/2-8, 60, 20, "Cappucino"));
		this.buttonList.add(new GuiButton(3, this.width/2+40, this.height/2+10, 60, 20, "Hot Choc"));
		this.buttonList.add(new GuiButton(4, this.width/2+40, this.height/2+28, 60, 20, "Latte"));
		this.buttonList.add(new GuiButton(5, this.width/2+40, this.height/2+46, 60, 20, "Tea"));
		this.buttonList.add(new GuiButton(6, this.width/2-95, this.height/2+50, 60, 20, EnumChatFormatting.GREEN+"OK"));
		this.buttonList.add(new GuiButton(7, this.width-20, 0, 20, 20, "X"));
	}
	
	@Override
	public void drawScreen(int x, int y, float f){
		Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation(RealLifeMod.ID+":textures/gui/guiEspresso.png"));
		this.drawTexturedModalRect(this.width/2-100, this.height/2-75, 0, 0, 200, 150);
		this.drawCenteredString(fontRendererObj, EnumChatFormatting.ITALIC+Displaymessage, this.width/2+68, this.height/2-63, Color.white.getRGB());
		super.drawScreen(x, y, f);
	}
	
	@Override
	public boolean doesGuiPauseGame(){
		return false;
	}
	
	public void actionPerformed(GuiButton button){
		if(button.id<6){
		this.Displaymessage=button.displayString;}
		
		if(button.id==6){
			if(((financialProps)player.getExtendedProperties("financialProps")).Cash-1.50>0)
			{
			if(Displaymessage=="Cappuccino"){
				mc.thePlayer.inventory.addItemStackToInventory(new ItemStack(TLMItems.cupCappuccino));
			}
			if(Displaymessage=="Coffee"){
				mc.thePlayer.inventory.addItemStackToInventory(new ItemStack(TLMItems.cupCoffee));
			}
			if(Displaymessage=="Hot Choc"){
				mc.thePlayer.inventory.addItemStackToInventory(new ItemStack(TLMItems.cupHotChocolate));
			}
			if(Displaymessage=="Espresso"){
				mc.thePlayer.inventory.addItemStackToInventory(new ItemStack(TLMItems.cupEspresso));
			}
			if(Displaymessage=="Latte"){
				mc.thePlayer.inventory.addItemStackToInventory(new ItemStack(TLMItems.cupLatte));
			}
			if(Displaymessage=="Tea"){
			mc.thePlayer.inventory.addItemStackToInventory(new ItemStack(TLMItems.cupTea));
			}else
				this.Displaymessage=EnumChatFormatting.RED+"Not enough Money!";
			}
			this.confirmClicked(true, 6);
		}else
		if(button.id==7){
			this.confirmClicked(true, 7);
			Minecraft.getMinecraft().displayGuiScreen(null);
		}
	}
	
    protected void keyTyped(char p_73869_1_, int p_73869_2_)
    {
    	super.keyTyped(p_73869_1_, p_73869_2_);
    }

}
