package co.uk.silvania.advancedarmoury;

import co.uk.silvania.advancedarmoury.blocks.machines.assemblytable.assault.AssaultAssemblyTable;
import co.uk.silvania.advancedarmoury.blocks.machines.assemblytable.assault.AssaultAssemblyTableEntity;
import co.uk.silvania.advancedarmoury.blocks.storage.lootcrates.LootCrateEntity;
import co.uk.silvania.advancedarmoury.blocks.storage.lootcrates.TinyLootCrate;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;

public class AABlocks {
	
	public static Block assaultRifleAssemblyTable;
	public static Block assaultRifleEncasementTable;
	
	public static Block tinyLootCrate;
	
	public static void init() {
		machines();
		lootCrates();
		
		registerBlocks();
		registerTileEntities();
	}
	
	public static void machines() {
		assaultRifleAssemblyTable = new AssaultAssemblyTable().setBlockName("assaultRifleAssemblyTable");
	}
	
	public static void lootCrates() {
		tinyLootCrate = new TinyLootCrate().setBlockName("tinyLootCrate");
	}
	
	public static void registerBlocks() {
		GameRegistry.registerBlock(assaultRifleAssemblyTable, "assaultRifleAssemblyTable");
		
		GameRegistry.registerBlock(tinyLootCrate, "tinyLootCrate");
	}
	
	public static void registerTileEntities() {
		GameRegistry.registerTileEntity(AssaultAssemblyTableEntity.class, "entityAssaultAssemblyTable");
		
		GameRegistry.registerTileEntity(LootCrateEntity.class, "lootCrateEntity");
	}

}
