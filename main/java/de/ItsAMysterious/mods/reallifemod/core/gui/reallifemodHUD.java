package de.ItsAMysterious.mods.reallifemod.core.gui;
import static org.lwjgl.opengl.GL11.GL_BLEND;
import static org.lwjgl.opengl.GL11.GL_ONE_MINUS_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.GL_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.glBlendFunc;
import static org.lwjgl.opengl.GL11.glColor3f;
import static org.lwjgl.opengl.GL11.glDepthMask;
import static org.lwjgl.opengl.GL11.glDisable;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushMatrix;

import java.awt.Color;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;

import org.lwjgl.input.Keyboard;

import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import de.ItsAMysterious.mods.reallifemod.api.entity.properties.RealLifeProperties;
import de.ItsAMysterious.mods.reallifemod.api.entity.properties.financialProps;
import de.ItsAMysterious.mods.reallifemod.core.config.TLMSettings;

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

  @SubscribeEvent(priority = EventPriority.NORMAL)
  public void RenderProps(RenderGameOverlayEvent event)
  {		
	  //event=new RenderGameOverlayEvent(1,new ScaledResolution(mc, mc.displayWidth, mc.displayHeight), Minecraft.getMinecraft().mouseHelper.deltaX, Minecraft.getMinecraft().mouseHelper.deltaY);
	  if(Keyboard.isKeyDown(Keyboard.KEY_H))this.showMessage=false;
	    this.world=mc.theWorld;
	  if(event.type!=ElementType.ALL){
		  return;
	  }
	  glPushMatrix();
	  if(!mc.thePlayer.capabilities.isCreativeMode){
		EntityPlayer p=mc.thePlayer;
		RealLifeProperties props=(RealLifeProperties) p.getExtendedProperties("RealLifeProperties");
		 if(Keyboard.isKeyDown(Keyboard.KEY_H)&&!p.capabilities.isCreativeMode)this.showTips=false;
		glEnable(GL_BLEND);
		glDepthMask(false);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		mc.getTextureManager().bindTexture(new ResourceLocation("reallifemod:textures/gui/guiThermometerEmpty.png"));
		drawTexturedModalRect(0, 0, 0, 0, 17, 68);
		mc.getTextureManager().bindTexture(new ResourceLocation("reallifemod:textures/gui/guiThermometer2.png"));
		drawTexturedModalRect(0, 64-64, 0, 64-64, 17, 68);

		/*if(this.world.getBlock(mc.objectMouseOver.blockX, mc.objectMouseOver.blockY, mc.objectMouseOver.blockZ)==TLMBlocks.boxbush){
			//this.drawString(mc.fontRenderer, "Press E to collect herbs", 21,35,Color.red.getRGB());
		}else;*/
		String mood=props.ColorOfMood(props.mood)+props.mood.name();
		this.drawString(mc.fontRenderer,"Cash: "+((financialProps)p.getExtendedProperties("financialProps")).Cash+"$",20, 5,  Color.white.getRGB());
		drawString(mc.fontRenderer,"Mood: "+ mood,20, 15,  Color.white.getRGB());
		drawRect(4, (int)(0.775F*(40-((RealLifeProperties)mc.thePlayer.getExtendedProperties("RealLifeProperties")).Temperature)), 13, (int)(0.775F*(40-((RealLifeProperties)mc.thePlayer.getExtendedProperties("RealLifeProperties")).Temperature))+1, Color.blue.getRGB());
		if(showTips==true){
			this.drawString(mc.fontRenderer, "Press tab to view additional information, like fatigue or courage!", 100, 0, Color.white.getRGB());
  			this.drawString(mc.fontRenderer, "Press "+EnumChatFormatting.YELLOW+"'H'"+EnumChatFormatting.WHITE+" to hide this info!", 100, 10, Color.white.getRGB());

		}
		//this.drawString(fontRenderObj,"_",4, (int)(32+(62/80)*(40-((financialProps)p.getExtendedProperties("financialProps")).Temperature)), Color.white.getRGB());
		drawRect(20, 25, 100, 35, Color.black.getRGB());
		drawRect(20, 25, 100-(int)(((RealLifeProperties)mc.thePlayer.getExtendedProperties("RealLifeProperties")).thirst/100*80), 35, Color.blue.getRGB());
		drawString(mc.fontRenderer, "water", 50, 26, Color.white.getRGB());
		drawRect(20,40,100,50,Color.black.getRGB());
		drawRect(20,40,20+(int) (((RealLifeProperties)mc.thePlayer.getExtendedProperties("RealLifeProperties")).Toilet/100*(80)),50,Color.orange.darker().darker().getRGB());
		drawString(mc.fontRenderer, "toilet", 50, 41, Color.white.getRGB());
		if(Keyboard.isKeyDown(Keyboard.KEY_TAB)){
			drawRect(10, 70, 100, 80, Color.black.getRGB());
			drawRect(10, 70, 100, 80, Color.red.darker().getRGB());
			drawString(mc.fontRenderer, "fatigue", 37, 71, Color.white.getRGB());
			Gui.drawRect(10, 85, 100, 95, Color.black.getRGB());
			Gui.drawRect(10, 85, 100, 95, Color.pink.getRGB());
			drawString(mc.fontRenderer, "sex-apeal", 30, 86, Color.white.getRGB());
			Gui.drawRect(10, 100, 100, 110, Color.black.getRGB());
			Gui.drawRect(10, 100, 100, 110, Color.green.getRGB());
			drawString(mc.fontRenderer, "fear", 45, 101, Color.white.getRGB());

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
  @SubscribeEvent
  public void renderRefreshContent(RenderGameOverlayEvent event){
	  
  }


  
 }