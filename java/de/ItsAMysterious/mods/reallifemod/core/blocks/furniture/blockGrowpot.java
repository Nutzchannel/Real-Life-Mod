package de.ItsAMysterious.mods.reallifemod.core.blocks.furniture;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.util.ForgeDirection;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import de.ItsAMysterious.mods.reallifemod.RealLifeMod;
import de.ItsAMysterious.mods.reallifemod.core.tiles.growpotTE;

public class blockGrowpot extends BlockContainer implements ITileEntityProvider {
	public blockGrowpot(Material material) {
		super(material);

		this.setHardness(5.0F);
		this.setResistance(5.0F);
		this.setStepSound(soundTypeMetal);
		this.setCreativeTab(RealLifeMod.Furniture);
		this.setBlockName("growpot");
		this.setBlockBounds(0F, 0F, 0F, 1F, 1F, 1F);
		this.canBlockGrass=true;
	}

	public int getRenderType() {
		return -1;
	}

	public boolean isOpaqueCube() {
		return false;
	}

	public boolean renderAsNormalBlock() {
		return false;
	}

	public boolean onBlockActivated(World world, int x, int y, int z,EntityPlayer player, int p_149727_6_, float p_149727_7_,
			float p_149727_8_, float p_149727_9_) {
		if (world.getTileEntity(x, y, z) != null
				&& world.getTileEntity(x, y, z) instanceof growpotTE) {
			growpotTE tile = (growpotTE) world.getTileEntity(x,y, z);
			if (!tile.isFilled) {
				if (player.inventory.getCurrentItem() != null
						&& player.inventory.getCurrentItem().getItem() == Item
								.getItemFromBlock(Blocks.dirt)) {
					tile.isFilled = true;
					player.inventory.getCurrentItem().stackSize--;
				}
			} else if(tile.isFilled&& player.inventory.getCurrentItem()!=null){
				if(player.inventory.getCurrentItem().getItem() instanceof ItemSpade){
					player.inventory.addItemStackToInventory(new ItemStack(Item.getItemFromBlock(Blocks.dirt)));
					tile.isFilled = false;
				}else
					return false;
			}
			return true;
		}else
			return false;
	}
	
	@Override
    public boolean isFertile(World world, int x, int y, int z)
    {
            return world.getBlockMetadata(x, y, z) > 0;
    }
	
	@Override
    public boolean canSustainPlant(IBlockAccess world, int x, int y, int z, ForgeDirection direction, IPlantable plantable)
    {
		if (world.getTileEntity(x, y, z) != null
				&& world.getTileEntity(x, y, z) instanceof growpotTE) {
			growpotTE pot=(growpotTE) world.getTileEntity(x, y, z);
			return pot.isFilled;
		}else
			return false;
    }


	@Override
	public TileEntity createNewTileEntity(World var1, int var2) {
		return new growpotTE();
	}

	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister iconRegister) {
		this.blockIcon = iconRegister.registerIcon(RealLifeMod.ID + ":"
				+ this.getUnlocalizedName().substring(5));

	}

	@Override
	public void onBlockAdded(World world, int i, int j, int k) {
		EntityPlayer entity = Minecraft.getMinecraft().thePlayer;
		if (entity != null && world != null) {
			int le = MathHelper
					.floor_double((double) (entity.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
			world.setBlockMetadataWithNotify(i, j, k, le, 2);
		}

		world.scheduleBlockUpdate(i, j, k, this, this.tickRate(world));

	}
}
