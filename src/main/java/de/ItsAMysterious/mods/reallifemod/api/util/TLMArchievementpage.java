package de.ItsAMysterious.mods.reallifemod.api.util;

import net.minecraft.init.Blocks;
import net.minecraft.stats.Achievement;
import net.minecraftforge.common.AchievementPage;

public class TLMArchievementpage extends AchievementPage{

	public TLMArchievementpage(String name, Achievement[] achievements) {
		super(name, achievements);
		achievements[0]=new Achievement("tbme.kruppstahl", "", 0, 0, Blocks.dirt, achievements[0]);
	}

}
