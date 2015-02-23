package de.ItsAMysterious.mods.reallifemod.core.items.meds;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import de.ItsAMysterious.mods.reallifemod.core.items.ExtendedItemFood;

public class itemMed extends ExtendedItemFood{

	public itemMed(int p_i45340_1_, boolean p_i45340_2_) {
		super(p_i45340_1_, p_i45340_2_);
	}
	
	public itemMed(int p_i45340_1_, float saturation, boolean p_i45340_2_) {
		super(p_i45340_1_, saturation,p_i45340_2_);
	}
	
	@Override
    protected void onFoodEaten(ItemStack stack, World world, EntityPlayer player)
    {
		if(!world.isRemote){
			player.heal(6.0F);
		}
    }

}
