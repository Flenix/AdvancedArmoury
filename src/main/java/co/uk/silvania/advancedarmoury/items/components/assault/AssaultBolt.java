package co.uk.silvania.advancedarmoury.items.components.assault;

import co.uk.silvania.advancedarmoury.AdvancedArmoury;
import co.uk.silvania.advancedarmoury.items.generic.Bolt;

public class AssaultBolt extends Bolt {

	public AssaultBolt(String componentName, String displayName) {
		super(componentName, displayName);
		this.setCreativeTab(AdvancedArmoury.tabComponentsAssault);
	}
}
