package de.ItsAMysterious.mods.reallifemod.core.blocks.furniture;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import org.lwjgl.util.vector.Vector3f;

import de.ItsAMysterious.mods.reallifemod.client.ClientProxy;
import de.ItsAMysterious.mods.reallifemod.core.tiles.tableTE;

public class blockTable extends BlockContainer implements ITileEntityProvider{
	public tableTE tile;
	private boolean setuptable;
	public blockTable() {
		super(Material.wood);
		this.setBlockName("woodenleg");
	}

	@Override
	public TileEntity createNewTileEntity(World world, int id) {
			return new tableTE();
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
	public void onBlockAdded(World world, int x, int y, int z){
		this.setuptable=true;
		EntityPlayer entity = Minecraft.getMinecraft().thePlayer;
		if(entity!=null&&world!=null){
		int le = MathHelper.floor_double((double)(entity.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
		world.setBlockMetadataWithNotify(x, y, z, le, 2);
		}
		world.markBlockForUpdate(x, y, z);
	}
      
}
