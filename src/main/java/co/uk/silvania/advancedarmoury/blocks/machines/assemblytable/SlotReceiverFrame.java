package co.uk.silvania.advancedarmoury.blocks.machines.assemblytable;

import co.uk.silvania.advancedarmoury.items.generic.ReceiverFrame;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotReceiverFrame extends Slot {
	
	public SlotReceiverFrame(IInventory inventory, int id, int x, int y) {
		super (inventory, id, x, y);
	}
	
	@Override
	public boolean isItemValid(ItemStack item) {
		if (item != null) {
			if (item.getItem() instanceof ReceiverFrame) {
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
