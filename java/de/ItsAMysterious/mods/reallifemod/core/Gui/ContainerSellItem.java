package de.ItsAMysterious.mods.reallifemod.core.Gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;

public class ContainerSellItem extends Container{

	public ContainerSellItem(InventoryPlayer p_i1812_1_, TileEntityItemSelling p_i1812_2_) {
		//this.addSlotToContainer(new Slot(p_i1812_2_, field_94535_f, field_94535_f, field_94535_f))
	}
	@Override
	public boolean canInteractWith(EntityPlayer p_75145_1_) {
		return true;
	}

}
