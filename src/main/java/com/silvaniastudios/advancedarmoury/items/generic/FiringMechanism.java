package com.silvaniastudios.advancedarmoury.items.generic;

import com.silvaniastudios.advancedarmoury.AdvancedArmoury;

public class FiringMechanism extends ItemComponent {

	public FiringMechanism(String componentName, String displayName) {
		super(componentName, displayName, "Receiver Component D", 5.0, false, true, true, false, true, false);
		this.setCreativeTab(AdvancedArmoury.tabComponentsGeneric);
	}
}
