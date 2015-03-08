package de.ItsAMysterious.mods.reallifemod.core.tiles;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;

public class bilboardTE extends TileEntityDirectional {
	public int imageNum=0;
	public ResourceLocation image=new ResourceLocation("minecraft:images/advert"+this.imageNum+".png");

    @Override
	public void readFromNBT(NBTTagCompound nbttagcompound)
    {
        super.readFromNBT(nbttagcompound);  
        this.imageNum=(nbttagcompound.getInteger("imageNumber"));
    }
	
    @Override
	public void writeToNBT(NBTTagCompound nbttagcompound)
    {	
		nbttagcompound.setInteger("imageNumber", this.imageNum);
    	super.writeToNBT(nbttagcompound);

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
