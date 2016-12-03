package com.silvaniastudios.advancedarmoury.items.components.generic;

import com.silvaniastudios.advancedarmoury.AdvancedArmoury;

public class FiringMechanismSmall extends ItemComponent {

	public FiringMechanismSmall(String componentName, String displayName) {
		super(componentName, displayName, "Receiver Component D", 42, false, false, false);
		this.setCreativeTab(AdvancedArmoury.tabComponentsInternal);
	}
}
