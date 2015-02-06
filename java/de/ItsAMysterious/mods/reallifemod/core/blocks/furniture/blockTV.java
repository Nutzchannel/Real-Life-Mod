package de.ItsAMysterious.mods.reallifemod.core.blocks.furniture;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import de.ItsAMysterious.mods.reallifemod.api.Materials.Materials;
import de.ItsAMysterious.mods.reallifemod.client.ClientProxy;
import de.ItsAMysterious.mods.reallifemod.core.rendering.TileEntitys.TileEntityTV;


public class blockTV extends BlockContainer implements ITileEntityProvider{



        public blockTV() {
            super(Materials.plastic);
            this.setBlockName("tv");
        }

        @Override
		public void onBlockDestroyedByPlayer(World world, int par1, int par2, int par3, int par4) 
        {
        	this.dropBlockAsItem(world, par1, par2, par3, new ItemStack(this));
		}
        @Override
        public TileEntity createNewTileEntity(World world,int var2) {
            return new TileEntityTV();
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
            if (te != null && te instanceof TileEntityTV)
            {
            	TileEntityTV tet = (TileEntityTV) te;
                tet.setFacingDirection(newFacing);
                world.markBlockForUpdate(i, j, k);
            }
        }
        
        @Override
		public boolean onBlockActivated(World p_149727_1_, int p_149727_2_, int p_149727_3_, int p_149727_4_, EntityPlayer p_149727_5_, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_)
        {    	
            return false;
        }
}