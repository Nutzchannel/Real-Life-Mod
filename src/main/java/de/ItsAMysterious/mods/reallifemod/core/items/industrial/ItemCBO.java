package de.ItsAMysterious.mods.reallifemod.core.items.industrial;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import de.ItsAMysterious.mods.reallifemod.core.gui.CustomBlockGui;
import de.ItsAMysterious.mods.reallifemod.core.gui.container.TileEntityCBO;
import de.ItsAMysterious.mods.reallifemod.core.items.ExtendedItem;

public class ItemCBO extends ExtendedItem{
	
	public Minecraft mc;
	public ItemCBO()
	{
		this.setTextureName("reallifemod:ItemCBO");
		this.setUnlocalizedName("cboItem");
		this.setPrice(23.99);
	}

    @Override
	public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player)
    {
    	int x=Minecraft.getMinecraft().objectMouseOver.blockX;
    	int y=Minecraft.getMinecraft().objectMouseOver.blockY;
    	int z=Minecraft.getMinecraft().objectMouseOver.blockZ;
    	Minecraft.getMinecraft().displayGuiScreen(new CustomBlockGui(x,y,z,Minecraft.getMinecraft().thePlayer.inventory,new TileEntityCBO()));
        return itemStack;
    }

}
