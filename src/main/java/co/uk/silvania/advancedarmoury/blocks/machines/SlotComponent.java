package co.uk.silvania.advancedarmoury.blocks.machines;

import co.uk.silvania.advancedarmoury.items.components.generic.ItemComponent;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotComponent extends Slot {
	
	String componentName;
	
	public SlotComponent(IInventory inventory, int id, int x, int y, String name) {
		super (inventory, id, x, y);
		componentName = name;
	}
	
	@Override
	public boolean isItemValid(ItemStack item) {
		if (item != null) {
			if (item.getItem() instanceof ItemComponent) {
				if (item.stackTagCompound != null) {
					if (item.stackTagCompound.getString("componentName").equalsIgnoreCase(componentName)) {
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
