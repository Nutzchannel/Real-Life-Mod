package de.ItsAMysterious.mods.reallifemod.core.rendering.TileEntitys;

public class bankTE extends TileEntitySitting {
	private double heightToSubstract;

	@Override
	public double getHeightToSubstract() {
		return heightToSubstract;
	}

	@Override
	public void setHeightToSubstract(double heightToSubstract) {
		this.heightToSubstract = heightToSubstract;
	}
	
	
}
