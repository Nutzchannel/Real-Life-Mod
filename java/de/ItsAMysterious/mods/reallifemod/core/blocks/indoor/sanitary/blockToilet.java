package de.ItsAMysterious.mods.reallifemod.core.blocks.indoor.sanitary;

import java.util.List;

import de.ItsAMysterious.mods.reallifemod.client.ClientProxy;
import de.ItsAMysterious.mods.reallifemod.core.rendering.TileEntitys.toiletTE;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class blockToilet extends BlockContainer implements ITileEntityProvider{
	private static blockToilet theBlock;
	private boolean inUse=false;

	
	public blockToilet() {
		super(Material.wood);
		this.setBlockName("toiletBowl");
	}
	
	@Override
	public TileEntity createNewTileEntity(World world, int id) {
		return new toiletTE();
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
        TileEntity te = world.getTileEntity(i, j, k);
        if (te != null && te instanceof toiletTE)
        {
        	toiletTE tet = (toiletTE) te;
            tet.setFacingDirection(newFacing);
            world.markBlockForUpdate(i, j, k);
        }
    }
    
    @Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_){
		toiletTE chair=(toiletTE)world.getTileEntity(x, y, z);
    	if(world.getTileEntity(x, y, z)!=null && world.getTileEntity(x, y, z) instanceof toiletTE)
    		if(chair.isSitting()==false){
    			chair.sitdown(player,1.0D);
    		}else
    		{
    			chair.dismountEntity();
    		}
			world.markBlockForUpdate(x, y, z);
    		player.addChatMessage(new ChatComponentText("Press L_SHIFT to stand up!"));
    		player.addChatMessage(new ChatComponentText("Press 'p' to poop!"));
    	return true;
    }
    
	@Override
	public void addCollisionBoxesToList(World world, int x, int y, int z, AxisAlignedBB bounds, List list, Entity entity) {
		bounds.setBounds(0.0F, 0.0F, 0.0F, 0.0625F, 1.0F, 1.0F);
		super.addCollisionBoxesToList(world, x, y, z, bounds, list, entity);
		bounds.setBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 0.0625F);
		super.addCollisionBoxesToList(world, x, y, z, bounds, list, entity);
		bounds.setBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0625F);
		super.addCollisionBoxesToList(world, x, y, z, bounds, list, entity);
		bounds.setBounds(0.0F, 0.0F, 0.0F, 1.0F, 1 + 0.0625F, 1.0F);
		super.addCollisionBoxesToList(world, x, y, z, bounds, list, entity);
		bounds.setBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
	}
    
    @Override
	public void onBlockDestroyedByPlayer(World world, int x, int y, int z, int p_149664_5_) {
    }


}
