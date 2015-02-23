package de.ItsAMysterious.mods.reallifemod.core.gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

public class TileEntityItemSelling extends TileEntity implements ISidedInventory{

	private  ItemStack[] itemStacks;
	private  String invName;
	
    public TileEntityItemSelling(int size, String displayName){
        itemStacks = new ItemStack[size];
        invName = displayName;
    }
    
    @Override
    public void readFromNBT(NBTTagCompound nbtTagCompound){
        super.readFromNBT(nbtTagCompound);

        NBTTagList tagList = nbtTagCompound.getTagList("Inventory", 10);
        for (int i = 0; i < tagList.tagCount(); i++){
            NBTTagCompound nbtTagCompound1 = tagList.getCompoundTagAt(i);
            byte slot = nbtTagCompound1.getByte("Slot");
            if(slot >= 0 && slot < itemStacks.length){
                itemStacks[slot] = ItemStack.loadItemStackFromNBT(nbtTagCompound1);
            }
        }
    }

    @Override
    public  void writeToNBT(NBTTagCompound nbtTagCompound){
        super.writeToNBT(nbtTagCompound);

        NBTTagList tagList = new NBTTagList();
        for(int i = 0; i < itemStacks.length; i++){
            ItemStack itemStack = itemStacks[i];
            if(itemStack != null){
                NBTTagCompound nbtTagCompound1 = new NBTTagCompound();
                nbtTagCompound1.setByte("Slot", (byte) i );
                itemStack.writeToNBT(nbtTagCompound1);
                tagList.appendTag(nbtTagCompound1);
            }
        }
        nbtTagCompound.setTag("Inventory", tagList);
    }
    
	@Override
	public int getSizeInventory() {
        return itemStacks.length;
	}

    @Override
    public ItemStack getStackInSlot(int slot) {
        return itemStacks[slot];
    }

    @Override
    public ItemStack decrStackSize(int slot, int amount) {
        if(this.itemStacks != null){
            ItemStack item;
            if(this.itemStacks[slot].stackSize <= amount){
                item = this.getStackInSlot(slot);
                this.setInventorySlotContents(slot, null);
                this.markDirty();
                return item;
            }else{
                item = this.getStackInSlot(slot).splitStack(amount);
                if(this.itemStacks[slot].stackSize == 0){
                    this.setInventorySlotContents(slot, null);
                }
                this.markDirty();
                return  item;
            }
        }else{
            return null;
        }
    }

    @Override
	public ItemStack getStackInSlotOnClosing(int slot) {
        if(itemStacks[slot] != null){
            ItemStack item = this.itemStacks[slot];
            this.itemStacks[slot] = null;
            return item;
        }else{
            return null;
        }
    }

    @Override
    public void setInventorySlotContents(int slot, ItemStack stack) {
        itemStacks[slot] = stack;
        if(stack != null && stack.stackSize > getInventoryStackLimit()){
            stack.stackSize = getInventoryStackLimit();
        }
        this.markDirty();
    }

    @Override
    public String getInventoryName() {
        if(hasCustomInventoryName() == true){
            return invName;
        }else{
            return null;
        }
    }

    @Override
    public boolean hasCustomInventoryName() {
        if (invName != null && invName.length() < 0) {
            return true;
        } else {
            return false;

        }
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
    public Packet getDescriptionPacket() {
        NBTTagCompound tag = new NBTTagCompound();
        writeToNBT(tag);
        return new S35PacketUpdateTileEntity(xCoord, yCoord, zCoord, 1, tag);
}
    @Override
    public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
        readFromNBT(pkt.func_148857_g());
    }

    @Override
    public void openInventory() {

    }

    @Override
    public void closeInventory() {

    }

    @Override
    public boolean isItemValidForSlot(int p_94041_1_, ItemStack p_94041_2_) {
        return false;
    }

	@Override
	public int[] getAccessibleSlotsFromSide(int p_94128_1_) {
		return null;
	}

	@Override
	public boolean canInsertItem(int p_102007_1_, ItemStack p_102007_2_,
			int p_102007_3_) {
		return true;
	}

	@Override
	public boolean canExtractItem(int p_102008_1_, ItemStack p_102008_2_,
			int p_102008_3_) {
		return true;
	}

}
