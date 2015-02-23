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
import de.ItsAMysterious.mods.reallifemod.TLMItems;
import de.ItsAMysterious.mods.reallifemod.api.util.Materials;
import de.ItsAMysterious.mods.reallifemod.client.ClientProxy;
import de.ItsAMysterious.mods.reallifemod.core.tiles.washbasinTE;


public class BlockWashingbasin extends BlockContainer{

        public BlockWashingbasin() {
                super(Materials.marmor);
                this.setBlockName("washbasin");
                this.setBlockTextureName("reallifemod:iconWashbasin");
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
    	public void onBlockAdded(World world, int x, int y, int z){
    		EntityPlayer entity = Minecraft.getMinecraft().thePlayer;
    		if(entity!=null&&world!=null){
    		int le = MathHelper.floor_double((double)(entity.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
    		world.setBlockMetadataWithNotify(x, y, z, le, 2);
    		}
    		world.markBlockForUpdate(x, y, z);
    	}
}