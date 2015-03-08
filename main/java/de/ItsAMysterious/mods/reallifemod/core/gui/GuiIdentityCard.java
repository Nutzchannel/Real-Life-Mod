package de.ItsAMysterious.mods.reallifemod.core.gui;

import net.minecraft.client.gui.GuiScreenBook;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class GuiIdentityCard extends GuiScreenBook{

	public GuiIdentityCard(EntityPlayer player, ItemStack itemStack,
			boolean b1) {
		super(player, itemStack, b1);
	}

}
