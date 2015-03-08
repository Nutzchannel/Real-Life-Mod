package de.ItsAMysterious.mods.reallifemod.core.gui;

import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushMatrix;

import java.awt.Color;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.ResourceLocation;
import de.ItsAMysterious.mods.reallifemod.RealLifeMod;
import de.ItsAMysterious.mods.reallifemod.api.entity.properties.RealLifeProperties;
import de.ItsAMysterious.mods.reallifemod.api.entity.properties.RealLifeProperties.phone.phonepartners;
import de.ItsAMysterious.mods.reallifemod.api.util.LittleFunctions;

public class guiPhone extends GuiScreen{
	public GuiTextField numberField;
	public guiPhone() {
		super();
	}
	
	@Override
	public void initGui(){
		super.initGui();
		int buttonPosX=0;
		int row=0;
		for(int i = 0;i<12; i++){
			if(buttonPosX==4*20){
				row+=1;
				buttonPosX=0;
			}
			
			if(buttonPosX==4*20){
				row+=1;
				buttonPosX=0;
			}
			if(i<9)
			this.buttonList.add(new GuiButton(i,(int)this.width/2-38+buttonPosX,(int)(this.height/2)-10+row*20,20,20, String.valueOf(i+1)));
			else{
				if(i==9){
					this.buttonList.add(new GuiButton(i,(int)this.width/2-38+buttonPosX,(int)(this.height/2)-10+row*20,20,20, "0"));
				}
				if(i==10){
					this.buttonList.add(new GuiButton(i,(int)this.width/2-38+buttonPosX,(int)(this.height/2)-10+row*20,20,20, "<-"));
				}
				if(i==11){
					this.buttonList.add(new GuiButton(i,(int)this.width/2-38+buttonPosX,(int)(this.height/2)-10+row*20,20,20, EnumChatFormatting.GREEN+"ok"));
				}
			}
			buttonPosX+=20;
		}

		this.numberField=new GuiTextField(this.fontRendererObj, (int)(this.width/2-35), this.height/2-52, 75, 15);
		this.numberField.setCanLoseFocus(true);
		this.numberField.setFocused(true);
	}
	
	@Override
	public void drawScreen(int x, int y,float f){
		glPushMatrix();
		mc.renderEngine.bindTexture(new ResourceLocation(RealLifeMod.ID+":textures/gui/phone.png"));
		this.drawTexturedModalRect((int)width/2-46,(int)height/2-80, 0, 0, 95, 1000);
		glPopMatrix();
		this.numberField.drawTextBox();
		super.drawScreen(x, y, f);
		this.drawString(fontRendererObj, ((RealLifeProperties)mc.thePlayer.getExtendedProperties("RealLifeProps")).ThePhone.partner.name(),(int)this.width/2-38,(int)(this.height/2)-30,Color.red.getRGB());
	}

	@Override
	public void actionPerformed(GuiButton button){
		switch(button.id){
		case 10:
			if(numberField.getText().length()-1>=0){
			numberField.setText(numberField.getText().substring(0, numberField.getText().length()-1));
			}
		case 11:
			if(numberField.getText()!=null){
			if(numberField.getText()=="411"||numberField.getText()=="911"||numberField.getText()=="110")
				((RealLifeProperties)mc.thePlayer.getExtendedProperties("RealLifeProps")).ThePhone.partner=phonepartners.POLICE;
			if(numberField.getText()=="411"||numberField.getText()=="911"||numberField.getText()=="110")
				((RealLifeProperties)mc.thePlayer.getExtendedProperties("RealLifeProps")).ThePhone.partner=phonepartners.POLICE;
			}
		}
		
		if(LittleFunctions.numbers.contains(button.displayString))
			this.numberField.setText(numberField.getText()+button.displayString);
		}
	
    protected void keyTyped(char theChar, int id)
    {
    	super.keyTyped(theChar, id);
    	this.numberField.textboxKeyTyped(theChar, id);
    }
    
    protected void mouseMovedOrUp(int p_146286_1_, int p_146286_2_, int p_146286_3_)
    {
    	numberField.mouseClicked(p_146286_1_, p_146286_2_, p_146286_3_);
    }

    
}
