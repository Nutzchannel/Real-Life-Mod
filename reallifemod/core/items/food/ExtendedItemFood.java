package de.ItsAMysterious.mods.reallifemod.core.items.food;

import de.ItsAMysterious.mods.reallifemod.api.interfaces.Buyable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ExtendedItemFood extends ItemFood implements Buyable{
	public ExtendedItemFood(int p_i45340_1_, boolean p_i45340_2_) {
		super(p_i45340_1_, p_i45340_2_);
	}

	public ExtendedItemFood(int p_i45340_1_, float f1,boolean p_i45340_2_) {
		super(p_i45340_1_,f1, p_i45340_2_);
	}

	private double price;
	
	public void setPrice(double thePrice){
		this.price=thePrice;
	}
	@Override
	public double price() {
		return price;
	}

	@Override
	public int percentage() {
		return 0;
	}

    public ItemStack onEaten(ItemStack p_77654_1_, World p_77654_2_, EntityPlayer p_77654_3_)
    {
        --p_77654_1_.stackSize;
        p_77654_3_.getFoodStats().func_151686_a(this, p_77654_1_);
        p_77654_2_.playSoundAtEntity(p_77654_3_, "random.burp", 0.5F, p_77654_2_.rand.nextFloat() * 0.1F + 0.9F);
        this.onFoodEaten(p_77654_1_, p_77654_2_, p_77654_3_);
        return p_77654_1_;
    }

}
