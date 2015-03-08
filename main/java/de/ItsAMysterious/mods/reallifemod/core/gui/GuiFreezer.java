package de.ItsAMysterious.mods.reallifemod.core.gui;

import de.ItsAMysterious.mods.reallifemod.RealLifeMod;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;

public class GuiFreezer extends GuiContainer{

	private ResourceLocation texture = new ResourceLocation(RealLifeMod.ID+":textures/gui/guiFreezer.png");

	public GuiFreezer(Container container) {
		super(container);
		xSize=176;
		ySize=203;
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float f,
			int x, int y) {
		Minecraft.getMinecraft().getTextureManager().bindTexture(texture );
		drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
		
	}

}
