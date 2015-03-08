package de.ItsAMysterious.mods.reallifemod.core.items;

import org.lwjgl.input.Mouse;

import de.ItsAMysterious.mods.reallifemod.core.entitys.bullets.EntityBullet;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class AK47Item extends ExtendedItem {
	public boolean aiming=false;
	private int shootcounter=0;
	private int ammo=30;
	
	public AK47Item(){
	    setFull3D();
		setTextureName("reallifemod:AK47");
	    setUnlocalizedName("AK47");
	    this.setPrice(1700);
	}
	
	@Override
	public boolean canItemEditBlocks(){
		return false;
	}
    @Override
	public EnumAction getItemUseAction(ItemStack p_77661_1_)
    {
        return EnumAction.none;
    }
    
    @Override
	public void onUpdate(ItemStack stack, World world, Entity player, int p_77663_4_, boolean p_77663_5_) {
    	EntityPlayer p=(EntityPlayer)player;
    	if(p.getCurrentEquippedItem()== stack){
    		if(Mouse.isButtonDown(1)){
    			this.aiming=true;
    		}else
    		{
    			this.aiming=false;
    		}
    	
    		if(Mouse.isButtonDown(0)){
    			this.shoot(player, world);
    		}
    	}else
    	{
    		this.aiming=false;
    	}

    }

	public void shoot(Entity player, World world) {
		double x=player.posX+Math.sin(player.getRotationYawHead())*1.0F;
		double z=player.posX+Math.cos(player.getRotationYawHead())*1.0F;
		if(this.shootcounter>1)
			this.shootcounter=0;
		if(this.shootcounter==0&&ammo>0){
			player.playSound("reallifemod:AK47_Shoot", 1.0F, 1.0F);
			world.spawnEntityInWorld(new EntityBullet(world, (EntityLivingBase) player, 10));
			//this.ammo--;
		}else
		{
			//player.playSound("", 1.0F, 1.0F);
		}
		this.shootcounter++;

	}

}
