package de.ItsAMysterious.mods.reallifemod;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import de.ItsAMysterious.mods.reallifemod.api.util.Materials;
import de.ItsAMysterious.mods.reallifemod.core.blocks.BlockAirExtended;
import de.ItsAMysterious.mods.reallifemod.core.blocks.ResourceBlock;
import de.ItsAMysterious.mods.reallifemod.core.blocks.Industries.blockFurnace;
import de.ItsAMysterious.mods.reallifemod.core.blocks.Industries.blockMeltedIron;
import de.ItsAMysterious.mods.reallifemod.core.blocks.crops.blockMaryGold;
import de.ItsAMysterious.mods.reallifemod.core.blocks.furniture.BlockComputer;
import de.ItsAMysterious.mods.reallifemod.core.blocks.furniture.BlockDesk;
import de.ItsAMysterious.mods.reallifemod.core.blocks.furniture.BlockFlooring;
import de.ItsAMysterious.mods.reallifemod.core.blocks.furniture.BlockFreezer;
import de.ItsAMysterious.mods.reallifemod.core.blocks.furniture.blockChair;
import de.ItsAMysterious.mods.reallifemod.core.blocks.furniture.blockCupboard;
import de.ItsAMysterious.mods.reallifemod.core.blocks.furniture.blockDishwasher;
import de.ItsAMysterious.mods.reallifemod.core.blocks.furniture.blockEspresso;
import de.ItsAMysterious.mods.reallifemod.core.blocks.furniture.blockGrowpot;
import de.ItsAMysterious.mods.reallifemod.core.blocks.furniture.blockHeating;
import de.ItsAMysterious.mods.reallifemod.core.blocks.furniture.blockNeonLamp;
import de.ItsAMysterious.mods.reallifemod.core.blocks.furniture.blockRadio;
import de.ItsAMysterious.mods.reallifemod.core.blocks.furniture.blockRailing;
import de.ItsAMysterious.mods.reallifemod.core.blocks.furniture.blockSafe;
import de.ItsAMysterious.mods.reallifemod.core.blocks.furniture.blockShelf;
import de.ItsAMysterious.mods.reallifemod.core.blocks.furniture.blockSink;
import de.ItsAMysterious.mods.reallifemod.core.blocks.furniture.blockTV;
import de.ItsAMysterious.mods.reallifemod.core.blocks.furniture.blockTable;
import de.ItsAMysterious.mods.reallifemod.core.blocks.furniture.blockToaster;
import de.ItsAMysterious.mods.reallifemod.core.blocks.furniture.blockcabinet;
import de.ItsAMysterious.mods.reallifemod.core.blocks.furniture.blockfishtank;
import de.ItsAMysterious.mods.reallifemod.core.blocks.indoor.sanitary.BlockPissoir;
import de.ItsAMysterious.mods.reallifemod.core.blocks.indoor.sanitary.BlockWashingbasin;
import de.ItsAMysterious.mods.reallifemod.core.blocks.indoor.sanitary.blockToilet;
import de.ItsAMysterious.mods.reallifemod.core.blocks.outdoor.BlockBuchs;
import de.ItsAMysterious.mods.reallifemod.core.blocks.outdoor.FloorTile;
import de.ItsAMysterious.mods.reallifemod.core.blocks.outdoor.bankBlock;
import de.ItsAMysterious.mods.reallifemod.core.blocks.outdoor.blockRooftile;
import de.ItsAMysterious.mods.reallifemod.core.blocks.outdoor.fireplaceBlock;
import de.ItsAMysterious.mods.reallifemod.core.blocks.outdoor.pillarBlock;
import de.ItsAMysterious.mods.reallifemod.core.blocks.outdoor.pillarBlock.pillarType;
import de.ItsAMysterious.mods.reallifemod.core.blocks.publicenvironment.BlockDrinksMachine;
import de.ItsAMysterious.mods.reallifemod.core.blocks.publicenvironment.blockATM;
import de.ItsAMysterious.mods.reallifemod.core.blocks.seasonalstuff.blockChristmasPyramid;
import de.ItsAMysterious.mods.reallifemod.core.blocks.seasonalstuff.blockChristmasTree;
import de.ItsAMysterious.mods.reallifemod.core.blocks.seasonalstuff.doorwreath;
import de.ItsAMysterious.mods.reallifemod.core.blocks.street.BlockLantern;
import de.ItsAMysterious.mods.reallifemod.core.blocks.street.BlockTar;
import de.ItsAMysterious.mods.reallifemod.core.blocks.street.BlockTrafficLight;
import de.ItsAMysterious.mods.reallifemod.core.blocks.street.SidewalkRim;
import de.ItsAMysterious.mods.reallifemod.core.blocks.street.blockBilboard;
import de.ItsAMysterious.mods.reallifemod.core.blocks.street.blockTrafficManager;
import de.ItsAMysterious.mods.reallifemod.core.blocks.street.crashbarrierBl;

public class TLMBlocks {
	public static Block meltedIron,Kruppstahlblock,blastFurnace,freezer,KruppST,boxbush,PC,
	advertisingShield,tile,cupboard,chair, basin, televison,blockTar,SidewalkRim,BlockTarLine,
	BlockTarLine2,laterne,TrafficLight,DrinksMachine,NeonLamp,pissoir,mirror, deskLamp,	trafficlightmanager,
	christmaspyramid, christmastree, BlockDoorwreath, Marmor,cb, pillarM,pillarI,pillarB, safe,bank, table, radio, fireplace, air,
	toaster, atm, heating, rooftile, toilet,fishtank, kitchencabinet,shelf,dishwasher,sink,marygold, growpot,
	parquet,espresso, desk,railing,lenoleum,railingleft;
	public static Fluid meltedI = new Fluid("meltedIron");
	public static List<Block> tlmBlockList = new ArrayList<Block>();
	
	public static void defineBlocks(){
		FluidRegistry.registerFluid(meltedI);
		tlmBlockList.add(meltedIron = new blockMeltedIron(meltedI).setBlockName("ironmeldt"));
		meltedI.setUnlocalizedName(meltedIron.getUnlocalizedName());
		tlmBlockList.add(boxbush = new BlockBuchs(Material.grass).setCreativeTab(RealLifeMod.Outdoor).setStepSound(Block.soundTypeGrass));
		tlmBlockList.add(advertisingShield = new blockBilboard(Material.wood).setCreativeTab(RealLifeMod.TLMIndustry));
		tlmBlockList.add(tile = new FloorTile(Material.rock).setCreativeTab(RealLifeMod.TLMIndustry));
		tlmBlockList.add(blastFurnace = new blockFurnace().setCreativeTab(RealLifeMod.TLMIndustry));
		tlmBlockList.add(freezer = new BlockFreezer().setCreativeTab(RealLifeMod.Furniture));
		tlmBlockList.add(cupboard=new blockCupboard().setCreativeTab(RealLifeMod.Furniture));
		tlmBlockList.add(PC=new BlockComputer(Material.iron).setCreativeTab(RealLifeMod.Furniture));
		tlmBlockList.add(chair=new blockChair().setCreativeTab(RealLifeMod.Furniture));
		tlmBlockList.add(televison=new blockTV().setCreativeTab(RealLifeMod.Furniture));
		tlmBlockList.add(SidewalkRim=new SidewalkRim().setCreativeTab(RealLifeMod.Streets));
		tlmBlockList.add(laterne=new BlockLantern().setCreativeTab(RealLifeMod.Streets));
		tlmBlockList.add(TrafficLight=new BlockTrafficLight().setCreativeTab(RealLifeMod.Streets));
		tlmBlockList.add(blockTar=new BlockTar().setCreativeTab(RealLifeMod.Streets));
		tlmBlockList.add(BlockTarLine=new BlockTar().setBlockTextureName("reallifemod:tarWhiteLine").setBlockName("whiteline1").setCreativeTab(RealLifeMod.Streets));
		tlmBlockList.add(BlockTarLine2=new BlockTar().setBlockTextureName("reallifemod:tarWhiteLine2").setBlockName("whiteline2").setCreativeTab(RealLifeMod.Streets));
		tlmBlockList.add(DrinksMachine=new BlockDrinksMachine().setCreativeTab(RealLifeMod.Furniture));
		tlmBlockList.add(NeonLamp=new blockNeonLamp().setCreativeTab(RealLifeMod.Furniture));
		tlmBlockList.add(pissoir=new BlockPissoir().setCreativeTab(RealLifeMod.Furniture));
		tlmBlockList.add(basin=new BlockWashingbasin().setCreativeTab(RealLifeMod.Furniture));
		tlmBlockList.add(trafficlightmanager= new blockTrafficManager().setCreativeTab(RealLifeMod.Streets));
		tlmBlockList.add(christmaspyramid=new blockChristmasPyramid().setCreativeTab(RealLifeMod.Furniture));
		tlmBlockList.add(christmastree=new blockChristmasTree().setCreativeTab(RealLifeMod.Furniture));
		tlmBlockList.add(BlockDoorwreath=new doorwreath().setCreativeTab(RealLifeMod.Furniture));
		tlmBlockList.add(Marmor=new ResourceBlock("marmor",Material.rock));
		tlmBlockList.add(cb=new crashbarrierBl().setCreativeTab(RealLifeMod.Streets));
		tlmBlockList.add(pillarB=new pillarBlock(Material.rock,pillarType.ROCK).setBlockTextureName("minecraft:brick").setBlockName("pillar-bricks"));
		tlmBlockList.add(pillarI=new pillarBlock(Material.iron, pillarType.IRON).setBlockTextureName("minecraft:iron_block").setBlockName("pillar-iron"));
		tlmBlockList.add(pillarM=new pillarBlock(Materials.marmor, pillarType.MARMOR).setBlockTextureName("reallifemod:marmor").setBlockName("pillar-marmor"));
		tlmBlockList.add(safe=new blockSafe().setCreativeTab(RealLifeMod.Furniture));
		tlmBlockList.add(bank=new bankBlock().setCreativeTab(RealLifeMod.Outdoor));
		tlmBlockList.add(table=new blockTable().setCreativeTab(RealLifeMod.Furniture));
		tlmBlockList.add(radio=new blockRadio().setCreativeTab(RealLifeMod.Furniture));
		tlmBlockList.add(fireplace=new fireplaceBlock().setCreativeTab(RealLifeMod.Outdoor));
		tlmBlockList.add(toaster=new blockToaster().setCreativeTab(RealLifeMod.Furniture));
		tlmBlockList.add(atm=new blockATM().setCreativeTab(RealLifeMod.TLMEverydaylife));
		tlmBlockList.add(heating=new blockHeating().setCreativeTab(RealLifeMod.Furniture));
		tlmBlockList.add(rooftile=new blockRooftile().setCreativeTab(RealLifeMod.Outdoor));
		tlmBlockList.add(toilet=new blockToilet().setCreativeTab(RealLifeMod.Furniture));
		tlmBlockList.add(fishtank=new blockfishtank().setCreativeTab(RealLifeMod.Furniture));
		tlmBlockList.add(kitchencabinet=new blockcabinet().setCreativeTab(RealLifeMod.Furniture));
		tlmBlockList.add(shelf=new blockShelf().setCreativeTab(RealLifeMod.Furniture));
		tlmBlockList.add(dishwasher=new blockDishwasher().setCreativeTab(RealLifeMod.Furniture));
		tlmBlockList.add(sink=new blockSink().setCreativeTab(RealLifeMod.Furniture));
		tlmBlockList.add(marygold=new blockMaryGold().setCreativeTab(RealLifeMod.medicine));
		tlmBlockList.add(growpot=new blockGrowpot(Material.clay));
		tlmBlockList.add(parquet=new ResourceBlock(Material.wood).setBlockName("parquetBlock").setCreativeTab(RealLifeMod.Furniture).setBlockTextureName("reallifemod:parquet"));
		tlmBlockList.add(parquet=new BlockFlooring(Material.wood).setBlockName("parquet").setCreativeTab(RealLifeMod.Furniture).setBlockTextureName("reallifemod:parquet"));
		tlmBlockList.add(lenoleum=new BlockFlooring(Materials.plastic).setBlockName("lenoleum").setCreativeTab(RealLifeMod.Furniture).setBlockTextureName("reallifemod:lenoleum"));
		tlmBlockList.add(espresso=new blockEspresso().setCreativeTab(RealLifeMod.Furniture));
		tlmBlockList.add(desk=new BlockDesk().setCreativeTab(RealLifeMod.Furniture));
		tlmBlockList.add(railing=new blockRailing().setCreativeTab(RealLifeMod.Furniture));
		GameRegistry.registerBlock(air=new BlockAirExtended(), "airExtended");

	}
	
	public static void registerBlocks(){
		for(int i=0; i<tlmBlockList.size();i++){
			GameRegistry.registerBlock(tlmBlockList.get(i), tlmBlockList.get(i).getUnlocalizedName());
		}
		//GameRegistry.registerBlock(meltedIron, "RealLifeMod" + "_"+ meltedIron.getUnlocalizedName().substring(5));
	}


}
