package co.uk.silvania.advancedarmoury.gun.inventory.assault;

import co.uk.silvania.advancedarmoury.blocks.machines.SlotComponent;
import co.uk.silvania.advancedarmoury.blocks.machines.SlotComponentOption;
import co.uk.silvania.advancedarmoury.blocks.machines.SlotCore;
import co.uk.silvania.advancedarmoury.gun.inventory.ItemIInventory;
import co.uk.silvania.advancedarmoury.gun.inventory.SlotAttachment;
import net.minecraft.item.ItemStack;

public class AssaultIInventory extends ItemIInventory {

	public AssaultIInventory(ItemStack item) {
		super(item, 32);
		// TODO Auto-generated constructor stub
	}
	
	public ItemStack getBolt() { 					return this.getStackInSlot(2); }
	public ItemStack getBoltCarrier() { 			return this.getStackInSlot(3); }
	public ItemStack getChargingHandle() { 			return this.getStackInSlot(4); }
	public ItemStack getEjector() { 				return this.getStackInSlot(5); }
	public ItemStack getExtractor() { 				return this.getStackInSlot(6); }
	public ItemStack getFiringPin() { 				return this.getStackInSlot(7); }
	public ItemStack getFiringPinRetainerPin() { 	return this.getStackInSlot(8); }
	
	public ItemStack getFiringSystemPart1() { 	return this.getStackInSlot(9); }
	public ItemStack getFiringSystemPart2() { 	return this.getStackInSlot(10); }
	public ItemStack getPiston() { 				return this.getStackInSlot(11); }
	
	public ItemStack getChamber() { return this.getStackInSlot(12); }
	public ItemStack getBarrel() { 	return this.getStackInSlot(13); }
	
	public ItemStack getTrigger() { 		return this.getStackInSlot(14); }
	public ItemStack getFireSelector() { 	return this.getStackInSlot(15); }
	
	public ItemStack getModifierCore() { return this.getStackInSlot(16); }
	
	public ItemStack getReceiver() { 	return this.getStackInSlot(17); }
	public ItemStack getStock() { 		return this.getStackInSlot(18); }
	public ItemStack getFrontEnd() { 	return this.getStackInSlot(19); }
	
	public ItemStack getFlashHider() { return this.getStackInSlot(20); }
}
