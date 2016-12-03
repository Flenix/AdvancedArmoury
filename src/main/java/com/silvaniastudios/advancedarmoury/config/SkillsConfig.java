package com.silvaniastudios.advancedarmoury.config;

import java.io.File;

import net.minecraftforge.common.config.Configuration;

public class SkillsConfig {
	
	public static File aaConfig;
	
	public static void init(String configPath) {
		aaConfig = new File(configPath + "skills.cfg");
		
		initConfig(aaConfig);
	}
	
	public static Configuration config;
	
	public static final String CATEGORY_ASSAULT_PROFICIENCY = "Assault Proficiency Skill";
	
	public static boolean enableAssaultProficiency;
	public static int assaultUnlockLevel;
	
	public static void initConfig(File configFile) {
		config = new Configuration(configFile);
		
		try {
			config.load();
			
			enableAssaultProficiency = config.getBoolean("Enable Assault Proficiency", CATEGORY_ASSAULT_PROFICIENCY, true, "Enable using the Assault Rifle Proficiency skill.");
			assaultUnlockLevel = config.getInt("Assault Proficiency Unlock Level", CATEGORY_ASSAULT_PROFICIENCY, 1, 1, 99, "What Global Level Assault Rifle Proficiency should be unlocked at.");

		} catch (Exception e) {
			System.out.println("### Warning! Advanced Armoury could not load the Skills config file! ###");
		} finally {
			if (config.hasChanged()) {
				config.save();
			}
		}
	}

}
