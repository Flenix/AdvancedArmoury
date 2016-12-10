package com.silvaniastudios.advancedarmoury.items.components.generic;

import com.silvaniastudios.advancedarmoury.AdvancedArmoury;

import net.minecraft.item.ItemStack;

public class Hammer extends ItemComponent {

	public Hammer(String componentName, String displayName) {
		super(componentName, displayName, "Receiver Component A", 5, false, true, false, "This small component is like a bolt in larger weapons; \nit moves the firing pin. \nIt affects both power and fire rate.");
		this.setCreativeTab(AdvancedArmoury.tabComponentsInternal);
	}
}
