package de.ItsAMysterious.mods.reallifemod.core.items.food;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class itemChips extends ExtendedItemFood{


	public itemChips(int heal, float sat, boolean wolfsmeat) {
		super(heal, sat, wolfsmeat);
		
		setUnlocalizedName("frenchfries");
		setTextureName("reallifemod:itemChips");
		this.setPrice(1.20F);
		}
	  
    @Override
	public EnumAction getItemUseAction(ItemStack par1ItemStack)
    {
        return EnumAction.eat;
    }
    

    /**
     * Called whenever this item is equipped and the right mouse button is pressed. Args: itemStack, world, entityPlayer
     */
    @Override
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
    {
        par3EntityPlayer.setItemInUse(par1ItemStack, this.getMaxItemUseDuration(par1ItemStack));
        return par1ItemStack;
    }
}
