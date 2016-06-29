package co.uk.silvania.advancedarmoury.items.components.assault;

import co.uk.silvania.advancedarmoury.AdvancedArmoury;
import co.uk.silvania.advancedarmoury.items.generic.Stock;

public class AssaultStock extends Stock {

	public AssaultStock(String componentName, String displayName, String modelName, String modelTexture, String gunType, float xSize, float ySize, float zSize) {
		super(componentName, displayName, modelName, modelTexture, gunType, xSize, ySize, zSize);
		this.setCreativeTab(AdvancedArmoury.tabComponentsAssault);
	}
}
