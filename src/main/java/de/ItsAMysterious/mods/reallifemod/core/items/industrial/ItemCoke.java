package de.ItsAMysterious.mods.reallifemod.core.items.industrial;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.IFuelHandler;
import de.ItsAMysterious.mods.reallifemod.core.items.ExtendedItem;

public class ItemCoke extends ExtendedItem implements IFuelHandler{
	public ItemCoke(){
	this.setUnlocalizedName("coke"); // itemCoke
	this.shouldRotateAroundWhenRendering();
	this.setMaxDamage(0);
	this.setPrice(15.0);
	}

	@Override
	public int getBurnTime(ItemStack fuel) {
		return 10000;
	}


}
