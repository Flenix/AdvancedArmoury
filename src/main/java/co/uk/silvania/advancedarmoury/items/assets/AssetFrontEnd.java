package co.uk.silvania.advancedarmoury.items.assets;

import co.uk.silvania.advancedarmoury.AdvancedArmoury;
import co.uk.silvania.advancedarmoury.config.MaterialStats;
import co.uk.silvania.advancedarmoury.items.generic.FrontEnd;
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
