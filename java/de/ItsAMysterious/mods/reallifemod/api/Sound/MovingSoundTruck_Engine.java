package de.ItsAMysterious.mods.reallifemod.api.Sound;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import de.ItsAMysterious.mods.reallifemod.core.Streets.Entitys.EntityTruck;
import net.minecraft.client.audio.MovingSound;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;

@SideOnly(Side.CLIENT)
public class MovingSoundTruck_Engine extends MovingSound
{
    private final EntityTruck truck;
    private float field_147669_l = 0.0F;
    protected int field_147665_h = 1;


    public MovingSoundTruck_Engine(EntityTruck entity)
    {
        super(new ResourceLocation("reallifemod:diesel"));
        this.truck = entity;
        this.repeat = true;
        this.field_147665_h = 0;
    }

    /**
     * Updates the JList with a new model.
     */
    @Override
	public void update()
    {
        if (this.truck.isDead)
        {
            this.donePlaying = true;
        }
        else
        {
            this.xPosF = (float)this.truck.posX;
            this.yPosF = (float)this.truck.posY;
            this.zPosF = (float)this.truck.posZ;
            float f = MathHelper.sqrt_double(this.truck.motionX * this.truck.motionX + this.truck.motionZ * this.truck.motionZ);

            if (f >= 0.01D)
            {
                this.field_147669_l = MathHelper.clamp_float(this.field_147669_l + 0.0025F, 0.0F, 1.0F);
                this.volume = 0.0F + MathHelper.clamp_float(f, 0.0F, 0.5F) * 0.7F;
            }
            else
            {
                this.field_147669_l = 0.0F;
                this.volume = 0.0F;
            }
        }
    }
}