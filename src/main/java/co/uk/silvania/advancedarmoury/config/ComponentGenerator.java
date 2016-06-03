package co.uk.silvania.advancedarmoury.config;

import co.uk.silvania.advancedarmoury.AdvancedArmoury;
import co.uk.silvania.advancedarmoury.gun.prebuilt.M4Material;
import co.uk.silvania.advancedarmoury.items.MaterialRegistry;
import co.uk.silvania.advancedarmoury.items.components.assault.AssaultBolt;
import co.uk.silvania.advancedarmoury.items.components.assault.AssaultReceiverCasing;
import co.uk.silvania.advancedarmoury.items.components.assault.AssaultFiringPin;
import co.uk.silvania.advancedarmoury.items.generic.Barrel;
import co.uk.silvania.advancedarmoury.items.generic.Chamber;
import co.uk.silvania.advancedarmoury.items.generic.ChargingHandle;
import co.uk.silvania.advancedarmoury.items.generic.FireSelector;
import co.uk.silvania.advancedarmoury.items.generic.Trigger;
import co.uk.silvania.advancedarmoury.items_old.components.asset.internal.M4FrontEnd;
import co.uk.silvania.advancedarmoury.items_old.components.asset.internal.M4Receiver;
import co.uk.silvania.advancedarmoury.items_old.components.asset.internal.M4Stock;
import co.uk.silvania.advancedarmoury.items_old.components.generic.ItemBarrel;
import co.uk.silvania.advancedarmoury.items_old.components.generic.ItemFireSelector;
import co.uk.silvania.advancedarmoury.items_old.components.generic.ItemFlashHider;
import co.uk.silvania.advancedarmoury.items_old.components.generic.ItemTrigger;
import co.uk.silvania.advancedarmoury.items_old.components.generic.assault.ItemAssaultChamber;
import co.uk.silvania.advancedarmoury.items_old.components.generic.assault.ItemAssaultGasChamber;
import co.uk.silvania.advancedarmoury.items_old.components.generic.assault.ItemAssaultGasFeed;
import co.uk.silvania.advancedarmoury.items_old.components.generic.assault.ItemAssaultPiston;
import co.uk.silvania.advancedarmoury.items_old.components.generic.assault.ItemAssaultPistonChamber;
import co.uk.silvania.advancedarmoury.items_old.components.generic.assault.ItemAssaultSpring;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;

public class ComponentGenerator {
	
	static ComponentGeneratorConfig config;
	
	public static void init() {
		double w = 0.75;
		for (int i = 0; i < config.materials.length; i++) {
			MaterialRegistry.register(config.materials[i]);
		}
		
		Item itemAssaultBolt = new AssaultBolt("assaultBolt", "Assault Rifle Bolt"/*"\u00A72Part ID: A", 7*w, false, true, true, false*/).setUnlocalizedName("assaultBolt");
		Item itemAssaultFiringPin = new AssaultFiringPin("assaultFiringPin", "Assault Rifle Firing Pin").setUnlocalizedName("assaultFiringPin");
		Item itemAssaultReceiverCasing = new AssaultReceiverCasing("assaultReceiverCasing", "Assault Receiver Casing").setUnlocalizedName("assaultReceiverCasing");
		
		Item itemChargingHandle = new ChargingHandle("chargingHandle", "Charging Handle").setUnlocalizedName("chargingHandle");
		Item itemFireSelector = new FireSelector("fireSelector", "Fire Selector").setUnlocalizedName("fireSelector");
		Item itemTrigger = new Trigger("trigger", "Trigger").setUnlocalizedName("trigger");
		
		Item itemChamber = new Chamber("chamber", "Chamber").setUnlocalizedName("chamber");
		//mechanic
		Item itemBarrel = new Barrel("barrel", "Barrel").setUnlocalizedName("barrel");
		
		GameRegistry.registerItem(itemAssaultBolt, "assaultBolt");
		GameRegistry.registerItem(itemAssaultFiringPin, "assaultFiringPin");
		GameRegistry.registerItem(itemAssaultReceiverCasing, "assaultReceiverCasing");
		
		GameRegistry.registerItem(itemChargingHandle, "chargingHandle");
		GameRegistry.registerItem(itemFireSelector, "fireSelector");
		GameRegistry.registerItem(itemTrigger, "trigger");
		
		GameRegistry.registerItem(itemChamber, "chamber");
		GameRegistry.registerItem(itemBarrel, "barrel");
		
		
		/*for (int i = 0; i < config.materials.length; i++) {
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
		generateMaterial("Silvanian, 			3.0, 1935, 0.31, \u00A7e, 16635392, 3, null, x, x" + EnumRarity.RARE); //High durability*/
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
		Item itemAssaultChamber 		= new ItemAssaultChamber("", "", materialName, 5.9 * w, 0, textCol, rgb, fireRate, true, oreDict).setUnlocalizedName("assaultChamber"+materialName);
		
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
		
		GameRegistry.registerItem(itemAssaultChamber, "assaultChamber"+materialName);
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
