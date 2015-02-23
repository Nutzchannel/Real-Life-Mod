package de.ItsAMysterious.mods.reallifemod.core.gui.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import de.ItsAMysterious.mods.reallifemod.core.tiles.freezerTE;

public class ContainerFreezer extends Container {

	public freezerTE freezer;
	
	public ContainerFreezer(InventoryPlayer player, freezerTE freezer) {
		this.freezer = freezer; 
		for(int i=0; i<9;i++){
			this.addSlotToContainer(new Slot(player, i, 46+i*18, 163));
		}
		
		for(int i=0; i<3;i++){
			for(int j=0; i<9;i++){
				this.addSlotToContainer(new Slot(player, i, 9+j+i*9, 142+i*18));
			}
		}
	}
	
	@Override
	public boolean canInteractWith(EntityPlayer p_75145_1_) {
		return true;
	}

}
