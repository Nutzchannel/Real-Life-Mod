package de.ItsAMysterious.mods.reallifemod;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import de.ItsAMysterious.mods.reallifemod.api.Materials.Materials;
import de.ItsAMysterious.mods.reallifemod.core.blocks.Industries.blockFurnace;
import de.ItsAMysterious.mods.reallifemod.core.blocks.Industries.blockMeltedIron;
import de.ItsAMysterious.mods.reallifemod.core.blocks.furniture.BlockComputer;
import de.ItsAMysterious.mods.reallifemod.core.blocks.furniture.BlockFreezer;
import de.ItsAMysterious.mods.reallifemod.core.blocks.furniture.BlockNeonLamp;
import de.ItsAMysterious.mods.reallifemod.core.blocks.furniture.BlockSafe;
import de.ItsAMysterious.mods.reallifemod.core.blocks.furniture.blockChair;
import de.ItsAMysterious.mods.reallifemod.core.blocks.furniture.blockCupboard;
import de.ItsAMysterious.mods.reallifemod.core.blocks.furniture.blockHeating;
import de.ItsAMysterious.mods.reallifemod.core.blocks.furniture.blockRadio;
import de.ItsAMysterious.mods.reallifemod.core.blocks.furniture.blockTV;
import de.ItsAMysterious.mods.reallifemod.core.blocks.furniture.blockTable;
import de.ItsAMysterious.mods.reallifemod.core.blocks.furniture.blockToaster;
import de.ItsAMysterious.mods.reallifemod.core.blocks.indoor.FloorTile;
import de.ItsAMysterious.mods.reallifemod.core.blocks.indoor.sanitary.BlockPissoir;
import de.ItsAMysterious.mods.reallifemod.core.blocks.indoor.sanitary.BlockWashingbasin;
import de.ItsAMysterious.mods.reallifemod.core.blocks.indoor.sanitary.blockToilet;
import de.ItsAMysterious.mods.reallifemod.core.blocks.outdoor.BlockBuchs;
import de.ItsAMysterious.mods.reallifemod.core.blocks.outdoor.bankBlock;
import de.ItsAMysterious.mods.reallifemod.core.blocks.outdoor.blockRooftile;
import de.ItsAMysterious.mods.reallifemod.core.blocks.outdoor.fireplaceBlock;
import de.ItsAMysterious.mods.reallifemod.core.blocks.outdoor.pillarBlock;
import de.ItsAMysterious.mods.reallifemod.core.blocks.outdoor.pillarBlock.pillarType;
import de.ItsAMysterious.mods.reallifemod.core.blocks.publicEnvironment.blockATM;
import de.ItsAMysterious.mods.reallifemod.core.blocks.BlockAirExtended;
import de.ItsAMysterious.mods.reallifemod.core.blocks.BlockDrinksMachine;
import de.ItsAMysterious.mods.reallifemod.core.blocks.ResourceBlock;
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
	christmaspyramid, christmastree, BlockDoorwreath, Marmor,cb, pillar, safe,bank, table, radio, fireplace, air,
	toaster, atm, heating, rooftile, toilet;
	public static Fluid meltedI = new Fluid("meltedIron");
	public static List<Block> tlmBlockList = new ArrayList<Block>();
	
	public static void defineBlocks(){
		FluidRegistry.registerFluid(meltedI);
		tlmBlockList.add(meltedIron = new blockMeltedIron(meltedI).setBlockName("ironmeldt"));
		meltedI.setUnlocalizedName(meltedIron.getUnlocalizedName());
		tlmBlockList.add(boxbush = new BlockBuchs(Material.grass).setCreativeTab(TLM.Outdoor).setStepSound(Block.soundTypeGrass));
		tlmBlockList.add(advertisingShield = new blockBilboard(Material.wood).setCreativeTab(TLM.TLMIndustry));
		tlmBlockList.add(tile = new FloorTile(Material.rock).setCreativeTab(TLM.TLMIndustry));
		tlmBlockList.add(blastFurnace = new blockFurnace().setCreativeTab(TLM.TLMIndustry));
		tlmBlockList.add(freezer = new BlockFreezer().setCreativeTab(TLM.Furniture));
		tlmBlockList.add(cupboard=new blockCupboard().setCreativeTab(TLM.Furniture));
		tlmBlockList.add(PC=new BlockComputer(Material.iron).setCreativeTab(TLM.Furniture));
		tlmBlockList.add(chair=new blockChair().setCreativeTab(TLM.Furniture));
		tlmBlockList.add(televison=new blockTV().setCreativeTab(TLM.Furniture));
		tlmBlockList.add(SidewalkRim=new SidewalkRim().setCreativeTab(TLM.Streets));
		tlmBlockList.add(laterne=new BlockLantern().setCreativeTab(TLM.Streets));
		tlmBlockList.add(TrafficLight=new BlockTrafficLight().setCreativeTab(TLM.Streets));
		tlmBlockList.add(blockTar=new BlockTar().setCreativeTab(TLM.Streets));
		tlmBlockList.add(BlockTarLine=new BlockTar().setBlockTextureName("reallifemod:tarWhiteLine").setBlockName("whiteline1").setCreativeTab(TLM.Streets));
		tlmBlockList.add(BlockTarLine2=new BlockTar().setBlockTextureName("reallifemod:tarWhiteLine2	").setBlockName("whiteline2").setCreativeTab(TLM.Streets));
		tlmBlockList.add(DrinksMachine=new BlockDrinksMachine().setCreativeTab(TLM.Furniture));
		tlmBlockList.add(NeonLamp=new BlockNeonLamp().setCreativeTab(TLM.Furniture));
		tlmBlockList.add(pissoir=new BlockPissoir().setCreativeTab(TLM.Furniture));
		tlmBlockList.add(basin=new BlockWashingbasin().setCreativeTab(TLM.Furniture));
		tlmBlockList.add(trafficlightmanager= new blockTrafficManager().setCreativeTab(TLM.Streets));
		tlmBlockList.add(christmaspyramid=new blockChristmasPyramid().setCreativeTab(TLM.seasonalstuff));
		tlmBlockList.add(christmastree=new blockChristmasTree().setCreativeTab(TLM.seasonalstuff));
		tlmBlockList.add(BlockDoorwreath=new doorwreath().setCreativeTab(TLM.seasonalstuff));
		tlmBlockList.add(Marmor=new ResourceBlock("marmor",Material.rock));
		tlmBlockList.add(cb=new crashbarrierBl().setCreativeTab(TLM.Streets));
		tlmBlockList.add(pillar=new pillarBlock(Material.rock,pillarType.ROCK).setBlockTextureName("minecraft:brick").setBlockName("pillar-bricks"));
		tlmBlockList.add(pillar=new pillarBlock(Material.iron, pillarType.IRON).setBlockTextureName("minecraft:iron_block").setBlockName("pillar-iron"));
		tlmBlockList.add(pillar=new pillarBlock(Materials.marmor, pillarType.MARMOR).setBlockTextureName("tlm:marmor").setBlockName("pillar-marmor"));
		tlmBlockList.add(safe=new BlockSafe().setCreativeTab(TLM.Furniture));
		tlmBlockList.add(bank=new bankBlock().setCreativeTab(TLM.Outdoor));
		tlmBlockList.add(bank=new blockTable().setCreativeTab(TLM.Furniture));
		tlmBlockList.add(radio=new blockRadio().setCreativeTab(TLM.Furniture));
		tlmBlockList.add(fireplace=new fireplaceBlock().setCreativeTab(TLM.Outdoor));
		tlmBlockList.add(toaster=new blockToaster().setCreativeTab(TLM.Furniture));
		tlmBlockList.add(atm=new blockATM().setCreativeTab(TLM.TLMEverydaylife));
		tlmBlockList.add(heating=new blockHeating().setCreativeTab(TLM.TLMEverydaylife));
		tlmBlockList.add(rooftile=new blockRooftile().setCreativeTab(TLM.Outdoor));
		tlmBlockList.add(toilet=new blockToilet().setCreativeTab(TLM.Furniture));
		GameRegistry.registerBlock(air=new BlockAirExtended(), "airExtended");

	}
	
	public static void registerBlocks(){
		for(int i=0; i<tlmBlockList.size();i++){
			GameRegistry.registerBlock(tlmBlockList.get(i), tlmBlockList.get(i).getUnlocalizedName());
		}
		//GameRegistry.registerBlock(meltedIron, "TLM" + "_"+ meltedIron.getUnlocalizedName().substring(5));
	}


}
