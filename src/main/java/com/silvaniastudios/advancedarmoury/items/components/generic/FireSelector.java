package com.silvaniastudios.advancedarmoury.items.components.generic;

import com.silvaniastudios.advancedarmoury.AdvancedArmoury;

public class FireSelector extends ItemComponent {
	
	public FireSelector(String componentName, String displayName) {
		super(componentName, displayName, "Receiver Component E", 1, false, true, false, "This component allows the user to switch between fire modes. \nCombined with Firing Mechanism, this allows changing fire modes.");
		this.setCreativeTab(AdvancedArmoury.tabComponentsInternal);
	}

}
