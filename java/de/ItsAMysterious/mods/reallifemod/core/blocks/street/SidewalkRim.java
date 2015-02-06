package de.ItsAMysterious.mods.reallifemod.core.blocks.street;

import de.ItsAMysterious.mods.reallifemod.api.Materials.Materials;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class SidewalkRim extends Block{

	public SidewalkRim() {
		super(Material.rock);
		this.setBlockTextureName("reallifemod:tar");
		this.setBlockName("sidewalk");
		this.setBlockBounds(0.0F,0.0F,0.0F,1.0F, 0.3F, 1.0F);
	}
	
    @Override
	public boolean isOpaqueCube()
    {
        return false;
    }

}
