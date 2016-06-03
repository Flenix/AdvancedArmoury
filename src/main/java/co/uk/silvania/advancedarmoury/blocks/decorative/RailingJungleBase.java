package co.uk.silvania.advancedarmoury.blocks.decorative;

import co.uk.silvania.advancedarmoury.client.ClientProxy;

public class RailingJungleBase extends RailingMilitaryBase {

	public RailingJungleBase(String textureName, String blockType) {
		super(textureName, blockType);
	}
	
	@Override
	public int getRenderType() {
		return ClientProxy.railingJungleRenderID;
	}
	
	@Override
	public String getBlockNameFromMeta(int meta) {
		if (meta <= 3) {
			return "oneRailingJungle";
		} else if (meta <= 7) {
			return "rightAngleRailingJungle";
		} else if (meta <= 11) {
			return "threeRailingJungle";
		} else if (meta <= 13) {
			return "parallelRailingJungle";
		} else if (meta == 14) {
			return "squareRailingJungle";
		} else
			return "connectedRailingJungle";
	}
}
