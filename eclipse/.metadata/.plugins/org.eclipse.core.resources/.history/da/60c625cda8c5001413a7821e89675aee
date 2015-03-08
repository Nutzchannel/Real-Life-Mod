package de.ItsAMysterious.mods.reallifemod.core.blocks.furniture;

import cpw.mods.fml.client.FMLClientHandler;
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
import de.ItsAMysterious.mods.reallifemod.core.gui.GuiEspresso;
import de.ItsAMysterious.mods.reallifemod.core.tiles.dishwasherTE;
import de.ItsAMysterious.mods.reallifemod.core.tiles.espressoTE;
import de.ItsAMysterious.mods.reallifemod.core.tiles.fishtankTE;
import de.ItsAMysterious.mods.reallifemod.core.tiles.shelfTE;
import de.ItsAMysterious.mods.reallifemod.core.tiles.toasterTE;

public class blockEspresso extends BlockContainer {
	public blockEspresso() {
		super(Materials.plastic);
		this.setBlockName("espresso");
		this.setBlockTextureName("reallifemod:itemEspresso");
	}
	
	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		return new espressoTE();
	}

    @Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par1, float par2, float par3, float par4){
    	FMLClientHandler.instance().getClient().displayGuiScreen(new GuiEspresso());
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
