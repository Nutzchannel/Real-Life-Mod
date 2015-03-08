package de.ItsAMysterious.mods.reallifemod.core.gui;

import java.awt.Color;
import java.util.Random;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.event.ClickEvent;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;

import org.lwjgl.input.Keyboard;

import de.ItsAMysterious.mods.reallifemod.api.handlers.SpeechHandler;

public class guiHelperBot extends GuiScreen{
	public GuiTextField chatline;
	private SpeechHandler handler;
	private boolean saidname=false;
	public boolean warnWhenNight;
	private int voicelevel=0;
	
	public guiHelperBot() {
		super();
		this.handler=new SpeechHandler();
		Keyboard.enableRepeatEvents(true);
	}
	
	@Override
	public void initGui(){
		super.initGui();
		this.chatline=new GuiTextField(this.fontRendererObj, (int)(this.width/2-150), this.height/2-52, 300, 15);
		this.chatline.setCanLoseFocus(true);
		this.chatline.setFocused(true);
		chatline.setMaxStringLength(200);
		this.buttonList.add(new GuiButton(0, this.width/2-100, this.height/2, "Ask your robot!"));
		this.buttonList.add(new GuiButton(1, this.width/2-100, this.height/2+25, "Let it speak!"));
		this.buttonList.add(new GuiButton(2, this.width/2-100, this.height/2+50, 100,20, "Voice up"));
		this.buttonList.add(new GuiButton(3, this.width/2, this.height/2+50, 100,20, "Set remembrance"));

	}
	
	@Override
	public void drawScreen(int x, int y,float f){
		this.chatline.drawTextBox();
		this.drawString( fontRendererObj,"Voicelevel: "+this.voicelevel,this.width/2-100, this.height/2-25, Color.white.getRGB());
		super.drawScreen(x, y, f);
	}

	@Override
	public void actionPerformed(GuiButton button){
		if(button.id==0){
			
		if((chatline.getText().toLowerCase().contains("money"))){
			this.handler.speechSynth(	1F, 7.0F, 16.0F, 0, "You can earn money by mining ores or by Killing mobs. You can get up to 1000$ per collected ore!");
			
		}else
		if((chatline.getText().toLowerCase().contains("buy"))){
			this.handler.speechSynth(	1F, 7.0F, 16.0F, voicelevel, "To buy something you have to craft a computer out of TWO circuit boards, FIVE iron-ingots and TWO redstone!");
			
		}
		else
		if((chatline.getText().toLowerCase().contains("circuitboard"))){
			this.handler.speechSynth(	1F, 7.0F, 16.0F, 0, "Kill Zombies or robots to get Circuitboards!");
			
		}else
		
		if((chatline.getText().toLowerCase().contains("shut up"))){
			this.handler.speechSynth(	1F, 7.0F, 16.0F, 0, "Nope!");
		}else
		
		if((chatline.getText().toLowerCase().contains("like"))){
			if(chatline.getText().toLowerCase().contains("don't")&&chatline.getText().toLowerCase().contains("you")){
				this.handler.speechSynth(	2F, 7.0F, 25.0F, 0.0F, "Stop w"
						+"eisting my Time!");
			}
			if(!chatline.getText().toLowerCase().contains("don't")&&chatline.getText().toLowerCase().contains("you")){
				this.handler.speechSynth(	2F, 7.0F, 25.0F, 0, "That's nice!");
			}
			
			if(!chatline.getText().toLowerCase().contains("don't")&&chatline.getText().toLowerCase().contains("me")){
				this.handler.speechSynth(	2F, 7.0F, 25.0F, 20, "Of course i do!");
			}
		}
		else
		if(chatline.getText().toLowerCase().contains("what")&&
				chatline.getText().toLowerCase().contains("can")){
			if(chatline.getText().toLowerCase().contains("you")){
				this.handler.speechSynth(	2F, 7.0F, 25.0F, 20, "Many things!");
			}
			
			if(chatline.getText().toLowerCase().contains("i")&&chatline.getText().toLowerCase().contains("do")){
				Random rand = new Random();
				int sound=0;
				sound=rand.nextInt(6);
				switch(sound+1){
				case 1:
					this.handler.speechSynth(	2F, 7.0F, 25.0F, 20, "Go in your favorite mine, but don't forget the torches again!");
					break;
				case 2:
					this.handler.speechSynth(	2F, 7.0F, 25.0F, 20, "I don't know everything!");
					break;
					
				case 3:
					this.handler.speechSynth(	2F, 7.0F, 25.0F, 20, "Build a new House!");
					
				case 4:
					this.handler.speechSynth(	2F, 7.0F, 25.0F, 20, "Wait for some monsters and kill them!");
					
				case 5:
					this.handler.speechSynth(	2F, 7.0F, 25.0F, 20, "Dig a new mine!");
					
				case 7:
					this.handler.speechSynth(	2F, 7.0F, 25.0F, 20, "Take a nap!");
				
				}
				
			}
		}else
		
		if((chatline.getText().toLowerCase().contains("what does the fox say"))){
			this.handler.speechSynth(	2F, 7.0F, 25.0F, 35, "Ring ding ding ding dinkeding, gering ding ding ding dinkeding!");
			
		}else
		
		if((chatline.getText().toLowerCase().contains("radio"))){
			this.handler.speechSynth(	1F, 7.0F, 16.0F, 0, "The modmaker says, he is sorry, but the radio can not be used at the moment. He "
					+ "is working on easier way to play songs and also CD's in Minecraft.");
		}
		else
		if((chatline.getText().toLowerCase().equals("what is your name?"))){
			this.handler.speechSynth(	1F, 7.0F, 16.0F, 0, "My name is Zanor! And what is your name?");
		}else
		
		if((chatline.getText().toLowerCase().contains("my name is"))){
			this.handler.speechSynth(	1F, 7.0F, 16.0F, 0, "Hello "+chatline.getText().split("is ")[1]+"!");
			this.saidname=true;
		}else
		
		if((chatline.getText().toLowerCase().contains("bye"))){
			this.handler.speechSynth(	1F, 7.0F, 16.0F, 0, "See you later!");
		}else
		
		if((chatline.getText().toLowerCase().contains("what can you"))){
			this.handler.speechSynth(	1F, 7.0F, 16.0F, 0, "I can speak with you and you can speak with me!");
		}else
		
		if((chatline.getText().toLowerCase().contains("hello")||chatline.getText().toLowerCase().contains("hi"))){
			this.handler.speechSynth(	1F, 7.0F, 16.0F, 0, "Hello! What's your name?");
		}
		else
		if((chatline.getText().toLowerCase().contains("ask you something"))){
			this.handler.speechSynth(	1F, 7.0F, 16.0F, 0, "Ask me any question you want!");
		}else
		
		if((chatline.getText().toLowerCase().contains("fuck"))){
			this.handler.speechSynth(	1F, 7.0F, 16.0F, 0, "Im a robot, not a sextoy!");
		}else
		
		if((chatline.getText().toLowerCase().contains("you")&&chatline.getText().toLowerCase().contains("asshole"))){
			this.handler.speechSynth(	1F, 7.0F, 16.0F, 0, "Stop swearing !");
		}else
			
		if((chatline.getText().toLowerCase().contains("you")&&chatline.getText().toLowerCase().contains("hate"))){
			this.handler.speechSynth(	1F, 7.0F, 16.0F, 0, "I don't have any feelings! You don't have to love me!");
		}else
		
		if((chatline.getText().toLowerCase().contains("what")&&chatline.getText().toLowerCase().contains("you")&&chatline.getText().toLowerCase().contains("doing"))){
			this.handler.speechSynth(	1F, 7.0F, 16.0F, 0, "Waiting for some questions i can answer better.");
		}else
		
		if((chatline.getText().toLowerCase().contains("how")&&chatline.getText().toLowerCase().contains("are")&&chatline.getText().toLowerCase().contains("you"))){
			this.handler.speechSynth(	1F, 7.0F, 16.0F, 0, "Im fine, thanks! And you ?");
		}else
		
		if((chatline.getText().toLowerCase().contains("i")&&chatline.getText().toLowerCase().contains("love")&&chatline.getText().toLowerCase().contains("you"))){
			this.handler.speechSynth(	1F, 7.0F, 16.0F, 0, "That's nice ! Just ask me when you need something! Ok?");
		}else
		
		if((chatline.getText().toLowerCase().contains("me")&&chatline.getText().toLowerCase().contains("too"))){
			this.handler.speechSynth(	1F, 7.0F, 16.0F, 0, "That's nice !");
		}else
		
		if((chatline.getText().toLowerCase().contains("suck my dick"))){
			this.handler.speechSynth(	1F, 7.0F, 16.0F, 0, "No i don't, do it on your own!");
		}else
		
		if((chatline.getText().toLowerCase().contains("are you human"))){
			this.handler.speechSynth(	1F, 7.0F, 16.0F, 0, "No, i am a robot!");
		}else
		
		if((chatline.getText().toLowerCase().contains("real life mod")&&chatline.getText().toLowerCase().contains("cool"))){
			this.handler.speechSynth(	1F, 7.0F, 16.0F, 0, "The modmakers say:<<THANK YOU! Whe are doing our best to make it COOL !>>.");
		}else
		
		if((chatline.getText().toLowerCase().contains("real life mod")&&chatline.getText().toLowerCase().contains("don't like"))){
			this.handler.speechSynth(	1F, 7.0F, 16.0F, 0, "Well, why have you downloaded it then ?");
		}else
		
		if((chatline.getText().toLowerCase().contains("when")&&chatline.getText().toLowerCase().contains("will")&&chatline.getText().toLowerCase().contains("mod")&&chatline.getText().toLowerCase().contains("update"))){
			this.handler.speechSynth(	1F, 7.0F, 16.0F, 0, "Next update will be on Saturday the 7th of March 2015 at 6 pm, german time!");
		}else
		
		if((chatline.getText().toLowerCase().contains("who ")&&chatline.getText().toLowerCase().contains("modmakers"))){
			this.handler.speechSynth(	1F, 7.0F, 16.0F, 0, "ItsAMysterious is the leed programmer, he had the idea and started coding the hole thing. He also makes some 3D models. Treedent Killer is the graphics designer and helps with websitehosting, 3D-moddeling and the Real life craft-server. El ar a 10 is making minecraft-styled 3d models for version Alpha 0.14!");
			return;
		}else
		
		if((chatline.getText().toLowerCase().contains("penis")||chatline.getText().toLowerCase().contains("dick"))){
			this.handler.speechSynth(	1F, 7.0F, 16.0F, 0, "Are you a boy? Then stop asking about penises, because if you do so, you must be gay!");
			return;
		}else
		
		if((chatline.getText().toLowerCase().contains("against")&&chatline.getText().toLowerCase().contains("gay"))){
			this.handler.speechSynth(	1F, 7.0F, 16.0F, 0, "No, but if you are not gay, you should not ask about penises!");	
		}else
		
		if((chatline.getText().toLowerCase().contains("what is planned"))){
			this.handler.speechSynth(	1F, 7.0F, 16.0F, 0, "Have a look at the link in chat, to see which features are planned for the Real Life Mod!");
			IChatComponent url = new ChatComponentText("http://www.minecraftforum.net/forums/mapping-and-modding/minecraft-mods/wip-mods/2196915-real-life-mod-updated-alpha-0-12-realistic");
			url.getChatStyle().setChatClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "http://www.minecraftforum.net/forums/mapping-and-modding/minecraft-mods/wip-mods/2196915-real-life-mod-updated-alpha-0-12-realistic"));
			Minecraft.getMinecraft().thePlayer.addChatComponentMessage(url);
		}else
		if((chatline.getText().toLowerCase().contains("what is the meaning")&&chatline.getText().toLowerCase().contains("of life")&&chatline.getText().toLowerCase().contains("universe")&&chatline.getText().toLowerCase().contains("everything"))){
			this.handler.speechSynth(	2F, 7.0F, 25.0F, 35, "42");
		}
		long time = mc.theWorld.getWorldTime();
		int hours = (int) Math.floor(time / 1000);
		int minutes = (int) ((time % 1000) / 1000.0 * 60);
		 
		if((chatline.getText().toLowerCase().contains("time")&&chatline.getText().toLowerCase().contains("is"))){
			this.handler.speechSynth(	2F, 7.0F, 25.0F, 35, String.valueOf(hours+" hours and"+minutes+"minutes"));
		}
		}
		if(button.id==1){
			this.handler.speechSynth(	1F, 7.0F, 16.0F, 0, chatline.getText());

		}
		if(button.id==2){
			this.voicelevel++;
		}
		

	}
		
	
    protected void keyTyped(char theChar, int id)
    {
    	super.keyTyped(theChar, id);
    	if(id==Keyboard.KEY_RETURN)this.actionPerformed((GuiButton)this.buttonList.get(0));
    	if(id==Keyboard.KEY_RETURN)this.actionPerformed((GuiButton)this.buttonList.get(0));

    	this.chatline.textboxKeyTyped(theChar, id);
    }
    
    protected void mouseMovedOrUp(int p_146286_1_, int p_146286_2_, int p_146286_3_)
    {
    	chatline.mouseClicked(p_146286_1_, p_146286_2_, p_146286_3_);
    }

    
}
