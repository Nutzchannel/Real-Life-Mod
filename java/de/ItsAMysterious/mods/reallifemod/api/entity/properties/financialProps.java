package de.ItsAMysterious.mods.reallifemod.api.entity.properties;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;

public class financialProps implements IExtendedEntityProperties{
	private final EntityPlayer player;
	public final static String EXT_PROP_NAME = "financialProps";
	public float Cash;
	
	public financialProps(EntityPlayer player)
	{
		this.player = player;
		this.Cash=0.0F;
	}
	
	public void setCash(float i) {
		this.Cash=i;
	}
	
	public void addCash(float i) {
		this.Cash+=i;
	}
	
	public static final void register(EntityPlayer player)
	{
		player.registerExtendedProperties(financialProps.EXT_PROP_NAME, new financialProps(player));
	}
	
	public static final financialProps get(EntityPlayer player)
	{
		return (financialProps) player.getExtendedProperties(EXT_PROP_NAME);
	}

	@Override
	public void saveNBTData(NBTTagCompound compound) {
		NBTTagCompound financial=new NBTTagCompound();
		financial.setFloat("Cash",this.Cash);
		compound.setTag("financialProps", financial);
	}

	@Override
	public void loadNBTData(NBTTagCompound compound) {
		NBTTagCompound financial=(NBTTagCompound)compound.getCompoundTag(financialProps.EXT_PROP_NAME);
		this.Cash=financial.getFloat("Cash");
		System.out.println("[TUT PROPS] Cash from NBT: " + this.Cash);
	}

	@Override
	public void init(Entity entity, World world) {
	}

}
