package com.silvaniastudios.advancedarmoury.config;

public class MaterialStats {
	
	static ComponentGeneratorConfig config;
	
	public static double getIntegrity(String str) {
		for (int i = 0; i < config.materials.length; i++) {
			String[] splitter = config.materials[i].split(",");
			String materialName = splitter[0].trim();
			double durability = ComponentGenerator.parseDouble(splitter[1].trim());
			if (materialName.equalsIgnoreCase(str)) {
				return durability;
			}
		}
		return 0;
	}
	
	public static int getWeight(String str) {
		for (int i = 0; i < config.materials.length; i++) {
			String[] splitter = config.materials[i].split(",");
			String materialName = splitter[0].trim();
			int weight = ComponentGenerator.parseInt(splitter[2].trim());
			if (materialName.equalsIgnoreCase(str)) {
				return weight;
			}
		}
		return 0;
	}
	
	public static float getAccuracry(String str) {
		for (int i = 0; i < config.materials.length; i++) {
			String[] splitter = config.materials[i].split(",");
			String materialName = splitter[0].trim();
			float accuracy = ComponentGenerator.parseFloat(splitter[3].trim());
			if (materialName.equalsIgnoreCase(str)) {
				return accuracy;
			}
		}
		return 0;
	}
	
	public static String getTextCol(String str) {
		for (int i = 0; i < config.materials.length; i++) {
			String[] splitter = config.materials[i].split(",");
			String materialName = splitter[0].trim();
			String textCol = splitter[4].trim();
			if (materialName.equalsIgnoreCase(str)) {
				return textCol;
			}
		}
		return "";
	}
	
	public static int getRGB(String str) {
		for (int i = 0; i < config.materials.length; i++) {
			String[] splitter = config.materials[i].split(",");
			String materialName = splitter[0].trim();
			int rgb = ComponentGenerator.parseInt(splitter[5].trim());
			if (materialName.equalsIgnoreCase(str)) {
				return rgb;
			}
		}
		return 0;
	}
	
	public static String getOreDict(String str) {
		for (int i = 0; i < config.materials.length; i++) {
			String[] splitter = config.materials[i].split(",");
			String materialName = splitter[0].trim();
			String oreDict = splitter[7].trim();
			if (materialName.equalsIgnoreCase(str)) {
				return oreDict;
			}
		}
		return "";
	}

}
