package co.uk.silvania.advancedarmoury.items.components.generic.assault;

import java.util.List;

import co.uk.silvania.advancedarmoury.items.EnumMaterial;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class ItemAssaultGasFeed extends ItemAssaultComponent {

	public ItemAssaultGasFeed(EnumMaterial mat) {
		super("gasFeed", 1.5, mat);
		this.fireRate = mat.fireRate;
	}
	
	@SideOnly(Side.CLIENT)
    public void addInformation(ItemStack item, EntityPlayer player, List list, boolean p_77624_4_) {
		list.add("\u00A71Part Identifier: B");
		list.add("");
		list.add("Material: " + material.getString());
		list.add("Accuracy: N/A");
		list.add("Fire Rate: " + fireRate);
		list.add("Cost (Parts): " + cost);
		list.add("Build Time: " + buildTime);
		list.add("Power Modifier: " + power);
		list.add("Weight: " + weight);
		list.add("Damage: " + this.getDamage(item) + "/" + this.getMaxDamage());
	}
}
