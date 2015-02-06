package de.ItsAMysterious.mods.reallifemod.api.Utils;

import java.io.File;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.AbstractResourcePack;
import net.minecraft.client.resources.FallbackResourceManager;
import net.minecraft.client.resources.IResourcePack;
import net.minecraft.client.resources.ResourcePackRepository;
import net.minecraft.client.resources.SimpleReloadableResourceManager;

import org.apache.commons.lang3.StringUtils;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.ModContainer;
import cpw.mods.fml.common.ObfuscationReflectionHelper;

public class ResourceLoader {
	private String folder;
	public HashSet folders;
	public HashSet files;
	public String extensions[];
	private int depth;
	private boolean isRoot;
	
	public ResourceLoader(String folder, String extensions[]) 
	{
	     folders = new HashSet();
	        files = new HashSet();
	        this.extensions = extensions;
	        setFolder(folder);
	    }

	    public void setFolder(String folder)
	    {
	        if(!folder.endsWith("/"))
	            folder = (new StringBuilder()).append(folder).append("/").toString();
	        isRoot = folder.length() <= 1;
	        this.folder = (new StringBuilder()).append("/assets").append(folder).toString();
	        depth = StringUtils.countMatches(this.folder, "/");
	        getFiles();
	    }

	    public ResourceLoader(String extensions[])
	    {
	        folders = new HashSet();
	        files = new HashSet();
	        this.extensions = extensions;
	    }

	    private void getFiles()
	    {
	        folders.clear();
	        files.clear();
	        ResourcePackRepository repos = Minecraft.getMinecraft().getResourcePackRepository();
	        SimpleReloadableResourceManager simplemanager = (SimpleReloadableResourceManager)Minecraft.getMinecraft().getResourceManager();
	        Map map = (Map)ObfuscationReflectionHelper.getPrivateValue(net.minecraft.client.resources.SimpleReloadableResourceManager.class, simplemanager, 2);
	        HashSet set = new HashSet();
	        Iterator iterator;
	        for(iterator = map.keySet().iterator(); iterator.hasNext();)
	        {
	            String name = (String)iterator.next();
	            if(map.get(name) instanceof FallbackResourceManager)
	            {
	                FallbackResourceManager manager = (FallbackResourceManager)map.get(name);
	                List list = (List)ObfuscationReflectionHelper.getPrivateValue(net.minecraft.client.resources.FallbackResourceManager.class, manager, 0);
	                Iterator iterator2 = list.iterator();
	                while(iterator2.hasNext()) 
	                {
	                    IResourcePack pack = (IResourcePack)iterator2.next();
	                    if(pack instanceof AbstractResourcePack)
	                    {
	                        AbstractResourcePack p = (AbstractResourcePack)pack;
	                        File file = RLMResourceHelper.getPackFile(p);
	                        set.add(file.getAbsolutePath());
	                    }
	                }
	            }
	        }

	        String file;
	        for(iterator = set.iterator(); iterator.hasNext(); progressFile(new File(file)))
	            file = (String)iterator.next();

	        iterator = Loader.instance().getModList().iterator();
	        do
	        {
	            if(!iterator.hasNext())
	                break;
	            ModContainer mod = (ModContainer)iterator.next();
	            if(mod.getSource().exists())
	                progressFile(mod.getSource());
	        } while(true);
	    }

	    private void progressFile(File file)
	    {
	        try
	        {
	            if(!file.isDirectory() && (file.getName().endsWith(".jar") || file.getName().endsWith(".zip")))
	            {
	                ZipFile zip = new ZipFile(file);
	                String entryName;
	                for(Enumeration entries = zip.entries(); entries.hasMoreElements(); checkFile(entryName))
	                {
	                    ZipEntry zipentry = (ZipEntry)entries.nextElement();
	                    entryName = zipentry.getName();
	                }

	                zip.close();
	            } else
	            if(file.isDirectory())
	            {
	                int length = file.getAbsolutePath().length();
	                checkFolder(file, length);
	            }
	        }
	        catch(Exception e)
	        {
	            e.printStackTrace();
	        }
	    }

	    private void checkFolder(File file, int length)
	    {
	        File files[] = file.listFiles();
	        if(files == null)
	            return;
	        File arr$[] = files;
	        int len$ = arr$.length;
	        for(int i$ = 0; i$ < len$; i$++)
	        {
	            File f = arr$[i$];
	            String name = f.getAbsolutePath().substring(length);
	            name = name.replace("\\", "/");
	            if(!name.startsWith("/"))
	                name = (new StringBuilder()).append("/").append(name).toString();
	            if(f.isDirectory() && (folder.startsWith(name) || name.startsWith(folder)))
	            {
	                checkFile((new StringBuilder()).append(name).append("/").toString());
	                checkFolder(f, length);
	            } else
	            {
	                checkFile(name);
	            }
	        }

	    }

	    private void checkFile(String name)
	    {
	        if(!name.startsWith("/"))
	            name = (new StringBuilder()).append("/").append(name).toString();
	        if(!name.startsWith(folder))
	            return;
	        String split[] = name.split("/");
	        int count = split.length;
	        if(count == depth + 1)
	        {
	            if(validExtension(name))
	                files.add(split[depth]);
	        } else
	        if(depth + 1 < count)
	            folders.add(split[depth]);
	    }

	    private boolean validExtension(String entryName)
	    {
	        int index = entryName.indexOf(".");
	        if(index < 0)
	            return false;
	        String extension = entryName.substring(index + 1);
	        String arr$[] = extensions;
	        int len$ = arr$.length;
	        for(int i$ = 0; i$ < len$; i$++)
	        {
	            String ex = arr$[i$];
	            if(ex.equalsIgnoreCase(extension))
	                return true;
	        }

	        return false;
	    }

	    public String getAsset(String asset)
	    {
	        String split[] = folder.split("/");
	        if(split.length < 3)
	        {
	            return null;
	        } else
	        {
	            String texture = (new StringBuilder()).append(split[2]).append(":").toString();
	            texture = (new StringBuilder()).append(texture).append(folder.substring(texture.length() + 8)).append(asset).toString();
	            return texture;
	        }
	    }
	
    public static String getRoot(String asset)
    {
        String mod = "minecraft";
        int index = asset.indexOf(":");
        if(index > 0)
        {
            mod = asset.substring(0, index);
            asset = asset.substring(index + 1);
        }
        if(asset.startsWith("/"))
            asset = asset.substring(1);
        String location = (new StringBuilder()).append("/").append(mod).append("/").append(asset).toString();
        index = location.lastIndexOf("/");
        if(index > 0)
            location = location.substring(0, index);
        return location;
    }

}
