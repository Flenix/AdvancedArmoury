package com.silvaniastudios.advancedarmoury.items.assets;

import com.silvaniastudios.advancedarmoury.AAUtils;
import com.silvaniastudios.advancedarmoury.AdvancedArmoury;
import com.silvaniastudios.advancedarmoury.TypeFile;

public class ComponentStock extends ComponentType {

	public ComponentStock(TypeFile file) {
		super(file);
	}
	
	public void read(String[] split, TypeFile file) {
		super.read(split, file);
		try {
			unlocalizedName = file.name;
			if (split[0].replace(" ", "").equalsIgnoreCase("displayname")) { 	displayName = split[1].trim(); }
			if (split[0].replace(" ", "").equalsIgnoreCase("componentName")) {	componentName = split[1].trim(); }
			if (split[0].replace(" ", "").equalsIgnoreCase("model")) { 			modelName = split[1].trim(); }
			if (split[0].replace(" ", "").equalsIgnoreCase("modelTexture")) { 	modelTexture = split[1].trim(); }
			if (split[0].replace(" ", "").equalsIgnoreCase("guntype")) { 		gunType = split[1].trim(); }
			if (split[0].replace(" ", "").equalsIgnoreCase("xSize")) { 			xSize = AAUtils.parseFloat(split[1].trim()); }
			if (split[0].replace(" ", "").equalsIgnoreCase("ySize")) { 			ySize = AAUtils.parseFloat(split[1].trim()); }
			if (split[0].replace(" ", "").equalsIgnoreCase("zSize")) { 			zSize = AAUtils.parseFloat(split[1].trim()); }
			
		} catch (Exception ex) {
			AdvancedArmoury.println("Reading of asset file " + file.name + " from pack " + file.assetPack + " has failed.");
		}
	}
}
