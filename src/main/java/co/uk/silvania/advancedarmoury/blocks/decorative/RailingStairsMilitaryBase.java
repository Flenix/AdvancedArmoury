package co.uk.silvania.advancedarmoury.blocks.decorative;

import co.uk.silvania.advancedarmoury.client.ClientProxy;

public class RailingStairsMilitaryBase extends WalkwayStairsMilitaryBase {

	public RailingStairsMilitaryBase(String textureName, String blockType) {
		super(textureName, blockType);
	}

	@Override
	public int getRenderType() {
		return ClientProxy.railingStairsRenderID;
	}
}
