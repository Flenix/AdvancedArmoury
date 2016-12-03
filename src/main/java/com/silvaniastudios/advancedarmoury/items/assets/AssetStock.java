package com.silvaniastudios.advancedarmoury.items.assets;

import com.silvaniastudios.advancedarmoury.AdvancedArmoury;
import com.silvaniastudios.advancedarmoury.config.MaterialStats;
import com.silvaniastudios.advancedarmoury.items.components.generic.Stock;

import cpw.mods.fml.common.registry.GameRegistry;

public class AssetStock extends Stock {
		
	public AssetStock(ComponentStock cpt) {
		super(cpt.componentName, cpt.displayName, 
				cpt.modelName, 
				cpt.modelTexture, 
				cpt.gunType, 
				cpt.xSize,
				cpt.ySize, 
				cpt.zSize);
		
		GameRegistry.registerItem(this, cpt.unlocalizedName, AdvancedArmoury.modid);
	}
}
