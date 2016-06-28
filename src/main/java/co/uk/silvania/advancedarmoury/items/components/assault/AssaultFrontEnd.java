package co.uk.silvania.advancedarmoury.items.components.assault;

import co.uk.silvania.advancedarmoury.AdvancedArmoury;
import co.uk.silvania.advancedarmoury.items.generic.FrontEnd;

public class AssaultFrontEnd extends FrontEnd {

	public AssaultFrontEnd(String componentName, String displayName, String modelName, String modelTexture,
			String gunType, float xSize, float ySize, float zSize, boolean top, boolean left, boolean right, boolean under) {
		super(componentName, displayName, modelName, modelTexture, gunType, xSize, ySize, zSize, top, left, right, under);
		this.setCreativeTab(AdvancedArmoury.tabComponentsAssault);
	}
}
