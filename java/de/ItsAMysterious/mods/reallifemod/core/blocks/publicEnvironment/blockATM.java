package de.ItsAMysterious.mods.reallifemod.core.blocks.publicEnvironment;

import cpw.mods.fml.client.FMLClientHandler;
import de.ItsAMysterious.mods.reallifemod.client.ClientProxy;
import de.ItsAMysterious.mods.reallifemod.core.Gui.guiATM;
import de.ItsAMysterious.mods.reallifemod.core.Gui.guiCreeBay;
import de.ItsAMysterious.mods.reallifemod.core.rendering.TileEntitys.atmTE;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class blockATM extends BlockContainer implements ITileEntityProvider{

	public blockATM() {
		super(Material.wood);
		this.setBlockName("atm");
		this.setBlockTextureName("reallifemod:atm");
		this.isBlockContainer=true;
	}
	
    @Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_)
    {
    	super.onBlockActivated(world, x, y, z, player, p_149727_6_, p_149727_7_, p_149727_8_, p_149727_9_);
    		if(player.inventory.getCurrentItem()!=null &&player.inventory.getCurrentItem().getItem()==Items.gold_ingot){
    			player.inventory.getCurrentItem().stackSize--;
    	    	ClientProxy.Money.replace(player.getDisplayName(),ClientProxy.Money.get(player.getDisplayName())+10);
    	        return true;
    	}else
    		FMLClientHandler.instance().getClient().displayGuiScreen(new guiCreeBay());
    		return false;
    }
    
    @Override
	public TileEntity createNewTileEntity(World world, int id) {
		return new atmTE();
	}
	
	@Override
	public boolean renderAsNormalBlock(){
		return false;
	}
	
	@Override
	public boolean isOpaqueCube(){
		return false;
	}
	
	@Override
	public String getTextureName(){
		return this.textureName;
	}
	
	@Override
	public int getRenderType(){
		return ClientProxy.getRenderID(this);
	}
	  
    @Override
    public void onBlockPlacedBy(World world, int i, int j, int k, EntityLivingBase entityliving, ItemStack itemStack)
    {
        int facing = MathHelper.floor_double((entityliving.rotationYaw * 4F) / 360F + 0.5D) & 3;
        int newFacing = 0;
        if (facing == 0)
        {
        	newFacing = 3;
        }
        if (facing == 1)
        {
        	newFacing = 5;
        }
        if (facing == 2)
        {
        	newFacing = 2;
        }
        if (facing == 3)
        {
        	newFacing = 4;
        }
               
        atmTE te = (atmTE) world.getTileEntity(i, j, k);
        if (te != null && te instanceof atmTE)
        {
        	atmTE tile = te;
            tile.setFacingDirection(newFacing);
        	double f=world.getBlock(i,j-1, k).getBlockBoundsMaxY();
        	tile.setHeightToSubstract(1-f);
            world.markBlockForUpdate(i, j, k);
        }
    }

    @Override
	public void onBlockDestroyedByPlayer(World world, int x, int y, int z, int p_149664_5_) {
    }


}
