package de.ItsAMysterious.mods.reallifemod.core.tiles;

import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;


public class safeTE extends TileEntityDirectional{
	public ItemStack currentItem=new ItemStack(Item.getItemFromBlock(Blocks.stone));
	
	@Override
    public void readFromNBT(NBTTagCompound nbttagcompound)
    {
		super.readFromNBT(nbttagcompound);
		this.facingDirection=nbttagcompound.getInteger("facingDirection");
		this.currentItem.loadItemStackFromNBT(nbttagcompound);
		System.out.println("read: "+this.currentItem.getDisplayName());
    }
	
	@Override
    public void writeToNBT(NBTTagCompound nbttagcompound)
    {	
		super.writeToNBT(nbttagcompound);
        nbttagcompound.setInteger("facingDirection", this.facingDirection);
        currentItem.writeToNBT(nbttagcompound);
		System.out.println("Wrote: "+this.currentItem.getDisplayName());

    }
	

}
