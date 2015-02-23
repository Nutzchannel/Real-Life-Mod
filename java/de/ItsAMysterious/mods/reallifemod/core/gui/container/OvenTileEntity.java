package de.ItsAMysterious.mods.reallifemod.core.gui.container;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import de.ItsAMysterious.mods.reallifemod.api.recipes.OvenRecipes;
import net.minecraft.block.BlockFurnace;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;

public class OvenTileEntity extends TileEntity implements ISidedInventory{
	 	private static final int[] slotsTop = new int[] {0};
	    private static final int[] slotsBottom = new int[] {2, 1};
	    private static final int[] slotsSides = new int[] {1};
	    /**
	     * The ItemStacks that hold the items currently being used in the furnace
	     */
	    private ItemStack[] ovenItemStacks = new ItemStack[3];
	    /** The number of ticks that the furnace will keep burning */
	    public int ovenBurnTime;
	    /**
	     * The number of ticks that a fresh copy of the currently-burning item would keep the furnace burning for
	     */
	    public int currentItemBurnTime;
	    private String field_145958_o;
		private int furnaceCookTime;
	    private static final String __OBFID = "CL_00000357";

	@Override
	public int getSizeInventory() {
		return this.ovenItemStacks.length;
	}

	@Override
	public ItemStack getStackInSlot(int id) {
		// TODO Auto-generated method stub
		return this.ovenItemStacks[id];
	}

	 @Override
	public ItemStack decrStackSize(int slotID, int Itemstack)
	    {
	        if (this.ovenItemStacks[slotID] != null)
	        {
	            ItemStack itemstack;

	            if (this.ovenItemStacks[slotID].stackSize <= Itemstack)
	            {
	                itemstack = this.ovenItemStacks[slotID];
	                this.ovenItemStacks[slotID] = null;
	                return itemstack;
	            }
	            else
	            {
	                itemstack = this.ovenItemStacks[slotID].splitStack(Itemstack);

	                if (this.ovenItemStacks[slotID].stackSize == 0)
	                {
	                    this.ovenItemStacks[slotID] = null;
	                }

	                return itemstack;
	            }
	        }
	        else
	        {
	            return null;
	        }
	    }

	@Override
	public ItemStack getStackInSlotOnClosing(int slotID)
	{
		        if (this.ovenItemStacks[slotID] != null)
		        {
		            ItemStack itemstack = this.ovenItemStacks[slotID];
		            this.ovenItemStacks[slotID] = null;
		            return itemstack;
		        }
		        else
		        {
		            return null;
		        }
	}
	

	@Override
	public void setInventorySlotContents(int slotID, ItemStack StackID) {
        this.ovenItemStacks[slotID] = StackID;

        if (StackID != null && StackID.stackSize > this.getInventoryStackLimit())
        {
            StackID.stackSize = this.getInventoryStackLimit();
        }		
	}
	
	@Override
    public String getInventoryName()
    {
		return null;
    }
	
	@Override
	public void readFromNBT(NBTTagCompound p_145839_1_)
    {
        super.readFromNBT(p_145839_1_);
        NBTTagList nbttaglist = p_145839_1_.getTagList("Items", 10);
        this.ovenItemStacks = new ItemStack[this.getSizeInventory()];

        for (int i = 0; i < nbttaglist.tagCount(); ++i)
        {
            NBTTagCompound nbttagcompound1 = nbttaglist.getCompoundTagAt(i);
            byte b0 = nbttagcompound1.getByte("Slot");

            if (b0 >= 0 && b0 < this.ovenItemStacks.length)
            {
                this.ovenItemStacks[b0] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
            }
        }

        this.ovenBurnTime = p_145839_1_.getShort("BurnTime");
        this.currentItemBurnTime = getItemBurnTime(this.ovenItemStacks[1]);

        if (p_145839_1_.hasKey("CustomName", 8))
        {
            this.field_145958_o = p_145839_1_.getString("CustomName");
        }
    }

    @Override
	public void writeToNBT(NBTTagCompound p_145841_1_)
    {
        super.writeToNBT(p_145841_1_);
        p_145841_1_.setShort("BurnTime", (short)this.ovenBurnTime);
        NBTTagList nbttaglist = new NBTTagList();

        for (int i = 0; i < this.ovenItemStacks.length; ++i)
        {
            if (this.ovenItemStacks[i] != null)
            {
                NBTTagCompound nbttagcompound1 = new NBTTagCompound();
                nbttagcompound1.setByte("Slot", (byte)i);
                this.ovenItemStacks[i].writeToNBT(nbttagcompound1);
                nbttaglist.appendTag(nbttagcompound1);
            }
        }

        p_145841_1_.setTag("Items", nbttaglist);

        if (this.hasCustomInventoryName())
        {
            p_145841_1_.setString("CustomName", this.field_145958_o);
        }
    }


	@Override
	public boolean hasCustomInventoryName() {
		return false;
	}

	@Override
	public int getInventoryStackLimit() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	 public boolean isUseableByPlayer(EntityPlayer p_70300_1_)
    {
        return this.worldObj.getTileEntity(this.xCoord, this.yCoord, this.zCoord) != this ? false : p_70300_1_.getDistanceSq(this.xCoord + 0.5D, this.yCoord + 0.5D, this.zCoord + 0.5D) <= 64.0D;
    }


	@Override
	public void openInventory() {}

	@Override
	public void closeInventory() {}

	@Override
    public boolean isItemValidForSlot(int slotID, ItemStack itemStack)
    {
        return slotID == 2 ? false : (slotID == 1 ? isItemFuel(itemStack) : true);
    }

    @Override
	public int[] getAccessibleSlotsFromSide(int slotID)
    {
        return slotID == 0 ? slotsBottom : (slotID == 1 ? slotsTop : slotsSides);
    }
	
	@Override
    public boolean canInsertItem(int slotID, ItemStack itemStack, int side)
    {
        return this.isItemValidForSlot(slotID, itemStack);
    }

    @Override
    public boolean canExtractItem(int slotID, ItemStack itemStack, int side)
    {
        return side != 0 || slotID != 1 || itemStack.getItem() == Items.bucket;
    }
	
	public static int getItemBurnTime(ItemStack itemStack)
    {
            return GameRegistry.getFuelValue(itemStack);        
    }
	public static boolean isItemFuel(ItemStack itemStack)
	{
        return getItemBurnTime(itemStack) > 0;
	 }

    @SideOnly(Side.CLIENT)
    public int getBurnTimeRemainingScaled(int p_145955_1_)
    {
        if (this.currentItemBurnTime == 0)
        {
            this.currentItemBurnTime = 200;
        }

        return this.ovenBurnTime * p_145955_1_ / this.currentItemBurnTime;
    }

    /**
     * Furnace isBurning
     */
    public boolean isBurning()
    {
        return this.ovenBurnTime > 0;
    }

    @Override
	public void updateEntity()
    {
        boolean flag = this.ovenBurnTime > 0;
        boolean flag1 = false;

        if (this.ovenBurnTime > 0)
        {
            --this.ovenBurnTime;
        }

        if (!this.worldObj.isRemote)
        {
            if (this.ovenBurnTime != 0 || this.ovenItemStacks[1] != null && this.ovenItemStacks[0] != null)
            {
                if (this.ovenBurnTime == 0 && this.canSmelt())
                {
                    this.currentItemBurnTime = this.ovenBurnTime = getItemBurnTime(this.ovenItemStacks[1]);

                    if (this.ovenBurnTime > 0)
                    {
                        flag1 = true;

                        if (this.ovenItemStacks[1] != null)
                        {
                            --this.ovenItemStacks[1].stackSize;

                            if (this.ovenItemStacks[1].stackSize == 0)
                            {
                                this.ovenItemStacks[1] = ovenItemStacks[1].getItem().getContainerItem(ovenItemStacks[1]);
                            }
                        }
                    }
                }

                if (this.isBurning() && this.canSmelt())
                {
                    ++this.furnaceCookTime;

                    if (this.furnaceCookTime == 200)
                    {
                        this.furnaceCookTime = 0;
                        this.smeltItem();
                        flag1 = true;
                    }
                }
                else
                {
                    this.furnaceCookTime = 0;
                }
            }

            if (flag != this.ovenBurnTime > 0)
            {
                flag1 = true;
                BlockFurnace.updateFurnaceBlockState(this.ovenBurnTime > 0, this.worldObj, this.xCoord, this.yCoord, this.zCoord);
            }
        }

        if (flag1)
        {
            this.markDirty();
        }
    }

    /**
     * Returns true if the furnace can smelt an item, i.e. has a source item, destination stack isn't full, etc.
     */
    private boolean canSmelt()
    {
        if (this.ovenItemStacks[0] == null)
        {
            return false;
        }
        else
        {
            ItemStack itemstack = OvenRecipes.smelting().getSmeltingResult(this.ovenItemStacks[0]);
            if (itemstack == null) return false;
            if (this.ovenItemStacks[2] == null) return true;
            if (!this.ovenItemStacks[2].isItemEqual(itemstack)) return false;
            int result = ovenItemStacks[2].stackSize + itemstack.stackSize;
            return result <= getInventoryStackLimit() && result <= this.ovenItemStacks[2].getMaxStackSize(); //Forge BugFix: Make it respect stack sizes properly.
        }
    }

    /**
     * Turn one item from the furnace source stack into the appropriate smelted item in the furnace result stack
     */
    public void smeltItem()
    {
        if (this.canSmelt())
        {
            ItemStack itemstack = OvenRecipes.smelting().getSmeltingResult(this.ovenItemStacks[0]);

            if (this.ovenItemStacks[2] == null)
            {
                this.ovenItemStacks[2] = itemstack.copy();
            }
            else if (this.ovenItemStacks[2].getItem() == itemstack.getItem())
            {
                this.ovenItemStacks[2].stackSize += itemstack.stackSize; // Forge BugFix: Results may have multiple items
            }

            --this.ovenItemStacks[0].stackSize;

            if (this.ovenItemStacks[0].stackSize <= 0)
            {
                this.ovenItemStacks[0] = null;
            }
        }
    }
    @SideOnly(Side.CLIENT)
    public int getCookProgressScaled(int scale)
    {
        return this.furnaceCookTime * scale / 200;
    }

}
