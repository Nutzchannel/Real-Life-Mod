package de.ItsAMysterious.mods.reallifemod.api.CustomMusic;

import java.awt.Color;
import javax.swing.JFileChooser;
import org.lwjgl.opengl.GL11;
























import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ResourceLocation;

public class GuiPlaylist extends GuiScreen{
	
    public static final int GUI_ID = 201;
    private static final ResourceLocation Hintergrund = new ResourceLocation("reallifemod:textures/gui/PlaylistBackground.png");
	public static String currentFile;
	public static String[] tracks;
	public static int Titelnummer;
	public JFileChooser fc;

    @Override
    public void drawScreen(int par1, int par2, float par3) 
    {
        //-----------test section---------------
		GL11.glColor4f(0.9F, 0.9F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(Hintergrund);
        this.drawTexturedModalRect(Minecraft.getMinecraft().displayWidth-150, 200, 0,0,800,600);
        Gui.drawRect(100, 100, 300, 100, Color.BLACK.getRGB());
        super.drawScreen(par1, par2, par3);
        this.buttonList.add(new GuiButton(0, 50,this.height/2+50, 200, 20, "add music"));
        this.buttonList.add(new GuiButton(1, 250, this.height/2+50, 200, 20, "random"));
     }
    @Override
     public void actionPerformed(GuiButton button){
    	if (button.id==0){
    		
    	}
     }
    }    	

     



