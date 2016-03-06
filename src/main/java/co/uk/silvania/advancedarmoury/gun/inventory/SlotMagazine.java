package co.uk.silvania.advancedarmoury.gun.inventory;

import co.uk.silvania.advancedarmoury.IMagazine;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotMagazine extends Slot {

	public SlotMagazine(IInventory inventory, int slotId, int xPos, int yPos) {
		super(inventory, slotId, xPos, yPos);
	}
	
	@Override
    public boolean isItemValid(ItemStack item) {
    	if (item.getItem() instanceof IMagazine) {
    		return true;
    	}
		return false;
    }
	
	@Override
	public int getSlotStackLimit() {
		return 1;
	}
}
