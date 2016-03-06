package co.uk.silvania.advancedarmoury.blocks.machines.assemblytable.assault;

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
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class AssaultAssemblyTableContainer extends MachineContainer {

	public AssaultAssemblyTableEntity tileEntity;

	public AssaultAssemblyTableContainer (InventoryPlayer player, AssaultAssemblyTableEntity te){
		super(te);
		tileEntity = te;
		
		addSlotToContainer(new SlotFrame(tileEntity, 0, 18, 112)); //Assault Frame slot
		addSlotToContainer(new SlotParts(tileEntity, 1, 8, 22)); //Insert parts slot
		addSlotToContainer(new SlotCore(tileEntity, 2, 152, 124)); //Modifier Core slot

		addSlotToContainer(new SlotComponent(tileEntity, 3, 8, 68, "chamber"));				//Cyan B
		
		addSlotToContainer(new SlotComponent(tileEntity, 4, 28, 68, "bolt"));					//Grey A
		addSlotToContainer(new SlotComponent(tileEntity, 5, 48, 68, "boltCarrier"));			//Grey B
		addSlotToContainer(new SlotComponent(tileEntity, 6, 68, 68, "chargingHandle"));		//Grey C
		addSlotToContainer(new SlotComponent(tileEntity, 7, 8, 48, "ejector"));				//Grey D
		addSlotToContainer(new SlotComponent(tileEntity, 8, 28, 48, "extractor"));			//Grey E
		addSlotToContainer(new SlotComponent(tileEntity, 9, 48, 48, "firingPin"));			//Grey F
		addSlotToContainer(new SlotComponent(tileEntity, 10, 68, 48, "firingPinRetainerPin"));//Grey G
		
		addSlotToContainer(new SlotComponentOption(tileEntity, 11, 28, 88, "pistonChamber", "gasChamber"));//Red A
		addSlotToContainer(new SlotComponentOption(tileEntity, 12, 48, 88, "spring", "gasFeed"));			//Red B
		addSlotToContainer(new SlotComponent(tileEntity, 13, 68, 88, "piston"));							//Red C
		
		addSlotToContainer(new SlotComponent(tileEntity, 14, 8, 88, "barrel"));		//Cyan A			
		
		addSlotToContainer(new SlotComponent(tileEntity, 15, 48, 108, "trigger"));		//Gold A
		addSlotToContainer(new SlotComponent(tileEntity, 16, 68, 108, "fireSelector"));	//Gold B
		
		bindPlayerInventory(player, 8, 217);
	}
}
