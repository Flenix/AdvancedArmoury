package com.silvaniastudios.advancedarmoury.blocks.machines.assemblytable;

import com.silvaniastudios.advancedarmoury.items.components.generic.ReceiverCasing;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotReceiverCasing extends Slot {
	
	boolean requireFilled; //True = receiver is full to be used in final gun. False = empty, to be filled.
	
	public SlotReceiverCasing(IInventory inventory, int id, int x, int y, boolean requireFilled) {
		super (inventory, id, x, y);
		this.requireFilled = requireFilled;
	}
	
	@Override
	public boolean isItemValid(ItemStack item) {
		if (item != null) {
			if (item.getItem() instanceof ReceiverCasing) {
				if (item.stackTagCompound.getBoolean("hasInternals") && requireFilled) {
					return true;
				} else if (!item.stackTagCompound.getBoolean("hasInternals") && !requireFilled) {
					return true;
				}
			}
		}
		return false;
	}
	
	@Override
	public int getSlotStackLimit() {
		return 1;
	}

}
