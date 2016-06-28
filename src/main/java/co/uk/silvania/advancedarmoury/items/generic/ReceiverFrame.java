package co.uk.silvania.advancedarmoury.items.generic;

import java.util.List;

import co.uk.silvania.advancedarmoury.RarityRegistry;
import co.uk.silvania.advancedarmoury.items.EnumRarity;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ReceiverFrame extends ItemComponent {
	
	public String gunType;
	
	public ReceiverFrame(String gunType, String componentName, String displayName) {
		super(componentName, displayName, "", 15, true, true, true, true, true, true);
		this.gunType = gunType;
	}
}
