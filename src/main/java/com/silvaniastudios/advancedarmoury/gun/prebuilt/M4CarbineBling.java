package com.silvaniastudios.advancedarmoury.gun.prebuilt;

import java.util.List;

import com.silvaniastudios.advancedarmoury.AdvancedArmoury;
import com.silvaniastudios.advancedarmoury.config.ComponentGenerator;
import com.silvaniastudios.advancedarmoury.config.MaterialStats;
import com.silvaniastudios.advancedarmoury.gun.inventory.ItemIInventory;
import com.silvaniastudios.advancedarmoury.gun.inventory.assault.AssaultIInventory;
import com.silvaniastudios.advancedarmoury.items.components.generic.GunFrame;
import com.silvaniastudios.advancedarmoury.items_old.AAItemModifierCores;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class M4CarbineBling extends GunFrame {
	
	public M4CarbineBling() {
		this.setCreativeTab(AdvancedArmoury.tabGuns);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(Item stack, CreativeTabs tab, List list) {
		if (ComponentGenerator.matExists("Gold") && 
				ComponentGenerator.matExists("Steel") && 
				ComponentGenerator.matExists("Darisin") && 
				ComponentGenerator.matExists("Promethium") &&
				ComponentGenerator.matExists("Titanium")) {

			ItemStack item = new ItemStack(stack);
			item.stackTagCompound = new NBTTagCompound();
			item.stackTagCompound.setBoolean("hasInternals", true);
			item.stackTagCompound.setBoolean("isCompletedGun", true);
			item.stackTagCompound.setString("name", "&e&lM4A1 Bling");
			item.stackTagCompound.setString("tag", "&oYou used to shoot me with your carbine...");
			ItemIInventory inventory = new AssaultIInventory(item);
			
			ItemStack barrel = new ItemStack(GameRegistry.findItem(AdvancedArmoury.modid, "barrelGold"));
			barrel.stackTagCompound = new NBTTagCompound();
			barrel.stackTagCompound.setInteger("length", 15);
			barrel.stackTagCompound.setDouble("calibre", 5.56);
			barrel.stackTagCompound.setString("partName", "barrel");
			item.stackTagCompound.setFloat("accuracy", MaterialStats.getAccuracry("Gold"));
			
			ItemStack chamber = new ItemStack(GameRegistry.findItem(AdvancedArmoury.modid, "assaultChamberSteel"));
			chamber.stackTagCompound = new NBTTagCompound();
			chamber.stackTagCompound.setDouble("calibre", 5.56);
			chamber.stackTagCompound.setString("partName", "chamber");
			
			inventory.setInventorySlotContents(2, new ItemStack(GameRegistry.findItem(AdvancedArmoury.modid, "assaultBoltDarisin"))); //bolt
			inventory.setInventorySlotContents(3, new ItemStack(GameRegistry.findItem(AdvancedArmoury.modid, "assaultBoltCarrierSteel"))); //boltCarrier
			inventory.setInventorySlotContents(4, new ItemStack(GameRegistry.findItem(AdvancedArmoury.modid, "assaultChargingHandleDarisin"))); //chargingHandle
			inventory.setInventorySlotContents(5, new ItemStack(GameRegistry.findItem(AdvancedArmoury.modid, "assaultEjectorSteel"))); //ejector
			inventory.setInventorySlotContents(6, new ItemStack(GameRegistry.findItem(AdvancedArmoury.modid, "assaultExtractorSteel"))); //extractor
			inventory.setInventorySlotContents(7, new ItemStack(GameRegistry.findItem(AdvancedArmoury.modid, "assaultFiringPinSteel"))); //firingPin
			inventory.setInventorySlotContents(8, new ItemStack(GameRegistry.findItem(AdvancedArmoury.modid, "assaultFiringPinRetainerPinSteel"))); //firingPinRetainerPin
			
			inventory.setInventorySlotContents(9, new ItemStack(GameRegistry.findItem(AdvancedArmoury.modid, "assaultPistonChamberTitanium"))); //piston/gas chamber
			inventory.setInventorySlotContents(10, new ItemStack(GameRegistry.findItem(AdvancedArmoury.modid, "assaultSpringPromethium"))); //spring/gas feed
			inventory.setInventorySlotContents(11, new ItemStack(GameRegistry.findItem(AdvancedArmoury.modid, "assaultPistonChamberTitanium"))); //piston
			
			inventory.setInventorySlotContents(12, chamber); //chamber
			inventory.setInventorySlotContents(13, barrel); //barrel
			
			inventory.setInventorySlotContents(14, new ItemStack(GameRegistry.findItem(AdvancedArmoury.modid, "triggerDarisin"))); //trigger
			inventory.setInventorySlotContents(15, new ItemStack(GameRegistry.findItem(AdvancedArmoury.modid, "fireSelectorDarisin"))); //fire selector
			
			inventory.setInventorySlotContents(16, new ItemStack(AAItemModifierCores.coreSimpleChamberNet)); //Modifier
			
			inventory.setInventorySlotContents(17, new ItemStack(GameRegistry.findItem(AdvancedArmoury.modid, "m4ReceiverGold"))); //receiver
			inventory.setInventorySlotContents(18, new ItemStack(GameRegistry.findItem(AdvancedArmoury.modid, "m4StockGold"))); //stock
			inventory.setInventorySlotContents(19, new ItemStack(GameRegistry.findItem(AdvancedArmoury.modid, "m4FrontEndGold"))); //frontEnd
			
			inventory.setInventorySlotContents(20, new ItemStack(GameRegistry.findItem(AdvancedArmoury.modid, "flashHiderGold"))); //Barrel Attachment
			list.add(item);
		}
	}

}
