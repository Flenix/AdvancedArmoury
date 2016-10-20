package com.silvaniastudios.advancedarmoury.blocks.decorative;

import com.silvaniastudios.advancedarmoury.client.ClientProxy;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.IBlockAccess;

public class Railing extends WalkwayFence {

	public Railing(String textureName, String blockType) {
		super(textureName, blockType);
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public int getRenderType() {
		return ClientProxy.railingRenderID;
	}
	
	@Override
	public String getBlockNameFromMeta(int meta) {
		if (meta <= 3) {
			return "oneRailing";
		} else if (meta <= 7) {
			return "rightAngleRailing";
		} else if (meta <= 11) {
			return "threeRailing";
		} else if (meta <= 13) {
			return "parallelRailing";
		} else if (meta == 14) {
			return "squareRailing";
		} else
			return "connectedRailing";
	}
	
	@Override
    public boolean isLadder(IBlockAccess world, int x, int y, int z, EntityLivingBase entity) {
		if (world.getBlock(x, y+1, z) instanceof Railing || world.getBlock(x, y-1, z) instanceof Railing) {
			return true;
		}
		
        return false;
    }
	
	public float getExplosionResistance(Entity p_149638_1_) {
        return 50F;
    }
}
