package de.ItsAMysterious.mods.reallifemod.api.Handlers;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.world.BlockEvent.BreakEvent;
import net.minecraftforge.event.world.BlockEvent.PlaceEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.ItemPickupEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.PlayerLoggedOutEvent;
import cpw.mods.fml.common.gameevent.TickEvent.PlayerTickEvent;
import de.ItsAMysterious.mods.reallifemod.api.entity.properties.RealLifeProps;
import de.ItsAMysterious.mods.reallifemod.api.entity.properties.financialProps;
import de.ItsAMysterious.mods.reallifemod.client.ClientProxy;

public class RLMEventHandler_Common {
	public boolean wasMined;//check wether a block has been mind
	public Block lastBlockPlaced;

	@SubscribeEvent
	public void updateRealLifeProps(PlayerTickEvent event)
	{
			EntityPlayer player=event.player;
			RealLifeProps props=(RealLifeProps)player.getExtendedProperties("RealLifeProps");
			props.updateStates();
	}
	
	@SubscribeEvent
	public void onLogin(PlayerLoggedInEvent event){
		ClientProxy.doNameUpdate();
	}
	
	@SubscribeEvent
	public void onLogout(PlayerLoggedOutEvent event){
		ClientProxy.doSaving();
	}
	
	 @SubscribeEvent
	 public void onItemPickup(ItemPickupEvent event){
		financialProps cash=(financialProps) Minecraft.getMinecraft().thePlayer.getExtendedProperties(financialProps.EXT_PROP_NAME);

		 if(event.pickedUp.getEntityItem().getItem()==Item.getItemFromBlock(Blocks.iron_ore)){
				EntityPlayer player=Minecraft.getMinecraft().thePlayer;
				cash.addCash(20);
		 }
		 if(event.pickedUp.getEntityItem().getItem()==Item.getItemFromBlock(Blocks.gold_ore)){
				EntityPlayer player=Minecraft.getMinecraft().thePlayer;
				cash.addCash(100);
		 }
		 if(event.pickedUp.getEntityItem().getItem()==Items.diamond){
				EntityPlayer player=Minecraft.getMinecraft().thePlayer;
				cash.addCash(500);
		 }
		 if(event.pickedUp.getEntityItem().getItem()==Item.getItemFromBlock(Blocks.emerald_ore)){
				EntityPlayer player=Minecraft.getMinecraft().thePlayer;
				cash.addCash(1000);
		 }
		 this.wasMined=false;
	 }
	 
	 public double itemPrice(Item item){
		 
		 return 0;
	 }
	 
	 @SubscribeEvent
	 public void onBlockBreak(BreakEvent event){
		 if((event.block.getClass() != this.lastBlockPlaced.getClass())){
			 this.wasMined=true;
		 }
	 }
	 
	 @SubscribeEvent
	 public void onBLockPlaced(PlaceEvent event){
		 this.lastBlockPlaced=event.block;
		 if(event.block==Blocks.iron_ore){
				EntityPlayer player=Minecraft.getMinecraft().thePlayer;
				ClientProxy.Money.replace(player.getDisplayName(), ClientProxy.Money.get(player.getDisplayName())-5);
		 }
		 if(event.block==Blocks.gold_ore){
				EntityPlayer player=Minecraft.getMinecraft().thePlayer;
				ClientProxy.Money.replace(player.getDisplayName(), ClientProxy.Money.get(player.getDisplayName())-10);
		 }
		 if(event.block==Blocks.emerald_ore){
				EntityPlayer player=Minecraft.getMinecraft().thePlayer;
				ClientProxy.Money.replace(player.getDisplayName(), ClientProxy.Money.get(player.getDisplayName())-40);
		 }
		 if(event.block==Blocks.diamond_ore){
				EntityPlayer player=Minecraft.getMinecraft().thePlayer;
				ClientProxy.Money.replace(player.getDisplayName(), ClientProxy.Money.get(player.getDisplayName())-50);
		 }
		 

	 }
}
