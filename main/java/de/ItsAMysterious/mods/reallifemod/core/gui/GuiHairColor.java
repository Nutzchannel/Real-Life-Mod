package de.ItsAMysterious.mods.reallifemod.core.gui;

import static org.lwjgl.opengl.GL11.*;

import java.awt.Color;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;
import cpw.mods.fml.client.config.GuiSlider;
import de.ItsAMysterious.mods.reallifemod.api.entity.properties.RealLifeProperties;
import de.ItsAMysterious.mods.reallifemod.api.gui.RLM_Gui;

public class GuiHairColor extends RLM_Gui{
	public static GuiSlider red,green,blue;
	private GuiScreen previousScreen;
	private IModelCustom modelHead;
	private int middleX, mouseX;
	private int middleY, mouseY;
	public GuiHairColor(guiCharacterSetup prevScreen) {
		this.previousScreen=prevScreen;
		this.modelHead = AdvancedModelLoader.loadModel(new ResourceLocation("reallifemod:models/characterhead.obj"));
	}
	
	public void initGui(){
		red=new GuiSlider(0, width-115, 30, 100, 20, "red:  ", " ", 0, 255,((RealLifeProperties)mc.thePlayer.getExtendedProperties("RealLifeProperties")).haircolor.x, true, true);
		green=new GuiSlider(1, width-115, 55, 100, 20, "green:  ", " ", 0, 255,((RealLifeProperties)mc.thePlayer.getExtendedProperties("RealLifeProperties")).haircolor.y, true, true);
		blue=new GuiSlider(2, width-115, 80, 100, 20, "blue:  ", " ", 0, 255,((RealLifeProperties)mc.thePlayer.getExtendedProperties("RealLifeProperties")).haircolor.z, true, true);
		this.buttonList.add(new GuiButton(4,width-90, height-25,75,20,"OK"));
		this.buttonList.add(red);
		this.buttonList.add(green);
		this.buttonList.add(blue);
	}
	public void updateScreen(){
		((RealLifeProperties)mc.thePlayer.getExtendedProperties("RealLifeProperties")).haircolor.x=(float) red.getValue();
		((RealLifeProperties)mc.thePlayer.getExtendedProperties("RealLifeProperties")).haircolor.y=(float) green.getValue();
		((RealLifeProperties)mc.thePlayer.getExtendedProperties("RealLifeProperties")).haircolor.z=(float) blue.getValue();
	}
	public void drawScreen(int par1, int par2, float par3){
		guiCharacterSetup.renderEntity(100, this.height+430, 300,0, 0,
				mc.thePlayer);
		Color color=new Color(red.getValueInt(),green.getValueInt(),blue.getValueInt());
		//drawRect(0, 0, 100, 100,color.getRGB());
		glPushMatrix();
		glEnable(GL_BLEND);
		glBlendFunc(GL_CONSTANT_COLOR, GL_ONE_MINUS_CONSTANT_COLOR);
		glColor3d(red.getValue(),green.getValue(),blue.getValue());
		glPopMatrix();
		glPushMatrix();
		glTranslatef(width/2, height/2, 1);
		glScalef(100.0F, 100.0F, 100.0F);
		this.modelHead.renderAll();
		glPopMatrix();
		super.drawScreen(par1, par2, par3);
	}
	
	public void actionPerformed(GuiButton  button){
		if(button.id==4){
			((RealLifeProperties)mc.thePlayer.getExtendedProperties("RealLifeProperties")).haircolor.x=(float) red.getValue();
			((RealLifeProperties)mc.thePlayer.getExtendedProperties("RealLifeProperties")).haircolor.y=(float) green.getValue();
			((RealLifeProperties)mc.thePlayer.getExtendedProperties("RealLifeProperties")).haircolor.z=(float) blue.getValue();
			mc.displayGuiScreen(this.previousScreen);

		}
	}
	
	public static void renderEntity(int p_147046_0_, int p_147046_1_,
			int p_147046_2_, float rot, float p_147046_4_,
			EntityLivingBase p_147046_5_) {
		GL11.glPushMatrix();
		GL11.glTranslatef(p_147046_0_, p_147046_1_, 100.0F);
		GL11.glScalef((-p_147046_2_), p_147046_2_, p_147046_2_);
		GL11.glRotatef(180, 0.0F, 0.0F, 1.0F);
		float f2 = p_147046_5_.renderYawOffset;
		float f3 = 0;//rotationYaw
		float f4 = p_147046_5_.rotationPitch;
		float f5 = 0;
		float f6 = 0;
		GL11.glRotatef(135.0F, 0.0F, 1.0F, 0.0F);
		GL11.glRotatef(rot, 0.0F, 1.0F, 0.0F);
		RenderHelper.enableStandardItemLighting();
		GL11.glRotatef(-135, 0.0F, 1.0F, 0.0F);
		 GL11.glRotatef(-((float)Math.atan((double)(p_147046_4_ / 40.0F))) *
		 20.0F, 1.0F, 0.0F, 0.0F);
		p_147046_5_.renderYawOffset = 0;
		p_147046_5_.rotationYaw = 180;
		p_147046_5_.rotationPitch = -((float) Math.atan(p_147046_4_ / 40.0F)) * 20.0F;
		p_147046_5_.rotationYawHead = 0;
		p_147046_5_.prevRotationYawHead = 0;
		GL11.glTranslatef(0.0F, p_147046_5_.yOffset, 0.0F);
		RenderManager.instance.playerViewY = rot;
		RenderManager.instance.renderEntityWithPosYaw(p_147046_5_, 0.0D, 0.0D,
				0.0D, 0.0F, 0.0F);
		p_147046_5_.renderYawOffset = f2;
		p_147046_5_.rotationYaw = f3;
		p_147046_5_.rotationPitch = f4;
		p_147046_5_.prevRotationYawHead = 0;
		p_147046_5_.rotationYawHead = 0;
		GL11.glPopMatrix();
		RenderHelper.disableStandardItemLighting();
		GL11.glDisable(GL12.GL_RESCALE_NORMAL);
		OpenGlHelper.setActiveTexture(OpenGlHelper.lightmapTexUnit);
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		OpenGlHelper.setActiveTexture(OpenGlHelper.defaultTexUnit);
	}

	@Override
	public void handleMouseInput() {
		super.handleMouseInput();
		this.mouseX = -(Mouse.getEventX() * this.width / this.mc.displayWidth);
		this.mouseY = -(this.height - Mouse.getEventY() * this.height
				/ this.mc.displayHeight - 1);
	}
}
