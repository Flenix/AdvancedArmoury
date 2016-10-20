package com.silvaniastudios.advancedarmoury.blocks.machines.assemblytable.assault;

import com.silvaniastudios.advancedarmoury.items.generic.ItemComponent;
import com.silvaniastudios.advancedarmoury.items.generic.ReceiverFrame;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotAssaultComponent extends Slot {
	
	String componentName;
	
	public SlotAssaultComponent(IInventory inventory, int id, int x, int y, String name) {
		super (inventory, id, x, y);
		componentName = name;
	}
	
	@Override
	public boolean isItemValid(ItemStack item) {
		if (item.stackTagCompound != null) {
			String compName = item.stackTagCompound.getString("componentName").toLowerCase();			
			if (compName.contains(componentName.toLowerCase())) {
				if (compName.equalsIgnoreCase("casing")) {
					if (!item.stackTagCompound.getBoolean("hasInternals")) {
						System.out.println("No internals have been set. Cannot use.");
						return false;
					}
				}
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
