package co.uk.silvania.advancedarmoury.items.components.assault;

import co.uk.silvania.advancedarmoury.AdvancedArmoury;
import co.uk.silvania.advancedarmoury.items.generic.FiringMechanism;

public class AssaultFiringMechanism extends FiringMechanism {

	public AssaultFiringMechanism(String componentName, String displayName) {
		super(componentName, displayName);
		this.setCreativeTab(AdvancedArmoury.tabComponentsAssault);
	}

}
