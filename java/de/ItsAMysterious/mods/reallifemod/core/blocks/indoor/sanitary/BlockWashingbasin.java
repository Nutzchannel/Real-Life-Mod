package de.ItsAMysterious.mods.reallifemod.core.blocks.indoor.sanitary;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import de.ItsAMysterious.mods.reallifemod.TLMItems;
import de.ItsAMysterious.mods.reallifemod.api.Materials.Materials;
import de.ItsAMysterious.mods.reallifemod.client.ClientProxy;
import de.ItsAMysterious.mods.reallifemod.core.rendering.TileEntitys.washbasinTE;


public class BlockWashingbasin extends BlockContainer implements ITileEntityProvider{


        public BlockWashingbasin() {
                super(Materials.plastic);
                setBlockName("washbasin");
        }

        @Override
		public void onBlockDestroyedByPlayer(World world, int par1, int par2, int par3, int par4) 
        {
        	this.dropBlockAsItem(world, par1, par2, par3, new ItemStack(this));
		}
        
        @Override
        public TileEntity createNewTileEntity(World world,int var2) {
            return new  washbasinTE();
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
		public boolean isNormalCube(){
    		return false;
    	}
    	
        @Override
		public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int x2, float x3, float y3, float z3)
        {
        	if(player.inventory.getCurrentItem()!=null&&player.inventory.getCurrentItem().getItem()==TLMItems.emptybottle){
    			player.inventory.setInventorySlotContents(player.inventory.currentItem, new ItemStack(TLMItems.bottle));
                return true;
        	}else
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
            if (te != null && te instanceof washbasinTE)
            {
            	washbasinTE tet = (washbasinTE) te;
                tet.setFacingDirection(newFacing);
                world.markBlockForUpdate(i, j, k);
            }
        }
}