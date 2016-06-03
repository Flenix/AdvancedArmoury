package co.uk.silvania.advancedarmoury.gun.prebuilt;

import java.util.List;

import co.uk.silvania.advancedarmoury.AdvancedArmoury;
import co.uk.silvania.advancedarmoury.config.ComponentGenerator;
import co.uk.silvania.advancedarmoury.config.MaterialStats;
import co.uk.silvania.advancedarmoury.gun.inventory.ItemIInventory;
import co.uk.silvania.advancedarmoury.gun.inventory.assault.AssaultIInventory;
import co.uk.silvania.advancedarmoury.items_old.AAItemModifierCores;
import co.uk.silvania.advancedarmoury.items_old.components.generic.GunFrame;
import cpw.mods.fml.common.registry.GameRegistry;
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
		if (ComponentGenerator.matExists("Polymer") && 
				ComponentGenerator.matExists("Steel") && 
				ComponentGenerator.matExists("Titanium")) {
			ItemStack item = new ItemStack(stack);
			item.stackTagCompound = new NBTTagCompound();
			item.stackTagCompound.setBoolean("hasInternals", true);
			item.stackTagCompound.setBoolean("isCompletedGun", true);
			item.stackTagCompound.setString("name", "M4A1 Carbine");
			item.stackTagCompound.setString("tag", "Standard issue M4A1");
			ItemIInventory inventory = new AssaultIInventory(item);
			
			ItemStack barrel = new ItemStack(GameRegistry.findItem(AdvancedArmoury.modid, "barrelSteel"));
			barrel.stackTagCompound = new NBTTagCompound();
			barrel.stackTagCompound.setInteger("length", 15);
			barrel.stackTagCompound.setDouble("calibre", 5.56);
			barrel.stackTagCompound.setString("partName", "barrel");
			item.stackTagCompound.setFloat("accuracy", MaterialStats.getAccuracry("Steel"));
			
			ItemStack chamber = new ItemStack(GameRegistry.findItem(AdvancedArmoury.modid, "assaultChamberSteel"));
			chamber.stackTagCompound = new NBTTagCompound();
			chamber.stackTagCompound.setDouble("calibre", 5.56);
			chamber.stackTagCompound.setString("partName", "chamber");
			
			inventory.setInventorySlotContents(2, new ItemStack(GameRegistry.findItem(AdvancedArmoury.modid, "assaultBoltSteel"))); //bolt
			inventory.setInventorySlotContents(3, new ItemStack(GameRegistry.findItem(AdvancedArmoury.modid, "assaultBoltCarrierSteel"))); //boltCarrier
			inventory.setInventorySlotContents(4, new ItemStack(GameRegistry.findItem(AdvancedArmoury.modid, "assaultChargingHandleSteel"))); //chargingHandle
			inventory.setInventorySlotContents(5, new ItemStack(GameRegistry.findItem(AdvancedArmoury.modid, "assaultEjectorSteel"))); //ejector
			inventory.setInventorySlotContents(6, new ItemStack(GameRegistry.findItem(AdvancedArmoury.modid, "assaultExtractorSteel"))); //extractor
			inventory.setInventorySlotContents(7, new ItemStack(GameRegistry.findItem(AdvancedArmoury.modid, "assaultFiringPinSteel"))); //firingPin
			inventory.setInventorySlotContents(8, new ItemStack(GameRegistry.findItem(AdvancedArmoury.modid, "assaultFiringPinRetainerPinSteel"))); //firingPinRetainerPin
			
			inventory.setInventorySlotContents(9, new ItemStack(GameRegistry.findItem(AdvancedArmoury.modid, "assaultPistonChamberTitanium"))); //piston/gas chamber
			inventory.setInventorySlotContents(10, new ItemStack(GameRegistry.findItem(AdvancedArmoury.modid, "assaultSpringPromethium"))); //spring/gas feed
			inventory.setInventorySlotContents(11, new ItemStack(GameRegistry.findItem(AdvancedArmoury.modid, "assaultPistonChamberTitanium"))); //piston
			
			inventory.setInventorySlotContents(12, chamber); //chamber
			inventory.setInventorySlotContents(13, barrel); //barrel
			
			inventory.setInventorySlotContents(14, new ItemStack(GameRegistry.findItem(AdvancedArmoury.modid, "triggerPolymer"))); //trigger
			inventory.setInventorySlotContents(15, new ItemStack(GameRegistry.findItem(AdvancedArmoury.modid, "fireSelectorPolymer"))); //fire selector
			
			inventory.setInventorySlotContents(16, new ItemStack(AAItemModifierCores.coreSimpleChamberNet)); //Modifier
			
			inventory.setInventorySlotContents(17, new ItemStack(GameRegistry.findItem(AdvancedArmoury.modid, "m4ReceiverPolymer"))); //receiver
			inventory.setInventorySlotContents(18, new ItemStack(GameRegistry.findItem(AdvancedArmoury.modid, "m4StockPolymer"))); //stock
			inventory.setInventorySlotContents(19, new ItemStack(GameRegistry.findItem(AdvancedArmoury.modid, "m4FrontEndPolymer"))); //frontEnd
			list.add(item);
		}
	}
}
