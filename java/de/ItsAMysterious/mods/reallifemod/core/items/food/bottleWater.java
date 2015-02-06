package de.ItsAMysterious.mods.reallifemod.core.items.food;

import de.ItsAMysterious.mods.reallifemod.TLM;
import de.ItsAMysterious.mods.reallifemod.TLMItems;
import de.ItsAMysterious.mods.reallifemod.api.entity.properties.RealLifeProps;
import de.ItsAMysterious.mods.reallifemod.core.items.ExtendedItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class bottleWater extends ExtendedItem{
	
	public bottleWater() {
		this.setUnlocalizedName("waterbottle");
		this.setTextureName("reallifemod:bottleFull");
		this.setCreativeTab(TLM.TLMFood);
		this.setMaxStackSize(1);
		this.setPrice(0.7F);
	}
	@Override
	public EnumAction getItemUseAction(ItemStack par1ItemStack)
    {
        return EnumAction.drink;
    }

    @Override
	public ItemStack onEaten(ItemStack par1ItemStack, World p_77654_2_, EntityPlayer par3EntityPlayer)
    {
    	if(par3EntityPlayer !=null&& !par3EntityPlayer.capabilities.isCreativeMode){
    		RealLifeProps props=(RealLifeProps) par3EntityPlayer.getExtendedProperties("RealLifeProps");
    		if(RealLifeProps.thirst-20>0){
    			RealLifeProps.thirst-=20;
    		}else
    		{
    			RealLifeProps.thirst=0;
    		}
        	par3EntityPlayer.inventory.setInventorySlotContents(par3EntityPlayer.inventory.currentItem, new ItemStack(TLMItems.emptybottle));
    		}
        return par1ItemStack;
    	
    }
    @Override
	public int getMaxItemUseDuration(ItemStack p_77626_1_)
    {
        return 32;
    }
    
    @Override
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World world, EntityPlayer par3EntityPlayer)
    {
		par3EntityPlayer.setItemInUse(par1ItemStack, this.getMaxItemUseDuration(par1ItemStack));
		return par1ItemStack;
    }
    
}
