package de.ItsAMysterious.mods.reallifemod.core.gui;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.IModelCustom;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import de.ItsAMysterious.mods.reallifemod.core.entitys.npcs.EntityLanz;

public class GuiLanz extends GuiScreen {
	public static int TextureID=0;
        public static final int GUI_ID = 20;
        private static final ResourceLocation Tacho = new ResourceLocation("reallifemod:textures/gui/geschwstufe_"+String.valueOf(TextureID)+".png");
        private float speed;
        private IModelCustom model;
        
        public GuiLanz (EntityLanz lanz){
        	speed=lanz.speed;
        }       
            
        @Override
		public void drawScreen(int par1, int par2, float par3) 
        {
            //-----------test section---------------
            
    		GL11.glColor4f(0.9F, 0.9F, 1.0F, 1.0F);
            this.mc.getTextureManager().bindTexture(Tacho);  
            //this.drawTexturedModalRect(this.width-128, this.height-128, 0,0,128,128);
            //this.drawTexturedModalRect(this.width-128, this.height-128, 0,0,128,128);
            super.drawScreen(par1, par2, par3);
            updateScreen();
        }
    
        

        /**
         * Returns true if this GUI should pause the game when it is displayed in single-player
         */
        @Override
		public boolean doesGuiPauseGame()
        {
            return false;
        }
        @Override
        protected void keyTyped(char par1, int par2)
        {
            if (par2 == Keyboard.KEY_ADD)
            {
            	if(TextureID>0)
            	TextureID++;
            	drawScreen(0, 0, 0);
            }
            if (par2 == Keyboard.KEY_SUBTRACT)
            {
            	if(TextureID>0)
            		TextureID--;
            	drawScreen(1, 1, 1);
            }
        }


        }
