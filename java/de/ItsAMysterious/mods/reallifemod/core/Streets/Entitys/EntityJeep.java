package de.ItsAMysterious.mods.reallifemod.core.Streets.Entitys;

import org.lwjgl.input.Keyboard;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class EntityJeep extends EntityBasicVehicle{

	public EntityJeep(World world) {
		super(world);
	}

	public EntityJeep(World world, double x	, double y, double z) {
		super(world,x,y,z, 4.0F, 1500F);
	}

	@Override
	protected void entityInit() {
        this.dataWatcher.addObject(17, new Integer(0));
        this.dataWatcher.addObject(18, new Integer(1));
        this.dataWatcher.addObject(19, new Float(0.0F));
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
				if(this.speed<1.0D){
					this.speed+=0.025D;
				}
				if(this.speed>1.0D){
					this.speed=1.0D;
				}
			}
			
			if(Keyboard.isKeyDown(Keyboard.KEY_S)){
				if(this.speed>-0.25D){
					this.speed-= 0.025D;
				}
				if(this.speed<-0.25D){
					this.speed=-0.25D;
				}
			}
			
			if(Keyboard.isKeyDown(Keyboard.KEY_A)){
				if(this.deltaSteer<45.0D){
					this.deltaSteer++;
				}
			}else
				if(this.deltaSteer>0){
					this.deltaSteer--;
				}
			
			if(Keyboard.isKeyDown(Keyboard.KEY_D)){
				if(this.deltaSteer>-45.0D){
					this.deltaSteer--;
				}
			}else
				if(this.deltaSteer<0){
					this.deltaSteer++;
				}
		}
		//Lenkausgleich


		//Radius of steeringcircle
		if(this.deltaSteer!=0){
			R=vehicleLength/this.deltaSteer;
			k=1/R;
		}else{
			k=0.0F;
		}
		this.rotationYaw+=(float)(k*this.speed);
		/*if(this.isCollidedHorizontally){
			this.rotationYaw+=(float)this.speed;
		}*/
		
		this.setRotation(this.rotationYaw, this.rotationPitch);
		//Bewegungsgleichung
		this.motionX=Math.sin((this.rotationYaw)*Math.PI/180)*this.speed;
		this.motionZ=Math.cos((this.rotationYaw)*Math.PI/180)*this.speed;
		this.speed-=this.FLuft()+this.FSteig();

		this.moveEntity(this.motionX, this.motionY, this.motionZ);
		
	}
	
	@Override
	protected void readEntityFromNBT(NBTTagCompound p_70037_1_) {
	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound p_70014_1_) {
	}
	
}