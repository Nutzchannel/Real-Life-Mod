package de.ItsAMysterious.mods.reallifemod.core.tiles;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

import org.lwjgl.util.vector.Vector3f;

public class tableTE extends TileEntity {
	public static List<Vector3f>edges=new ArrayList<Vector3f>();

	@Override
    public void readFromNBT(NBTTagCompound compound){
		super.readFromNBT(compound);
		NBTTagCompound tag=compound.getCompoundTag("TableTag");
		this.edges=new ArrayList<Vector3f>(tag.getInteger("numberofedges"));
		for(int i=0; i<this.edges.size();i++){
			this.edges.add(i,new Vector3f(tag.getIntArray("Edge"+i)[0],tag.getIntArray("Edge"+i)[1],tag.getIntArray("Edge"+i)[2]));
		}
    }
	
	@Override
    public void writeToNBT(NBTTagCompound compound){
    	super.writeToNBT(compound);
		NBTTagCompound tag=new NBTTagCompound();
		compound.setTag("TableTag", tag);
		tag.setInteger("numberofedges", edges.size());
		for(int i=0; i<edges.size();i++){
			tag.setIntArray("Edge"+i,new int[]{ (int) this.edges.get(i).x,(int) this.edges.get(i).y,(int) this.edges.get(i).z});
			System.out.println(this.getClass().getName()+"Saved edges:"+edges.get(i).x+" "+edges.get(i).y+" "+edges.get(i).z);
		}
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
    		theTag.setTag("TableTag", tag);
			tag.setInteger("numberofedges", this.edges.size());
			for(int i=0; i<edges.size();i++){
				tag.setIntArray("Edge"+i,new int[]{ (int) edges.get(i).x,(int) edges.get(i).y,(int) edges.get(i).z});
			}
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
