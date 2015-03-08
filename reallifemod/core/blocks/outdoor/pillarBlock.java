package de.ItsAMysterious.mods.reallifemod.core.blocks.outdoor;

import de.ItsAMysterious.mods.reallifemod.RealLifeMod;
import de.ItsAMysterious.mods.reallifemod.TLMBlocks;
import de.ItsAMysterious.mods.reallifemod.core.tiles.pillarTE;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class pillarBlock extends BlockContainer implements ITileEntityProvider{

	public static enum pillarType{
		MARMOR, ROCK, SANDSTONE, IRON;
	}
	public pillarType Type=pillarType.MARMOR;
	
	
	public pillarBlock(Material material) {
		super(material);
		this.setCreativeTab(RealLifeMod.Outdoor);
		this.setBlockTextureName(textureName);
	}
	
	public pillarBlock(Material material, pillarType mat) {
		this(material);
		this.setCreativeTab(RealLifeMod.Outdoor);
		this.Type=mat;
	}
	
    @Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_)
    {
    	if(player.inventory.getCurrentItem()!=null&&player.inventory.getCurrentItem().getItem()!=Item.getItemFromBlock(this)){
    		pillarTE te=(pillarTE)world.getTileEntity(x, y, z);
    		te.scale++;
    		if(te.scale==9.7F)
			te.scale=0.7F;
    		world.markBlockForUpdate(x, y, z);
    		return true;
    	}else
    	{
    		if(player.inventory.getCurrentItem()==null){
        		pillarTE te=(pillarTE)world.getTileEntity(x, y, z);
        		te.scale++;
        		if(te.scale==9.7F)
    			te.scale=0.7F;
        		world.markBlockForUpdate(x, y, z);
        		return true;
    		}
    		if(player.inventory.getCurrentItem()!=null&&player.inventory.getCurrentItem().getItem()==Item.getItemFromBlock(this)){
    			world.setBlock(x, y+1, z,Block.getBlockFromItem(player.inventory.getCurrentItem().getItem()));
    			return true;
    		}else
    		
    		return false;
    	}
    }

	@Override
	public TileEntity createNewTileEntity(World world, int id) {
		return new pillarTE(this.textureName);
	}
	
	@Override
	public boolean renderAsNormalBlock(){
		return false;
	}
	@Override
	public boolean isOpaqueCube(){
		return false;
	}
	
	@Override
	public String getTextureName(){
		return this.textureName;
	}
	
	@Override
	public int getRenderType(){
		return -1;
	}

}
