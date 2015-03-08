package de.ItsAMysterious.mods.reallifemod.core.blocks.publicenvironment;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.client.FMLClientHandler;
import de.ItsAMysterious.mods.reallifemod.api.entity.properties.financialProps;
import de.ItsAMysterious.mods.reallifemod.client.ClientProxy;
import de.ItsAMysterious.mods.reallifemod.core.gui.GuiCreebay;
import de.ItsAMysterious.mods.reallifemod.core.tiles.atmTE;

public class blockATM extends BlockContainer implements ITileEntityProvider{

	public blockATM() {
		super(Material.wood);
		this.setBlockName("atm");
		this.setBlockTextureName("reallifemod:atm");
		this.isBlockContainer=true;
	}
	
    @Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_)
    {
    	super.onBlockActivated(world, x, y, z, player, p_149727_6_, p_149727_7_, p_149727_8_, p_149727_9_);
    		if(player.inventory.getCurrentItem()!=null){
    			if(player.inventory.getCurrentItem().getItem()==Item.getItemFromBlock(Blocks.emerald_ore)){
    				((financialProps)player.getExtendedProperties("financialProps")).Cash+=2000;
        			player.inventory.getCurrentItem().stackSize--;
    			}
    			if(player.inventory.getCurrentItem().getItem()==Items.diamond){
    				((financialProps)player.getExtendedProperties("financialProps")).Cash+=1000;
        			player.inventory.getCurrentItem().stackSize--;
    			}
    			if(player.inventory.getCurrentItem().getItem()==Item.getItemFromBlock(Blocks.gold_ore)){
    				((financialProps)player.getExtendedProperties("financialProps")).Cash+=500;
        			player.inventory.getCurrentItem().stackSize--;
    			}
    			if(player.inventory.getCurrentItem().getItem()==Item.getItemFromBlock(Blocks.iron_ore)){
    				((financialProps)player.getExtendedProperties("financialProps")).Cash+=100;
        			player.inventory.getCurrentItem().stackSize--;
    			}
    			if(player.inventory.getCurrentItem().getItem()==Item.getItemFromBlock(Blocks.coal_ore)){
    				((financialProps)player.getExtendedProperties("financialProps")).Cash+=50;
        			player.inventory.getCurrentItem().stackSize--;
    			}
    			if(player.inventory.getCurrentItem().getItem()==Items.coal){
    				((financialProps)player.getExtendedProperties("financialProps")).Cash+=17;
        			player.inventory.getCurrentItem().stackSize--;
    			}
    	        return true;
    	}else
    		return false;
    }
    
    @Override
	public TileEntity createNewTileEntity(World world, int id) {
		return new atmTE();
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
	public String getTextureName(){
		return this.textureName;
	}
	
	@Override
	public int getRenderType(){
		return -1;
	}
}
