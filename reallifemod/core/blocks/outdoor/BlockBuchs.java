package de.ItsAMysterious.mods.reallifemod.core.blocks.outdoor;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockBuchs extends Block{

	public BlockBuchs(Material material) {
		super(material);
		setBlockTextureName("reallifemod:buchs");
		setBlockName("boxwood");
		getRenderType();
		setBlockBounds(0, 0, 0, 1, 1, 1);
	}
	@Override
	public boolean isOpaqueCube(){
		return false;
	}
	
	@Override
	public boolean isBlockNormalCube(){
		return false;
	}
	@Override
	public int getRenderType(){
		return 1;
		
	}
	
	

	

}
