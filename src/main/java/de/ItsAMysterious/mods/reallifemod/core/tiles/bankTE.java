package de.ItsAMysterious.mods.reallifemod.core.tiles;

public class bankTE extends TileEntitySitting {
	private double heightToSubstract;

	@Override
	public double getHeightToSubstract() {
		return heightToSubstract;
	}

	@Override
	public void setHeightToSubstract(double heightToSubstract) {
		this.heightToSubstract = heightToSubstract;
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
