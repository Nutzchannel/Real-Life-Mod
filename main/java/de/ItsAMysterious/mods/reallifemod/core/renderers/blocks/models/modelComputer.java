package de.ItsAMysterious.mods.reallifemod.core.renderers.blocks.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class modelComputer extends ModelBase
{
    ModelRenderer Shape1;
    ModelRenderer Shape2;
    ModelRenderer Shape3;
    ModelRenderer Shape4;
    ModelRenderer Shape5;
    ModelRenderer Shape6;
    ModelRenderer Shape7;
  
  public modelComputer()
  {
    textureWidth = 1024;
    textureHeight = 1024;
    
      Shape1 = new ModelRenderer(this, 300, 0);
      Shape1.addBox(0F, 0F, 0F, 6, 11, 1);
      Shape1.setRotationPoint(1F, 13F, -6F);
      Shape1.setTextureSize(64, 32);
      Shape1.mirror = true;
      setRotation(Shape1, 0F, 0F, 0F);
      Shape2 = new ModelRenderer(this, 400, 0);
      Shape2.addBox(0F, 0F, 0F, 6, 1, 2);
      Shape2.setRotationPoint(1F, 13F, -6F);
      Shape2.setTextureSize(64, 32);
      Shape2.mirror = true;
      setRotation(Shape2, 0.7853982F, 0F, 0F);
      Shape3 = new ModelRenderer(this, 100, 0);
      Shape3.addBox(0F, 0F, 0F, 6, 1, 10);
      Shape3.setRotationPoint(1F, 11.6F, -4.6F);
      Shape3.setTextureSize(64, 32);
      Shape3.mirror = true;
      setRotation(Shape3, 0F, 0F, 0F);
      Shape4 = new ModelRenderer(this, 200, 0);
      Shape4.addBox(0F, 0F, 0F, 6, 1, 10);
      Shape4.setRotationPoint(1F, 23F, -5F);
      Shape4.setTextureSize(64, 32);
      Shape4.mirror = true;
      setRotation(Shape4, 0F, 0F, 0F);
      Shape5 = new ModelRenderer(this, 500, 0);
      Shape5.addBox(0F, 0F, 0F, 6, 11, 1);
      Shape5.setRotationPoint(1F, 12F, 4F);
      Shape5.setTextureSize(64, 32);
      Shape5.mirror = true;
      setRotation(Shape5, 0F, 0F, 0F);
      Shape6 = new ModelRenderer(this, 50, 0);
      Shape6.addBox(0F, 0F, 0F, 1, 11, 9);
      Shape6.setRotationPoint(1F, 12.5F, -5F);
      Shape6.setTextureSize(64, 32);
      Shape6.mirror = true;
      setRotation(Shape6, 0F, 0F, 0F);
      Shape7 = new ModelRenderer(this, 50, 0);
      Shape7.addBox(0F, 0F, 0F, 1, 11, 9);
      Shape7.setRotationPoint(6F, 12.5F, -5F);
      Shape7.setTextureSize(64, 32);
      Shape7.mirror = true;
      setRotation(Shape7, 0F, 0F, 0F);
  }
  
  @Override
public void render(Entity te, float f, float f1, float f2, float f3, float f4, float f5)
  {
    setRotationAngles(f, f1, f2, f3, f4, f5, te);
    Shape1.render(f5);
    Shape2.render(f5);
    Shape3.render(f5);
    Shape4.render(f5);
    Shape5.render(f5);
    Shape6.render(f5);
    Shape7.render(f5);
    super.render(te, f, f1, f2, f3, f4, f5);
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
