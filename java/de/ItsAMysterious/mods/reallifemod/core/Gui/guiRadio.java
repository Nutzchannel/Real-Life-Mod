package de.ItsAMysterious.mods.reallifemod.core.Gui;

import java.awt.Color;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.vecmath.Vector3f;

import org.lwjgl.input.Mouse;

import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.audio.SoundHandler;
import net.minecraft.client.audio.SoundRegistry;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import de.ItsAMysterious.mods.reallifemod.TLM;
import de.ItsAMysterious.mods.reallifemod.api.Gui.RLM_Gui;
import de.ItsAMysterious.mods.reallifemod.core.rendering.TileEntitys.radioTE;

public class guiRadio extends RLM_Gui{
	private int lengthinmilliseconds, volume=0;
	private File track=new File(TLM.Dir, "assets/reallifemod/sounds");
	private List<ResourceLocation>tracks=new ArrayList<ResourceLocation>();
	private int currentsong;
	private Vector3f blockPosition;
	private Entity listener;
	private PositionedSoundRecord playing;
	private radioTE tile;
    private final SoundRegistry sndRegistry = new SoundRegistry();
    private int buttonsYPos;
	private int mouseY;
	private Color color=Color.red;

	@Override
	public void drawScreen(int x, int y, float f){
		//return this.minecraft.getResourcePackRepository().toString().replace("resourcepacks", "Images");
		this.drawGradientRect( 0, 30, this.width, this.height/2, Color.black.getRGB(), Color.black.getTransparency());
		this.drawGradientRect( 0, this.height/2, this.width, this.height/2, Color.black.getTransparency(), Color.black.getRGB());
		this.drawGradientRect(this.width-25, this.height-30-((840/100*this.volume/4)), this.width-15, this.height-30,Color.red.getRGB(),Color.blue.getTransparency());
		this.drawString(this.volume+"%", this.width-25, this.height/2, Color.white.getRGB());
				if(tracks.isEmpty()){
			this.drawCenteredString("There are no .ogg files in the directory '"+track+"' at the moment!", this.width/2, 35, Color.red.getRGB());
		}
		else
		for(int i=0;i<tracks.size();i++)
		{
			this.drawString(this.tracks.get(i).toString(), 5, 30+i*10, Color.white.getRGB());
		}
		this.drawString(track.toString(), 5,10, Color.white.getRGB());
		this.drawCenteredString("Files in Directory", this.width/2,20, Color.green.getRGB());
		this.drawString(length(), this.width-54, this.buttonsYPos+4, Color.white.getRGB());
		this.drawProgressBar(110, this.buttonsYPos+4,this.width/2+100, this.buttonsYPos+14,0F,100.0F,100);
		if(!this.tracks.isEmpty()&&this.currentsong<this.tracks.size())
		{
				this.drawCenteredString("CurrentSong: "+this.tracks.get(this.currentsong).toString(),this.width/2, this.height-21,Color.green.getRGB());
		}
		super.drawScreen(x, y, f);
	}
	
	@Override
	public void updateScreen(){
		super.updateScreen();
        if(mouseY>this.height-25){
        	if(this.buttonsYPos>this.height-25)
        	{
        		this.buttonsYPos--;
        	}
        }
        	
        if(mouseY<this.height-25)
        	if(this.buttonsYPos<this.height)
        	{
        		this.buttonsYPos++;
        	}

        if(this.volume<0)this.volume=0;
        if(this.volume>100)this.volume=100;
        ///Minecraft.getMinecraft().getSoundHandler().setSoundLevel(SoundCategory.MUSIC, this.volume);
		GuiButton b=(GuiButton) this.buttonList.get(0);
		b.yPosition=this.buttonsYPos;
		GuiButton c=(GuiButton) this.buttonList.get(1);
		c.yPosition=this.buttonsYPos;
		GuiButton d=(GuiButton) this.buttonList.get(2);
		d.yPosition=this.buttonsYPos;
	}
	
	@Override
	public void initGui(){
		super.initGui();
		this.buttonsYPos=this.height-25;
		this.buttonList.add(new GuiButton(0,5, this.buttonsYPos, 30,20,"Play"));
		this.buttonList.add(new GuiButton(1,40, this.buttonsYPos, 30,20,"<<"));
		this.buttonList.add(new GuiButton(2,75, this.buttonsYPos, 30,20,">>"));
		this.getFilesInFolder();
	}
	
	@Override
	public void actionPerformed(GuiButton b){
		if(b.id==0){
			if(!this.tracks.isEmpty()&&this.currentsong<this.tracks.size()-1)
			{
				//mc.getSoundHandler().playSound(PositionedSoundRecord.func_147673_a((ResourceLocation)));
				this.playSong(new ResourceLocation("reallifemod:"+(String) this.tracks.get(this.currentsong).getResourcePath().toString().subSequence(0, this.tracks.get(this.currentsong).getResourcePath().toString().length()-4)), mc.thePlayer);
				System.out.println(this.tracks.get(this.currentsong).toString());
			}
		}
		if(b.id==1){
			if(currentsong>0)
			{
				currentsong--;
			}
		}
		if(b.id==2){
			if(currentsong<tracks.size()){
				currentsong++;
			}
		}
		super.actionPerformed(b);
	}
	public String length(){
		int seconds=0;
		int minutes=0;
		int hours=0;
		seconds=this.lengthinmilliseconds/100-minutes*60;
		minutes=seconds-hours*60;
		hours=hours+minutes/60;
		if(this.lengthinmilliseconds!=0){
		return hours+"."+minutes+"."+seconds;}
		else return
			"0.00.00";
	}
	
	public void getFilesInFolder(){
		if(track.exists())
		{
		for(final File fileEntry:track.listFiles()){
			System.out.println(fileEntry.getName());
			if(fileEntry.isFile()&&fileEntry.getName().endsWith(".ogg")){
				this.tracks.add(new ResourceLocation(fileEntry.getName()));
			}
		}
		}else
		{
			track.mkdir();
			return;
		}
	}

	public void drawProgressBar( int xPos, int yPos,int width, int height, float min, float max, float value){
		Gui.drawRect(xPos, yPos, xPos+width, height, Color.black.getRGB());
		Gui.drawRect(xPos, yPos, (int) (this.width-((xPos+width)-(width/100)*value)), height, Color.white.getRGB());	
	}
	
	public void stopSounds(){
		SoundHandler manager=mc.getSoundHandler();
		manager.stopSounds();
	}
	public void playSong(ResourceLocation music, Entity entity)
	{
		this.stopSounds();
		this.listener=entity;
		SoundHandler manager=Minecraft.getMinecraft().getSoundHandler();
		manager.resumeSounds();
		this.playing=PositionedSoundRecord.func_147675_a(music,(float) entity.posX,(float) entity.posY,(float) entity.posZ);
		manager.playSound(this.playing);
	}
	
	public void hideButtons(){
		for(int i=0; i<20;i++){
			this.buttonsYPos+=1;
		}
	}
	
	public void showButtons(){
		for(int i=0; i<20;i++){
			this.buttonsYPos-=1;
		}
	}
	
	@Override
	public void handleMouseInput()
	{
		super.handleMouseInput();
	        int i = Mouse.getEventX() * this.width / this.mc.displayWidth;
	        int j = this.height - Mouse.getEventY() * this.height / this.mc.displayHeight - 1;
	        int k = Mouse.getEventButton();
	        this.mouseY=j;
	        int v=Mouse.getDWheel()/100;
	        	this.volume+=v;

	}
	
    @Override
	protected void keyTyped(char p_73869_1_, int p_73869_2_)
    {
    	super.keyTyped(p_73869_1_, p_73869_2_);
    }
       

}
