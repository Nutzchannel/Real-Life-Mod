package de.ItsAMysterious.mods.reallifemod.api.entity.properties;

import java.text.SimpleDateFormat;
import java.util.Date;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenDesert;
import net.minecraft.world.biome.BiomeGenForest;
import net.minecraft.world.biome.BiomeGenJungle;
import net.minecraft.world.biome.BiomeGenPlains;
import net.minecraft.world.biome.BiomeGenRiver;
import net.minecraft.world.biome.BiomeGenSnow;
import net.minecraftforge.common.IExtendedEntityProperties;

import org.lwjgl.util.vector.Vector3f;

public class RealLifeProperties implements IExtendedEntityProperties{
	public static enum Moods{
		HAPPY, UNCOMFORTABLE,TENSE, FRIGHTENED, WEAK, SAD,COQUETTISH, PLAYFUL, STIRRED, ASHAMED;
	}

	public static double Toilet=0;
	public static float thirst=0;
	public static float fatigue=0;
	public static float fun=100;
	public static float courage=100;	
	public static Moods mood=Moods.HAPPY;
	public static String name;
	public static String surname;
	public static EntityPlayer player;
	private static int time;
	private static int timewithoutwater=0;
	public static boolean sleeping;
	private static boolean pissing=false;
	public static boolean updated;
	public static phone ThePhone=new phone();
	public static float Temperature;
	public static Vector3f haircolor;
	public gender Gender;
	public String birthdate;
	private int coldCounter;
	private boolean showColdWarning;
	
	public static enum gender{
		Male,Female;
	}
	public RealLifeProperties(EntityPlayer player){
		this.player=player;
		showColdWarning=true;
	}

	
	public void saveNBTData(NBTTagCompound parCompound){
		NBTTagCompound compound=new NBTTagCompound();
		compound.setString("PLAYERNAME", this.name);
		compound.setString("SURNAME", this.surname);
		compound.setString("gender", this.Gender.name());
		if(haircolor!=null){
		compound.setFloat("HairColorRed", this.haircolor.x);
		compound.setFloat("HairColorGreen", this.haircolor.y);
		compound.setFloat("HairColorBlue", this.haircolor.z);}else
		{
			compound.setFloat("HairColorRed", 0F);
			compound.setFloat("HairColorGreen",1F);
			compound.setFloat("HairColorBlue", 0F);
		}
		parCompound.setTag("RealLife", compound);
		Minecraft.getMinecraft().thePlayer.writeEntityToNBT(parCompound);
	}
    
	public void loadNBTData(NBTTagCompound compound){
		Minecraft.getMinecraft().thePlayer.readEntityFromNBT(compound);
		NBTTagCompound comp=(NBTTagCompound)compound.getCompoundTag("RealLife");
		this.name=comp.getString("PLAYERNAME");
		this.surname=comp.getString("SURNAME");
		if(comp.getString("gender")=="Male"){
			this.Gender=gender.Male;
		}else
			this.Gender=gender.Female;
		this.haircolor.x=comp.getFloat("HairColorRed");
		this.haircolor.y=comp.getFloat("HairColorGreen");
		this.haircolor.z=comp.getFloat("HairColorBlue");
		
    }
    
	@Override
	public void init(Entity entity, World world) {
		if(entity instanceof EntityPlayer){
			this.player=(EntityPlayer)entity;
			this.Gender=gender.Male;
			this.name="";
			this.surname="";
			this.thirst=0;
			this.Toilet=0;
			Date zeitstempel = new Date();
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");
			this.birthdate=simpleDateFormat.format(zeitstempel);
			this.haircolor=new Vector3f(1.0F,1.0F,1.0F);
		}
	}
	
	public void updateStates(){
		if(thirst<0)thirst=0;	
		if(this.sleeping==true&&(player.posX!=player.getBedLocation().posX||player.posY!=player.getBedLocation().posY||player.posZ!=player.getBedLocation().posZ))
			this.sleeping=false;
		if(!player.capabilities.isCreativeMode){
		if(this.player.getHealth()<9.5&&this.player.getFoodStats().getSaturationLevel()<2.5F)this.mood=Moods.WEAK;
		else
		if(this.player.worldObj.getWorldTime()<24000&&this.player.worldObj.getWorldTime()>13000)this.mood=Moods.TENSE;else
		if(this.player.worldObj.getWorldTime()<24000&&this.player.worldObj.getWorldTime()>13000&&this.player.worldObj.difficultySetting!=EnumDifficulty.PEACEFUL)this.mood=Moods.FRIGHTENED;
		else
			if(this.player.worldObj.isRaining()&&this.player.isInWater())this.mood=Moods.UNCOMFORTABLE;
		else
			this.mood=Moods.HAPPY;
		
		thirst+=0.00138888889D;//0.0001;
		if(thirst>90){
			player.addPotionEffect(new PotionEffect(Potion.confusion.getId(), 100));
		}else
		if(player.getActivePotionEffect(Potion.confusion)!=null){
			player.removePotionEffect(Potion.confusion.id);
		}
		if(thirst>=100){
			player.addPotionEffect(new PotionEffect(Potion.weakness.getId(),100));
			timewithoutwater++;
			if(timewithoutwater==100){
				player.addChatComponentMessage(new ChatComponentText("you're dying of"+EnumChatFormatting.BOLD+EnumChatFormatting.RED+"thirst"+EnumChatFormatting.WHITE+"!"));
				player.setHealth(player.getHealth()-1);
			}
		}
		if((100-thirst+(player.getFoodStats().getSaturationLevel()/0.19))>=50){
		if(Toilet<100){
			Toilet+=0.00415151515151515D;

		}else
		{
			player.addPotionEffect(new PotionEffect(Potion.digSlowdown.getId(),160));
		}}
		if(this.pissing==true){
			player.motionX=0;
			player.motionY=0;
			player.motionZ=0;
			if(Toilet>0){
			Toilet--;
			thirst+=0.5;
			}
			else
				this.pissing=false;
		}
		if(thirst<0)thirst=0;
		if(thirst>100)thirst=100;
		if(!player.isInWater()){
			if(player.worldObj.getWorldChunkManager().getBiomeGenAt((int)player.posX,(int) player.posZ)instanceof BiomeGenSnow)
				Temperature=-20;
				if(player.worldObj.getWorldChunkManager().getBiomeGenAt((int)player.posX,(int) player.posZ)instanceof BiomeGenPlains)
				Temperature=28;
				if(player.worldObj.getWorldChunkManager().getBiomeGenAt((int)player.posX,(int) player.posZ)instanceof BiomeGenDesert)
				Temperature=35;
				if(player.worldObj.getWorldChunkManager().getBiomeGenAt((int)player.posX,(int) player.posZ)instanceof BiomeGenForest)
				Temperature=28;
				if(player.worldObj.getWorldChunkManager().getBiomeGenAt((int)player.posX,(int) player.posZ)instanceof BiomeGenRiver)
				Temperature=25;
				if(player.worldObj.getWorldChunkManager().getBiomeGenAt((int)player.posX,(int) player.posZ)instanceof BiomeGenJungle)
				Temperature=35;

		}else
		{
			if(this.Temperature>15.0F){
				Temperature-=0.25F;
			}
		}
		if(this.Temperature<-10&&this.coldCounter<1200){
			if(this.showColdWarning){
				player.addChatMessage(new ChatComponentText("It's to" +EnumChatFormatting.AQUA+"cold"+EnumChatFormatting.RESET+ "here! Dress warmer or build a fire to get warm!" ));
				this.showColdWarning=false;
			}
			player.addPotionEffect(new PotionEffect(Potion.moveSlowdown.id,10));
			this.coldCounter++;
		}else
		{
			player.addPotionEffect(new PotionEffect(Potion.harm.id,10));
			if(player.getHealth()<=0){
				this.coldCounter=0;
			}
		}
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
		if(theMood==Moods.ASHAMED)
			return EnumChatFormatting.YELLOW.toString();
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
