package de.ItsAMysterious.mods.reallifemod.core.blocks.indoor.sanitary;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import de.ItsAMysterious.mods.reallifemod.api.Materials.Materials;
import de.ItsAMysterious.mods.reallifemod.api.entity.properties.RealLifeProps;
import de.ItsAMysterious.mods.reallifemod.client.ClientProxy;
import de.ItsAMysterious.mods.reallifemod.core.rendering.TileEntitys.pissoirTE;


public class BlockPissoir extends BlockContainer implements ITileEntityProvider{


        public BlockPissoir() {
                super(Materials.plastic);
                setBlockName("pissoir");
        }

        @Override
		public boolean onBlockActivated(World p_149727_1_, int p_149727_2_, int p_149727_3_, int p_149727_4_, EntityPlayer player, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_)
        {
        	RealLifeProps props=(RealLifeProps)player.getExtendedProperties("RealLifeProps");
        	if(RealLifeProps.Toilet>0){
        		props.setPissing();
        	}
            return true;
        }

        
        @Override
        public TileEntity createNewTileEntity(World world,int var2) {
            return new  pissoirTE();
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
            if (te != null && te instanceof pissoirTE)
            {
            	pissoirTE tet = (pissoirTE) te;
                tet.setFacingDirection(newFacing);
                world.markBlockForUpdate(i, j, k);
            }
        }
}