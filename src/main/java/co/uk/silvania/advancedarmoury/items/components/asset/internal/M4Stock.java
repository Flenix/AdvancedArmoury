package co.uk.silvania.advancedarmoury.items.components.asset.internal;

import co.uk.silvania.advancedarmoury.items.EnumMaterial;
import co.uk.silvania.advancedarmoury.items.components.asset.Stock;

public class M4Stock extends Stock {
	
	static float xSize;
	static float ySize;
	static float zSize;

	public M4Stock(String name, String iconTexture, String gunType, EnumMaterial material) {
		super(name, "", "", iconTexture, gunType, material, (int) Math.round((3.8*material.durability)*100), (int) Math.round((3.8*material.weight)/4), xSize, ySize, zSize);
	}

}
