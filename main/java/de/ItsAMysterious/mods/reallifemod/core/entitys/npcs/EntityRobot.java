package de.ItsAMysterious.mods.reallifemod.core.entitys.npcs;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.INpc;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIOpenDoor;
import net.minecraft.entity.ai.EntityAIWatchClosest2;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import de.ItsAMysterious.mods.reallifemod.api.handlers.SpeechHandler;
import de.ItsAMysterious.mods.reallifemod.core.entitys.ai.EntityAIExtinguish;
import de.ItsAMysterious.mods.reallifemod.core.gui.guiHelperBot;

public class EntityRobot extends EntityLiving implements INpc{
	private SpeechHandler handler;
	public boolean firstClick=true;

	public EntityRobot(World world) {
		super(world);
		this.handler=new SpeechHandler();
        this.setSize(0.6F, 1.8F);
        this.getNavigator().setEnterDoors(true);
        this.getNavigator().setAvoidsWater(true);
        this.getNavigator().setSpeed(10.0F);
        this.setCustomNameTag("Helperbot Zanor");
        this.ignoreFrustumCheck=false;

	}
	
	public EntityRobot(World world,double d, double e,double f) {
		super(world);
		this.setPosition(d, e, f);
		this.setHealth(19);
        this.tasks.addTask(3, new EntityAIOpenDoor(this, true));
        this.tasks.addTask(5, new EntityAIWatchClosest2(this, EntityPlayer.class, 3.0F, 1.0F));
        this.tasks.addTask(8, new EntityAIExtinguish(this, 10));
        this.tasks.addTask(9, new EntityAILookIdle(this));
        this.ignoreFrustumCheck=false;
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
    	super.writeEntityToNBT(tag);
        NBTTagCompound comp=new NBTTagCompound();
        tag.setTag("RoboProps", tag);
		comp.setBoolean("FirstClick", this.firstClick);
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    @Override
	public void readEntityFromNBT(NBTTagCompound tag)
    {
    	super.readEntityFromNBT(tag);
        NBTTagCompound comp=tag.getCompoundTag("RoboProps");
		this.firstClick=comp.getBoolean("FirstClick");
    }

    @Override
     public void setCustomNameTag(String thename) {
    	thename="Zanor";
    };
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
    	if(!player.worldObj.isRemote&&this.firstClick==true){
    			this.handler.speechSynth(	1F, 7.0F, 16.0F, 0, "HI! I am your new Intelibot, just call me 'Zanor'! I can help you with everything in the Real Life Mod! You just have to right click on me.");
    			this.firstClick=false;
    			System.out.println("Firstclick:"+this.firstClick);
    			}else
    				if(!player.worldObj.isRemote&&this.firstClick==false){
    			Minecraft.getMinecraft().displayGuiScreen(new guiHelperBot());
    				}
    	//player.addChatMessage(new ChatComponentText(this.currentLine()));
        return true;
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

}
