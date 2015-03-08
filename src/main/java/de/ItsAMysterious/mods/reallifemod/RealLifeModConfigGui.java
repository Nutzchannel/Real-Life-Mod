package de.ItsAMysterious.mods.reallifemod;

import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.config.GuiConfig;

public class RealLifeModConfigGui extends GuiConfig {
	public RealLifeModConfigGui(GuiScreen parent) {
		super(parent, new ConfigElement(
				RealLifeMod.config.getCategory(Configuration.CATEGORY_GENERAL))
				.getChildElements(), Reference.MOD_NAME, false, false, GuiConfig
				.getAbridgedConfigPath(RealLifeMod.config.toString()));
	}
}