package de.ItsAMysterious.mods.reallifemod.core.gui.container;

import cpw.mods.fml.common.FMLCommonHandler;
import de.ItsAMysterious.mods.reallifemod.TLMItems;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.util.MathHelper;

public class SlotOven extends Slot{
    /** The player that is using the GUI where this slot resides. */
private EntityPlayer thePlayer;
private int field_75228_b;
private static final String __OBFID = "CL_00001749";

public SlotOven(EntityPlayer player, IInventory iInventory, int x, int y, int z)
{
    super(iInventory, x, y, z);
    this.thePlayer = player;
}

/**
 * Check if the stack is a valid item for this slot. Always true beside for the armor slots.
 */
@Override
public boolean isItemValid(ItemStack p_75214_1_)
{
    return false;
}

/**
 * Decrease the size of the stack in slot (first int arg) by the amount of the second int arg. Returns the new
 * stack.
 */
@Override
public ItemStack decrStackSize(int p_75209_1_)
{
    if (this.getHasStack())
    {
        this.field_75228_b += Math.min(p_75209_1_, this.getStack().stackSize);
    }

    return super.decrStackSize(p_75209_1_);
}

@Override
public void onPickupFromSlot(EntityPlayer p_82870_1_, ItemStack p_82870_2_)
{
    this.onCrafting(p_82870_2_);
    super.onPickupFromSlot(p_82870_1_, p_82870_2_);
}

@Override
protected void onCrafting(ItemStack itemStack, int id)
{
    this.field_75228_b += id;
    this.onCrafting(itemStack);
}

/**
 * the itemStack passed in is the output - ie, iron ingots, and pickaxes, not ore and wood.
 */
    @Override
	protected void onCrafting(ItemStack itemStack)
    {
        itemStack.onCrafting(this.thePlayer.worldObj, this.thePlayer, this.field_75228_b);

        if (!this.thePlayer.worldObj.isRemote)
        {
            int i = this.field_75228_b;
            float f = FurnaceRecipes.smelting().func_151398_b(itemStack);
            int j;

            if (f == 0.0F)
            {
                i = 0;
            }
            else if (f < 1.0F)
            {
                j = MathHelper.floor_float(i * f);

                if (j < MathHelper.ceiling_float_int(i * f) && (float)Math.random() < i * f - j)
                {
                    ++j;
                }

                i = j;
            }

            while (i > 0)
            {
                j = EntityXPOrb.getXPSplit(i);
                i -= j;
                this.thePlayer.worldObj.spawnEntityInWorld(new EntityXPOrb(this.thePlayer.worldObj, this.thePlayer.posX, this.thePlayer.posY + 0.5D, this.thePlayer.posZ + 0.5D, j));
            }
        }

        this.field_75228_b = 0;

        FMLCommonHandler.instance().firePlayerSmeltedEvent(thePlayer, itemStack);
        
        if(itemStack.getItem()==TLMItems.dirtySteel)
        {
        }

    }
}
