package co.uk.silvania.advancedarmoury;

import java.util.ArrayList;

import co.uk.silvania.advancedarmoury.blocks.decorative.BlockAA;
import co.uk.silvania.advancedarmoury.blocks.decorative.BlockDecorations;
import co.uk.silvania.advancedarmoury.blocks.decorative.BlockHesco;
import co.uk.silvania.advancedarmoury.blocks.decorative.ChainFence;
import co.uk.silvania.advancedarmoury.blocks.decorative.CornerPost;
import co.uk.silvania.advancedarmoury.blocks.decorative.Cover;
import co.uk.silvania.advancedarmoury.blocks.decorative.FenceAA;
import co.uk.silvania.advancedarmoury.blocks.decorative.ItemBlockAA;
import co.uk.silvania.advancedarmoury.blocks.decorative.OpenStairs;
import co.uk.silvania.advancedarmoury.blocks.decorative.Panel;
import co.uk.silvania.advancedarmoury.blocks.decorative.Railing;
import co.uk.silvania.advancedarmoury.blocks.decorative.RailingJungle;
import co.uk.silvania.advancedarmoury.blocks.decorative.RailingSnowy;
import co.uk.silvania.advancedarmoury.blocks.decorative.RailingStairs;
import co.uk.silvania.advancedarmoury.blocks.decorative.SimpleBarrier;
import co.uk.silvania.advancedarmoury.blocks.decorative.Slope225High;
import co.uk.silvania.advancedarmoury.blocks.decorative.Slope225Low;
import co.uk.silvania.advancedarmoury.blocks.decorative.Slope225Vertical;
import co.uk.silvania.advancedarmoury.blocks.decorative.Slope45;
import co.uk.silvania.advancedarmoury.blocks.decorative.StairsAA;
import co.uk.silvania.advancedarmoury.blocks.decorative.WalkwayFence;
import co.uk.silvania.advancedarmoury.blocks.decorative.WalkwayFenceJungle;
import co.uk.silvania.advancedarmoury.blocks.decorative.WalkwayFenceSnowy;
import co.uk.silvania.advancedarmoury.blocks.decorative.WalkwayStairs;
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
		materials.add("lightGreyConcrete");
		
		for (int i = 0; i < materials.size(); i++) {
			String mat = materials.get(i);
			
			Block block = new BlockAA(mat).setBlockName(mat + "Block");
			Block fence = new FenceAA(mat).setBlockName(mat + "Fence");
			Block stairs = new StairsAA(block, mat).setBlockName(mat + "Stairs");
			Block panel = new Panel(mat).setBlockName(mat + "Panel");
			Block cover = new Cover(mat).setBlockName(mat + "Cover");
			Block walkwayFence = new WalkwayFence(mat, "walkwayFence").setBlockName(mat + "WalkwayFence");
			Block walkwayFenceSnowy = new WalkwayFenceSnowy(mat, "walkwayFence").setBlockName(mat + "WalkwayFence");
			Block walkwayFenceJungle = new WalkwayFenceJungle(mat, "walkwayFence").setBlockName(mat + "WalkwayFence");
			Block walkwayStairs = new WalkwayStairs(mat, "walkwayStairs").setBlockName(mat + "WalkwayStairs");
			Block railing = new Railing(mat, "railing").setBlockName(mat + "Railing");
			Block railingSnowy = new RailingSnowy(mat, "railingFence").setBlockName(mat + "RailingSnowy");
			Block railingCamo = new RailingJungle(mat, "railingStairs").setBlockName(mat + "RailingCamo");
			Block railingStairs = new RailingStairs(mat, "railingStairs").setBlockName(mat + "RailingStairs");
			Block openStairs = new OpenStairs(mat, "openStairs").setBlockName(mat + "OpenStairs");
			Block slope45 = new Slope45(block, mat).setBlockName(mat + "Slope45");
			Block slope225Low = new Slope225Low(mat).setBlockName(mat + "Slope225Low");
			Block slope225High = new Slope225High(mat).setBlockName(mat + "Slope225High");
			Block slope225Vertical = new Slope225Vertical(mat).setBlockName(mat + "Slope225Vertical");
			Block cornerPost = new CornerPost(mat, "cornerPost").setBlockName(mat + "CornerPost");
			
			GameRegistry.registerBlock(block, ItemBlockAA.class, mat + "Block");
			GameRegistry.registerBlock(fence, ItemBlockAA.class, mat + "Fence");
			GameRegistry.registerBlock(stairs, ItemBlockAA.class, mat + "Stairs");
			GameRegistry.registerBlock(panel, ItemBlockAA.class, mat + "Panel");
			GameRegistry.registerBlock(cover, ItemBlockAA.class, mat + "Cover");
			GameRegistry.registerBlock(walkwayFence, ItemBlockAA.class, mat + "WalkwayFence");
			GameRegistry.registerBlock(walkwayFenceSnowy, ItemBlockAA.class, mat + "WalkwayFenceSnowy");
			GameRegistry.registerBlock(walkwayFenceJungle, ItemBlockAA.class, mat + "WalkwayFenceJungle");
			GameRegistry.registerBlock(walkwayStairs, ItemBlockAA.class, mat + "WalkwayStairs");
			GameRegistry.registerBlock(railing, ItemBlockAA.class, mat + "Railing");
			GameRegistry.registerBlock(railingSnowy, ItemBlockAA.class, mat + "RailingSnowy");
			GameRegistry.registerBlock(railingCamo, ItemBlockAA.class, mat + "RailingCamo");
			GameRegistry.registerBlock(railingStairs, ItemBlockAA.class, mat + "RailingStairs");
			GameRegistry.registerBlock(openStairs, ItemBlockAA.class, mat + "OpenStairs");
			GameRegistry.registerBlock(slope45, ItemBlockAA.class, mat + "Slope45");
			GameRegistry.registerBlock(slope225Low, ItemBlockAA.class, mat + "Slope225Low");
			GameRegistry.registerBlock(slope225High, ItemBlockAA.class, mat + "Slope225High");
			GameRegistry.registerBlock(slope225Vertical, ItemBlockAA.class, mat + "Slope225Vertical");
			GameRegistry.registerBlock(cornerPost, ItemBlockAA.class, mat + "CornerPost");
			
			AdvancedArmoury.proxy.renderItemBlock(block);
		}
		
		Block standardDecoratives = new BlockDecorations().setBlockName("decorativeBlock1");
		Block hescoBlock100 = new BlockHesco("hesco").setBlockName("hescoBlock100");
		Block plywoodBarrier = new SimpleBarrier("plywood").setBlockName("plywoodBarrier");
		Block chainFence = new ChainFence("chainFence").setBlockName("chainFence");

		//Sand bags
		
		GameRegistry.registerBlock(standardDecoratives, ItemBlockAA.class, "decorativeBlock1");
		GameRegistry.registerBlock(hescoBlock100, ItemBlockAA.class, "hescoBlock100");
		GameRegistry.registerBlock(plywoodBarrier, ItemBlockAA.class, "plywoodBarrier");
		GameRegistry.registerBlock(chainFence, ItemBlockAA.class, "chainFence");
		
		AdvancedArmoury.proxy.renderItemBlock(hescoBlock100);
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
