package co.uk.silvania.advancedarmoury.items.assets;

import co.uk.silvania.advancedarmoury.AdvancedArmoury;
import co.uk.silvania.advancedarmoury.config.MaterialStats;
import co.uk.silvania.advancedarmoury.items.generic.Stock;
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
