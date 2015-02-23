package de.ItsAMysterious.mods.reallifemod.core.streets.entitys;

import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import org.lwjgl.input.Keyboard;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import de.ItsAMysterious.mods.reallifemod.RealLifeMod;

public class EntityTruck extends Entity 
{

	private double acceleration;
	private boolean isEmpty;
	private double speedMultiplier;
	private int posRotationIncrements;
	private double truckX;
	private double truckY;
	private double truckZ;
	private double truckYaw;
	private double truckPitch;
	public float truckRoll;
	private float velocityX;
	private float velocityY;
	private float velocityZ;
	public float DeltaSteer;
	public double velocity;
	public double wheelRotation;
	protected double delta1,delta2;
	protected double k,R;
	public double Damage;
	public float FuelAmount=100.0F;
	private double burning;
	public boolean damaged;
	public boolean isBraking;
	private float ccm=1.0F;
	private float m=15500F;
	private float radstand=3600;
	private float PS=0;
	private double AFront=10.2;

	public EntityTruck(World world) {
		super(world);
        this.isEmpty = true;
        this.speedMultiplier = 0.07D;
        this.preventEntitySpawning = true;
        this.setSize(3.0F, 1.0F);
        this.yOffset = 0.0F;
        this.ignoreFrustumCheck=true;
	}
	
    protected boolean canTriggerWalking()
    {
        return false;
    }
    
    protected void entityInit()
    {
        this.dataWatcher.addObject(17, new Integer(0));
        this.dataWatcher.addObject(18, new Integer(1));
        this.dataWatcher.addObject(19, new Float(0.0F));
    }
    
    /**
     * Returns a boundingBox used to collide the entity with other entities and blocks. This enables the entity to be
     * pushable on contact, like boats or minecarts.
     */
    public AxisAlignedBB getCollisionBox(Entity entity)
    {
        return entity.boundingBox.getBoundingBox(-1, 0, 0, 1, 1, 7);
    }
    

    /**
     * returns the bounding box for this entity
     */
    public AxisAlignedBB getBoundingBox()
    {
        return this.boundingBox;
    }

    public boolean canBePushed()
    {
        return false;
    }
    
	public EntityTruck(World world,double posX,double posY,double posZ) {
        super(world);
        this.motionX = 0.0D;
        this.motionY = 0.0D;
        this.motionZ = 0.0D;
        this.prevPosX = posX;
        this.prevPosY = posY;
        this.prevPosZ = posZ;
        this.setPosition(posX, posY, posZ);
	}
    /**
     * Returns the Y offset from the entity's position for any entity riding this one.
     */
    public double getMountedYOffset()
    {
        return this.height+2-0F;
    }
    
    /**
     * Called when the entity is attacked.
     */
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

                if (!flag)
                {
                    this.func_145778_a(Items.boat, 1, 0.0F);
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

    /**
     * Setups the entity to do the hurt animation. Only used by packets in multiplayer.
     */
    @Override
    @SideOnly(Side.CLIENT)
    public void performHurtAnimation()
    {
        this.setForwardDirection(-this.getForwardDirection());
        this.setTimeSinceHit(10);
        this.setDamageTaken(this.getDamageTaken() * 11.0F);
    }
    
    /**
     * Returns true if other Entities should be prevented from moving through this Entity.
     */
    @Override
    public boolean canBeCollidedWith()
    {
        return !this.isDead;
    }
    
    
    /**
     * Sets the position and rotation. Only difference from the other one is no bounding on the rotation. Args: posX,
     * posY, posZ, yaw, pitch
     */
    @SideOnly(Side.CLIENT)
    public void setPositionAndRotation2(double p_70056_1_, double p_70056_3_, double p_70056_5_, float p_70056_7_, float p_70056_8_, int p_70056_9_)
    {
        this.truckX = p_70056_1_;
        this.truckY = p_70056_3_;
        this.truckZ = p_70056_5_;
        this.truckYaw = (double)p_70056_7_;
        this.truckPitch = (double)p_70056_8_;
        this.motionX = this.velocityX;
        this.motionY = this.velocityY;
        this.motionZ = this.velocityZ;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void setVelocity(double p_70016_1_, double p_70016_3_, double p_70016_5_)
    {
        this.velocityX = (float) (this.motionX = p_70016_1_);
        this.velocityY = (float) (this.motionY = p_70016_3_);
        this.velocityZ = (float) (this.motionZ = p_70016_5_);
    }
    
    public void onUpdate()
    {    
        super.onUpdate();

        if (this.getTimeSinceHit() > 0)
        {
            this.setTimeSinceHit(this.getTimeSinceHit() - 1);
        }

        if (this.getDamageTaken() > 0.0F)
        {
            this.setDamageTaken(this.getDamageTaken() - 1.0F);
        }
        this.prevPosX = this.posX;
        this.prevPosY = this.posY;
        this.prevPosZ = this.posZ;
        
        if(this.riddenByEntity!=null && this.riddenByEntity instanceof EntityLivingBase){
        	
        	if(Keyboard.isKeyDown(Keyboard.KEY_W))
			{
        		if(this.ccm<1.5){
        			this.ccm+=0.05F;
        		}else
        		{
        			if(this.ccm==1.5){
        				this.ccm-=0.05F;
        			}
        		}
        		if(this.velocity<1.0F)
        		this.velocity+=0.025F;else
        			if(this.velocity>1.0F)
        				this.velocity=1.0F;
	        	this.worldObj.playSoundEffect(this.posX, this.posY,this.posZ,"reallifemod:diesel",1.0F, this.ccm);
		    	this.PS+=1.0F;
			}else
			{
	        	this.worldObj.playSoundEffect(this.posX, this.posY,this.posZ,"reallifemod:diesel",1.0F, this.ccm);
				if(this.ccm>1){
					this.ccm-=0.01F;
				}
				this.PS=0;
			}
        	
        	if(Keyboard.isKeyDown(Keyboard.KEY_S))
			{
        		this.velocity-=0.025F;
        		this.isBraking=true;
        		if(this.ticksExisted%20==5){
    				this.playSound("reallifemod:reversebeep", 1.0F, 1.0F);
        		}
			}
        	
			if(Keyboard.isKeyDown(Keyboard.KEY_A)){
				if(this.DeltaSteer<45)
				{
					this.DeltaSteer++;
				}

			}
			else
			{
				if(this.DeltaSteer>0){
					this.DeltaSteer--;
				}
			}

			
			if(Keyboard.isKeyDown(Keyboard.KEY_D))
			{

				if(this.DeltaSteer>-45)
				{
				this.DeltaSteer--;
				}
			}
			else
			{
				if(this.DeltaSteer<0){
					this.DeltaSteer++;
				}
			}
		
		if(Keyboard.isKeyDown(Keyboard.KEY_RIGHT))
		{
			if(this.truckRoll<90)
			{
			this.truckRoll++;
			}
		}
		else
		{
			if(this.truckRoll>0)
			{
				this.truckRoll--;
			}
		}
		
		if(Keyboard.isKeyDown(Keyboard.KEY_LEFT))
		{
			if(this.truckRoll>-90)
			{
			this.truckRoll--;
			}
		}
		else
		{
			if(this.truckRoll<0)
			{
				this.truckRoll++;
			}
		}
		
		if(Keyboard.isKeyDown(Keyboard.KEY_SPACE)){
			this.brake();
		}
		
        if(Keyboard.isKeyDown(RealLifeMod.Key_Horn.getKeyCode())){
        	if(this.ticksExisted%20==2){
        		this.playSound("tlm:horn",1.0F,1.0F);
        	}
         }
        if(this.FuelAmount!=0){
	        if(this.ticksExisted%20==1){
	        }
        }
        else{
        	if(this.ticksExisted%20==1){
        		this.worldObj.playSoundEffect(this.posX, this.posY,this.posZ,"diesel_stall", 1.0F, 1.0F);
        	}
        }
        }

		if(Keyboard.isKeyDown(Keyboard.KEY_RETURN)){
			if(this.worldObj.getClosestPlayer(posX, posY, posZ, 5.0F)!=null){
				EntityPlayer entity=(EntityPlayer)this.worldObj.getClosestPlayer(posX, posY, posZ, 5.0F);
				this.interactFirst(entity);
				this.riddenByEntity=(EntityPlayer)entity;
				this.playSound("tlm:door_close", 1.0F, 1.0F);
				if(this.riddenByEntity!=null)
				{
						entity.dismountEntity(this);
						this.setIsEmpty(true);
						this.worldObj.playSoundEffect(this.posX, this.posY,this.posZ,"tlm:door_close", 1.0F, 1.0F);
				}
			}
		}
        
        this.boundingBox.calculateXOffset(this.boundingBox, this.motionX);
        this.boundingBox.calculateYOffset(this.boundingBox, this.motionY);
        this.boundingBox.calculateZOffset(this.boundingBox, this.motionZ);

        double d2;
        double d4;
        int j;
       
        double d11;
        double d12;
        

        double sqrtMotion=Math.sqrt(this.motionX*this.motionX+this.motionZ*this.motionZ);
        if (this.riddenByEntity != null && this.riddenByEntity.isDead)
        {
            this.riddenByEntity = null;
        }
        
        if(this.isDead){
            FMLClientHandler.instance().getClient().displayGuiScreen(null);
        }
        
        //Movement
        if(this.DeltaSteer!=0){
        	this.R=7/this.DeltaSteer;
        	this.k=1/this.R;
     	}
        
        if(this.Damage==100){
        	this.setFire(10);
        	this.Damage=0.0F;
        	this.damaged=true;
        	this.worldObj.createExplosion(this, this.posX, this.posY, this.posZ, 10.0F, false);
        }else
        if (this.isCollidedHorizontally || this.isCollidedVertically)
        {
            	this.calculateDamage(sqrtMotion);
        }
        //this.acceleration=this.FV()/this.mRed();
		this.wheelRotation+=this.velocity*20;
		//double angleVelocity=this.velocity/R;
		if(this.velocity>0.006)
			this.velocity-=0.006F;
		else
		if(this.velocity<-0.006)
			this.velocity+=0.006F;
		else 
			this.velocity=0;
		double f=this.velocity*this.k;
		this.rotationYaw+=MathHelper.wrapAngleTo180_double(f);
        this.motionX=(float) (Math.sin(this.rotationYaw*((float)Math.PI / 180)))*this.velocity;
        this.motionZ=(float) (Math.cos(this.rotationYaw*((float)Math.PI / 180)))*this.velocity;
        this.FuelAmount-=sqrtMotion;
        this.moveEntity(this.motionX, this.motionY, this.motionZ);
    	double z=Math.cos((this.rotationYaw)*Math.PI /180.0)*4.0;
    	double x=Math.sin((this.rotationYaw)*Math.PI/180)*4.0;
		if(!(this.worldObj.isRemote && (this.worldObj.blockExists((int)(this.posX+x),(int)(0), (int)(this.posZ+z)) || !this.worldObj.getChunkFromBlockCoords((int)this.posX, (int)this.posZ).isChunkLoaded))){
 			this.motionY=0.0;
 		}else
        {
 			if(this.worldObj.blockExists((int)(this.posX+x),(int)(1), (int)(this.posZ+z))){
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
		
			if(this.worldObj.getBlock((int)(this.posX+x),(int)(this.posY-Math.tan(this.rotationPitch*Math.PI/180)*6),(int)(this.posZ+z))==Blocks.air){
				this.rotationPitch+=1.0F;
				this.setRotation(this.rotationYaw, this.rotationPitch);
			}
			
			{
			if(this.worldObj.getBlock((int)(this.posX+x),(int)( posY-Math.tan(this.rotationPitch*Math.PI/180)*6),(int)(this.posZ+z))!=Blocks.air){
				if(this.rotationPitch>0){
					this.rotationPitch-=1.0F;
				}else
				{
					if(this.rotationPitch<=0)
						this.rotationPitch=0;}
			}
			}
    }
    
    private void calculateDamage(double speed) {
    	this.Damage+=speed;
	}

	public void brake(){
    	if(this.velocity>0){
    		this.velocity-=0.012;
    	}else
    	if(this.velocity<0)
    		this.velocity+=0.012F;
    }
    
    public void roll(){
    	this.velocity-=this.FR();
    }
    
    public double FN(float FG){
    	return Math.cos(this.rotationPitch)*FG;
    }
    
    public double FT(float FG){
    	return Math.sin(this.rotationPitch)*FG;
    }
    
    public double FR(){
    	return 0.006F *this.FN((-9.81F*this.m));
    }
    
    public double FA(){
    	return (AFront/2 * CW() * 1.29 * velocity);
    }
    
    public double CW(){
    	return 0.29/((this.velocity*20)*10.2);
    }
    
    public double traegheitsmomQuad(){
    	return 1/12*m*(12000*400);
    }
    
    public double mRed(){
    	return traegheitsmomQuad()/(4*0.3)*(4*0.3);
    }
    
    public double FV(){
    	return this.FP()-(((this.velocity*this.velocity))+-9.81*m);
    }
    
    public double FP(){
    	return this.PS;
    }
    
    @Override
	public void updateRiderPosition(){
    	double z=Math.cos((this.rotationYaw+16)*Math.PI /180.0)*2.2;//*5.0990195135927848300282241090228D;
    	double y=posY+Math.tan(rotationPitch*Math.PI/180)*3;
    	double x=Math.sin((this.rotationYaw+16)*Math.PI/180)*2.2;
    	this.riddenByEntity.setPosition(this.posX+x, this.posY+1.6, this.posZ+z);
    	this.riddenByEntity.rotationYaw-=this.velocity*this.k;
    	EntityPlayer p=(EntityPlayer)this.riddenByEntity;
    	p.rotationYawHead-=this.velocity*this.k;
	}
    
	@Override
	protected void readEntityFromNBT(NBTTagCompound tag) {
		//this.Damage=tag.getDouble("damage");
		//this.DeltaSteer=tag.getFloat("deltaSteer");
	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound tag) {
		//tag.setDouble("damage", this.Damage);
		//tag.setFloat("deltaSteer",this.DeltaSteer);
	}	  

    /**
     * First layer of player interaction
     */
    public boolean interactFirst(EntityPlayer player)
    {
        if (this.riddenByEntity != null && this.riddenByEntity instanceof EntityPlayer && this.riddenByEntity != player)
        {
            return true;
        }
        else
        {
            if (!this.worldObj.isRemote&&this.damaged!=true)
            {
            	player.mountEntity(this);
            	this.isEmpty=false;
              //  FMLClientHandler.instance().getClient().displayGuiScreen(new GuiVehicle(this));
            }
            if(this.damaged==true){
            	player.addChatComponentMessage(new ChatComponentTranslation("This vehicle is broken! It must be repaired!"));
            }
            return true;
        }
    }
    
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
            this.fallDistance = (float)((double)this.fallDistance - p_70064_1_);
        }
    }
	
    /**
     * Sets the damage taken from the last hit.
     */
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

    /**
     * true if no player in truck
     */
    @SideOnly(Side.CLIENT)
    public void setIsEmpty(boolean empty)
    {
        this.isEmpty = empty;
    }

	
    public double getDelta2() {
		return this.delta2;
	}
	
	public double getDelta1() {
		return this.delta1;
	}

	public double getWheelRotation() {
		return wheelRotation;
	}

	public void setWheelRotation(double wheelRotation) {
		this.wheelRotation = wheelRotation;
	}

	public float getDeltaSteer() {
		return this.DeltaSteer;
	}

	public void setDeltaSteer(float deltaSteer) {
		this.DeltaSteer = deltaSteer;
	}

	public void setTrailer(EntityTrailer entityTrailer) {
		this.worldObj.spawnEntityInWorld(entityTrailer);
		entityTrailer.setPosition(this.posX, this.posY, this.posZ);
		//this.trailer=entityTrailer;
	}

}