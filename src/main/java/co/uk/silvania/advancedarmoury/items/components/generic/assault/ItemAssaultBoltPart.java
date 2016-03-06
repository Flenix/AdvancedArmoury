package co.uk.silvania.advancedarmoury.items.components.generic.assault;

import java.util.List;

import co.uk.silvania.advancedarmoury.items.EnumMaterial;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class ItemAssaultBoltPart extends ItemAssaultComponent {
	
	String identColour;
	String identId;
	
	public ItemAssaultBoltPart(String identColour, String identId, String partName, double size, EnumMaterial mat) {
		super(partName, size, mat);
		this.identColour = identColour;
		this.identId = identId;
	}
	
	@SideOnly(Side.CLIENT)
    public void addInformation(ItemStack item, EntityPlayer player, List list, boolean p_77624_4_) {
		list.add(identColour + "Part Identifier: " + identId);
		list.add("");
		list.add("Material: " + material.getString());
		list.add("Accuracy: N/A");
		list.add("Fire Rate: N/A");
		list.add("Cost (Parts): " + cost);
		list.add("Build Time: " + buildTime);
		list.add("Power Modifier: " + power);
		list.add("Weight: " + weight);
		list.add("Damage: " + this.getDamage(item) + "/" + this.getMaxDamage());
	}
}
