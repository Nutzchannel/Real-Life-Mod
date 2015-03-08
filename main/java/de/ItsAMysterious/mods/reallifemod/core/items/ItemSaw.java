package de.ItsAMysterious.mods.reallifemod.core.items;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import org.lwjgl.util.vector.Vector3f;

import de.ItsAMysterious.mods.reallifemod.RealLifeMod;
import de.ItsAMysterious.mods.reallifemod.TLMBlocks;
import de.ItsAMysterious.mods.reallifemod.core.tiles.tableTE;

public class ItemSaw extends Item{
	public static Vector3f table;
	
	public ItemSaw() {
		this.setTextureName(RealLifeMod.ID+":saw");
		this.setUnlocalizedName("saw");
	}
	
    /**
     * Called whenever this item is equipped and the right mouse button is pressed. Args: itemStack, world, entityPlayer
     */
	@Override
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player)
    {
	/*if(!world.isRemote){
		int x=Minecraft.getMinecraft().objectMouseOver.blockX;
		int y=Minecraft.getMinecraft().objectMouseOver.blockY;
		int z=Minecraft.getMinecraft().objectMouseOver.blockZ;
    	if(this.table==null){
    		this.table=new Vector3f(x, y, z);
    	}else{}
    	
    	if(world.getBlock(x, y, z)==TLMBlocks.table){
    		((tableTE)world.getTileEntity((int)table.x, (int)table.y, (int)table.z)).edges.add(new Vector3f(x,y,z));
    		System.out.println(((tableTE)world.getTileEntity((int)table.x, (int)table.y, (int)table.z)).edges);
    		world.markBlockForUpdate((int)table.x, (int)table.y, (int)table.z);
    	}
    	if(x==table.x&&y==table.y&&z==table.z&&((tableTE)world.getTileEntity((int)table.x, (int)table.y, (int)table.z)).edges.size()==0){
    		//this.table=null;
    		System.out.println("table is build!");
    	}
		}*/
        return stack;
    }

}
