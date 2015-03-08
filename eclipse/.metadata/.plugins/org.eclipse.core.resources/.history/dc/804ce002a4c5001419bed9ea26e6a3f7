package de.ItsAMysterious.mods.reallifemod.core.entitys.npcs;

import org.lwjgl.input.Keyboard;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityLanz extends Entity 
{
	private boolean isEmpty;
	private int lanzPosRotationIncrements;
	private double lanzX;
	private double lanzY;
	private double lanzZ;
	private double lanzYaw;
	private double lanzPitch;
	@SideOnly(Side.CLIENT)
	private double velocityX;
	@SideOnly(Side.CLIENT)
	private double velocityY;
	@SideOnly(Side.CLIENT)
	private double velocityZ;
	public float speed;
	private final float acceleration=0.1F;
	float timepressed;
	private float move;
	private float radEinschlag;



	public EntityLanz(World par1World)
	{
		super(par1World);
		this.riddenByEntity=null;
		this.isEmpty=true;
		this.preventEntitySpawning=true;
		this.speed=0;
		this.setSize(1,2);
	}

	public EntityLanz(World world, double x, double y, double z) {
		this(world);
		this.setPosition(x, y, z);
		this.setRotation(rotationYaw, rotationPitch);
		this.motionX=0.0F;
		this.motionY=0.0F;
		this.motionZ=0.0F;
	}

	@Override
	protected void entityInit()
	{
		this.dataWatcher.addObject(17, new Integer(0));
		this.dataWatcher.addObject(18, new Integer(1));
		this.dataWatcher.addObject(19, new Float(0.0F));
	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound p_70037_1_) {}

	@Override
	protected void writeEntityToNBT(NBTTagCompound p_70014_1_) {}

	
	@Override
	protected void updateFallState(double p_70064_1_, boolean p_70064_3_)
    {
        int i = MathHelper.floor_double(this.posX);
        int j = MathHelper.floor_double(this.posY);
        int k = MathHelper.floor_double(this.posZ);

        if (p_70064_3_)
        {
            if (this.fallDistance > 3.0F)
            {
                this.fall(this.fallDistance);

                if (!this.worldObj.isRemote && !this.isDead)
                {
                    this.setDead();
                    int l;

                }

                this.fallDistance = 0.0F;
            }
        }
        else if (this.worldObj.getBlock(i, j - 1, k).getMaterial() == Material.air && p_70064_1_ < 0.0D)
        {
            this.fallDistance = (float)(this.fallDistance - p_70064_1_);
        }
    }

	@Override
	public boolean attackEntityFrom(DamageSource p_70097_1_, float p_70097_2_)
	{
		if (this.isEntityInvulnerable())
		{
			return false;
		}
		else if (!this.worldObj.isRemote && !this.isDead)
		{
			this.setForwardDirection(-this.getForwardDirection());
			this.setTimeSinceHit(10);
			this.setDamageTaken(this.getDamageTaken() + p_70097_2_ * 10.0F);
			this.setBeenAttacked();
			boolean flag = p_70097_1_.getEntity() instanceof EntityPlayer && ((EntityPlayer)p_70097_1_.getEntity()).capabilities.isCreativeMode;

			if (flag || this.getDamageTaken() > 40.0F)
			{
				if (this.riddenByEntity != null)
				{
					this.riddenByEntity.mountEntity(this);
				}

				this.setDead();
			}

			return true;
		}
		else
		{
			return true;
		}
	}

	@Override
	public boolean interactFirst(EntityPlayer entityPlayer)
	{
		if (this.riddenByEntity != null && this.riddenByEntity instanceof EntityPlayer)
		{
			return true;
		}
		else
		{
			if (!this.worldObj.isRemote)
			{
				entityPlayer.mountEntity(this);
			}

			return true;
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void performHurtAnimation()
	{
		for(int i=0;i<100;i++){
			this.posY+=i/1000;
		}
		for(int i=100;i>0;i--){
			this.posY-=i/1000;
		}
		this.setForwardDirection(-this.getForwardDirection());
		this.setTimeSinceHit(1);
		this.setDamageTaken(this.getDamageTaken() * 11.0F);
	}
	
	@Override
	public void onUpdate() {
		super.onUpdate();
		if (this.getTimeSinceHit() > 0)
		{
			this.setTimeSinceHit(this.getTimeSinceHit() - 1);
		}

		if (this.getDamageTaken() > 0.0F)
		{
			this.setDamageTaken(this.getDamageTaken() - 1.0F);
		}
		
		if(this.riddenByEntity!=null&&this.riddenByEntity instanceof EntityLivingBase)
		{			
			if(Keyboard.isKeyDown(Keyboard.KEY_W)){
				if(this.timepressed<10){
				this.timepressed+=0.1;
				}
			}else
			if(this.timepressed>1){
				this.timepressed-=2;
			}
			else
			{
				this.timepressed=0;
			}
			System.out.println(this.speed);

			if(Keyboard.isKeyDown(Keyboard.KEY_A)){
				this.rotationYaw+=1;
			}
			if(Keyboard.isKeyDown(Keyboard.KEY_D)){
				this.rotationYaw-=1;
			}
			this.speed=(float) Math.sqrt((2*(this.acceleration))*(this.timepressed));
			this.rotationYaw+=this.radEinschlag;
            this.motionX=-Math.sin(this.rotationYaw * Math.PI / -180.0D);
            this.motionZ=Math.cos(this.rotationYaw * Math.PI / 180.0D);
            this.motionX*=speed/5;
            this.motionZ*=speed/5;
			this.moveEntity(this.motionX, this.motionY, this.motionZ);
		}

	}

	@Override
	public void updateRiderPosition()
	{
		if (this.riddenByEntity != null)
		{
			double d0 = Math.cos(this.rotationYaw * Math.PI / 180.0D) * 0.4D;
			double d1 = Math.sin(this.rotationYaw * Math.PI / 180.0D) * 0.4D;
			this.riddenByEntity.setPosition(this.posX + d0, this.posY+2, this.posZ + d1);
		}
	}

	@Override
	public AxisAlignedBB getCollisionBox(Entity entity)
	{
		return entity.boundingBox;
	}

	@Override
	public AxisAlignedBB getBoundingBox()
	{
		return this.boundingBox;
	}

	@Override
	public boolean canBePushed()
	{
		return false;
	}

	@Override
	public boolean canBeCollidedWith()
	{
		return !this.isDead;
	}

	public void setDamageTaken(float p_70266_1_)
	{
		this.dataWatcher.updateObject(19, Float.valueOf(p_70266_1_));
	}

	public float getDamageTaken()
	{
		return this.dataWatcher.getWatchableObjectFloat(19);
	}

	public void setTimeSinceHit(int p_70265_1_)
	{
		this.dataWatcher.updateObject(17, Integer.valueOf(p_70265_1_));
	}

	public int getTimeSinceHit()
	{
		return this.dataWatcher.getWatchableObjectInt(17);
	}

	public void setForwardDirection(int p_70269_1_)
	{
		this.dataWatcher.updateObject(18, Integer.valueOf(p_70269_1_));
	}

	public int getForwardDirection()
	{
		return this.dataWatcher.getWatchableObjectInt(18);
	}
}