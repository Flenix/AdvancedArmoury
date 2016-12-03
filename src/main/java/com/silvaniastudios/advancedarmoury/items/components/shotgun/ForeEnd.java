package com.silvaniastudios.advancedarmoury.items.components.shotgun;

import com.silvaniastudios.advancedarmoury.AdvancedArmoury;
import com.silvaniastudios.advancedarmoury.items.components.generic.ItemComponent;

public class ForeEnd extends ItemComponent {

	public ForeEnd(String componentName, String displayName) {
		super(componentName, displayName, "Pump-Action Shotgun Component", 40, false, true, false);
		this.setCreativeTab(AdvancedArmoury.tabComponentsExternal);
	}
}
