package co.uk.silvania.advancedarmoury.items.components.asset.internal;

import co.uk.silvania.advancedarmoury.items.EnumMaterial;
import co.uk.silvania.advancedarmoury.items.components.asset.FrontEnd;

public class M4FrontEnd extends FrontEnd {
	
	static float xSize = 0.16F;
	static float ySize = 0.162F;
	static float zSize = 1.042F;

	public M4FrontEnd(String name, String iconTexture, String gunType, EnumMaterial material, boolean topRail, boolean leftRail, boolean rightRail, boolean bottomRail) {
		super(name, "", "", iconTexture, gunType, material, (int) Math.round((3.8*material.durability)*100), (int) Math.round((3.8*material.weight)/4), xSize, ySize, zSize, topRail, leftRail,
				rightRail, bottomRail);
	}

}
