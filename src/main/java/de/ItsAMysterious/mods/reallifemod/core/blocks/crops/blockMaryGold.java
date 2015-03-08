package de.ItsAMysterious.mods.reallifemod.core.blocks.crops;

import java.util.Random;

import net.minecraft.block.BlockBush;

public class blockMaryGold extends BlockBush {
    private IIcon[] blockIcon;

	public blockMaryGold() {
		this.setBlockName("marygold");
		this.setBlockTextureName("reallifemod:marygold");
		this.setStepSound(soundTypeGrass);
	}

	public int quantityDropped(Random random){
		return 1;
	}
	
}
