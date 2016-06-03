package co.uk.silvania.advancedarmoury.blocks.machines;

import co.uk.silvania.advancedarmoury.blocks.machines.assemblytable.assault.AssaultAssemblyTableContainer;
import co.uk.silvania.advancedarmoury.items_old.components.generic.ItemComponent;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;

public class MachineGui extends GuiContainer {

	public MachineGui(MachineContainer cntr) {
		super(cntr);
	}

	@Override protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int p_146976_2_, int p_146976_3_) {}
	
	protected int totalBuildTime(int low, int high, MachineEntity te) {
		int total = 0;
		for (int i = low; i <= high; i++) {
			ItemStack itemComponent = te.getStackInSlot(i);
			if (itemComponent != null) {
				if (itemComponent.getItem() instanceof ItemComponent) {
					ItemComponent component = (ItemComponent) itemComponent.getItem();
					total = total + component.buildTime(itemComponent);
				}
			}
		}
		return total;		
	}
	
	protected double totalDurability(int low, int high, MachineEntity te) {
		double total = 0.0;
		for (int i = low; i <= high; i++) {
			ItemStack itemComponent = te.getStackInSlot(i);
			if (itemComponent != null) {
				if (itemComponent.getItem() instanceof ItemComponent) {
					ItemComponent component = (ItemComponent) itemComponent.getItem();
					total = total + component.getDurability(itemComponent);
				}
			}
		}
		return total;
	}
	
	protected int totalWeight(int low, int high, MachineEntity te) {
		int total = 0;
		for (int i = low; i <= high; i++) {
			ItemStack itemComponent = te.getStackInSlot(i);
			if (itemComponent != null) {
				if (itemComponent.getItem() instanceof ItemComponent) {
					ItemComponent component = (ItemComponent) itemComponent.getItem();
					total = total + component.getWeight(itemComponent);
				}
			}
		}
		return total;
	}
	
	protected float totalAccuracy(int low, int high, MachineEntity te) {
		float total = 0.0F;
		for (int i = low; i <= high; i++) {
			ItemStack itemComponent = te.getStackInSlot(i);
			if (itemComponent != null) {
				if (itemComponent.getItem() instanceof ItemComponent) {
					ItemComponent component = (ItemComponent) itemComponent.getItem();
					total = total + component.getAccuracy(itemComponent);
				}
			}
		}
		return total;
	}
	
	protected int fireRate(int low, int high, MachineEntity te) {
		int rate = 0;
		for (int i = low; i <= high; i++) {
			ItemStack itemComponent = te.getStackInSlot(i);
			if (itemComponent != null) {
				if (itemComponent.getItem() instanceof ItemComponent) {
					ItemComponent component = (ItemComponent) itemComponent.getItem();
					rate = rate + component.getFireRate(itemComponent);
				}
			}
		}
		return rate;
	}
	
	protected int power(int low, int high, MachineEntity te) {
		int power = 0;
		for (int i = low; i <= high; i++) {
			ItemStack itemComponent = te.getStackInSlot(i);
			if (itemComponent != null) {
				if (itemComponent.getItem() instanceof ItemComponent) {
					ItemComponent component = (ItemComponent) itemComponent.getItem();
					power = power + component.getPower(itemComponent);
				}
			}
		}
		return power;
	}
	
	protected int partsCost(int low, int high, MachineEntity te) {
		int cost = 0;
		for (int i = low; i <= high; i++) {
			ItemStack itemComponent = te.getStackInSlot(i);
			if (itemComponent != null) {
				if (itemComponent.getItem() instanceof ItemComponent) {
					ItemComponent component = (ItemComponent) itemComponent.getItem();
					cost = cost + component.cost(itemComponent);
				}
			}
		}
		return cost;
	}

}
