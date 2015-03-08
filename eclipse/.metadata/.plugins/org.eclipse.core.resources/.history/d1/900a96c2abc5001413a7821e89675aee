package de.ItsAMysterious.mods.reallifemod.core.items.industrial;

import cpw.mods.fml.common.IFuelHandler;
import de.ItsAMysterious.mods.reallifemod.core.items.ExtendedItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemCoke extends ExtendedItem implements IFuelHandler{
	public ItemCoke(){
	this.setUnlocalizedName("coke");
	setTextureName("reallifemod:itemCoke");
	this.shouldRotateAroundWhenRendering();
	this.setMaxDamage(0);
	this.setPrice(15.0);
	}

	@Override
	public int getBurnTime(ItemStack fuel) {
		return 10000;
	}


}
