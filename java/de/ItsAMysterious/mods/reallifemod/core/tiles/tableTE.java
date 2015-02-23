package de.ItsAMysterious.mods.reallifemod.core.tiles;

public class tableTE extends TileEntityDirectional {
	public static enum type{
		plate,legs;
	}
	public static type tableType=type.plate;
	public boolean leftleg,rightleg,leflegback,rightlegback;
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
