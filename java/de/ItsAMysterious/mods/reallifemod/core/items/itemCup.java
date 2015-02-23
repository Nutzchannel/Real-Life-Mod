package de.ItsAMysterious.mods.reallifemod.core.items;

import de.ItsAMysterious.mods.reallifemod.RealLifeMod;

public class itemCup extends ExtendedItemFood{
	public CupType theType;
	
	public itemCup(int p_i45340_1_, boolean p_i45340_2_, CupType type) {
		super(p_i45340_1_, p_i45340_2_);
		theType=type;
		setTextureName(RealLifeMod.ID+":item"+type.name().toLowerCase());
		setUnlocalizedName(type.name().toLowerCase());
		setCreativeTab(RealLifeMod.rlmfood);
		setPrice(1.50F);
	}
	

}
