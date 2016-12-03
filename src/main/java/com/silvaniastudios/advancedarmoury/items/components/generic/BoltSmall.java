package com.silvaniastudios.advancedarmoury.items.components.generic;

import com.silvaniastudios.advancedarmoury.AdvancedArmoury;

import net.minecraft.item.ItemStack;

public class BoltSmall extends ItemComponent {

	public BoltSmall(String componentName, String displayName) {
		super(componentName, displayName, "Receiver Component A", 9, false, true, false);
		this.setCreativeTab(AdvancedArmoury.tabComponentsInternal);
	}
}
