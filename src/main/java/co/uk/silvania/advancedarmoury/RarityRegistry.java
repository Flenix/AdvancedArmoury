package co.uk.silvania.advancedarmoury;

import java.util.ArrayList;

import co.uk.silvania.advancedarmoury.items.components.cores.EnumRarity;
import net.minecraft.item.Item;

public class RarityRegistry {
	
	public static ArrayList<Item> veryCommon = new ArrayList<Item>();
	public static ArrayList<Item> common = new ArrayList<Item>();
	public static ArrayList<Item> uncommon = new ArrayList<Item>();
	public static ArrayList<Item> rare = new ArrayList<Item>();
	public static ArrayList<Item> veryRare = new ArrayList<Item>();
	public static ArrayList<Item> legendary = new ArrayList<Item>();
	public static ArrayList<Item> mythic = new ArrayList<Item>();
	public static ArrayList<Item> ultimate = new ArrayList<Item>();
	
	public static String veryCommon() 	{ return "\u00A7f"; }
	public static String common() 		{ return "\u00A7a"; }
	public static String uncommon() 	{ return "\u00A72"; }
	public static String rare() 		{ return "\u00A7e"; }
	public static String veryRare() 	{ return "\u00A7c"; }
	public static String legendary()	{ return "\u00A79\u00A7l"; }
	public static String mythic() 		{ return "\u00A7d\u00A7l"; }
	public static String ultimate() 	{ return "\u00A7b\u00A7l"; }
	
	public static String veryCommonTag() { return "\u00A7fVery Common"; }
	public static String commonTag() 	 { return "\u00A7aCommon"; }
	public static String uncommonTag() 	 { return "\u00A72Uncommon"; }
	public static String rareTag() 		 { return "\u00A7eRare"; }
	public static String veryRareTag() 	 { return "\u00A7cVery Rare"; }
	public static String legendaryTag()  { return "\u00A79\u00A7lLegendary"; }
	public static String mythicTag()	 { return "\u00A7d\u00A7lMythic"; }
	public static String ultimateTag() 	 { return "\u00A7b\u00A7lUltimate"; }
	
	public static String getColorFromRarity(EnumRarity rarity) {
		if (rarity == EnumRarity.VERY_COMMON) { return veryCommon(); }
		if (rarity == EnumRarity.COMMON) 	  { return common(); }
		if (rarity == EnumRarity.UNCOMMON)    { return uncommon(); }
		if (rarity == EnumRarity.RARE) 		  { return rare(); }
		if (rarity == EnumRarity.VERY_RARE)   { return veryRare(); }
		if (rarity == EnumRarity.LEGENDARY)   { return legendary(); }
		if (rarity == EnumRarity.MYTHIC) 	  { return mythic(); }
		if (rarity == EnumRarity.ULTIMATE)    { return ultimate(); }
		return null;
	}
	
	public static String getRarityTag(EnumRarity rarity) {
		if (rarity == EnumRarity.VERY_COMMON) { return veryCommonTag(); }
		if (rarity == EnumRarity.COMMON) 	  { return commonTag(); }
		if (rarity == EnumRarity.UNCOMMON)    { return uncommonTag(); }
		if (rarity == EnumRarity.RARE) 		  { return rareTag(); }
		if (rarity == EnumRarity.VERY_RARE)   { return veryRareTag(); }
		if (rarity == EnumRarity.LEGENDARY)   { return legendaryTag(); }
		if (rarity == EnumRarity.MYTHIC) 	  { return mythicTag(); }
		if (rarity == EnumRarity.ULTIMATE)    { return ultimateTag(); }
		return null;
	}

}
