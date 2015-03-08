package de.ItsAMysterious.mods.reallifemod.api.entity;

import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class EntityHalo extends Entity{

	public EntityHalo(World p_i1582_1_) {
		super(p_i1582_1_);
	}

	@Override
	protected void entityInit() {
		
	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound p_70037_1_) {
		
	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound p_70014_1_) {
		
	}
    // if(this.velocity!=0.0F){
    //this.motionX=-Math.sin((this.rotatedYaw*Math.PI / -180.0))*this.velocity;
   // this.motionZ=Math.cos((this.rotatedYaw*Math.PI / 180.0))*this.velocity;
   // } 
}
