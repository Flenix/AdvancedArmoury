package com.silvaniastudios.advancedarmoury.items.components.generic;

import com.silvaniastudios.advancedarmoury.AdvancedArmoury;

import net.minecraft.item.ItemStack;

public class Hammer extends ItemComponent {

	public Hammer(String componentName, String displayName) {
		super(componentName, displayName, "Receiver Component A", 5, false, true, false);
		this.setCreativeTab(AdvancedArmoury.tabComponentsInternal);
	}
}
