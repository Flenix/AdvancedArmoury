package com.silvaniastudios.advancedarmoury.items.components.pistol;

import com.silvaniastudios.advancedarmoury.AdvancedArmoury;
import com.silvaniastudios.advancedarmoury.items.components.generic.ItemComponent;

public class PistolSlide extends ItemComponent {

	public PistolSlide(String componentName, String displayName) {
		super(componentName, displayName, "Semi-Auto Pistol Component X", 10);
		this.setCreativeTab(AdvancedArmoury.tabComponentsExternal);
	}
}
