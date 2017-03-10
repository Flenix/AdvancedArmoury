package com.silvaniastudios.advancedarmoury.config;

import java.io.File;

import net.minecraftforge.common.config.Configuration;

public class ComponentGeneratorConfig {
	
	public static File configFile;
	public static ConfigurationCustom config;
	
	public static String[] materials;
	
	public static void init(String path) {
		configFile = new File(path + "ComponentGenerator.cfg");
		config = new ConfigurationCustom(configFile);
		
		initConfig();
	}
	
	public static void initConfig() {
		try {
			config.load();
			config.addCustomCategoryComment(ConfigurationCustom.CATEGORY_GENERAL, "Adding new materials is very easy, so long as you follow this guide! \n" + 
			"Your material is made up of a single line of text, consisting of 11 parts as follows: \n" + 
					" - The Material Name. This must be different from all others in the list. \n" +
					" - The integrity, as a double, eg 1.3 (Used for Durability) \n" + 
					" - The materials weight, in kilograms per sq meter\n" + 
					" - The accuracy of the material when utilized as a barrel. Closer to 0 is more accurate. Must be 0-1.\n" +
					" - The colour of the text. See the examples, the last letter is the colour code as here: http://minecraft.gamepedia.com/Formatting_codes\n" +
					" - The item's RGB colour, as a single number. Use this to convert RGB to int: https://www.shodor.org/stella2java/rgbint.html\n" +
					" - The oreDict name for the ingot used to craft, eg ingotIron. If not found, you won't be able to craft.\n" +
					" - The rarity, for world-generation. Use 'null' to disable world-gen.\n" +
					"Valid rarities are VERY_COMMON, COMMON, UNCOMMON, RARE, VERY_RARE, LEGENDARY, MYTHIC, ULTIMATE\n" + 
					"Each section -MUST- be seperated with a comma! If you're confused, see the examples.\n");
			materials = config.getStringList("Materials", ConfigurationCustom.CATEGORY_GENERAL, new String[] {
					"Oak, 0.19, 760, 0.75, \u00A7e, 11968361, plankWood, VERY_COMMON", 
					"Spruce, 0.13, 760, 0.8, \u00A7e, 7757879, plankWood, VERY_COMMON", 
					"Birch, 0.17, 760, 0.76, \u00A7e, 13682576, plankWood, VERY_COMMON", 
					"Jungle, 0.15, 760, 0.78, \u00A7e, 11306860, plankWood, VERY_COMMON", 
					"Acacia, 0.1, 760, 0.81, \u00A7e, 11104578, plankWood, VERY_COMMON", 
					"Dark Oak, 0.22, 760, 0.69, \u00A7e, 5586444, plankWood, VERY_COMMON", 
					
					"Copper, 0.6, 8930, 0.69, \u00A7c, 12344370, ingotCopper, COMMON", 
					"Polymer, 0.95, 950, 0.69, \u00A78, 4079166, materialPolymer, COMMON", 
					"Iron, 1.5, 7300, 0.69, \u00A77, 12303291, ingotIron, COMMON", 
					
					"Gold, 0.2, 19300, 0.77, \u00A76, 16635392, ingotGold, UNCOMMON", 
					"Bronze, 2.65, 8850, 0.49, \u00A76, 11231279, ingotBronze, UNCOMMON", 
					"Steel, 3.9, 7850, 0.4, \u00A78, 10329501, ingotSteel, UNCOMMON", 

					//"Titanium, 3.1, 450, 0.43, \u00A78, 12042182, 1, ingotTitanium",
					//"Aluminium, 1.4, 256, 0.51, \u00A7b, 12042182, 2, ingotAluminium",
					/*"Mythian, 4.8, 1000, 0.38, \u00A72, 1789472, 2, ingotMythian",
					"Promethium, 6.9, 1450, 0.34, \u00A7d, 6706030, 3, ingotPromethium",
					"Sarinum, 4.4, 1100, 0.39, \u00A74, 8257536, 3, ingotSarinum",
					"Velerium, 3.7, 1050, 0.41, \u00A7a, 3255808, 3, ingotVelerium",
					"Celibras, 4.1, 995, 0.41, \u00A71, 141567, 2, ingotCelibras",
					"Aericas, 3.95, 1125, 0.4, \u00A7f, 15066597, 3, ingoAericas",
					"Darisin, 3.95, 1125, 0.4, \u00A78, 1644825, 3, ingotDarisin"*/}, "A list of material names");
		} catch (Exception e) {
			System.out.println("### WARNING! Advanced Armoury custom component creation system has failed to load!! ###");
		} finally {
			if (config.hasChanged()) {
				config.save();
			}
		}
	}

}
