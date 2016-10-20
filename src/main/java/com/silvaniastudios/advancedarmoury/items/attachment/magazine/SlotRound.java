package com.silvaniastudios.advancedarmoury.items.attachment.magazine;

import com.silvaniastudios.advancedarmoury.IMagazine;
import com.silvaniastudios.advancedarmoury.IRound;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotRound extends Slot {
	
	public SlotRound(IInventory inventory, int slotId, int xPos, int yPos) {
		super(inventory, slotId, xPos, yPos);
	}
	
	@Override
    public boolean isItemValid(ItemStack item) {
    	if (item.getItem() instanceof IRound) {
    		return true;
    	}
		return false;
    }

}
