package co.uk.silvania.advancedarmoury.items.components.asset;

public class Stock extends ItemAsset {

	static double size = 14.6*0.75;
	
	public Stock(String name, String modelName, String modelTexture, String iconTexture, String gunType, float xSize, float ySize, float zSize, int weight, double durability, String material, String textCol) {
		super(gunType, "\u00A79", "S", "stock", size, material, textCol);
		this.displayName = name;
		this.modelName = modelName;
		this.modelTexture = modelTexture;
		this.iconTexture = iconTexture;
		//this.material = material;
		
		this.xSize = xSize;
		this.ySize = ySize;
		this.zSize = zSize;
	}

}
