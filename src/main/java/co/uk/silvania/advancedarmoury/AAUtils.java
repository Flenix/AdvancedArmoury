package co.uk.silvania.advancedarmoury;

import co.uk.silvania.advancedarmoury.items.EnumRarity;
import co.uk.silvania.advancedarmoury.items.generic.GunFrame;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class AAUtils {
	
	
	//A class full of little methods I can use anywhere in the mod.
	//Some methods are taken from FlenixCitiesCore's EconUtils class. I wrote them, so it's fine.
	
	public static double parseDouble(String s) {
		try { 
			return Double.parseDouble("" + s);
		} catch (NumberFormatException ex) {
			return 0;
		}
	}
	
	public static int parseInt(String s) {
		try {
			return Integer.parseInt("" + s);
		} catch (NumberFormatException ex) {
			return 0;
		}
	}
	
	public static float parseFloat(String s) {
		try { 
			return Float.parseFloat("" + s);
		} catch (NumberFormatException ex) {
			return 0;
		}
	}
	
	public static boolean parseBoolean(String s) {
		return (s.equalsIgnoreCase("true"));
	}
	
	public static boolean isHoldingWeapon(EntityPlayer player) {
		ItemStack item = player.inventory.getCurrentItem();
		if (item != null) {
			if (item.getItem() instanceof GunFrame) {
				System.out.println("Player has an assault rifle!");
			}
		}
		return false;
	}
	
	public static String formatText(String str) {
		return str.replace("&", "\u00A7");
	}
}
