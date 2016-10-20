package com.silvaniastudios.advancedarmoury.items.components.assault;

import com.silvaniastudios.advancedarmoury.AdvancedArmoury;
import com.silvaniastudios.advancedarmoury.items.generic.FiringPin;

public class AssaultFiringPin extends FiringPin {

	public AssaultFiringPin(String componentName, String displayName) {
		super(componentName, displayName);
		this.setCreativeTab(AdvancedArmoury.tabComponentsAssault);
	}

}
