package de.ItsAMysterious.mods.reallifemod.core.items;

import java.util.List;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;

public class ItemIdentityCard extends ExtendedItem{
	
	public ItemIdentityCard(){
		this.setUnlocalizedName("identitycard");
		this.setTextureName("reallifemod:itemIdentityCard");
		this.setPrice(5);
	}
	
	@Override
	public void onCreated(ItemStack itemStack, World world, EntityPlayer player) {
		if(itemStack.getItem().getClass() == this.getClass())
		{
			itemStack.stackTagCompound = new NBTTagCompound();
			itemStack.stackTagCompound.setString("owner", player.getExtendedProperties("name").toString());
		}
	    super.onCreated(itemStack, world, player);
	}
	
	@Override
	public void addInformation(ItemStack itemStack, EntityPlayer player,
            List list, boolean par4) {
    if (itemStack.stackTagCompound != null) {
            String owner = itemStack.stackTagCompound.getString("owner");
            list.add("owner: " + owner);
            if (owner.equals(player.getDisplayName())) {
                    list.add(EnumChatFormatting.GREEN+ owner);
            }
    	}
    super.addInformation(itemStack, player, list, par4);
    }
	
	public static int getCodeIfValid(EntityPlayer player){
	    ItemStack hold = player.inventory.getCurrentItem();
	    return hold.stackTagCompound.getInteger("user");
	}
		
}
