package de.ItsAMysterious.mods.reallifemod.api.Utils;

import de.ItsAMysterious.mods.reallifemod.api.Interfaces.IVehicleEntity;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityDrivable extends Entity implements IVehicleEntity{

	 protected float speed;

	public EntityDrivable(World p_i1582_1_) {
		super(p_i1582_1_);
	}

	public void moveEntityWithHeading(double motionX, double motionZ)
	    {
	        double d0;
	        {
	            float f2 = 0.91F;

	            if (this.onGround)
	            {
	                f2 = this.worldObj.getBlock(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.boundingBox.minY) - 1, MathHelper.floor_double(this.posZ)).slipperiness * 0.91F;
	            }

	            float f3 = 0.16277136F / (f2 * f2 * f2);
	            float f4;

	            if (this.onGround)
	            {
	                f4 = this.speed * f3;
	            }

	            if (this.onGround)
	            {
	                f2 = this.worldObj.getBlock(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.boundingBox.minY) - 1, MathHelper.floor_double(this.posZ)).slipperiness * 0.91F;
	            }


	            this.moveEntity(this.motionX, this.motionY, this.motionZ);

	            if (this.worldObj.isRemote && (!this.worldObj.blockExists((int)this.posX, 0, (int)this.posZ) || !this.worldObj.getChunkFromBlockCoords((int)this.posX, (int)this.posZ).isChunkLoaded))
	            {
	                if (this.posY > 0.0D)
	                {
	                    this.motionY = -0.1D;
	                }
	                else
	                {
	                    this.motionY = 0.0D;
	                }
	            }
	            else
	            {
	                this.motionY -= 0.08D;
	            }

	            this.motionY *= 0.9800000190734863D;
	            this.motionX *= f2;
	            this.motionZ *= f2;
	        }

	        d0 = this.posX - this.prevPosX;
	        double d1 = this.posZ - this.prevPosZ;
	        float f6 = MathHelper.sqrt_double(d0 * d0 + d1 * d1) * 4.0F;
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

	@Override
	public double LightValue() {
		return 1000;
	}

	@Override
	public double lightPoint(float x, float y, float z) {
		return 0;
	}

}
