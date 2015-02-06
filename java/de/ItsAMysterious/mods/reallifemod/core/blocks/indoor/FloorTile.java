package de.ItsAMysterious.mods.reallifemod.core.blocks.indoor;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class FloorTile extends Block{

	public FloorTile(Material material) {
		super(material);
		this.setBlockName("tile");
		this.textureName="reallifemod:tile";
		this.setStepSound(soundTypeStone);
		this.setBlockBounds(0F, 0F, 0F, 1F, 0.1F, 1F);
	}
	
	@Override
	public boolean isOpaqueCube()
	{ 	
		return false;
	}

    @Override
	public boolean isNormalCube()
    {
        return false;
    }

}
