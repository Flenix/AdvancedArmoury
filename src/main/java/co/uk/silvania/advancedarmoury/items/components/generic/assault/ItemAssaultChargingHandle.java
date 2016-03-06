package co.uk.silvania.advancedarmoury.items.components.generic.assault;

import java.util.List;

import co.uk.silvania.advancedarmoury.items.EnumMaterial;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class ItemAssaultChargingHandle extends ItemAssaultBoltPart {

	public ItemAssaultChargingHandle(EnumMaterial mat) {
		super("\u00A72", "C", "chargingHandle", 2.3, mat);
	}
}
