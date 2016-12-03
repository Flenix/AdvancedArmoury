package com.silvaniastudios.advancedarmoury.items.components.generic;

import com.silvaniastudios.advancedarmoury.AdvancedArmoury;

public class Trigger extends ItemComponent {
	
	public Trigger(String componentName, String displayName) {
		super(componentName, displayName, "Receiver Component G", 2, false, true, false);
		this.setCreativeTab(AdvancedArmoury.tabComponentsInternal);
	}

}
