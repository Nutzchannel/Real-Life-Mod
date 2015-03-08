package de.ItsAMysterious.mods.reallifemod.config;

import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.common.config.Configuration;
import cpw.mods.fml.client.config.GuiConfig;
import cpw.mods.fml.common.FMLCommonHandler;
import de.ItsAMysterious.mods.reallifemod.RealLifeMod;

public class RealLifeModConfig {
	public static boolean mouseSteering;
	public static boolean minecraftstyle;
	public static boolean playermodel;
	public static boolean MOUSESTEERING_DEFAULT=false;
	public static boolean MINECRAFTSTYLE_DEFAULT=false;
	public static boolean PLAYERMODEL_DEFAULT=false;
	public static final String mousesteeringname="En-/disables steering with mouse when flying";
	public static final String minecraftstylename="Enable Minecraftstyled 3D-models";
	public static final String playermodelname="Enable HD-Playermodel";



			
	public static void syncConfig() {
        if(RealLifeMod.config.getConfigFile()!=null){
        	RealLifeMod.config.load();
        }
    	final String FEATURES=RealLifeMod.config.CATEGORY_GENERAL+RealLifeMod.config.CATEGORY_SPLITTER+"Features";
    	final String THREED=RealLifeMod.config.CATEGORY_GENERAL+RealLifeMod.config.CATEGORY_SPLITTER+"3D-Settings";
        RealLifeMod.config.addCustomCategoryComment(FEATURES, "Enable or disable different features");
        mouseSteering=RealLifeMod.config.get(FEATURES,mousesteeringname,MOUSESTEERING_DEFAULT).getBoolean(MOUSESTEERING_DEFAULT);
        RealLifeMod.config.addCustomCategoryComment(THREED, "Change 3D setting for Real Life Mod");
        minecraftstyle=RealLifeMod.config.get(THREED,minecraftstylename,MINECRAFTSTYLE_DEFAULT).getBoolean(MINECRAFTSTYLE_DEFAULT);
        playermodel=RealLifeMod.config.get(THREED,playermodelname,PLAYERMODEL_DEFAULT).getBoolean(PLAYERMODEL_DEFAULT);

        if(RealLifeMod.config.hasChanged()){
        	RealLifeMod.config.save();
        }
	}
}
