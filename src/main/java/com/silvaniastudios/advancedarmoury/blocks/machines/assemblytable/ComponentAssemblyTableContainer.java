package com.silvaniastudios.advancedarmoury.blocks.machines.assemblytable;

import com.silvaniastudios.advancedarmoury.blocks.machines.MachineContainer;
import com.silvaniastudios.advancedarmoury.blocks.machines.SlotComponent;
import com.silvaniastudios.advancedarmoury.blocks.machines.SlotParts;

import net.minecraft.entity.player.InventoryPlayer;

public class ComponentAssemblyTableContainer extends MachineContainer {
	
	public ComponentAssemblyTableEntity tileEntity;

	public ComponentAssemblyTableContainer(InventoryPlayer player, ComponentAssemblyTableEntity te) {
		super(te);
		tileEntity = te;
		
		addSlotToContainer(new SlotParts(tileEntity, 0, 8, 10)); //Insert parts slot
		
		addSlotToContainer(new SlotReceiverFrame(tileEntity, 1, 41, 54)); 				//Core  		ALL

		addSlotToContainer(new SlotComponent(tileEntity, 2,  8, 50, "bolt", true)); 			//Bolt			ALL
		addSlotToContainer(new SlotComponent(tileEntity, 3,  8, 72, "chamber", false)); 		//Chamber		ALL
		addSlotToContainer(new SlotComponent(tileEntity, 4,  8, 94, "firingPin", true));		//Firing Pin	ALL
		addSlotToContainer(new SlotComponent(tileEntity, 5, 30, 94, "firingMechanism", true));	//Firing Mechanic	ASS/LMG/SMG/PSTL
		addSlotToContainer(new SlotComponent(tileEntity, 6, 52, 94, "fireSelector", false)); 	//Fire Selector		ASS/SMG/PSTL
		addSlotToContainer(new SlotComponent(tileEntity, 7, 74, 94, "chargingHandle", false)); 	//Charging Handle	ASS/LMG/SMG
		addSlotToContainer(new SlotComponent(tileEntity, 8, 74, 72, "trigger", false)); 		//Trigger		ALL
		addSlotToContainer(new SlotComponent(tileEntity, 9, 74, 50, "casing", false)); 			//Casing		ALL
		
		bindPlayerInventory(player, 17, 217);
	}

}
