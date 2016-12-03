package com.silvaniastudios.advancedarmoury.items.components.generic;

import com.silvaniastudios.advancedarmoury.AdvancedArmoury;

import net.minecraft.item.ItemStack;

public class BoltLarge extends ItemComponent {

	public BoltLarge(String componentName, String displayName) {
		super(componentName, displayName, "Receiver Component A", 20, false, true, false);
		this.setCreativeTab(AdvancedArmoury.tabComponentsInternal);
	}
}
