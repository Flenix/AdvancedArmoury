package co.uk.silvania.advancedarmoury.blocks.decorative;

import co.uk.silvania.advancedarmoury.client.ClientProxy;

public class WalkwayFenceJungleBase extends WalkwayFenceMilitaryBase {
	
	public WalkwayFenceJungleBase(String textureName, String blockType) {
		super(textureName, blockType);
	}

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

}
