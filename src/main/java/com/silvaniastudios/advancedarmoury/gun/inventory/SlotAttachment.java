package com.silvaniastudios.advancedarmoury.gun.inventory;

import com.silvaniastudios.advancedarmoury.IAttachment;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotAttachment extends Slot {
	
	String type;
	
	public SlotAttachment(IInventory inventory, int slotId, int xPos, int yPos, String type) {
		super(inventory, slotId, xPos, yPos);
		this.type = type;
	}
	
	@Override
    public boolean isItemValid(ItemStack item) {
    	if (item.getItem() instanceof IAttachment) {
    		return true;
    	}
		return false;
    }
	
	@Override
	public int getSlotStackLimit() {
		return 1;
	}

}
