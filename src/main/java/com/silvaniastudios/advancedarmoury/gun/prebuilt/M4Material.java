package com.silvaniastudios.advancedarmoury.gun.prebuilt;

import java.util.List;

import com.silvaniastudios.advancedarmoury.AdvancedArmoury;
import com.silvaniastudios.advancedarmoury.config.MaterialStats;
import com.silvaniastudios.advancedarmoury.gun.inventory.ItemIInventory;
import com.silvaniastudios.advancedarmoury.gun.inventory.assault.AssaultIInventory;
import com.silvaniastudios.advancedarmoury.items.components.generic.Barrel;
import com.silvaniastudios.advancedarmoury.items.components.generic.GunFrame;
import com.silvaniastudios.advancedarmoury.items_old.AAItemModifierCores;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class M4Material extends GunFrame {
	
	String material;
	
	public M4Material(String material) {
		this.setCreativeTab(AdvancedArmoury.tabGuns);
		this.material = material;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(Item stack, CreativeTabs tab, List list) {
		for (int i = 0; i < Barrel.cal.length; i++) {
			ItemStack item = new ItemStack(stack);
			item.stackTagCompound = new NBTTagCompound();
			item.stackTagCompound.setBoolean("hasInternals", true);
			item.stackTagCompound.setBoolean("isCompletedGun", true);
			item.stackTagCompound.setString("name", MaterialStats.getTextCol(material) + material + " M4A1");
			item.stackTagCompound.setString("tag", "Standardized Firearm, " + Barrel.cal[i] + "mm");
			item.stackTagCompound.setString("creator", "\u00A7c[Advanced Armoury]");
			ItemIInventory inventory = new AssaultIInventory(item);
			
			ItemStack barrel = new ItemStack(GameRegistry.findItem(AdvancedArmoury.modid, "barrel" + material));
			barrel.stackTagCompound = new NBTTagCompound();
			barrel.stackTagCompound.setInteger("length", 15);
			barrel.stackTagCompound.setDouble("calibre", Barrel.cal[i]);
			barrel.stackTagCompound.setString("partName", "barrel");
			item.stackTagCompound.setFloat("accuracy", MaterialStats.getAccuracry("Gold"));
			
			ItemStack chamber = new ItemStack(GameRegistry.findItem(AdvancedArmoury.modid, "assaultChamber" + material));
			chamber.stackTagCompound = new NBTTagCompound();
			chamber.stackTagCompound.setDouble("calibre", Barrel.cal[i]);
			chamber.stackTagCompound.setString("partName", "chamber");
			
			inventory.setInventorySlotContents(2, new ItemStack(GameRegistry.findItem(AdvancedArmoury.modid, "assaultBolt" + material))); //bolt
			inventory.setInventorySlotContents(3, new ItemStack(GameRegistry.findItem(AdvancedArmoury.modid, "assaultBoltCarrier" + material))); //boltCarrier
			inventory.setInventorySlotContents(4, new ItemStack(GameRegistry.findItem(AdvancedArmoury.modid, "assaultChargingHandle" + material))); //chargingHandle
			inventory.setInventorySlotContents(5, new ItemStack(GameRegistry.findItem(AdvancedArmoury.modid, "assaultEjector" + material))); //ejector
			inventory.setInventorySlotContents(6, new ItemStack(GameRegistry.findItem(AdvancedArmoury.modid, "assaultExtractor" + material))); //extractor
			inventory.setInventorySlotContents(7, new ItemStack(GameRegistry.findItem(AdvancedArmoury.modid, "assaultFiringPin" + material))); //firingPin
			inventory.setInventorySlotContents(8, new ItemStack(GameRegistry.findItem(AdvancedArmoury.modid, "assaultFiringPinRetainerPin" + material))); //firingPinRetainerPin
			
			inventory.setInventorySlotContents(9, new ItemStack(GameRegistry.findItem(AdvancedArmoury.modid, "assaultPistonChamber" + material))); //piston/gas chamber
			inventory.setInventorySlotContents(10, new ItemStack(GameRegistry.findItem(AdvancedArmoury.modid, "assaultSpring" + material))); //spring/gas feed
			inventory.setInventorySlotContents(11, new ItemStack(GameRegistry.findItem(AdvancedArmoury.modid, "assaultPistonChamber" + material))); //piston
			
			inventory.setInventorySlotContents(12, chamber); //chamber
			inventory.setInventorySlotContents(13, barrel); //barrel
			
			inventory.setInventorySlotContents(14, new ItemStack(GameRegistry.findItem(AdvancedArmoury.modid, "trigger" + material))); //trigger
			inventory.setInventorySlotContents(15, new ItemStack(GameRegistry.findItem(AdvancedArmoury.modid, "fireSelector" + material))); //fire selector
			
			inventory.setInventorySlotContents(16, new ItemStack(AAItemModifierCores.coreSimpleChamberNet)); //Modifier
			
			inventory.setInventorySlotContents(17, new ItemStack(GameRegistry.findItem(AdvancedArmoury.modid, "m4Receiver" + material))); //receiver
			inventory.setInventorySlotContents(18, new ItemStack(GameRegistry.findItem(AdvancedArmoury.modid, "m4Stock" + material))); //stock
			inventory.setInventorySlotContents(19, new ItemStack(GameRegistry.findItem(AdvancedArmoury.modid, "m4FrontEnd" + material))); //frontEnd
			
			inventory.setInventorySlotContents(20, new ItemStack(GameRegistry.findItem(AdvancedArmoury.modid, "flashHider" + material))); //Barrel Attachment
			list.add(item);
		}
	}

}
