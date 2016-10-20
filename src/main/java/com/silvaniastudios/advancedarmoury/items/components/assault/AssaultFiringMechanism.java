package com.silvaniastudios.advancedarmoury.items.components.assault;

import com.silvaniastudios.advancedarmoury.AdvancedArmoury;
import com.silvaniastudios.advancedarmoury.items.generic.FiringMechanism;

public class AssaultFiringMechanism extends FiringMechanism {

	public AssaultFiringMechanism(String componentName, String displayName) {
		super(componentName, displayName);
		this.setCreativeTab(AdvancedArmoury.tabComponentsAssault);
	}

}
