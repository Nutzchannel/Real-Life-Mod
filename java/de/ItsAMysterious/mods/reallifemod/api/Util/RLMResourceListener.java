package de.ItsAMysterious.mods.reallifemod.api.Util;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import de.ItsAMysterious.mods.reallifemod.TLM;
import net.minecraft.client.resources.FolderResourcePack;
import net.minecraft.client.resources.IResource;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.client.resources.IResourceManagerReloadListener;
import net.minecraft.client.resources.SimpleReloadableResourceManager;
import net.minecraft.util.ResourceLocation;

public class RLMResourceListener implements IResourceManagerReloadListener, IResourceManager {

	@Override
	public void onResourceManagerReload(IResourceManager manager) {
		if(manager instanceof SimpleReloadableResourceManager)
        {
            SimpleReloadableResourceManager simplemanager = (SimpleReloadableResourceManager)manager;
            FolderResourcePack pack = new FolderResourcePack(TLM.Dir);
            //simplemanager.reloadResourcePack(pack);
        }

	}

	@Override
	public Set getResourceDomains() {
		return null;
	}

	@Override
	public IResource getResource(ResourceLocation p_110536_1_)
			throws IOException {
		return null;
	}

	@Override
	public List getAllResources(ResourceLocation p_135056_1_)
			throws IOException {
		return null;
	}

}
