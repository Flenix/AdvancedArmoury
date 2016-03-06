package co.uk.silvania.advancedarmoury.items.components.asset;

import co.uk.silvania.advancedarmoury.AdvancedArmoury;
import co.uk.silvania.advancedarmoury.items.EnumMaterial;
import cpw.mods.fml.common.registry.GameRegistry;

public class AssetReceiver extends Receiver {
	
	public int magId = 0;
	public boolean topRail = false;
	
	public AssetReceiver(ComponentReceiver cpt) {
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
				cpt.zSize, 
				cpt.frontEndX,
				cpt.frontEndY,
				cpt.frontEndZ,
				cpt.stockX,
				cpt.stockY,
				cpt.stockZ,
				cpt.triggerX,
				cpt.triggerY,
				cpt.triggerZ,
				cpt.magX,
				cpt.magY,
				cpt.magZ,
				cpt.attachmentX,
				cpt.attachmentY,
				cpt.attachmentZ,
				cpt.magId, 
				cpt.topRail);
		
		GameRegistry.registerItem(this, cpt.unlocalizedName, AdvancedArmoury.modid);
	}
}
