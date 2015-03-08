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
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

import org.lwjgl.input.Keyboard;

import de.ItsAMysterious.mods.reallifemod.api.handlers.RLMEventHandler_Common;
import de.ItsAMysterious.mods.reallifemod.api.handlers.RLMTerrainHandler;
import de.ItsAMysterious.mods.reallifemod.api.handlers.TLMKeysHandler;
import de.ItsAMysterious.mods.reallifemod.api.handlers.TLM_EventHandler;
import de.ItsAMysterious.mods.reallifemod.api.util.TLMCustomCreativeTabs;
import de.ItsAMysterious.mods.reallifemod.config.RealLifeModConfig;
import de.ItsAMysterious.mods.reallifemod.core.gui.reallifemodHUD;
import de.ItsAMysterious.mods.reallifemod.server.RLMCommands;
import de.ItsAMysterious.mods.reallifemod.server.ServerProxy;
import de.ItsAMysterious.mods.reallifemod.server.TutorialBotCommand;

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.VERSION, guiFactory = "de.ItsAMysterious.mods.reallifemod.core.gui.RealLifeModGuiFactory")
public class RealLifeMod {

	@Mod.Instance(Reference.MOD_ID)
	public static RealLifeMod instance;

	@SidedProxy(clientSide = "de.ItsAMysterious.mods.reallifemod.client.ClientProxy", serverSide = "de.ItsAMysterious.mods.reallifemod.server.ServerProxy")
	public static ServerProxy proxy;
	public static Logger log = Logger.getLogger("Minecraft");

	public static boolean enableMinecraftStyle = false;

	public static KeyBinding character;
	public static KeyBinding Phone;
	public static KeyBinding Key_Horn;
	public static ToolMaterial Krupp = EnumHelper.addToolMaterial("Krupp", 4,
			3000, 4.0F, 12, 10);

	public static final TLMCustomCreativeTabs TLMAgriculture = new TLMCustomCreativeTabs(
			"reallifemod-agriculture");
	public static final TLMCustomCreativeTabs rlmfood = new TLMCustomCreativeTabs(
			"reallifemod-food") {
		@Override
		public Item getTabIconItem() {
			return TLMItems.noodlesoup;
		}
	};
	
	public static final TLMCustomCreativeTabs TLMIndustry = new TLMCustomCreativeTabs(
			"reallifemod-technology") {
		@Override
		public Item getTabIconItem() {
			return TLMItems.circuitBrd;
		}
	};
	public static final TLMCustomCreativeTabs Furniture = new TLMCustomCreativeTabs(
			"reallifemod-furniture") {
		@Override
		public Item getTabIconItem() {
			return Item.getItemFromBlock(TLMBlocks.cupboard);
		}
	};
	public static final TLMCustomCreativeTabs Outdoor = new TLMCustomCreativeTabs(
			"reallifemod-outdoor") {
		@Override
		public Item getTabIconItem() {
			return Item.getItemFromBlock(TLMBlocks.boxbush);
		}
	};
	public static final TLMCustomCreativeTabs medicine = new TLMCustomCreativeTabs(
			"reallifemod-medicine") {
		@Override
		public Item getTabIconItem() {
			return TLMItems.syringe;
		}
	};
	public static final TLMCustomCreativeTabs TLMEverydaylife = new TLMCustomCreativeTabs(
			"reallifemod-everydalife stuff") {
		@Override
		public Item getTabIconItem() {
			return TLMItems.ID;
		}
	};
	public static final TLMCustomCreativeTabs Streets = new TLMCustomCreativeTabs(
			"reallifemod-roads") {
		@Override
		public Item getTabIconItem() {
			return TLMItems.Lowry;
		}
	};
	public static final TLMCustomCreativeTabs Work = new TLMCustomCreativeTabs(
			"tools and weapons") {
		@Override
		public Item getTabIconItem() {
			return TLMItems.ak;
		}
	};
	public static Configuration config;
	public static File Dir;
	private File dir;

	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		log.info("real life mod loaded");
		config = new Configuration(event.getSuggestedConfigurationFile());
		RealLifeModConfig.syncConfig();
		log.info("real life mod loaded configs");
		proxy.registerTileEntities();
		proxy.setIDs();
		character = new KeyBinding("Open chararcter setup", 38, "real life mod");
		Phone = new KeyBinding("Open Phone", Keyboard.KEY_P, "real life mod");
		Key_Horn = new KeyBinding("Horn", 23, "real life mod");
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

	@Mod.EventHandler
	public void init(FMLInitializationEvent event) {
		FMLCommonHandler.instance().bus().register(instance);
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

	@Mod.EventHandler
	public void PostInit(FMLPostInitializationEvent event) {
		proxy.doPostInit();
		config.load();
	}

	@Mod.EventHandler
	public void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event) {
		if (event.modID.equals(Reference.MOD_ID)) {
			RealLifeModConfig.syncConfig();
		}
	}

	@Mod.EventHandler
	public void onServerStart(FMLServerStartingEvent event) {
		event.registerServerCommand(new RLMCommands());
		event.registerServerCommand(new TutorialBotCommand());
	}
	/*
	 * Wenn player spawn, ACHIEVEMENT GET! Downloaded Real Life Mod!
	 */

}
