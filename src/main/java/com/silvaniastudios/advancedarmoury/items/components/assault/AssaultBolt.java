package com.silvaniastudios.advancedarmoury.items.components.assault;

import com.silvaniastudios.advancedarmoury.AdvancedArmoury;
import com.silvaniastudios.advancedarmoury.items.generic.Bolt;

public class AssaultBolt extends Bolt {

	public AssaultBolt(String componentName, String displayName) {
		super(componentName, displayName);
		this.setCreativeTab(AdvancedArmoury.tabComponentsAssault);
	}
}
