package de.ItsAMysterious.mods.reallifemod.core.rendering.TileEntitys;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.item.ItemStack;

public class freezerTE extends TileEntityDirectional {
	private boolean open=false;
	public List<ItemStack>storedItems= new ArrayList<ItemStack>(24);
	public static boolean isOpen=false;
}
