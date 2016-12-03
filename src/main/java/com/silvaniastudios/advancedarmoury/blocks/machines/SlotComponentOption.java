package com.silvaniastudios.advancedarmoury.blocks.machines;

import com.silvaniastudios.advancedarmoury.items.components.generic.ItemComponent;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotComponentOption extends Slot {
	
	String partName1;
	String partName2;
	
	public SlotComponentOption(IInventory inventory, int id, int x, int y, String name1, String name2) {
		super (inventory, id, x, y);
		partName1 = name1;
		partName2 = name2;
	}
	
	@Override
	public boolean isItemValid(ItemStack item) {
		if (item != null) {
			if (item.getItem() instanceof ItemComponent) {
				if (item.stackTagCompound != null) {
					if (item.stackTagCompound.getString("partName").equalsIgnoreCase(partName1)) {
						return true;
					} else if (item.stackTagCompound.getString("partName").equalsIgnoreCase(partName2)) {
						return true;
					}
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
