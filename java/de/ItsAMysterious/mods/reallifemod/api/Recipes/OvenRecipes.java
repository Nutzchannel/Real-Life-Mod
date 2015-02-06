package de.ItsAMysterious.mods.reallifemod.api.Recipes;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import de.ItsAMysterious.mods.reallifemod.TLMItems;
import net.minecraft.block.Block;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class OvenRecipes {
    private static final OvenRecipes smeltingBase = new OvenRecipes();
    private Map smeltingList = new HashMap();
    private Map experienceList = new HashMap();
    
    public static OvenRecipes smelting()
    {
        return smeltingBase;
    }
    
    private OvenRecipes()
    {
        this.smeltItem(new ItemStack(Items.iron_ingot), new ItemStack(TLMItems.dirtySteel),0.7F);
    }
    
    public void smeltBlock(Block block, ItemStack itemStack, float value)
    {
        this.smeltItemBlock(Item.getItemFromBlock(block), itemStack, value);
    }

    public void smeltItemBlock(Item item, ItemStack itemStack, float value)
    {
        this.smeltItem(new ItemStack(item, 1, 32767), itemStack, value);
    }

    public void smeltItem(ItemStack itemStack, ItemStack itemStack2, float value)
    {
        this.smeltingList.put(itemStack, itemStack2);
        this.experienceList.put(itemStack2, Float.valueOf(value));
    }

    /**
     * Returns the smelting result of an item.
     */
    public ItemStack getSmeltingResult(ItemStack itemStack)
    {
        Iterator iterator = this.smeltingList.entrySet().iterator();
        Entry entry;

        do
        {
            if (!iterator.hasNext())
            {
                return null;
            }

            entry = (Entry)iterator.next();
        }
        while (!this.getItems(itemStack, (ItemStack)entry.getKey()));

        return (ItemStack)entry.getValue();
    }

    private boolean getItems(ItemStack itemStack, ItemStack itemStack2)
    {
        return itemStack2.getItem() == itemStack.getItem() && (itemStack2.getItemDamage() == 32767 || itemStack2.getItemDamage() == itemStack.getItemDamage());
    }

    public Map getSmeltingList()
    {
        return this.smeltingList;
    }

    public float func_151398_b(ItemStack p_151398_1_)
    {
        float ret = p_151398_1_.getItem().getSmeltingExperience(p_151398_1_);
        if (ret != -1) return ret;

        Iterator iterator = this.experienceList.entrySet().iterator();
        Entry entry;

        do
        {
            if (!iterator.hasNext())
            {
                return 0.0F;
            }

            entry = (Entry)iterator.next();
        }
        while (!this.getItems(p_151398_1_, (ItemStack)entry.getKey()));

        return ((Float)entry.getValue()).floatValue();
    }

}
