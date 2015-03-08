package de.ItsAMysterious.mods.reallifemod.core.blocks.furniture;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import cpw.mods.fml.client.FMLClientHandler;
import de.ItsAMysterious.mods.reallifemod.client.ClientProxy;
import de.ItsAMysterious.mods.reallifemod.core.gui.GuiCreebay;
import de.ItsAMysterious.mods.reallifemod.core.tiles.computerTE;

public class BlockComputer extends BlockContainer {

	public BlockComputer(Material material) {
		super(material);
		this.setBlockName("computer");
		this.setBlockTextureName("reallifemod:iconComputer");
	}
	
	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		return new computerTE();
	}

    @Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par1, float par2, float par3, float par4){
    	FMLClientHandler.instance().getClient().displayGuiScreen(new GuiCreebay());;
    	return true;
    }
    
    @Override
	public void onBlockDestroyedByPlayer(World world, int par1, int par2, int par3, int par4) 
    {
    	this.getBlocksMovement(world,(int)(par1+0.1),(int)( par2+0.5),(int)( par3+0.1));
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
