package com.silvaniastudios.advancedarmoury.items.components.generic;

import com.silvaniastudios.advancedarmoury.AdvancedArmoury;

public class FiringMechanismSmall extends ItemComponent {

	public FiringMechanismSmall(String componentName, String displayName) {
		super(componentName, displayName, "Receiver Component D", 42, false, false, false, "This collection of pipes and springs automates the \nprocess of chambering a new round. \nSome allow for full-auto fire, some are semi-auto only.");
		this.setCreativeTab(AdvancedArmoury.tabComponentsInternal);
	}
}
