package de.ItsAMysterious.mods.reallifemod;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class TLMRecipes {

	public static void addRecipes(){
		GameRegistry.addRecipe(new ItemStack(TLMItems.cracker), "SSS", "TTT", "XXX", 'S',TLMItems.salt, 'T', TLMItems.keksteig);
		GameRegistry.addRecipe(new ItemStack(TLMItems.steelAxe), "XKK", "XSK", "XSX",'S', Items.stick, 'K', TLMItems.ingotSteel);
		GameRegistry.addRecipe(new ItemStack(TLMItems.chips, 4), "XXX", "XKX", "XXX",'K', Items.baked_potato);
		GameRegistry.addRecipe(new ItemStack(TLMBlocks.blastFurnace),"BBB","BLB","BCB",'C',Items.cauldron, 'L', Items.lava_bucket, 'B', Blocks.brick_block);
		GameRegistry.addRecipe(new ItemStack(TLMBlocks.basin), "XIX","MWM","XMX",'I', Items.iron_ingot, 'M', TLMBlocks.Marmor, 'W', Items.water_bucket);
		GameRegistry.addRecipe(new ItemStack(TLMBlocks.fireplace), "XSX","SLS","RRR",'S', Items.stick, 'L', Blocks.leaves, 'R', Blocks.stone);
		GameRegistry.addRecipe(new ItemStack(TLMBlocks.toaster), "IXI","IRI","WXW",'I', Items.iron_ingot, 'R', Items.redstone, 'W', Items.stick);
		GameRegistry.addRecipe(new ItemStack(TLMBlocks.PC), "III","ICR","ICR",'I', Items.iron_ingot, 'R', Items.redstone, 'C', TLMItems.circuitBrd);
		GameRegistry.addRecipe(new ItemStack(TLMBlocks.pillarB), "XBX","XBX","XBX",'B', Blocks.brick_block);
		GameRegistry.addRecipe(new ItemStack(TLMBlocks.pillarM), "XMX","XMX","XMX",'M', TLMBlocks.Marmor);
		GameRegistry.addRecipe(new ItemStack(TLMBlocks.pillarI), "XIX","XIX","XIX",'I', Blocks.iron_block);
		GameRegistry.addRecipe(new ItemStack(TLMBlocks.pissoir), "XXM","MXM","XMX",'M', TLMBlocks.Marmor);
		GameRegistry.addRecipe(new ItemStack(TLMBlocks.toilet), "MXM","MWM","XMM",'M', TLMBlocks.Marmor,'W',Items.water_bucket);
		GameRegistry.addRecipe(new ItemStack(TLMBlocks.toilet), "MXM","MWM","XMM",'M',TLMBlocks.Marmor,'W',Items.water_bucket);
		GameRegistry.addRecipe(new ItemStack(TLMBlocks.growpot), "CXC","CXC","CCC",'C', Items.clay_ball);

	}
	
	public static void addSmeltings(){
		GameRegistry.addSmelting(Blocks.coal_block, new ItemStack(TLMItems.coke), 0);
		GameRegistry.addSmelting(Items.bread, new ItemStack(TLMItems.toast), 0);
	}
}
