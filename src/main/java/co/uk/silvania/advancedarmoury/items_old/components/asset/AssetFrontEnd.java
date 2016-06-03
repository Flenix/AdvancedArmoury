package co.uk.silvania.advancedarmoury.items_old.components.asset;

import co.uk.silvania.advancedarmoury.AdvancedArmoury;
import co.uk.silvania.advancedarmoury.config.MaterialStats;
import cpw.mods.fml.common.registry.GameRegistry;

public class AssetFrontEnd extends FrontEnd {
	
	public AssetFrontEnd(ComponentFrontEnd cpt) {
		super(cpt.displayName, 
				cpt.modelName, 
				cpt.modelTexture, 
				cpt.iconTexture, 
				cpt.gunType, 
				cpt.xSize,
				cpt.ySize, 
				cpt.zSize,
				cpt.topRail,
				cpt.leftRail,
				cpt.rightRail,
				cpt.underRail,
				MaterialStats.getWeight(cpt.material),
				MaterialStats.getDurability(cpt.material),
				cpt.material,
				MaterialStats.getTextCol(cpt.material));

		GameRegistry.registerItem(this, cpt.unlocalizedName, AdvancedArmoury.modid);
	}
}
