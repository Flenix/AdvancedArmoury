package com.silvaniastudios.advancedarmoury.items.assets;

import com.silvaniastudios.advancedarmoury.AdvancedArmoury;
import com.silvaniastudios.advancedarmoury.config.MaterialStats;
import com.silvaniastudios.advancedarmoury.items.components.generic.ReceiverCasing;

import cpw.mods.fml.common.registry.GameRegistry;

public class AssetReceiver extends ReceiverCasing {
	
	public int magId = 0;
	public boolean topRail = false;
	
	public AssetReceiver(ComponentReceiver cpt) {
		super("large", cpt.gunType, cpt.componentName,
				cpt.displayName, 
				cpt.xSize,
				cpt.ySize, 
				cpt.zSize, 
				cpt.frontEndX,
				cpt.frontEndY,
				cpt.frontEndZ,
				cpt.stockX,
				cpt.stockY,
				cpt.stockZ,
				cpt.magX,
				cpt.magY,
				cpt.magZ,
				cpt.attachmentX,
				cpt.attachmentY,
				cpt.attachmentZ,
				cpt.magId, 
				cpt.topRail,
				cpt.modelName, 
				cpt.modelTexture);
		
		GameRegistry.registerItem(this, cpt.unlocalizedName, AdvancedArmoury.modid);
	}
}
