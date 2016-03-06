package co.uk.silvania.advancedarmoury;

import co.uk.silvania.advancedarmoury.items.components.cores.EnumRarity;
import co.uk.silvania.advancedarmoury.items.components.generic.GunFrame;
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
	
	public static String getColorFromRarity(EnumRarity rarity) {
		if (rarity == EnumRarity.VERY_COMMON) { return "\u00A7f"; }
		if (rarity == EnumRarity.COMMON) { 		return "\u00A77"; }
		if (rarity == EnumRarity.UNCOMMON) {	return "\u00A72"; }
		if (rarity == EnumRarity.RARE) { 		return "\u00A7e"; }
		if (rarity == EnumRarity.VERY_RARE) { 	return "\u00A7c"; }
		if (rarity == EnumRarity.LEGENDARY) {	return "\u00A79\u00A7l"; }
		if (rarity == EnumRarity.MYTHIC) { 		return "\u00A7d\u00A7l"; }
		if (rarity == EnumRarity.ULTIMATE) { 	return "\u00A7b\u00A7l"; }
		return "";
	}
	
	public static String getRarity(EnumRarity rarity) {
		if (rarity == EnumRarity.VERY_COMMON) { return "\u00A7f\u00A7oVery Common"; }
		if (rarity == EnumRarity.COMMON) { 		return "\u00A77\u00A7oCommon"; }
		if (rarity == EnumRarity.UNCOMMON) {	return "\u00A72\u00A7oUncommon"; }
		if (rarity == EnumRarity.RARE) { 		return "\u00A7e\u00A7oRare"; }
		if (rarity == EnumRarity.VERY_RARE) { 	return "\u00A7c\u00A7oVery Rare"; }
		if (rarity == EnumRarity.LEGENDARY) {	return "\u00A79\u00A7l\u00A7oLegendary"; }
		if (rarity == EnumRarity.MYTHIC) { 		return "\u00A7d\u00A7l\u00A7oMythic"; }
		if (rarity == EnumRarity.ULTIMATE) { 	return "\u00A7b\u00A7l\u00A7oUltimate"; }
		return "";
	}
}
