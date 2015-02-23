package de.ItsAMysterious.mods.reallifemod.core.tiles;

public class radioTE extends TileEntityDirectional {
	protected boolean active;
	
	public void setActive(boolean b){
		this.active=b;
	}
	
	public boolean isActive(){
		return this.active;
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
