package de.ItsAMysterious.mods.reallifemod.core.blocks.furniture;

import net.minecraft.block.BlockContainer;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import de.ItsAMysterious.mods.reallifemod.TLMItems;
import de.ItsAMysterious.mods.reallifemod.api.util.Materials;
import de.ItsAMysterious.mods.reallifemod.core.tiles.fishtankTE;
import de.ItsAMysterious.mods.reallifemod.core.tiles.toasterTE;

public class blockfishtank extends BlockContainer {
	public blockfishtank() {
		super(Materials.plastic);
		this.setBlockName("fishtank");
		this.setBlockTextureName("reallifemod:fishtank");
	}
	@Override
    public void setBlockBoundsBasedOnState(IBlockAccess world, int x, int y, int z) {
		super.setBlockBoundsBasedOnState(world, x, y, z);
		switch(world.getBlockMetadata(x, y, z)){
			//North
			case 0:{
				this.setBlockBounds(-0.5F, 0F, 0.0F, 1.5F, 1F, 1.0F);
				break;
			}
			//West
			case 1:{
				this.setBlockBounds(0.0F, 0.0F, -0.5F, 1F, 1F, 1.5F);
				break;
			}
			//South
			case 2:{
				this.setBlockBounds(-0.5F, 0F, 0.0F, 1.5F, 1F, 1.0F);
				break;
			}
			//East
			case 3:{
				this.setBlockBounds(0.0F, 0.0F, -0.5F, 1F, 1F, 1.5F);
				break;
			}

			}
		}

	
	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		return new fishtankTE();
	}

    @Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par1, float par2, float par3, float par4){
    	((fishtankTE)world.getTileEntity(x, y, z)).isFilled=!((fishtankTE)world.getTileEntity(x, y, z)).isFilled;
    	world.markBlockForUpdate(x, y, z);
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
