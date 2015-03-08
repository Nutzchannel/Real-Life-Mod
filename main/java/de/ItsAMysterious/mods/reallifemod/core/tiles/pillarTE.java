package de.ItsAMysterious.mods.reallifemod.core.tiles;

public class pillarTE extends TileEntityDirectional{
	public String Texture;
	public float scale=1.0F;
	
	public pillarTE(String texture){
		this.Texture=texture;
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
