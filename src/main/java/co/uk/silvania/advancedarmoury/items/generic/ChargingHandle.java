package co.uk.silvania.advancedarmoury.items.generic;

import co.uk.silvania.advancedarmoury.AdvancedArmoury;

public class ChargingHandle extends ItemComponent {

	public ChargingHandle(String componentName, String displayName) {
		super(componentName, displayName, "Receiver Component F", 5.25, false, false, false, true, false, false);
		this.setCreativeTab(AdvancedArmoury.tabComponentsGeneric);
	}

}
