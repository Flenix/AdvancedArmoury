package co.uk.silvania.advancedarmoury.config;

import co.uk.silvania.advancedarmoury.AdvancedArmoury;
import co.uk.silvania.advancedarmoury.client.renderers.assault.RenderAssaultRifle;
import co.uk.silvania.advancedarmoury.gun.prebuilt.M4Material;
import co.uk.silvania.advancedarmoury.items.AAItemPrebuiltGuns;
import co.uk.silvania.advancedarmoury.items.components.asset.internal.M4FrontEnd;
import co.uk.silvania.advancedarmoury.items.components.asset.internal.M4Receiver;
import co.uk.silvania.advancedarmoury.items.components.asset.internal.M4Stock;
import co.uk.silvania.advancedarmoury.items.components.cores.EnumRarity;
import co.uk.silvania.advancedarmoury.items.components.generic.ItemBarrel;
import co.uk.silvania.advancedarmoury.items.components.generic.ItemFireSelector;
import co.uk.silvania.advancedarmoury.items.components.generic.ItemFlashHider;
import co.uk.silvania.advancedarmoury.items.components.generic.ItemTrigger;
import co.uk.silvania.advancedarmoury.items.components.generic.assault.ItemAssaultChamber;
import co.uk.silvania.advancedarmoury.items.components.generic.assault.ItemAssaultComponent;
import co.uk.silvania.advancedarmoury.items.components.generic.assault.ItemAssaultGasChamber;
import co.uk.silvania.advancedarmoury.items.components.generic.assault.ItemAssaultGasFeed;
import co.uk.silvania.advancedarmoury.items.components.generic.assault.ItemAssaultPiston;
import co.uk.silvania.advancedarmoury.items.components.generic.assault.ItemAssaultPistonChamber;
import co.uk.silvania.advancedarmoury.items.components.generic.assault.ItemAssaultSpring;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;
import net.minecraftforge.client.MinecraftForgeClient;

public class ComponentGenerator {
	
	static ComponentGeneratorConfig config;
	
	public static void init() {
		for (int i = 0; i < config.materials.length; i++) {
			generateMaterial(config.materials[i]);
		}
		//very common/common/uncommon/rare/very rare/legendary/mythic/ultimate
		
		//Mechanic-effecting: durability weight, accuracy, fire rate, power, range
		//Name durability weight acc textcol itemcol firerate oredict power range rarity
		generateMaterial("Silvanian Armoury,	0.6, 1935, 0.31, \u00A7e, 16635392, 3, null, x, x" + EnumRarity.ULTIMATE); //Good overall
		
		generateMaterial("Silvanian, 			0.6, 1935, 0.31, \u00A7e, 16635392, 3, null, x, x" + EnumRarity.RARE); //Low weight
		generateMaterial("Silvanian, 			0.6, 1935, 0.31, \u00A7e, 16635392, 3, null, x, x" + EnumRarity.VERY_RARE); //Low weight & high accuracy
		generateMaterial("Hypax Arms, 			0.6, 1935, 0.31, \u00A7e, 16635392, 3, null, x, x" + EnumRarity.VERY_RARE); //Low weight & high fire rate
		generateMaterial("Silvanian, 			0.6, 1935, 0.31, \u00A7e, 16635392, 3, null, x, x" + EnumRarity.VERY_RARE); //Low weight & high power
		generateMaterial("Silvanian, 			0.6, 1935, 0.31, \u00A7e, 16635392, 3, null, x, x" + EnumRarity.VERY_RARE); //Low weight & high range
		generateMaterial("Silvanian, 			3.0, 1935, 0.31, \u00A7e, 16635392, 3, null, x, x" + EnumRarity.VERY_RARE); //Low weight & high durability
		generateMaterial("Silvanian, 			0.6, 1935, 0.31, \u00A7e, 16635392, 3, null, x, x" + EnumRarity.LEGENDARY); //Low weight/high acc/high fire rate
		generateMaterial("Silvanian, 			0.6, 1935, 0.31, \u00A7e, 16635392, 3, null, x, x" + EnumRarity.LEGENDARY); //Low weight/high acc/high power
		generateMaterial("Silvanian, 			0.6, 1935, 0.31, \u00A7e, 16635392, 3, null, x, x" + EnumRarity.LEGENDARY); //Low weight/high acc/high range
		generateMaterial("Silvanian, 			3.0, 1935, 0.31, \u00A7e, 16635392, 3, null, x, x" + EnumRarity.LEGENDARY); //Low weight/high acc/high durability
		generateMaterial("Silvanian, 			0.6, 1935, 0.31, \u00A7e, 16635392, 3, null, x, x" + EnumRarity.LEGENDARY); //Low weight/high fire rate/high power
		generateMaterial("Silvanian, 			0.6, 1935, 0.31, \u00A7e, 16635392, 3, null, x, x" + EnumRarity.LEGENDARY); //Low weight/high fire rate/high range
		generateMaterial("Silvanian, 			3.0, 1935, 0.31, \u00A7e, 16635392, 3, null, x, x" + EnumRarity.LEGENDARY); //Low weight/high fire rate/high durability
		generateMaterial("Silvanian, 			0.6, 1935, 0.31, \u00A7e, 16635392, 3, null, x, x" + EnumRarity.LEGENDARY); //Low weight/high power/high range
		generateMaterial("Silvanian, 			3.0, 1935, 0.31, \u00A7e, 16635392, 3, null, x, x" + EnumRarity.LEGENDARY); //Low weight/high power/high durability
		generateMaterial("Silvanian, 			3.0, 1935, 0.31, \u00A7e, 16635392, 3, null, x, x" + EnumRarity.LEGENDARY); //Low weight/high range/high durability
		generateMaterial("Silvanian, 			0.6, 1935, 0.31, \u00A7e, 16635392, 3, null, x, x" + EnumRarity.RARE); //High accuracy
		generateMaterial("Silvanian, 			0.6, 1935, 0.31, \u00A7e, 16635392, 3, null, x, x" + EnumRarity.VERY_RARE); //High accuracy & high fire rate
		generateMaterial("Silvanian, 			0.6, 1935, 0.31, \u00A7e, 16635392, 3, null, x, x" + EnumRarity.VERY_RARE); //High accuracy & high power
		generateMaterial("Kirugotu Industries,	0.6, 1935, 0.31, \u00A7e, 16635392, 3, null, x, x" + EnumRarity.VERY_RARE); //High accuracy & high range
		generateMaterial("Silvanian, 			3.0, 1935, 0.31, \u00A7e, 16635392, 3, null, x, x" + EnumRarity.VERY_RARE); //High accuracy & high durability
		generateMaterial("Silvanian, 			0.6, 1935, 0.31, \u00A7e, 16635392, 3, null, x, x" + EnumRarity.LEGENDARY); //High accuracy/high fire rate/high power
		generateMaterial("Silvanian, 			0.6, 1935, 0.31, \u00A7e, 16635392, 3, null, x, x" + EnumRarity.LEGENDARY); //High accuracy/high fire rate/high range
		generateMaterial("Silvanian, 			3.0, 1935, 0.31, \u00A7e, 16635392, 3, null, x, x" + EnumRarity.LEGENDARY); //High accuracy/high fire rate/high durability
		generateMaterial("Silvanian, 			0.6, 1935, 0.31, \u00A7e, 16635392, 3, null, x, x" + EnumRarity.LEGENDARY); //High accuracy/high power/high range
		generateMaterial("Silvanian, 			3.0, 1935, 0.31, \u00A7e, 16635392, 3, null, x, x" + EnumRarity.LEGENDARY); //High accuracy/high power/high durability
		generateMaterial("Silvanian, 			3.0, 1935, 0.31, \u00A7e, 16635392, 3, null, x, x" + EnumRarity.LEGENDARY); //High accuracy/high range/high durability
		generateMaterial("Silvanian, 			0.6, 1935, 0.31, \u00A7e, 16635392, 3, null, x, x" + EnumRarity.RARE); //High fire rate
		generateMaterial("Silvanian, 			0.6, 1935, 0.31, \u00A7e, 16635392, 3, null, x, x" + EnumRarity.VERY_RARE); //High fire rate & high power
		generateMaterial("Silvanian, 			0.6, 1935, 0.31, \u00A7e, 16635392, 3, null, x, x" + EnumRarity.VERY_RARE); //High fire rate & high range
		generateMaterial("Silvanian, 			3.0, 1935, 0.31, \u00A7e, 16635392, 3, null, x, x" + EnumRarity.VERY_RARE); //High fire rate & high durability
		generateMaterial("Silvanian, 			0.6, 1935, 0.31, \u00A7e, 16635392, 3, null, x, x" + EnumRarity.LEGENDARY); //High fire rate/high power/high range
		generateMaterial("Silvanian, 			3.0, 1935, 0.31, \u00A7e, 16635392, 3, null, x, x" + EnumRarity.LEGENDARY); //High fire rate/high power/high durability
		generateMaterial("Abramov Militia, 		0.6, 1935, 0.31, \u00A7e, 16635392, 3, null, x, x" + EnumRarity.LEGENDARY); //High power
		generateMaterial("Silvanian, 			0.6, 1935, 0.31, \u00A7e, 16635392, 3, null, x, x" + EnumRarity.VERY_RARE); //High power & high range
		generateMaterial("Silvanian, 			3.0, 1935, 0.31, \u00A7e, 16635392, 3, null, x, x" + EnumRarity.VERY_RARE); //High power & high durability
		generateMaterial("Silvanian, 			3.0, 1935, 0.31, \u00A7e, 16635392, 3, null, x, x" + EnumRarity.LEGENDARY); //High power/high range/high durability
		generateMaterial("Girchler & Schmidt, 	0.6, 1935, 0.31, \u00A7e, 16635392, 3, null, x, x" + EnumRarity.RARE); //High range
		generateMaterial("Silvanian, 			3.0, 1935, 0.31, \u00A7e, 16635392, 3, null, x, x" + EnumRarity.VERY_RARE); //High range & high durability
		generateMaterial("Silvanian, 			3.0, 1935, 0.31, \u00A7e, 16635392, 3, null, x, x" + EnumRarity.RARE); //High durability
	}
	
	public static void generateMaterial(String string) {
		String[] splitter = string.split(",");
		String materialName = splitter[0].trim();
		double durability = parseDouble(splitter[1].trim());
		int weight = parseInt(splitter[2].trim());
		float accuracy = parseFloat(splitter[3].trim());
		String textCol = splitter[4].trim();
		int rgb = parseInt(splitter[5].trim());
		int fireRate = parseInt(splitter[6].trim());
		String oreDict = splitter[7].trim();
		
		double w = 0.75;
		
		AdvancedArmoury.println("Loaded custom Component Material: " + materialName);
		
		Item itemBarrel = new ItemBarrel("barrel", 2.0 * w, materialName, durability, weight, accuracy, textCol, rgb, 0, oreDict, false).setUnlocalizedName("barrel"+materialName);
		Item itemBarrelRifled = new ItemBarrel("barrel", 1.96 * w, materialName, durability, weight, accuracy, textCol, rgb, 0, oreDict, true).setUnlocalizedName("barrelRifled"+materialName);
		Item itemTrigger = new ItemTrigger("trigger", 1.9 * w, materialName, durability, weight, 0, textCol, rgb, 0, oreDict).setUnlocalizedName("trigger"+materialName);
		Item itemFlashHider = new ItemFlashHider("flashHider", 6 * w, materialName, durability, weight, 0, textCol, rgb, 0, oreDict).setUnlocalizedName("flashHider"+materialName);
		Item itemFireSelector = new ItemFireSelector("fireSelector", 1 * w, materialName, durability, weight, 0, textCol, rgb, 0, oreDict).setUnlocalizedName("fireSelector"+materialName);
		//componentName, displayName, size, mat, acc, col, rgb, fireRate, power-affecting?, oredict, identCol, identName
		Item itemAssaultBolt 			= new ItemAssaultComponent("bolt", "Bolt", 7 * w, materialName, 0, textCol, rgb, fireRate, true, oreDict, "\u00A72", "A").setUnlocalizedName("assaultBolt"+materialName);
		Item itemAssaultBoltCarrier 	= new ItemAssaultComponent("boltCarrier", "Bolt Carrier", 9 * w, materialName, 0, textCol, rgb, fireRate, true, oreDict, "\u00A72", "B").setUnlocalizedName("assaultBoltCarrier"+materialName);
		Item itemAssaultChamber 		= new ItemAssaultChamber("", "", materialName, 5.9 * w, 0, textCol, rgb, fireRate, true, oreDict).setUnlocalizedName("assaultChamber"+materialName);
		Item itemAssaultChargingHandle 	= new ItemAssaultComponent("chargingHandle", "Charging Handle", 8.8, materialName, 0, textCol, rgb, 0, false, oreDict, "\u00A72", "C").setUnlocalizedName("assaultChargingHandle"+materialName);
		Item itemAssaultEjector 		= new ItemAssaultComponent("ejector", "Ejector", 2.7 * w, materialName, 0, textCol, rgb, 0, false, oreDict, "\u00A72", "D").setUnlocalizedName("assaultEjector"+materialName);
		Item itemAssaultExtractor 		= new ItemAssaultComponent("extractor", "Extractor", 2.56 * w, materialName, 0, textCol, rgb, 0, false, oreDict, "\u00A72", "E").setUnlocalizedName("assaultExtractor"+materialName);
		Item itemAssaultFiringPin 		= new ItemAssaultComponent("firingPin", "Firing Pin", 5.6 * w, materialName, 0, textCol, rgb, 0, false, oreDict, "\u00A72", "F").setUnlocalizedName("assaultFiringPin"+materialName);
		Item itemAssaultFiringPinRetainerPin = new ItemAssaultComponent("firingPinRetainerPin", "Firing Pin Retainer Pin", 0.4 * w, materialName, 0, textCol, rgb, 0, false, oreDict, "\u00A72", "G").setUnlocalizedName("assaultFiringPinRetainerPin"+materialName);
		Item itemAssaultGasChamber 		= new ItemAssaultGasChamber("gasChamber", 6.2 * w, materialName, 0, textCol, rgb, fireRate, oreDict).setUnlocalizedName("assaultGasChamber"+materialName);
		Item itemAssaultGasFeed 		= new ItemAssaultGasFeed("gasFeed", 11.4 * w, materialName, 0, textCol, rgb, fireRate, oreDict).setUnlocalizedName("assaultGasFeed"+materialName);
		Item itemAssaultPiston 			= new ItemAssaultPiston("piston", 5 * w, materialName, 0, textCol, rgb, fireRate, oreDict).setUnlocalizedName("assaultPiston"+materialName);
		Item itemAssaultPistonChamber 	= new ItemAssaultPistonChamber("pistonChamber", 7 * w, materialName, 0, textCol, rgb, fireRate, oreDict).setUnlocalizedName("assaultPistonChamber"+materialName);
		Item itemAssaultSpring 			= new ItemAssaultSpring("spring", 5.6 * w, materialName, 0, textCol, rgb, fireRate, oreDict).setUnlocalizedName("assaultSpring"+materialName);
		
		Item assaultReceiverM4 = new M4Receiver(capitalize(materialName) + " M4A1 Receiver", "m4receiver", "assault", materialName, durability, weight, textCol, true).setUnlocalizedName("m4Receiver"+materialName);
		Item assaultFrontEndM4 = new M4FrontEnd(capitalize(materialName) + " M4A1 Front End", "m4frontEnd", "assault", materialName, durability, weight, textCol, true, true, true, true).setUnlocalizedName("m4FrontEnd"+materialName);
		Item assaultStockM4 = new M4Stock(capitalize(materialName) + " M4A1 Stock", "m4stockGold", "assault", materialName, durability, weight, textCol).setUnlocalizedName("m4Stock"+materialName);
		
		Item assaultM4Gun = new M4Material(materialName).setUnlocalizedName("m4Carbine" + materialName);
		
		GameRegistry.registerItem(itemBarrel, "barrel"+materialName);
		GameRegistry.registerItem(itemBarrelRifled, "barrelRifled"+materialName);
		GameRegistry.registerItem(itemTrigger, "trigger"+materialName);
		GameRegistry.registerItem(itemFlashHider, "flashHider"+materialName);
		GameRegistry.registerItem(itemFireSelector, "fireSelector"+materialName);
		
		GameRegistry.registerItem(itemAssaultBolt, "assaultBolt"+materialName);
		GameRegistry.registerItem(itemAssaultBoltCarrier, "assaultBoltCarrier"+materialName);
		GameRegistry.registerItem(itemAssaultChamber, "assaultChamber"+materialName);
		GameRegistry.registerItem(itemAssaultChargingHandle, "assaultChargingHandle"+materialName);
		GameRegistry.registerItem(itemAssaultEjector, "assaultEjector"+materialName);
		GameRegistry.registerItem(itemAssaultExtractor, "assaultExtractor"+materialName);
		GameRegistry.registerItem(itemAssaultFiringPin, "assaultFiringPin"+materialName);
		GameRegistry.registerItem(itemAssaultFiringPinRetainerPin, "assaultFiringPinRetainerPin"+materialName);
		GameRegistry.registerItem(itemAssaultGasChamber, "assaultGasChamber"+materialName);
		GameRegistry.registerItem(itemAssaultGasFeed, "assaultGasFeed"+materialName);
		GameRegistry.registerItem(itemAssaultPiston, "assaultPiston"+materialName);
		GameRegistry.registerItem(itemAssaultPistonChamber, "assaultPistonChamber"+materialName);
		GameRegistry.registerItem(itemAssaultSpring, "assaultSpring"+materialName);
		
		GameRegistry.registerItem(assaultReceiverM4, "m4Receiver"+materialName);
		GameRegistry.registerItem(assaultFrontEndM4, "m4FrontEnd"+materialName);
		GameRegistry.registerItem(assaultStockM4, "m4Stock"+materialName);
		
		GameRegistry.registerItem(assaultM4Gun, "m4Carbine"+materialName);
		
		AdvancedArmoury.proxy.renderTriggers(itemTrigger, materialName);
		AdvancedArmoury.proxy.renderBarrels(itemBarrel, materialName);
		AdvancedArmoury.proxy.renderFlashHiders(itemFlashHider, materialName);
		
		AdvancedArmoury.proxy.renderReceivers(assaultReceiverM4, materialName, rgb);
		AdvancedArmoury.proxy.renderFrontEnds(assaultFrontEndM4, materialName);
		AdvancedArmoury.proxy.renderStocks(assaultStockM4, materialName);
		
		AdvancedArmoury.proxy.renderAssaultRifles(assaultM4Gun);
	}
	
	public static String capitalize(String s) {
        if (s.length() == 0) return s;
        return s.substring(0, 1).toUpperCase() + s.substring(1).toLowerCase();
    }

	
	public static int parseInt(String s) {
		try {
			return Integer.parseInt("" + s);
		} catch (NumberFormatException ex) {
			return 0;
		}
	}
	
	public static double parseDouble(String s) {
		try {
			return Double.parseDouble("" + s);
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
	
	public static boolean matExists(String str) {
		for (int i = 0; i < ComponentGeneratorConfig.materials.length; i++) {
			String[] splitter = ComponentGeneratorConfig.materials[i].split(",");
			String materialName = splitter[0].trim();
			if (materialName.equalsIgnoreCase(str)) {
				return true;
			}
		}
		return false;
	}

}
