package com.silvaniastudios.advancedarmoury.config;

import java.io.File;

import net.minecraftforge.common.config.Configuration;

public class AAConfig {
	
	public static File aaConfig;
	
	public static void init(String configPath) {
		aaConfig = new File(configPath + "settings.cfg");
		
		initConfig(aaConfig);
	}
	
	public static Configuration config;
	
	public static boolean hardMode;
	public static boolean componentCrafting;
	public static boolean ammoCrafting;
	public static boolean debugMode;
	public static boolean levelling;
	public static boolean renderHealthText;
	public static double damageModifier;
	
	public static void initConfig(File configFile) {
		config = new Configuration(configFile);
		
		try {
			config.load();
			
			hardMode = config.getBoolean("Hard Mode", Configuration.CATEGORY_GENERAL, false, "Hide GUI elements until you use HUD devices/augments");
			componentCrafting = config.getBoolean("Component Crafting", Configuration.CATEGORY_GENERAL, true, "Whether components can be crafted. If false, they can only be scavenged.");
			ammoCrafting = config.getBoolean("Ammo Crafting", Configuration.CATEGORY_GENERAL, true, "Whether rounds can be crafted. If false, they can only be scavenged. Note; Magazines are components!");
			debugMode = config.getBoolean("Debug Mode", Configuration.CATEGORY_GENERAL, false, "Extra console output to find errors etc");
			levelling = config.getBoolean("Level System", Configuration.CATEGORY_GENERAL, true, "Enable/Disable the levelling system.");
			renderHealthText = config.getBoolean("Render Health Text", Configuration.CATEGORY_GENERAL, true, "Display text on the health bar");
			
			damageModifier = config.get(Configuration.CATEGORY_GENERAL, "Damage Modifier", 2.9, "Adjust to offset the damage per round throughout the mod. "
					+ "\nLower means more damage per shot, higher is weaker shots. "
					+ "\n2.9 is roughly 'Real' damage; a high end sniper can one-shot a newbie in the head. 2.6 would be CoD Hardcore style."
					+ "\nSet drastically high (Eg around 30+) for 'RPG'-style gameplay where \npeople will tank a lot of shots before death.").getDouble();
			
		} catch (Exception e) {
			System.out.println("### Warning! Advanced Armoury could not load its config file! ###");
		} finally {
			if (config.hasChanged()) {
				config.save();
			}
		}
	}

}
