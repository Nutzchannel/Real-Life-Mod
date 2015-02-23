package de.ItsAMysterious.mods.reallifemod.core.blocks.furniture;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.ITileEntityProvider;
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
import de.ItsAMysterious.mods.reallifemod.core.tiles.neonlampTE;
import de.ItsAMysterious.mods.reallifemod.core.tiles.safeTE;


public class blockSafe extends BlockContainer implements ITileEntityProvider{


        public blockSafe() {
                super(Material.glass);
                setBlockName("showcase");
                setBlockTextureName("reallifemod:showcase");
                this.setLightLevel(0.9375F);
                this.setLightOpacity(0)	;
        }

        @Override
        public TileEntity createNewTileEntity(World world,int var2) {
            return new  safeTE();
        }
        
        @Override
        public int getRenderType() {
           return -1;
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
        public void setBlockBoundsBasedOnState(IBlockAccess world, int x, int y, int z) {
    		super.setBlockBoundsBasedOnState(world, x, y, z);
    		switch(world.getBlockMetadata(x, y, z)){
    			//North
    			case 0:{
    				this.setBlockBounds(-0.5F, 0F, -0.5F, 1.5F, 2.3F, 1.5F);
    				break;
    			}
    			//West
    			case 1:{
    				this.setBlockBounds(-0.5F, 0F, -0.5F, 1.5F, 2.3F, 1.5F);
    				break;
    			}
    			//South
    			case 2:{
    				this.setBlockBounds(-0.5F, 0F, -0.5F, 1.5F, 2.3F, 1.5F);
    				break;
    			}
    			//East
    			case 3:{
    				this.setBlockBounds(-0.5F, 0F, -0.5F, 1.5F, 2.3F, 1.5F);
    				break;
    			}

    			}
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