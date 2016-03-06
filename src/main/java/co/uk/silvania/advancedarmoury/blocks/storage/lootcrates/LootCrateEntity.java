package co.uk.silvania.advancedarmoury.blocks.storage.lootcrates;

import java.util.ArrayList;

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

public class LootCrateEntity extends TileEntity implements IInventory {
	
	public ItemStack[] inv;
	
	public ArrayList<String> playerList = new ArrayList();
	public ArrayList<int[]> playerInv  = new ArrayList();
	public ArrayList<Boolean> playerLock = new ArrayList();
	public String playerUuid;
	public int rotation;
	
	public float oldRotation = 0.0F; //These are only ever changed client-side
	public int openDelay = 0;		 //and are used for animation only.
	
	public LootCrateEntity() {
		inv = new ItemStack[2];
	}
	
	@Override
	public void writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		NBTTagList nbtTagList = new NBTTagList();
		
		for (int i = 0; i < inv.length; i++) {
			if (inv[i] != null) {
				NBTTagCompound nbtTagCompound = new NBTTagCompound();
				nbtTagCompound.setByte("Slot", (byte)i);
				inv[i].writeToNBT(nbtTagCompound);
				nbtTagList.appendTag(nbtTagCompound);
			}
		}
		nbt.setTag("Items", nbtTagList);
		
		NBTTagList list = new NBTTagList();

		for (int i = 0; i < this.playerList.size(); i++) {
			NBTTagCompound compound = new NBTTagCompound();
			this.playerInv.ensureCapacity(i);
			this.playerLock.ensureCapacity(i);
			compound.setInteger("id", i);
			compound.setString("value", this.playerList.get(i));
			compound.setIntArray("taken", this.playerInv.get(i));
			compound.setBoolean("unlocked", this.playerLock.get(i));
			list.appendTag(compound);
		}
		nbt.setInteger("rotation", rotation);
		nbt.setTag("playerList", list);
	}
	
	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		NBTTagList nbtTagList = nbt.getTagList("Items", 10);
		
		for (int i = 0; i < nbtTagList.tagCount(); i++) {
			NBTTagCompound nbtTagCompound = nbtTagList.getCompoundTagAt(i);
			int slot = nbtTagCompound.getByte("Slot") & 255;
			
			if (slot >= 0 && slot < inv.length) {
				inv[slot] = ItemStack.loadItemStackFromNBT(nbtTagCompound);
			}
		}
		rotation = nbt.getInteger("rotation");
		
		NBTTagList list = nbt.getTagList("playerList", 10);
		this.playerList.clear();
		this.playerInv.clear();
		this.playerLock.clear();
		for (int i = 0; i < list.tagCount(); i++) {
			NBTTagCompound compound = list.getCompoundTagAt(i);
			int id = compound.getInteger("id");
			String value = compound.getString("value");
			this.playerList.ensureCapacity(id);
			this.playerInv.ensureCapacity(id);
			this.playerLock.ensureCapacity(id);
			
			this.playerList.add(value);
			this.playerInv.add(compound.getIntArray("taken"));
			this.playerLock.add(compound.getBoolean("unlocked"));
		}
	}
	
	@Override
	public Packet getDescriptionPacket() {
		NBTTagCompound nbt = new NBTTagCompound();
		NBTTagList nbtTagList = new NBTTagList();
		
		for (int i = 0; i < inv.length; i++) {
			if (inv[i] != null) {
				NBTTagCompound nbtTagCompound = new NBTTagCompound();
				nbtTagCompound.setByte("Slot", (byte)i);
				inv[i].writeToNBT(nbtTagCompound);
				nbtTagList.appendTag(nbtTagCompound);
			}
		}
		nbt.setTag("Items", nbtTagList);
		
		NBTTagList list = new NBTTagList();

		for (int i = 0; i < this.playerList.size(); i++) {
			NBTTagCompound compound = new NBTTagCompound();
			this.playerInv.ensureCapacity(i);
			this.playerLock.ensureCapacity(i);
			compound.setInteger("id", i);
			compound.setString("value", this.playerList.get(i));
			compound.setIntArray("taken", this.playerInv.get(i));
			compound.setBoolean("unlocked", this.playerLock.get(i));
			list.appendTag(compound);
		}
		nbt.setInteger("rotation", rotation);
		nbt.setTag("playerList", list);
		
		worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
		markDirty();
		
		return new S35PacketUpdateTileEntity(xCoord, yCoord, zCoord, 1, nbt);
	}
	
	@Override
	public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
		NBTTagCompound nbt = pkt.func_148857_g();
		
		NBTTagList nbtTagList = nbt.getTagList("Items", 10);
		
		for (int i = 0; i < nbtTagList.tagCount(); i++) {
			NBTTagCompound nbtTagCompound = nbtTagList.getCompoundTagAt(i);
			int slot = nbtTagCompound.getByte("Slot") & 255;
			
			if (slot >= 0 && slot < inv.length) {
				inv[slot] = ItemStack.loadItemStackFromNBT(nbtTagCompound);
			}
		}
		
		rotation = nbt.getInteger("rotation");
		
		NBTTagList list = nbt.getTagList("playerList", 10);
		this.playerList.clear();
		this.playerInv.clear();
		this.playerLock.clear();
		for (int i = 0; i < list.tagCount(); i++) {
			NBTTagCompound compound = list.getCompoundTagAt(i);
			int id = compound.getInteger("id");
			String value = compound.getString("value");
			this.playerList.ensureCapacity(id);
			this.playerInv.ensureCapacity(id);
			this.playerLock.ensureCapacity(id);
			
			this.playerList.add(value);
			this.playerInv.add(compound.getIntArray("taken"));
			this.playerLock.add(compound.getBoolean("unlocked"));
		}
		
		worldObj.updateLightByType(EnumSkyBlock.Block, xCoord, yCoord, zCoord);
	}

	@Override
	public int getSizeInventory() {
		return inv.length;
	}

	@Override
	public ItemStack getStackInSlot(int slot) {
		return inv[slot];
	}

	@Override
	public ItemStack decrStackSize(int slot, int amount) {
		ItemStack item = getStackInSlot(slot);
		
		if (item != null) {
			if (item.stackSize <= amount) {
				setInventorySlotContents(slot, null);
			} else {
				item = item.splitStack(amount);
			}
		}
		return item;
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int slot) {
		ItemStack item = getStackInSlot(slot);
		setInventorySlotContents(slot, null);
		return item;
	}

	@Override
	public void setInventorySlotContents(int slot, ItemStack item) {
		inv[slot] = item;
		if (item != null && item.stackSize > getInventoryStackLimit()) {
			item.stackSize = getInventoryStackLimit();
		}
	}

	@Override public boolean isItemValidForSlot(int slot, ItemStack item) { return true; }
	@Override public String getInventoryName() { 		return null; }
	@Override public boolean hasCustomInventoryName() { return false; }
	@Override public int getInventoryStackLimit() { 	return 64; }
	@Override public boolean isUseableByPlayer(EntityPlayer player) { return player.getDistanceSq(xCoord + 0.5, yCoord + 0.5, zCoord + 0.5) <= 64; }
	@Override public void openInventory() {}
	@Override public void closeInventory() {}

}
