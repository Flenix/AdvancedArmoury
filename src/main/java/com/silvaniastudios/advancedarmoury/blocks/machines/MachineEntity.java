package com.silvaniastudios.advancedarmoury.blocks.machines;

import com.silvaniastudios.advancedarmoury.config.MaterialStats;
import com.silvaniastudios.advancedarmoury.items.components.assault.AssaultReceiverCasing;
import com.silvaniastudios.advancedarmoury.items.components.lmg.LMGReceiverCasing;
import com.silvaniastudios.advancedarmoury.items.components.rifle.RifleReceiverCasing;
import com.silvaniastudios.advancedarmoury.items.components.smg.SMGReceiverCasing;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.EnumSkyBlock;

public class MachineEntity extends TileEntity implements IInventory {
	
	public ItemStack[] inv;
	protected MaterialStats stats;
	
	public int partsValue;
	public int buildProgress;
	public int clientBuildProgress;
	public int buildTime;
	public boolean building;
	public String initiator;
	public String gunName;
	public String gunTag;
	
	public MachineEntity(int invSize) {
		inv = new ItemStack[invSize]; //17 for assembly, 8 for encasement
	}
	
	@Override
	public void writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);

		NBTTagList itemList = new NBTTagList();
		for (int i = 0; i < inv.length; i++) {
			ItemStack stack = inv[i];
			if (stack != null) {
				NBTTagCompound tag = new NBTTagCompound();
				tag.setByte("Slot", (byte) i);
				stack.writeToNBT(tag);
				itemList.appendTag(tag);
			}
		}
		
		nbt.setTag("Inventory", itemList);
		
		nbt.setInteger("partsValue", partsValue);
		nbt.setInteger("buildProgress", buildProgress);
		nbt.setInteger("buildTime", buildTime);
		nbt.setBoolean("building", building);
	}
	
	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);

		NBTTagList tagList = nbt.getTagList("Inventory", 10);
		for (int i = 0; i < tagList.tagCount(); i++) {
			NBTTagCompound tag = (NBTTagCompound) tagList.getCompoundTagAt(i);
			byte slot = tag.getByte("Slot");
			if (slot >= 0 && slot < inv.length) {
				inv[slot] = ItemStack.loadItemStackFromNBT(tag);
			}
		}
		
		this.partsValue = nbt.getInteger("partsValue");
		this.buildProgress = nbt.getInteger("buildProgress");
		this.buildTime = nbt.getInteger("buildTime");
		this.building = nbt.getBoolean("building");
	}
	
	@Override
	public Packet getDescriptionPacket() {
		NBTTagCompound nbt = new NBTTagCompound();
		nbt.setInteger("partsValue", partsValue);
		nbt.setInteger("buildProgress", buildProgress);
		
		this.worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
		markDirty();
		
		return new S35PacketUpdateTileEntity(xCoord, yCoord, zCoord, 1, nbt);
	}
	
	@Override
	public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
		NBTTagCompound nbt = pkt.func_148857_g();
		
		this.partsValue = nbt.getInteger("partsValue");
		this.buildProgress = nbt.getInteger("buildProgress");
		
		this.worldObj.updateLightByType(EnumSkyBlock.Block, this.xCoord, this.yCoord, this.zCoord);
	}
	
	public void setBuildProgress(int b) {
		clientBuildProgress = b;
	}
	
	public int partCost(ItemStack item) {
		if (item != null) {
			if (item.getItem() instanceof AssaultReceiverCasing) {
				return 450;
			} else if (item.getItem() instanceof RifleReceiverCasing) {
				return 350;
			} else if (item.getItem() instanceof LMGReceiverCasing) {
				return 400;
			} else if (item.getItem() instanceof SMGReceiverCasing) {
				return 450;
			}
		}
		return 0;
	}
	
	public int buildTime(ItemStack item) {
		if (item != null) {
			if (item.getItem() instanceof AssaultReceiverCasing) {
				return 1200;
			} else if (item.getItem() instanceof RifleReceiverCasing) {
				return 2400;
			} else if (item.getItem() instanceof LMGReceiverCasing) {
				return 1800;
			} else if (item.getItem() instanceof SMGReceiverCasing) {
				return 900;
			}
		}
		return 0;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer player) {
		return worldObj.getTileEntity(xCoord, yCoord, zCoord) == this && player.getDistanceSq(xCoord + 0.5, yCoord + 0.5, zCoord + 0.5) < 64;
	}
	
	@Override
	public void setInventorySlotContents(int slot, ItemStack stack) {
		inv[slot] = stack;
		if (stack != null && stack.stackSize > getInventoryStackLimit()) {
			stack.stackSize = getInventoryStackLimit();
		}
	}

	@Override
	public ItemStack decrStackSize(int slot, int amt) {
		ItemStack stack = getStackInSlot(slot);
		if (stack != null) {
			if (stack.stackSize <= amt) {
				setInventorySlotContents(slot, null);
			} else {
				stack = stack.splitStack(amt);
				if (stack.stackSize == 0) {
					setInventorySlotContents(slot, null);
				}
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
	
	@Override public boolean isItemValidForSlot(int s, ItemStack i) {	return true; 		}
	@Override public int getSizeInventory() { 							return inv.length; 	}
	@Override public ItemStack getStackInSlot(int slot) {				return inv[slot];	}
	@Override public String getInventoryName() { 						return null; 		}
	@Override public boolean hasCustomInventoryName() { 				return false; 		}
	@Override public int getInventoryStackLimit() {				 		return 64; 			}
	@Override public void openInventory() {}
	@Override public void closeInventory() {}
	
	public String getMaterial(ItemStack item) 		{ return item.stackTagCompound != null ? item.stackTagCompound.getString("materialName") : null; }

}
