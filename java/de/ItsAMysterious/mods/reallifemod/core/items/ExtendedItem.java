package de.ItsAMysterious.mods.reallifemod.core.items;

import de.ItsAMysterious.mods.reallifemod.api.Interfaces.Buyable;
import net.minecraft.item.Item;

public class ExtendedItem extends Item implements Buyable{
	private double price;
	
	public void setPrice(double thePrice){
		this.price=thePrice;
	}
	@Override
	public double price() {
		return price;
	}

	@Override
	public int percentage() {
		return 0;
	}

}
