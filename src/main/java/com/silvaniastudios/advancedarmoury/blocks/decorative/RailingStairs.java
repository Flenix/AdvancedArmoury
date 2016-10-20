package com.silvaniastudios.advancedarmoury.blocks.decorative;

import com.silvaniastudios.advancedarmoury.client.ClientProxy;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.Entity;

public class RailingStairs extends WalkwayStairs {

	public RailingStairs(String textureName, String blockType) {
		super(textureName, blockType);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public int getRenderType() {
		return ClientProxy.railingStairsRenderID;
	}
	
	public float getExplosionResistance(Entity p_149638_1_) {
        return 50F;
    }
}
