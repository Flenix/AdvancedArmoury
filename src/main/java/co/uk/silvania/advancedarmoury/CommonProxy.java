package co.uk.silvania.advancedarmoury;

import java.io.File;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import co.uk.silvania.advancedarmoury.client.renderers.PartRenderBarrel;
import co.uk.silvania.advancedarmoury.client.renderers.PartRenderBase;
import co.uk.silvania.advancedarmoury.client.renderers.PartRenderComponent;
import co.uk.silvania.advancedarmoury.client.renderers.assault.AssaultReceiverRenderer;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;
import net.minecraftforge.client.MinecraftForgeClient;

public class CommonProxy {
	
	public void registerRenderers() {}
	public void reflectResourcePackMethods() {}
		
	protected static Pattern zipJar = Pattern.compile("(.+).(zip|jar)$");
	
	public List<File> getAssetPackList() {
		List<File> assetPacks = new ArrayList<File>();
		for (File file : AdvancedArmoury.assetDir.listFiles()) {
			if (zipJar.matcher(file.getName()).matches()) {
				AdvancedArmoury.println("Asset Pack " + file.getName() + " found and loaded.");
				assetPacks.add(file);
			}
		}
		
		return assetPacks;
	}
	
	public void renderTriggers(Item itemTrigger, String materialName) {}
	public void renderBarrels(Item item, String material) {}
	public void renderFlashHiders(Item item, String material) {}
	public void renderReceivers(Item item, String material, int rgb) {}
	public void renderFrontEnds(Item item, String material) {}
	public void renderStocks(Item item, String material) {}
}
