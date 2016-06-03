package co.uk.silvania.advancedarmoury.blocks.machines;

import co.uk.silvania.advancedarmoury.items_old.components.generic.GunFrame;
import co.uk.silvania.advancedarmoury.items_old.components.generic.assault.ItemAssaultComponent_OLD;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotFrame extends Slot {
	
	public SlotFrame(IInventory inventory, int id, int x, int y) {
		super (inventory, id, x, y);
	}
	
	@Override
	public boolean isItemValid(ItemStack item) {
		if (item != null) {
			if (item.getItem() instanceof GunFrame) {
				if (item.stackTagCompound != null) {
					if (!item.stackTagCompound.getBoolean("completedFrame")) {
						return true;
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
