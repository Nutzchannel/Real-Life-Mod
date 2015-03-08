package de.ItsAMysterious.mods.reallifemod.core.blocks.furniture;

import java.util.List;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import de.ItsAMysterious.mods.reallifemod.client.ClientProxy;
import de.ItsAMysterious.mods.reallifemod.core.renderers.blocks.models.modelChair;
import de.ItsAMysterious.mods.reallifemod.core.tiles.chairTE;

public class blockChair extends BlockContainer implements ITileEntityProvider{

	public static modelChair model=new modelChair();
	private static blockChair theBlock;
	private boolean inUse=false;

	
	public blockChair() {
		super(Material.wood);
		this.setBlockName("chair");
		this.setBlockTextureName("reallifemod:iconChair");
	}

	@Override
	public TileEntity createNewTileEntity(World world, int id) {
		return new chairTE();
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
		EntityPlayer entity = Minecraft.getMinecraft().thePlayer;
		if(entity!=null&&world!=null){
		int le = MathHelper.floor_double((double)(entity.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
		world.setBlockMetadataWithNotify(x, y, z, le, 2);
		}
		world.markBlockForUpdate(x, y, z);
	}
    
	@SideOnly(Side.CLIENT)
    @Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_){
		chairTE chair=(chairTE)world.getTileEntity(x, y, z);
    	if(world.getTileEntity(x, y, z)!=null && world.getTileEntity(x, y, z) instanceof chairTE)
    		if(chair.isSitting()==false){
    			chair.sitdown(player,1.0D);
    		}else
    		{
    			chair.dismountEntity();
    		}
			world.markBlockForUpdate(x, y, z);
			if(!world.isRemote){
    		player.addChatMessage(new ChatComponentText("Press L_SHIFT to stand up!"));}
    	return true;

    }
    
	@Override
	public void addCollisionBoxesToList(World world, int x, int y, int z, AxisAlignedBB bounds, List list, Entity entity) {
		bounds.setBounds(0.0F, 0.0F, 0.0F, 0.0625F, 1.0F, 1.0F);
		super.addCollisionBoxesToList(world, x, y, z, bounds, list, entity);
		bounds.setBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 0.0625F);
		super.addCollisionBoxesToList(world, x, y, z, bounds, list, entity);
		bounds.setBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0625F);
		super.addCollisionBoxesToList(world, x, y, z, bounds, list, entity);
		bounds.setBounds(0.0F, 0.0F, 0.0F, 1.0F, 1 + 0.0625F, 1.0F);
		super.addCollisionBoxesToList(world, x, y, z, bounds, list, entity);
		bounds.setBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
	}
}
