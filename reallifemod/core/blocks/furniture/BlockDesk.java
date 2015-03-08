package de.ItsAMysterious.mods.reallifemod.core.blocks.furniture;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import de.ItsAMysterious.mods.reallifemod.client.ClientProxy;
import de.ItsAMysterious.mods.reallifemod.core.tiles.deskTE;


public class BlockDesk extends BlockContainer   {

        public BlockDesk() {
                super(Material.iron);
                this.setBlockName("desk");
                this.setBlockTextureName("reallifemod:desk");
        }

    	@Override
        public void setBlockBoundsBasedOnState(IBlockAccess world, int x, int y, int z) {
    		super.setBlockBoundsBasedOnState(world, x, y, z);
    		switch(world.getBlockMetadata(x, y, z)){
    			//North
    			case 0:{
    				this.setBlockBounds(-1.0F, 0F, 0.0F, 1.0F, 1F, 1.0F);
    				break;
    			}
    			//West
    			case 1:{
    				this.setBlockBounds(0.0F, 0.0F, -1.0F, 1F, 1F, 1.0F);
    				break;
    			}
    			//South
    			case 2:{
    				this.setBlockBounds(0.0F, 0F, 0.0F, 2.0F, 1F, 1.0F);
    				break;
    			}
    			//East
    			case 3:{
    				this.setBlockBounds(0.0F, 0.0F, 0.0F, 1F, 1F, 2.0F);
    				break;
    			}

    			}
    		}
    	
        @Override
		public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par1, float par2, float par3, float par4)
        {
        	TileEntity tileEntity=world.getTileEntity(x, y, z);
        	if(tileEntity!=null && tileEntity instanceof deskTE){
        		deskTE freezer=(deskTE)tileEntity;
        		//deskTE.isOpen=!deskTE.isOpen;
        		return true;
        	}else
        	return true;
        }
        
        @Override
        public TileEntity createNewTileEntity(World world,int var2) {
            return new deskTE();
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
    	public void onBlockAdded(World world, int x, int y, int z){
    		EntityPlayer entity = Minecraft.getMinecraft().thePlayer;
    		if(entity!=null&&world!=null){
    		int le = MathHelper.floor_double((double)(entity.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
    		world.setBlockMetadataWithNotify(x, y, z, le, 2);
    		}
    		world.markBlockForUpdate(x, y, z);
    	}
}