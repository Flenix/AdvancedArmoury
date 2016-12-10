package com.silvaniastudios.advancedarmoury.items.components.generic;

import com.silvaniastudios.advancedarmoury.AdvancedArmoury;

public class ChargingHandle extends ItemComponent {

	public ChargingHandle(String componentName, String displayName) {
		super(componentName, displayName, "Receiver Component F", 8, false, true, false, "This component is used to prepare a weapon for firing, \nmanually pulling a round into the chamber.");
		this.setCreativeTab(AdvancedArmoury.tabComponentsInternal);
	}

}
