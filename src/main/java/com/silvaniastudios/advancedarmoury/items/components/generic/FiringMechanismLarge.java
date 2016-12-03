package com.silvaniastudios.advancedarmoury.items.components.generic;

import com.silvaniastudios.advancedarmoury.AdvancedArmoury;

public class FiringMechanismLarge extends ItemComponent {

	public FiringMechanismLarge(String componentName, String displayName) {
		super(componentName, displayName, "Receiver Component D", 60, false, false, false);
		this.setCreativeTab(AdvancedArmoury.tabComponentsInternal);
	}
}
