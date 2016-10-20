package com.silvaniastudios.advancedarmoury.config;

import java.io.File;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;

public class ConfigurationCustom extends Configuration {
	
	public ConfigurationCustom(File file) {
        super(file);
    }
	
	@Override
    public String[] getStringList(String name, String category, String[] defaultValue, String comment, String[] validValues, String langKey) {
        Property prop = this.get(category, name, defaultValue);
        prop.setLanguageKey(langKey);
        prop.setValidValues(validValues);
        prop.comment = comment;
        return prop.getStringList();
    }

}
