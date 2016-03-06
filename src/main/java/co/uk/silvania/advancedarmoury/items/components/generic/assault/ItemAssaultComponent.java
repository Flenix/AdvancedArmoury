package co.uk.silvania.advancedarmoury.items.components.generic.assault;

import co.uk.silvania.advancedarmoury.AdvancedArmoury;
import co.uk.silvania.advancedarmoury.items.EnumMaterial;
import co.uk.silvania.advancedarmoury.items.components.generic.ItemComponent;

public class ItemAssaultComponent extends ItemComponent {
	
	public ItemAssaultComponent(String partName, double size, EnumMaterial mat) {
		super(partName, size, (int) Math.round((size*mat.weight)/4), (int) Math.round((size*mat.durability)*100), (int) Math.round(mat.weight / size), mat);
		this.setCreativeTab(AdvancedArmoury.tabComponentsAssault);
	}
}
