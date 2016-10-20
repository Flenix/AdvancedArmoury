package com.silvaniastudios.advancedarmoury.items;

import java.util.ArrayList;

import com.silvaniastudios.advancedarmoury.AdvancedArmoury;
import com.silvaniastudios.advancedarmoury.RarityRegistry;

public class MaterialRegistry {

	public static ArrayList<String> materials = new ArrayList<String>();
	
	public static void register(String str) {
		String[] splitter = str.split(",");
		String materialName = splitter[0].trim();
		if (splitter.length < 11) {
			AdvancedArmoury.println("WARNING! Failed to register material " + materialName + ". Check you have included ALL values and try again.");
			return;
		}
		AdvancedArmoury.println("Registering Material: " + materialName);
		RarityRegistry.register(materialName, RarityRegistry.getEnumRarity(splitter[10].trim()));
		materials.add(str);
	}
}
