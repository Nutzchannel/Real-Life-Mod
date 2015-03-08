package de.ItsAMysterious.mods.reallifemod.core.tiles;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.*;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

public class growpotTE extends TileEntity {
	public boolean isFilled;
	public growpotTE() {
		this.isFilled=false;
	}
	@Override
    public void readFromNBT(NBTTagCompound compound){
		super.readFromNBT(compound);
		NBTTagCompound tag=compound.getCompoundTag("GrowPotTag");
    	this.isFilled=tag.getBoolean("IsFilled");

    }
	
	@Override
    public void writeToNBT(NBTTagCompound compound){
    	super.writeToNBT(compound);
		NBTTagCompound tag=new NBTTagCompound();
    	tag.setBoolean("IsFilled",this.isFilled);
    	compound.setTag("GrowPotTag", tag);
    	worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
    }
	
    @Override
    public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
            NBTTagCompound nbttagcompound = pkt.func_148857_g();
            this.readFromNBT(nbttagcompound);
    }
   
    @Override
    public Packet getDescriptionPacket() {
			NBTTagCompound theTag=new NBTTagCompound();
			NBTTagCompound tag=new NBTTagCompound();
    		tag.setBoolean("IsFilled",this.isFilled);
    		theTag.setTag("GrowPotTag", tag);
            this.writeToNBT(theTag);
            return new S35PacketUpdateTileEntity(this.xCoord, this.yCoord, this.zCoord, this.blockMetadata, theTag);
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
