package co.uk.silvania.advancedarmoury.items.components.asset;

import co.uk.silvania.advancedarmoury.items.EnumMaterial;

public class Stock extends ItemAsset {

	static double size = 3.7;
	
	public Stock(String name, String modelName, String modelTexture, String iconTexture, String gunType, EnumMaterial material, int buildTime, int cost, float xSize, float ySize, float zSize) {
		super("\u00A79", "S", "stock", size, (int) Math.round((size*material.weight)/4), (int) Math.round((size*material.durability)*100), 0, material);
		this.displayName = name;
		this.modelName = modelName;
		this.modelTexture = modelTexture;
		this.iconTexture = iconTexture;
		this.gunType = gunType;
		this.material = material;
		this.buildTime = buildTime;
		this.cost = cost;
		
		this.xSize = xSize;
		this.ySize = ySize;
		this.zSize = zSize;
	}

}
