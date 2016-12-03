package com.silvaniastudios.advancedarmoury.items.components.rifle;

import com.silvaniastudios.advancedarmoury.AdvancedArmoury;
import com.silvaniastudios.advancedarmoury.items.components.generic.ItemComponent;

public class BoltArm extends ItemComponent {

	public BoltArm(String componentName, String displayName) {
		super(componentName, displayName, "Rifle Component", 7, false, true, false);
		this.setCreativeTab(AdvancedArmoury.tabComponentsExternal);
	}
}
