package de.ItsAMysterious.mods.reallifemod.core.blocks.street;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import cpw.mods.fml.client.FMLClientHandler;
import de.ItsAMysterious.mods.reallifemod.client.ClientProxy;
import de.ItsAMysterious.mods.reallifemod.core.tiles.lanteernTE;


public class BlockLantern extends BlockContainer implements ITileEntityProvider{

        public BlockLantern() {
                super(Material.iron);
                this.setBlockBounds( 0, 0, 0, 1, 3, 1);
                this.setBlockName("lantern");
                this.useNeighborBrightness=false;
        }
        
        @Override
		public boolean canProvidePower()
        {
            return true;
        }


        @Override
        public void onBlockDestroyedByPlayer(World world, int par1, int par2, int par3, int par4) 
        {
        	this.dropBlockAsItem(world, par1, par2, par3, new ItemStack(this));
		}
        
        @Override
		public void updateTick(World world, int x, int y, int z, Random rand) {
        	super.updateTick(world, x, y, z, rand);
            if (world.getBlockMetadata(x, y, z) == 0)
            {
                this.onBlockAdded(world, x, y, z);
            }
    		lanteernTE tl= (lanteernTE) world.getTileEntity(x, y, z);
    		
    		if(tl.getWorldObj().isDaytime()!=true){
    			tl.active=true;
    		}
    		if(tl.getWorldObj().isDaytime()==true)
    		{
    			tl.active=false;
    		}
    		
    		if(tl.active==true){
    			this.setLightLevel(0.9213f);
    		}
    		if(tl.active!=true){
    			this.setLightLevel(0.0f);
    		}
    		
			this.onBlockActivated(world, x, y, z, FMLClientHandler.instance().getClientPlayerEntity(), 0, 0, 0, blockHardness);
    	}
        
        
        @Override
        public TileEntity createNewTileEntity(World world,int var2) {
            return new lanteernTE();
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
        public boolean isNormalCube() {
                return false;
        }
        
        @Override
		public void onNeighborBlockChange(World world, int x, int y, int z, Block block)
        {
            if (world.isBlockIndirectlyGettingPowered(x, y, z))
            {
        		lanteernTE tl= (lanteernTE) world.getTileEntity(x, y, z);
        		tl.active=!tl.active;
    			world.markBlockForUpdate(x, y, z);
            }
        }
        
        @Override
        public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_)
        {
        		TileEntity tl= world.getTileEntity(x, y, z);
        		if(tl!=null && tl instanceof lanteernTE){
        			lanteernTE tile=(lanteernTE)world.getTileEntity(x, y, z);
        			tile.active=!tile.active;
        			world.markBlockForUpdate(x, y, z);
        		}
				return true;
        }
        
        @Override
		public int tickRate(World p_149738_1_)
        {
            return 10;
        }
        
    	@Override
    	public void onBlockAdded(World world, int x, int y, int z){
        	world.scheduleBlockUpdate(x, y, z, this, this.tickRate(world) + world.rand.nextInt(10));
    		EntityPlayer entity = Minecraft.getMinecraft().thePlayer;
    		if(entity!=null&&world!=null){
    		int le = MathHelper.floor_double((double)(entity.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
    		world.setBlockMetadataWithNotify(x, y, z, le, 2);
    		}
    		world.markBlockForUpdate(x, y, z);
    	}
        
        private boolean func_150107_m(World p_150107_1_, int p_150107_2_, int p_150107_3_, int p_150107_4_)
        {
            if (World.doesBlockHaveSolidTopSurface(p_150107_1_, p_150107_2_, p_150107_3_, p_150107_4_))
            {
                return true;
            }
            else return false;
        }

        @Override
		public boolean canPlaceBlockAt(World p_149742_1_, int p_149742_2_, int p_149742_3_, int p_149742_4_)
        {
            return func_150107_m(p_149742_1_, p_149742_2_, p_149742_3_ - 1, p_149742_4_);
        }



}