package co.uk.silvania.advancedarmoury.items.components.generic;

import java.util.List;

import co.uk.silvania.advancedarmoury.IAttachment;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class ItemFlashHider extends ItemComponent implements IAttachment {

	String material;
	String col;
	
	public ItemFlashHider(String name, double size, String materialName, double durability, int weight, float acc, String textCol, int rgb, int fireRate, String oreDict) {
		super("", "Flash Hider", name, materialName, size, (int) Math.round((size*weight)/4), (int) Math.round((size*durability)*100), (int) Math.round(weight / size), durability, weight, acc, textCol, rgb, fireRate, oreDict);
		this.material = materialName;
		this.col = textCol;
	}
	
	@SideOnly(Side.CLIENT)
    public void addInformation(ItemStack item, EntityPlayer player, List list, boolean p_77624_4_) {
		list.add("\u00A7e" + "Part Identifier: " + "FH");
		list.add("");
		list.add(col + "Material: " + material);
		list.add("\u00A78" + "Accuracy: N/A");
		list.add("\u00A78" + "Fire Rate: N/A");
		list.add("\u00A78" + "Power Modifier: N/A");
		list.add("Weight: " + weight);
		list.add("");
		list.add("Cost (Parts): " + cost);
		list.add("Build Time: " + buildTime);
		list.add("");
		list.add("Damage: " + this.getDamage(item) + "/" + this.getMaxDamage());
	}

}
