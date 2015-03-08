package de.ItsAMysterious.mods.reallifemod.core.renderers.blocks.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import de.ItsAMysterious.mods.reallifemod.core.blocks.furniture.BlockFreezer;

public class modelFreezer extends ModelBase
{
  //fields
    public static ModelRenderer Shape1;
    public static ModelRenderer Shape2;
    public static ModelRenderer Shape3;
    public static ModelRenderer Shape4;
    public static ModelRenderer Shape5;
    public static ModelRenderer TL;
    public static ModelRenderer TR;
	BlockFreezer TB;
	
  public modelFreezer()
  {
	  textureWidth = 512;
	  textureHeight = 512;
	  
      Shape1 = new ModelRenderer(this, 0, 150);
      Shape1.addBox(0F, 0F, 0F, 32, 17, 1);
      Shape1.setRotationPoint(-8F, 7F, -7F);
      Shape1.setTextureSize(512, 512);
      Shape1.mirror = true;
      setRotation(Shape1, 0F, 0F, 0F);
      Shape2 = new ModelRenderer(this, 0, 150);
      Shape2.addBox(0F, 0F, 0F, 32, 17, 1);
      Shape2.setRotationPoint(-8F, 7F, 7F);
      Shape2.setTextureSize(512, 512);
      Shape2.mirror = true;
      setRotation(Shape2, 0F, 0F, 0F);
      Shape3 = new ModelRenderer(this, 0, 100);
      Shape3.addBox(0F, 0F, 0F, 1, 17, 13);
      Shape3.setRotationPoint(-8F, 7F, -6F);
      Shape3.setTextureSize(512, 512);
      Shape3.mirror = true;
      setRotation(Shape3, 0F, 0F, 0F);
      Shape4 = new ModelRenderer(this, 0, 100);
      Shape4.addBox(0F, 0F, 0F, 1, 17, 13);
      Shape4.setRotationPoint(23F, 7F, -6F);
      Shape4.setTextureSize(512, 512);
      Shape4.mirror = true;
      setRotation(Shape4, 0F, 0F, 0F);
      Shape5 = new ModelRenderer(this, 0, 25);
      Shape5.addBox(0F, 0F, 0F, 30, 1, 13);
      Shape5.setRotationPoint(-7F, 23F, -6F);
      Shape5.setTextureSize(512, 512);
      Shape5.mirror = true;
      setRotation(Shape5, 0F, 0F, 0F);
      TL = new ModelRenderer(this, 75, 0);
      TL.addBox(0F, 0F, 0F, 15, 1, 13);
      TL.setRotationPoint(-7F, 7F, -6F);
      TL.setTextureSize(512, 512);
      TL.mirror = true;
      setRotation(TL, 0F, 0F, 0F);
      TR = new ModelRenderer(this, 75, 0);
      TR.addBox(0F, 0F, 0F, 16, 1, 13);
      TR.setRotationPoint(23F, 8F, 7F);
      TR.setTextureSize(512, 512);
      TR.mirror = true;
      setRotation(TR, 0F, 3.141593F, 0F);
  }
  
  @Override
public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    Shape1.render(f5);
    Shape2.render(f5);
    Shape3.render(f5);
    Shape4.render(f5);
    Shape5.render(f5);
    TL.render(f5);
    TR.render(f5);
  }
  
  private void setRotation(ModelRenderer model, float x, float y, float z)
  {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }
  
  @Override
public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5,Entity entity)
  {
    super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
  }

}
