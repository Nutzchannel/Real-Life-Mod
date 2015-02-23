package de.ItsAMysterious.mods.reallifemod.api.handlers;
import java.util.Random;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.player.PlayerSleepInBedEvent;
import net.minecraftforge.event.entity.EntityEvent.EntityConstructing;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.PlayerLoggedOutEvent;
import de.ItsAMysterious.mods.reallifemod.TLMItems;
import de.ItsAMysterious.mods.reallifemod.api.entity.properties.RealLifeProps;
import de.ItsAMysterious.mods.reallifemod.api.entity.properties.financialProps;
import de.ItsAMysterious.mods.reallifemod.client.ClientProxy;

public class TLM_EventHandler 
{
	private RealLifeProps PlayerProps;
	public static Random random;
	public static int dropped;
	
	@SubscribeEvent
	public void onEntitySpawn(EntityJoinWorldEvent event)
	{
			if(event.entity!=null && event.entity instanceof EntityPlayer){
			EntityPlayer entity=(EntityPlayer)event.entity;
			if(entity.getExtendedProperties("RealLifeProps")==null){
			RealLifeProps props=new RealLifeProps(entity);
			props.player=entity;
			if(!entity.capabilities.isCreativeMode&&!(entity.inventory.hasItem(TLMItems.bottle)||entity.inventory.hasItem(TLMItems.emptybottle)))
			entity.inventory.addItemStackToInventory(new ItemStack(TLMItems.bottle));
			ClientProxy.doNameUpdate();
			props.thirst=0;
			props.Toilet=0;
			entity.registerExtendedProperties("RealLifeProps", props);
			this.PlayerProps=props;
			}else
			this.PlayerProps=(RealLifeProps) entity.getExtendedProperties("RealLifeProps");
		}
	}
	
	@SubscribeEvent
	public void onEntityDrop(LivingDropsEvent event)
	{
		random = new Random();
		dropped = random.nextInt(2) + 1; //DO NOT CHANGE THIS
	
		if(event.entityLiving instanceof EntityZombie)
		{
			event.entityLiving.entityDropItem(new ItemStack(TLMItems.circuitBrd), dropped);
		}
	}
	
	@SubscribeEvent
	public void onEntityConstructing(EntityConstructing event)
	{
		if (event.entity instanceof EntityPlayer && event.entity.getExtendedProperties("financialProps")== null){
			event.entity.registerExtendedProperties("financialProps", new financialProps());
		}
	}
	

	/*@SubscribeEvent
	public void onMove(PlayerEvent event)
	{
			if(event.entity.worldObj.getBlock((int)event.entity.posX,(int) event.entity.posY,(int) event.entity.posZ)instanceof BlockAirExtended){
        		BlockAirExtended airblock=(BlockAirExtended)event.entity.worldObj.getBlock((int)event.entity.posX,(int) event.entity.posY+3,(int) event.entity.posZ);
        		if(airblock.TemperaturC==30){
        			System.out.println("Block temperature: 30°C");
        		};
			}
	}*/
	
	
	 @SubscribeEvent
	 public void onKillAnimal(LivingDeathEvent event) {
		 if(event.entity!=null &&(event.entity instanceof EntityZombie)){
			 if(!event.entity.worldObj.isRemote){
				EntityPlayer player=Minecraft.getMinecraft().thePlayer;
				 ((financialProps)player.getExtendedProperties("financialProps")).Cash+=50;
				//props.addCash(5.0F);
				 EntitySkeleton Skeleton=new EntitySkeleton(event.entity.worldObj);
				 Skeleton.setPosition(event.entity.posX,event.entity.posY,event.entity.posZ);
				 event.entity.worldObj.spawnEntityInWorld(Skeleton);
			 }
		 }
	 }
	 
	 @SubscribeEvent
	 public void onSleep(PlayerSleepInBedEvent event){
		 if(event.entityPlayer==Minecraft.getMinecraft().thePlayer){
			 RealLifeProps props=(RealLifeProps) event.entity.getExtendedProperties("RealLifeProps");
			 props.sleeping=true;
			 ClientProxy.bedTime=event.entityPlayer.getAge();
			 props.updated=false;
		 }
	 }
	 
   }
