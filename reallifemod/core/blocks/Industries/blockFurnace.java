package de.ItsAMysterious.mods.reallifemod.core.blocks.Industries;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import de.ItsAMysterious.mods.reallifemod.client.ClientProxy;
import de.ItsAMysterious.mods.reallifemod.core.gui.GuiOven;
import de.ItsAMysterious.mods.reallifemod.core.gui.container.OvenTileEntity;
import de.ItsAMysterious.mods.reallifemod.core.tiles.blastfurnaceTE;


public class blockFurnace extends BlockContainer implements ITileEntityProvider{


    	private static boolean burns;
		public static Minecraft mc;

        public blockFurnace() {
                super(Material.rock);
                this.setBlockBounds( -1, 0, -1, 1, 3, 0);
                setBlockName("blastfurnace");
        }

        @Override
		public void onBlockDestroyedByPlayer(World world, int par1, int par2, int par3, int par4) 
        {
        	this.dropBlockAsItem(world, par1, par2, par3, new ItemStack(this));
		}
        
        @Override
        public TileEntity createNewTileEntity(World world,int var2) {
                 
            return new blastfurnaceTE();
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
        	Minecraft.getMinecraft().displayGuiScreen(new GuiOven(Minecraft.getMinecraft().thePlayer.inventory, new OvenTileEntity()));
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