package de.ItsAMysterious.mods.reallifemod.core.blocks.furniture;

import de.ItsAMysterious.mods.reallifemod.TLMBlocks;
import de.ItsAMysterious.mods.reallifemod.client.ClientProxy;
import de.ItsAMysterious.mods.reallifemod.core.Renderers.blocks.models.modelChair;
import de.ItsAMysterious.mods.reallifemod.core.rendering.TileEntitys.tableTE;
import de.ItsAMysterious.mods.reallifemod.core.rendering.TileEntitys.tableTE.type;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class blockTable extends BlockContainer implements ITileEntityProvider{

	public static modelChair model=new modelChair();
	private static blockTable theBlock;
	private boolean inUse=false;

	
	public blockTable() {
		super(Material.wood);
		this.setBlockName("table");
		this.setBlockTextureName("tlm:table");
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
		return ClientProxy.getRenderID(this);
	}
	
    @Override
    public void onBlockPlacedBy(World world, int i, int j, int k, EntityLivingBase entityliving, ItemStack itemStack)
    {
        int facing = MathHelper.floor_double((entityliving.rotationYaw * 4F) / 360F + 0.5D) & 3;
        int newFacing = 0;
        if (facing == 0)
        {
        	newFacing = 2;
        }
        if (facing == 1)
        {
        	newFacing = 5;
        }
        if (facing == 2)
        {
        	newFacing = 3;
        }
        if (facing == 3)
        {
        	newFacing = 4;
        }
        tableTE te = (tableTE) world.getTileEntity(i, j, k);
        if (te != null && te instanceof tableTE)
        {
        	tableTE tet = te;
            tet.setFacingDirection(newFacing);
            world.markBlockForUpdate(i, j, k);
        }
    }
    
    @Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_){
    	return true;
    }
    
    @Override
	public void onBlockDestroyedByPlayer(World world, int x, int y, int z, int p_149664_5_) {
    }
    
    @Override
    public void onNeighborBlockChange(World world, int x, int y, int z, Block block) {
    	if(block==TLMBlocks.table){
    		for(int i=-1; i<1;i++){
        		for(int j=-1;j<1;j++){
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
    		
    	}
    	
    }



}
