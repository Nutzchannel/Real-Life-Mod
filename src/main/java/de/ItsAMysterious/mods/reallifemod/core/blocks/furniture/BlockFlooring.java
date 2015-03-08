package de.ItsAMysterious.mods.reallifemod.core.blocks.furniture;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockFlooring extends Block{

	public BlockFlooring(Material p_i45394_1_) {
		super(p_i45394_1_);
		this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.001F, 1.0F);
	}
	
    public boolean isOpaqueCube()
    {
        return false;
    }
    

    public boolean isNormalCube()
    {
        return false;
    }
    
}
