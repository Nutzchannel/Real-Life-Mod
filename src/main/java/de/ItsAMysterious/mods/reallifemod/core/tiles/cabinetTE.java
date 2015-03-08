package de.ItsAMysterious.mods.reallifemod.core.tiles;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class cabinetTE extends TileEntityDirectional {
	
	public static String tile;
	public static List<ItemStack>items=new ArrayList<ItemStack>(18);
	public cabinetTE(int type) {
		switch(type){
			case 0:this.tile="kitchen-cabinet";
			case 1:this.tile="kitchen-cabinethuge";
		}
	}

	@Override
    public int getBlockMetadata()
    {
		if(this.worldObj!=null){
        if (this.blockMetadata == -1)
        {
            this.blockMetadata = this.worldObj.getBlockMetadata(this.xCoord, this.yCoord, this.zCoord);
        }
        return this.blockMetadata;
    }else
    	return 1;
    }

}
