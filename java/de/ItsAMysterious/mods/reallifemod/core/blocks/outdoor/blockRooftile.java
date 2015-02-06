package de.ItsAMysterious.mods.reallifemod.core.blocks.outdoor;

import de.ItsAMysterious.mods.reallifemod.core.blocks.BlockAirExtended;
import de.ItsAMysterious.mods.reallifemod.core.rendering.TileEntitys.rooftileTE;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class blockRooftile extends BlockContainer implements ITileEntityProvider{


	public blockRooftile() {
		super(Material.wood);
		this.setBlockName("rooftile");
	}
	@Override
    public boolean isCollidable()
    {
        return true;
    }

	@Override	
	public TileEntity createNewTileEntity(World world, int id) {
		return new rooftileTE();
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
	public int tickRate(World p_149738_1_)
    {
        return 1;
    }
    
    @Override
	public void onBlockAdded(World world, int x, int y, int z)
    {
    	world.scheduleBlockUpdate(x, y, z, this, this.tickRate(world) + world.rand.nextInt(1));
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
        TileEntity te = world.getTileEntity(i, j, k);
        if (te != null && te instanceof rooftileTE)
        {
        	rooftileTE tet = (rooftileTE) te;
            tet.setFacingDirection(newFacing);
            world.markBlockForUpdate(i, j, k);
        }
        for(int x=0;x<5;x++)
        	for (int y = 0; y < 3; y++)
            	for (int z = 0; z < 5; z++)
            	{
            		if((world.getBlock(i+x, j+y, k+z))instanceof BlockAirExtended){
                		BlockAirExtended airblock=(BlockAirExtended)world.getBlock(i+x, j+y, k+z);
                		BlockAirExtended.TemperaturC=30;
            		}
            		world.markBlockForUpdate(i+x, j+y, k+z);
            	}
        }
    
    
}
