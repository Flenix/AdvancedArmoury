package co.uk.silvania.advancedarmoury.blocks.decorative;

import co.uk.silvania.advancedarmoury.client.ClientProxy;

public class RailingMilitaryBase extends WalkwayFenceMilitaryBase {

	public RailingMilitaryBase(String textureName, String blockType) {
		super(textureName, blockType);
	}
	
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
}
