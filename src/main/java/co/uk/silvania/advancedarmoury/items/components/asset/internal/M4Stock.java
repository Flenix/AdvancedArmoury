package co.uk.silvania.advancedarmoury.items.components.asset.internal;

import co.uk.silvania.advancedarmoury.items.components.asset.Stock;

public class M4Stock extends Stock {
	
	static float xSize;
	static float ySize;
	static float zSize;

	public M4Stock(String name, String iconTexture, String gunType, String materialName, double dura, int weight, String textCol) {
		super(name, "", "", iconTexture, gunType, xSize, ySize, zSize, weight, dura, materialName, textCol);
	}

}
