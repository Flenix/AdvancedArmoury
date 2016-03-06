package co.uk.silvania.advancedarmoury.items;

import co.uk.silvania.advancedarmoury.items.components.generic.ItemBarrel;
import co.uk.silvania.advancedarmoury.items.components.generic.ItemFireSelector;
import co.uk.silvania.advancedarmoury.items.components.generic.ItemFlashHider;
import co.uk.silvania.advancedarmoury.items.components.generic.ItemTrigger;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;

public class AAItemComponents {
	
	public static Item barrelGold;
	public static Item barrelIron;
	public static Item barrelBronze;
	public static Item barrelTitanium;
	public static Item barrelAluminium;
	public static Item barrelSteel;
	public static Item barrelPolymer;
	public static Item barrelMythian;
	public static Item barrelPromethium;
	public static Item barrelSarinum;
	public static Item barrelVelerium;
	public static Item barrelCelibras;
	public static Item barrelAericas;
	public static Item barrelDarisin;
	
	public static Item rifledBarrelGold;
	public static Item rifledBarrelIron;
	public static Item rifledBarrelBronze;
	public static Item rifledBarrelTitanium;
	public static Item rifledBarrelAluminium;
	public static Item rifledBarrelSteel;
	public static Item rifledBarrelPolymer;
	public static Item rifledBarrelMythian;
	public static Item rifledBarrelPromethium;
	public static Item rifledBarrelSarinum;
	public static Item rifledBarrelVelerium;
	public static Item rifledBarrelCelibras;
	public static Item rifledBarrelAericas;
	public static Item rifledBarrelDarisin;
	
	public static Item fireSelectorGold;
	public static Item fireSelectorIron;
	public static Item fireSelectorBronze;
	public static Item fireSelectorTitanium;
	public static Item fireSelectorAluminium;
	public static Item fireSelectorSteel;
	public static Item fireSelectorPolymer;
	public static Item fireSelectorMythian;
	public static Item fireSelectorPromethium;
	public static Item fireSelectorSarinum;
	public static Item fireSelectorVelerium;
	public static Item fireSelectorCelibras;
	public static Item fireSelectorAericas;
	public static Item fireSelectorDarisin;
	
	public static Item triggerGold;
	public static Item triggerIron;
	public static Item triggerBronze;
	public static Item triggerTitanium;
	public static Item triggerAluminium;
	public static Item triggerSteel;
	public static Item triggerPolymer;
	public static Item triggerMythian;
	public static Item triggerPromethium;
	public static Item triggerSarinum;
	public static Item triggerVelerium;
	public static Item triggerCelibras;
	public static Item triggerAericas;
	public static Item triggerDarisin;
	
	public static Item flashHiderGold;
	public static Item flashHiderIron;
	public static Item flashHiderBronze;
	public static Item flashHiderTitanium;
	public static Item flashHiderAluminium;
	public static Item flashHiderSteel;
	public static Item flashHiderPolymer;
	public static Item flashHiderMythian;
	public static Item flashHiderPromethium;
	public static Item flashHiderSarinum;
	public static Item flashHiderVelerium;
	public static Item flashHiderCelibras;
	public static Item flashHiderAericas;
	public static Item flashHiderDarisin;
	
	public static void init() {
		genericComponents();
		registerItems();
	}
	
	public static void genericComponents() {
		//DURABILITY - COST - BUILD TIME - POWER - WEIGHT - ACCURACY - MATERIAL (for display) - RIFLED (true yes, false smoothbore)
		barrelGold			= new ItemBarrel(EnumMaterial.GOLD, false).setUnlocalizedName("barrelGold");
		barrelIron 			= new ItemBarrel(EnumMaterial.IRON, false).setUnlocalizedName("barrelIron");
		barrelBronze 		= new ItemBarrel(EnumMaterial.BRONZE, false).setUnlocalizedName("barrelBronze");
		barrelTitanium 		= new ItemBarrel(EnumMaterial.TITANIUM, false).setUnlocalizedName("barrelTitanium");
		barrelAluminium 	= new ItemBarrel(EnumMaterial.ALUMINIUM, false).setUnlocalizedName("barrelAluminium");
		barrelSteel 		= new ItemBarrel(EnumMaterial.STEEL, false).setUnlocalizedName("barrelSteel");
		barrelPolymer 		= new ItemBarrel(EnumMaterial.POLYMER, false).setUnlocalizedName("barrelPolymer");
		barrelMythian 		= new ItemBarrel(EnumMaterial.MYTHIAN, false).setUnlocalizedName("barrelMythian");
		barrelPromethium	= new ItemBarrel(EnumMaterial.PROMETHIUM, false).setUnlocalizedName("barrelPromethium");
		barrelSarinum 		= new ItemBarrel(EnumMaterial.SARINUM, false).setUnlocalizedName("barrelSarinum");
		barrelVelerium 		= new ItemBarrel(EnumMaterial.VELERIUM, false).setUnlocalizedName("barrelVelerium");
		barrelCelibras 		= new ItemBarrel(EnumMaterial.CELIBRAS, false).setUnlocalizedName("barrelCelibras");
		barrelAericas 		= new ItemBarrel(EnumMaterial.AERICAS, false).setUnlocalizedName("barrelAericas");
		barrelDarisin 		= new ItemBarrel(EnumMaterial.DARISIN, false).setUnlocalizedName("barrelDarisin");
		
		rifledBarrelGold 		= new ItemBarrel(EnumMaterial.GOLD, true).setUnlocalizedName("rifledBarrelGold");
		rifledBarrelIron 		= new ItemBarrel(EnumMaterial.IRON, true).setUnlocalizedName("rifledBarrelIron");
		rifledBarrelBronze 		= new ItemBarrel(EnumMaterial.BRONZE, true).setUnlocalizedName("rifledBarrelBronze");
		rifledBarrelTitanium 	= new ItemBarrel(EnumMaterial.TITANIUM, true).setUnlocalizedName("rifledBarrelTitanium");
		rifledBarrelAluminium 	= new ItemBarrel(EnumMaterial.ALUMINIUM, true).setUnlocalizedName("rifledBarrelAluminium");
		rifledBarrelSteel 		= new ItemBarrel(EnumMaterial.STEEL, true).setUnlocalizedName("rifledBarrelSteel");
		rifledBarrelPolymer 	= new ItemBarrel(EnumMaterial.POLYMER, true).setUnlocalizedName("rifledBarrelPolymer");
		rifledBarrelMythian 	= new ItemBarrel(EnumMaterial.MYTHIAN, true).setUnlocalizedName("rifledBarrelMythian");
		rifledBarrelPromethium 	= new ItemBarrel(EnumMaterial.PROMETHIUM, true).setUnlocalizedName("rifledBarrelPromethium");
		rifledBarrelSarinum		= new ItemBarrel(EnumMaterial.SARINUM, true).setUnlocalizedName("rifledBarrelSarinum");
		rifledBarrelVelerium	= new ItemBarrel(EnumMaterial.VELERIUM, true).setUnlocalizedName("rifledBarrelVelerium");
		rifledBarrelCelibras 	= new ItemBarrel(EnumMaterial.CELIBRAS, true).setUnlocalizedName("rifledBarrelCelibras");
		rifledBarrelAericas 	= new ItemBarrel(EnumMaterial.AERICAS, true).setUnlocalizedName("rifledBarrelAericas");
		rifledBarrelDarisin 	= new ItemBarrel(EnumMaterial.DARISIN, true).setUnlocalizedName("rifledBarrelDarisin");
		
		triggerGold			= new ItemTrigger(EnumMaterial.GOLD).setUnlocalizedName("triggerGold");
		triggerIron 		= new ItemTrigger(EnumMaterial.IRON).setUnlocalizedName("triggerIron");
		triggerBronze 		= new ItemTrigger(EnumMaterial.BRONZE).setUnlocalizedName("triggerBronze");
		triggerTitanium 	= new ItemTrigger(EnumMaterial.TITANIUM).setUnlocalizedName("triggerTitanium");
		triggerAluminium 	= new ItemTrigger(EnumMaterial.ALUMINIUM).setUnlocalizedName("triggerAluminium");
		triggerSteel 		= new ItemTrigger(EnumMaterial.STEEL).setUnlocalizedName("triggerSteel");
		triggerPolymer 		= new ItemTrigger(EnumMaterial.POLYMER).setUnlocalizedName("triggerPolymer");
		triggerMythian 		= new ItemTrigger(EnumMaterial.MYTHIAN).setUnlocalizedName("triggerMythian");
		triggerPromethium	= new ItemTrigger(EnumMaterial.PROMETHIUM).setUnlocalizedName("triggerPromethium");
		triggerSarinum 		= new ItemTrigger(EnumMaterial.SARINUM).setUnlocalizedName("triggerSarinum");
		triggerVelerium		= new ItemTrigger(EnumMaterial.VELERIUM).setUnlocalizedName("triggerVelerium");
		triggerCelibras 	= new ItemTrigger(EnumMaterial.CELIBRAS).setUnlocalizedName("triggerCelibras");
		triggerAericas	 	= new ItemTrigger(EnumMaterial.AERICAS).setUnlocalizedName("triggerAericas");
		triggerDarisin 		= new ItemTrigger(EnumMaterial.DARISIN).setUnlocalizedName("triggerDarisin");
		
		fireSelectorGold 		= new ItemFireSelector(EnumMaterial.GOLD).setUnlocalizedName("fireSelectorGold");
		fireSelectorIron 		= new ItemFireSelector(EnumMaterial.IRON).setUnlocalizedName("fireSelectorIron");
		fireSelectorBronze 		= new ItemFireSelector(EnumMaterial.BRONZE).setUnlocalizedName("fireSelectorBronze");
		fireSelectorTitanium 	= new ItemFireSelector(EnumMaterial.TITANIUM).setUnlocalizedName("fireSelectorTitanium");
		fireSelectorAluminium 	= new ItemFireSelector(EnumMaterial.ALUMINIUM).setUnlocalizedName("fireSelectorAluminium");
		fireSelectorSteel 		= new ItemFireSelector(EnumMaterial.STEEL).setUnlocalizedName("fireSelectorSteel");
		fireSelectorPolymer 	= new ItemFireSelector(EnumMaterial.POLYMER).setUnlocalizedName("fireSelectorPolymer");
		fireSelectorMythian 	= new ItemFireSelector(EnumMaterial.MYTHIAN).setUnlocalizedName("fireSelectorMythian");
		fireSelectorPromethium	= new ItemFireSelector(EnumMaterial.PROMETHIUM).setUnlocalizedName("fireSelectorPromethium");
		fireSelectorSarinum 	= new ItemFireSelector(EnumMaterial.SARINUM).setUnlocalizedName("fireSelectorSarinum");
		fireSelectorVelerium 	= new ItemFireSelector(EnumMaterial.VELERIUM).setUnlocalizedName("fireSelectorVelerium");
		fireSelectorCelibras 	= new ItemFireSelector(EnumMaterial.CELIBRAS).setUnlocalizedName("fireSelectorCelibras");
		fireSelectorAericas 	= new ItemFireSelector(EnumMaterial.AERICAS).setUnlocalizedName("fireSelectorAericas");
		fireSelectorDarisin 	= new ItemFireSelector(EnumMaterial.DARISIN).setUnlocalizedName("fireSelectorDarisin");
		
		flashHiderGold		= new ItemFlashHider(EnumMaterial.GOLD).setUnlocalizedName("flashHiderGold");
		flashHiderIron 		= new ItemFlashHider(EnumMaterial.IRON).setUnlocalizedName("flashHiderIron");
		flashHiderBronze 	= new ItemFlashHider(EnumMaterial.BRONZE).setUnlocalizedName("flashHiderBronze");
		flashHiderTitanium 	= new ItemFlashHider(EnumMaterial.TITANIUM).setUnlocalizedName("flashHiderTitanium");
		flashHiderAluminium = new ItemFlashHider(EnumMaterial.ALUMINIUM).setUnlocalizedName("flashHiderAluminium");
		flashHiderSteel 	= new ItemFlashHider(EnumMaterial.STEEL).setUnlocalizedName("flashHiderSteel");
		flashHiderPolymer 	= new ItemFlashHider(EnumMaterial.POLYMER).setUnlocalizedName("flashHiderPolymer");
		flashHiderMythian 	= new ItemFlashHider(EnumMaterial.MYTHIAN).setUnlocalizedName("flashHiderMythian");
		flashHiderPromethium= new ItemFlashHider(EnumMaterial.PROMETHIUM).setUnlocalizedName("flashHiderPromethium");
		flashHiderSarinum 	= new ItemFlashHider(EnumMaterial.SARINUM).setUnlocalizedName("flashHiderSarinum");
		flashHiderVelerium	= new ItemFlashHider(EnumMaterial.VELERIUM).setUnlocalizedName("flashHiderVelerium");
		flashHiderCelibras 	= new ItemFlashHider(EnumMaterial.CELIBRAS).setUnlocalizedName("flashHiderCelibras");
		flashHiderAericas	= new ItemFlashHider(EnumMaterial.AERICAS).setUnlocalizedName("flashHiderAericas");
		flashHiderDarisin 	= new ItemFlashHider(EnumMaterial.DARISIN).setUnlocalizedName("flashHiderDarisin");
	}
	
	public static void registerItems() {
		GameRegistry.registerItem(barrelGold, "barrelGold");
		GameRegistry.registerItem(barrelIron, "barrelIron");
		GameRegistry.registerItem(barrelBronze, "barrelBronze");
		GameRegistry.registerItem(barrelTitanium, "barrelTitanium");
		GameRegistry.registerItem(barrelAluminium, "barrelAluminium");
		GameRegistry.registerItem(barrelSteel, "barrelSteel");
		GameRegistry.registerItem(barrelPolymer, "barrelPolymer");
		GameRegistry.registerItem(barrelMythian, "barrelMythian");
		GameRegistry.registerItem(barrelPromethium, "barrelPromethium");
		GameRegistry.registerItem(barrelSarinum, "barrelSarinum");
		GameRegistry.registerItem(barrelVelerium, "barrelVelerium");
		GameRegistry.registerItem(barrelCelibras, "barrelCelibras");
		GameRegistry.registerItem(barrelAericas, "barrelAericas");
		GameRegistry.registerItem(barrelDarisin, "barrelDarisin");
		
		GameRegistry.registerItem(rifledBarrelGold, "rifledBarrelGold");
		GameRegistry.registerItem(rifledBarrelIron, "rifledBarrelIron");
		GameRegistry.registerItem(rifledBarrelBronze, "rifledBarrelBronze");
		GameRegistry.registerItem(rifledBarrelTitanium, "rifledBarrelTitanium");
		GameRegistry.registerItem(rifledBarrelAluminium, "rifledBarrelAluminium");
		GameRegistry.registerItem(rifledBarrelSteel, "rifledBarrelSteel");
		GameRegistry.registerItem(rifledBarrelPolymer, "rifledBarrelPolymer");
		GameRegistry.registerItem(rifledBarrelMythian, "rifledBarrelMythian");
		GameRegistry.registerItem(rifledBarrelPromethium, "rifledBarrelPromethium");
		GameRegistry.registerItem(rifledBarrelSarinum, "rifledBarrelSarinum");
		GameRegistry.registerItem(rifledBarrelVelerium, "rifledBarrelVelerium");
		GameRegistry.registerItem(rifledBarrelCelibras, "rifledBarrelCelibras");
		GameRegistry.registerItem(rifledBarrelAericas, "rifledBarrelAericas");
		GameRegistry.registerItem(rifledBarrelDarisin, "rifledBarrelDarisin");
		
		GameRegistry.registerItem(triggerGold, "triggerGold");
		GameRegistry.registerItem(triggerIron, "triggerIron");
		GameRegistry.registerItem(triggerBronze, "triggerBronze");
		GameRegistry.registerItem(triggerTitanium, "triggerTitanium");
		GameRegistry.registerItem(triggerAluminium, "triggerAluminium");
		GameRegistry.registerItem(triggerSteel, "triggerSteel");
		GameRegistry.registerItem(triggerPolymer, "triggerPolymer");
		GameRegistry.registerItem(triggerMythian, "triggerMythian");
		GameRegistry.registerItem(triggerPromethium, "triggerPromethium");
		GameRegistry.registerItem(triggerSarinum, "triggerSarinum");
		GameRegistry.registerItem(triggerVelerium, "triggerVelerium");
		GameRegistry.registerItem(triggerCelibras, "triggerCelibras");
		GameRegistry.registerItem(triggerAericas, "triggerAericas");
		GameRegistry.registerItem(triggerDarisin, "triggerDarisin");
		
		GameRegistry.registerItem(fireSelectorGold, "fireSelectorGold");
		GameRegistry.registerItem(fireSelectorIron, "fireSelectorIron");
		GameRegistry.registerItem(fireSelectorBronze, "fireSelectorBronze");
		GameRegistry.registerItem(fireSelectorTitanium, "fireSelectorTitanium");
		GameRegistry.registerItem(fireSelectorAluminium, "fireSelectorAluminium");
		GameRegistry.registerItem(fireSelectorSteel, "fireSelectorSteel");
		GameRegistry.registerItem(fireSelectorPolymer, "fireSelectorPolymer");
		GameRegistry.registerItem(fireSelectorMythian, "fireSelectorMythian");
		GameRegistry.registerItem(fireSelectorPromethium, "fireSelectorPromethium");
		GameRegistry.registerItem(fireSelectorSarinum, "fireSelectorSarinum");
		GameRegistry.registerItem(fireSelectorVelerium, "fireSelectorVelerium");
		GameRegistry.registerItem(fireSelectorCelibras, "fireSelectorCelibras");
		GameRegistry.registerItem(fireSelectorAericas, "fireSelectorAericas");
		GameRegistry.registerItem(fireSelectorDarisin, "fireSelectorDarisin");
		
		
		GameRegistry.registerItem(flashHiderGold, "flashHiderGold");
		GameRegistry.registerItem(flashHiderIron, "flashHiderIron");
		GameRegistry.registerItem(flashHiderBronze, "flashHiderBronze");
		GameRegistry.registerItem(flashHiderTitanium, "flashHiderTitanium");
		GameRegistry.registerItem(flashHiderAluminium, "flashHiderAluminium");
		GameRegistry.registerItem(flashHiderSteel, "flashHiderSteel");
		GameRegistry.registerItem(flashHiderPolymer, "flashHiderPolymer");
		GameRegistry.registerItem(flashHiderMythian, "flashHiderMythian");
		GameRegistry.registerItem(flashHiderPromethium, "flashHiderPromethium");
		GameRegistry.registerItem(flashHiderSarinum, "flashHiderSarinum");
		GameRegistry.registerItem(flashHiderVelerium, "flashHiderVelerium");
		GameRegistry.registerItem(flashHiderCelibras, "flashHiderCelibras");
		GameRegistry.registerItem(flashHiderAericas, "flashHiderAericas");
		GameRegistry.registerItem(flashHiderDarisin, "flashHiderDarisin");
	}
}
