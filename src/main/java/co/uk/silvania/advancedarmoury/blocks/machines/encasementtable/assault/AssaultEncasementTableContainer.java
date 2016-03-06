package co.uk.silvania.advancedarmoury.blocks.machines.encasementtable.assault;

import co.uk.silvania.advancedarmoury.blocks.machines.MachineContainer;
import co.uk.silvania.advancedarmoury.blocks.machines.SlotComponent;
import co.uk.silvania.advancedarmoury.blocks.machines.SlotComponentOption;
import co.uk.silvania.advancedarmoury.blocks.machines.SlotCore;
import co.uk.silvania.advancedarmoury.blocks.machines.SlotFrame;
import co.uk.silvania.advancedarmoury.blocks.machines.SlotParts;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class AssaultEncasementTableContainer extends MachineContainer {

	public AssaultEncasementTableEntity tileEntity;

	public AssaultEncasementTableContainer (InventoryPlayer player, AssaultEncasementTableEntity te){
		super(te);
		tileEntity = te;
		
		addSlotToContainer(new SlotFrame(tileEntity, 0, 102, 51)); //Assault Frame slot
		addSlotToContainer(new SlotParts(tileEntity, 1, 8, 22)); //Insert parts slot

		addSlotToContainer(new SlotComponent(tileEntity, 2, 32, 51, "receiver"));
		addSlotToContainer(new SlotComponent(tileEntity, 3, 54, 51, "stock"));
		addSlotToContainer(new SlotComponent(tileEntity, 4, 76, 51, "frontEnd"));
		addSlotToContainer(new SlotComponent(tileEntity, 5, 128, 51, "flashHider"));
		
		bindPlayerInventory(player, 8, 186);
	}
}
