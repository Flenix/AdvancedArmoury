package co.uk.silvania.advancedarmoury.gun.inventory;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ItemContainer extends Container {
	
	public void bindPlayerInventory(InventoryPlayer invPlayer, int xPos, int yPos) {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 9; j++) {
				this.addSlotToContainer(new Slot(invPlayer, j + i * 9 + 9, xPos + j * 18, yPos - 58 + i * 18));
			}
		}
		
		for (int i = 0; i < 9; i++) {
			this.addSlotToContainer(new Slot(invPlayer, i, xPos + i * 18, yPos));
		}
	}
	
	@Override
	public boolean canInteractWith(EntityPlayer player) {
		return true;
	}
	
	@Override
	public ItemStack transferStackInSlot(EntityPlayer player, int slotId) {
		return null;
	}
}
