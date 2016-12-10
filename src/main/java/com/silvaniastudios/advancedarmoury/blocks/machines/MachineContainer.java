package com.silvaniastudios.advancedarmoury.blocks.machines;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;

public class MachineContainer extends Container {
	
	public MachineEntity tileEntity;
	private int lastProgress;
	
	public MachineContainer(MachineEntity te) {
		tileEntity = te;
	}
	
	@Override 
	public boolean canInteractWith(EntityPlayer player) { 
		return tileEntity.isUseableByPlayer(player);
	}
	
	@Override
    public void addCraftingToCrafters(ICrafting par1) {
        super.addCraftingToCrafters(par1);
        par1.sendProgressBarUpdate(this, 0, this.tileEntity.buildProgress);
    }
	
	@Override
	public void detectAndSendChanges() {
        super.detectAndSendChanges();
        
        for (int i = 0; i < this.crafters.size(); ++i) {

	        ICrafting icrafting = (ICrafting)this.crafters.get(i);
	
	        if (this.lastProgress != this.tileEntity.buildProgress) {
	        	icrafting.sendProgressBarUpdate(this, 0, this.tileEntity.buildProgress);
	        }
        }
        this.lastProgress = this.tileEntity.buildProgress;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void updateProgressBar(int par1, int par2) {
        if (par1 == 0) {
            tileEntity.setBuildProgress(par2);
        }
    }
	
	
	public void bindPlayerInventory(InventoryPlayer inventoryPlayer, int xStart, int yStart) { // X/Y are the top-left corner of the first hotbar slot.
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 9; j++) {
				addSlotToContainer(new Slot(inventoryPlayer, j + i * 9 + 9, xStart + j * 18, yStart - 58 + i * 18));
			}
		}

		for (int i = 0; i < 9; i++) {
			addSlotToContainer(new Slot(inventoryPlayer, i, xStart + i * 18, yStart));
		}
	}

	@Override
	public ItemStack transferStackInSlot(EntityPlayer player, int slot) {
		//All my gun creation tables are intentionally disabled for shift-clicking. YOU GOTTA WORK FOR IT.
		return null;
	}
	
	@Override
	public ItemStack slotClick(int slot, int button, int flag, EntityPlayer player) {
		if (slot != 0) {
			if (tileEntity.building) {
				return null; //No clicking slots while things are building. Stops you taking out parts as they are added.
			}
		}
		return super.slotClick(slot, button, flag, player);
	}

}
