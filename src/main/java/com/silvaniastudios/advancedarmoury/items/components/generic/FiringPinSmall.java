package com.silvaniastudios.advancedarmoury.items.components.generic;

import com.silvaniastudios.advancedarmoury.AdvancedArmoury;

public class FiringPinSmall extends ItemComponent {

	public FiringPinSmall(String componentName, String displayName) {
		super(componentName, displayName, "Receiver Component: C", 14, true, false, false);
		this.setCreativeTab(AdvancedArmoury.tabComponentsInternal);
	}

}
