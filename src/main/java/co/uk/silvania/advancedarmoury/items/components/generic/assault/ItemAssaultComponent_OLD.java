package co.uk.silvania.advancedarmoury.items.components.generic.assault;

import java.util.List;

import co.uk.silvania.advancedarmoury.AdvancedArmoury;
import co.uk.silvania.advancedarmoury.config.MaterialStats;
import co.uk.silvania.advancedarmoury.items.components.generic.ItemComponent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class ItemAssaultComponent_OLD extends ItemComponent {
	
	double size;
	
	float accuracy;
	int fireRate;
	int power;
	
	public ItemAssaultComponent_OLD(String componentName, String displayName, double size, String materialName, float accuracy, String col, int rgb, int fireRate, boolean powerBool, String oredict, String identCol, String identName) {
		super(displayName, componentName, materialName, col, rgb, oredict, identCol, identName);
		this.setCreativeTab(AdvancedArmoury.tabComponentsAssault);
		
		this.size = size;
		
		this.accuracy = accuracy;
		this.fireRate = fireRate;
	}


	MaterialStats stats = new MaterialStats();
	
	public double size(ItemStack item) { return size; }
	public int weight(ItemStack item) { return (int) (size*stats.getWeight(getMaterial(item))); }
	public double durability(ItemStack item) { return Math.round((size*stats.getDurability(getMaterial(item)))*100); }
	public float accuracy(ItemStack item) { return accuracy; }
	public int fireRate(ItemStack item) { return fireRate; }
	public int power(ItemStack item) { return power; }
}
