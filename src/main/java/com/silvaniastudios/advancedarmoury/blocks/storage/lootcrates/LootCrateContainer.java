package com.silvaniastudios.advancedarmoury.blocks.storage.lootcrates;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class LootCrateContainer extends Container {

	public LootCrateEntity tileEntity;
	
	public LootCrateContainer(InventoryPlayer player, LootCrateEntity te) {
		tileEntity = te;
		int a = te.getSizeInventory();
		
		addSlotToContainer(new Slot(tileEntity, 0, 152, 16)); //Lock key slot

		for (int i = 0; i < a-1; i++) { //Could've probably done this like bindPlayerInventory, but I'm too lazy.
									  //Code run would be identical anyway, this just involves more visible text.
			if (i == 0)  { addSlotToContainer(new Slot(tileEntity, 1, 8,  16)); };
			if (i == 1)  { addSlotToContainer(new Slot(tileEntity, 2, 28, 16)); };
			if (i == 2)  { addSlotToContainer(new Slot(tileEntity, 3, 48, 16)); };
			if (i == 3)  { addSlotToContainer(new Slot(tileEntity, 4, 68, 16)); };
			if (i == 4)  { addSlotToContainer(new Slot(tileEntity, 5, 8,  36)); };
			if (i == 5)  { addSlotToContainer(new Slot(tileEntity, 6, 28, 36)); };
			if (i == 6)  { addSlotToContainer(new Slot(tileEntity, 7, 48, 36)); };
			if (i == 7)  { addSlotToContainer(new Slot(tileEntity, 8, 68, 36)); };
			if (i == 8)  { addSlotToContainer(new Slot(tileEntity, 9, 8,  56)); };
			if (i == 9)  { addSlotToContainer(new Slot(tileEntity, 10, 28, 56)); };
			if (i == 10) { addSlotToContainer(new Slot(tileEntity, 11, 48, 56)); };
			if (i == 11) { addSlotToContainer(new Slot(tileEntity, 12, 68, 56)); };
		}
		
		bindPlayerInventory(player, 8, 136);
	}
	
	@Override
	public boolean canInteractWith(EntityPlayer player) {
		return tileEntity.isUseableByPlayer(player);
	}

	public void bindPlayerInventory(InventoryPlayer inventoryPlayer, int xStart, int yStart) { // X/Y are the top-left corner of the first hotbar slot.
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 9; j++) {
				addSlotToContainer(new Slot(inventoryPlayer, j + i * 9 + 9, xStart + j * 18, yStart - 58 + i * 18));
			}
		}

		for (int i = 0; i < 9; i++) {
			addSlotToContainer(new Slot(inventoryPlayer, i, xStart + i * 18, yStart));
		}
	}
	
	@Override
	public ItemStack transferStackInSlot(EntityPlayer player, int slot) {
		return null;
	}
}
