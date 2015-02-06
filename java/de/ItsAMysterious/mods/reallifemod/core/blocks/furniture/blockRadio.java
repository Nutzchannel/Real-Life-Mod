package de.ItsAMysterious.mods.reallifemod.core.blocks.furniture;

import cpw.mods.fml.client.FMLClientHandler;
import net.minecraft.block.BlockContainer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import de.ItsAMysterious.mods.reallifemod.api.Materials.Materials;
import de.ItsAMysterious.mods.reallifemod.client.ClientProxy;
import de.ItsAMysterious.mods.reallifemod.core.Gui.guiRadio;
import de.ItsAMysterious.mods.reallifemod.core.rendering.TileEntitys.radioTE;

public class blockRadio extends BlockContainer {

	private radioTE tile;

	public blockRadio() {
		super(Materials.plastic);
		this.setBlockName("radio");
		this.setBlockTextureName("reallifemod:radio");
	}
	
	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		radioTE tile=new radioTE();
		this.tile=tile;
		return this.tile;
	}

    @Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par1, float par2, float par3, float par4){
    	FMLClientHandler.instance().getClient().displayGuiScreen(new guiRadio());
    	//speechThreaded.speechSynth(1, 200, 500, 500, "Hello! I'm your new Computer! I will now give you a few tipps for using me right!");
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
    public void onBlockPlacedBy(World world, int i, int j, int k, EntityLivingBase entityliving, ItemStack itemStack)
    {
        int facing = MathHelper.floor_double((entityliving.rotationYaw * 4F) / 360F + 0.5D) & 3;
        int newFacing = 0;
        if (facing == 0)
        {
        	newFacing = 2;
        }
        if (facing == 1)
        {
        	newFacing = 5;
        }
        if (facing == 2)
        {
        	newFacing = 3;
        }
        if (facing == 3)
        {
        	newFacing = 4;
        }
        radioTE te =(radioTE) world.getTileEntity(i, j, k);
        if (te != null && te instanceof radioTE)
        {
        	radioTE tet = te;
            tet.setFacingDirection(newFacing);
            world.markBlockForUpdate(i, j, k);
        }
    }
    
}
