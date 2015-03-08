package de.ItsAMysterious.mods.reallifemod.core.items.food;

import de.ItsAMysterious.mods.reallifemod.api.entity.properties.RealLifeProperties;
import de.ItsAMysterious.mods.reallifemod.api.entity.properties.RealLifeProperties;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemSalt extends ExtendedItemFood{
	
	private boolean alwaysEdible;
	
	public ItemSalt(int HealAmount, float saturationModifier, boolean isWolfsFavoriteMeat) {
		super(HealAmount, saturationModifier, isWolfsFavoriteMeat);
		setUnlocalizedName("salt");
		setTextureName("reallifemod:salt");
		this.alwaysEdible=true;
		}

		@Override
		public EnumAction getItemUseAction(ItemStack par1ItemStack)
	    {
	        return EnumAction.eat;
	    }
	    

	    /**
	     * Called whenever this item is equipped and the right mouse button is pressed. Args: itemStack, world, entityPlayer
	     */
	    @Override
		public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
	    {
	        if (par3EntityPlayer.canEat(this.alwaysEdible))
	        {
	            par3EntityPlayer.setItemInUse(par1ItemStack, this.getMaxItemUseDuration(par1ItemStack));
	        }
	        
			EntityPlayer p=Minecraft.getMinecraft().thePlayer;
			RealLifeProperties props=(RealLifeProperties) p.getExtendedProperties("RealLifeProps");
			if(RealLifeProperties.thirst+20<100){
			RealLifeProperties.thirst+=20;
			}else
				RealLifeProperties.thirst=100;
			return par1ItemStack;
	    }


}
