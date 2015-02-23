package de.ItsAMysterious.mods.reallifemod;

import java.io.File;
import java.util.logging.Logger;

import org.lwjgl.input.Keyboard;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.util.EnumHelper;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartedEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import de.ItsAMysterious.mods.reallifemod.api.handlers.RLMEventHandler_Common;
import de.ItsAMysterious.mods.reallifemod.api.handlers.RLMTerrainHandler;
import de.ItsAMysterious.mods.reallifemod.api.handlers.TLMKeysHandler;
import de.ItsAMysterious.mods.reallifemod.api.handlers.TLM_EventHandler;
import de.ItsAMysterious.mods.reallifemod.api.util.TLMCustomCreativeTabs;
import de.ItsAMysterious.mods.reallifemod.core.config.TLMSettings;
import de.ItsAMysterious.mods.reallifemod.core.gui.reallifemodHUD;
import de.ItsAMysterious.mods.reallifemod.server.RLMCommands;
import de.ItsAMysterious.mods.reallifemod.server.ServerProxy;
import de.ItsAMysterious.mods.reallifemod.server.TutorialBotCommand;


@Mod(modid=RealLifeMod.ID, name=RealLifeMod.NAME, version=RealLifeMod.VERSION,guiFactory = "de.ItsAMysterious.mods.reallifemod.TLMGuiFactory")
public class RealLifeMod {
	public static final String ID="reallifemod";
	public static final String NAME="real life mod";
	public static final String VERSION="0.1.2";
	
	@Instance(ID)
	public static RealLifeMod instance;

	@SidedProxy(clientSide = "de.ItsAMysterious.mods.reallifemod.client.ClientProxy", serverSide = "de.ItsAMysterious.mods.reallifemod.server.ServerProxy")
	public static ServerProxy proxy;
    public static Logger log = Logger.getLogger("Minecraft");
	 
    public static int timeMultiplier = 400;
    public static String PlayerName = "Max";
    public static boolean RealityMode = false;

	public static KeyBinding character;
	public static KeyBinding Phone;
	public static KeyBinding Key_Horn;
	public static ToolMaterial Krupp = EnumHelper.addToolMaterial("Krupp", 4,3000, 4.0F, 12, 10);
	
	public static final TLMCustomCreativeTabs TLMAgriculture= new TLMCustomCreativeTabs("reallifemod-agriculture");
	public static final TLMCustomCreativeTabs rlmfood = new TLMCustomCreativeTabs("reallifemod-food") {@Override
	public Item getTabIconItem() {return TLMItems.noodlesoup;}};
	public static final TLMCustomCreativeTabs TLMIndustry = new TLMCustomCreativeTabs("reallifemod-technology") {@Override
	public Item getTabIconItem() {return TLMItems.circuitBrd;}};
	public static final TLMCustomCreativeTabs Furniture = new TLMCustomCreativeTabs("reallifemod-furniture") {@Override
	public Item getTabIconItem() {return Item.getItemFromBlock(TLMBlocks.cupboard);}};
	public static final TLMCustomCreativeTabs Outdoor = new TLMCustomCreativeTabs("reallifemod-outdoor") {@Override
	public Item getTabIconItem() {return Item.getItemFromBlock(TLMBlocks.boxbush);}};
	public static final TLMCustomCreativeTabs medicine = new TLMCustomCreativeTabs("reallifemod-medicine") {@Override
	public Item getTabIconItem() {return TLMItems.syringe;}};
	public static final TLMCustomCreativeTabs TLMEverydaylife = new TLMCustomCreativeTabs("reallifemod-everydalife stuff") {@Override
	public Item getTabIconItem() {return TLMItems.ID;}};
	public static final TLMCustomCreativeTabs Streets = new TLMCustomCreativeTabs("reallifemod-roads") {@Override
	public Item getTabIconItem() {return TLMItems.Lowry;}};
	public static final TLMCustomCreativeTabs Work = new TLMCustomCreativeTabs("Weapons") {@Override
	public Item getTabIconItem() {return TLMItems.ak;}};
    public static Configuration configFile;
	public static File Dir;
	private File dir;
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event){
		log.info("real life mod loaded");
        configFile = new Configuration(event.getSuggestedConfigurationFile());
        syncConfig();
        log.info("real life mod loaded configs");
		proxy.registerTileEntities();
		proxy.setIDs();
		character = new KeyBinding("Open chararcter setup", 38,"real life mod");
		Phone=new KeyBinding("Open Phone", Keyboard.KEY_P,"real life mod");
		Key_Horn=new KeyBinding("Horn", 23, "real life mod");
		ClientRegistry.registerKeyBinding(character);
		ClientRegistry.registerKeyBinding(Phone);
		ClientRegistry.registerKeyBinding(Key_Horn);
		MinecraftForge.EVENT_BUS.register(new TLM_EventHandler());
		MinecraftForge.EVENT_BUS.register(new ServerProxy());
		MinecraftForge.EVENT_BUS.register(new reallifemodHUD(FMLClientHandler.instance().getClient()));
		FMLCommonHandler.instance().bus().register(new RLMEventHandler_Common());
		MinecraftForge.EVENT_BUS.register(new RLMTerrainHandler());
		FMLCommonHandler.instance().bus().register(new TLMKeysHandler());
	}


	@EventHandler
	public void init(FMLInitializationEvent event) {
        dir = Minecraft.getMinecraft().mcDataDir;
        RealLifeMod.Dir = new File(dir, "reallifemod");
        proxy.createFolders();
		TLMBlocks.defineBlocks();
		TLMItems.defineItems();
		TLMBlocks.registerBlocks();
		TLMItems.registerItems();
		proxy.registerBlockHandlers();
		proxy.registerRenderThings();
		proxy.registerEntitys();
		TLMRecipes.addRecipes();
		TLMRecipes.addSmeltings();
		proxy.registerThings();
	}

	@EventHandler
	public void PostInit(FMLPostInitializationEvent event) {
		proxy.doPostInit();
	}

    public static void syncConfig() {
    	TLMSettings.Inventory3D=configFile.getBoolean("Inventory3D", Configuration.CATEGORY_GENERAL, false, "Says, wether the 3D models should be realistic or Minecraft-like");
        TLMSettings.realityMode = configFile.getBoolean("realityMode", Configuration.CATEGORY_GENERAL, false, "Switch this on if you whant to have growing hair, have to brush your teeth");
        TLMSettings.showHud = configFile.getBoolean("show HUD", Configuration.CATEGORY_GENERAL, true, "Wether to hide or show the real life mod HUD");
        if(configFile.hasChanged())
            configFile.save();
    }
    @EventHandler    
    public void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent eventArgs) {
        if(eventArgs.modID.equals("reallifemod")){
            syncConfig();}
        if(eventArgs.configID=="show HUD"){
            TLMSettings.showHud = configFile.getBoolean("show HUD", Configuration.CATEGORY_GENERAL, true, "Wether to hide or show the real life mod HUD");
        }
    }
   
  @EventHandler
   public void onServerStart(FMLServerStartingEvent event){
	   event.registerServerCommand(new RLMCommands());
	   event.registerServerCommand(new TutorialBotCommand());

   }
   	
	
}
	
