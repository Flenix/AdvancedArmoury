package com.silvaniastudios.advancedarmoury.client;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.silvaniastudios.advancedarmoury.AABlocks;
import com.silvaniastudios.advancedarmoury.AAItems;
import com.silvaniastudios.advancedarmoury.AdvancedArmoury;
import com.silvaniastudios.advancedarmoury.CommonProxy;
import com.silvaniastudios.advancedarmoury.blocks.storage.lootcrates.LootCrateEntity;
import com.silvaniastudios.advancedarmoury.client.renderers.PartRenderBarrel;
import com.silvaniastudios.advancedarmoury.client.renderers.PartRenderBase;
import com.silvaniastudios.advancedarmoury.client.renderers.PartRenderComponent;
import com.silvaniastudios.advancedarmoury.client.renderers.assault.AssaultReceiverRenderer;
import com.silvaniastudios.advancedarmoury.client.renderers.assault.RenderAssaultRifle;
import com.silvaniastudios.advancedarmoury.client.renderers.blocks.ChainFenceRenderer;
import com.silvaniastudios.advancedarmoury.client.renderers.blocks.CornerPostRenderer;
import com.silvaniastudios.advancedarmoury.client.renderers.blocks.ItemBlockRenderer;
import com.silvaniastudios.advancedarmoury.client.renderers.blocks.OpenStairsRenderer;
import com.silvaniastudios.advancedarmoury.client.renderers.blocks.RailingRenderer;
import com.silvaniastudios.advancedarmoury.client.renderers.blocks.SimpleBarrierRenderer;
import com.silvaniastudios.advancedarmoury.client.renderers.blocks.Slope225HighRenderer;
import com.silvaniastudios.advancedarmoury.client.renderers.blocks.Slope225LowRenderer;
import com.silvaniastudios.advancedarmoury.client.renderers.blocks.Slope225VerticalRenderer;
import com.silvaniastudios.advancedarmoury.client.renderers.blocks.Slope45Renderer;
import com.silvaniastudios.advancedarmoury.client.renderers.blocks.TinyLootCrateRenderer;
import com.silvaniastudios.advancedarmoury.client.renderers.blocks.WalkwayFenceRenderer;
import com.silvaniastudios.advancedarmoury.client.renderers.blocks.WalkwayStairsRenderer;
import com.silvaniastudios.advancedarmoury.items.assets.AssetFrontEnd;
import com.silvaniastudios.advancedarmoury.items.assets.AssetReceiver;
import com.silvaniastudios.advancedarmoury.items.assets.AssetStock;
import com.silvaniastudios.advancedarmoury.items.assets.ComponentType;
import com.silvaniastudios.advancedarmoury.items_old.AAItemPrebuiltGuns;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.ObfuscationReflectionHelper;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.FileResourcePack;
import net.minecraft.client.resources.IResourcePack;
import net.minecraft.item.Item;
import net.minecraftforge.client.MinecraftForgeClient;

public class ClientProxy extends CommonProxy {
	
	public static int tinyLootCrateRenderID;
	
	public static int walkwayFenceRenderID;
	public static int walkwayFenceSnowyRenderID;
	public static int walkwayFenceJungleRenderID;
	public static int walkwayStairsRenderID;
	public static int railingRenderID;
	public static int railingSnowyRenderID;
	public static int railingJungleRenderID;
	public static int railingStairsRenderID;
	public static int openStairsRenderID;
	public static int slope45RenderID;
	public static int slope225LowRenderID;
	public static int slope225HighRenderID;
	public static int slope225VerticalRenderID;
	public static int cornerPostRenderID;
	
	public static int simpleBarrierRenderID;
	public static int chainFenceRenderID;
	
	@Override
	public void registerRenderers() {		
		for (ComponentType componentType : ComponentType.items.values()) {
			Item item = GameRegistry.findItem(AdvancedArmoury.modid, componentType.unlocalizedName);
			if (item instanceof AssetFrontEnd) {
				//MinecraftForgeClient.registerItemRenderer(item, new PartRenderBase(componentType.modelName, componentType.modelTexture, true));
			}
			if (item instanceof AssetReceiver) {
				//MinecraftForgeClient.registerItemRenderer(item, new AssaultReceiverRenderer(componentType.modelName, componentType.modelTexture, true, 0));
				
			}
			if (item instanceof AssetStock) {
				//MinecraftForgeClient.registerItemRenderer(item, new PartRenderBase(componentType.modelName, componentType.modelTexture, true));
			}
		}
		
		MinecraftForgeClient.registerItemRenderer(AAItems.m4mag, new PartRenderBase("m4mag", "m4mag", true));
		MinecraftForgeClient.registerItemRenderer(AAItems.gunFrame, new RenderAssaultRifle());
		MinecraftForgeClient.registerItemRenderer(AAItems.itemRound, new RoundRenderer());
		
		renderBlocks();
		renderPrebuiltGuns();
	}
	
	public static void renderBlocks() {
		ClientRegistry.bindTileEntitySpecialRenderer(LootCrateEntity.class, new TinyLootCrateRenderer());
		
		tinyLootCrateRenderID = RenderingRegistry.getNextAvailableRenderId();
		
		walkwayFenceRenderID = RenderingRegistry.getNextAvailableRenderId();
		walkwayFenceSnowyRenderID = RenderingRegistry.getNextAvailableRenderId();
		walkwayFenceJungleRenderID = RenderingRegistry.getNextAvailableRenderId();
		walkwayStairsRenderID = RenderingRegistry.getNextAvailableRenderId();
		railingRenderID = RenderingRegistry.getNextAvailableRenderId();
		railingSnowyRenderID = RenderingRegistry.getNextAvailableRenderId();
		railingJungleRenderID = RenderingRegistry.getNextAvailableRenderId();
		railingStairsRenderID = RenderingRegistry.getNextAvailableRenderId();
		openStairsRenderID = RenderingRegistry.getNextAvailableRenderId();
		slope45RenderID = RenderingRegistry.getNextAvailableRenderId();
		slope225LowRenderID = RenderingRegistry.getNextAvailableRenderId();
		slope225HighRenderID = RenderingRegistry.getNextAvailableRenderId();
		slope225VerticalRenderID = RenderingRegistry.getNextAvailableRenderId();
		cornerPostRenderID = RenderingRegistry.getNextAvailableRenderId();
		
		simpleBarrierRenderID = RenderingRegistry.getNextAvailableRenderId();
		chainFenceRenderID = RenderingRegistry.getNextAvailableRenderId();
		
		RenderingRegistry.registerBlockHandler(AABlocks.tinyLootCrate.getRenderType(), new TinyLootCrateRenderer());

		RenderingRegistry.registerBlockHandler(walkwayFenceRenderID, new WalkwayFenceRenderer(false, false));
		RenderingRegistry.registerBlockHandler(walkwayFenceSnowyRenderID, new WalkwayFenceRenderer(true, false));
		RenderingRegistry.registerBlockHandler(walkwayFenceJungleRenderID, new WalkwayFenceRenderer(false, true));
		RenderingRegistry.registerBlockHandler(walkwayStairsRenderID, new WalkwayStairsRenderer());
		RenderingRegistry.registerBlockHandler(openStairsRenderID, new OpenStairsRenderer());
		RenderingRegistry.registerBlockHandler(railingRenderID, new RailingRenderer(false, false));
		RenderingRegistry.registerBlockHandler(railingSnowyRenderID, new RailingRenderer(true, false));
		RenderingRegistry.registerBlockHandler(railingJungleRenderID, new RailingRenderer(false, true));
		RenderingRegistry.registerBlockHandler(slope45RenderID, new Slope45Renderer());
		RenderingRegistry.registerBlockHandler(slope225LowRenderID, new Slope225LowRenderer());
		RenderingRegistry.registerBlockHandler(slope225HighRenderID, new Slope225HighRenderer());
		RenderingRegistry.registerBlockHandler(slope225VerticalRenderID, new Slope225VerticalRenderer());
		RenderingRegistry.registerBlockHandler(cornerPostRenderID, new CornerPostRenderer());
		
		RenderingRegistry.registerBlockHandler(simpleBarrierRenderID, new SimpleBarrierRenderer());
		RenderingRegistry.registerBlockHandler(chainFenceRenderID, new ChainFenceRenderer());
	}
	
	@Override
	public void renderItemBlock(Block block) {
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(block), new ItemBlockRenderer());
	}
	
	public static void renderPrebuiltGuns() {
		MinecraftForgeClient.registerItemRenderer(AAItemPrebuiltGuns.m4CarbineStandard, new RenderAssaultRifle());
		MinecraftForgeClient.registerItemRenderer(AAItemPrebuiltGuns.m4CarbineBling, new RenderAssaultRifle());
	}
	
	@Override
	public void reflectResourcePackMethods() {
		AdvancedArmoury.println("Reflecting in and loading resources");
		List<IResourcePack> assetPacks = ObfuscationReflectionHelper.getPrivateValue(Minecraft.class, Minecraft.getMinecraft(), "defaultResourcePacks", "field_110449_ao");
		List<File> assets = getAssetPackList();
		
		for (File file : AdvancedArmoury.assetDir.listFiles()) {
			try {
				if (AdvancedArmoury.isZipFile(file)) {
					assetPacks.add(new FileResourcePack(file));
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		System.out.println("Refreshing game resources to make stuff available.");
		Minecraft.getMinecraft().refreshResources();
	}
	/*
	 * Force users with asset packs to use correct mod formatting style.
	 */
	
	
	@Override
	public void renderTriggers(Item item, String material) {
		MinecraftForgeClient.registerItemRenderer(item, new PartRenderComponent("trigger", material));
	}
	
	@Override
	public void renderBarrels(Item item, String material) {
		MinecraftForgeClient.registerItemRenderer(item, new PartRenderBarrel(material));
	}
	
	@Override
	public void renderFlashHiders(Item item, String material) {
		MinecraftForgeClient.registerItemRenderer(item, new PartRenderComponent("flashHider", material));
	}
	
	@Override
	public void renderReceivers(Item item, String material) {
		MinecraftForgeClient.registerItemRenderer(item, new AssaultReceiverRenderer("m4receiver", material, false));
	}
	
	@Override
	public void renderFrontEnds(Item item, String material) {
		MinecraftForgeClient.registerItemRenderer(item, new PartRenderBase("m4frontEnd", material, false));
	}
	
	@Override
	public void renderStocks(Item item, String material) {
		MinecraftForgeClient.registerItemRenderer(item, new PartRenderBase("m4stock", material, false));
	}
	
	@Override
	public void renderAssaultRifles(Item item) {
		MinecraftForgeClient.registerItemRenderer(item, new RenderAssaultRifle());
	}

}
