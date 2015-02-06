package de.ItsAMysterious.mods.reallifemod.core.Renderers.blocks.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class modelShield extends ModelBase
{
  //fields
    ModelRenderer Shape1;
    ModelRenderer Shape2;
    ModelRenderer Shape22;
    ModelRenderer Shape3;
    ModelRenderer Shape32;
    ModelRenderer Shape4;
    ModelRenderer Shape42;
    ModelRenderer Shape5;
    ModelRenderer Shape52;
  
  public modelShield()
  {
    textureWidth = 1024;
    textureHeight = 1024;
    
    Shape1 = new ModelRenderer(this, 0, 150);
    Shape1.addBox(0F, 0F, 0F, 40, 30, 0);
    Shape1.setRotationPoint(-20F, -13F, 0F);
    Shape1.setTextureSize(64, 32);
    Shape1.mirror = true;
    setRotation(Shape1, 0F, 0F, 0F);
    Shape2 = new ModelRenderer(this, 0, 43);
    Shape2.addBox(0F, 0F, 0F, 2, 34, 2);
    Shape2.setRotationPoint(20F, -15F, -1F);
    Shape2.setTextureSize(64, 32);
    Shape2.mirror = true;
    setRotation(Shape2, 0F, 0F, 0F);
    Shape22 = new ModelRenderer(this, 300, 43);
    Shape22.addBox(0F, 0F, 0F, 2, 34, 2);
    Shape22.setRotationPoint(-22F, -15F, -1F);
    Shape22.setTextureSize(64, 32);
    Shape22.mirror = true;
    setRotation(Shape22, 0F, 0F, 0F);
    Shape3 = new ModelRenderer(this, 50, 0);
    Shape3.addBox(0F, 0F, 0F, 40, 2, 2);
    Shape3.setRotationPoint(-20F, -15F, -1F);
    Shape3.setTextureSize(64, 32);
    Shape3.mirror = true;
    setRotation(Shape3, 0F, 0F, 0F);
    Shape32 = new ModelRenderer(this, 350, 0);
    Shape32.addBox(0F, 0F, 0F, 40, 2, 2);
    Shape32.setRotationPoint(-20F, 17F, -1F);
    Shape32.setTextureSize(64, 32);
    Shape32.mirror = true;
    setRotation(Shape32, 0F, 0F, 0F);
    Shape4 = new ModelRenderer(this, 500, 0);
    Shape4.addBox(0F, 0F, 0F, 2, 9, 2);
    Shape4.setRotationPoint(-22F, 15F, 1F);
    Shape4.setTextureSize(64, 32);
    Shape4.mirror = true;
    setRotation(Shape4, 0F, 0F, 0F);
    Shape42 = new ModelRenderer(this, 500, 0);
    Shape42.addBox(0F, 0F, 0F, 2, 9, 2);
    Shape42.setRotationPoint(20F, 15F, 1F);
    Shape42.setTextureSize(64, 32);
    Shape42.mirror = true;
    setRotation(Shape42, 0F, 0F, 0F);
    Shape5 = new ModelRenderer(this, 600, 0);
    Shape5.addBox(0F, 0F, 0F, 1, 50, 1);
    Shape5.setRotationPoint(20F, -14F, 0.1F);
    Shape5.setTextureSize(64, 32);
    Shape5.mirror = true;
    setRotation(Shape5, 0F, 0F, 0.9337511F);
    Shape52 = new ModelRenderer(this, 600, 0);
    Shape52.addBox(0F, 0F, 0F, 1, 50, 1);
    Shape52.setRotationPoint(-20F, -13F, 0.1F);
    Shape52.setTextureSize(64, 32);
    Shape52.mirror = true;
    setRotation(Shape52, 0F, 0F, -0.9337511F);
}
  
  @Override
public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    Shape1.render(f5);
    Shape2.render(f5);
    Shape22.render(f5);
    Shape3.render(f5);
    Shape32.render(f5);
    Shape4.render(f5);
    Shape42.render(f5);
    Shape5.render(f5);
    Shape52.render(f5);
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
