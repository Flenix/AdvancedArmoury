package com.silvaniastudios.advancedarmoury.blocks.machines;

import com.silvaniastudios.advancedarmoury.items.ItemParts;
import com.silvaniastudios.advancedarmoury.items.components.generic.GunFrame;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotParts extends Slot {
	
	public SlotParts (IInventory inventory, int id, int x, int y) {
		super (inventory, id, x, y);
	}
	
	@Override
	public boolean isItemValid(ItemStack item) {
		if (item != null) {
			if (item.getItem() instanceof ItemParts) {
				return true;
			}
		}
		return false;
	}

}
