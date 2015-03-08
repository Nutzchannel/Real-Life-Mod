package de.ItsAMysterious.mods.reallifemod.core.tiles;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

public class railingTE extends TileEntity {

	public enum Sites{
		left,right;
	}
	public static Sites site;
	
	public railingTE() {
		this.site=site.left;
	}
	
	@Override
    public void readFromNBT(NBTTagCompound compound){
		super.readFromNBT(compound);
		NBTTagCompound comp=compound.getCompoundTag("SiteTag");
    	this.site=Sites.valueOf(comp.getString("site"));
    }
	
	@Override
    public void writeToNBT(NBTTagCompound compound){
		super.writeToNBT(compound);
		NBTTagCompound comp=new NBTTagCompound();
    	comp.setString("site", this.site.name());
    	compound.setTag("SiteTag", comp);
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
    		theTag.setString("site",this.site.name());
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
