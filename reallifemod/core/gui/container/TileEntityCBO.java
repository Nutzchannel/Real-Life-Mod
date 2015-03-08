package de.ItsAMysterious.mods.reallifemod.core.gui.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class TileEntityCBO extends TileEntity implements IInventory {

    private ItemStack inv;

    public TileEntityCBO(){
    	inv=null;
    }
    
    @Override
    public int getSizeInventory() {
    	return 1;
    }

    @Override
    public ItemStack getStackInSlot(int slot) {
    	return inv;
    }
    
    @Override
    public void setInventorySlotContents(int slot, ItemStack stack) {
            inv = stack;
            if (stack != null && stack.stackSize > getInventoryStackLimit()) {
                    stack.stackSize = getInventoryStackLimit();
            }               
    }

    @Override
    public ItemStack decrStackSize(int slot, int amt) {
            ItemStack stack = getStackInSlot(slot);
            if (stack != null) {
                    if (stack.stackSize <= amt) {
                            setInventorySlotContents(slot, null);
                    } else {
                            stack = stack.splitStack(amt);
                            if (stack.stackSize == 0) {
                                    setInventorySlotContents(slot, null);
                            }
                    }
            }
            return stack;
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int slot) {
            ItemStack stack = getStackInSlot(slot);
            if (stack != null) {
                    setInventorySlotContents(slot, null);
            }
            return stack;
    }
    
    @Override
    public int getInventoryStackLimit() {
            return 64;
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer player) {
            return worldObj.getTileEntity(xCoord, yCoord, zCoord) == this &&
            player.getDistanceSq(xCoord + 0.5, yCoord + 0.5, zCoord + 0.5) < 64;
    }

    
    @Override
    public void readFromNBT(NBTTagCompound tagCompound) {
            super.readFromNBT(tagCompound);
    }

    @Override
    public void writeToNBT(NBTTagCompound tagCompound) {
            super.writeToNBT(tagCompound);
    }

            @Override
            public String getInventoryName() {
                    return "tbme.TileEntityCBO";
            }

			@Override
			public boolean hasCustomInventoryName() {
				// TODO Auto-generated method stub
				return true;
			}

			@Override
			public void openInventory() {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void closeInventory() {
				// TODO Auto-generated method stub
				
			}

			@Override
			public boolean isItemValidForSlot(int p_94041_1_,
					ItemStack p_94041_2_) {
				// TODO Auto-generated method stub
				return true;
			}
}