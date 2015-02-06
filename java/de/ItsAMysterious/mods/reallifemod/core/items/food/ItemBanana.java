package de.ItsAMysterious.mods.reallifemod.core.items.food;

import de.ItsAMysterious.mods.reallifemod.core.items.ExtendedItem;
import de.ItsAMysterious.mods.reallifemod.core.items.ExtendedItemFood;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;

public class ItemBanana extends ExtendedItemFood{
	
	public ItemBanana(int HealAmmount, boolean isWolfsFavorite) {
		super(HealAmmount, isWolfsFavorite);
		setUnlocalizedName("banana");
		setTextureName("reallifemod:Banana");
		setAlwaysEdible();
		this.setPrice(0.31F);
	}
	@Override
	public EnumAction getItemUseAction(ItemStack par1ItemStack)
    {
        return EnumAction.eat;
    }


}
