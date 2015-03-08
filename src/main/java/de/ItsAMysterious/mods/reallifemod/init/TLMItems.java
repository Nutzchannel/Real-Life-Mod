package de.ItsAMysterious.mods.reallifemod.init;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;
import de.ItsAMysterious.mods.reallifemod.RealLifeMod;
import de.ItsAMysterious.mods.reallifemod.Reference;
import de.ItsAMysterious.mods.reallifemod.core.items.AK47Item;
import de.ItsAMysterious.mods.reallifemod.core.items.CupType;
import de.ItsAMysterious.mods.reallifemod.core.items.ItemIdentityCard;
import de.ItsAMysterious.mods.reallifemod.core.items.ItemSaw;
import de.ItsAMysterious.mods.reallifemod.core.items.NPCPlacer;
import de.ItsAMysterious.mods.reallifemod.core.items.itemEmergencyHelicopter;
import de.ItsAMysterious.mods.reallifemod.core.items.itemcircuit;
import de.ItsAMysterious.mods.reallifemod.core.items.uziItem;
import de.ItsAMysterious.mods.reallifemod.core.items.food.ItemBanana;
import de.ItsAMysterious.mods.reallifemod.core.items.food.ItemDough;
import de.ItsAMysterious.mods.reallifemod.core.items.food.ItemNoodlesoup;
import de.ItsAMysterious.mods.reallifemod.core.items.food.ItemSalt;
import de.ItsAMysterious.mods.reallifemod.core.items.food.ItemToast;
import de.ItsAMysterious.mods.reallifemod.core.items.food.bottleWater;
import de.ItsAMysterious.mods.reallifemod.core.items.food.emptyBottle;
import de.ItsAMysterious.mods.reallifemod.core.items.food.itemChips;
import de.ItsAMysterious.mods.reallifemod.core.items.food.itemCracker;
import de.ItsAMysterious.mods.reallifemod.core.items.food.itemCup;
import de.ItsAMysterious.mods.reallifemod.core.items.food.itemGingerbread;
import de.ItsAMysterious.mods.reallifemod.core.items.food.itemKetchup;
import de.ItsAMysterious.mods.reallifemod.core.items.food.itemSugarPole;
import de.ItsAMysterious.mods.reallifemod.core.items.industrial.ItemCoke;
import de.ItsAMysterious.mods.reallifemod.core.items.industrial.ItemSchrott;
import de.ItsAMysterious.mods.reallifemod.core.items.industrial.ItemSteelAxe;
import de.ItsAMysterious.mods.reallifemod.core.items.industrial.itemIngotSteel;
import de.ItsAMysterious.mods.reallifemod.core.items.meds.itemMed;
import de.ItsAMysterious.mods.reallifemod.core.items.meds.itemSyringe;
import de.ItsAMysterious.mods.reallifemod.core.items.roads.ItemLowry;
import de.ItsAMysterious.mods.reallifemod.core.items.roads.itemJeep;
import de.ItsAMysterious.mods.reallifemod.core.items.roads.itemTrailer;

public class TLMItems {
	public static Item 
	ID, customBlocksItem, ItemSnowMan, CustomSpade, cracker, salt, keksteig, banana, 
	chips, ingotSteel, steelAxe, coke, Schrott, dirtySteel, emergencyHelicopter, Lowry, trailer,
	 bottle,santa,itemGingerBread,ItemSugarPole, ak,guide, fireleggins, firehelmet, fireboots,
	 fireplate, placer,toast, ketchup,uzi, jeep, emptybottle, circuitBrd, pillbox, syringe,
	 cupEmpty,cupCappuccino,cupCoffee, cupHotChocolate,cupLatte, cupEspresso,cupTea,noodlesoup, saw;
	
	public static List<Item> itemList=new ArrayList<Item>();

	public static void defineItems() {
		itemList.add(ingotSteel = new itemIngotSteel().setCreativeTab(RealLifeMod.TLMIndustry));
		itemList.add(cracker = new itemCracker(2, 5F, true).setCreativeTab(RealLifeMod.rlmfood));
		itemList.add(salt = new ItemSalt(-1, -1, false).setCreativeTab(RealLifeMod.rlmfood));
		itemList.add(keksteig = new ItemDough(2, false).setCreativeTab(RealLifeMod.rlmfood));
		itemList.add(steelAxe = new ItemSteelAxe(RealLifeMod.Krupp).setCreativeTab(RealLifeMod.TLMIndustry));
		itemList.add(dirtySteel = new Item().setCreativeTab(RealLifeMod.TLMIndustry).setUnlocalizedName("dirtysteel")); // texture "RealLifeMod:dirtySteel"
		itemList.add(ID = new ItemIdentityCard().setCreativeTab(RealLifeMod.TLMEverydaylife));	
		itemList.add(emergencyHelicopter = new itemEmergencyHelicopter().setCreativeTab(RealLifeMod.medicine));
		//itemList.add(customBlocksItem = new ItemCBO().setCreativeTab(RealLifeMod.TLMIndustry));
		itemList.add(coke = new ItemCoke().setCreativeTab(RealLifeMod.TLMIndustry));
		itemList.add(Schrott = new ItemSchrott().setCreativeTab(RealLifeMod.TLMIndustry));
		itemList.add(banana = new ItemBanana(5,false).setCreativeTab(RealLifeMod.rlmfood));
		itemList.add(chips = new itemChips(5, 3,false).setCreativeTab(RealLifeMod.rlmfood));
		itemList.add(Lowry = new ItemLowry().setCreativeTab(RealLifeMod.Streets));
		itemList.add(trailer = new itemTrailer().setCreativeTab(RealLifeMod.Streets));
		itemList.add(bottle =(new bottleWater()));
		itemList.add(ItemSugarPole=new itemSugarPole(1, 1.0f, false).setCreativeTab(RealLifeMod.rlmfood));
		itemList.add(itemGingerBread=new itemGingerbread(1, 1.0f, false).setCreativeTab(RealLifeMod.rlmfood));
		itemList.add(ak=new AK47Item().setCreativeTab(RealLifeMod.Work));
		//itemList.add(firehelmet = new fireprotectionsuite("fireprotection helmet", Materials.FireProtection,"fireprotectionsuite", 0).setCreativeTab(RealLifeMod.Work).setUnlocalizedName("fireprotectionsuite")); //0 for helmet
		//itemList.add(fireplate = new fireprotectionsuite("fireprotection chestplate", Materials.FireProtection,"fireprotectionsuite", 1).setCreativeTab(RealLifeMod.Work).setUnlocalizedName("fireprotectionsuite_chestplate")); //0 for helmet
		//itemList.add(fireleggins = new fireprotectionsuite("fire leggins", Materials.FireProtection,"fireprotectionsuite", 2).setCreativeTab(RealLifeMod.Work).setUnlocalizedName("fireprotection_leggins")); //0 for helmet
		itemList.add(placer=new NPCPlacer().setCreativeTab(RealLifeMod.Work));
		itemList.add(toast=new ItemToast(1, false).setCreativeTab(RealLifeMod.rlmfood));
		itemList.add(ketchup=new itemKetchup(1, false).setCreativeTab(RealLifeMod.rlmfood));
		itemList.add(uzi=new uziItem().setCreativeTab(RealLifeMod.Work));
		itemList.add(jeep=new itemJeep().setCreativeTab(RealLifeMod.Streets));
		itemList.add(emptybottle=new emptyBottle().setCreativeTab(RealLifeMod.rlmfood));
		itemList.add(circuitBrd=( new itemcircuit()).setCreativeTab(RealLifeMod.TLMIndustry));
		itemList.add(pillbox=new itemMed(0, false).setUnlocalizedName("pillbox").setCreativeTab(RealLifeMod.medicine)); //texture "reallifemod:medicine"
		itemList.add(syringe=new itemSyringe().setCreativeTab(RealLifeMod.medicine));
		itemList.add(cupEmpty=new itemCup(0,false,CupType.CUP));
		itemList.add(cupCoffee=new itemCup(1,false,CupType.COFFEE));
		itemList.add(cupCappuccino=new itemCup(1,false,CupType.CAPPUCCINO));
		itemList.add(cupEspresso=new itemCup(1,false,CupType.ESPRESSO));
		itemList.add(cupLatte=new itemCup(1,false,CupType.LATTE));
		itemList.add(cupHotChocolate=new itemCup(1,false,CupType.HOTCHOC));
		itemList.add(cupTea=new itemCup(1,false,CupType.TEA));
		itemList.add(noodlesoup=new ItemNoodlesoup(3, false).setCreativeTab(RealLifeMod.rlmfood));
		itemList.add(saw=new ItemSaw().setCreativeTab(RealLifeMod.Work));
	}
	
	public static void registerItems(){
		for(int i=0; i<itemList.size();i++){
			GameRegistry.registerItem(itemList.get(i), itemList.get(i).getUnlocalizedName());
			registerRender(itemList.get(i));
		}
	}
	public static void registerRender(Item item)
	{
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0, new ModelResourceLocation(Reference.MOD_ID + ":" + item.getUnlocalizedName().substring(5), "inventory"));
	}

}
