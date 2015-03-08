package de.ItsAMysterious.mods.reallifemod.core.entitys.seasonalstuff;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class entitySanta extends EntityLivingBase{

	public entitySanta(World p_i1594_1_) {
		super(p_i1594_1_);
	}
	
	public entitySanta(World world,double x, double y, double z) {
		this(world);
		this.setPosition(x, y, z);
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
		
	}

	@Override
	public ItemStack[] getLastActiveItems() {
		return null;
	}

}
