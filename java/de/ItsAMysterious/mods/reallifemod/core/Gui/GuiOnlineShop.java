package de.ItsAMysterious.mods.reallifemod.core.Gui;

import java.awt.Color;
import java.util.List;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.client.FMLClientHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiYesNoCallback;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import de.ItsAMysterious.mods.reallifemod.api.Gui.RLM_Gui;
import de.ItsAMysterious.mods.reallifemod.core.Streets.Entitys.EntityJeep;
import de.ItsAMysterious.mods.reallifemod.core.Streets.Entitys.EntityTruck;
import de.ItsAMysterious.mods.reallifemod.core.entitys.NPCs.EntityLanz;

public class GuiOnlineShop extends RLM_Gui implements GuiYesNoCallback{
	private float d=0;
	public static enum Vehicle{
		LANZ,TRUCK,JEEP;
	}
	public Vehicle currentVehicle=Vehicle.LANZ;
	private EntityClientPlayerMP player;
	public GuiOnlineShop() {
		this.player=Minecraft.getMinecraft().thePlayer;
	}
	
	@Override
	public void drawScreen(int par1,int par2,float f1){
		Entity entity;
		if(currentVehicle==Vehicle.LANZ)
			entity=new EntityLanz(Minecraft.getMinecraft().theWorld);
		else
		if(currentVehicle==Vehicle.TRUCK)
			entity=new EntityTruck(Minecraft.getMinecraft().theWorld);
		else
		if(currentVehicle==Vehicle.JEEP)
			entity=new EntityJeep(Minecraft.getMinecraft().theWorld);
		GuiOnlineShop.drawRect(0, 20, this.width, this.height, Color.WHITE.getRGB(),-150);
		this.buttonList.add(new GuiButton(0, 0, 0,this.width/5,20, "Home"));
		this.buttonList.add(new GuiButton(1, this.width/5, 0,this.width/5,20, "E-Mail"));
		this.buttonList.add(new GuiButton(2,this.width/5*2,0,this.width/5,20,"Help"));
		this.buttonList.add(new GuiButton(3,this.width/5*3,0,this.width/5,20,"Settings"));
		this.buttonList.add(new GuiButton(4, this.width/5*4,0,this.width/5, 20,"Management"));
		this.buttonList.add(new GuiButton(6, 20, this.height-30,20,20, "<"));
		this.buttonList.add(new GuiButton(7, 220, this.height-30,20,20, ">"));
		this.buttonList.add(new GuiButton(8, this.width/2+20, this.height-30,100 ,20,"Buy Vehicle"));
		this.buttonList.add(new GuiButton(9, this.width/2+160, this.height-30,100 ,20,"Sell Vehicle"));

		this.renderEntity(new EntityTruck(FMLClientHandler.instance().getWorldClient()));
		
		this.drawString(fontRendererObj, "Vehicle Facts", 400, 20, Color.gray.getRGB());
		super.drawScreen(par1, par2, f1);
	}
	@Override
	public void updateScreen(){
		this.d+=1;
		this.renderEntity(new EntityTruck(FMLClientHandler.instance().getWorldClient()));
		super.updateScreen();
		}		
	
	@Override
	public void actionPerformed(GuiButton button){
		if (button.id==0){
			mc.displayGuiScreen(new GuiInternet());
		}
		if (button.id==1){
			mc.displayGuiScreen(new GuiEMail());
		}
		if (button.id==2){
			mc.displayGuiScreen(new GuiHelp());
		}
		if (button.id==3){
			mc.displayGuiScreen(new GuiSettings());
		}
		if (button.id==4){
			mc.displayGuiScreen(new GuiManagement());
		}
		
		if(button.id==6){
			if(currentVehicle==Vehicle.LANZ)
				currentVehicle=Vehicle.TRUCK;
			else
			if(currentVehicle==Vehicle.TRUCK)
				currentVehicle=Vehicle.JEEP;
			else
			if(currentVehicle==Vehicle.JEEP)
				currentVehicle=Vehicle.LANZ;
		}
		
		if(button.id==7){
			if(currentVehicle==Vehicle.LANZ)
				currentVehicle=Vehicle.JEEP;
			else
			if(currentVehicle==Vehicle.TRUCK)
				currentVehicle=Vehicle.LANZ;
			else
			if(currentVehicle==Vehicle.JEEP)
				currentVehicle=Vehicle.TRUCK;
		}
		
		if(button.id==8){
			if(currentVehicle==Vehicle.LANZ){
				this.spawnEntity(new EntityLanz(Minecraft.getMinecraft().theWorld, player.posX, player.posY, player.posZ+2),Minecraft.getMinecraft().theWorld,this.player);
			}
			else
			if(currentVehicle==Vehicle.TRUCK){
				this.spawnEntity(new EntityTruck(Minecraft.getMinecraft().theWorld, player.posX, player.posY, player.posZ+2),Minecraft.getMinecraft().theWorld,this.player);
			}
			if(currentVehicle==Vehicle.JEEP){
				this.spawnEntity(new EntityJeep(Minecraft.getMinecraft().theWorld, player.posX, player.posY, player.posZ+2),Minecraft.getMinecraft().theWorld,this.player);
			}
		}
		confirmClicked(true, button.id);
	}
	
    private void renderEntity(Entity entity) {
    	GL11.glPushMatrix();
        GL11.glTranslatef(150.0F, 250.0F, 5.0F);
        GL11.glRotatef(d, 0.0F, 1.0F, 0.0F);
        RenderHelper.enableStandardItemLighting();
        GL11.glScalef(20.0F,20.0F,20.0F);
    	GL11.glPushMatrix();
	        GL11.glRotatef(180, 0.0F,0.0F, 1.0F);
	        GL11.glPushMatrix();
	        	RenderManager.instance.renderEntityWithPosYaw(entity, 0.0D, 0.0D, 0.0D, 0.0F, 1.0F);
	        GL11.glPopMatrix();
        GL11.glPopMatrix();
        RenderHelper.disableStandardItemLighting();
        GL11.glPopMatrix();
    }
    public void spawnEntity(Entity entity, World world, EntityPlayer player){
    	 float f = 1.0F;
         float f1 = player.prevRotationPitch + (player.rotationPitch - player.prevRotationPitch) * f;
         float f2 = player.prevRotationYaw + (player.rotationYaw - player.prevRotationYaw) * f;
         double d0 = player.prevPosX + (player.posX - player.prevPosX) * f;
         double d1 = player.prevPosY + (player.posY - player.prevPosY) * f + 1.62D - player.yOffset;
         double d2 = player.prevPosZ + (player.posZ - player.prevPosZ) * f;
         Vec3 vec3 = Vec3.createVectorHelper(d0, d1, d2);
         float f3 = MathHelper.cos(-f2 * 0.017453292F - (float)Math.PI);
         float f4 = MathHelper.sin(-f2 * 0.017453292F - (float)Math.PI);
         float f5 = -MathHelper.cos(-f1 * 0.017453292F);
         float f6 = MathHelper.sin(-f1 * 0.017453292F);
         float f7 = f4 * f5;
         float f8 = f3 * f5;
         double d3 = 5.0D;
         Vec3 vec31 = vec3.addVector(f7 * d3, f6 * d3, f8 * d3);
         MovingObjectPosition movingobjectposition = world.rayTraceBlocks(vec3, vec31, true);
             Vec3 vec32 = player.getLook(f);
             boolean flag = false;
             float f9 = 1.0F;
             List list = world.getEntitiesWithinAABBExcludingEntity(player, player.boundingBox.addCoord(vec32.xCoord * d3, vec32.yCoord * d3, vec32.zCoord * d3).expand(f9, f9, f9));
             int i;

             for (i = 0; i < list.size(); ++i)
             {
                 Entity ent = (Entity)list.get(i);

                 if (entity.canBeCollidedWith())
                 {
                     float f10 = entity.getCollisionBorderSize();
                     AxisAlignedBB axisalignedbb = entity.boundingBox.expand(f10, f10, f10);

                     if (axisalignedbb.isVecInside(vec3))
                     {
                         flag = true;
                     }
                 }
             }

                 if (movingobjectposition.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK)
                 {
                     i = movingobjectposition.blockX;
                     int j = movingobjectposition.blockY;
                     int k = movingobjectposition.blockZ;

                     if (world.getBlock(i, j, k) == Blocks.snow_layer)
                     {
                         --j;
                     }

                     if (!world.isRemote)
                     {
                         world.spawnEntityInWorld(entity);
                     }

                 }

             }

    public static void drawRect(int x1, int y1, int x2, int y2, int color,float zPos)
    {
        int j1;

        if (x1 < x2)
        {
            j1 = x1;
            x1 = x2;
            x2 = j1;
        }

        if (y1 < y2)
        {
            j1 = y1;
            y1 = y2;
            y2 = j1;
        }

        float f3 = (color >> 24 & 255) / 255.0F;
        float f = (color >> 16 & 255) / 255.0F;
        float f1 = (color >> 8 & 255) / 255.0F;
        float f2 = (color & 255) / 255.0F;
        Tessellator tessellator = Tessellator.instance;
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        OpenGlHelper.glBlendFunc(770, 771, 1, 0);
        GL11.glColor4f(f, f1, f2, f3);
        tessellator.startDrawingQuads();
        tessellator.addVertex(x1, y2, zPos);
        tessellator.addVertex(x2, y2, zPos);
        tessellator.addVertex(x2, y1, zPos);
        tessellator.addVertex(x1, y1, zPos);
        tessellator.draw();
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glDisable(GL11.GL_BLEND);
    }

	
}
