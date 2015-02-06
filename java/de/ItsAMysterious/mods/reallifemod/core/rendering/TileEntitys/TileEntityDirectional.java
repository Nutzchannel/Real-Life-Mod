package de.ItsAMysterious.mods.reallifemod.core.rendering.TileEntitys;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

public class TileEntityDirectional extends TileEntity {
	protected int facingDirection;
	private double heightToSubstract;
	
	public int getFacingDirection()
    {
        return facingDirection;
    }
	
	public void setFacingDirection(int par1)
    {
        facingDirection = par1;
    }
	
	@Override
    public void readFromNBT(NBTTagCompound nbttagcompound)
    {
		super.readFromNBT(nbttagcompound);
		NBTTagCompound comb=nbttagcompound.getCompoundTag("DIRECTION");
		this.facingDirection=comb.getInteger("facingDirection");
    }
	
	@Override
    public void writeToNBT(NBTTagCompound nbttagcompound)
    {	
		NBTTagCompound comb=new NBTTagCompound();
		comb.setInteger("facingDirection", facingDirection);
        nbttagcompound.setTag("DIRECTION", comb);
    }
	
    @Override
	public Packet getDescriptionPacket()
    {
        NBTTagCompound tag = new NBTTagCompound();
        tag.setInteger("facingDirection", facingDirection);
        this.writeToNBT(tag);
        return new S35PacketUpdateTileEntity(xCoord, yCoord, zCoord, 1, tag);
    }

    @Override
	public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity packet)
    {
    	this.readFromNBT(packet.func_148857_g());
    }
    
	public double getHeightToSubstract() {
		return heightToSubstract;
	}

	public void setHeightToSubstract(double heightToSubstract) {
		this.heightToSubstract = heightToSubstract;
	}
}