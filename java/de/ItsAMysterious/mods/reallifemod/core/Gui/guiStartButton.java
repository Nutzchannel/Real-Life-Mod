package de.ItsAMysterious.mods.reallifemod.core.Gui;

import net.minecraft.client.gui.Gui;
import net.minecraft.util.ResourceLocation;

public class guiStartButton extends Gui {
	public static ResourceLocation windowsLogo=new ResourceLocation("reallifemod:textures/gui/System/minedowsLogo.png");

	int posX,posY,width,height;
	public guiStartButton(int id,int x, int y,String caption) {
		
	}
	
	/**@Override
    public void drawButton(Minecraft minecraft, int x, int y)
    {
        if (this.visible)
        {
            minecraft.getTextureManager().bindTexture(windowsLogo);
            this.drawTexturedModalRect(this.xPosition, this.yPosition, 0, 0, this.width , this.height);
            this.mouseDragged(minecraft, x, y);
        }
    }*/
}

