package de.ItsAMysterious.mods.reallifemod;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.item.Item;
import cpw.mods.fml.common.registry.GameRegistry;
import de.ItsAMysterious.mods.reallifemod.core.items.AK47Item;
import de.ItsAMysterious.mods.reallifemod.core.items.ItemIdentityCard;
import de.ItsAMysterious.mods.reallifemod.core.items.NPCPlacer;
import de.ItsAMysterious.mods.reallifemod.core.items.itemEmergencyHelicopter;
import de.ItsAMysterious.mods.reallifemod.core.items.uziItem;
import de.ItsAMysterious.mods.reallifemod.core.items.food.ItemBanana;
import de.ItsAMysterious.mods.reallifemod.core.items.food.ItemDough;
import de.ItsAMysterious.mods.reallifemod.core.items.food.ItemSalt;
import de.ItsAMysterious.mods.reallifemod.core.items.food.ItemToast;
import de.ItsAMysterious.mods.reallifemod.core.items.food.bottleWater;
import de.ItsAMysterious.mods.reallifemod.core.items.food.emptyBottle;
import de.ItsAMysterious.mods.reallifemod.core.items.food.itemChips;
import de.ItsAMysterious.mods.reallifemod.core.items.food.itemCracker;
import de.ItsAMysterious.mods.reallifemod.core.items.food.itemKetchup;
import de.ItsAMysterious.mods.reallifemod.core.items.industrial.ItemCoke;
import de.ItsAMysterious.mods.reallifemod.core.items.industrial.ItemSchrott;
import de.ItsAMysterious.mods.reallifemod.core.items.industrial.ItemSteelAxe;
import de.ItsAMysterious.mods.reallifemod.core.items.industrial.itemIngotSteel;
import de.ItsAMysterious.mods.reallifemod.core.items.seasonalstuff.itemGingerbread;
import de.ItsAMysterious.mods.reallifemod.core.items.seasonalstuff.itemSugarPole;
import de.ItsAMysterious.mods.reallifemod.core.items.streets.ItemLowry;
import de.ItsAMysterious.mods.reallifemod.core.items.streets.itemJeep;
import de.ItsAMysterious.mods.reallifemod.core.items.streets.itemTrailer;

public class TLMItems {
	public static Item 
	ID, customBlocksItem, ItemSnowMan, CustomSpade, cracker, salt, keksteig, banana, 
	chips, ingotSteel, steelAxe, coke, Schrott, dirtySteel, emergencyHelicopter, Lowry, trailer,
	 bottle,santa,itemGingerBread,ItemSugarPole, ak,guide, fireleggins, firehelmet, fireboots,
	 fireplate, placer,toast, ketchup,uzi, jeep, emptybottle;
	
	public static List<Item> itemList=new ArrayList<Item>();

	public static void defineItems() {
		itemList.add(ingotSteel = new itemIngotSteel().setCreativeTab(TLM.TLMIndustry));
		itemList.add(cracker = new itemCracker(2, 5F, true).setCreativeTab(TLM.TLMFood));
		itemList.add(salt = new ItemSalt(-1, -1, false).setCreativeTab(TLM.TLMFood));
		itemList.add(keksteig = new ItemDough(2, false).setCreativeTab(TLM.TLMFood));
		itemList.add(steelAxe = new ItemSteelAxe(TLM.Krupp).setCreativeTab(TLM.TLMIndustry));
		itemList.add(dirtySteel = new Item().setCreativeTab(TLM.TLMIndustry).setTextureName("TLM:dirtySteel").setUnlocalizedName("dirtysteel"));
		itemList.add(ID = new ItemIdentityCard().setCreativeTab(TLM.TLMEverydaylife));	
		itemList.add(emergencyHelicopter = new itemEmergencyHelicopter().setCreativeTab(TLM.TLMEmergency));
		//itemList.add(customBlocksItem = new ItemCBO().setCreativeTab(TLM.TLMIndustry));
		itemList.add(coke = new ItemCoke().setCreativeTab(TLM.TLMIndustry));
		itemList.add(Schrott = new ItemSchrott().setCreativeTab(TLM.TLMIndustry));
		itemList.add(banana = new ItemBanana(5,false).setCreativeTab(TLM.TLMFood));
		itemList.add(chips = new itemChips(5, 3,false).setCreativeTab(TLM.TLMFood));
		itemList.add(Lowry = new ItemLowry().setCreativeTab(TLM.Streets));
		itemList.add(trailer = new itemTrailer().setCreativeTab(TLM.Streets));
		itemList.add(bottle =(new bottleWater()));
		itemList.add(ItemSugarPole=new itemSugarPole(1, 1.0f, false).setCreativeTab(TLM.seasonalstuff));
		itemList.add(itemGingerBread=new itemGingerbread(1, 1.0f, false).setCreativeTab(TLM.seasonalstuff));
		itemList.add(ak=new AK47Item().setCreativeTab(TLM.Work));
		//itemList.add(firehelmet = new fireprotectionsuite("fire helmet", Materials.FireProtection,"fireprotectionsuite", 0).setCreativeTab(TLM.Work).setUnlocalizedName("fireprotectionsuite")); //0 for helmet
		//itemList.add(fireplate = new fireprotectionsuite("fire chestplate", Materials.FireProtection,"fireprotectionsuite", 1).setCreativeTab(TLM.Work).setUnlocalizedName("fireprotectionsuite_chestplate")); //0 for helmet
		//itemList.add(fireleggins = new fireprotectionsuite("fire leggins", Materials.FireProtection,"fireprotectionsuite", 2).setCreativeTab(TLM.Work).setUnlocalizedName("fireprotection_leggins")); //0 for helmet
		itemList.add(placer=new NPCPlacer().setCreativeTab(TLM.Work));
		itemList.add(toast=new ItemToast(1, false).setCreativeTab(TLM.TLMFood));
		itemList.add(ketchup=new itemKetchup(1, false).setCreativeTab(TLM.TLMFood));
		itemList.add(uzi=new uziItem().setCreativeTab(TLM.Work));
		itemList.add(jeep=new itemJeep().setCreativeTab(TLM.Streets));
		itemList.add(emptybottle=new emptyBottle().setCreativeTab(TLM.TLMFood));
	}
	
	public static void registerItems(){
		for(int i=0; i<itemList.size();i++){
			GameRegistry.registerItem(itemList.get(i), itemList.get(i).getUnlocalizedName());
		}
	}

}
