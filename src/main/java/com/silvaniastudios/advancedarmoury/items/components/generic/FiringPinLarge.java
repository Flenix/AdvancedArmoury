package com.silvaniastudios.advancedarmoury.items.components.generic;

import com.silvaniastudios.advancedarmoury.AdvancedArmoury;

public class FiringPinLarge extends ItemComponent {

	public FiringPinLarge(String componentName, String displayName) {
		super(componentName, displayName, "Receiver Component: C", 29, true, false, false);
		this.setCreativeTab(AdvancedArmoury.tabComponentsInternal);
	}

}
