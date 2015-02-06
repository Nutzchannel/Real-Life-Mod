package de.ItsAMysterious.mods.reallifemod.core.Streets.Entitys;

import net.minecraft.world.World;

public class EntityTruck extends EntityBasicVehicle 
{

	public EntityTruck(World world) {
		super(world);
	}
	
	public EntityTruck(World world, double x, double y, double z) {
		super(world,x,y,z, 7.0F, 10000.0F);
	}


    /*@Override
	public void updateRiderPosition(){
    	double z=Math.cos((this.rotationYaw+6.5)*Math.PI /180.0)*6.5;//*5.0990195135927848300282241090228D;
    	double y=posY+Math.tan(rotationPitch*Math.PI/180+10)*8;
    	double x=Math.sin((this.rotationYaw+8)*Math.PI/180)*6.5;
    	this.riddenByEntity.setPosition(this.posX+x, this.posY+3.5, this.posZ+z);
    	this.riddenByEntity.rotationYaw-=this.getSpeed()*this.K;
    	//Vec3 look=this.riddenByEntity.getLookVec();
    	//his.riddenByEntity.setWorld(worldObj);
    	//this.riddenByEntity.setRotationYawHead(this.rotationYaw);
	}*/
}