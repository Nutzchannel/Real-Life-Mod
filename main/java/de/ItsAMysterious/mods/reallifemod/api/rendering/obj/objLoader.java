package de.ItsAMysterious.mods.reallifemod.api.rendering.obj;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelFormatException;

public class objLoader {
	public static WavefrontObject loadModel(ResourceLocation resource)throws ModelFormatException
	{
		return new WavefrontObject(resource);
	}

}
