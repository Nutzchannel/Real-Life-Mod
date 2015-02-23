package de.ItsAMysterious.mods.reallifemod.core.renderers.blocks.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class modelCupboard extends ModelBase
{
    public static ModelRenderer Boottom;
    public static ModelRenderer WallLeft;
    public static ModelRenderer WallRight;
    public static ModelRenderer Top;
    public static ModelRenderer MiddleWall;
    public static ModelRenderer drawer2Front;
    public static ModelRenderer drawer1Front;
    public static ModelRenderer LittleDoor;
    public static ModelRenderer DrawerButton1;
    public static ModelRenderer DrawerButton2;
    public static ModelRenderer LeftDoor;
    public static ModelRenderer RightDoor;
    public static ModelRenderer BackWall;
    public static ModelRenderer LittleDoorButton;
    public static ModelRenderer LeftDoorButton;
    public static ModelRenderer RightDoorButton;
    public int textureWidth = 512;
    public int textureHeight = 512;
  public modelCupboard()
  {
      Boottom = new ModelRenderer(this, 0, 134);
      Boottom.addBox(0F, 0F, 0F, 48, 1, 16);
      Boottom.setRotationPoint(-24F, 23F, -8F);
      Boottom.setTextureSize(512, 512);
      Boottom.mirror = true;
      setRotation(Boottom, 0F, 0F, 0F);
      WallLeft = new ModelRenderer(this, 46, 70);
      WallLeft.addBox(0F, 0F, 0F, 1, 39, 16);
      WallLeft.setRotationPoint(-24F, -16F, -8F);
      WallLeft.setTextureSize(512, 512);
      WallLeft.mirror = true;
      setRotation(WallLeft, 0F, 0F, 0F);
      WallRight = new ModelRenderer(this, 87, 70);
      WallRight.addBox(0F, 0F, 0F, 1, 39, 16);
      WallRight.setRotationPoint(23F, -16F, -8F);
      WallRight.setTextureSize(512, 512);
      WallRight.mirror = true;
      setRotation(WallRight, 0F, 0F, 0F);
      Top = new ModelRenderer(this, 0, 46);
      Top.addBox(0F, 0F, 0F, 48, 1, 17);
      Top.setRotationPoint(-24F, -17F, -9F);
      Top.setTextureSize(512, 512);
      Top.mirror = true;
      setRotation(Top, 0F, 0F, 0F);
      MiddleWall = new ModelRenderer(this, 0, 70);
      MiddleWall.addBox(0F, 0F, 0F, 2, 39, 15);
      MiddleWall.setRotationPoint(-8F, -16F, -8F);
      MiddleWall.setTextureSize(512, 512);
      MiddleWall.mirror = true;
      setRotation(MiddleWall, 0F, 0F, 0F);
      drawer2Front = new ModelRenderer(this, 0, 32);
      drawer2Front.addBox(0F, 0F, 0F, 17, 6, 1);
      drawer2Front.setRotationPoint(-24F, 17F, -9F);
      drawer2Front.setTextureSize(512, 512);
      drawer2Front.mirror = true;
      setRotation(drawer2Front, 0F, 0F, 0F);
      drawer1Front = new ModelRenderer(this, 0, 0);
      drawer1Front.addBox(0F, 0F, 0F, 17, 6, 1);
      drawer1Front.setRotationPoint(-24F, 10.9F, -9F);
      drawer1Front.setTextureSize(512, 512);
      drawer1Front.mirror = true;
      setRotation(drawer1Front, 0F, 0F, 0F);
      LittleDoor = new ModelRenderer(this, 0, 0);
      LittleDoor.addBox(0F, 0F, 0F, 17, 27, 1);
      LittleDoor.setRotationPoint(-24F, -16F, -9F);
      LittleDoor.setTextureSize(512, 512);
      LittleDoor.mirror = true;
      setRotation(LittleDoor, 0F, 0F, 0F);
      DrawerButton1 = new ModelRenderer(this, 110, 0);
      DrawerButton1.addBox(0F, 0F, 0F, 4, 1, 1);
      DrawerButton1.setRotationPoint(-17F, 13F, -10F);
      DrawerButton1.setTextureSize(512, 512);
      DrawerButton1.mirror = true;
      setRotation(DrawerButton1, 0F, 0F, 0F);
      DrawerButton2 = new ModelRenderer(this, 127, 0);
      DrawerButton2.addBox(0F, 0F, 0F, 4, 1, 1);
      DrawerButton2.setRotationPoint(-17F, 19F, -10F);
      DrawerButton2.setTextureSize(512, 512);
      DrawerButton2.mirror = true;
      setRotation(DrawerButton2, 0F, 0F, 0F);
      LeftDoor = new ModelRenderer(this, 50, 200);
      LeftDoor.addBox(0F, 0F, 0F, 15, 39, 1);
      LeftDoor.setRotationPoint(-7F, -16F, -9F);
      LeftDoor.setTextureSize(512, 512);
      LeftDoor.mirror = true;
      setRotation(LeftDoor, 0F, 0F, 0F);
      RightDoor = new ModelRenderer(this, 0, 200);
      RightDoor.addBox(0F, 0F, 0F, 16, 39, 1);
      RightDoor.setRotationPoint(24F, -16F, -8F);
      RightDoor.setTextureSize(512, 512);
      RightDoor.mirror = true;
      setRotation(RightDoor, 0F, 3.141593F, 0F);
      BackWall = new ModelRenderer(this, 0, 154);
      BackWall.addBox(0F, 0F, 0F, 46, 39, 0);
      BackWall.setRotationPoint(-23F, -16F, 7.6F);
      BackWall.setTextureSize(512, 512);
      BackWall.mirror = true;
      setRotation(BackWall, 0F, 0F, 0F);
      LittleDoorButton = new ModelRenderer(this, 100, 0);
      LittleDoorButton.addBox(0F, 0F, 0F, 1, 1, 1);
      LittleDoorButton.setRotationPoint(-10F, 0F, -10F);
      LittleDoorButton.setTextureSize(512, 512);
      LittleDoorButton.mirror = true;
      setRotation(LittleDoorButton, 0F, 0F, 0F);
      LeftDoorButton = new ModelRenderer(this, 142, 0);
      LeftDoorButton.addBox(0F, 0F, 0F, 1, 1, 1);
      LeftDoorButton.setRotationPoint(6F, 5F, -10F);
      LeftDoorButton.setTextureSize(512, 512);
      LeftDoorButton.mirror = true;
      setRotation(LeftDoorButton, 0F, 0F, 0F);
      RightDoorButton = new ModelRenderer(this, 140, 0);
      RightDoorButton.addBox(0F, 0F, 0F, 1, 1, 1);
      RightDoorButton.setRotationPoint(9F, 5F, -10F);
      RightDoorButton.setTextureSize(512, 512);
      RightDoorButton.mirror = true;
      setRotation(RightDoorButton, 0F, 0F, 0F);
  }
  
  @Override
public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    Boottom.render(f5);
    WallLeft.render(f5);
    WallRight.render(f5);
    Top.render(f5);
    MiddleWall.render(f5);
    drawer2Front.render(f5);
    drawer1Front.render(f5);
    LittleDoor.render(f5);
    DrawerButton1.render(f5);
    DrawerButton2.render(f5);
    LeftDoor.render(f5);
    RightDoor.render(f5);
    BackWall.render(f5);
    LittleDoorButton.render(f5);
    LeftDoorButton.render(f5);
    RightDoorButton.render(f5);
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
