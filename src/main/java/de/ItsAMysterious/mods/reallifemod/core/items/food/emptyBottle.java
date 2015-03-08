package de.ItsAMysterious.mods.reallifemod.core.items.food;

import de.ItsAMysterious.mods.reallifemod.RealLifeMod;
import de.ItsAMysterious.mods.reallifemod.core.items.ExtendedItem;
import de.ItsAMysterious.mods.reallifemod.init.TLMItems;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;

public class emptyBottle extends ExtendedItem{

	private boolean showMessage=true;

	public emptyBottle() {
		this.setUnlocalizedName("bottle");
		this.setTextureName("reallifemod:bottle");
		this.setCreativeTab(RealLifeMod.rlmfood);
		this.setMaxStackSize(1);
		this.setPrice(0.25F);
	}
	@Override
	public EnumAction getItemUseAction(ItemStack par1ItemStack)
    {
        return EnumAction.drink;
    }

    @Override
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World world, EntityPlayer player)
    {
    	Minecraft mc=Minecraft.getMinecraft();
    	if(world.getBlock(mc.objectMouseOver.blockX, mc.objectMouseOver.blockY, mc.objectMouseOver.blockZ)==Blocks.water){
    		int slot=player.inventory.currentItem;
    		player.inventory.setInventorySlotContents(slot, new ItemStack(TLMItems.bottle));
    		world.destroyBlockInWorldPartially(mc.objectMouseOver.blockX, mc.objectMouseOver.blockY, mc.objectMouseOver.blockZ,10,10);
    		world.markBlockForUpdate(mc.objectMouseOver.blockX, mc.objectMouseOver.blockY, mc.objectMouseOver.blockZ);
    		}else
    		if(this.showMessage==true){
    			player.addChatMessage(new ChatComponentText("You have to fill up your Bottle with some fresh "+EnumChatFormatting.AQUA+"Water"+EnumChatFormatting.WHITE+"!"));
    			this.showMessage=false;
    		}
        return par1ItemStack;
    }
    

}
