package de.ItsAMysterious.mods.reallifemod.core.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

public class ResourceBlock extends Block {

	public ResourceBlock(Material p_i45394_1_) {
		super(p_i45394_1_);
	}
	
	public ResourceBlock(String Name,Material material) {
		super(material);
		this.setBlockName(Name);
		this.setBlockTextureName("reallifemod:"+Name);
		this.setCreativeTab(CreativeTabs.tabBlock);
	}

}
