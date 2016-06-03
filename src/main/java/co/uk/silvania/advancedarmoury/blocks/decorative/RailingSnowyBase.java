package co.uk.silvania.advancedarmoury.blocks.decorative;

import co.uk.silvania.advancedarmoury.client.ClientProxy;

public class RailingSnowyBase extends RailingMilitaryBase {

	public RailingSnowyBase(String textureName, String blockType) {
		super(textureName, blockType);
	}
	
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
}
