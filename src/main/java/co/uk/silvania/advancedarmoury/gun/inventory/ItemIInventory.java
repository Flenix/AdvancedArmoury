package co.uk.silvania.advancedarmoury.gun.inventory;

import co.uk.silvania.advancedarmoury.AdvancedArmoury;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;

public class ItemIInventory implements IInventory {
	
	public ItemStack[] inventory;
	public final ItemStack invItem;
	
	public ItemIInventory(ItemStack item, int inventorySize) {
		invItem = item;
		
		inventory = new ItemStack[inventorySize];
		
		if (item != null) {
			if (!item.hasTagCompound()) {
				item.setTagCompound(new NBTTagCompound());
			}
		}
		
		readFromNBT(item.getTagCompound());
	}

	@Override
	public ItemStack decrStackSize(int slot, int amount) {
		ItemStack stack = getStackInSlot(slot);
		if (stack != null) {
			if(stack.stackSize > amount) {
				stack = stack.splitStack(amount);
				markDirty();
			} else {
				setInventorySlotContents(slot, null);
			}
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
		markDirty();
	}

	@Override public int getSizeInventory() { 				return inventory.length; }
	@Override public ItemStack getStackInSlot(int slot) { 	return inventory[slot]; }
	@Override public String getInventoryName() { 			return invItem.getDisplayName(); }
	@Override public boolean hasCustomInventoryName() { 	return invItem.getDisplayName().length() > 0; }
	@Override public int getInventoryStackLimit() { 		return 1; }
	@Override public boolean isUseableByPlayer(EntityPlayer player) { return true; }
	@Override public void openInventory() {}
	@Override public void closeInventory() {}
	@Override public boolean isItemValidForSlot(int slot, ItemStack stack) { return true; }
	
	@Override
	public void markDirty() {
		for (int i = 0; i < this.getSizeInventory(); i++) {
			if (this.getStackInSlot(i) != null && this.getStackInSlot(i).stackSize == 0) {
				this.setInventorySlotContents(i, null);
			}
		}
		writeToNBT(invItem.getTagCompound());
	}
	
	public void readFromNBT(NBTTagCompound tag) {
		NBTTagList tagList = tag.getTagList("gunInventory", tag.getId());
		
		for (int i = 0; i < tagList.tagCount(); i++) {
			NBTTagCompound tagCompound = (NBTTagCompound) tagList.getCompoundTagAt(i);
			
			int slot = tagCompound.getInteger("Slot");
			
			if (slot >= 0  && slot < this.getSizeInventory()) {
				setInventorySlotContents(slot, ItemStack.loadItemStackFromNBT(tagCompound));
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
		tagCompound.setTag("gunInventory", tagList);
	}
}
