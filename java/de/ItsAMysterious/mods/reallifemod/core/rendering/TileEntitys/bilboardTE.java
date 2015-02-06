package de.ItsAMysterious.mods.reallifemod.core.rendering.TileEntitys;

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
}
