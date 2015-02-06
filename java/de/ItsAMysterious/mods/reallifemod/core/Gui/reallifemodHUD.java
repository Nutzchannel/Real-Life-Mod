package de.ItsAMysterious.mods.reallifemod.core.Gui;
import java.awt.Color;

import org.lwjgl.input.Keyboard;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import de.ItsAMysterious.mods.reallifemod.TLMBlocks;
import de.ItsAMysterious.mods.reallifemod.api.entity.properties.RealLifeProps;
import de.ItsAMysterious.mods.reallifemod.api.entity.properties.financialProps;
import de.ItsAMysterious.mods.reallifemod.client.ClientProxy;
import de.ItsAMysterious.mods.reallifemod.core.Configuration.TLMSettings;
import static org.lwjgl.opengl.GL11.*;

public class reallifemodHUD extends Gui
{
 private Minecraft mc;
private FontRenderer fontRenderObj;
private int width;
private World world;
private boolean showMessage=true;
private boolean showTips=true;
public boolean showAdditionalInfo=false;
  
  public reallifemodHUD(Minecraft mc)
  {
	  super();
    this.mc = mc;
    this.fontRenderObj=mc.fontRenderer;
    this.width=mc.displayWidth;	
    this.showMessage=true;
    this.showTips=true;
  }

  @SubscribeEvent(priority = EventPriority.HIGH)
  public void RenderProps(RenderGameOverlayEvent event)
  {		
	  if(Keyboard.isKeyDown(Keyboard.KEY_H))this.showMessage=false;
	    this.world=mc.theWorld;
	  if(event.type!=ElementType.ALL){
		  return;
	  }
	  glPushMatrix();
	  if(TLMSettings.showHud==true&&!mc.thePlayer.capabilities.isCreativeMode){
		EntityPlayer p=this.mc.thePlayer;
		RealLifeProps props=(RealLifeProps) p.getExtendedProperties("RealLifeProps");
		 if(Keyboard.isKeyDown(Keyboard.KEY_H)&&!p.capabilities.isCreativeMode)this.showTips=false;
		glEnable(GL_BLEND);
		glDepthMask(false);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		mc.getTextureManager().bindTexture(new ResourceLocation("reallifemod:textures/gui/guiThermometerEmpty.png"));
		drawTexturedModalRect(0, 0, 0, 0, 17, 68);
		mc.getTextureManager().bindTexture(new ResourceLocation("reallifemod:textures/gui/guiThermometer2.png"));
		drawTexturedModalRect(0, 64-64, 0, 64-64, 17, 68);
		if(!mc.thePlayer.capabilities.isCreativeMode){
		/*mc.getTextureManager().bindTexture(new ResourceLocation("reallifemod:textures/gui/hud/toiletEmpty.png"));
		this.drawTexturedModalRect(34, 54, 0, 0, 256, 256);
		mc.getTextureManager().bindTexture(new ResourceLocation("reallifemod:textures/gui/hud/thirstBottleFull.png"));
		this.drawTexturedModalRect(18,  (int)(36),  0,0, 256, 256);
		mc.getTextureManager().bindTexture(new ResourceLocation("reallifemod:textures/gui/hud/thirstBottle.png"));*/
		//this.drawRect(19, 36+(int)(32/100*props.thirst), 24, 37+(int)(32/100*props.thirst), Color.BLUE.getRGB());
		//this.drawTexturedModalRect(18, 36, 0, 0, 256, 256);
		//this.drawRect(22, (int) (42-props.thirst), 32, 63, Color.blue.getRGB());
		}
		if(this.world.getBlock(mc.objectMouseOver.blockX, mc.objectMouseOver.blockY, mc.objectMouseOver.blockZ)==TLMBlocks.boxbush){
			this.drawString(mc.fontRenderer, "Press E to collect herbs", 21,35,Color.red.getRGB());
		}else;
			//this.drawString(mc.fontRenderer, this.world.getBlock(mc.objectMouseOver.blockX, mc.objectMouseOver.blockY, mc.objectMouseOver.blockZ).getUnlocalizedName().split("tile.")[1], 21,35,Color.red.getRGB());
		String mood=props.ColorOfMood(props.mood)+props.mood.name();
		financialProps cash=(financialProps) mc.thePlayer.getExtendedProperties(financialProps.EXT_PROP_NAME);
		this.drawString(mc.fontRenderer,"Cash: "+cash.Cash+"$",20, 5,  Color.white.getRGB());
		this.drawString(mc.fontRenderer,"Mood: "+ mood,20, 15,  Color.white.getRGB());
		if(showTips==true){
			this.drawString(mc.fontRenderer, "Press tab to view additional information, like fatigue or courage!", 100, 0, Color.white.getRGB());
  			this.drawString(mc.fontRenderer, "Press "+EnumChatFormatting.YELLOW+"'H'"+EnumChatFormatting.WHITE+" to hide this info!", 100, 10, Color.white.getRGB());

		}
		Gui.drawRect(20, 25, 100, 35, Color.black.getRGB());
		Gui.drawRect(20, 25, 100-(int)(RealLifeProps.thirst/100*80), 35, Color.blue.getRGB());
		this.drawString(mc.fontRenderer, "water", 50, 26, Color.white.getRGB());
		Gui.drawRect(20,40,100,50,Color.black.getRGB());
		Gui.drawRect(20,40,20+(int) (RealLifeProps.Toilet/100*(80)),50,Color.orange.darker().darker().getRGB());
		this.drawString(mc.fontRenderer, "toilet", 50, 41, Color.white.getRGB());
		if(Keyboard.isKeyDown(Keyboard.KEY_TAB)){
			//this.drawTexturedModalRect(0, 68, 0, 0, 100, 100);
			Gui.drawRect(10, 70, 100, 80, Color.black.getRGB());
			Gui.drawRect(10, 70, 100, 80, Color.red.darker().getRGB());
			this.drawString(mc.fontRenderer, "fatigue", 37, 71, Color.white.getRGB());
			Gui.drawRect(10, 85, 100, 95, Color.black.getRGB());
			Gui.drawRect(10, 85, 100, 95, Color.pink.getRGB());
			this.drawString(mc.fontRenderer, "sex-apeal", 30, 86, Color.white.getRGB());
			Gui.drawRect(10, 100, 100, 110, Color.black.getRGB());
			Gui.drawRect(10, 100, 100, 110, Color.green.getRGB());
			this.drawString(mc.fontRenderer, "fear", 45, 101, Color.white.getRGB());

		}
  		if(p.capabilities.isCreativeMode&&this.showMessage==true){
  			this.drawString(mc.fontRenderer, "HUD is disabled in CreativeMode!", 0, 0, Color.white.getRGB());
  			this.drawString(mc.fontRenderer, "Press "+EnumChatFormatting.YELLOW+"'H'"+EnumChatFormatting.WHITE+" to hide this info!", 0, 10, Color.white.getRGB());
  		}
	  	}
	  
		glColor3f(1.0F,1.0F,1.0F);
		glDisable(GL_BLEND);
		glDepthMask(true);
		glPopMatrix();
  }

  
 }