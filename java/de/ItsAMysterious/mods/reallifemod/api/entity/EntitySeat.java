package de.ItsAMysterious.mods.reallifemod.api.entity;

import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class EntitySeat extends Entity{

	public EntitySeat(World p_i1582_1_) {
		super(p_i1582_1_);
	}
	
	public EntitySeat(World world,Entity parent,int SeatID,float posX,float posZ) {
		this(world);
		this.setPosition(parent.posX+posX, parent.posY+parent.getMountedYOffset(), parent.posZ+posZ);
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

}
