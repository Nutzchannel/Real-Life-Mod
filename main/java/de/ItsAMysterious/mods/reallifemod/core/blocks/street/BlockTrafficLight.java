package de.ItsAMysterious.mods.reallifemod.core.blocks.street;

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
import de.ItsAMysterious.mods.reallifemod.client.ClientProxy;
import de.ItsAMysterious.mods.reallifemod.core.tiles.trafficlightTE;
import de.ItsAMysterious.mods.reallifemod.core.tiles.trafficlightTE.State;


public class BlockTrafficLight extends BlockContainer implements ITileEntityProvider{

        public BlockTrafficLight() {
                super(Material.iron);
                this.setBlockBounds( 0.25F, 0, 0.25F, 0.75F, 3, 0.75F);
                this.setBlockName("trafficlight");
                this.setBlockTextureName("reallifemod:iconTrafficlight");
        }

        @Override
		public void onBlockDestroyedByPlayer(World world, int par1, int par2, int par3, int par4) 
        {
        	this.dropBlockAsItem(world, par1, par2, par3, new ItemStack(this));
		}
        
        @Override
        public TileEntity createNewTileEntity(World world,int var2) {
                 
            return new trafficlightTE();
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
		public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_)
        {
        	trafficlightTE tl=(trafficlightTE)world.getTileEntity(x, y, z);
        	switch(tl.state){
        	case GREEN:
        		tl.state=State.ORANGE;
        		break;
        	case ORANGE:
        		tl.state=State.RED;
        		break;
        	case RED:
        		tl.state=State.INACTIVE;
        		break;
        	case INACTIVE:
        		tl.state=State.GREEN;
			default:
				break;
        	}
        	tl.delta=0;
        	world.markBlockForUpdate(x, y, z);
            return true;
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