package de.ItsAMysterious.mods.reallifemod.api.handlers;

import net.minecraft.client.Minecraft;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent;
import de.ItsAMysterious.mods.reallifemod.RealLifeMod;
import de.ItsAMysterious.mods.reallifemod.core.gui.guiCharacterSetup;
import de.ItsAMysterious.mods.reallifemod.core.gui.guiPhone;

public class TLMKeysHandler{

	@SubscribeEvent
	public void onKeyInput(InputEvent.KeyInputEvent event)
	{
		if (RealLifeMod.character.isPressed())
			Minecraft.getMinecraft().displayGuiScreen(new guiCharacterSetup());
		if (RealLifeMod.Phone.isPressed()){
			Minecraft.getMinecraft().displayGuiScreen(new guiPhone());
		}
	}

}
