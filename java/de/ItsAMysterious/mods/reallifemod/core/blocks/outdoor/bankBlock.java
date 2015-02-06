package de.ItsAMysterious.mods.reallifemod.core.blocks.outdoor;

import de.ItsAMysterious.mods.reallifemod.client.ClientProxy;
import de.ItsAMysterious.mods.reallifemod.core.rendering.TileEntitys.bankTE;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class bankBlock extends BlockContainer implements ITileEntityProvider{

	public bankBlock() {
		super(Material.wood);
		this.setBlockName("bank");
		this.setBlockTextureName("reallifemod:bank");
	}
	
    @Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_)
    {
        bankTE te = (bankTE) world.getTileEntity(x, y, z);
		if(te.isSitting()==false){
			te.sitdown(player,1.0D);
		}else
		{
			te.dismountEntity();
		}
		player.addChatMessage(new ChatComponentText("Press L_SHIFT to stand up!"));
		world.markBlockForUpdate(x, y, z);
        return true;
    }
    
    @Override
	public TileEntity createNewTileEntity(World world, int id) {
		return new bankTE();
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
		return ClientProxy.getRenderID(this);
	}
	  
    @Override
    public void onBlockPlacedBy(World world, int i, int j, int k, EntityLivingBase entityliving, ItemStack itemStack)
    {
        int facing = MathHelper.floor_double((entityliving.rotationYaw * 4F) / 360F + 0.5D) & 3;
        int newFacing = 0;
        if (facing == 0)
        {
        	newFacing = 3;
        }
        if (facing == 1)
        {
        	newFacing = 5;
        }
        if (facing == 2)
        {
        	newFacing = 2;
        }
        if (facing == 3)
        {
        	newFacing = 4;
        }
               
        bankTE te = (bankTE) world.getTileEntity(i, j, k);
        if (te != null && te instanceof bankTE)
        {
        	bankTE tile = te;
            tile.setFacingDirection(newFacing);
        	double f=world.getBlock(i,j-1, k).getBlockBoundsMaxY();
        	tile.setHeightToSubstract(1-f);
            world.markBlockForUpdate(i, j, k);
        }
    }

    @Override
	public void onBlockDestroyedByPlayer(World world, int x, int y, int z, int p_149664_5_) {
    }


}
