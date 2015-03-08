package de.ItsAMysterious.mods.reallifemod.core.blocks.furniture;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import de.ItsAMysterious.mods.reallifemod.api.util.Materials;
import de.ItsAMysterious.mods.reallifemod.client.ClientProxy;
import de.ItsAMysterious.mods.reallifemod.core.tiles.neonlampTE;


public class blockNeonLamp extends BlockContainer implements ITileEntityProvider{


        public blockNeonLamp() {
                super(Materials.plastic);
                this.setBlockName("neonlamp");
                this.setBlockTextureName("reallifemod:iconNeonlamp");
                this.setBlockBounds(-0.3F, 0.7F, 0.3F, 1.3F, 1.0F, 0.7F);
                this.setLightLevel(0.9375F);
                this.setLightOpacity(0)	;
        }

        @Override
		public void onBlockDestroyedByPlayer(World world, int par1, int par2, int par3, int par4) 
        {
        	this.dropBlockAsItem(world, par1, par2, par3, new ItemStack(this));
		}
        
        @Override
        public TileEntity createNewTileEntity(World world,int var2) {
            return new  neonlampTE();
        }
        
        @Override
		public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer p_149727_5_, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_) {
            TileEntity te = world.getTileEntity(x, y, z);
            if (te != null && te instanceof neonlampTE)
            {
            	neonlampTE tet = (neonlampTE) te;
            	tet.isActive=!tet.isActive;
                world.markBlockForUpdate(x, y, z);
            	return true;
            }else
        	return false;
        };
        
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