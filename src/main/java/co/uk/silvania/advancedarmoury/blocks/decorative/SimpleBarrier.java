package co.uk.silvania.advancedarmoury.blocks.decorative;

import co.uk.silvania.advancedarmoury.client.ClientProxy;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class SimpleBarrier extends WalkwayFence {

	public SimpleBarrier(String textureName) {
		super(textureName, "simpleBarrier");
	}

	@SideOnly(Side.CLIENT)
	@Override
	public int getRenderType() {
		return ClientProxy.simpleBarrierRenderID;
	}
	
	@Override
	public String getBlockNameFromMeta(int meta) {
		if (meta <= 3) {
			return "oneBarrier";
		} else if (meta <= 7) {
			return "rightAngleBarrier";
		} else if (meta <= 11) {
			return "threeBarrier";
		} else if (meta <= 13) {
			return "parallelBarrier";
		} else if (meta == 14) {
			return "squareBarrier";
		} else
			return "connectedBarrier";
	}
}
