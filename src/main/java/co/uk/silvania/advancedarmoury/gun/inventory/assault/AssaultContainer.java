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
		
		this.addSlotToContainer(new SlotMagazine(this.inventory, 0, 114, 112));
		this.addSlotToContainer(new SlotRound(this.inventory, 1, 132, 112));
		

		addSlotToContainer(new SlotComponent(this.inventory, 2, 214, 19, "bolt"));
		addSlotToContainer(new SlotComponent(this.inventory, 3, 214, 37, "boltCarrier"));
		addSlotToContainer(new SlotComponent(this.inventory, 4, 214, 55, "chargingHandle"));
		addSlotToContainer(new SlotComponent(this.inventory, 5, 214, 73, "ejector"));
		addSlotToContainer(new SlotComponent(this.inventory, 6, 214, 91, "extractor"));
		addSlotToContainer(new SlotComponent(this.inventory, 7, 214, 109, "firingPin"));
		addSlotToContainer(new SlotComponent(this.inventory, 8, 214, 127, "firingPinRetainerPin"));
		
		addSlotToContainer(new SlotComponentOption(this.inventory, 9, 232, 37, "pistonChamber", "gasChamber"));
		addSlotToContainer(new SlotComponentOption(this.inventory, 10, 232, 55, "spring", "gasFeed"));
		addSlotToContainer(new SlotComponent(this.inventory, 11, 232, 73, "piston"));
		
		addSlotToContainer(new SlotComponent(this.inventory, 12, 232, 19, "chamber"));
		addSlotToContainer(new SlotComponent(this.inventory, 13, 232, 91, "barrel"));
		
		addSlotToContainer(new SlotComponent(this.inventory, 14, 232, 109, "trigger"));
		addSlotToContainer(new SlotComponent(this.inventory, 15, 232, 127, "fireSelector"));
		
		addSlotToContainer(new SlotCore(this.inventory, 16, 174, 192));	//Modifier Core
		
		addSlotToContainer(new SlotComponent(this.inventory, 17, 196, 192, "receiver"));
		addSlotToContainer(new SlotComponent(this.inventory, 18, 214, 192, "stock"));
		addSlotToContainer(new SlotComponent(this.inventory, 19, 232, 192, "frontEnd"));
		
		addSlotToContainer(new SlotAttachment(this.inventory, 20, 8, 76, "flashHider"));
		
		//Any double space slots which have a double-size attachment must have the attachment installed in the rear slot.
		addSlotToContainer(new SlotAttachment(this.inventory, 21, 74, 40,  "rail")); //Front top
		addSlotToContainer(new SlotAttachment(this.inventory, 22, 28, 112, "rail")); //Front left
		addSlotToContainer(new SlotAttachment(this.inventory, 23, 28, 40,  "rail")); //Front right
		addSlotToContainer(new SlotAttachment(this.inventory, 24, 57, 112, "rail")); //Front bottom front
		addSlotToContainer(new SlotAttachment(this.inventory, 25, 75, 112, "rail")); //Front bottom rear
		addSlotToContainer(new SlotAttachment(this.inventory, 26, 114, 40, "rail")); //Rec low front
		addSlotToContainer(new SlotAttachment(this.inventory, 27, 132, 40, "rail")); //rec low rear 
		addSlotToContainer(new SlotAttachment(this.inventory, 28, 114, 22, "rail")); //rec high front
		addSlotToContainer(new SlotAttachment(this.inventory, 29, 132, 22, "rail")); //rec high rear
		
		addSlotToContainer(new SlotAttachment(this.inventory, 30, 172, 40, "stock")); //Stock

		bindPlayerInventory(invPlayer, 8, 192);
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
