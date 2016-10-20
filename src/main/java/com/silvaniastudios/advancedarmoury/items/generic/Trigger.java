package com.silvaniastudios.advancedarmoury.items.generic;

import com.silvaniastudios.advancedarmoury.AdvancedArmoury;

public class Trigger extends ItemComponent {
	
	public Trigger(String componentName, String displayName) {
		super(componentName, displayName, "Receiver Component G", 5.25, false, false, false, true, false, false);
		this.setCreativeTab(AdvancedArmoury.tabComponentsGeneric);
	}

}
