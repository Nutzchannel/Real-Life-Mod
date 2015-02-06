package de.ItsAMysterious.mods.reallifemod.core.rendering.TileEntitys;

public class tableTE extends TileEntityDirectional {
	public static enum type{
		plate,legs;
	}
	public static type tableType=type.plate;
	public boolean leftleg,rightleg,leflegback,rightlegback;

}
