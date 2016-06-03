package co.uk.silvania.advancedarmoury;

import java.util.ArrayList;

import co.uk.silvania.advancedarmoury.items.EnumRarity;
import net.minecraft.item.Item;

public class RarityRegistry {
	
	public static ArrayList<String> veryCommon = new ArrayList<String>();
	public static ArrayList<String> common = new ArrayList<String>();
	public static ArrayList<String> uncommon = new ArrayList<String>();
	public static ArrayList<String> rare = new ArrayList<String>();
	public static ArrayList<String> veryRare = new ArrayList<String>();
	public static ArrayList<String> legendary = new ArrayList<String>();
	public static ArrayList<String> mythic = new ArrayList<String>();
	public static ArrayList<String> ultimate = new ArrayList<String>();
	
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
	
	public static EnumRarity getEnumRarity(String str) {
		if (str.equalsIgnoreCase("veryCommon") 	|| str.equalsIgnoreCase("very_common")) { return EnumRarity.VERY_COMMON; }
		if (str.equalsIgnoreCase("common")) 	{ return EnumRarity.COMMON; }
		if (str.equalsIgnoreCase("uncommon")) 	{ return EnumRarity.UNCOMMON; }
		if (str.equalsIgnoreCase("rare")) 		{ return EnumRarity.RARE; }
		if (str.equalsIgnoreCase("veryRare") 	|| str.equalsIgnoreCase("very_rare")) 	{ return EnumRarity.VERY_RARE; }
		if (str.equalsIgnoreCase("legendary")) 	{ return EnumRarity.LEGENDARY; }
		if (str.equalsIgnoreCase("mythic")) 	{ return EnumRarity.MYTHIC; }
		if (str.equalsIgnoreCase("ultimate")) 	{ return EnumRarity.ULTIMATE; }
		return null;
	}
	
	public static void register(String name, EnumRarity rarity) {
		System.out.println("Registering " + name + " with rarity " + rarity);
		if (rarity == EnumRarity.VERY_COMMON) { veryCommon.add(name); }
		if (rarity == EnumRarity.COMMON) 	  { common.add(name); }
		if (rarity == EnumRarity.UNCOMMON)    { uncommon.add(name); }
		if (rarity == EnumRarity.RARE) 		  { rare.add(name); }
		if (rarity == EnumRarity.VERY_RARE)   { veryRare.add(name); }
		if (rarity == EnumRarity.LEGENDARY)   { legendary.add(name); }
		if (rarity == EnumRarity.MYTHIC) 	  { mythic.add(name); }
		if (rarity == EnumRarity.ULTIMATE)    { ultimate.add(name); }
	}
	
	public static ArrayList<String> getList(EnumRarity rarity) {
		if (rarity == EnumRarity.VERY_COMMON) { return veryCommon; }
		if (rarity == EnumRarity.COMMON) 	  { return common; }
		if (rarity == EnumRarity.UNCOMMON)    { return uncommon; }
		if (rarity == EnumRarity.RARE) 		  { return rare; }
		if (rarity == EnumRarity.VERY_RARE)   { return veryRare; }
		if (rarity == EnumRarity.LEGENDARY)   { return legendary; }
		if (rarity == EnumRarity.MYTHIC) 	  { return mythic; }
		if (rarity == EnumRarity.ULTIMATE)    { return ultimate; }
		return null;
	}

}
