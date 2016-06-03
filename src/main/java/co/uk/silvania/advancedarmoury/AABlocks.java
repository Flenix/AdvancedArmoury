package co.uk.silvania.advancedarmoury;

import java.util.ArrayList;

import co.uk.silvania.advancedarmoury.blocks.decorative.BlockMilitaryBase;
import co.uk.silvania.advancedarmoury.blocks.decorative.CornerPostMilitaryBase;
import co.uk.silvania.advancedarmoury.blocks.decorative.CoverMilitaryBase;
import co.uk.silvania.advancedarmoury.blocks.decorative.FenceMilitaryBase;
import co.uk.silvania.advancedarmoury.blocks.decorative.ItemBlockMilitaryBase;
import co.uk.silvania.advancedarmoury.blocks.decorative.OpenStairsMilitaryBase;
import co.uk.silvania.advancedarmoury.blocks.decorative.PanelMilitaryBase;
import co.uk.silvania.advancedarmoury.blocks.decorative.RailingJungleBase;
import co.uk.silvania.advancedarmoury.blocks.decorative.RailingMilitaryBase;
import co.uk.silvania.advancedarmoury.blocks.decorative.RailingSnowyBase;
import co.uk.silvania.advancedarmoury.blocks.decorative.RailingStairsMilitaryBase;
import co.uk.silvania.advancedarmoury.blocks.decorative.SlabMilitaryBase;
import co.uk.silvania.advancedarmoury.blocks.decorative.Slope45MilitaryBase;
import co.uk.silvania.advancedarmoury.blocks.decorative.StairsMilitaryBase;
import co.uk.silvania.advancedarmoury.blocks.decorative.WalkwayFenceJungleBase;
import co.uk.silvania.advancedarmoury.blocks.decorative.WalkwayFenceMilitaryBase;
import co.uk.silvania.advancedarmoury.blocks.decorative.WalkwayFenceSnowyBase;
import co.uk.silvania.advancedarmoury.blocks.decorative.WalkwayStairsMilitaryBase;
import co.uk.silvania.advancedarmoury.blocks.machines.assemblytable.ComponentAssemblyTable;
import co.uk.silvania.advancedarmoury.blocks.machines.assemblytable.ComponentAssemblyTableEntity;
import co.uk.silvania.advancedarmoury.blocks.machines.assemblytable.assault.AssaultAssemblyTable;
import co.uk.silvania.advancedarmoury.blocks.machines.assemblytable.assault.AssaultAssemblyTableEntity;
import co.uk.silvania.advancedarmoury.blocks.storage.lootcrates.LootCrateEntity;
import co.uk.silvania.advancedarmoury.blocks.storage.lootcrates.TinyLootCrate;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;

public class AABlocks {
	
	public static Block componentAssemblyTable;
	public static Block assaultRifleAssemblyTable;
	
	public static Block tinyLootCrate;
	
	public static Block militaryBaseStandard;
	public static Block militaryBaseStandardSlab;
	
	public static void init() {
		machines();
		lootCrates();
		decorative();
		
		registerBlocks();
		registerTileEntities();
	}
	
	public static void machines() {
		componentAssemblyTable = new ComponentAssemblyTable().setBlockName("componentAssemblyTable");
		assaultRifleAssemblyTable = new AssaultAssemblyTable().setBlockName("assaultRifleAssemblyTable");
	}
	
	public static void lootCrates() {
		tinyLootCrate = new TinyLootCrate().setBlockName("tinyLootCrate");
	}
	
	public static void decorative() {
		ArrayList<String> materials = new ArrayList<String>();
		
		materials.add("concrete");
		materials.add("darkConcrete");
		materials.add("paleConcrete");
		materials.add("greenConcrete");
		materials.add("redCement");
		materials.add("blueCement");
		materials.add("blackCement");
		materials.add("sandyCement");
		
		for (int i = 0; i < materials.size(); i++) {
			String mat = materials.get(i);
			
			Block block = new BlockMilitaryBase(mat).setBlockName(mat + "Block");
			Block fence = new FenceMilitaryBase(mat).setBlockName(mat + "Fence");
			Block stairs = new StairsMilitaryBase(block, mat).setBlockName(mat + "Stairs");
			Block slab = new SlabMilitaryBase(block, mat).setBlockName(mat + "Slab");
			Block panel = new PanelMilitaryBase(slab, mat).setBlockName(mat + "Panel");
			Block cover = new CoverMilitaryBase(panel, mat).setBlockName(mat + "Cover");
			Block walkwayFence = new WalkwayFenceMilitaryBase(mat, "walkwayFence").setBlockName(mat + "WalkwayFence");
			Block walkwayFenceSnowy = new WalkwayFenceSnowyBase(mat, "walkwayFence").setBlockName(mat + "WalkwayFence");
			Block walkwayFenceJungle = new WalkwayFenceJungleBase(mat, "walkwayFence").setBlockName(mat + "WalkwayFence");
			Block walkwayStairs = new WalkwayStairsMilitaryBase(mat, "walkwayStairs").setBlockName(mat + "WalkwayStairs");
			Block railing = new RailingMilitaryBase(mat, "railing").setBlockName(mat + "Railing");
			Block railingSnowy = new RailingSnowyBase(mat, "railingFence").setBlockName(mat + "RailingSnowy");
			Block railingCamo = new RailingJungleBase(mat, "railingStairs").setBlockName(mat + "RailingCamo");
			Block railingStairs = new RailingStairsMilitaryBase(mat, "railingStairs").setBlockName(mat + "RailingStairs");
			Block openStairs = new OpenStairsMilitaryBase(mat, "openStairs").setBlockName(mat + "OpenStairs");
			Block slope45 = new Slope45MilitaryBase(block, mat).setBlockName(mat + "Slope45");
			//Block cornerPost = new CornerPostMilitaryBase(mat, "cornerPost").setBlockName(mat + "CornerPost");
			
			GameRegistry.registerBlock(block, ItemBlockMilitaryBase.class, mat + "Block");
			GameRegistry.registerBlock(fence, ItemBlockMilitaryBase.class, mat + "Fence");
			GameRegistry.registerBlock(stairs, ItemBlockMilitaryBase.class, mat + "Stairs");
			GameRegistry.registerBlock(slab, ItemBlockMilitaryBase.class, mat + "Slab");
			GameRegistry.registerBlock(panel, ItemBlockMilitaryBase.class, mat + "Panel");
			GameRegistry.registerBlock(cover, ItemBlockMilitaryBase.class, mat + "Cover");
			GameRegistry.registerBlock(walkwayFence, ItemBlockMilitaryBase.class, mat + "WalkwayFence");
			GameRegistry.registerBlock(walkwayFenceSnowy, ItemBlockMilitaryBase.class, mat + "WalkwayFenceSnowy");
			GameRegistry.registerBlock(walkwayFenceJungle, ItemBlockMilitaryBase.class, mat + "WalkwayFenceJungle");
			GameRegistry.registerBlock(walkwayStairs, ItemBlockMilitaryBase.class, mat + "WalkwayStairs");
			GameRegistry.registerBlock(railing, ItemBlockMilitaryBase.class, mat + "Railing");
			GameRegistry.registerBlock(railingSnowy, ItemBlockMilitaryBase.class, mat + "RailingSnowy");
			GameRegistry.registerBlock(railingCamo, ItemBlockMilitaryBase.class, mat + "RailingCamo");
			GameRegistry.registerBlock(railingStairs, ItemBlockMilitaryBase.class, mat + "RailingStairs");
			GameRegistry.registerBlock(openStairs, ItemBlockMilitaryBase.class, mat + "OpenStairs");
			GameRegistry.registerBlock(slope45, ItemBlockMilitaryBase.class, mat + "Slope45");
			//GameRegistry.registerBlock(cornerPost, ItemBlockMilitaryBase.class, mat + "CornerPost");
		}
	}
	
	public static void registerBlocks() {
		GameRegistry.registerBlock(componentAssemblyTable, "componentAssemblyTable");
		GameRegistry.registerBlock(assaultRifleAssemblyTable, "assaultRifleAssemblyTable");
		
		GameRegistry.registerBlock(tinyLootCrate, "tinyLootCrate");
	}
	
	public static void registerTileEntities() {
		GameRegistry.registerTileEntity(ComponentAssemblyTableEntity.class, "entityComponentAssemblyTable");
		GameRegistry.registerTileEntity(AssaultAssemblyTableEntity.class, "entityAssaultAssemblyTable");
		
		GameRegistry.registerTileEntity(LootCrateEntity.class, "lootCrateEntity");
	}

}
