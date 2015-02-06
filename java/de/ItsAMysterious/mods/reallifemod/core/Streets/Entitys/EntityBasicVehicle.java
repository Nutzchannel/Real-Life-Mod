package de.ItsAMysterious.mods.reallifemod.core.Streets.Entitys;

import org.lwjgl.input.Keyboard;

import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityBasicVehicle extends Entity{
	public double speed;
	protected double mFzg;
	protected double mZu=0;
	public float deltaSteer;
	public double wheelRotation;
	protected float vehicleLength;
	protected double width,height;
	protected boolean isEmpty;
	protected boolean shoulddrive;
	protected float R;
	protected float k;
	public double Damage;
	protected static final double RollWKoef=0.066;
	protected static final double g=9.81;//9.81 ms /20;
	
	public EntityBasicVehicle(World world) {
		super(world);
		this.preventEntitySpawning=true;
		this.ignoreFrustumCheck=true;
		this.isEmpty=true;
		this.Damage=0;
		this.shoulddrive=true;
	}

	public EntityBasicVehicle(World world, double x, double y, double z,
			float TheVehicleLength, float mass) {
		this(world);
		setPosition(x, y, z);
		setRotation(0.0F, 0.0F);
		mFzg=mass;
		vehicleLength=TheVehicleLength;
		prevPosX=x;
		prevPosY=y;
		prevPosZ=z;
		motionX=0.0D;
		motionY=0.0D;
		motionZ=0.0D;
	}

	@Override
	protected void entityInit() {
        dataWatcher.addObject(17, new Integer(0));
        dataWatcher.addObject(18, new Integer(1));
        dataWatcher.addObject(19, new Float(0.0F));
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
	public void onUpdate() {
		super.onUpdate();
		//Damage stuff
        if (this.getTimeSinceHit() > 0)
        {
            this.setTimeSinceHit(this.getTimeSinceHit() - 1);
        }

        if (this.getDamageTaken() > 0.0F)
        {
            this.setDamageTaken(this.getDamageTaken() - 1.0F);
        }
        //Y-Movement
		if(!(this.worldObj.isRemote && (this.worldObj.blockExists((int)(this.posX),(0), (int)(this.posZ)) || !this.worldObj.getChunkFromBlockCoords((int)this.posX, (int)this.posZ).isChunkLoaded))){
 			this.motionY=0.0;
 		}else
        {
 			if(this.worldObj.blockExists((int)(this.posX),(1), (int)(this.posZ))){
 				this.motionY+=0.1D;
 			}
	        if (this.posY > 0.0D)
	        {
	            this.motionY = -0.1D;
	        }
	        else
	        {
	            this.motionY = 0.0D;
	        }
     		this.motionY *= 0.9800000190734863D;
        }
		
		//Movement
		if(this.riddenByEntity!=null&&this.riddenByEntity instanceof EntityLivingBase){
			if(Keyboard.isKeyDown(Keyboard.KEY_W)){
				if(speed<1.0D){
					speed+=0.05D;
				}
				if(speed>1.0D){
					speed=1.0D;
				}
			}
			
			if(Keyboard.isKeyDown(Keyboard.KEY_S)){
				if(speed>-0.25D){
					speed-= 0.025D;
				}
				if(speed<-0.25D){
					speed=-0.25D;
				}
			}
			
			if(Keyboard.isKeyDown(Keyboard.KEY_A)){
				if(deltaSteer<45.0D){
					deltaSteer++;
				}
			}else
				if(deltaSteer>0){
					deltaSteer--;
				}
			
			if(Keyboard.isKeyDown(Keyboard.KEY_D)){
				if(deltaSteer>-45.0D){
					deltaSteer--;
				}
			}else
				if(deltaSteer<0){
					deltaSteer++;
				}
		}
		//Lenkausgleich


		//Radius of steeringcircle
		if(deltaSteer!=0){
			R=vehicleLength/deltaSteer;
			k=1/R;
		}else{
			k=0.0F;
		}
		this.rotationYaw+=(float)(k*this.speed);
		if(isCollidedHorizontally){
			this.rotationYaw+=(float)this.speed;
			this.posX-=this.motionX;
			this.posZ-=this.motionZ;
		}
		this.setRotation(this.rotationYaw, this.rotationPitch);
		//Bewegungsgleichung
		motionX=Math.sin((rotationYaw)*Math.PI/180)*speed;
		motionZ=Math.cos((rotationYaw)*Math.PI/180)*speed;
		this.speed-=FLuft()+FSteig();

		moveEntity(motionX, motionY, motionZ);
		
	}
	

	@Override
	    public boolean canBeCollidedWith()
	    {
	        return !this.isDead;
	    }
	    
	    @Override
		public boolean interactFirst(EntityPlayer player)
	    {
	        if (this.riddenByEntity != null && this.riddenByEntity instanceof EntityPlayer && this.riddenByEntity != player)
	        {
	            return true;
	        }
	        else
	        {
	            if (!this.worldObj.isRemote)
	            {
	            	player.mountEntity(this);
	            	this.isEmpty=false;
	               //FMLClientHandler.instance().getClient().displayGuiScreen(new GuiVehicle(this));
	                return true;
	            }else
	                if(!this.shoulddrive==true){
	                	player.addChatComponentMessage(new ChatComponentTranslation("This vehicle is broken! It must be repaired!"));
	                    return false;
	                }else
	                	return true;
	        }
	    }
	    
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

	                    for (l = 0; l < 3; ++l)
	                    {
	                    }

	                    for (l = 0; l < 2; ++l)
	                    {
	                    }
	                }

	                this.fallDistance = 0.0F;
	            }
	        }
	        else if (this.worldObj.getBlock(i, j - 1, k).getMaterial() != Material.water && p_70064_1_ < 0.0D)
	        {
	            this.fallDistance = (float)(this.fallDistance - p_70064_1_);
	        }
	    }
		
	    public void setDamageTaken(float p_70266_1_)
	    {
	        this.dataWatcher.updateObject(19, Float.valueOf(p_70266_1_));
	    }

	    /**
	     * Gets the damage taken from the last hit.
	     */
	    public float getDamageTaken()
	    {
	        return this.dataWatcher.getWatchableObjectFloat(19);
	    }

	    /**
	     * Sets the time to count down from since the last time entity was hit.
	     */
	    public void setTimeSinceHit(int p_70265_1_)
	    {
	        this.dataWatcher.updateObject(17, Integer.valueOf(p_70265_1_));
	    }

	    /**
	     * Gets the time since the last hit.
	     */
	    public int getTimeSinceHit()
	    {
	        return this.dataWatcher.getWatchableObjectInt(17);
	    }

	    /**
	     * Sets the forward direction of the entity.
	     */
	    public void setForwardDirection(int p_70269_1_)
	    {
	        this.dataWatcher.updateObject(18, Integer.valueOf(p_70269_1_));
	    }

	    /**
	     * Gets the forward direction of the entity.
	     */
	    public int getForwardDirection()
	    {
	        return this.dataWatcher.getWatchableObjectInt(18);
	    }
	
	@Override
	protected void readEntityFromNBT(NBTTagCompound p_70037_1_) {	
	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound p_70014_1_) {
	}
		
	
	protected double FLuft(){
		return (1.44*(speed*speed));//(Luftdichte/2)*Strömungswiderstand*Flaeche*Geschwindigkeit²
	}
	
	protected double FRoll(){
		return (mFzg+mZu)*g*RollWKoef*Math.cos(rotationPitch);//Steigung in Bogenmaß
	}
	//Steigungswiderstand
	protected double FSteig(){
		return (mFzg+mZu)*g*Math.sin(rotationPitch);
	}
	
	protected double FB(){
		return (mF()*(mFzg+mZu))*aG();
	}
	
	protected double mF(){
		return FT();
	}
	
	protected double FT(){
		return -(mFzg+mZu)*aG();
	}
	
	protected double FWGes(){
		return FLuft()+FRoll();//+FSteig()+FB());
	}
	protected double aG(){
		return Math.sqrt((motionX*motionX)+(motionZ*motionZ))*20;
	}

	protected double getSpeed() {
		return this.speed;
	}

	protected void setSpeed(double speed) {
		this.speed = speed;
	}
}
