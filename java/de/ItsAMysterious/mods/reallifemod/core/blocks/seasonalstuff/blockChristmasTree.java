package de.ItsAMysterious.mods.reallifemod.core.blocks.seasonalstuff;

import de.ItsAMysterious.mods.reallifemod.client.ClientProxy;
import de.ItsAMysterious.mods.reallifemod.core.rendering.TileEntitys.ChristmasTreeTE;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class blockChristmasTree extends BlockContainer implements ITileEntityProvider{

	public blockChristmasTree() {
		super(Material.leaves);
		this.setBlockName("christmastree");
	}

	@Override
	public TileEntity createNewTileEntity(World world, int id) {
		return new ChristmasTreeTE();
	}
	
	@Override
	public boolean isNormalCube(){
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
        TileEntity te = world.getTileEntity(i, j, k);
        if (te != null && te instanceof ChristmasTreeTE)
        {
        	ChristmasTreeTE tet = (ChristmasTreeTE) te;
            tet.setFacingDirection(newFacing);
            world.markBlockForUpdate(i, j, k);
        }
    }
    
    @Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer p_149727_5_, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_)
    {	
    	float light=0;
    	ChristmasTreeTE tile=(ChristmasTreeTE) world.getTileEntity(x, y, z);
    	if(tile instanceof ChristmasTreeTE){
		if(tile.isActive==true){
			tile.isActive=false;
			light=0;
		}else
		if(tile.isActive==false){
			tile.isActive=true;
			light=0.7f;
		}
		tile.getWorldObj().getBlock(x, y, z).setLightLevel(light);
    	}
    	world.markBlockForUpdate(x, y, z);
        return true;
    }

}
