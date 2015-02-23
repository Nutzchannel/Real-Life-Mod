package de.ItsAMysterious.mods.reallifemod;
 
import cpw.mods.fml.client.config.GuiConfig;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.common.config.Configuration;
 
public class TLMConfigGUI extends GuiConfig {
    public TLMConfigGUI(GuiScreen parent) {
        super(parent,
                new ConfigElement(RealLifeMod.configFile.getCategory(Configuration.CATEGORY_GENERAL)).getChildElements(),
                "RealLifeMod", false, false, GuiConfig.getAbridgedConfigPath(RealLifeMod.configFile.toString()));
    }
}