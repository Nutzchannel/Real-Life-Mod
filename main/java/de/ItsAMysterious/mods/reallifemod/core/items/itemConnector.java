package de.ItsAMysterious.mods.reallifemod.core.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class itemConnector extends ExtendedItem {
	public itemConnector(){
		this.setUnlocalizedName("trailerconnector");
	}
	
    @Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer p_77659_3_)
    {
        return stack;
    }

}
