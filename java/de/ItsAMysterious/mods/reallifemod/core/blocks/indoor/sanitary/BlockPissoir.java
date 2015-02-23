package de.ItsAMysterious.mods.reallifemod.core.blocks.indoor.sanitary;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import de.ItsAMysterious.mods.reallifemod.api.entity.properties.RealLifeProps;
import de.ItsAMysterious.mods.reallifemod.api.util.Materials;
import de.ItsAMysterious.mods.reallifemod.client.ClientProxy;
import de.ItsAMysterious.mods.reallifemod.core.tiles.pissoirTE;


public class BlockPissoir extends BlockContainer implements ITileEntityProvider{


        public BlockPissoir() {
                super(Materials.plastic);
                this.setBlockName("urinal");
                this.setBlockTextureName("reallifemod:iconUrinal");
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
    	public void onBlockAdded(World world, int x, int y, int z){
    		EntityPlayer entity = Minecraft.getMinecraft().thePlayer;
    		if(entity!=null&&world!=null){
    		int le = MathHelper.floor_double((double)(entity.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
    		world.setBlockMetadataWithNotify(x, y, z, le, 2);
    		}
    		world.markBlockForUpdate(x, y, z);
    	}
}