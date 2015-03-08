package de.ItsAMysterious.mods.reallifemod.core.gui;

import java.awt.Color;

import javafx.scene.control.TreeItem;

import org.lwjgl.opengl.GL11;

import static org.lwjgl.opengl.GL11.*;
import cpw.mods.fml.client.FMLClientHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.ResourceLocation;
import de.ItsAMysterious.mods.reallifemod.TLMBlocks;
import de.ItsAMysterious.mods.reallifemod.api.entity.properties.financialProps;
import de.ItsAMysterious.mods.reallifemod.api.gui.RLM_Gui;
import de.ItsAMysterious.mods.reallifemod.client.ClientProxy;
import de.ItsAMysterious.mods.reallifemod.core.items.ExtendedItem;
import de.ItsAMysterious.mods.reallifemod.init.TLMItems;

public class GuiCreebay extends RLM_Gui{
	public int currentEntry=0;
	public int centerX, centerY;
	private float itemRotation=0;
	private EntityItem item;
	public Item theitem;
	private float currentPrice=0;
	private String state;
	
	public GuiCreebay() {
		super();
	}
	
	@Override
	public void initGui(){
		this.item=new EntityItem(mc.theWorld);
		this.item.setEntityItemStack(new ItemStack(TLMItems.ak));
		this.theitem=new Item();
		this.state="item.";
		super.initGui();
	}
	
	@Override
	public void drawScreen(int x, int y, float f){
		this.centerX=(int)width/2;
		this.centerY=(int)height/2;
		mc.getTextureManager().bindTexture(new ResourceLocation("reallifemod:textures/gui/NameChangeGui.png"));
		this.drawTexturedModalRect(centerX-100, centerY-50, 0, 0, 200, 100);
		this.drawItem();
		itemRotation--;
		super.drawScreen(x, y,f);
		this.buttonList.clear();
		this.buttonList.add(new GuiButton(0, centerX+55, centerY+25,40, 20, "Buy!"));
		this.buttonList.add(new GuiButton(1, centerX+55, centerY-45,15, 20, "<"));
		this.buttonList.add(new GuiButton(2, centerX+80, centerY-45,15, 20, ">"));
		this.drawString(EnumChatFormatting.DARK_GREEN+"Price: "+EnumChatFormatting.WHITE+String.valueOf(this.currentPrice)+"$", centerX-90, centerY-25, Color.white.getRGB());
		if(this.item.getEntityItem().getItem().getUnlocalizedName().contains("item.")){
		this.drawString(EnumChatFormatting.UNDERLINE+this.item.getEntityItem().getItem().getUnlocalizedName().split("item.")[1], centerX-90, centerY-40, Color.white.getRGB());
		}else
			if(this.item.getEntityItem().getItem().getUnlocalizedName().contains("block.")){
			this.drawString(EnumChatFormatting.UNDERLINE+this.item.getEntityItem().getItem().getUnlocalizedName().split("block.")[1], centerX-90, centerY-40, Color.white.getRGB());
		}
			
		this.drawString("current item: "+this.currentEntry+"/"+String.valueOf(TLMItems.itemList.size()+TLMBlocks.tlmBlockList.size()), centerX-90, centerY+35, Color.white.getRGB());
	} 
	
	@Override
	public void updateScreen(){
		super.updateScreen();
	}
	
	@Override
	public void actionPerformed(GuiButton button){
		financialProps props=(financialProps)mc.thePlayer.getExtendedProperties("financialProps");
		if(button.id==0){
			if(props.Cash-this.currentPrice>0){
			props.Cash-=this.currentPrice;
			mc.thePlayer.inventory.addItemStackToInventory(new ItemStack(Item.getItemById(Item.getIdFromItem(theitem))));
			mc.thePlayer.inventory.inventoryChanged=true;
			}
			else this.mc.thePlayer.addChatMessage(new ChatComponentText("You don't have enough Money for this!"));
		}
		
		if(button.id==1){
			if(currentEntry>0)currentEntry--;
			else currentEntry=TLMItems.itemList.size()+TLMBlocks.tlmBlockList.size();
			if(currentEntry>TLMItems.itemList.size()&&currentEntry-TLMItems.itemList.size()<TLMBlocks.tlmBlockList.size())
			{
				this.item.setEntityItemStack(new ItemStack(ExtendedItem.getItemFromBlock(TLMBlocks.tlmBlockList.get(currentEntry-TLMItems.itemList.size()))));
				this.theitem=ExtendedItem.getItemFromBlock(TLMBlocks.tlmBlockList.get(currentEntry-TLMItems.itemList.size()));
			}else
				if(this.currentEntry<TLMItems.itemList.size())
				{
					this.item.setEntityItemStack(new ItemStack(TLMItems.itemList.get(currentEntry)));
					this.theitem=(Item)TLMItems.itemList.get(currentEntry);

				}
				else{
					this.item.setEntityItemStack(new ItemStack(Items.apple));
					this.theitem=Items.apple;
			}
			
			if(this.item.getEntityItem().getItem() instanceof ExtendedItem||this.item.getEntityItem().getItem() instanceof ExtendedItem){
				ExtendedItem theItem=(ExtendedItem)this.item.getEntityItem().getItem();
				this.currentPrice=(float) theItem.price();
			}
		}
		if(button.id==2){
			if(currentEntry<(TLMItems.itemList.size()+TLMBlocks.tlmBlockList.size()))currentEntry++;//Block Air-Extended abgezogen
			else currentEntry=0;
			if(currentEntry>TLMItems.itemList.size()&&currentEntry-TLMItems.itemList.size()<TLMBlocks.tlmBlockList.size())
			{
				this.item.setEntityItemStack(new ItemStack(ExtendedItem.getItemFromBlock(TLMBlocks.tlmBlockList.get(currentEntry-TLMItems.itemList.size()))));
				this.theitem=ExtendedItem.getItemFromBlock(TLMBlocks.tlmBlockList.get(currentEntry-TLMItems.itemList.size()));

			}else
				if(this.currentEntry<TLMItems.itemList.size())
				{
					this.item.setEntityItemStack(new ItemStack(TLMItems.itemList.get(currentEntry)));
					this.theitem=(Item)TLMItems.itemList.get(currentEntry);

				}
				else{
					this.item.setEntityItemStack(new ItemStack(Items.apple));
			}
			
			if(this.item.getEntityItem().getItem() instanceof ExtendedItem||this.item.getEntityItem().getItem() instanceof ExtendedItem){
				ExtendedItem theItem=(ExtendedItem)this.item.getEntityItem().getItem();
				this.currentPrice=(float) theItem.price();
			}
		}
		this.confirmClicked(true, button.id);
	}
	
    private void drawItem() {
    	glPushMatrix();
        glTranslatef(centerX+70, centerY+12, 15.0F);
        glRotatef(itemRotation, 0.0F, 1.0F, 0.0F);
        glRotatef(180, 0.0F,0.0F, 1.0F);
        glPushMatrix();
        	glScalef(50.0F,50.0F,50.0F);
	        glPushMatrix();
	        glEnable(GL_ALPHA_TEST);
	        glDisable(GL_CULL_FACE);
	        	RenderManager.instance.renderEntityWithPosYaw(this.item, 0.0D, 0.0D, 0.0D, 0.0F, 1.0F);
	            glRotatef(180, 0.0F,0.0F, 1.0F);
	        glPopMatrix();
        glPopMatrix();
        glPopMatrix();
    }
	
}
