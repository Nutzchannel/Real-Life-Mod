package de.ItsAMysterious.mods.reallifemod.api.rendering.obj;

import java.nio.*;
import org.lwjgl.opengl.GL11;

public class GLMaterial
{

    public GLMaterial()
    {
        mtlname = "noname";
        textureFile = null;
        setDefaults();
    }

    public GLMaterial(float color[])
    {
        mtlname = "noname";
        textureFile = null;
        setDefaults();
        setColor(color);
    }

    public void setDefaults()
    {
        setDiffuse(colorDefaultDiffuse);
        setAmbient(colorDefaultAmbient);
        setSpecular(colorNone);
        setEmission(colorNone);
        setShininess(0.0F);
    }

    public void setDiffuse(float color[])
    {
        diffuse = allocFloats(color);
    }

    public void setAmbient(float color[])
    {
        ambient = allocFloats(color);
    }

    public void setSpecular(float color[])
    {
        specular = allocFloats(color);
    }

    public void setEmission(float color[])
    {
        emission = allocFloats(color);
    }

    public void setShininess(float howShiny)
    {
        if(howShiny >= 0.0F && howShiny <= 127F)
        {
            float tmp[] = {
                howShiny, 0.0F, 0.0F, 0.0F
            };
            shininess = allocFloats(tmp);
        }
    }

    public void apply()
    {
        GL11.glMaterial(1028, 4609, diffuse);
        GL11.glMaterial(1028, 4608, ambient);
        GL11.glMaterial(1028, 4610, specular);
        GL11.glMaterial(1028, 5632, emission);
        GL11.glMaterial(1028, 5633, shininess);
    }

    public static void clear()
    {
        GL11.glMaterial(1028, 4609, defaultDiffuse);
        GL11.glMaterial(1028, 4608, defaultAmbient);
        GL11.glMaterial(1028, 4610, defaultSpecular);
        GL11.glMaterial(1028, 5632, defaultEmission);
        GL11.glMaterial(1028, 5633, defaultShine);
    }

    public void setColor(float color[])
    {
        setDiffuse(color);
        setAmbient(color);
    }

    public void setReflection(float intensity, float highlight)
    {
        float color[] = {
            intensity, intensity, intensity, 1.0F
        };
        setSpecular(color);
        setShininess((int)(highlight * 127F));
    }

    public void setGlowColor(float color[])
    {
        emission = allocFloats(color);
    }

    public void setAlpha(float alphaVal)
    {
        diffuse.put(3, alphaVal);
    }

    public float getAlpha()
    {
        return diffuse.get(3);
    }

    public void setTextureFile(String s)
    {
        textureFile = s;
    }

    public void setTexture(int txtrHandle)
    {
        textureHandle = txtrHandle;
    }

    public String getTextureFile()
    {
        return textureFile;
    }

    public int getTexture()
    {
        return textureHandle;
    }

    public void setName(String s)
    {
        mtlname = s;
    }

    public String getName()
    {
        return mtlname;
    }

    public static FloatBuffer allocFloats(int howmany)
    {
        return ByteBuffer.allocateDirect(howmany * 4).order(ByteOrder.nativeOrder()).asFloatBuffer();
    }

    public static FloatBuffer allocFloats(float floatarray[])
    {
        FloatBuffer fb = ByteBuffer.allocateDirect(floatarray.length * 4).order(ByteOrder.nativeOrder()).asFloatBuffer();
        fb.put(floatarray).flip();
        return fb;
    }

    public static final float colorClear[] = {
        0.0F, 0.0F, 0.0F, 0.0F
    };
    public static final float colorNone[] = {
        0.0F, 0.0F, 0.0F, 1.0F
    };
    public static final float colorRed[] = {
        1.0F, 0.0F, 0.0F, 1.0F
    };
    public static final float colorGreen[] = {
        0.0F, 1.0F, 0.0F, 1.0F
    };
    public static final float colorBlue[] = {
        0.0F, 0.0F, 1.0F, 1.0F
    };
    public static final float colorYellow[] = {
        1.0F, 1.0F, 0.0F, 1.0F
    };
    public static final float colorCyan[] = {
        0.0F, 1.0F, 1.0F, 1.0F
    };
    public static final float colorMagenta[] = {
        1.0F, 0.0F, 1.0F, 1.0F
    };
    public static final float colorGrayLight[] = {
        0.8F, 0.8F, 0.8F, 1.0F
    };
    public static final float colorGrayMedium[] = {
        0.5F, 0.5F, 0.5F, 1.0F
    };
    public static final float colorGrayDark[] = {
        0.2F, 0.2F, 0.2F, 1.0F
    };
    public static final float colorWhite[] = {
        1.0F, 1.0F, 1.0F, 1.0F
    };
    public static final float colorBlack[] = {
        0.0F, 0.0F, 0.0F, 1.0F
    };
    public static final float colorBeige[] = {
        0.7F, 0.7F, 0.4F, 1.0F
    };
    public static final float colorDefaultDiffuse[] = {
        0.8F, 0.8F, 0.8F, 1.0F
    };
    public static final float colorDefaultAmbient[] = {
        0.2F, 0.2F, 0.2F, 1.0F
    };
    public static final float minShine = 0F;
    public static final float maxShine = 127F;
    private static FloatBuffer defaultDiffuse = allocFloats(colorDefaultDiffuse);
    private static FloatBuffer defaultAmbient = allocFloats(colorDefaultAmbient);
    private static FloatBuffer defaultSpecular = allocFloats(colorNone);
    private static FloatBuffer defaultEmission = allocFloats(colorNone);
    private static FloatBuffer defaultShine = allocFloats(new float[] {
        0.0F, 0.0F, 0.0F, 0.0F
    });
    public FloatBuffer diffuse;
    public FloatBuffer ambient;
    public FloatBuffer specular;
    public FloatBuffer emission;
    public FloatBuffer shininess;
    public String mtlname;
    public String textureFile;
    public int textureHandle;
    public static final int SIZE_FLOAT = 4;

}