package co.uk.silvania.advancedarmoury.attachment.magazine;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class MagazineContainer extends Container {
	
	public final MagazineInventory inventory;
	private final ItemStack containerStack;
	public boolean needsUpdate;
	
	private static final int INV_START = MagazineInventory.INV_SIZE, INV_END = INV_START+26, HOTBAR_START = INV_END+1, HOTBAR_END = HOTBAR_START+8;

	
	public MagazineContainer(EntityPlayer player, InventoryPlayer invPlayer, MagazineInventory invMagazine) {
		this.inventory = invMagazine;
		this.containerStack = player.getHeldItem();
		int i;
		
		this.addSlotToContainer(new SlotRound(this.inventory, 0, 91, 77)); //Magazine
		
		for (i = 1; i < MagazineInventory.INV_SIZE; i++) {
			this.addSlotToContainer(new Slot(this.inventory, i, 200 + (18 * (int) (i/4)), 8 + (18*(i%4))));
		}
		
		for (i = 0; i < 3; i++) {
			for (int j = 0; j < 9; j++) {
				this.addSlotToContainer(new Slot(invPlayer, j + i * 9 + 9, 8 + j * 18, 113 + i * 18));
			}
		}
		
		for (i = 0; i < 9; i++) {
			this.addSlotToContainer(new Slot(invPlayer, i, 8 + i * 18, 171));
		}
	}
	
	public void writeToNBT() {
		if (!this.containerStack.hasTagCompound()) {
			this.containerStack.setTagCompound(new NBTTagCompound());
		}
		((MagazineInventory) inventory).writeToNBT(this.containerStack.getTagCompound());
	}
	
	@Override
	public boolean canInteractWith(EntityPlayer player) {
		return true;
	}
	
	public ItemStack transferStackInSlot(EntityPlayer player, int slotId) {
		ItemStack itemstack = null;
		Slot slot = (Slot) this.inventorySlots.get(slotId);

		if (slot != null && slot.getHasStack()) {
			ItemStack itemstack1 = slot.getStack();
			itemstack = itemstack1.copy();

			if (slotId < INV_START) {
				if (!this.mergeItemStack(itemstack1, INV_START, HOTBAR_END + 1, true)) {
					return null;
				}
				
				slot.onSlotChange(itemstack1, itemstack);
			} else {

		/* If your inventory only stores certain instances of Items,
		you can implement shift-clicking to your inventory like this:
		Check that the item is the right type
		
		if (itemstack1.getItem() instanceof ItemCustom) {
			   Try to merge into your custom inventory slots
			   We use 'InventoryItem.INV_SIZE' instead of INV_START just in case
			   you also add armor or other custom slots
			if (!this.mergeItemStack(itemstack1, 0, InventoryItem.INV_SIZE, false))	{
				return null;
			}
		}

		// If you added armor slots, check them here as well:

		// Item being shift-clicked is armor - try to put in armor slot

		if (itemstack1.getItem() instanceof ItemArmor) {
			int type = ((ItemArmor) itemstack1.getItem()).armorType;
			if (!this.mergeItemStack(itemstack1, ARMOR_START + type, ARMOR_START + type + 1, false)) {
				return null;
			}
		}

		* Otherwise, you have basically 2 choices:

		* 1. shift-clicking between action bar and inventory

		* 2. shift-clicking between player inventory and custom inventory

		* I've implemented number 1:

		*/

		// item is in player's inventory, but not in action bar

				if (slotId >= INV_START && slotId < HOTBAR_START) {
					if (!this.mergeItemStack(itemstack1, HOTBAR_START, HOTBAR_END + 1, false)) {
						return null;
					}
				} else if (slotId >= HOTBAR_START && slotId < HOTBAR_END + 1) {
					if (!this.mergeItemStack(itemstack1, INV_START, INV_END + 1, false)) {
						return null;
					}
				}
			}

			if (itemstack1.stackSize == 0) {
				slot.putStack((ItemStack) null);
			} else {
				slot.onSlotChanged();
			}
			
			if (itemstack1.stackSize == itemstack.stackSize) {
				return null;
			}

			slot.onPickupFromSlot(player, itemstack1);
		}

		this.needsUpdate = true;
		return itemstack;
	}
	
	@Override
	public ItemStack slotClick(int slot, int button, int flag, EntityPlayer player) {
		this.needsUpdate = true;
		return super.slotClick(slot, button, flag, player);
	}


}
