package de.ItsAMysterious.mods.reallifemod.core.blocks.furniture;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import de.ItsAMysterious.mods.reallifemod.TLMBlocks;
import de.ItsAMysterious.mods.reallifemod.core.renderers.blocks.models.modelChair;
import de.ItsAMysterious.mods.reallifemod.core.tiles.tableTE;
import de.ItsAMysterious.mods.reallifemod.core.tiles.tableTE.type;

public class blockTable extends BlockContainer implements ITileEntityProvider{

	public blockTable() {
		super(Material.wood);
		this.setBlockName("table");
		this.setBlockTextureName("reallifemod:blockTable");
	}

	@Override
	public TileEntity createNewTileEntity(World world, int id) {
		return new tableTE();
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
	public int getRenderType(){
		return -1;
	}
	
	@Override
	public void onBlockAdded(World world, int x, int y, int z){
		EntityPlayer entity = Minecraft.getMinecraft().thePlayer;
		if(entity!=null&&world!=null){
		int le = MathHelper.floor_double((double)(entity.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
		world.setBlockMetadataWithNotify(x, y, z, le, 2);
		}
		world.markBlockForUpdate(x, y, z);
	}
      
    @Override
    public void onNeighborBlockChange(World world, int x, int y, int z, Block block) {
    	if(block==TLMBlocks.table){
    		for(int i=-1; i<1;i++){
        		for(int j=-1;j<1;j++){
        			if(world.getTileEntity(x+i, y, z+j)!=null&&world.getTileEntity(x+i, y, z+j)instanceof tableTE){
    			tableTE table=(tableTE) world.getTileEntity(x+i, y, z+j);
    				tableTE.tableType=type.plate;
    				if(i<0){
    					table.leftleg=true;
    					table.leflegback=true;
    				}
    				if(i>0){
    					table.rightleg=true;
    					table.rightlegback=true;
    					}
        		}
    		}
    		
		}}    	
    }



}
