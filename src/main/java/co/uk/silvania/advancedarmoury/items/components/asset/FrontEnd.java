package co.uk.silvania.advancedarmoury.items.components.asset;

public class FrontEnd extends ItemAsset{
	
	boolean topRail = false;
	boolean leftRail = false;
	boolean rightRail = false;
	boolean underRail = false;
	
	public float topRailX;
	public float topRailY;
	public float topRailZ;
	public float leftRailX;
	public float leftRailY;
	public float leftRailZ;
	public float rightRailX;
	public float rightRailY;
	public float rightRailZ;
	public float bottomRailX;
	public float bottomRailY;
	public float bottomRailZ;
	
	public float barrelAttachmentX;
	public float barrelAttachmentY;
	public float barrelAttachmentZ;
	
	public float ironSightX;
	public float ironSightY;
	public float ironSightZ;
	
	static double size = 3.4;

	public FrontEnd(String name, String modelName, String modelTexture, String iconTexture, String gunType, float xSize, float ySize, float zSize, 
			boolean topRail, boolean leftRail, boolean rightRail, boolean bottomRail, int weight, double durability, String materialName, String textCol) {
		super(gunType, "\u00A7c", "F", "frontEnd", size, materialName, textCol);
		this.displayName = name;
		this.modelName = modelName;
		this.modelTexture = modelTexture;
		this.iconTexture = iconTexture;
		
		this.xSize = xSize;
		this.ySize = ySize;
		this.zSize = zSize;
		
		this.topRail = topRail;
		this.leftRail = leftRail;
		this.rightRail = rightRail;
		this.underRail = bottomRail;
	}
}
