package de.ItsAMysterious.mods.reallifemod.core.blocks.outdoor;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import de.ItsAMysterious.mods.reallifemod.core.blocks.BlockAirExtended;
import de.ItsAMysterious.mods.reallifemod.core.rendering.TileEntitys.fireplaceTE;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class fireplaceBlock extends BlockContainer implements ITileEntityProvider{

	private Random rand;

	public fireplaceBlock() {
		super(Material.wood);
		this.setBlockName("firepit");
		this.setBlockTextureName("reallifemod:outdoor/fire");
        this.setTickRandomly(true);
	}
	@Override
    public boolean isCollidable()
    {
        return true;
    }

	@Override	
	public TileEntity createNewTileEntity(World world, int id) {
		return new fireplaceTE();
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
		return -1;
	}
	
    @Override
	public int tickRate(World p_149738_1_)
    {
        return 1;
    }
    
    @Override
	public void onBlockAdded(World world, int x, int y, int z)
    {
    	world.scheduleBlockUpdate(x, y, z, this, this.tickRate(world) + world.rand.nextInt(1));
    }
	
    @Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_){
	TileEntity te = world.getTileEntity(x, y, z);
	if (te != null && te instanceof fireplaceTE)
    {
    	fireplaceTE tet = (fireplaceTE) te;
		if(player.getCurrentEquippedItem()!=null&&player.getCurrentEquippedItem().getItem() == Items.flint_and_steel)
		{
	    	tet.lit();
	    	this.setLightLevel(1.0F);
	    }
		if(player.getCurrentEquippedItem()!=null&&player.getCurrentEquippedItem().getItem()==Items.water_bucket)
	    {
	    	int k=player.inventory.currentItem;
	    	player.setCurrentItemOrArmor(k, new ItemStack(Items.bucket));
	    	tet.extinguish();
	    	this.setLightLevel(0.0F);

	    }
		} 
		world.markBlockForUpdate(x, y, z);
	    	return true;
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
        TileEntity te = world.getTileEntity(i, j, k);
        if (te != null && te instanceof fireplaceTE)
        {
        	fireplaceTE tet = (fireplaceTE) te;
        	tet.setHeightToSubstract(1-world.getBlock(i, j, k).getBlockBoundsMaxY());
            tet.setFacingDirection(newFacing);
            world.markBlockForUpdate(i, j, k);
        }
        for(int x=0;x<5;x++)
        	for (int y = 0; y < 3; y++)
            	for (int z = 0; z < 5; z++)
            	{
            		if((world.getBlock(i+x, j+y, k+z))instanceof BlockAirExtended){
                		BlockAirExtended airblock=(BlockAirExtended)world.getBlock(i+x, j+y, k+z);
                		BlockAirExtended.TemperaturC=30;
            		}
            		world.markBlockForUpdate(i+x, j+y, k+z);
            	}
        }
    @Override
	@SideOnly(Side.CLIENT)
    public void updateTick(World world, int x, int y, int z, Random rand)
    {
    	//super.updateTick(world, x, y, z, rand);
    	TileEntity te=world.getTileEntity(x, y, z);
    	if(te != null && te instanceof fireplaceTE)
    	{
    		fireplaceTE tile=(fireplaceTE)world.getTileEntity(x, y, z);
    		if(tile.burning==true){
    			this.setLightLevel(1.0F);
    		}
    		if(tile.burning==false){
    			setLightLevel(0.0F);
    		}
    		if(world.rainingStrength>0.2 && world.canBlockSeeTheSky(x, y, z)==true);
    		{
    	    	tile.extinguish();
    			this.setLightLevel(0.0F);
    		}
    	}
		world.markBlockForUpdate(x, y, z);
    }
    
    @Override
	@SideOnly(Side.CLIENT)
    public void randomDisplayTick(World p_149734_1_, int p_149734_2_, int p_149734_3_, int p_149734_4_, Random p_149734_5_)
    {
    }
    
}
