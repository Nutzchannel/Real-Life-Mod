package de.ItsAMysterious.mods.reallifemod.api.entity.properties;

import javax.swing.border.CompoundBorder;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;

public class financialProps implements IExtendedEntityProperties{
	public static float Cash;
	public EntityPlayer player;
	@Override
	public void saveNBTData(NBTTagCompound parCompound) {
		NBTTagCompound compound=new NBTTagCompound();
		compound.setFloat("Cash", this.Cash);
		parCompound.setTag("Money", compound);
		this.player.writeEntityToNBT(parCompound);
	}

	@Override
	public void loadNBTData(NBTTagCompound compound) {
		player.readEntityFromNBT(compound);
		NBTTagCompound comp=compound.getCompoundTag("Money");
		this.Cash=comp.getFloat("Cash");
		
	}

	@Override
	public void init(Entity entity, World world) {
		if(entity instanceof EntityPlayer){
			this.player=(EntityPlayer)entity;
		}
	}
	
}
