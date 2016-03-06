package co.uk.silvania.advancedarmoury;

import java.io.File;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import cpw.mods.fml.common.registry.GameRegistry;

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
	

}
