package de.ItsAMysterious.mods.reallifemod.core.rendering.TileEntitys;

import java.util.ArrayList;
import java.util.List;

import de.ItsAMysterious.mods.reallifemod.core.Renderers.blocks.models.modelCupboard;
import net.minecraft.item.ItemStack;

public class cupboardTE extends TileEntityDirectional {
	public boolean open;
	public List<ItemStack> items=new ArrayList<ItemStack>();
	public modelCupboard model;
}
