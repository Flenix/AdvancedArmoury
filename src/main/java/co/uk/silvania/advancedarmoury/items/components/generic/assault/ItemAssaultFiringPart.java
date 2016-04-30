package co.uk.silvania.advancedarmoury.items.components.generic.assault;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class ItemAssaultFiringPart extends ItemAssaultComponent {
	
	String matName;
	String col;
	String partId;

	public ItemAssaultFiringPart(String partId, String displayName, String partName, double size, String name, double durability, int weight, float accuracy, String textCol, int rgb, int fireRate, String oreDict) {
		super(partName, displayName, size, name, durability, weight, accuracy, textCol, rgb, fireRate, oreDict);
		this.fireRate = fireRate;
		this.matName = name;
		this.col = textCol;
		this.partId = partId;
	}
	
	@SideOnly(Side.CLIENT)
    public void addInformation(ItemStack item, EntityPlayer player, List list, boolean p_77624_4_) {
		list.add("\u00A71" + "Part Identifier: " + partId);
		list.add("");
		list.add(col + "Material: " + matName);
		list.add("");
		list.add("\u00A78" + "Accuracy: N/A");
		list.add("Fire Rate: " + fireRate);
		list.add("Power Modifier: " + power);
		list.add("Weight: " + weight);
		list.add("");
		list.add("Cost (Parts): " + cost);
		list.add("Build Time: " + buildTime);
		list.add("");
		list.add("Damage: " + this.getDamage(item) + "/" + this.getMaxDamage());
	}
}
