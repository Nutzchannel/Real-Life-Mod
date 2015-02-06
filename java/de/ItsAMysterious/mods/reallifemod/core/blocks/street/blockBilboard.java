package de.ItsAMysterious.mods.reallifemod.core.blocks.street;

import cpw.mods.fml.client.FMLClientHandler;
import de.ItsAMysterious.mods.reallifemod.core.Gui.GuiAdvert;
import de.ItsAMysterious.mods.reallifemod.core.rendering.TileEntitys.bilboardTE;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;


public class blockBilboard extends BlockContainer   {

	public blockBilboard(Material material) {
	        super(Material.iron);
	        setBlockTextureName("TLM:shield");
	        setBlockName("advertBilboard");
	        setBlockBounds(-2.0F, 0F, 0F, 2.0F, 3.0F, 0.4F);
        }

        @Override
		public void onBlockDestroyedByPlayer(World world, int par1, int par2, int par3, int par4) 
        {
        	this.dropBlockAsItem(world, par1, par2, par3, new ItemStack(this));
        	this.getBlocksMovement(world,(int)(par1+0.1),(int)( par2+0.5),(int)( par3+0.1));
        	
		}

        @Override
        public TileEntity createNewTileEntity(World world,int var2) {
                 
            return new bilboardTE();
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
		public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer p_149727_5_, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_)
        {
        	FMLClientHandler.instance().getClient().displayGuiScreen(new GuiAdvert((bilboardTE)world.getTileEntity(x, y, z)));
            return true;
        }
}