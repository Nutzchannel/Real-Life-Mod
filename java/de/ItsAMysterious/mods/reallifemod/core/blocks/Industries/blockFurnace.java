package de.ItsAMysterious.mods.reallifemod.core.blocks.Industries;

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
import de.ItsAMysterious.mods.reallifemod.core.Gui.GuiOven;
import de.ItsAMysterious.mods.reallifemod.core.Gui.TileEntity.OvenTileEntity;
import de.ItsAMysterious.mods.reallifemod.core.rendering.TileEntitys.blastfurnaceTE;


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
        public void onBlockPlacedBy(World world, int i, int j, int k, EntityLivingBase entityliving, ItemStack itemStack)
        {
            int facing = MathHelper.floor_double((entityliving.rotationYaw * 4F) / 360F + 0.5D) & 3;
            int newFacing = 0;
            if (facing == 0)
            {
            	newFacing = 2;
            }
            if (facing == 1)
            {
            	newFacing = 5;
            }
            if (facing == 2)
            {
            	newFacing = 3;
            }
            if (facing == 3)
            {
            	newFacing = 4;
            }
            TileEntity te = world.getTileEntity(i, j, k);
            if (te != null && te instanceof blastfurnaceTE)
            {
            	blastfurnaceTE tet = (blastfurnaceTE) te;
                tet.setFacingDirection(newFacing);
                world.markBlockForUpdate(i, j, k);
            }
        }
}