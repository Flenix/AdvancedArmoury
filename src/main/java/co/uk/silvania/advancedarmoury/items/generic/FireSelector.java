package co.uk.silvania.advancedarmoury.items.generic;

import co.uk.silvania.advancedarmoury.AdvancedArmoury;

public class FireSelector extends ItemComponent {
	
	public FireSelector(String componentName, String displayName) {
		super(componentName, displayName, "Receiver Component E", 5.25, false, false, false, true, false, false);
		this.setCreativeTab(AdvancedArmoury.tabComponentsGeneric);
	}

}
