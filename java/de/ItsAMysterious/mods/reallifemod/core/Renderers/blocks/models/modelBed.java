package de.ItsAMysterious.mods.reallifemod.core.Renderers.blocks.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class modelBed extends ModelBase
{
    ModelRenderer Shape2;
    ModelRenderer Shape3;
    ModelRenderer Shape1;
    ModelRenderer shape4;
    ModelRenderer Shape5;
    ModelRenderer Shape6;
    ModelRenderer Shape7;
    ModelRenderer Shape8;
    ModelRenderer mattress;
    ModelRenderer Shape10;
  
  public modelBed()
  {
    textureWidth = 512;
    textureHeight = 512;
    
      Shape2 = new ModelRenderer(this, 0, 128);
      Shape2.addBox(0F, 0F, 0F, 2, 11, 2);
      Shape2.setRotationPoint(-8F, 13F, -9F);
      Shape2.setTextureSize(64, 32);
      Shape2.mirror = true;
      setRotation(Shape2, 0F, 0F, 0F);
      Shape3 = new ModelRenderer(this, 128, 128);
      Shape3.addBox(0F, 0F, 0F, 12, 3, 1);
      Shape3.setRotationPoint(-6F, 18F, -8F);
      Shape3.setTextureSize(64, 32);
      Shape3.mirror = true;
      setRotation(Shape3, 0F, 0F, 0F);
      Shape1.mirror = true;
      Shape1 = new ModelRenderer(this, 0, 128);
      Shape1.addBox(0F, 0F, 0F, 2, 11, 2);
      Shape1.setRotationPoint(6F, 13F, -9F);
      Shape1.setTextureSize(64, 32);
      Shape1.mirror = true;
      setRotation(Shape1, 0F, 0F, 0F);
      Shape1.mirror = false;
      shape4 = new ModelRenderer(this, 0, 128);
      shape4.addBox(0F, 0F, 0F, 2, 11, 2);
      shape4.setRotationPoint(-8F, 13F, 22F);
      shape4.setTextureSize(64, 32);
      shape4.mirror = true;
      setRotation(shape4, 0F, 0F, 0F);
      Shape5.mirror = true;
      Shape5 = new ModelRenderer(this, 0, 128);
      Shape5.addBox(0F, 0F, 0F, 2, 11, 2);
      Shape5.setRotationPoint(6F, 13F, 22F);
      Shape5.setTextureSize(64, 32);
      Shape5.mirror = true;
      setRotation(Shape5, 0F, 0F, 0F);
      Shape5.mirror = false;
      Shape6 = new ModelRenderer(this, 128, 128);
      Shape6.addBox(0F, 0F, 0F, 12, 3, 1);
      Shape6.setRotationPoint(-6F, 18F, 22F);
      Shape6.setTextureSize(64, 32);
      Shape6.mirror = true;
      setRotation(Shape6, 0F, 0F, 0F);
      Shape7.mirror = true;
      Shape7 = new ModelRenderer(this, 0, 0);
      Shape7.addBox(0F, 0F, 0F, 1, 3, 29);
      Shape7.setRotationPoint(7F, 18F, -7F);
      Shape7.setTextureSize(512, 512);
      Shape7.mirror = true;
      setRotation(Shape7, 0F, 0F, 0F);
      Shape7.mirror = false;
      Shape8 = new ModelRenderer(this, 0, 0);
      Shape8.addBox(0F, 0F, 0F, 1, 3, 29);
      Shape8.setRotationPoint(-8F, 18F, -7F);
      Shape8.setTextureSize(512, 512);
      Shape8.mirror = true;
      setRotation(Shape8, 0F, 0F, 0F);
      mattress = new ModelRenderer(this, 128, 0);
      mattress.addBox(0F, 0F, 0F, 14, 3, 29);
      mattress.setRotationPoint(-7F, 17F, -7F);
      mattress.setTextureSize(512, 512);
      mattress.mirror = true;
      setRotation(mattress, 0F, 0F, 0F);
      Shape10 = new ModelRenderer(this, 128, 128);
      Shape10.addBox(0F, 0F, 0F, 12, 3, 1);
      Shape10.setRotationPoint(-6F, 14F, -8F);
      Shape10.setTextureSize(512, 512);
      Shape10.mirror = true;
      setRotation(Shape10, 0F, 0F, 0F);
  }
  
  @Override
public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    Shape2.render(f5);
    Shape3.render(f5);
    Shape1.render(f5);
    shape4.render(f5);
    Shape5.render(f5);
    Shape6.render(f5);
    Shape7.render(f5);
    Shape8.render(f5);
    mattress.render(f5);
    Shape10.render(f5);
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
    super.setRotationAngles(f, f1, f2, f3, f4, f5,entity);
  }

}
