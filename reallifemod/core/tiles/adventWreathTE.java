package de.ItsAMysterious.mods.reallifemod.core.tiles;


public class adventWreathTE extends TileEntityDirectional{
	enum state{
		FirstAdvent,SecondAdvent,ThirdAdvent,FourthAdvent,None;
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
