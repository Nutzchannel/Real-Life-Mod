package de.ItsAMysterious.mods.reallifemod.core.blocks.furniture;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockContainer;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import de.ItsAMysterious.mods.reallifemod.TLMItems;
import de.ItsAMysterious.mods.reallifemod.api.util.Materials;
import de.ItsAMysterious.mods.reallifemod.core.tiles.fishtankTE;
import de.ItsAMysterious.mods.reallifemod.core.tiles.shelfTE;
import de.ItsAMysterious.mods.reallifemod.core.tiles.toasterTE;

public class blockShelf extends BlockContainer {
	public blockShelf() {
		super(Materials.plastic);
		this.setBlockName("shelf");
		this.setBlockTextureName("reallifemod:iconShelfsmall");
	}
	
	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		return new shelfTE();
	}

    @Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par1, float par2, float par3, float par4){
    	if(!world.isRemote){
    		shelfTE shelf=(shelfTE)world.getTileEntity(x, y, z);
    	if(player.inventory.getCurrentItem()!=null){
    		System.out.println(player.inventory.getCurrentItem().getItem().getUnlocalizedName()+" "+player.inventory.getCurrentItem().stackSize);
    		shelf.items.add(player.inventory.getCurrentItem());
    		player.inventory.setInventorySlotContents(player.inventory.currentItem, null);
    		world.markBlockForUpdate(x, y, z);
    		return true;
    	}else if(player.inventory.getCurrentItem()==null)
    	{
    		if(shelf.items.size()!=0){
    			player.inventory.setInventorySlotContents(player.inventory.currentItem, shelf.items.get(shelf.items.size()-1));
    			shelf.items.remove(shelf.items.size()-1);
    		}
    	}
    	}
    	return true;
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
