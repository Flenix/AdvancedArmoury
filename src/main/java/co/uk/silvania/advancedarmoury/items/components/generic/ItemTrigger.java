package co.uk.silvania.advancedarmoury.items.components.generic;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class ItemTrigger extends ItemComponent {
	
	String material;
	String col;
	
	double size;
	int weight;
	double durability;
	
	float accuracy;
	int fireRate;
	int power;

	public ItemTrigger(String componentName, double size, String materialName, double durability, int weight, float accuracy, String textCol, int rgb, int fireRate, String oreDict) {
		super("", "Trigger", componentName, materialName, textCol, rgb, oreDict, "\u00A76", "A");
		this.material = materialName;
		this.col = textCol;
		
		this.size = size;
		this.weight = weight;
		this.durability = durability;
		
		this.accuracy = accuracy;
		this.fireRate = fireRate;
		this.power = (int) Math.round(weight / size);
	}
	
	@Override public double size(ItemStack item) { return size; }
	@Override public int weight(ItemStack item) { return (int) (size*stats.getWeight(material)); }
	@Override public double durability(ItemStack item) { return Math.round((size*stats.getDurability(material))*100); }
	@Override public float accuracy(ItemStack item) { return accuracy; }
	@Override public int fireRate(ItemStack item) { return fireRate; }
	@Override public int power(ItemStack item) { return power; }
}
