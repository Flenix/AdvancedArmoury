package com.silvaniastudios.advancedarmoury.items.components.generic;

import com.silvaniastudios.advancedarmoury.AdvancedArmoury;

public class FiringPinSmall extends ItemComponent {

	public FiringPinSmall(String componentName, String displayName) {
		super(componentName, displayName, "Receiver Component: C", 14, true, false, false, "This component is moved by the bolt and strikes the cartridge, \nigniting the gunpowder and firing the bullet. \nIt affects a weapons power.");
		this.setCreativeTab(AdvancedArmoury.tabComponentsInternal);
	}

}
