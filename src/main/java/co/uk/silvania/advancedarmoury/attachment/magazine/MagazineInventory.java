package co.uk.silvania.advancedarmoury.attachment.magazine;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

public class MagazineInventory implements IInventory {
	
	private String name = "Gun";
	public static int INV_SIZE = 30;
	ItemStack[] inventory = new ItemStack[INV_SIZE];
	
	public MagazineInventory(ItemStack item) {
		if (!item.hasTagCompound()) {
			item.setTagCompound(new NBTTagCompound());
		}
		readFromNBT(item.getTagCompound());
	}

	@Override
	public int getSizeInventory() {
		return inventory.length;
	}

	@Override
	public ItemStack getStackInSlot(int slot) {
		return inventory[slot];
	}

	@Override
	public ItemStack decrStackSize(int slot, int amount) {
		ItemStack stack = getStackInSlot(slot);
		if (stack != null) {
			if(stack.stackSize > amount) {
				stack = stack.splitStack(amount);
				if (stack.stackSize == 0) {
					setInventorySlotContents(slot, null);
				}
			} else {
				setInventorySlotContents(slot, null);
			}
			this.markDirty();
		}
		return stack;
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int slot) {
		ItemStack stack = getStackInSlot(slot);
		if (stack != null) {
			setInventorySlotContents(slot, null);
		}
		return stack;
	}

	@Override
	public void setInventorySlotContents(int slot, ItemStack stack) {
		this.inventory[slot] = stack;
		if (stack != null && stack.stackSize > this.getInventoryStackLimit()) {
			stack.stackSize = this.getInventoryStackLimit();
		}
		
		this.markDirty();
		
	}

	@Override
	public String getInventoryName() {
		return name;
	}

	@Override
	public boolean hasCustomInventoryName() {
		return name.length() > 0;
	}

	@Override
	public int getInventoryStackLimit() {
		return 1;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer player) {
		return true;
	}

	@Override
	public void markDirty() {
		for (int i = 0; i < this.getSizeInventory(); i++) {
			if (this.getStackInSlot(i) != null && this.getStackInSlot(i).stackSize == 0) {
				this.setInventorySlotContents(i, null);
			}
		}
	}
	@Override public void openInventory() {}
	@Override public void closeInventory() {}

	@Override
	public boolean isItemValidForSlot(int slot, ItemStack stack) {
		return true;
	}
	
	public void readFromNBT(NBTTagCompound tag) {
		NBTTagList tagList = tag.getTagList("magInventory", tag.getId());
		
		for (int i = 0; i < tagList.tagCount(); i++) {
			NBTTagCompound tagCompound = (NBTTagCompound)tagList.getCompoundTagAt(i);
			
			int b0 = tagCompound.getInteger("Slot");
			
			if (b0 >= 0  && b0 < this.getSizeInventory()) {
				this.setInventorySlotContents(b0, ItemStack.loadItemStackFromNBT(tagCompound));
			}
		}
	}
	
	public void writeToNBT(NBTTagCompound tagCompound) {
		NBTTagList tagList = new NBTTagList();
		
		for (int i = 0; i < this.getSizeInventory(); i++) {
			if (this.getStackInSlot(i) != null) {
				NBTTagCompound tagCompound1 = new NBTTagCompound();
				tagCompound1.setInteger("Slot", i);
				
				this.getStackInSlot(i).writeToNBT(tagCompound1);
				
				tagList.appendTag(tagCompound1);
			}
		}
		tagCompound.setTag("magInventory", tagList);
	}

}
