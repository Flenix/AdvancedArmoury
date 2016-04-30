package co.uk.silvania.advancedarmoury.items.components.asset;

public class Stock extends ItemAsset {

	static double size = 3.7;
	
	public Stock(String name, String modelName, String modelTexture, String iconTexture, String gunType, int buildTime, int cost, float xSize, float ySize, float zSize, int weight, double durability, String material, String textCol) {
		super("\u00A79", "S", "stock", size, (int) Math.round((size*weight)/4), (int) Math.round((size*durability)*100), 0, material, textCol);
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
