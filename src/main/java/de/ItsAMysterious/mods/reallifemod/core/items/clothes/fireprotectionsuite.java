package de.ItsAMysterious.mods.reallifemod.core.items.clothes;

import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class fireprotectionsuite extends ItemArmor{
	
	private String textureName;

	public fireprotectionsuite(String unlocalizedName, ArmorMaterial material, String textureName, int type) {
	    super(material, 25, type);
	    this.textureName = textureName;
	    this.setUnlocalizedName(unlocalizedName);
	    this.setTextureName("reallifemod:" + unlocalizedName);
	}
    
    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type)
    {
        return "reallifemod:textures/models/armor/fireprotectionsuite_" + (this.armorType == 2 ? "2" : "1") + ".png";
    }
    
    @Override
	public void onArmorTick(World world, EntityPlayer player, ItemStack itemStack) {
    	if (player.getCurrentArmor(1) != null && player.getCurrentArmor(1).getItem().getClass() == this.getClass()){
    		player.extinguish();
    		player.hurtResistantTime=1000;
    		player.fireResistance=1000;
    		if(player.isInsideOfMaterial(Material.lava)){
    			player.setHealth(player.getMaxHealth());
    		}else ;
    	}

    }

}
;