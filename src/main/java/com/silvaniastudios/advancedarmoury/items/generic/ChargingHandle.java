package com.silvaniastudios.advancedarmoury.items.generic;

import com.silvaniastudios.advancedarmoury.AdvancedArmoury;

public class ChargingHandle extends ItemComponent {

	public ChargingHandle(String componentName, String displayName) {
		super(componentName, displayName, "Receiver Component F", 5.25, false, false, false, true, false, false);
		this.setCreativeTab(AdvancedArmoury.tabComponentsGeneric);
	}

}
