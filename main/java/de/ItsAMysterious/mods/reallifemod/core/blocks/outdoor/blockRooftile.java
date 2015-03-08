package de.ItsAMysterious.mods.reallifemod.core.blocks.outdoor;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import de.ItsAMysterious.mods.reallifemod.core.tiles.rooftileTE;
import de.ItsAMysterious.mods.reallifemod.client.ClientProxy;

public class blockRooftile extends BlockContainer implements ITileEntityProvider{


	public blockRooftile() {
		super(Material.wood);
		this.setBlockName("rooftile");
	}
	@Override
    public boolean isCollidable()
    {
        return true;
    }

	@Override	
	public TileEntity createNewTileEntity(World world, int id) {
		return new rooftileTE();
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
	public int getRenderType(){
		return ClientProxy.getRenderID(this);
	}
	
    @Override
	public int tickRate(World p_149738_1_)
    {
        return 1;
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
