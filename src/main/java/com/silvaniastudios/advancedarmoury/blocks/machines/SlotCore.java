package com.silvaniastudios.advancedarmoury.blocks.machines;

import com.silvaniastudios.advancedarmoury.items.components.generic.GunFrame;
import com.silvaniastudios.advancedarmoury.items.modifiers.IModifierCore;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotCore extends Slot {
	
	public SlotCore(IInventory inventory, int id, int x, int y) {
		super (inventory, id, x, y);
	}
	
	@Override
	public boolean isItemValid(ItemStack item) {
		if (item != null) {
			if (item.getItem() instanceof IModifierCore) {
				return true;
			}
		}
		return false;
	}
	
	@Override
	public int getSlotStackLimit() {
		return 1;
	}
}
