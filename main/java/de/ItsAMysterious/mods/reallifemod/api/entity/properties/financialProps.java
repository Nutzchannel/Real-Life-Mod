package de.ItsAMysterious.mods.reallifemod.api.entity.properties;

import javax.swing.border.CompoundBorder;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;

public class financialProps implements IExtendedEntityProperties{
	public static double Cash;
	public static float Temperature;
	public EntityPlayer player;
	public financialProps(EntityPlayer player) {
		this.player=player;
	}
	@Override
	public void saveNBTData(NBTTagCompound parCompound) {
		NBTTagCompound compound=new NBTTagCompound();
		compound.setDouble("Cash", this.Cash);
		compound.setFloat("Temperature", this.Temperature);
		parCompound.setTag("Financial", compound);
		Minecraft.getMinecraft().thePlayer.writeEntityToNBT(parCompound);
	}

	@Override
	public void loadNBTData(NBTTagCompound compound) {
		Minecraft.getMinecraft().thePlayer.readEntityFromNBT(compound);
		NBTTagCompound comp;
		if(compound.getCompoundTag("Money")!=null)
		comp=compound.getCompoundTag("Money");
		else
		comp=compound.getCompoundTag("Financial");
		this.Cash=comp.getDouble("Cash");
		this.Temperature=comp.getFloat("Temperature");
		
	}

	@Override
	public void init(Entity entity, World world) {
		if(entity instanceof EntityPlayer){
			this.player=(EntityPlayer)entity;
			this.Cash=0;
			this.Temperature=0;
		}
	}
	
}
