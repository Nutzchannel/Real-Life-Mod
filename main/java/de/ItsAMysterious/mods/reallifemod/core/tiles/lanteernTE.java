package de.ItsAMysterious.mods.reallifemod.core.tiles;

public class lanteernTE extends TileEntityDirectional {
	public boolean active=false;
	
	public void updateEntity(){
		if(!this.worldObj.isDaytime()){
			this.active=true;
		}else
		{
			this.active=false;
		}
		this.worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
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
