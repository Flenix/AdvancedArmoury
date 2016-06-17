package co.uk.silvania.advancedarmoury.blocks.decorative;

import co.uk.silvania.advancedarmoury.client.ClientProxy;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.Entity;

public class RailingSnowy extends Railing {

	public RailingSnowy(String textureName, String blockType) {
		super(textureName, blockType);
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public int getRenderType() {
		return ClientProxy.railingSnowyRenderID;
	}
	
	@Override
	public String getBlockNameFromMeta(int meta) {
		if (meta <= 3) {
			return "oneRailingSnowy";
		} else if (meta <= 7) {
			return "rightAngleRailingSnowy";
		} else if (meta <= 11) {
			return "threeRailingSnowy";
		} else if (meta <= 13) {
			return "parallelRailingSnowy";
		} else if (meta == 14) {
			return "squareRailingSnowy";
		} else
			return "connectedRailingSnowy";
	}
	
	public float getExplosionResistance(Entity p_149638_1_) {
        return 50F;
    }
}
