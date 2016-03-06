package co.uk.silvania.advancedarmoury.items.components.asset.internal;

import co.uk.silvania.advancedarmoury.items.EnumMaterial;
import co.uk.silvania.advancedarmoury.items.components.asset.Receiver;

public class M4Receiver extends Receiver {
	
	static float xSize = 0.155F;
	static float ySize = 0.677F;
	static float zSize = 0.501F;
	
	static float frontEndX = -0.35F;
	static float frontEndY = 0.03F;
	static float frontEndZ = 0.0F;
	
	static float stockX = 0.175F;
	static float stockY = 0.035F;
	static float stockZ = 0.0F;
	
	static float triggerX = -0.052F;
	static float triggerY = -0.1F;
	static float triggerZ = 0.0F;
	
	static float magX = -0.24F;
	static float magY = -0.01F;
	static float magZ = 0.0F;
	
	static float attachmentX = 0.0F;
	static float attachmentY = 0.0F;
	static float attachmentZ = 0.0F;

	public M4Receiver(String name, String iconTexture, String gunType, EnumMaterial mat, boolean topRail) {
		super(name, "m4receiver", "m4receiver", iconTexture, gunType, mat, (int) Math.round((3.8*mat.durability)*100), (int) Math.round((3.8*mat.weight)/4),
				xSize, ySize, zSize,
				frontEndX, frontEndY, frontEndZ,
				stockX, stockY, stockZ,
				triggerX, triggerY, triggerZ,
				magX, magY, magZ,
				attachmentX, attachmentY, attachmentZ,
				0, topRail);
	}

}
