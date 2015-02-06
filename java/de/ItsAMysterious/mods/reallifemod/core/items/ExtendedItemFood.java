package de.ItsAMysterious.mods.reallifemod.core.items;

import de.ItsAMysterious.mods.reallifemod.api.Interfaces.Buyable;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;

public class ExtendedItemFood extends ItemFood implements Buyable{
	public ExtendedItemFood(int p_i45340_1_, boolean p_i45340_2_) {
		super(p_i45340_1_, p_i45340_2_);
		// TODO Auto-generated constructor stub
	}

	public ExtendedItemFood(int p_i45340_1_, float f1,boolean p_i45340_2_) {
		super(p_i45340_1_,f1, p_i45340_2_);
		// TODO Auto-generated constructor stub
	}

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
