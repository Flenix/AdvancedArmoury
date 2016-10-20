package com.silvaniastudios.advancedarmoury.items.components.assault;

import com.silvaniastudios.advancedarmoury.AdvancedArmoury;
import com.silvaniastudios.advancedarmoury.items.generic.Stock;

public class AssaultStock extends Stock {

	public AssaultStock(String componentName, String displayName, String modelName, String modelTexture, String gunType, float xSize, float ySize, float zSize) {
		super(componentName, displayName, modelName, modelTexture, gunType, xSize, ySize, zSize);
		this.setCreativeTab(AdvancedArmoury.tabComponentsAssault);
	}
}
