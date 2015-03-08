package de.ItsAMysterious.mods.reallifemod.core.blocks.furniture;

import net.minecraft.block.BlockContainer;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import de.ItsAMysterious.mods.reallifemod.api.util.Materials;
import de.ItsAMysterious.mods.reallifemod.core.tiles.toasterTE;
import de.ItsAMysterious.mods.reallifemod.init.TLMItems;

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
	public void onBlockAdded(World world, int x, int y, int z){
		EntityPlayer entity = Minecraft.getMinecraft().thePlayer;
		if(entity!=null&&world!=null){
		int le = MathHelper.floor_double((double)(entity.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
		world.setBlockMetadataWithNotify(x, y, z, le, 2);
		}
		world.markBlockForUpdate(x, y, z);
	}
    
}
