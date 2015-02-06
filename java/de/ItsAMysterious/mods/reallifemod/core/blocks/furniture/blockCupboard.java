package de.ItsAMysterious.mods.reallifemod.core.blocks.furniture;

import de.ItsAMysterious.mods.reallifemod.client.ClientProxy;
import de.ItsAMysterious.mods.reallifemod.core.Renderers.blocks.models.modelCupboard;
import de.ItsAMysterious.mods.reallifemod.core.rendering.TileEntitys.cupboardTE;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDirectional;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class blockCupboard extends BlockDirectional implements ITileEntityProvider{
	public static modelCupboard model=new modelCupboard();
	private static blockCupboard blockCupboard;

	
	public blockCupboard() {
		super(Material.wood);
    	this.setBlockBounds(-1, 0, 0, 2.5F, 2, 1);
		this.setBlockName("cupboard");
	}

	@Override
	public TileEntity createNewTileEntity(World world, int id) {
		cupboardTE t=new cupboardTE();
		t.model=blockCupboard.model;
		return t;
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
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int p_149727_6_, float posX, float posY, float posZ)
    {
    	cupboardTE c=(cupboardTE)world.getTileEntity(x, y, z);
    	if(c.open==true){
			if(modelCupboard.LeftDoor.rotateAngleY>0)
				modelCupboard.LeftDoor.rotateAngleY--;
    		c.open=false;

    	}else
    	if(c.open==false){
			if(modelCupboard.LeftDoor.rotateAngleY<90)
				modelCupboard.LeftDoor.rotateAngleY++;
    		c.open=true;
    	}
		world.markBlockForUpdate(x, y, z);
        return true;
    }
    
    @Override
	public void breakBlock(World world, int x, int y, int z, Block theBlock, int p_149749_6_)
    {
    	this.dropBlockAsItem(world, x, y, z, new ItemStack(this));
        world.removeTileEntity(x, y, z);
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
        if (te != null && te instanceof cupboardTE)
        {
        	cupboardTE tet = (cupboardTE) te;
            tet.setFacingDirection(newFacing);
            world.markBlockForUpdate(i, j, k);
        }
    }

}
