package de.ItsAMysterious.mods.reallifemod;

import java.io.File;
import java.util.logging.Logger;

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
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import de.ItsAMysterious.mods.reallifemod.api.Handlers.RLMEventHandler_Common;
import de.ItsAMysterious.mods.reallifemod.api.Handlers.RLMTerrainHandler;
import de.ItsAMysterious.mods.reallifemod.api.Handlers.TLMKeysHandler;
import de.ItsAMysterious.mods.reallifemod.api.Handlers.TLM_EventHandler;
import de.ItsAMysterious.mods.reallifemod.api.tabs.TLMCustomCreativeTabs;
import de.ItsAMysterious.mods.reallifemod.core.Configuration.TLMSettings;
import de.ItsAMysterious.mods.reallifemod.core.Gui.reallifemodHUD;
import de.ItsAMysterious.mods.reallifemod.server.ServerProxy;


@Mod(modid=TLM.ID, name=TLM.NAME, version=TLM.VERSION,guiFactory = "de.ItsAMysterious.mods.reallifemod.TLMGuiFactory")
public class TLM {
	public static final String ID="reallifemod";
	public static final String NAME="real life mod";
	public static final String VERSION="0.1.1";
	
	@Instance(ID)
	public static TLM instance;

	@SidedProxy(clientSide = "de.ItsAMysterious.mods.reallifemod.client.ClientProxy", serverSide = "de.ItsAMysterious.mods.reallifemod.server.ServerProxy")
	public static ServerProxy proxy;
    public static Logger log = Logger.getLogger("Minecraft");
	 
    public static int timeMultiplier = 400;
    public static String PlayerName = "Max";
    public static boolean RealityMode = false;

	public static KeyBinding character;
	public static KeyBinding KeySettings;
	public static KeyBinding Key_Horn;
	public static ToolMaterial Krupp = EnumHelper.addToolMaterial("Krupp", 4,3000, 4.0F, 12, 10);
	
	public static final TLMCustomCreativeTabs TLMAgriculture = new TLMCustomCreativeTabs("TLM: agriculture");
	public static final TLMCustomCreativeTabs TLMFood = new TLMCustomCreativeTabs("TLM: food") {@Override
	public Item getTabIconItem() {return TLMItems.cracker;}};
	public static final TLMCustomCreativeTabs TLMIndustry = new TLMCustomCreativeTabs("TLM: industries") {@Override
	public Item getTabIconItem() {return Item.getItemFromBlock(TLMBlocks.blastFurnace);}};
	public static final TLMCustomCreativeTabs Furniture = new TLMCustomCreativeTabs("Furniture") {@Override
	public Item getTabIconItem() {return Item.getItemFromBlock(TLMBlocks.cupboard);}};
	public static final TLMCustomCreativeTabs Outdoor = new TLMCustomCreativeTabs("Outdoor") {@Override
	public Item getTabIconItem() {return Item.getItemFromBlock(TLMBlocks.boxbush);}};
	public static final TLMCustomCreativeTabs TLMEmergency = new TLMCustomCreativeTabs("TLM: Emergency") {@Override
	public Item getTabIconItem() {return TLMItems.emergencyHelicopter;}};
	public static final TLMCustomCreativeTabs TLMEverydaylife = new TLMCustomCreativeTabs("everydalife stuff") {@Override
	public Item getTabIconItem() {return TLMItems.ID;}};
	public static final TLMCustomCreativeTabs Streets = new TLMCustomCreativeTabs("street-stuff") {@Override
	public Item getTabIconItem() {return TLMItems.Lowry;}};
	public static final TLMCustomCreativeTabs seasonalstuff = new TLMCustomCreativeTabs("seasonal stuff") {@Override
	public Item getTabIconItem() {return Item.getItemFromBlock(TLMBlocks.BlockDoorwreath);}};
	public static final TLMCustomCreativeTabs Work = new TLMCustomCreativeTabs("work") {@Override
	public Item getTabIconItem() {return Item.getItemFromBlock(TLMBlocks.BlockDoorwreath);}};
    public static Configuration configFile;
	public static File Dir;
	private File dir;
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event){
		FMLCommonHandler.instance().bus().register(instance);
		log.info("real life mod loaded");
        configFile = new Configuration(event.getSuggestedConfigurationFile());
        syncConfig();
        log.info("real life mod loaded configs");
		proxy.registerTileEntities();
		proxy.setIDs();
		character = new KeyBinding("Open chararcter setup", 38,"real life mod");
		KeySettings=new KeyBinding("real life mod settings", 44,"real life mod");
		Key_Horn=new KeyBinding("Horn", 23, "real life mod");
		ClientRegistry.registerKeyBinding(character);
		ClientRegistry.registerKeyBinding(KeySettings);
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
        TLM.Dir = new File(dir, "reallifemod");
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
   @SubscribeEvent
    public void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent eventArgs) {
        if(eventArgs.modID.equals("reallifemod")){
            syncConfig();}
        if(eventArgs.configID=="show HUD"){
            TLMSettings.showHud = configFile.getBoolean("show HUD", Configuration.CATEGORY_GENERAL, true, "Wether to hide or show the real life mod HUD");
        }
    }
   /**For next update*/
   /*
    * Radio->Ressourcemanager und co wieder aktivieren!
	  Buy blocks and items using the computer
	  New decorative blocks e.g. heating, playstation
	  New, improved driving dynamics
    */
   
   /**Quest*/
   /*
    Gardener-->grow trees...
    */
   
   /**Ideen*/
   /*Freefalltower und co-->Mut, Spaß...
    * Im suchemodus:miniansicht von guiscreens(glScaleF(0.1F))
    * Anzahl an Einkommen haengt von der timmung ab--> Happy=mehr geld.
    * Bankautomat-->Wandelt eisen in 1 geld, gold in 5, diamant in 50 usw.
    * Wasserfestes Feuer
    * Busse etc Fahren vorbestimmte Strecken ab(bloecke), dann kann man auf Servern zusteigen.
    * Texturepack for the mod-> 16 bit textures--> 16 bit models...
    * Custom Items-> Ketchup+Toast=Ketchuptoast
    * Spuehlmaschiene->wie brennen. Mit tellern-> itemPlate und itemDirtyplate
    * Toast->Can be used to craft hamburgers that can be selled to villagers	
    * Pissstrahl-->EntityBullet, verschieden groß skalierte kugeln.
    * Ostereiersuche
    * Je nach Spracheinstellungen sehen die Uniformen z.B. Feuerwehr den jeweiligen Laendern entsprechend anders aus.	
    * -Hebebuehne
    * Lebensbalken wird angezeigt wenn man entity oder mob schlaegt
    * reallife hacks=real life mod cheats
    * Creeper/Monsterfalle wie Mausefalle mit tuer-> Items ernten
    *-Feuerwehr
    *-Echte Buecher(Buch+PDF-Dokument)
    *-Gartenbank
    *-Bewegungsmelderlampe
    *-Schoene Gartenlampe
    *-Schornstein(modeled)
    *-Shop(Geld etc)/Craftingrezepte(Einstellbar bei Modsettings)
    *Garagentor wie in GTA->RollTor
    *Scheißen->in Natur...
    *Milchglas
    *MCA-Namen uebernehmen->Abfrage
    *Staubsauger
    *abschließbare tueren
    *Krippenfiguren->Jesuskind...
    *Kartons
    *Immitationspillen-> zum Creeper werden, damit creeper nicht explodieren
    */
   	
	
}
	
