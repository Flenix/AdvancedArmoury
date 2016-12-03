package com.silvaniastudios.advancedarmoury.blocks.machines.assemblytable;

import com.silvaniastudios.advancedarmoury.items.components.generic.ReceiverCasing;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotReceiverCasing extends Slot {
	
	public SlotReceiverCasing(IInventory inventory, int id, int x, int y) {
		super (inventory, id, x, y);
	}
	
	@Override
	public boolean isItemValid(ItemStack item) {
		if (item != null) {
			if (item.getItem() instanceof ReceiverCasing) {
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
