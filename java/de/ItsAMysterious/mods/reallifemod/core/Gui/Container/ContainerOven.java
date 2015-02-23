package de.ItsAMysterious.mods.reallifemod.core.gui.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.tileentity.TileEntityFurnace;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ContainerOven extends Container
{
    private OvenTileEntity tileOven;
    private int lastCookTime;
    private int lastBurnTime;
    private int lastItemBurnTime;
    private static final String __OBFID = "CL_00001748";

    public ContainerOven(InventoryPlayer p_i1812_1_, OvenTileEntity tile)
    {
        this.tileOven = tile;
        this.addSlotToContainer(new Slot(tile, 0, 56, 17));
        this.addSlotToContainer(new Slot(tile, 1, 56, 53));
        this.addSlotToContainer(new SlotOven(p_i1812_1_.player, tile, 2, 116, 35));
        int i;

        for (i = 0; i < 3; ++i)
        {
            for (int j = 0; j < 9; ++j)
            {
                this.addSlotToContainer(new Slot(p_i1812_1_, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }

        for (i = 0; i < 9; ++i)
        {
            this.addSlotToContainer(new Slot(p_i1812_1_, i, 8 + i * 18, 142));
        }
    }

    @Override
	public void addCraftingToCrafters(ICrafting p_75132_1_)
    {
        super.addCraftingToCrafters(p_75132_1_);
        p_75132_1_.sendProgressBarUpdate(this, 1, this.tileOven.ovenBurnTime);
        p_75132_1_.sendProgressBarUpdate(this, 2, this.tileOven.currentItemBurnTime);
    }

    /**
     * Looks for changes made in the container, sends them to every listener.
     */
    @Override
	public void detectAndSendChanges()
    {
        super.detectAndSendChanges();

        for (int i = 0; i < this.crafters.size(); ++i)
        {
            ICrafting icrafting = (ICrafting)this.crafters.get(i);

            if (this.lastBurnTime != this.tileOven.ovenBurnTime)
            {
                icrafting.sendProgressBarUpdate(this, 1, this.tileOven.ovenBurnTime);
            }

            if (this.lastItemBurnTime != this.tileOven.currentItemBurnTime)
            {
                icrafting.sendProgressBarUpdate(this, 2, this.tileOven.currentItemBurnTime);
            }
        }

        this.lastBurnTime = this.tileOven.ovenBurnTime;
        this.lastItemBurnTime = this.tileOven.currentItemBurnTime;
    }

    @Override
	@SideOnly(Side.CLIENT)
    public void updateProgressBar(int ovenB, int cItemB)
    {
        if (ovenB == 1)
        {
            this.tileOven.ovenBurnTime = cItemB;
        }

        if (ovenB == 2)
        {
            this.tileOven.currentItemBurnTime = cItemB;
        }
    }

    @Override
	public boolean canInteractWith(EntityPlayer player)
    {
        return this.tileOven.isUseableByPlayer(player);
    }

    /**
     * Called when a player shift-clicks on a slot. You must override this or you will crash when someone does that.
     */
    @Override
	public ItemStack transferStackInSlot(EntityPlayer player, int slotID)
    {
        ItemStack itemstack = null;
        Slot slot = (Slot)this.inventorySlots.get(slotID);

        if (slot != null && slot.getHasStack())
        {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            if (slotID == 2)
            {
                if (!this.mergeItemStack(itemstack1, 3, 39, true))
                {
                    return null;
                }

                slot.onSlotChange(itemstack1, itemstack);
            }
            else if (slotID != 1 && slotID != 0)
            {
                if (FurnaceRecipes.smelting().getSmeltingResult(itemstack1) != null)
                {
                    if (!this.mergeItemStack(itemstack1, 0, 1, false))
                    {
                        return null;
                    }
                }
                else if (TileEntityFurnace.isItemFuel(itemstack1))
                {
                    if (!this.mergeItemStack(itemstack1, 1, 2, false))
                    {
                        return null;
                    }
                }
                else if (slotID >= 3 && slotID < 30)
                {
                    if (!this.mergeItemStack(itemstack1, 30, 39, false))
                    {
                        return null;
                    }
                }
                else if (slotID >= 30 && slotID < 39 && !this.mergeItemStack(itemstack1, 3, 30, false))
                {
                    return null;
                }
            }
            else if (!this.mergeItemStack(itemstack1, 3, 39, false))
            {
                return null;
            }

            if (itemstack1.stackSize == 0)
            {
                slot.putStack((ItemStack)null);
            }
            else
            {
                slot.onSlotChanged();
            }

            if (itemstack1.stackSize == itemstack.stackSize)
            {
                return null;
            }

            slot.onPickupFromSlot(player, itemstack1);
        }

        return itemstack;
    }
    
}