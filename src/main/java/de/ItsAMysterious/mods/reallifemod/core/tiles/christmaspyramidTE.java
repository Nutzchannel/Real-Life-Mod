package de.ItsAMysterious.mods.reallifemod.core.tiles;

public class christmaspyramidTE extends TileEntityDirectional{
	private float rotation=0;
	private boolean active;
	
	public void clicked(){
		if(this.isActive()){
			this.setActive(false);
		}else
		if(!this.isActive()){
			this.setActive(true);
		}
	}

	public boolean isActive() {
		return this.active;
	}

	public boolean setActive(boolean active) {
		this.active = active;
		return active;
	}

	public float getRotation() {
		return this.rotation;
	}

	public void setRotation(float f) {
		this.rotation+=f;
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
