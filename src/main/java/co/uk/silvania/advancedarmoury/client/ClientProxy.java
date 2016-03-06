package co.uk.silvania.advancedarmoury.client;

import java.io.File;
import java.io.IOException;
import java.util.List;

import co.uk.silvania.advancedarmoury.AAItems;
import co.uk.silvania.advancedarmoury.AdvancedArmoury;
import co.uk.silvania.advancedarmoury.CommonProxy;
import co.uk.silvania.advancedarmoury.client.renderers.PartRenderBarrel;
import co.uk.silvania.advancedarmoury.client.renderers.PartRenderBase;
import co.uk.silvania.advancedarmoury.client.renderers.PartRenderComponent;
import co.uk.silvania.advancedarmoury.client.renderers.assault.AssaultReceiverRenderer;
import co.uk.silvania.advancedarmoury.client.renderers.assault.RenderAssaultRifle;
import co.uk.silvania.advancedarmoury.items.AAItemAssaultComponents;
import co.uk.silvania.advancedarmoury.items.AAItemComponents;
import co.uk.silvania.advancedarmoury.items.components.ComponentType;
import co.uk.silvania.advancedarmoury.items.components.asset.AssetFrontEnd;
import co.uk.silvania.advancedarmoury.items.components.asset.AssetReceiver;
import co.uk.silvania.advancedarmoury.items.components.asset.AssetStock;
import cpw.mods.fml.common.ObfuscationReflectionHelper;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.FileResourcePack;
import net.minecraft.client.resources.IResourcePack;
import net.minecraft.item.Item;
import net.minecraftforge.client.MinecraftForgeClient;

public class ClientProxy extends CommonProxy {

	@Override
	public void registerRenderers() {		
		for (ComponentType componentType : ComponentType.items.values()) {
			Item item = GameRegistry.findItem(AdvancedArmoury.modid, componentType.unlocalizedName);
			if (item instanceof AssetFrontEnd) {
				MinecraftForgeClient.registerItemRenderer(item, new PartRenderBase(componentType.modelName, componentType.modelTexture));
			}
			if (item instanceof AssetReceiver) {
				MinecraftForgeClient.registerItemRenderer(item, new AssaultReceiverRenderer(componentType.modelName, componentType.modelTexture));
				
			}
			if (item instanceof AssetStock) {
				MinecraftForgeClient.registerItemRenderer(item, new PartRenderBase(componentType.modelName, componentType.modelTexture));
			}
		}
		
		RenderRegisters.initRenders();
		
		MinecraftForgeClient.registerItemRenderer(AAItems.m4mag, new PartRenderBase("m4mag", "m4mag"));
		MinecraftForgeClient.registerItemRenderer(AAItems.assaultFrame, new RenderAssaultRifle());
		MinecraftForgeClient.registerItemRenderer(AAItems.itemRound, new RoundRenderer());
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
}
