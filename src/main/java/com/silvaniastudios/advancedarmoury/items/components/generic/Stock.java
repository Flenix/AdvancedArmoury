package com.silvaniastudios.advancedarmoury.items.components.generic;

public class Stock extends ItemComponent {

	public Stock(String componentName, String displayName, String modelName, String modelTexture, String gunType, float xSize, float ySize, float zSize) {
		super(componentName, displayName, "Receiver Component: S", 18, "This is the component at the rear of a weapon. \nIt transfers weapon recoil into the users body, keeping stability.");
	}

}
