package com.silvaniastudios.advancedarmoury.items.components.generic;

import com.silvaniastudios.advancedarmoury.AdvancedArmoury;

import net.minecraft.item.ItemStack;

public class BoltSmall extends ItemComponent {

	public BoltSmall(String componentName, String displayName) {
		super(componentName, displayName, "Receiver Component A", 9, false, true, false, "The component which moves the firing pin to \nstrike the cartridge and fire it. \nAffects RPS and Power.");
		this.setCreativeTab(AdvancedArmoury.tabComponentsInternal);
	}
}
