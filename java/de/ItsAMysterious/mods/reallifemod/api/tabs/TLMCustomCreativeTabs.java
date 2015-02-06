package de.ItsAMysterious.mods.reallifemod.api.tabs;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;

public class TLMCustomCreativeTabs extends CreativeTabs{

	public TLMCustomCreativeTabs(String lable) {
		super(lable);
	}

	@Override
	public Item getTabIconItem() {
		return Items.stone_hoe;
	}
	
    @Override
	@SideOnly(Side.CLIENT)
    public String getTranslatedTabLabel()
    {
        return this.getTabLabel();
    }

}
