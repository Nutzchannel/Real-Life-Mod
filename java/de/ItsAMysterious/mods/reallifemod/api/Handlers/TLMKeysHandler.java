package de.ItsAMysterious.mods.reallifemod.api.Handlers;

import net.minecraft.client.Minecraft;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent;
import de.ItsAMysterious.mods.reallifemod.TLM;
import de.ItsAMysterious.mods.reallifemod.core.Gui.GuiSettings;
import de.ItsAMysterious.mods.reallifemod.core.Gui.guiCharacterSetup;

public class TLMKeysHandler{

	@SubscribeEvent
	public void onKeyInput(InputEvent.KeyInputEvent event)
	{
		if (TLM.character.isPressed())
			Minecraft.getMinecraft().displayGuiScreen(new guiCharacterSetup());
		if (TLM.KeySettings.isPressed()){
			Minecraft.getMinecraft().displayGuiScreen(new GuiSettings());
		}
	}

}
