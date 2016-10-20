package com.silvaniastudios.advancedarmoury.blocks.decorative;

import com.silvaniastudios.advancedarmoury.client.ClientProxy;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.Entity;

public class WalkwayFenceSnowy extends WalkwayFence {

	public WalkwayFenceSnowy(String textureName, String blockType) {
		super(textureName, blockType);
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public int getRenderType() {
		return ClientProxy.walkwayFenceSnowyRenderID;
	}
	
	@Override
	public String getBlockNameFromMeta(int meta) {
		if (meta <= 3) {
			return "oneFenceSnowy";
		} else if (meta <= 7) {
			return "rightAngleFenceSnowy";
		} else if (meta <= 11) {
			return "threeFenceSnowy";
		} else if (meta <= 13) {
			return "parallelFenceSnowy";
		} else if (meta == 14) {
			return "squareFenceSnowy";
		} else
			return "connectedFenceSnowy";
	}
	
	public float getExplosionResistance(Entity p_149638_1_) {
        return 50F;
    }
}
