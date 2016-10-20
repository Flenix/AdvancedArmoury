package com.silvaniastudios.advancedarmoury.blocks.machines;

import com.silvaniastudios.advancedarmoury.items.generic.ItemComponent;
import com.silvaniastudios.advancedarmoury.items.generic.ReceiverFrame;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotComponent extends Slot {
	
	String componentName;
	boolean gunSpecific = false;
	
	public SlotComponent(IInventory inventory, int id, int x, int y, String name, boolean specific) {
		super (inventory, id, x, y);
		componentName = name;
		gunSpecific = specific;
	}
	
	@Override
	public boolean isItemValid(ItemStack item) {
		ItemStack itemReceiver = inventory.getStackInSlot(1);
		if (itemReceiver != null && item != null) {
			if (item != null) {
				if (item.getItem() instanceof ItemComponent && itemReceiver.getItem() instanceof ReceiverFrame) {
					ReceiverFrame receiver = (ReceiverFrame) itemReceiver.getItem();
					if (item.stackTagCompound != null) {
						String compName = item.stackTagCompound.getString("componentName").toLowerCase();
						String gunType = gunSpecific ? receiver.gunType : "";
						
						System.out.println("GunSpecific? " + gunSpecific + "Checking names. Item: " + compName + " must contain " + componentName + " and " + gunType);
						if (compName.contains(componentName.toLowerCase()) && compName.contains(gunType)) {
							return true;
						}
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
