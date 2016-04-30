package co.uk.silvania.advancedarmoury.items.components.asset.internal;

import co.uk.silvania.advancedarmoury.items.components.asset.Stock;

public class M4Stock extends Stock {
	
	static float xSize;
	static float ySize;
	static float zSize;

	public M4Stock(String name, String iconTexture, String gunType, String materialName, double dura, int weight, String textCol) {
		super(name, "", "", iconTexture, gunType, (int) Math.round((3.8*dura)*100), (int) Math.round((3.8*weight)/4), xSize, ySize, zSize, weight, dura, materialName, textCol);
	}

}
