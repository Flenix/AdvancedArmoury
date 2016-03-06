package co.uk.silvania.advancedarmoury.client;

import co.uk.silvania.advancedarmoury.AABlocks;
import co.uk.silvania.advancedarmoury.blocks.storage.lootcrates.LootCrateEntity;
import co.uk.silvania.advancedarmoury.client.renderers.PartRenderBarrel;
import co.uk.silvania.advancedarmoury.client.renderers.PartRenderBase;
import co.uk.silvania.advancedarmoury.client.renderers.PartRenderComponent;
import co.uk.silvania.advancedarmoury.client.renderers.assault.AssaultReceiverRenderer;
import co.uk.silvania.advancedarmoury.client.renderers.assault.RenderAssaultRifle;
import co.uk.silvania.advancedarmoury.client.renderers.blocks.TinyLootCrateRenderer;
import co.uk.silvania.advancedarmoury.items.AAItemAssaultComponents;
import co.uk.silvania.advancedarmoury.items.AAItemComponents;
import co.uk.silvania.advancedarmoury.items.AAItemPrebuiltGuns;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import net.minecraftforge.client.MinecraftForgeClient;

public class RenderRegisters {
	
	public static int tinyLootCrateRenderID;
	
	public static void initRenders() {
		renderBlocks();
		renderPrebuiltGuns();
		renderTriggers();
		renderBarrels();
		renderFlashHiders();
		renderReceivers();
		renderFrontEnds();
		renderStocks();
	}
	
	public static void renderBlocks() {
		ClientRegistry.bindTileEntitySpecialRenderer(LootCrateEntity.class, new TinyLootCrateRenderer());
		tinyLootCrateRenderID = RenderingRegistry.getNextAvailableRenderId();
		
		RenderingRegistry.registerBlockHandler(AABlocks.tinyLootCrate.getRenderType(), new TinyLootCrateRenderer());
	}
	
	public static void renderPrebuiltGuns() {
		MinecraftForgeClient.registerItemRenderer(AAItemPrebuiltGuns.m4CarbinePolymer, new RenderAssaultRifle());
		MinecraftForgeClient.registerItemRenderer(AAItemPrebuiltGuns.m4CarbineBling, new RenderAssaultRifle());
	}
	
	public static void renderTriggers() {
		MinecraftForgeClient.registerItemRenderer(AAItemComponents.triggerGold, new PartRenderComponent("trigger", "Gold"));
		MinecraftForgeClient.registerItemRenderer(AAItemComponents.triggerIron, new PartRenderComponent("trigger", "Iron"));
		MinecraftForgeClient.registerItemRenderer(AAItemComponents.triggerBronze, new PartRenderComponent("trigger", "Bronze"));
		MinecraftForgeClient.registerItemRenderer(AAItemComponents.triggerTitanium, new PartRenderComponent("trigger", "Titanium"));
		MinecraftForgeClient.registerItemRenderer(AAItemComponents.triggerAluminium, new PartRenderComponent("trigger", "Aluminium"));
		MinecraftForgeClient.registerItemRenderer(AAItemComponents.triggerSteel, new PartRenderComponent("trigger", "Steel"));
		MinecraftForgeClient.registerItemRenderer(AAItemComponents.triggerPolymer, new PartRenderComponent("trigger", "Polymer"));
		MinecraftForgeClient.registerItemRenderer(AAItemComponents.triggerMythian, new PartRenderComponent("trigger", "Mythian"));
		MinecraftForgeClient.registerItemRenderer(AAItemComponents.triggerPromethium, new PartRenderComponent("trigger", "Promethium"));
		MinecraftForgeClient.registerItemRenderer(AAItemComponents.triggerSarinum, new PartRenderComponent("trigger", "Sarinum"));
		MinecraftForgeClient.registerItemRenderer(AAItemComponents.triggerVelerium, new PartRenderComponent("trigger", "Velerium"));
		MinecraftForgeClient.registerItemRenderer(AAItemComponents.triggerCelibras, new PartRenderComponent("trigger", "Celibras"));
		MinecraftForgeClient.registerItemRenderer(AAItemComponents.triggerAericas, new PartRenderComponent("trigger", "Aericas"));
		MinecraftForgeClient.registerItemRenderer(AAItemComponents.triggerDarisin, new PartRenderComponent("trigger", "Darisin"));
	}
	
	public static void renderBarrels() {
		MinecraftForgeClient.registerItemRenderer(AAItemComponents.barrelGold, new PartRenderBarrel("Gold"));
		MinecraftForgeClient.registerItemRenderer(AAItemComponents.barrelIron, new PartRenderBarrel("Iron"));
		MinecraftForgeClient.registerItemRenderer(AAItemComponents.barrelBronze, new PartRenderBarrel("Bronze"));
		MinecraftForgeClient.registerItemRenderer(AAItemComponents.barrelTitanium, new PartRenderBarrel("Titanium"));
		MinecraftForgeClient.registerItemRenderer(AAItemComponents.barrelAluminium, new PartRenderBarrel("Aluminium"));
		MinecraftForgeClient.registerItemRenderer(AAItemComponents.barrelSteel, new PartRenderBarrel("Steel"));
		MinecraftForgeClient.registerItemRenderer(AAItemComponents.barrelPolymer, new PartRenderBarrel("Polymer"));
		MinecraftForgeClient.registerItemRenderer(AAItemComponents.barrelMythian, new PartRenderBarrel("Mythian"));
		MinecraftForgeClient.registerItemRenderer(AAItemComponents.barrelPromethium, new PartRenderBarrel("Promethium"));
		MinecraftForgeClient.registerItemRenderer(AAItemComponents.barrelSarinum, new PartRenderBarrel("Sarinum"));
		MinecraftForgeClient.registerItemRenderer(AAItemComponents.barrelVelerium, new PartRenderBarrel("Velerium"));
		MinecraftForgeClient.registerItemRenderer(AAItemComponents.barrelCelibras, new PartRenderBarrel("Celibras"));
		MinecraftForgeClient.registerItemRenderer(AAItemComponents.barrelAericas, new PartRenderBarrel("Aericas"));
		MinecraftForgeClient.registerItemRenderer(AAItemComponents.barrelDarisin, new PartRenderBarrel("Darisin"));
	}
	
	public static void renderFlashHiders() {
		MinecraftForgeClient.registerItemRenderer(AAItemComponents.flashHiderGold, new PartRenderComponent("flashHider", "Gold"));
		MinecraftForgeClient.registerItemRenderer(AAItemComponents.flashHiderIron, new PartRenderComponent("flashHider", "Iron"));
		MinecraftForgeClient.registerItemRenderer(AAItemComponents.flashHiderBronze, new PartRenderComponent("flashHider", "Bronze"));
		MinecraftForgeClient.registerItemRenderer(AAItemComponents.flashHiderTitanium, new PartRenderComponent("flashHider", "Titanium"));
		MinecraftForgeClient.registerItemRenderer(AAItemComponents.flashHiderAluminium, new PartRenderComponent("flashHider", "Aluminium"));
		MinecraftForgeClient.registerItemRenderer(AAItemComponents.flashHiderSteel, new PartRenderComponent("flashHider", "Steel"));
		MinecraftForgeClient.registerItemRenderer(AAItemComponents.flashHiderPolymer, new PartRenderComponent("flashHider", "Polymer"));
		MinecraftForgeClient.registerItemRenderer(AAItemComponents.flashHiderMythian, new PartRenderComponent("flashHider", "Mythian"));
		MinecraftForgeClient.registerItemRenderer(AAItemComponents.flashHiderPromethium, new PartRenderComponent("flashHider", "Promethium"));
		MinecraftForgeClient.registerItemRenderer(AAItemComponents.flashHiderSarinum, new PartRenderComponent("flashHider", "Sarinum"));
		MinecraftForgeClient.registerItemRenderer(AAItemComponents.flashHiderVelerium, new PartRenderComponent("flashHider", "Velerium"));
		MinecraftForgeClient.registerItemRenderer(AAItemComponents.flashHiderCelibras, new PartRenderComponent("flashHider", "Celibras"));
		MinecraftForgeClient.registerItemRenderer(AAItemComponents.flashHiderAericas, new PartRenderComponent("flashHider", "Aericas"));
		MinecraftForgeClient.registerItemRenderer(AAItemComponents.flashHiderDarisin, new PartRenderComponent("flashHider", "Darisin"));
	}
	
	public static void renderReceivers() {
		MinecraftForgeClient.registerItemRenderer(AAItemAssaultComponents.assaultReceiverM4Gold, new AssaultReceiverRenderer("m4receiver", "textureGold"));
		MinecraftForgeClient.registerItemRenderer(AAItemAssaultComponents.assaultReceiverM4Iron, new AssaultReceiverRenderer("m4receiver", "textureIron"));
		MinecraftForgeClient.registerItemRenderer(AAItemAssaultComponents.assaultReceiverM4Bronze, new AssaultReceiverRenderer("m4receiver", "textureBronze"));
		MinecraftForgeClient.registerItemRenderer(AAItemAssaultComponents.assaultReceiverM4Titanium, new AssaultReceiverRenderer("m4receiver", "textureTitanium"));
		MinecraftForgeClient.registerItemRenderer(AAItemAssaultComponents.assaultReceiverM4Aluminium, new AssaultReceiverRenderer("m4receiver", "textureAluminium"));
		MinecraftForgeClient.registerItemRenderer(AAItemAssaultComponents.assaultReceiverM4Steel, new AssaultReceiverRenderer("m4receiver", "textureSteel"));
		MinecraftForgeClient.registerItemRenderer(AAItemAssaultComponents.assaultReceiverM4Polymer, new AssaultReceiverRenderer("m4receiver", "texturePolymer"));
		MinecraftForgeClient.registerItemRenderer(AAItemAssaultComponents.assaultReceiverM4Mythian, new AssaultReceiverRenderer("m4receiver", "textureMythian"));
		MinecraftForgeClient.registerItemRenderer(AAItemAssaultComponents.assaultReceiverM4Promethium, new AssaultReceiverRenderer("m4receiver", "texturePromethium"));
		MinecraftForgeClient.registerItemRenderer(AAItemAssaultComponents.assaultReceiverM4Sarinum, new AssaultReceiverRenderer("m4receiver", "textureSarinum"));
		MinecraftForgeClient.registerItemRenderer(AAItemAssaultComponents.assaultReceiverM4Velerium, new AssaultReceiverRenderer("m4receiver", "textureVelerium"));
		MinecraftForgeClient.registerItemRenderer(AAItemAssaultComponents.assaultReceiverM4Celibras, new AssaultReceiverRenderer("m4receiver", "textureCelibras"));
		MinecraftForgeClient.registerItemRenderer(AAItemAssaultComponents.assaultReceiverM4Aericas, new AssaultReceiverRenderer("m4receiver", "textureAericas"));
		MinecraftForgeClient.registerItemRenderer(AAItemAssaultComponents.assaultReceiverM4Darisin, new AssaultReceiverRenderer("m4receiver", "textureDarisin"));
	}
	
	public static void renderFrontEnds() {
		MinecraftForgeClient.registerItemRenderer(AAItemAssaultComponents.assaultFrontEndM4Gold, new PartRenderBase("m4frontEnd", "textureGold"));
		MinecraftForgeClient.registerItemRenderer(AAItemAssaultComponents.assaultFrontEndM4Iron, new PartRenderBase("m4frontEnd", "textureIron"));
		MinecraftForgeClient.registerItemRenderer(AAItemAssaultComponents.assaultFrontEndM4Bronze, new PartRenderBase("m4frontEnd", "textureBronze"));
		MinecraftForgeClient.registerItemRenderer(AAItemAssaultComponents.assaultFrontEndM4Titanium, new PartRenderBase("m4frontEnd", "textureTitanium"));
		MinecraftForgeClient.registerItemRenderer(AAItemAssaultComponents.assaultFrontEndM4Aluminium, new PartRenderBase("m4frontEnd", "textureAluminium"));
		MinecraftForgeClient.registerItemRenderer(AAItemAssaultComponents.assaultFrontEndM4Steel, new PartRenderBase("m4frontEnd", "textureSteel"));
		MinecraftForgeClient.registerItemRenderer(AAItemAssaultComponents.assaultFrontEndM4Polymer, new PartRenderBase("m4frontEnd", "texturePolymer"));
		MinecraftForgeClient.registerItemRenderer(AAItemAssaultComponents.assaultFrontEndM4Mythian, new PartRenderBase("m4frontEnd", "textureMythian"));
		MinecraftForgeClient.registerItemRenderer(AAItemAssaultComponents.assaultFrontEndM4Promethium, new PartRenderBase("m4frontEnd", "texturePromethium"));
		MinecraftForgeClient.registerItemRenderer(AAItemAssaultComponents.assaultFrontEndM4Sarinum, new PartRenderBase("m4frontEnd", "textureSarinum"));
		MinecraftForgeClient.registerItemRenderer(AAItemAssaultComponents.assaultFrontEndM4Velerium, new PartRenderBase("m4frontEnd", "textureVelerium"));
		MinecraftForgeClient.registerItemRenderer(AAItemAssaultComponents.assaultFrontEndM4Celibras, new PartRenderBase("m4frontEnd", "textureCelibras"));
		MinecraftForgeClient.registerItemRenderer(AAItemAssaultComponents.assaultFrontEndM4Aericas, new PartRenderBase("m4frontEnd", "textureAericas"));
		MinecraftForgeClient.registerItemRenderer(AAItemAssaultComponents.assaultFrontEndM4Darisin, new PartRenderBase("m4frontEnd", "textureDarisin"));
	}
	
	public static void renderStocks() {
		MinecraftForgeClient.registerItemRenderer(AAItemAssaultComponents.assaultStockM4Gold, new PartRenderBase("m4stock", "textureGold"));
		MinecraftForgeClient.registerItemRenderer(AAItemAssaultComponents.assaultStockM4Iron, new PartRenderBase("m4stock", "textureIron"));
		MinecraftForgeClient.registerItemRenderer(AAItemAssaultComponents.assaultStockM4Bronze, new PartRenderBase("m4stock", "textureBronze"));
		MinecraftForgeClient.registerItemRenderer(AAItemAssaultComponents.assaultStockM4Titanium, new PartRenderBase("m4stock", "textureTitanium"));
		MinecraftForgeClient.registerItemRenderer(AAItemAssaultComponents.assaultStockM4Aluminium, new PartRenderBase("m4stock", "textureAluminium"));
		MinecraftForgeClient.registerItemRenderer(AAItemAssaultComponents.assaultStockM4Steel, new PartRenderBase("m4stock", "textureSteel"));
		MinecraftForgeClient.registerItemRenderer(AAItemAssaultComponents.assaultStockM4Polymer, new PartRenderBase("m4stock", "texturePolymer"));
		MinecraftForgeClient.registerItemRenderer(AAItemAssaultComponents.assaultStockM4Mythian, new PartRenderBase("m4stock", "textureMythian"));
		MinecraftForgeClient.registerItemRenderer(AAItemAssaultComponents.assaultStockM4Promethium, new PartRenderBase("m4stock", "texturePromethium"));
		MinecraftForgeClient.registerItemRenderer(AAItemAssaultComponents.assaultStockM4Sarinum, new PartRenderBase("m4stock", "textureSarinum"));
		MinecraftForgeClient.registerItemRenderer(AAItemAssaultComponents.assaultStockM4Velerium, new PartRenderBase("m4stock", "textureVelerium"));
		MinecraftForgeClient.registerItemRenderer(AAItemAssaultComponents.assaultStockM4Celibras, new PartRenderBase("m4stock", "textureCelibras"));
		MinecraftForgeClient.registerItemRenderer(AAItemAssaultComponents.assaultStockM4Aericas, new PartRenderBase("m4stock", "textureAericas"));
		MinecraftForgeClient.registerItemRenderer(AAItemAssaultComponents.assaultStockM4Darisin, new PartRenderBase("m4stock", "textureDarisin"));
	}

}
