package de.ItsAMysterious.mods.reallifemod.core.tiles;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;

import org.lwjgl.input.Keyboard;

public class TileEntitySitting extends TileEntityDirectional{

	private boolean sitting=false;
	private EntityPlayer EntitySitting=null;
	private double yOffset;
	
	public void sitdown(EntityPlayer entity,double yOfset){
		this.yOffset=yOfset;
		this.EntitySitting=entity;
		this.setSitting(true);
	}
	
	public void updateEntity() {
    	super.updateEntity();
    	if(this.setSitting(true && this.EntitySitting!=null)){
    		this.EntitySitting.setSneaking(true);
    		this.EntitySitting.setPosition(this.xCoord+0.5, this.yCoord+1+this.yOffset, this.zCoord+0.5);
    		this.EntitySitting.noClip=true;
    		this.EntitySitting.motionX=0;
    		this.EntitySitting.motionZ=0;
    		this.markDirty();
    	}else
    	{
    		if(this.EntitySitting!=null){
    			this.dismountEntity();
    		}
    		this.markDirty();
    	}
    	if(Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)){
    		if(this.EntitySitting!=null){
    			this.dismountEntity();
    		}    	
    	}
    }
    
	public void readFromNBT(NBTTagCompound nbttagcompound)
    {
    	super.readFromNBT(nbttagcompound);
        this.setSitting(nbttagcompound.getBoolean("sitting"));
    }
	
	public void writeToNBT(NBTTagCompound nbttagcompound)
    {	
    	super.writeToNBT(nbttagcompound);
		nbttagcompound.setBoolean("sitting", this.isSitting());
    }

	public void dismountEntity() {
		this.EntitySitting.setPosition(this.xCoord,  this.yCoord+3, this.zCoord);
		this.EntitySitting.noClip=false;
		this.EntitySitting=null;
	}
	
	public boolean isSitting() {
		return sitting;
	}

	public boolean setSitting(boolean sitting) {
		this.sitting = sitting;
		return sitting;
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
