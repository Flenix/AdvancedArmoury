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
		
		//RECEIVER. Contains bolt/chamber/handle/pin/fire system/fire selector/trigger
		//Controls fire rate, power
		//FRONT END. defines attachments, supports barrel.
		//BARREL. Defines accuracy, range (TODO: flatten off model)
		//STOCK. Lowers recoil (TODO: recoil)
		
		addSlotToContainer(new SlotParts(tileEntity, 0, 8, 10)); //Insert parts slot
		addSlotToContainer(new SlotFrame(tileEntity, 1, 18, 100)); //Assault Frame slot
		
		//addSlotToContainer(new SlotComponent(tileEntity, 2, 8,  56, "barrel"));		//Cyan A
		//addSlotToContainer(new SlotComponent(tileEntity, 3, 28, 76, "assaultReceiver"));	//Gold B
		//addSlotToContainer(new SlotComponent(tileEntity, 4, 48, 76, "assaultStock"));		//Gold B
		//addSlotToContainer(new SlotComponent(tileEntity, 5, 68, 76, "assaultFrontEnd"));	//Gold B

		//addSlotToContainer(new SlotCore(tileEntity, 6, 68, 96)); //Modifier Core slot
		
		/*addSlotToContainer(new SlotComponent(tileEntity, 6, 8,  36, "assaultBolt"));		  //Green A
		addSlotToContainer(new SlotComponent(tileEntity, 7, 48, 36, "assaultChargingHandle"));//Green B
		addSlotToContainer(new SlotComponent(tileEntity, 8, 68, 36, "assaultFiringPin"));	  //Green C
		addSlotToContainer(new SlotComponent(tileEntity, 9, 28, 56, "rifleChamber"));//Cyan B
		addSlotToContainer(new SlotComponent(tileEntity, 10, 48, 56, "trigger"));		//Gold A
		addSlotToContainer(new SlotComponent(tileEntity, 11, 68, 56, "fireSelector"));	//Gold B
		addSlotToContainer(new SlotComponent(tileEntity, 12, 8, 76, "fireSystem"));	//Red A
		
		addSlotToContainer(new SlotComponent(tileEntity, 10, 28, 76, "assaultReceiver"));	//Gold B
		addSlotToContainer(new SlotComponent(tileEntity, 11, 48, 76, "assaultStock"));		//Gold B
		addSlotToContainer(new SlotComponent(tileEntity, 12, 68, 76, "assaultFrontEnd"));	//Gold B*/
		
		addSlotToContainer(new SlotCore(tileEntity, 14, 68, 96)); //Modifier Core slot
		
		//addSlotToContainer(new SlotComponentOption(tileEntity, 11, 28, 88, "pistonChamber", "gasChamber"));//Red A
		//addSlotToContainer(new SlotComponentOption(tileEntity, 12, 48, 88, "spring", "gasFeed"));			//Red B
		//addSlotToContainer(new SlotComponent(tileEntity, 13, 68, 88, "piston"));							//Red C		
		bindPlayerInventory(player, 17, 217);
	}
}
