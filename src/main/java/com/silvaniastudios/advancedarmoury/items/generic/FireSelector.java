package com.silvaniastudios.advancedarmoury.items.generic;

import com.silvaniastudios.advancedarmoury.AdvancedArmoury;

public class FireSelector extends ItemComponent {
	
	public FireSelector(String componentName, String displayName) {
		super(componentName, displayName, "Receiver Component E", 5.25, false, false, false, true, false, false);
		this.setCreativeTab(AdvancedArmoury.tabComponentsGeneric);
	}

}
