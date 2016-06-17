package co.uk.silvania.advancedarmoury.blocks.decorative;

import co.uk.silvania.advancedarmoury.client.ClientProxy;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.Entity;

public class WalkwayFenceJungle extends WalkwayFence {
	
	public WalkwayFenceJungle(String textureName, String blockType) {
		super(textureName, blockType);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public int getRenderType() {
		return ClientProxy.walkwayFenceJungleRenderID;
	}
	
	@Override
	public String getBlockNameFromMeta(int meta) {
		if (meta <= 3) {
			return "oneFenceJungle";
		} else if (meta <= 7) {
			return "rightAngleFenceJungle";
		} else if (meta <= 11) {
			return "threeFenceJungle";
		} else if (meta <= 13) {
			return "parallelFenceJungle";
		} else if (meta == 14) {
			return "squareFenceJungle";
		} else
			return "connectedFenceJungle";
	}

	public float getExplosionResistance(Entity p_149638_1_) {
        return 50F;
    }
}
