package de.ItsAMysterious.mods.reallifemod.core.rendering.TileEntitys;

import java.util.Random;

public class fireplaceTE extends TileEntityDirectional{

	public boolean burning=false;
	private Random rand;

	public void extinguish() {
		this.burning=false;
	}

	public void lit(){
		this.burning=true;
	}
	
    @Override
	public void updateEntity() {
    	if (this.worldObj.isRaining()){
    		this.extinguish();
    		this.getBlockType().updateTick(worldObj, xCoord, yCoord, zCoord, rand);
    	}
    }


}
