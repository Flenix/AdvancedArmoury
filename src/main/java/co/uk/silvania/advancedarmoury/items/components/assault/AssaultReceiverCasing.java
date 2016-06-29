package co.uk.silvania.advancedarmoury.items.components.assault;

import co.uk.silvania.advancedarmoury.AdvancedArmoury;
import co.uk.silvania.advancedarmoury.items.generic.ReceiverCasing;

public class AssaultReceiverCasing extends ReceiverCasing {

	public AssaultReceiverCasing(String componentName, String displayName, String gunType,
			float xSize, float ySize, float zSize, 
			float barrelX, float barrelY, float barrelZ,
			float stockX, float stockY, float stockZ,
			float magX, float magY, float magZ,
			float attachmentX, float attachmentY, float attachmentZ,
			int magId, boolean topRail,
			String modelName, String modelTexture) {
		super(componentName, displayName, gunType, xSize, ySize, zSize, barrelX, barrelY, barrelZ, stockX, stockY, stockZ,
				magX, magY, magZ, attachmentX, attachmentY, attachmentZ, magId, topRail, modelName, modelTexture);
		this.setCreativeTab(AdvancedArmoury.tabComponentsAssault);
	}
}
