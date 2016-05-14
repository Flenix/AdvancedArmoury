package co.uk.silvania.advancedarmoury.client;

import java.io.File;
import java.io.IOException;
import java.util.List;

import co.uk.silvania.advancedarmoury.AABlocks;
import co.uk.silvania.advancedarmoury.AAItems;
import co.uk.silvania.advancedarmoury.AdvancedArmoury;
import co.uk.silvania.advancedarmoury.CommonProxy;
import co.uk.silvania.advancedarmoury.blocks.storage.lootcrates.LootCrateEntity;
import co.uk.silvania.advancedarmoury.client.renderers.PartRenderBarrel;
import co.uk.silvania.advancedarmoury.client.renderers.PartRenderBase;
import co.uk.silvania.advancedarmoury.client.renderers.PartRenderComponent;
import co.uk.silvania.advancedarmoury.client.renderers.assault.AssaultReceiverRenderer;
import co.uk.silvania.advancedarmoury.client.renderers.assault.RenderAssaultRifle;
import co.uk.silvania.advancedarmoury.client.renderers.blocks.TinyLootCrateRenderer;
import co.uk.silvania.advancedarmoury.items.AAItemPrebuiltGuns;
import co.uk.silvania.advancedarmoury.items.components.ComponentType;
import co.uk.silvania.advancedarmoury.items.components.asset.AssetFrontEnd;
import co.uk.silvania.advancedarmoury.items.components.asset.AssetReceiver;
import co.uk.silvania.advancedarmoury.items.components.asset.AssetStock;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.ObfuscationReflectionHelper;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.FileResourcePack;
import net.minecraft.client.resources.IResourcePack;
import net.minecraft.item.Item;
import net.minecraftforge.client.MinecraftForgeClient;

public class ClientProxy extends CommonProxy {
	
	public static int tinyLootCrateRenderID;

	@Override
	public void registerRenderers() {		
		for (ComponentType componentType : ComponentType.items.values()) {
			Item item = GameRegistry.findItem(AdvancedArmoury.modid, componentType.unlocalizedName);
			if (item instanceof AssetFrontEnd) {
				MinecraftForgeClient.registerItemRenderer(item, new PartRenderBase(componentType.modelName, componentType.modelTexture, true));
			}
			if (item instanceof AssetReceiver) {
				MinecraftForgeClient.registerItemRenderer(item, new AssaultReceiverRenderer(componentType.modelName, componentType.modelTexture, true, 0));
				
			}
			if (item instanceof AssetStock) {
				MinecraftForgeClient.registerItemRenderer(item, new PartRenderBase(componentType.modelName, componentType.modelTexture, true));
			}
		}
		
		MinecraftForgeClient.registerItemRenderer(AAItems.m4mag, new PartRenderBase("m4mag", "m4mag", true));
		MinecraftForgeClient.registerItemRenderer(AAItems.assaultFrame, new RenderAssaultRifle());
		MinecraftForgeClient.registerItemRenderer(AAItems.itemRound, new RoundRenderer());
		
		renderBlocks();
		renderPrebuiltGuns();
	}
	
	public static void renderBlocks() {
		ClientRegistry.bindTileEntitySpecialRenderer(LootCrateEntity.class, new TinyLootCrateRenderer());
		tinyLootCrateRenderID = RenderingRegistry.getNextAvailableRenderId();
		
		RenderingRegistry.registerBlockHandler(AABlocks.tinyLootCrate.getRenderType(), new TinyLootCrateRenderer());
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
	public void renderReceivers(Item item, String material, int rgb) {
		MinecraftForgeClient.registerItemRenderer(item, new AssaultReceiverRenderer("m4receiver", material, false, rgb));
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
