package de.ItsAMysterious.mods.reallifemod.core.items.roads;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import de.ItsAMysterious.mods.reallifemod.core.items.ExtendedItem;
import de.ItsAMysterious.mods.reallifemod.core.streets.entitys.EntityCherokee;

public class itemJeep extends ExtendedItem {
	public itemJeep(){
		setUnlocalizedName("jeep");
		setTextureName("reallifemod:cherokee");
		this.setPrice(5899.99F);
	}
    
	@Override
	public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player)
    {	
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

        if (movingobjectposition == null)
        {
            return itemStack;
        }
        else
        {
            Vec3 vec32 = player.getLook(f);
            boolean flag = false;
            float f9 = 1.0F;
            List list = world.getEntitiesWithinAABBExcludingEntity(player, player.boundingBox.addCoord(vec32.xCoord * d3, vec32.yCoord * d3, vec32.zCoord * d3).expand(f9, f9, f9));
            int i;

            for (i = 0; i < list.size(); ++i)
            {
                Entity entity = (Entity)list.get(i);

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

            if (flag)
            {
                return itemStack;
            }
            else
            {
                if (movingobjectposition.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK)
                {
                    i = movingobjectposition.blockX;
                    int j = movingobjectposition.blockY;
                    int k = movingobjectposition.blockZ;

                    if (world.getBlock(i, j, k) == Blocks.snow_layer)
                    {
                        --j;
                    }
                    EntityCherokee jeep = new EntityCherokee(world, (i + 0.5), (j + 1.0F),(k + 0.5F));

                    if (!world.getCollidingBoundingBoxes(jeep, jeep.boundingBox.expand(-0.1D, -0.1D, -0.1D)).isEmpty())
                    {
                        return itemStack;
                    }

                    if (!world.isRemote)
                    {
                        world.spawnEntityInWorld(jeep);
                    }

                    if (!player.capabilities.isCreativeMode)
                    {
                        --itemStack.stackSize;
                    }
                }

                return itemStack;
            }
        }
    }
	
	@Override
	public double price() {
		return 50.000;
	}

	@Override
	public int percentage() {
		return 0;
	}
}