package de.ItsAMysterious.mods.reallifemod.api.CustomMusic;

import net.minecraft.client.audio.ISound;
import net.minecraft.util.ResourceLocation;

public class EngineSound implements ISound{

	@Override
	public ResourceLocation getPositionedSoundLocation() {
		return new ResourceLocation("tlm:diesel");
	}

	@Override
	public boolean canRepeat() {
		return true;
	}

	@Override
	public int getRepeatDelay() {
		return 1;
	}

	@Override
	public float getVolume() {
		// TODO Auto-generated method stub
		return 100;
	}

	@Override
	public float getPitch() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float getXPosF() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float getYPosF() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float getZPosF() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public AttenuationType getAttenuationType() {
		return null;
	}

}
