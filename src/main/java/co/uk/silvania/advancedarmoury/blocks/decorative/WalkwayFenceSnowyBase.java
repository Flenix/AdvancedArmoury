package co.uk.silvania.advancedarmoury.blocks.decorative;

import co.uk.silvania.advancedarmoury.client.ClientProxy;

public class WalkwayFenceSnowyBase extends WalkwayFenceMilitaryBase {

	public WalkwayFenceSnowyBase(String textureName, String blockType) {
		super(textureName, blockType);
	}
	
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
}
