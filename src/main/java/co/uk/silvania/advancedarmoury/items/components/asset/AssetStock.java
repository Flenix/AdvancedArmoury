package co.uk.silvania.advancedarmoury.items.components.asset;

import co.uk.silvania.advancedarmoury.AdvancedArmoury;
import co.uk.silvania.advancedarmoury.items.EnumMaterial;
import cpw.mods.fml.common.registry.GameRegistry;

public class AssetStock extends Stock {
		
	public AssetStock(ComponentStock cpt) {
		super(cpt.displayName, 
				cpt.modelName, 
				cpt.modelTexture, 
				cpt.iconTexture, 
				cpt.gunType, 
				EnumMaterial.valueOf(cpt.material.toUpperCase()), 
				cpt.buildTime, 
				cpt.partCost, 
				cpt.xSize,
				cpt.ySize, 
				cpt.zSize);
		
		GameRegistry.registerItem(this, cpt.unlocalizedName, AdvancedArmoury.modid);
	}
}
