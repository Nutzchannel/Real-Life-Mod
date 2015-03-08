package de.ItsAMysterious.mods.reallifemod.core.tiles;

import java.util.Random;

import de.ItsAMysterious.mods.reallifemod.core.tiles.trafficlightTE.State;

public class fireplaceTE extends TileEntityDirectional{

	public boolean burning=false;
	private Random rand;

	public fireplaceTE() {
	}
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
    
	@Override
    public int getBlockMetadata()
    {
		if(this.worldObj!=null){
        if (this.blockMetadata == -1)
        {
            this.blockMetadata = this.worldObj.getBlockMetadata(this.xCoord, this.yCoord, this.zCoord);
        }
        return this.blockMetadata;
    }else
    	return 1;
    }


}
