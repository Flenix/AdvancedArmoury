package co.uk.silvania.advancedarmoury.items.generic;

public class FrontEnd extends ItemComponent {
	
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
	
	public FrontEnd(String componentName, String displayName, String modelName, String modelTexture, String gunType, 
			float xSize, float ySize, float zSize, boolean topRail, boolean leftRail, boolean rightRail, boolean underRail) {
		super(componentName, displayName, "Receiver Component: F", 18);
		this.xSize = xSize;
		this.ySize = ySize;
		this.zSize = zSize;
		
		this.topRail = topRail;
		this.leftRail = leftRail;
		this.rightRail = rightRail;
		this.underRail = underRail;
	}
}
