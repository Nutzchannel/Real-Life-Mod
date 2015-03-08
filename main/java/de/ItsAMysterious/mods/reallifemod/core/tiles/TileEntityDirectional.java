package de.ItsAMysterious.mods.reallifemod.core.tiles;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

public class TileEntityDirectional extends TileEntity {
	protected int facingDirection;
	private double heightToSubstract;
	public TileEntityDirectional() {
		this.facingDirection=0;
		this.heightToSubstract=0;
	}
	public int getFacingDirection()
    {
        return this.facingDirection;
    }
	
	public void setFacingDirection(int par1)
    {
        facingDirection = par1;
    }
	
	@Override
    public void readFromNBT(NBTTagCompound compound){
		super.readFromNBT(compound);
		NBTTagCompound tag=compound.getCompoundTag("dirctionalTag");
    	this.facingDirection=tag.getInteger("facingDirection");

    }
	
	@Override
    public void writeToNBT(NBTTagCompound compound){
    	super.writeToNBT(compound);
		NBTTagCompound tag=new NBTTagCompound();
    	tag.setInteger("facingDirection",this.facingDirection);
    	compound.setTag("dirctionalTag", tag);
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
    		tag.setInteger("facingDirection",this.facingDirection);
    		theTag.setTag("dirctionalTag", tag);
            this.writeToNBT(theTag);
            return new S35PacketUpdateTileEntity(this.xCoord, this.yCoord, this.zCoord, this.blockMetadata, theTag);
    }
    
	public double getHeightToSubstract() {
		return heightToSubstract;
	}

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