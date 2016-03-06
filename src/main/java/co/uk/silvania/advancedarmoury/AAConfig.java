package co.uk.silvania.advancedarmoury;

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
	public static boolean debugMode;
	public static boolean levelling;
	public static boolean renderHealthText;
	
	public static void initConfig(File configFile) {
		config = new Configuration(configFile);
		
		try {
			config.load();
			
			hardMode = config.getBoolean("Hard Mode", Configuration.CATEGORY_GENERAL, false, "Hide GUI elements until you use HUD devices/augments");
			debugMode = config.getBoolean("Debug Mode", Configuration.CATEGORY_GENERAL, false, "Extra console output to find errors etc");
			levelling = config.getBoolean("Level System", Configuration.CATEGORY_GENERAL, true, "Enable/Disable the levelling system.");
			renderHealthText = config.getBoolean("Render Health Text", Configuration.CATEGORY_GENERAL, true, "Display text on the health bar");
			
		} catch (Exception e) {
			System.out.println("### Warning! Advanced Armoury could not load its config file! ###");
		} finally {
			if (config.hasChanged()) {
				config.save();
			}
		}
	}

}
