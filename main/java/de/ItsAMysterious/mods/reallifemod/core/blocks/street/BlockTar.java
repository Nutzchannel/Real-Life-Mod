package de.ItsAMysterious.mods.reallifemod.core.blocks.street;

import de.ItsAMysterious.mods.reallifemod.api.util.Materials;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;

public class BlockTar extends Block{
	Minecraft mc;

	public BlockTar() {
		super(Material.rock);
		this.setBlockName("tarmac");
		this.setBlockTextureName("reallifemod:tar");
	}
	
}
