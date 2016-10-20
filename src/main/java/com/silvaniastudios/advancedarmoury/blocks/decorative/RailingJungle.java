package com.silvaniastudios.advancedarmoury.blocks.decorative;

import com.silvaniastudios.advancedarmoury.client.ClientProxy;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.Entity;

public class RailingJungle extends Railing {

	public RailingJungle(String textureName, String blockType) {
		super(textureName, blockType);
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public int getRenderType() {
		return ClientProxy.railingJungleRenderID;
	}
	
	@Override
	public String getBlockNameFromMeta(int meta) {
		if (meta <= 3) {
			return "oneRailingJungle";
		} else if (meta <= 7) {
			return "rightAngleRailingJungle";
		} else if (meta <= 11) {
			return "threeRailingJungle";
		} else if (meta <= 13) {
			return "parallelRailingJungle";
		} else if (meta == 14) {
			return "squareRailingJungle";
		} else
			return "connectedRailingJungle";
	}
	
	public float getExplosionResistance(Entity p_149638_1_) {
        return 50F;
    }
}
