package co.uk.silvania.advancedarmoury.gun.inventory.assault;

import co.uk.silvania.advancedarmoury.blocks.machines.SlotComponent;
import co.uk.silvania.advancedarmoury.blocks.machines.SlotComponentOption;
import co.uk.silvania.advancedarmoury.blocks.machines.SlotCore;
import co.uk.silvania.advancedarmoury.gun.inventory.ItemContainer;
import co.uk.silvania.advancedarmoury.gun.inventory.ItemIInventory;
import co.uk.silvania.advancedarmoury.gun.inventory.SlotAttachment;
import co.uk.silvania.advancedarmoury.gun.inventory.SlotMagazine;
import co.uk.silvania.advancedarmoury.gun.inventory.SlotRound;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class AssaultContainer extends ItemContainer {
	
	public final ItemIInventory inventory;
	private final ItemStack containerStack;
	public boolean needsUpdate;
		
	public AssaultContainer(EntityPlayer player, InventoryPlayer invPlayer, ItemIInventory invGun) {
		this.inventory = invGun;
		this.containerStack = player.getHeldItem();
		
		this.addSlotToContainer(new SlotMagazine(this.inventory, 0, 134, 152));
		this.addSlotToContainer(new SlotRound(this.inventory, 1, 152, 152));
		
		addSlotToContainer(new SlotComponent(this.inventory, 2, 178, 232, "barrel", false));
		addSlotToContainer(new SlotComponent(this.inventory, 3, 196, 232, "frontEnd", false));
		addSlotToContainer(new SlotComponent(this.inventory, 4, 214, 232, "casing", false));
		addSlotToContainer(new SlotComponent(this.inventory, 5, 232, 232, "stock", false));

		addSlotToContainer(new SlotCore(this.inventory, 6, 232, 210));	//Modifier Core

		addSlotToContainer(new SlotAttachment(this.inventory, 7, 8, 96, "barrel"));
		
		//Any double space slots which have a double-size attachment must have the attachment installed in the rear slot.
		addSlotToContainer(new SlotAttachment(this.inventory, 8,  28, 40,  "rail")); //Front top
		addSlotToContainer(new SlotAttachment(this.inventory, 9,  51, 40,  "rail")); //Front left
		addSlotToContainer(new SlotAttachment(this.inventory, 10, 74, 40,  "rail")); //Front right
		addSlotToContainer(new SlotAttachment(this.inventory, 11, 28, 152, "rail")); //Front bottom front
		addSlotToContainer(new SlotAttachment(this.inventory, 12, 46, 152, "rail")); //Front bottom rear
		addSlotToContainer(new SlotAttachment(this.inventory, 13, 114, 40, "rail")); //Rec low front
		addSlotToContainer(new SlotAttachment(this.inventory, 14, 132, 40, "rail")); //rec low rear 
		addSlotToContainer(new SlotAttachment(this.inventory, 15, 114, 22, "rail")); //rec high front
		addSlotToContainer(new SlotAttachment(this.inventory, 16, 132, 22, "rail")); //rec high rear
		
		addSlotToContainer(new SlotAttachment(this.inventory, 17, 232, 96, "stock")); //Stock

		bindPlayerInventory(invPlayer, 8, 232);
	}

	public void writeToNBT() {
		if (!this.containerStack.hasTagCompound()) {
			this.containerStack.setTagCompound(new NBTTagCompound());
		}
		((ItemIInventory) inventory).writeToNBT(this.containerStack.getTagCompound());
	}

	
	public ItemStack transferStackInSlot(EntityPlayer player, int slotId) {
		return null;
	}
	
	@Override
	public ItemStack slotClick(int slot, int button, int flag, EntityPlayer player) {
		if (slot == 0 || slot == 1) {
			return super.slotClick(slot, button, flag, player);
		} else if (slot <= 30) {
			return null;
		}
		this.needsUpdate = true;
		return super.slotClick(slot, button, flag, player);
	}
}
