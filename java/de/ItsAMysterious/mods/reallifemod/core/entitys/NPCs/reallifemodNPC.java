package de.ItsAMysterious.mods.reallifemod.core.entitys.npcs;

import net.minecraft.block.BlockFire;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.INpc;
import net.minecraft.entity.ai.EntityAIAvoidEntity;
import net.minecraft.entity.ai.EntityAIMoveIndoors;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAIOpenDoor;
import net.minecraft.entity.ai.EntityAIRestrictOpenDoor;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.ai.EntityAIWatchClosest2;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import de.ItsAMysterious.mods.reallifemod.api.handlers.SpeechHandler;
import de.ItsAMysterious.mods.reallifemod.core.entitys.ai.EntityAIExtinguish;

public class ReallifemodNPC extends EntityAgeable implements INpc{
	public enum Job{
		NOJOB,POLICEMEN,FIREMEN, MINOR;
	}
	public int jobIndex=0;
	public Job currentJob=Job.NOJOB;
	private SpeechHandler handler;

	public ReallifemodNPC(World world) {
		super(world);
		this.handler=new SpeechHandler();
        this.setSize(0.6F, 1.8F);
        this.getNavigator().setBreakDoors(true);
        this.getNavigator().setAvoidsWater(true);
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.ignoreFrustumCheck=false;

	}
	
	public ReallifemodNPC(World world,double d, double e,double f) {
		super(world);
		this.setPosition(d, e, f);
		this.setHealth(10);
        this.tasks.addTask(1, new EntityAIAvoidEntity(this, EntityZombie.class, 8.0F, 0.6D, 0.6D));
        this.tasks.addTask(2, new EntityAIMoveIndoors(this));
        this.tasks.addTask(3, new EntityAIRestrictOpenDoor(this));
        this.tasks.addTask(4, new EntityAIOpenDoor(this, true));
        this.tasks.addTask(5, new EntityAIMoveTowardsRestriction(this, 1.0D));
        this.tasks.addTask(9, new EntityAIWatchClosest2(this, EntityPlayer.class, 3.0F, 1.0F));
        this.tasks.addTask(9, new EntityAIWander(this, 1.5D));
        this.tasks.addTask(10, new EntityAIWatchClosest(this, BlockFire.class, 8.0F));
        this.tasks.addTask(11, new EntityAIExtinguish(this, 10));
        this.ignoreFrustumCheck=false;
	}
	
	public ReallifemodNPC(World world,double d, double e,double f, Job thejob) {
		this(world, d, e, f);
		this.setJob(thejob);
		this.isImmuneToFire=true;
	}
	
    private void setJob(Job thejob) {
    	this.currentJob=thejob;
	}

	@Override
	protected void entityInit()
    {
    	super.entityInit();
        this.dataWatcher.addObject(17, new Integer(0));
        this.dataWatcher.addObject(18, new Integer(1));
        this.dataWatcher.addObject(19, new Float(0.0F));
    }
	
    @Override
	public void writeEntityToNBT(NBTTagCompound tag)
    {
        NBTTagCompound comp=new NBTTagCompound();
		comp.setInteger("Age", this.getGrowingAge());
		tag.setTag("reallifemodNPC", comp);
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    @Override
	public void readEntityFromNBT(NBTTagCompound tag)
    {
		this.setGrowingAge(tag.getInteger("Age"));
    }

    @Override
	protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
    }
    
    @Override
    public boolean attackEntityFrom(DamageSource p_70097_1_, float p_70097_2_)
    {
    	return false;
    }

    
	@Override
	public ItemStack getHeldItem() {
		return null;
	}

	@Override
	public ItemStack getEquipmentInSlot(int p_71124_1_) {
		return null;
	}

	@Override
	public void setCurrentItemOrArmor(int p_70062_1_, ItemStack p_70062_2_) {
		super.setCurrentItemOrArmor(p_70062_1_, p_70062_2_);
	}

	@Override
	public ItemStack[] getLastActiveItems() {
		return null;
	}
	
    @Override
	public boolean interact(EntityPlayer player)
    {
    	if(this.jobIndex<3){
    		jobIndex++;
    	}else
    	if(this.jobIndex==3){
    		this.jobIndex=0;
    	};
    	switch(this.jobIndex){
    	case 0:
    		this.setJob(Job.NOJOB);
    		break;
    	case 1:
    		this.setJob(Job.FIREMEN);
    		break;

    	case 2:
    		this.setJob(Job.MINOR);
    		break;
    	case 3:
    		this.setJob(Job.POLICEMEN);
    		break;
    	}
    	if(!player.worldObj.isRemote){
    		if(this.currentJob!=Job.NOJOB){
    			this.handler.speechSynth(	10.0F, 0.0F, 5.0F, 10, "Hello, my name is Steve and i am a "+this.currentLine()+".");}else
    			{
        			this.handler.speechSynth(	10.0F, 0.0F, 5.0F, 10, "Hello, my name is Steve"
        					+ " and i don't have a Job!I'm a very poor man!");

    			}
    	//SpeechHandler.speechSynth(1,4.5F, 5, 35, "How are you?");
    	player.addChatComponentMessage(new ChatComponentText("Hello, my name is Steve and i am a "+this.currentLine()+"."));
    }
    	//player.addChatMessage(new ChatComponentText(this.currentLine()));
        return true;
    }

	private String currentLine() {
		return this.currentJob.name().toLowerCase();
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

	@Override
	public EntityAgeable createChild(EntityAgeable entity) {
		return entity;
	}

}
