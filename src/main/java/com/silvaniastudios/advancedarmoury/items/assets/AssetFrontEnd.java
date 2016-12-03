package com.silvaniastudios.advancedarmoury.items.assets;

import com.silvaniastudios.advancedarmoury.AdvancedArmoury;
import com.silvaniastudios.advancedarmoury.config.MaterialStats;
import com.silvaniastudios.advancedarmoury.items.components.generic.FrontEnd;

import cpw.mods.fml.common.registry.GameRegistry;

public class AssetFrontEnd extends FrontEnd {
	
	public AssetFrontEnd(ComponentFrontEnd cpt) {
		super(cpt.componentName, cpt.displayName, 
				cpt.modelName, 
				cpt.modelTexture, 
				cpt.gunType, 
				cpt.xSize,
				cpt.ySize, 
				cpt.zSize,
				cpt.topRail,
				cpt.leftRail,
				cpt.rightRail,
				cpt.underRail);

		GameRegistry.registerItem(this, cpt.unlocalizedName, AdvancedArmoury.modid);
	}
}
