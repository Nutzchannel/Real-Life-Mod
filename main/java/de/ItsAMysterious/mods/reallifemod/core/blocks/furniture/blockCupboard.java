package de.ItsAMysterious.mods.reallifemod.core.blocks.furniture;

import net.minecraft.block.BlockDirectional;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import de.ItsAMysterious.mods.reallifemod.client.ClientProxy;
import de.ItsAMysterious.mods.reallifemod.core.renderers.blocks.models.modelCupboard;
import de.ItsAMysterious.mods.reallifemod.core.tiles.cupboardTE;

public class blockCupboard extends BlockDirectional implements ITileEntityProvider{
	public static modelCupboard model=new modelCupboard();
	private static blockCupboard blockCupboard;

	
	public blockCupboard() {
		super(Material.wood);
    	this.setBlockBounds(-1, 0, 0, 2.5F, 2, 1);
		this.setBlockName("cupboard");
		this.setBlockTextureName("reallifemod:iconCupboard");
	}

	@Override
	public TileEntity createNewTileEntity(World world, int id) {
		cupboardTE t=new cupboardTE();
		model=blockCupboard.model;
		return t;
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
    public void setBlockBoundsBasedOnState(IBlockAccess world, int x, int y, int z) {
		super.setBlockBoundsBasedOnState(world, x, y, z);
		switch(world.getBlockMetadata(x, y, z)){
			//North
			case 0:{
				this.setBlockBounds(-1F, 0F, 0.0F, 2F, 2.6F, 1.0F);
				break;
			}
			//West
			case 1:{
				this.setBlockBounds(0.0F, 0.0F, -1F, 1F, 2.6F, 2F);
				break;
			}
			//South
			case 2:{
				this.setBlockBounds(-1F, 0F, 0.0F, 2F, 2.6F, 1.0F);
				break;
			}
			//East
			case 3:{
				this.setBlockBounds(0.0F, 0.0F, -1F, 1F, 2.6F, 2F);
				break;
			}

			}
		}
    @Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int p_149727_6_, float posX, float posY, float posZ)
    {
    	cupboardTE c=(cupboardTE)world.getTileEntity(x, y, z);
    	if(c.open==true){
			if(modelCupboard.LeftDoor.rotateAngleY>0)
				modelCupboard.LeftDoor.rotateAngleY--;
    		c.open=false;

    	}else
    	if(c.open==false){
			if(modelCupboard.LeftDoor.rotateAngleY<90)
				modelCupboard.LeftDoor.rotateAngleY++;
    		c.open=true;
    	}
		world.markBlockForUpdate(x, y, z);
        return true;
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
