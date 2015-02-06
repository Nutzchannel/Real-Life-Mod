package de.ItsAMysterious.mods.reallifemod.api.Handlers;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.player.PlayerSleepInBedEvent;
import net.minecraftforge.event.entity.EntityEvent.EntityConstructing;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.PlayerLoggedOutEvent;
import de.ItsAMysterious.mods.reallifemod.api.entity.properties.RealLifeProps;
import de.ItsAMysterious.mods.reallifemod.api.entity.properties.financialProps;
import de.ItsAMysterious.mods.reallifemod.client.ClientProxy;

public class TLM_EventHandler 
{
	private RealLifeProps PlayerProps;

	@SubscribeEvent
	public void onEntitySpawn(EntityJoinWorldEvent event)
	{
			if(event.entity!=null && event.entity instanceof EntityPlayer){
			EntityPlayer entity=(EntityPlayer)event.entity;
			if(entity.getExtendedProperties("RealLifeProps")==null){
			RealLifeProps props=new RealLifeProps(entity);
			props.player=entity;
			/*if(ClientProxy.Money.get(entity)!=null){
				props.money=ClientProxy.Money.get(entity);
			}else
			ClientProxy.Money.put(entity.getDisplayName(), 0.0F);*/
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
	public void onEntityConstructing(EntityConstructing event)
	{
		if (event.entity instanceof EntityPlayer && financialProps.get((EntityPlayer)event.entity) == null){
			financialProps.register((EntityPlayer)event.entity);
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
				EntityPlayer player=Minecraft.getMinecraft().thePlayer;
				ClientProxy.Money.replace(player.getDisplayName(), ClientProxy.Money.get(player.getDisplayName())+2);
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
	 
	 @SubscribeEvent
	 public void onZombyKilled(LivingDeathEvent event) {
		 if(event.entity instanceof EntityZombie &&event.source ==DamageSource.starve){
			 EntitySkeleton Skeleton=new EntitySkeleton(event.entity.worldObj);
			 Skeleton.setPosition(event.entity.posX,event.entity.posY,event.entity.posZ);
			 event.entity.worldObj.spawnEntityInWorld(Skeleton);
			 
		 }
		 if(event.entity!=null &&(event.entity instanceof EntityZombie)){
				EntityPlayer player=Minecraft.getMinecraft().thePlayer;
				ClientProxy.Money.replace(player.getDisplayName(), ClientProxy.Money.get(player.getDisplayName())+2);
		 }
	 }

   }
