package co.uk.silvania.advancedarmoury.blocks.machines.encasementtable.assault;

import co.uk.silvania.advancedarmoury.blocks.machines.MachineEntity;
import co.uk.silvania.advancedarmoury.gun.inventory.ItemIInventory;
import co.uk.silvania.advancedarmoury.gun.inventory.assault.AssaultIInventory;
import co.uk.silvania.advancedarmoury.items.ItemParts;
import co.uk.silvania.advancedarmoury.items.components.generic.GunFrame;
import co.uk.silvania.advancedarmoury.items.components.generic.ItemComponent;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class AssaultEncasementTableEntity extends MachineEntity implements IInventory {
		
	public AssaultEncasementTableEntity() {
		super(6);
	}
		
	@Override
	public void updateEntity() {
		ItemStack parts = this.getStackInSlot(1);
		if (parts != null) {
			if (parts.getItem() instanceof ItemParts) {
				ItemParts partsItem = (ItemParts) parts.getItem();
				partsValue = partsValue + partsItem.getValue(parts.getItemDamage());
				if (parts.stackSize > 1) {
					this.setInventorySlotContents(1, new ItemStack(parts.getItem(), parts.stackSize - 1, parts.getItemDamage()));
				} else {
					this.setInventorySlotContents(1, null);
				}
			}
		}
		
		if (isGunValid()) {
			int time = 0;
			for (int i = 2; i <= 5; i++) {
				ItemStack itemComponent = this.getStackInSlot(i);
				if (itemComponent != null) {
					if (itemComponent.getItem() instanceof ItemComponent) {
						ItemComponent assaultComponent = (ItemComponent) itemComponent.getItem();
						time = time + assaultComponent.buildTime;
					}
				}
			}
			buildTime = time;
		}
		
		if (building) {
			if (partsValue >= gunCost(2, 5)) {
				if (buildProgress < buildTime) {
					buildProgress++;
				} else {
					buildGun();
					buildProgress = 0;
					gunName = "";
					building = false;
				}
			} else {
				building = false;
			}
		} else {
			if (buildProgress > 0) {
				buildProgress = 0;
			}
		}
	}
	
	public boolean buildGun() {
		ItemStack frame = getStackInSlot(0);

		ItemStack receiver = getStackInSlot(2);
		ItemStack stock = getStackInSlot(3);
		ItemStack frontEnd = getStackInSlot(4);
		ItemStack flashHider = getStackInSlot(5);
		
		if (isGunValid()) {
			partsValue = partsValue - gunCost(2, 5);
			getDescriptionPacket();
			
			if (frame != null) {
				if (frame.getItem() instanceof GunFrame) {
					if (frame.stackTagCompound == null) {
						frame.stackTagCompound = new NBTTagCompound();
					}
					
					ItemIInventory inventory = new AssaultIInventory(frame);
					
					inventory.setInventorySlotContents(17, receiver);
					inventory.setInventorySlotContents(18, stock);
					inventory.setInventorySlotContents(19, frontEnd);
					if (flashHider != null) {
						inventory.setInventorySlotContents(20, flashHider);
					}
					
					this.setInventorySlotContents(2, null);
					this.setInventorySlotContents(3, null);
					this.setInventorySlotContents(4, null);
					this.setInventorySlotContents(5, null);
				}
			}
			return true;
		}
		return false;
	}
	
	public boolean isGunValid() {
		ItemStack frameItem = getStackInSlot(0);
		boolean frame = frameItem != null;
		
		boolean receiver = getStackInSlot(2) != null;
		boolean stock = getStackInSlot(3) != null;
		boolean frontEnd = getStackInSlot(4) != null;

		if (frame && receiver && stock && frontEnd) {
			if (frameItem.getItem() instanceof GunFrame) {
				GunFrame gunFrame = (GunFrame) frameItem.getItem();
				if (gunFrame.hasInternals(frameItem)) {
					return true;
				}
			}
		}

		return false;
	}
}
