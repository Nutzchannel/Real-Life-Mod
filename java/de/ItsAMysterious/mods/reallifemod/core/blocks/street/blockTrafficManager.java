package de.ItsAMysterious.mods.reallifemod.core.blocks.street;

import cpw.mods.fml.client.FMLClientHandler;
import de.ItsAMysterious.mods.reallifemod.core.Gui.Gui_TrafficLight;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class blockTrafficManager extends Block {
	public blockTrafficManager() {
		super(Material.rock);
		this.setBlockName("trafficmanager");
		this.setBlockTextureName("reallifemod:tar");
	}
	
    @Override
	public boolean onBlockActivated(World p_149727_1_, int p_149727_2_, int p_149727_3_, int p_149727_4_, EntityPlayer p_149727_5_, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_)
    {
    	Minecraft mc= FMLClientHandler.instance().getClient();
		mc.displayGuiScreen(new Gui_TrafficLight(mc.objectMouseOver.blockX,mc.objectMouseOver.blockY,mc.objectMouseOver.blockZ));
        return true;
    }

}
