package de.ItsAMysterious.mods.reallifemod.core.items.food;

import de.ItsAMysterious.mods.reallifemod.core.items.ExtendedItemFood;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class itemCracker extends ExtendedItemFood{
	private boolean alwaysEdible;
	
	public itemCracker(int HealAmmount, float saturationModifier, boolean isWolfsFavoriteMeat) {
		super(HealAmmount, saturationModifier, isWolfsFavoriteMeat);
		setUnlocalizedName("cracker");
		setTextureName("reallifemod:itemCracker");
		this.setPrice(0.5F);
		}

	  
	    @Override
		public EnumAction getItemUseAction(ItemStack par1ItemStack)
	    {
	        return EnumAction.eat;
	    }
	    

	    @Override
		public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
	    {
	        if (par3EntityPlayer.canEat(this.alwaysEdible))
	        {
	            par3EntityPlayer.setItemInUse(par1ItemStack, this.getMaxItemUseDuration(par1ItemStack));
	        }

	        return par1ItemStack;
	    }

	    
	    

}
