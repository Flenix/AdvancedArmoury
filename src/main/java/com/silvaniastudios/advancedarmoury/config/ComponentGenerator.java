package com.silvaniastudios.advancedarmoury.config;

import com.silvaniastudios.advancedarmoury.AdvancedArmoury;
import com.silvaniastudios.advancedarmoury.items.MaterialRegistry;
import com.silvaniastudios.advancedarmoury.items.attachment.magazine.Magazine;
import com.silvaniastudios.advancedarmoury.items.components.assault.AssaultFrontEnd;
import com.silvaniastudios.advancedarmoury.items.components.assault.AssaultReceiverCasing;
import com.silvaniastudios.advancedarmoury.items.components.assault.AssaultStock;
import com.silvaniastudios.advancedarmoury.items.components.generic.Barrel;
import com.silvaniastudios.advancedarmoury.items.components.generic.BoltLarge;
import com.silvaniastudios.advancedarmoury.items.components.generic.BoltSmall;
import com.silvaniastudios.advancedarmoury.items.components.generic.ChamberLarge;
import com.silvaniastudios.advancedarmoury.items.components.generic.ChamberSmall;
import com.silvaniastudios.advancedarmoury.items.components.generic.ChargingHandle;
import com.silvaniastudios.advancedarmoury.items.components.generic.FireSelector;
import com.silvaniastudios.advancedarmoury.items.components.generic.FiringMechanismLarge;
import com.silvaniastudios.advancedarmoury.items.components.generic.FiringMechanismSmall;
import com.silvaniastudios.advancedarmoury.items.components.generic.FiringPinLarge;
import com.silvaniastudios.advancedarmoury.items.components.generic.FiringPinSmall;
import com.silvaniastudios.advancedarmoury.items.components.generic.Hammer;
import com.silvaniastudios.advancedarmoury.items.components.generic.MagazineTube;
import com.silvaniastudios.advancedarmoury.items.components.generic.RevolvingChamber;
import com.silvaniastudios.advancedarmoury.items.components.generic.Trigger;
import com.silvaniastudios.advancedarmoury.items.components.pistol.PistolSlide;
import com.silvaniastudios.advancedarmoury.items.components.rifle.BoltArm;
import com.silvaniastudios.advancedarmoury.items.components.shotgun.ForeEnd;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;

public class ComponentGenerator {
	
	static ComponentGeneratorConfig config;
	
	public static void init() {
		double w = 0.75;
		for (int i = 0; i < config.materials.length; i++) {
			MaterialRegistry.register(config.materials[i]);
		}

		Item itemAssaultReceiverCasing = new AssaultReceiverCasing("assaultReceiverCasing", "Assault Receiver Casing", 0.155F, 0.677F, 0.501F, -0.35F, 0.03F, 0.0F, 0.175F, 0.035F, 0.0F, -0.24F, -0.01F, 0.0F, 0.0F, 0.0F, 0.0F, 0, true, "m4receiver", "m4receiver").setUnlocalizedName("assaultReceiverCasing");

		Item boltLarge = new BoltLarge("boltLarge", "Large Bolt").setUnlocalizedName("boltLarge");
		Item boltSmall = new BoltSmall("boltSmall", "Small Bolt").setUnlocalizedName("boltSmall");
		Item firingPinLarge = new FiringPinLarge("firingPinLarge", "Large Firing Pin").setUnlocalizedName("firingPinLarge");
		Item firingPinSmall = new FiringPinSmall("firingPinSmall", "Small Firing Pin").setUnlocalizedName("firingPinSmall");
		Item firingMechanismLarge = new FiringMechanismLarge("firingMechanismLarge", "Large Firing Mechanism").setUnlocalizedName("firingMechanismLarge");
		Item firingMechanismSmall = new FiringMechanismSmall("firingMechanismSmall", "Small Firing Mechanism").setUnlocalizedName("firingMechanismSmall");
		
		Item itemChargingHandle = new ChargingHandle("chargingHandle", "Charging Handle").setUnlocalizedName("chargingHandle");
		Item itemFireSelector = new FireSelector("fireSelector", "Fire Selector").setUnlocalizedName("fireSelector");
		Item itemTrigger = new Trigger("trigger", "Trigger").setUnlocalizedName("trigger");
		
		Item itemChamberLarge = new ChamberLarge("chamberLarge", "Large Chamber").setUnlocalizedName("chamberLarge");
		Item itemChamberSmall = new ChamberSmall("chamberSmall", "Small Chamber").setUnlocalizedName("chamberSmall");
		Item itemBarrel = new Barrel("barrel", "Barrel").setUnlocalizedName("barrel");
		
		Item itemHammer = new Hammer("hammer", "Hammer").setUnlocalizedName("hammer");
		Item pistolSlide = new PistolSlide("pistolSlide", "Semi-Auto Pistol Slide").setUnlocalizedName("pistolSlide");
		Item revolvingChamber = new RevolvingChamber("revolvingChamber", "Revolving Chamber").setUnlocalizedName("revolvingChamber");
		Item magazineTube = new MagazineTube("magazineTube", "Shotgun Magazine Tube").setUnlocalizedName("magazineTube");
		Item foreEnd = new ForeEnd("foreEnd", "Shotgun Fore End").setUnlocalizedName("foreEnd");
		Item boltArm = new BoltArm("boltArm", "Bolt Arm").setUnlocalizedName("boltArm");
		
		Item itemAssaultFrontEnd = new AssaultFrontEnd("frontEnd", "Front End", "m4FrontEnd", "m4FrontEnd", "assault", 0.16F, 0.162F, 1.042F, true, true, true, true).setUnlocalizedName("assaultFrontEnd");
		Item itemAssaultStock = new AssaultStock("stock", "Stock", "m4Stock", "m4Stock", "assault", 0.0F, 0.0F, 0.0F).setUnlocalizedName("assaultStock");
		
		Item magazine30rd = new Magazine(0, 30).setUnlocalizedName("magazine30rd");
		
		GameRegistry.registerItem(itemAssaultReceiverCasing, "assaultReceiverCasing");
		
		GameRegistry.registerItem(boltLarge, "boltLarge");
		GameRegistry.registerItem(boltSmall, "boltSmall");
		GameRegistry.registerItem(firingPinLarge, "firingPinLarge");
		GameRegistry.registerItem(firingPinSmall, "firingPinSmall");
		GameRegistry.registerItem(firingMechanismLarge, "firingMechanismLarge");
		GameRegistry.registerItem(firingMechanismSmall, "firingMechanismSmall");
		
		GameRegistry.registerItem(itemChargingHandle, "chargingHandle");
		GameRegistry.registerItem(itemFireSelector, "fireSelector");
		GameRegistry.registerItem(itemTrigger, "trigger");
		
		GameRegistry.registerItem(itemChamberLarge, "chamberLarge");
		GameRegistry.registerItem(itemChamberSmall, "chamberSmall");
		GameRegistry.registerItem(itemBarrel, "barrel");
		
		GameRegistry.registerItem(itemHammer, "hammer");
		GameRegistry.registerItem(pistolSlide, "pistolSlide");
		GameRegistry.registerItem(revolvingChamber, "revolvingChamber");
		GameRegistry.registerItem(magazineTube, "magazineTube");
		GameRegistry.registerItem(foreEnd, "foreEnd");
		GameRegistry.registerItem(boltArm, "boltArm");
		
		GameRegistry.registerItem(itemAssaultFrontEnd, "assaultFrontEnd");
		GameRegistry.registerItem(itemAssaultStock, "assaultStock");
		
		GameRegistry.registerItem(magazine30rd, "magazine30rd");
		
		AdvancedArmoury.proxy.renderReceivers(itemAssaultReceiverCasing, "");
		AdvancedArmoury.proxy.renderFrontEnds(itemAssaultFrontEnd, "");
		AdvancedArmoury.proxy.renderStocks(itemAssaultStock, "");
		AdvancedArmoury.proxy.renderBarrels(itemBarrel, "");
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
		generateMaterial("Arbamov Militia, 		0.6, 1935, 0.31, \u00A7e, 16635392, 3, null, x, x" + EnumRarity.LEGENDARY); //High power
		generateMaterial("Silvanian, 			0.6, 1935, 0.31, \u00A7e, 16635392, 3, null, x, x" + EnumRarity.VERY_RARE); //High power & high range
		generateMaterial("Silvanian, 			3.0, 1935, 0.31, \u00A7e, 16635392, 3, null, x, x" + EnumRarity.VERY_RARE); //High power & high durability
		generateMaterial("Silvanian, 			3.0, 1935, 0.31, \u00A7e, 16635392, 3, null, x, x" + EnumRarity.LEGENDARY); //High power/high range/high durability
		generateMaterial("Girchler & Schmitt, 	0.6, 1935, 0.31, \u00A7e, 16635392, 3, null, x, x" + EnumRarity.RARE); //High range
		generateMaterial("Silvanian, 			3.0, 1935, 0.31, \u00A7e, 16635392, 3, null, x, x" + EnumRarity.VERY_RARE); //High range & high durability
		generateMaterial("Silvanian, 			3.0, 1935, 0.31, \u00A7e, 16635392, 3, null, x, x" + EnumRarity.RARE); //High durability*/
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
