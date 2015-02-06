package de.ItsAMysterious.mods.reallifemod.api.Renderer.obj;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;


public class GLMaterialLib
{

    public GLMaterialLib(String mtlFilename)
    {
        filepath = "";
        filename = "";
        if(mtlFilename != null && mtlFilename.length() > 0)
            materials = loadMaterials(mtlFilename);
    }

    public GLMaterial[] loadMaterials(String mtlFilename)
    {
        GLMaterial mtls[] = null;
        File f=new File(mtlFilename);
        String[] pathParts=f.list(); 
        filepath = pathParts[0];
        filename = pathParts[1];
        try
        {
            BufferedReader reader = new BufferedReader(new FileReader(new File(f,mtlFilename)));
            mtls = loadMaterials(reader);
            reader.close();
        }
        catch(Exception e)
        {
            System.out.println((new StringBuilder()).append("GLMaterialLib.loadMaterials(): Exception when loading ").append(mtlFilename).append(": ").append(e).toString());
        }
        return mtls;
    }

    public GLMaterial[] loadMaterials(BufferedReader br)
    {
        ArrayList mtlslist = new ArrayList();
        GLMaterial material = null;
        String line = "";
        try
        {
            do
            {
                if((line = br.readLine()) == null)
                    break;
                line = line.trim();
                float rgb[];
                if(line.length() > 0 && !line.startsWith("#"))
                    if(line.startsWith("newmtl"))
                    {
                        material = new GLMaterial();
                        material.setName(line.substring(7));
                        mtlslist.add(material);
                    } else
                    if(line.startsWith("Kd"))
                    {
                        if((rgb = read3Floats(line)) != null)
                            material.setDiffuse(rgb);
                    } else
                    if(line.startsWith("Ka"))
                    {
                        if((rgb = read3Floats(line)) != null)
                            material.setAmbient(rgb);
                    } else
                    if(line.startsWith("Ks"))
                    {
                        if((rgb = read3Floats(line)) != null)
                            material.setSpecular(rgb);
                    } else
                    if(line.startsWith("Ns"))
                    {
                        if((rgb = read3Floats(line)) != null)
                        {
                            int shininessValue = (int)((rgb[0] / 1000F) * 127F);
                            material.setShininess(shininessValue);
                        }
                    } else
                    if(line.startsWith("d"))
                    {
                        if((rgb = read3Floats(line)) != null)
                            material.setAlpha(rgb[0]);
                    } else
                    if(line.startsWith("illum"))
                    {
                        if((rgb = read3Floats(line)) == null);
                    } else
                    if(line.startsWith("map_Kd"))
                    {
                        String textureFile = line.substring(7);
                        if(textureFile != null && !textureFile.equals(""))
                        {
                            int textureHandle = 0;
                            try
                            {
                                //textureHandle = GLApp.makeTexture((new StringBuilder()).append(filepath).append(textureFile).toString(), true, true);
                            }
                            catch(Exception e)
                            {
                                System.out.println((new StringBuilder()).append("GLMaterialLib.loadMaterials(): could not load texture file (").append(line).append(")").append(e).toString());
                            }
                            material.setTextureFile(textureFile);
                            material.setTexture(textureHandle);
                        }
                    }
            } while(true);
        }
        catch(Exception e)
        {
            System.out.println((new StringBuilder()).append("GLMaterialLib.loadMaterials() failed at line: ").append(line).toString());
        }
        System.out.println((new StringBuilder()).append("GLMaterialLib.loadMaterials(): loaded ").append(mtlslist.size()).append(" materials ").toString());
        GLMaterial mtls[] = new GLMaterial[mtlslist.size()];
        mtlslist.toArray(mtls);
        return mtls;
    }

    private float[] read3Floats(String line)
    {
        StringTokenizer st;
        st = new StringTokenizer(line, " ");
        st.nextToken();
        if(st.countTokens() == 1)
            return (new float[] {
                Float.parseFloat(st.nextToken()), 0.0F, 0.0F, 0.0F
            });
        try
        {
            if(st.countTokens() == 3)
                return (new float[] {
                    Float.parseFloat(st.nextToken()), Float.parseFloat(st.nextToken()), Float.parseFloat(st.nextToken()), 1.0F
                });
        }
        catch(Exception e)
        {
            System.out.println((new StringBuilder()).append("GLMaterialLib.read3Floats(): error on line '").append(line).append("', ").append(e).toString());
        }
        return null;
    }

    public void writeLibe(GLMaterial mtls[], String filename)
    {
        try
        {
            PrintWriter mtlfile = new PrintWriter(new FileWriter(filename));
            writeLibe(mtls, mtlfile);
            mtlfile.close();
        }
        catch(IOException e)
        {
            System.out.println((new StringBuilder()).append("GLMaterialLib.writeLibe(): IOException:").append(e).toString());
        }
    }

    public void writeLibe(GLMaterial mtls[], PrintWriter out)
    {
        if(out != null)
        {
            out.println("#");
            out.println("# Wavefront material file for use with OBJ file");
            out.println("# Created by GLMaterialLib.java");
            out.println("#");
            out.println("");
            for(int i = 0; i < mtls.length; i++)
                write(out, mtls[i]);

        }
    }

    public void write(PrintWriter out, GLMaterial mtl)
    {
        if(out != null)
        {
            out.println((new StringBuilder()).append("newmtl ").append(mtl.mtlname).toString());
            out.println((new StringBuilder()).append("Ka ").append(mtl.ambient.get(0)).append(" ").append(mtl.ambient.get(1)).append(" ").append(mtl.ambient.get(2)).toString());
            out.println((new StringBuilder()).append("Kd ").append(mtl.diffuse.get(0)).append(" ").append(mtl.diffuse.get(1)).append(" ").append(mtl.diffuse.get(2)).toString());
            out.println((new StringBuilder()).append("Ks ").append(mtl.specular.get(0)).append(" ").append(mtl.specular.get(1)).append(" ").append(mtl.specular.get(2)).toString());
            out.println((new StringBuilder()).append("Ns ").append((mtl.shininess.get(0) / 128D) * 1000D).toString());
            if(mtl.textureFile != null && !mtl.textureFile.equals(""))
                out.println((new StringBuilder()).append("map_Kd ").append(mtl.textureFile).toString());
            if(mtl.getAlpha() != 1.0F)
                out.println((new StringBuilder()).append("d ").append(mtl.getAlpha()).toString());
            out.println("");
        }
    }

    public GLMaterial getClone(GLMaterial mtl)
    {
        GLMaterial clone = new GLMaterial();
        clone.setDiffuse(new float[] {
            mtl.diffuse.get(0), mtl.diffuse.get(1), mtl.diffuse.get(2), mtl.diffuse.get(3)
        });
        clone.setAmbient(new float[] {
            mtl.ambient.get(0), mtl.ambient.get(1), mtl.ambient.get(2), mtl.ambient.get(3)
        });
        clone.setSpecular(new float[] {
            mtl.specular.get(0), mtl.specular.get(1), mtl.specular.get(2), mtl.specular.get(3)
        });
        clone.setGlowColor(new float[] {
            mtl.emission.get(0), mtl.emission.get(1), mtl.emission.get(2), mtl.emission.get(3)
        });
        clone.setShininess(mtl.shininess.get(0));
        clone.textureFile = mtl.textureFile;
        clone.textureHandle = mtl.textureHandle;
        clone.setName((new StringBuilder()).append(mtl.mtlname).append("-copy").toString());
        return clone;
    }

    public GLMaterial find(String materialName)
    {
        int mtl_idx = findID(materialName);
        if(mtl_idx >= 0)
            return materials[mtl_idx];
        else
            return null;
    }

    public int findID(String materialName)
    {
        if(materials != null && materialName != null)
        {
            for(int m = 0; m < materials.length; m++)
                if(materials[m].mtlname.equals(materialName))
                    return m;

        }
        return -1;
    }

    public String filepath;
    public String filename;
    GLMaterial materials[];
}