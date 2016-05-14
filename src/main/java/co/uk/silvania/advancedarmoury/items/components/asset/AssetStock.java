package co.uk.silvania.advancedarmoury.items.components.asset;

import co.uk.silvania.advancedarmoury.AdvancedArmoury;
import co.uk.silvania.advancedarmoury.config.MaterialStats;
import cpw.mods.fml.common.registry.GameRegistry;

public class AssetStock extends Stock {
		
	public AssetStock(ComponentStock cpt) {
		super(cpt.displayName, 
				cpt.modelName, 
				cpt.modelTexture, 
				cpt.iconTexture, 
				cpt.gunType, 
				cpt.xSize,
				cpt.ySize, 
				cpt.zSize,
				MaterialStats.getWeight(cpt.material),
				MaterialStats.getDurability(cpt.material),
				cpt.material,
				MaterialStats.getTextCol(cpt.material));
		
		GameRegistry.registerItem(this, cpt.unlocalizedName, AdvancedArmoury.modid);
	}
}
