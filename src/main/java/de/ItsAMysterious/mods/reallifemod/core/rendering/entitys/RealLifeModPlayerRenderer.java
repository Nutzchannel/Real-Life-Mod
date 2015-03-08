package de.ItsAMysterious.mods.reallifemod.core.rendering.entitys;

import static org.lwjgl.opengl.GL11.GL_CULL_FACE;
import static org.lwjgl.opengl.GL11.GL_LIGHTING;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL11.glDisable;
import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushMatrix;
import static org.lwjgl.opengl.GL11.glRotatef;
import static org.lwjgl.opengl.GL11.glScalef;
import static org.lwjgl.opengl.GL11.glTranslated;
import static org.lwjgl.opengl.GL11.glTranslatef;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;

import org.lwjgl.BufferUtils;
import org.lwjgl.input.Keyboard;
import org.lwjgl.util.vector.Vector3f;

import de.ItsAMysterious.mods.reallifemod.api.entity.properties.RealLifeProperties;
import de.ItsAMysterious.mods.reallifemod.config.RealLifeModConfig;

/**
 * 
 * @author MO
 *
 */

public class RealLifeModPlayerRenderer extends RenderPlayer {
	protected IModelCustom model, modelHead, leftArm, rightArm, leftleg,
			rightleg, hairs;
	private float bodyRotY;
	private float headRotX;
	private float rightArmRotZ = 0;
	private float leftArmRotY = 0;
	private float leftArmRotX = 0;
	private float rightArmRotX = 0;
	private float rightArmRotY = 0;
	private float headRotY;
	private boolean aimedBow;
	private float bodyRotX;
	private boolean isSneak;
	private float headwearRotY = 0;
	private float headwearRotX = 0;
	private float rightlegRotX = 0;
	private float leftlegRotX = 0;
	private float rightlegRotY = 0;
	private float leftlegRotY = 0;
	private ItemStack heldItemLeft;
	private ItemStack heldItemRight;
	private float onGround;
	private float rightarmRotPointZ = 0;
	private float rightarmRotPointX = 0;
	private float leftarmRotPointZ = 0;
	private float leftarmRotPointX = 0;
	private boolean isRiding;
	private float delta = 0;
	private float rightlegRotZ;
	private float leftlegRotZ;
	private float bodyRotZ;
	private float leftArmRotZ;
	private float rightArmRotWater;
	private double rightArmRotWaterX;
	private double swimmingdelta;

	public enum swimmingstate {
		rotate, down
	}

	public static FloatBuffer allocFloats(int howmany) {
		return ByteBuffer.allocateDirect(howmany * 4)
				.order(ByteOrder.nativeOrder()).asFloatBuffer();
	}

	public static FloatBuffer allocFloats(float floatarray[]) {
		FloatBuffer fb = ByteBuffer.allocateDirect(floatarray.length * 4)
				.order(ByteOrder.nativeOrder()).asFloatBuffer();
		fb.put(floatarray).flip();
		return fb;
	}

	public RealLifeModPlayerRenderer() {
		this.model = AdvancedModelLoader.loadModel(new ResourceLocation(
				"reallifemod:models/minecraftcharacter.obj"));
		this.modelHead = AdvancedModelLoader.loadModel(new ResourceLocation(
				"reallifemod:models/characterhead.obj"));
		this.leftArm = AdvancedModelLoader.loadModel(new ResourceLocation(
				"reallifemod:models/leftarm.obj"));
		this.rightArm = AdvancedModelLoader.loadModel(new ResourceLocation(
				"reallifemod:models/rightarm.obj"));
		this.leftleg = AdvancedModelLoader.loadModel(new ResourceLocation(
				"reallifemod:models/leftleg.obj"));
		this.rightleg = AdvancedModelLoader.loadModel(new ResourceLocation(
				"reallifemod:models/rightleg.obj"));
		this.hairs = AdvancedModelLoader.loadModel(new ResourceLocation(
				"reallifemod:models/characterhairs.obj"));

	}

	@Override
	protected void renderEquippedItems(AbstractClientPlayer entity,
			float p_77029_2_) {
		EntityItem itementity = new EntityItem(entity.worldObj, 0, 0, 0,
				entity.getCurrentEquippedItem());
	}

	protected void doRendering(EntityPlayer entity, double x, double y,
			double z, float rotatedYaw, float rotatedPitch) {

		this.delta = (float) (entity.getAge() * 0.75);
		glPushMatrix();
		glDisable(GL_LIGHTING);
		glDisable(GL_CULL_FACE);
		glPushMatrix();
		this.bindTexture(new ResourceLocation("reallifemod:textures/corps.png"));
		if (entity.isRiding()) {
			glTranslated((float) x,
					(float) (y - 1.5F) - entity.ridingEntity.getYOffset(),
					(float) z);
		} else
			glTranslatef((float) x, (float) (y - 1.5F), (float) z);
		glRotatef(-entity.rotationYaw, 0.0F, 1.0F, 0.0F);
		if (!entity.onGround && entity.capabilities.isFlying) {
			if (RealLifeModConfig.mouseSteering) {
				glRotatef(entity.rotationPitch, 1.0F, 0.0F, 0.0F);
				entity.motionY = -Math.tanh((entity.rotationPitch / Math.PI * 180))* 9.81/ 20 * (entity.moveForward);
			}
		}
		glPushMatrix();
		glTranslatef(0.0F, 0.85F, 0.0F);
		glRotatef(bodyRotX, 1.0F, 0.0F, 0.0F);
		glRotatef(bodyRotY, 0.0F, 1.0F, 0.0F);
		glRotatef(bodyRotZ, 0.0F, 0.0F, 1.0F);
		this.model.renderAll();

		glPushMatrix();
		glTranslatef(0.0F, 0.6F, 0.0F);
		glRotatef(-entity.rotationYaw, 0.0F, 1.0F, 0.0F);
		glRotatef(entity.rotationYawHead, 0.0F, 1.0F, 0.0F);
		glRotatef(entity.rotationPitch, 1.0F, 0.0F, 0.0F);
		this.modelHead.renderAll();
		glPushMatrix();
		glTranslated(0.0125, 0, 0);
		Vector3f haircolor = ((RealLifeProperties) entity.getExtendedProperties("RealLifeProperties")).haircolor;
		if(haircolor!=null){
		glColor4f(haircolor.x/256, haircolor.y/256, haircolor.z/256,1.0F);
		}else
			glColor4f(1.0F, 1.0F, 1.0F,0.5F);
		this.hairs.renderAll();
		glPopMatrix();
		glPopMatrix();


		glColor3f(1.0F, 1.0F, 1.0F);

		glPushMatrix();
		glTranslatef(0.175F, 0.45F, 0.0F);
		glRotatef(leftArmRotX, 1.0F, 0.0F, 0.0F);
		glRotatef(leftArmRotY, 0.0F, 1.0F, 0.0F);
		glRotatef(leftArmRotZ, 0.0F, 0.0F, 1.0F);
		this.leftArm.renderAll();
		glPopMatrix();

		glPushMatrix();
		glTranslatef(-0.14525F, 0.5F, -0.035F);
		glRotatef(rightArmRotX, 1.0F, 0.0F, 0.0F);
		glRotatef(rightArmRotY, 0.0F, 1.0F, 0.0F);
		glRotatef(rightArmRotZ, 0.0F, 0.0F, 1.0F);
		this.rightArm.renderAll();
		glPopMatrix();
		glPopMatrix();

		glPushMatrix();
		glTranslatef(0.0F, 0.85F, 0.0F);
		glRotatef(rightlegRotX, 1.0F, 0.0F, 0);
		glRotatef(rightlegRotY, 0.0F, 1.0F, 0);
		glRotatef(rightlegRotZ, 0.0F, 0.0F, 1.0F);
		this.rightleg.renderAll();
		glPopMatrix();

		glPushMatrix();
		glTranslatef(0.0F, 0.85F, 0.0F);
		glRotatef(leftlegRotX, 1.0F, 0.0F, 0);
		glRotatef(leftlegRotY, 0.0F, 0.0F, 1.0F);
		glRotatef(leftlegRotZ, 0.0F, 0.0F, 1.0F);
		this.leftleg.renderAll();
		glPopMatrix();
		glPopMatrix();
		glPopMatrix();
		ItemStack itemstack = entity.inventory.getCurrentItem();
		setRotationAngles(delta, 10, (float) x, (float) z, entity.rotationYaw,
				entity.rotationPitch, entity);
		if (!entity.isInWater() && !entity.isRiding())
			this.doDanceMovements(entity);
	}

	public void renderFirstPersonArm(EntityPlayer player) {

		if (player.ridingEntity == null) {
			glPushMatrix();
			glDisable(GL_LIGHTING);
			Minecraft.getMinecraft().renderEngine
					.bindTexture(new ResourceLocation(
							"reallifemod:textures/corps.png"));
			if (!player.onGround && player.capabilities.isFlying
					&& player.isAirBorne) {
				if (RealLifeModConfig.mouseSteering) {
				player.motionY = -Math.tanh((player.rotationPitch / Math.PI * 180))* 9.81/ 20 * (player.moveForward);
				}
			}
			glPushMatrix();
			glTranslated(-0.75F, 0.0, -0.1);
			glRotatef(180, 1.0F, 0.0F, 0.0F);
			glRotatef(-30.0F, 0.0F, 1F, 0.0F);
			// Y
			// glRotatef(45.0F, 0.0F, 0.0F, 1.0F);
			glDisable(GL_CULL_FACE);
			glPushMatrix();
			glScalef(2.0F, 2.0F, 2.0F);
			this.rightArm.renderAll();
			glPopMatrix();
			glPopMatrix();
			glPopMatrix();
		} else {
			glPushMatrix();
			glDisable(GL_LIGHTING);
			glDisable(GL_CULL_FACE);
			glPushMatrix();
			Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation("reallifemod:textures/corps.png"));
			glTranslated(-0.80F, 0.0, -0.1);
			glRotatef(180, 1.0F, 0.0F, 0.0F);
			glRotatef(-35.0F, 0.0F, 1F, 0.0F);
			// Y
			glRotatef(35F, 0.0F, 0.0F, 1.0F);
			glDisable(GL_CULL_FACE);
			glPushMatrix();
			// glRotatef(-player.rotationPitch, 0.0F, 1.0F, 0.0F);
			glScalef(2.0F, 2.0F, 2.0F);
			this.rightArm.renderAll();
			glPopMatrix();
			glPushMatrix();
			glDisable(GL_LIGHTING);
			glDisable(GL_CULL_FACE);
			glPushMatrix();
			Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation("reallifemod:textures/corps.png"));
			glTranslated(0.80F, 0.0, -0.1);
			glRotatef(180, 1.0F, 0.0F, 0.0F);
			glRotatef(-35.0F, 0.0F, 1F, 0.0F);
			// Y
			glRotatef(35F, 0.0F, 0.0F, 1.0F);
			glDisable(GL_CULL_FACE);
			glPushMatrix();
			// glRotatef(-player.rotationPitch, 0.0F, 1.0F, 0.0F);
			glScalef(2.0F, 2.0F, 2.0F);
			this.leftArm.renderAll();
			glPopMatrix();
			glPopMatrix();
			glPopMatrix();
		}
		if (player.getCurrentEquippedItem() != null) {
			this.heldItemRight = player.inventory.getCurrentItem();
			EntityItem item = new EntityItem(player.worldObj);
			item.setEntityItemStack(player.inventory.getCurrentItem());
			RenderManager.instance.renderEntityWithPosYaw(item, player.posX,
					player.posY + 2, player.posZ, 0.0F, 0.0F);
		}
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity p_110775_1_) {
		return null;
	}

	@Override
	public boolean isStaticEntity() {
		return false;
	}

	private static FloatBuffer asFloatBuffer(float[] values) {
		FloatBuffer buffer = BufferUtils.createFloatBuffer(values.length);
		buffer.put(values);
		buffer.flip();
		return buffer;
	}

	@Override
	public void doRender(Entity entity, double x, double y, double z,
			float rotationYaw, float rotationPitch) {
		doRendering((EntityPlayer) entity, x, y, z, rotationYaw, rotationPitch);
	}

	public void setRotationAngles(float p_78087_1_, float p_78087_2_,
			float p_78087_3_, float p_78087_4_, float p_78087_5_,
			float p_78087_6_, EntityPlayer entity) {
		if (swimmingdelta < 1.0F)
			swimmingdelta += 0.01;
		else
			swimmingdelta = 0.00F;
		this.headRotY = p_78087_4_ / (180F / (float) Math.PI);
		this.headRotX = p_78087_5_ / (180F / (float) Math.PI);
		this.headwearRotY = this.headRotY;
		this.headwearRotX = this.headRotX;
		// If entity is in water
		if (entity.isInWater()) {
			if (entity.motionZ != 0) {
				this.leftArmRotZ += -(float) (Math.cos(swimmingdelta) / 2 + 0.5F) * 1.75;
				this.rightArmRotZ += +(float) (Math.cos(swimmingdelta) / 2 + 0.5F) * 1.75;
				this.rightlegRotX = 90 + MathHelper.cos(p_78087_1_ * 0.6662F)
						* 1.4F * p_78087_2_;
				this.leftlegRotX = 90
						+ MathHelper
								.cos(p_78087_1_ * 0.6662F + (float) Math.PI)
						* 1.4F * p_78087_2_;
				if (leftArmRotZ < 0) {
					leftArmRotZ = 180;
					rightArmRotZ = 180;
				}
			}

			bodyRotX = 90.0F;
			bodyRotZ = 0.0F;
			leftArmRotX = 0.0F;
			leftArmRotY = 0.0F;
			rightArmRotY = 0.0F;
			rightArmRotX = 0.0F;
			leftlegRotZ = 0.0F;
			leftlegRotY = 0.0F;
			rightlegRotZ = 0.0F;
			rightlegRotY = 0.0F;
		} else {
			rightlegRotX = 0.0F;
			leftlegRotX = 0.0F;
			leftArmRotZ = 0.0F;
			rightArmRotZ = 0.0F;

			// Walkinganimationcycle
			if (entity.moveForward != 0 && !entity.isRiding()) {
				this.rightArmRotX = MathHelper.cos(p_78087_1_ * 0.7562F
						+ (float) Math.PI)
						* 3.0F * p_78087_2_ * 0.5F;
				this.leftArmRotX = MathHelper.cos(p_78087_1_ * 0.7562F) * 3.0F
						* p_78087_2_ * 0.5F;
				this.rightArmRotZ = 0.0F;
				this.leftArmRotY = 0.0F;
				this.rightlegRotX = MathHelper.cos(p_78087_1_ * 0.6662F) * 1.4F
						* p_78087_2_;
				this.leftlegRotX = MathHelper.cos(p_78087_1_ * 0.6662F
						+ (float) Math.PI)
						* 1.4F * p_78087_2_;
				this.leftArmRotY = 0.0F;
				this.rightlegRotY = MathHelper.cos(p_78087_1_ * 0.6662F) * 1.4F
						* p_78087_2_;
				this.leftlegRotY = MathHelper.cos(p_78087_1_ * 0.6662F
						+ (float) Math.PI)
						* 1.4F * p_78087_2_;
				this.rightlegRotY = 0.0F;
				this.leftlegRotY = 0.0F;
			}

			if (!entity.onGround && entity.capabilities.isFlying
					&& entity.isAirBorne) {
				if (Keyboard.isKeyDown(Keyboard.KEY_A)) {
					if (this.bodyRotY > -45) {
						this.bodyRotY -= 3.6F;
					}
				} else {
					if (this.bodyRotY < 0) {
						this.bodyRotY += 3.6F;
					}
				}
				bodyRotX = 90.0F;
				leftArmRotX = 0.0F;
				leftArmRotY = 0.0F;
				rightArmRotX = 0.0F;
				rightArmRotY = 0.0F;
				leftlegRotX = 90;
				leftlegRotY = 0.0F;
				leftlegRotZ = 0.0F;
				rightlegRotX = 90;
				rightlegRotY = 0.0F;
				rightlegRotZ = 0.0F;
			}
			if (entity.isOnLadder() && entity.motionY >= 0) {
				this.rightArmRotX = -90 + MathHelper.cos(p_78087_1_ * 0.6662F)
						* 1.4F * p_78087_2_;
				this.leftArmRotX = -90
						+ MathHelper
								.cos(p_78087_1_ * 0.6662F + (float) Math.PI)
						* 1.4F * p_78087_2_;
			}

			if (entity.isRiding()) {
				// left leg
				this.leftlegRotX = -45.0F;
				this.leftlegRotY = 0.0F;
				this.leftlegRotZ = 22.5F;
				// right leg
				this.rightlegRotX = -45.0F;
				this.rightlegRotY = 0.0F;
				this.rightlegRotZ = -22.5F;

				this.rightArmRotX = -45.0F;
				this.leftArmRotX = -45.0F;

			}

			if (this.heldItemLeft != null) {
				this.leftArmRotX = this.leftArmRotX * 0.5F
						- ((float) Math.PI / 10F);
			}

			if (this.heldItemRight != null
					&& this.heldItemRight.getItem() instanceof ItemTool) {
				this.rightArmRotX = -90.0F;
			}

			this.rightArmRotY = 0.0F;
			this.leftArmRotY = 0.0F;
			float f6;
			float f7;
			if (this.onGround > -9990.0F) {
				f6 = this.onGround;
				this.bodyRotY = MathHelper.sin(MathHelper.sqrt_float(f6)
						* (float) Math.PI * 2.0F) * 0.2F;
				this.rightarmRotPointZ = MathHelper.sin(this.bodyRotY) * 5.0F;
				this.rightarmRotPointX = -MathHelper.cos(this.bodyRotY) * 5.0F;
				this.leftarmRotPointZ = -MathHelper.sin(this.bodyRotY) * 5.0F;
				this.leftarmRotPointX = MathHelper.cos(this.bodyRotY) * 5.0F;
				this.rightArmRotY += this.bodyRotY;
				this.leftArmRotY += this.bodyRotY;
				this.leftArmRotX += this.bodyRotY;
				f6 = 1.0F - this.onGround;
				f6 *= f6;
				f6 *= f6;
				f6 = 1.0F - f6;
				f7 = MathHelper.sin(f6 * (float) Math.PI);
				float f8 = MathHelper.sin(this.onGround * (float) Math.PI)
						* -(this.headRotX - 0.7F) * 0.75F;
				this.rightArmRotX = (float) ((double) this.rightArmRotX - ((double) f7 * 1.2D + (double) f8));
				this.rightArmRotY += this.bodyRotY * 2.0F;
				this.rightArmRotZ = MathHelper.sin(this.onGround
						* (float) Math.PI)
						* -0.4F;
			}

			if (entity.isSneaking()) {
				if (this.bodyRotX < 45) {
					this.bodyRotX += 1.8F;
				}
				if (this.leftArmRotX < 25) {
					this.rightArmRotX += 2.5;
					this.leftArmRotX += 2.5;
				}
			} else {
				if (this.bodyRotX > 0) {
					this.bodyRotX -= 1.8F;
				}
				if (this.leftArmRotX > 0) {
					this.rightArmRotX -= 2.5;
					this.leftArmRotX -= 2.5;
				}
			}

			/*
			 * this.rightArmRotZ += MathHelper.cos(p_78087_3_ * 0.09F) * 0.05F +
			 * 0.05F; this.leftArmRotY -= MathHelper.cos(p_78087_3_ * 0.09F) *
			 * 0.05F + 0.05F; this.rightArmRotX += MathHelper.sin(p_78087_3_ *
			 * 0.067F) * 0.05F; this.leftArmRotX -= MathHelper.sin(p_78087_3_ *
			 * 0.067F) * 0.05F;
			 */

			if (this.aimedBow) {
				f6 = 0.0F;
				f7 = 0.0F;
				this.rightArmRotZ = 0.0F;
				this.leftArmRotY = 0.0F;
				this.rightArmRotY = -(0.1F - f6 * 0.6F) + this.headRotY;
				this.leftArmRotY = 0.1F - f6 * 0.6F + this.headRotY + 0.4F;
				this.rightArmRotX = -((float) Math.PI / 2F) + this.headRotX;
				this.leftArmRotX = -((float) Math.PI / 2F) + this.headRotX;
				this.rightArmRotX -= f6 * 1.2F - f7 * 0.4F;
				this.leftArmRotX -= f6 * 1.2F - f7 * 0.4F;
				this.rightArmRotZ += MathHelper.cos(p_78087_3_ * 0.09F) * 0.05F + 0.05F;
				this.leftArmRotY -= MathHelper.cos(p_78087_3_ * 0.09F) * 0.05F + 0.05F;
				this.rightArmRotX += MathHelper.sin(p_78087_3_ * 0.067F) * 0.05F;
				this.leftArmRotX -= MathHelper.sin(p_78087_3_ * 0.067F) * 0.05F;
			}
		}
	}

	public void doDanceMovements(EntityPlayer entity) {
		if (!entity.capabilities.isFlying) {
			if (Keyboard.isKeyDown(Keyboard.KEY_LEFT)) {
				if (this.leftlegRotZ < 45) {
					this.leftlegRotZ += 3.6F;
				}
			} else {
				if (this.leftlegRotZ > 0) {
					this.leftlegRotZ -= 3.6F;
				}
			}
			if (Keyboard.isKeyDown(Keyboard.KEY_RIGHT)) {
				if (this.rightlegRotZ > -45) {
					this.rightlegRotZ -= 3.6F;
				}
			} else {
				if (this.rightlegRotZ < 0) {
					this.rightlegRotZ += 3.6F;
				}
			}

			if (Keyboard.isKeyDown(Keyboard.KEY_UP)) {
				if (this.rightlegRotX > -45) {
					this.rightlegRotX -= 3.6F;
					this.leftlegRotX -= 3.6F;
				}
			} else {
				if (this.rightlegRotX < 0) {
					this.rightlegRotX += 3.6F;
					this.leftlegRotX += 3.6F;
				}
			}
			if (Keyboard.isKeyDown(Keyboard.KEY_DOWN)) {
				if (this.leftlegRotX < 45) {
					this.leftlegRotX += 3.6F;
					this.rightlegRotX += 3.6F;
				}
			} else {
				if (this.leftlegRotX > 0) {
					this.leftlegRotX -= 3.6F;
					this.rightlegRotX -= 3.6F;
				}
			}
			if (Keyboard.isKeyDown(Keyboard.KEY_NUMPAD6)) {
				if (this.bodyRotZ < 45) {
					this.bodyRotZ += 3.6F;
				}
			} else {
				if (this.bodyRotZ > 0) {
					this.bodyRotZ -= 3.6F;
				}
			}
			if (Keyboard.isKeyDown(Keyboard.KEY_NUMPAD4)) {
				if (this.bodyRotZ > -45) {
					this.bodyRotZ -= 3.6F;
				}
			} else {
				if (this.bodyRotZ < 0) {
					this.bodyRotZ += 3.6F;
				}
			}
			if (!entity.isSneaking()) {
				if (Keyboard.isKeyDown(Keyboard.KEY_NUMPAD2)) {
					if (this.bodyRotX > -45) {
						this.bodyRotX -= 3.6F;
					}
				} else {
					if (!Keyboard.isKeyDown(Keyboard.KEY_NUMPAD8))
						if (this.bodyRotX < -0.36F) {
							this.bodyRotX += 3.6F;
						} else
							this.bodyRotX = 0.0F;
				}
				if (Keyboard.isKeyDown(Keyboard.KEY_NUMPAD8)) {
					if (this.bodyRotX < 45) {
						this.bodyRotX += 3.6F;
					}
				} else {

					if (!Keyboard.isKeyDown(Keyboard.KEY_NUMPAD2))
						if (this.bodyRotX > 0.36F) {
							this.bodyRotX -= 3.6F;
						} else
							this.bodyRotX = 0.0F;
				}
			}
		}
	}

}