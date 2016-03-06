package co.uk.silvania.advancedarmoury.gun.prebuilt;

import java.util.List;

import co.uk.silvania.advancedarmoury.AdvancedArmoury;
import co.uk.silvania.advancedarmoury.gun.inventory.ItemIInventory;
import co.uk.silvania.advancedarmoury.gun.inventory.assault.AssaultIInventory;
import co.uk.silvania.advancedarmoury.items.AAItemAssaultComponents;
import co.uk.silvania.advancedarmoury.items.AAItemComponents;
import co.uk.silvania.advancedarmoury.items.AAItemModifierCores;
import co.uk.silvania.advancedarmoury.items.EnumMaterial;
import co.uk.silvania.advancedarmoury.items.components.generic.GunFrame;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class M4CarbinePolymer extends GunFrame {
	
	public M4CarbinePolymer() {
		this.setCreativeTab(AdvancedArmoury.tabGuns);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(Item stack, CreativeTabs tab, List list) {
		ItemStack item = new ItemStack(stack);
		item.stackTagCompound = new NBTTagCompound();
		item.stackTagCompound.setBoolean("hasInternals", true);
		item.stackTagCompound.setBoolean("isCompletedGun", true);
		item.stackTagCompound.setString("name", "M4A1 Carbine");
		item.stackTagCompound.setString("tag", "Standard issue M4A1");
		ItemIInventory inventory = new AssaultIInventory(item);
		
		ItemStack barrel = new ItemStack(AAItemComponents.barrelSteel);
		barrel.stackTagCompound = new NBTTagCompound();
		barrel.stackTagCompound.setInteger("length", 15);
		barrel.stackTagCompound.setDouble("calibre", 5.56);
		barrel.stackTagCompound.setString("partName", "barrel");
		item.stackTagCompound.setFloat("accuracy", EnumMaterial.STEEL.accuracy);
		
		ItemStack chamber = new ItemStack(AAItemAssaultComponents.assaultChamberSteel);
		chamber.stackTagCompound = new NBTTagCompound();
		chamber.stackTagCompound.setDouble("calibre", 5.56);
		chamber.stackTagCompound.setString("partName", "chamber");
		
		inventory.setInventorySlotContents(2, new ItemStack(AAItemAssaultComponents.assaultBoltSteel)); //bolt
		inventory.setInventorySlotContents(3, new ItemStack(AAItemAssaultComponents.assaultBoltCarrierSteel)); //boltCarrier
		inventory.setInventorySlotContents(4, new ItemStack(AAItemAssaultComponents.assaultChargingHandleSteel)); //chargingHandle
		inventory.setInventorySlotContents(5, new ItemStack(AAItemAssaultComponents.assaultEjectorSteel)); //ejector
		inventory.setInventorySlotContents(6, new ItemStack(AAItemAssaultComponents.assaultExtractorSteel)); //extractor
		inventory.setInventorySlotContents(7, new ItemStack(AAItemAssaultComponents.assaultFiringPinSteel)); //firingPin
		inventory.setInventorySlotContents(8, new ItemStack(AAItemAssaultComponents.assaultFiringPinRetainerPinSteel)); //firingPinRetainerPin
		
		inventory.setInventorySlotContents(9, new ItemStack(AAItemAssaultComponents.assaultPistonChamberTitanium)); //piston/gas chamber
		inventory.setInventorySlotContents(10, new ItemStack(AAItemAssaultComponents.assaultSpringPromethium)); //spring/gas feed
		inventory.setInventorySlotContents(11, new ItemStack(AAItemAssaultComponents.assaultPistonChamberTitanium)); //piston
		
		inventory.setInventorySlotContents(12, chamber); //chamber
		inventory.setInventorySlotContents(13, barrel); //barrel
		
		inventory.setInventorySlotContents(14, new ItemStack(AAItemComponents.triggerPolymer)); //trigger
		inventory.setInventorySlotContents(15, new ItemStack(AAItemComponents.fireSelectorPolymer)); //fire selector
		
		inventory.setInventorySlotContents(16, new ItemStack(AAItemModifierCores.coreSimpleChamberNet)); //Modifier
		
		inventory.setInventorySlotContents(17, new ItemStack(AAItemAssaultComponents.assaultReceiverM4Polymer)); //receiver
		inventory.setInventorySlotContents(18, new ItemStack(AAItemAssaultComponents.assaultStockM4Polymer)); //stock
		inventory.setInventorySlotContents(19, new ItemStack(AAItemAssaultComponents.assaultFrontEndM4Polymer)); //frontEnd
		list.add(item);
	}

}
