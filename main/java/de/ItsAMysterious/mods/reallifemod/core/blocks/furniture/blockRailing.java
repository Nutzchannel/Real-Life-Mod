package de.ItsAMysterious.mods.reallifemod.core.blocks.furniture;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import de.ItsAMysterious.mods.reallifemod.api.util.Materials;
import de.ItsAMysterious.mods.reallifemod.client.ClientProxy;
import de.ItsAMysterious.mods.reallifemod.core.tiles.railingTE;
import de.ItsAMysterious.mods.reallifemod.core.tiles.railingTE.Sites;

public class blockRailing extends BlockContainer implements ITileEntityProvider{
	public blockRailing() {
		super(Materials.plastic);
		this.setBlockName("railing");
	}
	
	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		return new railingTE();
	}

    @Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par1, float par2, float par3, float par4){
    	/*if(((railingTE)world.getTileEntity(x, y, z)).site==Sites.left){
    		((railingTE)world.getTileEntity(x, y, z)).site=Sites.right;
    	}else
    		((railingTE)world.getTileEntity(x, y, z)).site=Sites.left;
    	world.markBlockForUpdate(x, y, z);*/
    	return true;
    }
   
    @Override
	public int getRenderType()
    {
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
    
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World p_149668_1_, int p_149668_2_, int p_149668_3_, int p_149668_4_)
    {
    	return AxisAlignedBB.getBoundingBox(0.9F, 1.0F, 0.0F, 1.0F, 0.0F, 1.0F);
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
