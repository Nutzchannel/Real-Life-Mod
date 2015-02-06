package de.ItsAMysterious.mods.reallifemod.core.Streets.Entitys;

import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class EntityTrailer extends Entity{
	protected EntityTruck parent;

	public EntityTrailer(World world) {
		super(world);
	}
	
	public EntityTrailer(World world, double x, double y, double z){
		super(world);
		this.setPosition(x, y, z);
		this.ignoreFrustumCheck=true;
	}

	public EntityTrailer(World world, EntityTruck entityTruck, double x, double y, double z) {
		super(world);
		//entityTruck.setTrailer(this);
	}

	@Override
	protected void entityInit() {
	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound p_70037_1_) {}

	@Override
	protected void writeEntityToNBT(NBTTagCompound p_70014_1_) {}
	
	@Override
	public void onUpdate(){
	    	//this.rotationYaw=(float) ((this.parent.velocity/5)*((0.5/20)*Math.sin(this.rotationYaw)+((0.5/20)*Math.cos(this.rotationYaw)+1)*this.parent.velocity/5));
	}
}
