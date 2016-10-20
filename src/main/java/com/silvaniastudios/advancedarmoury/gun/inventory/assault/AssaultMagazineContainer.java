package com.silvaniastudios.advancedarmoury.gun.inventory.assault;

import com.silvaniastudios.advancedarmoury.gun.inventory.ItemContainer;
import com.silvaniastudios.advancedarmoury.gun.inventory.ItemIInventory;
import com.silvaniastudios.advancedarmoury.gun.inventory.SlotRound;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class AssaultMagazineContainer extends ItemContainer {
	
	public final ItemIInventory inventory;
	private final ItemStack containerStack;
	public boolean needsUpdate;
	
	public AssaultMagazineContainer(EntityPlayer player, InventoryPlayer invPlayer, ItemIInventory invGun) {
		this.inventory = invGun;
		this.containerStack = player.getHeldItem();
		
		this.addSlotToContainer(new SlotRound(this.inventory, 0, 14, 40));
		this.addSlotToContainer(new SlotRound(this.inventory, 1, 14, 58));
		this.addSlotToContainer(new SlotRound(this.inventory, 2, 14, 76));
		this.addSlotToContainer(new SlotRound(this.inventory, 3, 32, 40));
		this.addSlotToContainer(new SlotRound(this.inventory, 4, 32, 58));
		this.addSlotToContainer(new SlotRound(this.inventory, 5, 32, 76));
		this.addSlotToContainer(new SlotRound(this.inventory, 6, 50, 40));
		this.addSlotToContainer(new SlotRound(this.inventory, 7, 50, 58));
		this.addSlotToContainer(new SlotRound(this.inventory, 8, 50, 76));
		this.addSlotToContainer(new SlotRound(this.inventory, 9, 68, 40));
		this.addSlotToContainer(new SlotRound(this.inventory, 10, 68, 58));
		this.addSlotToContainer(new SlotRound(this.inventory, 11, 68, 76));
		this.addSlotToContainer(new SlotRound(this.inventory, 12, 86, 40));
		this.addSlotToContainer(new SlotRound(this.inventory, 13, 86, 58));
		this.addSlotToContainer(new SlotRound(this.inventory, 14, 86, 76));
		
		this.addSlotToContainer(new SlotRound(this.inventory, 15, 104, 36));
		this.addSlotToContainer(new SlotRound(this.inventory, 16, 104, 54));
		this.addSlotToContainer(new SlotRound(this.inventory, 17, 104, 72));
		
		this.addSlotToContainer(new SlotRound(this.inventory, 18, 122, 32));
		this.addSlotToContainer(new SlotRound(this.inventory, 19, 122, 50));
		this.addSlotToContainer(new SlotRound(this.inventory, 20, 122, 68));
		
		this.addSlotToContainer(new SlotRound(this.inventory, 21, 140, 28));
		this.addSlotToContainer(new SlotRound(this.inventory, 22, 140, 46));
		this.addSlotToContainer(new SlotRound(this.inventory, 23, 140, 64));
		
		this.addSlotToContainer(new SlotRound(this.inventory, 24, 158, 24));
		this.addSlotToContainer(new SlotRound(this.inventory, 25, 158, 42));
		this.addSlotToContainer(new SlotRound(this.inventory, 26, 158, 60));
		
		this.addSlotToContainer(new SlotRound(this.inventory, 27, 176, 20));
		this.addSlotToContainer(new SlotRound(this.inventory, 28, 176, 38));
		this.addSlotToContainer(new SlotRound(this.inventory, 29, 176, 56));

		bindPlayerInventory(invPlayer, 30, 169);
	}	
	
	public void writeToNBT() {
		if (!this.containerStack.hasTagCompound()) {
			this.containerStack.setTagCompound(new NBTTagCompound());
		}
		((ItemIInventory) inventory).writeToNBT(this.containerStack.getTagCompound());
	}
}
