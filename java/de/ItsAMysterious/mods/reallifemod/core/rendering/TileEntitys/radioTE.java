package de.ItsAMysterious.mods.reallifemod.core.rendering.TileEntitys;

public class radioTE extends TileEntityDirectional {
	protected boolean active;
	
	public void setActive(boolean b){
		this.active=b;
	}
	
	public boolean isActive(){
		return this.active;
	}

}
