package de.ItsAMysterious.mods.reallifemod.core.gui.mypad;

import java.awt.Color;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.input.Keyboard;

import cpw.mods.fml.client.FMLClientHandler;

public class GuiPad_Main extends GuiScreen{

	private static ResourceLocation Background=new ResourceLocation("tlm:textures/gui/GuiSteckbrief.png");
	private GuiButton NameChanging;
    private Entity thePlayer;
    private int GuiLifePage;
	private int middleX;
	private int middleY;

	
	@Override
	public void updateScreen(){
        super.updateScreen();
        this.allowUserInput = true;
        this.thePlayer=FMLClientHandler.instance().getClientPlayerEntity();
        Keyboard.enableRepeatEvents(true);
	}
	
	@Override
	public void initGui()
	{
		super.initGui();
		this.buttonList.clear();
		NameChanging=new GuiButton(4, width/2-256,60, 75, 20, "Namen aendern");
		if(GuiLifePage==0){
	    this.buttonList.add(NameChanging);}
		this.buttonList.add(new GuiButton(0, width/2-58, height-55,20,20,"<"));
		this.buttonList.add(new GuiButton(1, width/2+48, height-55,20,20,">"));
	    this.buttonList.add(new GuiButton(3, width/2-32, height-55, 64, 20, "OK"));
	}
	
	@Override
	public boolean doesGuiPauseGame(){
		return false;
	}
	
	@Override
	public void drawScreen(int p1, int p2, float p3) 
    {
		this.mc.getTextureManager().bindTexture(Background);
		this.drawTexturedModalRect( this.width/2-64, this.height/2-120, 0, 0,256, 256);
	    if(GuiLifePage==0){
	    	NameChanging.visible=true;
		    this.drawCenteredString(this.fontRendererObj, Minecraft.getMinecraft().thePlayer.getDisplayName()+"'s Health Data", width/2+20, 30, Color.white.getRGB());
		    //this.drawProperty("Name", NameRegistry.getEntityName(this.mc.thePlayer), width/2-58, height/2-100);
		    //this.drawProperty("Surname", NameRegistry.getEntitySurName(this.mc.thePlayer), width/2-58, height/2-90);
		    //this.drawProperty("Age",LittleFunctions.calculateAge(mc.thePlayer.getAge()*400),width/2-58,height/2-80);
		   //this.drawProperty("Date of Birth",,width/2-58,height/2-80);

	    }
	    else 
	    if(GuiLifePage==1){
			this.drawCenteredString(fontRendererObj, "World Data", width/2, 35, Color.WHITE.getRGB());
		    this.drawProperty("Time",String.valueOf(mc.theWorld.getWorldTime()),width/2-64,60);
		    this.drawProperty("Position",mc.thePlayer.getPosition(0F).toString(),width/2-64,70);
	    	NameChanging.visible=false;
	    }
	    super.drawScreen(p1, p2, p3);
    }
	
    @Override
	protected void actionPerformed(GuiButton button) 
	{
    	if(button.id==1){
    		if(GuiLifePage<1)
    		GuiLifePage++;
    	}
    	if(button.id==0){
	    	if(GuiLifePage>0)
	    		GuiLifePage--;
    	}
		if(button.id==3)
		{
			 this.mc.displayGuiScreen(null);
			 this.mc.setIngameFocus();
		}
		if(button.id==4){
		}
		super.actionPerformed(button);
	}
    
    public void drawProperty(String Property,String Value,int posX,int posY)
    {
    	this.drawString(fontRendererObj, Property+":", posX, posY, Color.LIGHT_GRAY.getRGB());
    	if(Value.length()>30){
    		drawString(fontRendererObj, Value.substring(0,30).toString(), (posX+Property.length()*6)+36, posY, Color.LIGHT_GRAY.getRGB());
    		drawString(fontRendererObj, Value.substring(30,Value.length()).toString(), (posX+Property.length()*6)+3, posY+10, Color.LIGHT_GRAY.getRGB());
    	}else
    		this.drawString(fontRendererObj, Value, (posX+Property.length()*6)+3, posY, Color.LIGHT_GRAY.getRGB());

    }
}

