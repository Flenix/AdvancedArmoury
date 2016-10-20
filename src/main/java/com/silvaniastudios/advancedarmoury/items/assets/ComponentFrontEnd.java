package com.silvaniastudios.advancedarmoury.items.assets;

import com.silvaniastudios.advancedarmoury.AAUtils;
import com.silvaniastudios.advancedarmoury.AdvancedArmoury;
import com.silvaniastudios.advancedarmoury.TypeFile;

public class ComponentFrontEnd extends ComponentType {
	
	boolean topRail;
	boolean leftRail;
	boolean rightRail;
	boolean underRail;

	public ComponentFrontEnd(TypeFile file) {
		super(file);
	}
	
	public void read(String[] split, TypeFile file) {
		super.read(split, file);
		try {
			unlocalizedName = file.name;
			if (split[0].replace(" ", "").equalsIgnoreCase("displayname")) { 	displayName = split[1].trim();	}
			if (split[0].replace(" ", "").equalsIgnoreCase("componentName")) {	componentName = split[1].trim(); }
			if (split[0].replace(" ", "").equalsIgnoreCase("model")) { 			modelName = split[1].trim(); }
			if (split[0].replace(" ", "").equalsIgnoreCase("modelTexture")) { 	modelTexture = split[1].trim(); }
			if (split[0].replace(" ", "").equalsIgnoreCase("gunType")) { 		gunType = split[1].trim(); }
			if (split[0].replace(" ", "").equalsIgnoreCase("xSize")) { 			xSize = AAUtils.parseFloat(split[1].trim()); }
			if (split[0].replace(" ", "").equalsIgnoreCase("ySize")) { 			ySize = AAUtils.parseFloat(split[1].trim()); }
			if (split[0].replace(" ", "").equalsIgnoreCase("zSize")) { 			zSize = AAUtils.parseFloat(split[1].trim()); }
			
			
			if (modelName.equalsIgnoreCase("m4frontEnd")) {
				topRail = true;
				leftRail = true;
				rightRail = true;
				underRail = true;
				
				xSize = 0.16F;
				ySize = 0.162F;
				zSize = 1.042F;
			}
		} catch (Exception ex) {
			AdvancedArmoury.println("Reading of asset file " + file.name + " from pack " + file.assetPack + " has failed.");
		}
	}
}
