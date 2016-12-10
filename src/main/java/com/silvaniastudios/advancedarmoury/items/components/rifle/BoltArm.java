package com.silvaniastudios.advancedarmoury.items.components.rifle;

import com.silvaniastudios.advancedarmoury.AdvancedArmoury;
import com.silvaniastudios.advancedarmoury.items.components.generic.ItemComponent;

public class BoltArm extends ItemComponent {

	public BoltArm(String componentName, String displayName) {
		super(componentName, displayName, "Rifle Component", 7, false, true, false, "This attaches to the bolt, allowing manual control over the bolt.\nPulling it back will eject the old shell casing, \nand chamber a new cartridge.");
		this.setCreativeTab(AdvancedArmoury.tabComponentsExternal);
	}
}
