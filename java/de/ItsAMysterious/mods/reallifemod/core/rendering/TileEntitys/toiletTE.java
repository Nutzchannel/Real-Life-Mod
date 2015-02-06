package de.ItsAMysterious.mods.reallifemod.core.rendering.TileEntitys;

import org.lwjgl.input.Keyboard;

public class toiletTE extends TileEntitySitting {
	
	@Override
	public void updateEntity(){
		if(this.isSitting()&&Keyboard.isKeyDown(Keyboard.KEY_P)){
			System.out.println("Pooping!");
		}
	}

}
