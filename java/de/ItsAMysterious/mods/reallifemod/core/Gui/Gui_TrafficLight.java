package de.ItsAMysterious.mods.reallifemod.core.Gui;

import java.awt.Color;
import java.util.ArrayList;

import cpw.mods.fml.client.FMLClientHandler;
import de.ItsAMysterious.mods.reallifemod.TLMBlocks;
import de.ItsAMysterious.mods.reallifemod.api.Gui.RLM_Gui;
import de.ItsAMysterious.mods.reallifemod.api.Gui.selectableElement;
import de.ItsAMysterious.mods.reallifemod.core.rendering.TileEntitys.trafficlightTE;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import static org.lwjgl.opengl.GL11.*;

public class Gui_TrafficLight extends RLM_Gui{
	private int blockX;
	private int blockY;
	private int blockZ;
	private int mouseX, mouseY;
	
	public ArrayList<selectableElement>elements=new ArrayList<selectableElement>();
	
	public Gui_TrafficLight(int x, int y, int z){
		this.blockX=x;
		this.blockY=y;
		this.blockZ=z;
	}
	
	@Override
	public void drawScreen(int x, int y, float f){
		this.mc.renderEngine.bindTexture(new ResourceLocation("minecraft:textures/blocks/planks_oak.png"));
		this.drawTexturedModalRect(this.width/2-200, this.height/2-100, 0, 0, 400, 200);
		this.mc.renderEngine.bindTexture(new ResourceLocation("reallifemod:textures/gui/Display.png"));
		this.drawTexturedModalRect(this.width/2-180, this.height/2-80, 0,0,165, 100);
	//	this.drawRect(this.width/2-180, this.height/2-80, this.width/2-180+150,this.height/2-80+100, Color.black.getRGB());
		this.renderMap(FMLClientHandler.instance().getClient().theWorld,this.width/2-180+8, this.height/2-80+19, 150, 100);
		for(selectableElement selectable:elements){
			selectable.mouseOver( x, y);
			selectable.draw(mc, selectable.posX, selectable.posY);
		}
		super.drawScreen(x, y, f);
	}
	
	public void udateScreen(){
		//this.renderMap(FMLClientHandler.instance().getClient().theWorld,this.width/2-180, this.height/2-80);
		super.updateScreen();

	}
	
	@Override
	public void initGui(){
		super.initGui();
		for(int i=0;i<5;i++){
			this.buttonList.add(new GuiButton(i,this.width/2-180+150+5,this.height/2-80+20*i, 40, 20, "slot "+i));
		}
	}
	
	public void renderMap(World world, int xPos, int yPos, int width, int height){
		glPushMatrix();
		int intervalX=width/20;
		int intervalY=height/intervalX;
		int middleX=xPos+(width/2)-15;
		int middleY=yPos+(height/2)-23;
		for(int x=-10;x<10;x++){
			for(int z=-10;z<10;z++){
				for(int y=-1;y<1;y++){
					if(world.getBlock(blockX+x, blockY+y, this.blockZ+z) == TLMBlocks.blockTar){
					}else
					{
						//this.mc.renderEngine.bindTexture(new ResourceLocation(world.getBlock(blockX+x, blockY+y, this.blockZ+z).getBlockTextureFromSide(0).toString()));
					}
					
					if(world.getTileEntity(blockX+x, blockY+y, this.blockZ+z) instanceof trafficlightTE)
					{
						this.elements.add(new selectableElement(x*y, xPos+x*5, yPos+z*5, 5, 5));
					}
				}
			}
		}
		for(int i=0;i<20;i++){
			for(int j=0;j<height/intervalX;j++){
				Gui.drawRect(xPos+i*intervalX, yPos-15, xPos+i*intervalX+1, yPos-23+height, Color.white.getRGB());
				Gui.drawRect(xPos-5, yPos+j*intervalX-15, xPos+width-11, yPos+j*intervalX+1-15, Color.white.getRGB());
			}
		}
		
		for(selectableElement e:elements){
			e.draw(mc, xPos, yPos);
		}
		glTranslatef(xPos, yPos, 0);
		glPopMatrix();

	}
	
}
