package co.uk.silvania.advancedarmoury.items.components.generic.assault;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class ItemAssaultFiringPart extends ItemAssaultComponent_OLD {
	
	String matName;
	String col;
	String partId;

	public ItemAssaultFiringPart(String partId, String displayName, String partName, double size, String name, float accuracy, String textCol, int rgb, int fireRate, boolean powerBool, String oreDict) {
		super(partName, displayName, size, name, accuracy, textCol, rgb, fireRate, powerBool, oreDict, "\u00A71", partId);
		this.fireRate = fireRate;
		this.matName = name;
		this.col = textCol;
		this.partId = partId;
	}
}
