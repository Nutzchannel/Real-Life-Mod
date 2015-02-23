package de.ItsAMysterious.mods.reallifemod.api.entity.properties;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;


public class RealLifeProps implements IExtendedEntityProperties{
	public static enum Moods{
		HAPPY, UNCOMFORTABLE,TENSE, FRIGHTENED, WEAK, SAD,COQUETTISH, PLAYFUL, STIRRED;
	}

	public static double Toilet=0;
	public static float thirst=0;
	public float fatigue=0;
	public float fun=100;
	public float courage=100;	
	public float money;
	public Moods mood=Moods.HAPPY;
	public int bedX=0;
	public int bedY=0;
	public int bedZ=0;
	public String name, surname;
	
	public EntityPlayer player;
	private int time;
	private int timewithoutwater=0;
	public boolean sleeping;
	private boolean pissing=false;
	public boolean updated;
	public phone ThePhone=new phone();
	
	public RealLifeProps(EntityPlayer player) {
		this.player=player;
	}
	
	@Override
	public void saveNBTData(NBTTagCompound saveTag) {
		this.player.writeEntityToNBT(saveTag);
		NBTTagCompound compound=new NBTTagCompound();
		saveTag.setTag("RealLifeProps", compound);
		compound.setString("MOOD", this.mood.name());
		compound.setFloat("FATIGUE", this.fatigue);
		compound.setString("PlayerName", this.name);

	}

	@Override
	public void loadNBTData(NBTTagCompound loadTag) {
		this.player.readEntityFromNBT(loadTag);
		NBTTagCompound compound=loadTag.getCompoundTag("RealLifeProps");
		this.mood=Moods.valueOf(compound.getString("MOOD"));
		this.fatigue=compound.getFloat("FATIGUE");
		this.name=compound.getString("PlayerName");

	}

	@Override
	public void init(Entity entity, World world) {
		if(entity!=null&& entity instanceof EntityPlayer){
			EntityPlayer player=(EntityPlayer)entity;
			player.registerExtendedProperties("RealLifeProps", this);
			this.player=player;
		}
	}
	
	public void updateStates(){
		if(thirst<0)RealLifeProps.thirst=0;	
		if(this.sleeping==true&&(player.posX!=player.getBedLocation().posX||player.posY!=player.getBedLocation().posY||player.posZ!=player.getBedLocation().posZ))
			this.sleeping=false;
		if(!player.capabilities.isCreativeMode){
		if(this.player.getHealth()<9.5&&this.player.getFoodStats().getSaturationLevel()<2.5F)this.mood=Moods.WEAK;
		else
		if(this.player.worldObj.getWorldTime()<24000&&this.player.worldObj.getWorldTime()>13000)this.mood=Moods.TENSE;else
		if(this.player.worldObj.getWorldTime()<24000&&this.player.worldObj.getWorldTime()>13000&&this.player.worldObj.difficultySetting!=EnumDifficulty.PEACEFUL)this.mood=Moods.FRIGHTENED;
		else
			if(this.player.worldObj.isRaining())this.mood=Moods.UNCOMFORTABLE;
		else
			this.mood=Moods.HAPPY;
		
		RealLifeProps.thirst+=0.00138888889D;//0.0001;
		if(thirst>90){
			player.addPotionEffect(new PotionEffect(Potion.confusion.getId(), 100));
		}else
		if(player.getActivePotionEffect(Potion.confusion)!=null){
			player.removePotionEffect(Potion.confusion.id);
		}
		if(RealLifeProps.thirst>=100){
			player.addPotionEffect(new PotionEffect(Potion.weakness.getId(),100));
			timewithoutwater++;
			if(timewithoutwater==100){
				player.addChatComponentMessage(new ChatComponentText("you're dying of"+EnumChatFormatting.BOLD+EnumChatFormatting.RED+"thirst"+EnumChatFormatting.WHITE+"!"));
				player.setHealth(player.getHealth()-1);
			}
		}
		if((100-RealLifeProps.thirst+(player.getFoodStats().getSaturationLevel()/0.19))>=50){
		if(RealLifeProps.Toilet<100){
			RealLifeProps.Toilet+=0.00415151515151515D;

		}else
		{
			//player.addPotionEffect(new PotionEffect(Potion.harm.getId(),160));
		}}
		if(this.pissing==true){
			player.motionX=0;
			player.motionY=0;
			player.motionZ=0;
			if(RealLifeProps.Toilet>0){
			RealLifeProps.Toilet--;
			RealLifeProps.thirst+=0.5;
			}
			else
				this.pissing=false;
		}
		if(thirst<0)thirst=0;
		if(thirst>100)thirst=100;
		}
}
	
	public String ColorOfMood(Moods theMood){
		if(theMood==Moods.HAPPY)
			return EnumChatFormatting.GREEN.toString();
		if(theMood==Moods.UNCOMFORTABLE)
			return EnumChatFormatting.DARK_AQUA.toString();
		if(theMood==Moods.FRIGHTENED)
			return EnumChatFormatting.YELLOW.toString();
		if(theMood==Moods.TENSE)
			return EnumChatFormatting.DARK_GREEN.toString();
		if(theMood==Moods.COQUETTISH)
			return EnumChatFormatting.LIGHT_PURPLE.toString();
		if(theMood==Moods.WEAK)
			return EnumChatFormatting.GRAY.toString();
		if(theMood==Moods.COQUETTISH)
			return EnumChatFormatting.DARK_GRAY.toString();
		return "";
	}

	public void setPissing() {
		this.pissing=true;
	}
	
	public static class phone{
		public enum phonepartners{
			POLICE,FIREFIGHTER,PARAMEDICS, TUTORIALHOTLINE;
		}
		public static phonepartners partner=phonepartners.POLICE;
	};

	
}
