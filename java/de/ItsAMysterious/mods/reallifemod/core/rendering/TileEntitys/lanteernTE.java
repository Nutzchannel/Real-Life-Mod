package de.ItsAMysterious.mods.reallifemod.core.rendering.TileEntitys;

public class lanteernTE extends TileEntityDirectional {
	public boolean active=false;
	
	/**public TileEntityLantern update(TickEvent event){
		if(!this.worldObj.isDaytime()){
			this.active=true;
		}else
		{
			this.active=false;
		}
		this.worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
		return this;
	}*/
	
}
