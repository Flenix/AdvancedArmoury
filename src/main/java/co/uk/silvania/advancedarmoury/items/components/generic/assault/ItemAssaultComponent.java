package co.uk.silvania.advancedarmoury.items.components.generic.assault;

import co.uk.silvania.advancedarmoury.AdvancedArmoury;
import co.uk.silvania.advancedarmoury.config.MaterialStats;
import co.uk.silvania.advancedarmoury.items.components.generic.ItemComponent;
import net.minecraft.item.ItemStack;

public class ItemAssaultComponent extends ItemComponent {
	
	double size;
	
	float accuracy;
	int fireRate;
	int power;
	
	public ItemAssaultComponent(String componentName, String displayName, double size, String materialName, float accuracy, String col, int rgb, int fireRate, boolean powerBool, String oredict, String identCol, String identName) {
		super("assault", displayName, componentName, materialName, col, rgb, oredict, identCol, identName);
		this.setCreativeTab(AdvancedArmoury.tabComponentsAssault);
		
		this.size = size;
		
		this.accuracy = accuracy;
		this.fireRate = fireRate;
		if (powerBool) {
			this.power = (int) Math.round((stats.getWeight(material) / size));
		} else {
			this.power = -1;
		}
		
	}

	MaterialStats stats = new MaterialStats();
	
	@Override public double size(ItemStack item) { return size; }
	@Override public int weight(ItemStack item) { return (int) (size*stats.getWeight(material)); }
	@Override public double durability(ItemStack item) { return Math.round((size*stats.getDurability(material))*100); }
	@Override public float accuracy(ItemStack item) { return accuracy; }
	@Override public int fireRate(ItemStack item) { return fireRate; }
	@Override public int power(ItemStack item) { return power; }
}
