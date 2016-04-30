package co.uk.silvania.advancedarmoury.items.components.asset.internal;

import co.uk.silvania.advancedarmoury.items.components.asset.FrontEnd;

public class M4FrontEnd extends FrontEnd {
	
	static float xSize = 0.16F;
	static float ySize = 0.162F;
	static float zSize = 1.042F;

	public M4FrontEnd(String name, String iconTexture, String gunType, String materialName, double dura, int weight, String col, boolean topRail, boolean leftRail, boolean rightRail, boolean bottomRail) {
		super(name, "", "", iconTexture, gunType, (int) Math.round((3.8*dura)*100), (int) Math.round((3.8*weight)/4), xSize, ySize, zSize, topRail, leftRail,
				rightRail, bottomRail, weight, dura, materialName, col);
	}

}
