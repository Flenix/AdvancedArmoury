package co.uk.silvania.advancedarmoury.items.components.generic;

import java.util.List;

import co.uk.silvania.advancedarmoury.items.EnumMaterial;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class ItemFireSelector extends ItemComponent {

	static double size = 0.6;
	
	public ItemFireSelector(EnumMaterial mat) {
		super("fireSelector", 0.6, (int) Math.round((size*mat.weight)/4), (int) Math.round((size*mat.durability)*100), (int) Math.round(mat.weight / size), mat);
	}
	
	@SideOnly(Side.CLIENT)
    public void addInformation(ItemStack item, EntityPlayer player, List list, boolean p_77624_4_) {
		list.add("\u00A76Part Identifier: B");
		list.add("");
		list.add("Material: " + material.getString());
		list.add("Accuracy: N/A");
		list.add("Fire Rate: N/A");
		list.add("Cost (Parts): " + cost);
		list.add("Build Time: " + buildTime);
		list.add("Power Modifier: N/A");
		list.add("Weight: " + weight);
		list.add("Damage: " + this.getDamage(item) + "/" + this.getMaxDamage());
	}
}
