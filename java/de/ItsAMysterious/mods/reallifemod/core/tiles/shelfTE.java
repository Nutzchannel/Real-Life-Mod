package de.ItsAMysterious.mods.reallifemod.core.tiles;

import java.util.ArrayList;
import java.util.List;

import cpw.mods.fml.common.registry.FMLControlledNamespacedRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;

public class shelfTE extends TileEntity {
	public List<ItemStack>items=new ArrayList<ItemStack>(9);
	
    public void readFromNBT(NBTTagCompound parCompound)
    {
		super.readFromNBT(parCompound);
		NBTTagList itemlist=parCompound.getTagList("shelfItems", 10);
		for(int i =0;i<itemlist.tagCount();i++){
            NBTTagCompound nbttagcompound1 = itemlist.getCompoundTagAt(i);
            int j = nbttagcompound1.getByte("Slot") & 255;
            if (j >= 0 && j < this.items.size()-1)
            {
                this.items.add(ItemStack.loadItemStackFromNBT(nbttagcompound1));
            }

		}
    }
    
    public void writeToNBT(NBTTagCompound parCompound)
    {
		super.writeToNBT(parCompound);
		NBTTagList itemList=new NBTTagList();
		for(int i = 0;i<this.items.size();i++){
			if(this.items.get(i)!=null){
				NBTTagCompound compound=new NBTTagCompound();
				compound.setByte("Slot", (byte)i);
				this.items.get(i).writeToNBT(compound);
				itemList.appendTag(compound);
			}
		}
		parCompound.setTag("shelfItems", itemList);
    }
	
    public void setInventorySlotContents(int p_70299_1_, ItemStack p_70299_2_)
    {
        this.items.add(p_70299_2_);

        if (p_70299_2_ != null && p_70299_2_.stackSize > 64)
        {
            p_70299_2_.stackSize = 64;
        }

        this.markDirty();
    }
    
	@Override
    public int getBlockMetadata()
    {
		if(this.worldObj!=null){
        if (this.blockMetadata == -1)
        {
            this.blockMetadata = this.worldObj.getBlockMetadata(this.xCoord, this.yCoord, this.zCoord);
        }
        return this.blockMetadata;
    }else
    	return 1;
    }
}
