package de.ItsAMysterious.mods.reallifemod.core.blocks.furniture;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import de.ItsAMysterious.mods.reallifemod.client.ClientProxy;
import de.ItsAMysterious.mods.reallifemod.core.rendering.TileEntitys.freezerTE;


public class BlockFreezer extends BlockContainer   {

        public BlockFreezer() {
                super(Material.iron);
                this.setBlockName("freezer");
        }

        @Override
		public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par1, float par2, float par3, float par4)
        {
        	TileEntity tileEntity=world.getTileEntity(x, y, z);
        	if(tileEntity!=null && tileEntity instanceof freezerTE){
        		freezerTE freezer=(freezerTE)tileEntity;
        		freezerTE.isOpen=!freezerTE.isOpen;
        		return true;
        	}else
        	return true;
        }
        
        @Override
		public void onBlockDestroyedByPlayer(World world, int par1, int par2, int par3, int par4) 
        {
        	this.dropBlockAsItem(world, par1, par2, par3, new ItemStack(this));
        	this.getBlocksMovement(world,(int)(par1+0.1),(int)( par2+0.5),(int)( par3+0.1));
        	
		}

        @Override
        public TileEntity createNewTileEntity(World world,int var2) {
            return new freezerTE();
        }
        
        @Override
        public int getRenderType() {
                return ClientProxy.getRenderID(this);
        }
        
        @Override
        public boolean isOpaqueCube() {
                return false;
        }
        
        @Override
		public boolean renderAsNormalBlock() {
                return false;
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
            if (te != null && te instanceof freezerTE)
            {
            	freezerTE tet = (freezerTE) te;
                tet.setFacingDirection(newFacing);
                world.markBlockForUpdate(i, j, k);
            }
        }
}