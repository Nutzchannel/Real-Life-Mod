package de.ItsAMysterious.mods.reallifemod.api.Utils;

import java.io.File;
import net.minecraft.client.resources.AbstractResourcePack;

public class RLMResourceHelper
{

    public RLMResourceHelper()
    {
    }

    public static File getPackFile(AbstractResourcePack pack)
    {
        return new File(pack.toString());
    }
}