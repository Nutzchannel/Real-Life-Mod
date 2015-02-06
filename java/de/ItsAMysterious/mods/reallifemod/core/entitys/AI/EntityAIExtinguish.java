package de.ItsAMysterious.mods.reallifemod.core.entitys.AI;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

public class EntityAIExtinguish extends EntityAIBase{
	public int interactionradius;
	public Entity theEntity;
	public World theWorld;
	public Block currentBlock;
	
	public EntityAIExtinguish(Entity entity, int blockRadius) {
		this.theEntity=entity;
		this.theWorld=entity.worldObj;
		this.interactionradius=blockRadius;
	}
	
	@Override
	public boolean shouldExecute() {
		if(this.theWorld.getBlock((int)this.theEntity.posX,(int) this.theEntity.posY-1,(int) this.theEntity.posZ)==Blocks.fire)
		return true;
		else return false;
	}
	
    @Override
	public boolean continueExecuting()
    {
        return this.shouldExecute();
    }

    /**
     * Determine if this AI Task is interruptible by a higher (= lower value) priority task.
     */
    @Override
	public boolean isInterruptible()
    {
        return false;
    }

    /**
     * Execute a one shot task or start executing a continuous task
     */
    @Override
	public void startExecuting() {
    	
    	this.theWorld.setBlock((int) this.theEntity.posZ,(int) this.theEntity.posX+1,(int) this.theEntity.posY,Blocks.water);
    }

    /**
     * Resets the task
     */
    @Override
	public void resetTask() {}

    /**
     * Updates the task
     */
    @Override
	public void updateTask() {}


}
