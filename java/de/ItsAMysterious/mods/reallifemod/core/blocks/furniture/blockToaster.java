package de.ItsAMysterious.mods.reallifemod.core.blocks.furniture;

import net.minecraft.block.BlockContainer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import de.ItsAMysterious.mods.reallifemod.TLMItems;
import de.ItsAMysterious.mods.reallifemod.api.Materials.Materials;
import de.ItsAMysterious.mods.reallifemod.core.rendering.TileEntitys.toasterTE;

public class blockToaster extends BlockContainer {
	public blockToaster() {
		super(Materials.plastic);
		this.setBlockName("toaster");
		this.setBlockTextureName("reallifemod:toasterItem");
	}
	
	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		return new toasterTE();
	}

    @Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par1, float par2, float par3, float par4){
    	if(player.getCurrentEquippedItem()!=null&&  player.getCurrentEquippedItem().getItem() ==Items.bread){
    		player.inventory.getCurrentItem().stackSize--;
    		player.inventory.addItemStackToInventory(new ItemStack(TLMItems.toast));
        	return true;
    	}else return false;	
    }
   
    @Override
	public int getRenderType()
    {
    	return-1;
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
        toasterTE te =(toasterTE) world.getTileEntity(i, j, k);
        if (te != null && te instanceof toasterTE)
        {
        	toasterTE tet = te;
            tet.setFacingDirection(newFacing);
            world.markBlockForUpdate(i, j, k);
        }
    }
    
}
