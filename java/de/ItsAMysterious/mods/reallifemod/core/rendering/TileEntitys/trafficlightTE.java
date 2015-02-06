package de.ItsAMysterious.mods.reallifemod.core.rendering.TileEntitys;

public class trafficlightTE extends TileEntityDirectional{
	
	public static enum State{
		GREEN,ORANGE, RED, ORANGE_FLASH, INACTIVE;
	}
	public State state = State.RED;
	
}
