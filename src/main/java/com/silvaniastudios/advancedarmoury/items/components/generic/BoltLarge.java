package com.silvaniastudios.advancedarmoury.items.components.generic;

import com.silvaniastudios.advancedarmoury.AdvancedArmoury;

import net.minecraft.item.ItemStack;

public class BoltLarge extends ItemComponent {

	public BoltLarge(String componentName, String displayName) {
		super(componentName, displayName, "Receiver Component A", 20, false, true, false, "The component which moves the firing pin to \nstrike the cartridge and fire it. \nAffects RPS and Power.");
		this.setCreativeTab(AdvancedArmoury.tabComponentsInternal);
	}
}
