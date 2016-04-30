package co.uk.silvania.advancedarmoury.config;

import co.uk.silvania.advancedarmoury.AdvancedArmoury;
import co.uk.silvania.advancedarmoury.items.components.asset.internal.M4FrontEnd;
import co.uk.silvania.advancedarmoury.items.components.asset.internal.M4Receiver;
import co.uk.silvania.advancedarmoury.items.components.asset.internal.M4Stock;
import co.uk.silvania.advancedarmoury.items.components.generic.ItemBarrel;
import co.uk.silvania.advancedarmoury.items.components.generic.ItemFireSelector;
import co.uk.silvania.advancedarmoury.items.components.generic.ItemFlashHider;
import co.uk.silvania.advancedarmoury.items.components.generic.ItemTrigger;
import co.uk.silvania.advancedarmoury.items.components.generic.assault.ItemAssaultBoltPart;
import co.uk.silvania.advancedarmoury.items.components.generic.assault.ItemAssaultChamber;
import co.uk.silvania.advancedarmoury.items.components.generic.assault.ItemAssaultGasChamber;
import co.uk.silvania.advancedarmoury.items.components.generic.assault.ItemAssaultGasFeed;
import co.uk.silvania.advancedarmoury.items.components.generic.assault.ItemAssaultPiston;
import co.uk.silvania.advancedarmoury.items.components.generic.assault.ItemAssaultPistonChamber;
import co.uk.silvania.advancedarmoury.items.components.generic.assault.ItemAssaultSpring;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;

public class ComponentGenerator {
	
	static ComponentGeneratorConfig config;
	
	public static void init() {
		for (int i = 0; i < config.materials.length; i++) {
			String[] splitter = config.materials[i].split(",");
			String materialName = splitter[0].trim();
			double durability = parseDouble(splitter[1].trim());
			int weight = parseInt(splitter[2].trim());
			float accuracy = parseFloat(splitter[3].trim());
			String textCol = splitter[4].trim();
			int rgb = parseInt(splitter[5].trim());
			int fireRate = parseInt(splitter[6].trim());
			String oreDict = splitter[7].trim();
			
			AdvancedArmoury.println("Loaded custom Component Material: " + materialName);
			
			Item itemBarrel = new ItemBarrel("barrel", 0.3, materialName, durability, weight, accuracy, textCol, rgb, fireRate, oreDict, false).setUnlocalizedName("barrel"+materialName);
			Item itemBarrelRifled = new ItemBarrel("barrel", 0.3, materialName, durability, weight, accuracy, textCol, rgb, fireRate, oreDict, true).setUnlocalizedName("barrelRifled"+materialName);
			Item itemTrigger = new ItemTrigger("trigger", 0.95, materialName, durability, weight, accuracy, textCol, rgb, fireRate, oreDict).setUnlocalizedName("trigger"+materialName);
			Item itemFlashHider = new ItemFlashHider("flashHider", 1, materialName, durability, weight, accuracy, textCol, rgb, fireRate, oreDict).setUnlocalizedName("flashHider"+materialName);
			Item itemFireSelector = new ItemFireSelector("fireSelector", 0.6, materialName, durability, weight, accuracy, textCol, rgb, fireRate, oreDict).setUnlocalizedName("fireSelector"+materialName);
			
			Item itemAssaultBolt 			= new ItemAssaultBoltPart("\u00A72", "A", "bolt", "Bolt", 1.5, materialName, durability, weight, accuracy, textCol, rgb, fireRate, oreDict).setUnlocalizedName("assaultBolt"+materialName);
			Item itemAssaultBoltCarrier 	= new ItemAssaultBoltPart("\u00A72", "B", "boltCarrier", "Bolt Carrier", 2.5, materialName, durability, weight, accuracy, textCol, rgb, fireRate, oreDict).setUnlocalizedName("assaultBoltCarrier"+materialName);
			Item itemAssaultChamber 		= new ItemAssaultChamber(materialName, durability, weight, accuracy, textCol, rgb, fireRate, oreDict).setUnlocalizedName("assaultChamber"+materialName);
			Item itemAssaultChargingHandle 	= new ItemAssaultBoltPart("\u00A72", "C", "chargingHandle", "Charging Handle", 2.3, materialName, durability, weight, accuracy, textCol, rgb, fireRate, oreDict).setUnlocalizedName("assaultChargingHandle"+materialName);
			Item itemAssaultEjector 		= new ItemAssaultBoltPart("\u00A72", "D", "ejector", "Ejector", 0.8, materialName, durability, weight, accuracy, textCol, rgb, fireRate, oreDict).setUnlocalizedName("assaultEjector"+materialName);
			Item itemAssaultExtractor 		= new ItemAssaultBoltPart("\u00A72", "E", "extractor", "Extractor", 0.8, materialName, durability, weight, accuracy, textCol, rgb, fireRate, oreDict).setUnlocalizedName("assaultExtractor"+materialName);
			Item itemAssaultFiringPin 		= new ItemAssaultBoltPart("\u00A72", "G", "firingPin", "Firing Pin", 1.3, materialName, durability, weight, accuracy, textCol, rgb, fireRate, oreDict).setUnlocalizedName("assaultFiringPin"+materialName);
			Item itemAssaultFiringPinRetainerPin = new ItemAssaultBoltPart("\u00A72", "F", "firingPinRetainerPin", "Firing Pin Retainer Pin", 0.5, materialName, durability, weight, accuracy, textCol, rgb, fireRate, oreDict).setUnlocalizedName("assaultFiringPinRetainerPin"+materialName);
			Item itemAssaultGasChamber 		= new ItemAssaultGasChamber("gasChamber", 2.8, materialName, durability, weight, accuracy, textCol, rgb, fireRate, oreDict).setUnlocalizedName("assaultGasChamber"+materialName);
			Item itemAssaultGasFeed 		= new ItemAssaultGasFeed("gasFeed", 1.5, materialName, durability, weight, accuracy, textCol, rgb, fireRate, oreDict).setUnlocalizedName("assaultGasFeed"+materialName);
			Item itemAssaultPiston 			= new ItemAssaultPiston("piston", 1.5, materialName, durability, weight, accuracy, textCol, rgb, fireRate, oreDict).setUnlocalizedName("assaultPiston"+materialName);
			Item itemAssaultPistonChamber 	= new ItemAssaultPistonChamber("pistonChamber", 2.8, materialName, durability, weight, accuracy, textCol, rgb, fireRate, oreDict).setUnlocalizedName("assaultPistonChamber"+materialName);
			Item itemAssaultSpring 			= new ItemAssaultSpring("spring", 1.7, materialName, durability, weight, accuracy, textCol, rgb, fireRate, oreDict).setUnlocalizedName("assaultSpring"+materialName);
			
			Item assaultReceiverM4 = new M4Receiver(capitalize(materialName) + " M4A1 Receiver", "m4receiver", "assault", materialName, durability, weight, textCol, true).setUnlocalizedName("m4Receiver"+materialName);
			Item assaultFrontEndM4 = new M4FrontEnd(capitalize(materialName) + " M4A1 Front End", "m4frontEnd", "assault", materialName, durability, weight, textCol, true, true, true, true).setUnlocalizedName("m4FrontEnd"+materialName);
			Item assaultStockM4 = new M4Stock(capitalize(materialName) + " M4A1 Stock", "m4stockGold", "assault", materialName, durability, weight, textCol).setUnlocalizedName("m4Stock"+materialName);
			
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
			
			AdvancedArmoury.proxy.renderTriggers(itemTrigger, materialName);
			AdvancedArmoury.proxy.renderBarrels(itemBarrel, materialName);
			AdvancedArmoury.proxy.renderFlashHiders(itemFlashHider, materialName);
			
			AdvancedArmoury.proxy.renderReceivers(assaultReceiverM4, materialName, rgb);
			AdvancedArmoury.proxy.renderFrontEnds(assaultFrontEndM4, materialName);
			AdvancedArmoury.proxy.renderStocks(assaultStockM4, materialName);
		}
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
