package co.uk.silvania.advancedarmoury.items.components.generic.assault;

import co.uk.silvania.advancedarmoury.AdvancedArmoury;
import co.uk.silvania.advancedarmoury.items.components.generic.ItemComponent;

public class ItemAssaultComponent extends ItemComponent {
		
	public ItemAssaultComponent(String partName, String displayName, double size, String materialName, double dura, int weight, float acc, String col, int rgb, int rate, String oredict) {
		super("assault", displayName, partName, materialName, size, (int) Math.round((size*weight)/4), (int) Math.round((size*dura)*100), (int) Math.round(weight / size), dura, weight, acc, col, rgb, rate, oredict);
		this.setCreativeTab(AdvancedArmoury.tabComponentsAssault);
	}
}
