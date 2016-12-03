package com.silvaniastudios.advancedarmoury.items.components.pistol;

import com.silvaniastudios.advancedarmoury.AdvancedArmoury;
import com.silvaniastudios.advancedarmoury.items.components.generic.ReceiverCasing;

public class SemiAutoPistolLowerFrame extends ReceiverCasing {

	public SemiAutoPistolLowerFrame(String componentName, String displayName, float xSize,
			float ySize, float zSize, float barrelX, float barrelY, float barrelZ, float stockX, float stockY,
			float stockZ, float magX, float magY, float magZ, float attachmentX, float attachmentY, float attachmentZ,
			int magId, boolean topRail, String modelName, String modelTexture) {
		super("small", "semiauto", componentName, displayName, xSize, ySize, zSize, barrelX, barrelY, barrelZ, stockX, stockY,
				stockZ, magX, magY, magZ, attachmentX, attachmentY, attachmentZ, magId, topRail, modelName, modelTexture);
		this.setCreativeTab(AdvancedArmoury.tabComponentsExternal);
	}
}
