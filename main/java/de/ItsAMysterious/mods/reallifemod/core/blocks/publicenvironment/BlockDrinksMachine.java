package de.ItsAMysterious.mods.reallifemod.core.blocks.publicenvironment;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import de.ItsAMysterious.mods.reallifemod.client.ClientProxy;
import de.ItsAMysterious.mods.reallifemod.core.tiles.drinksmachineTE;


public class BlockDrinksMachine extends BlockContainer implements ITileEntityProvider{

        public BlockDrinksMachine() {
        	super(Material.rock);
        	setBlockName("drinksmachine");
        	setBlockTextureName("reallifemod:iconDrinksmachine");
        }

       
        @Override
        public TileEntity createNewTileEntity(World world,int var2) {
            return new drinksmachineTE();
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
		public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int x2, float x3, float y3, float z3)
        {
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