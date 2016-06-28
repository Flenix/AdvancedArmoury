package co.uk.silvania.advancedarmoury;

import co.uk.silvania.advancedarmoury.items.ItemParts;
import co.uk.silvania.advancedarmoury.items.attachment.magazine.Magazine;
import co.uk.silvania.advancedarmoury.items.components.assault.AssaultReceiverFrame;
import co.uk.silvania.advancedarmoury.items.generic.GunFrame;
import co.uk.silvania.advancedarmoury.items_old.AAItemModifierCores;
import co.uk.silvania.advancedarmoury.items_old.AAItemPrebuiltGuns;
import co.uk.silvania.advancedarmoury.items_old.rounds.ItemBullet;
import co.uk.silvania.advancedarmoury.items_old.rounds.ItemCase;
import co.uk.silvania.advancedarmoury.items_old.rounds.ItemRound;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;

public class AAItems {

	public static Item assaultReceiverFrame;
	public static Item gunFrame;
	
	public static Item itemPartGear;
	public static Item itemPartScrew;
	public static Item itemPartWasher;
	public static Item itemPartBolt;
	public static Item itemPartNut;
	
	public static Item bulletLead;
	public static Item bulletFMJ;
	public static Item bulletCopper;
	public static Item bulletSteel;
	public static Item bulletIncendiary;
	public static Item bulletExploding;
	public static Item bulletPoison;
	public static Item bulletTracer;
	public static Item bulletUranium;
	public static Item bulletArmourPiercing;
	public static Item bulletPolymer;
	
	public static Item caseBrass556;	
	public static Item caseSteel556;
	public static Item caseAluminium556;
	public static Item casePolymer556;
	
	public static Item caseBrass762;	
	public static Item caseSteel762;
	public static Item caseAluminium762;
	public static Item casePolymer762;
	
	public static Item caseBrass900;	
	public static Item caseSteel900;
	public static Item caseAluminium900;
	public static Item casePolymer900;
	
	public static Item caseBrass127;	
	public static Item caseSteel127;
	public static Item caseAluminium127;
	public static Item casePolymer127;
	
	public static Item m4mag;
	public static Item itemRound;

	public static void init() {
		items();
		parts();
		magazines();
		rounds();
		
		//General item component registration etc is found in the ComponentGenerator class.
		AAItemPrebuiltGuns.init();
		AAItemModifierCores.init();
		
		registerItems();
	}

	public static void items() {
		assaultReceiverFrame = new AssaultReceiverFrame().setUnlocalizedName("assaultReceiverFrame");
		gunFrame = new GunFrame().setUnlocalizedName("gunFrame");
	}
	
	public static void magazines() {
		m4mag = new Magazine(0, 30).setUnlocalizedName("m4mag");
	}
	
	public static void rounds() {
		itemRound = new ItemRound().setUnlocalizedName("itemRound");
		
		bulletLead = new ItemBullet().setUnlocalizedName("bulletLead");
		bulletFMJ = new ItemBullet().setUnlocalizedName("bulletFMJ");
		bulletCopper = new ItemBullet().setUnlocalizedName("bulletCopper");
		bulletSteel = new ItemBullet().setUnlocalizedName("bulletSteel");
		bulletIncendiary = new ItemBullet().setUnlocalizedName("bulletIncendiary");
		bulletExploding = new ItemBullet().setUnlocalizedName("bulletExploding");
		bulletPoison = new ItemBullet().setUnlocalizedName("bulletPoison");
		bulletTracer = new ItemBullet().setUnlocalizedName("bulletTracer");
		bulletUranium = new ItemBullet().setUnlocalizedName("bulletUranium");
		bulletArmourPiercing = new ItemBullet().setUnlocalizedName("bulletArmourPiercing");
		bulletPolymer = new ItemBullet().setUnlocalizedName("bulletPolymer");
		
		caseBrass556 = new ItemCase(5.56).setUnlocalizedName("caseBrass556");
		caseSteel556 = new ItemCase(5.56).setUnlocalizedName("caseSteel556");
		caseAluminium556 = new ItemCase(5.56).setUnlocalizedName("caseAluminium556");
		casePolymer556 = new ItemCase(5.56).setUnlocalizedName("casePolymer556");
		
		caseBrass762 = new ItemCase(7.62).setUnlocalizedName("caseBrass762");
		caseSteel762 = new ItemCase(7.62).setUnlocalizedName("caseSteel762");
		caseAluminium762 = new ItemCase(7.62).setUnlocalizedName("caseAluminium762");
		casePolymer762 = new ItemCase(7.62).setUnlocalizedName("casePolymer762");
		
		caseBrass900 = new ItemCase(9).setUnlocalizedName("caseBrass900");
		caseSteel900 = new ItemCase(9).setUnlocalizedName("caseSteel900");
		caseAluminium900 = new ItemCase(9).setUnlocalizedName("caseAluminium900");
		casePolymer900 = new ItemCase(9).setUnlocalizedName("casePolymer900");
		
		caseBrass127 = new ItemCase(12.7).setUnlocalizedName("caseBrass127");
		caseSteel127 = new ItemCase(12.7).setUnlocalizedName("caseSteel127");
		caseAluminium127 = new ItemCase(12.7).setUnlocalizedName("caseAluminium127");
		casePolymer127 = new ItemCase(12.7).setUnlocalizedName("casePolymer127");
	}

	public static void parts() {
		itemPartGear = new ItemParts(40).setUnlocalizedName("itemPartGear");
		itemPartScrew = new ItemParts(20).setUnlocalizedName("itemPartScrew");
		itemPartWasher = new ItemParts(10).setUnlocalizedName("itemPartWasher");
		itemPartBolt = new ItemParts(30).setUnlocalizedName("itemPartBolt");
		itemPartNut = new ItemParts(20).setUnlocalizedName("itemPartNut");
	}
	
	public static void registerItems() {
    	GameRegistry.registerItem(assaultReceiverFrame, "assaultReceiverFrame");
    	GameRegistry.registerItem(gunFrame, "gunFrame");
    	
    	GameRegistry.registerItem(m4mag, "m4mag");
    	
		GameRegistry.registerItem(itemRound, "itemRound");
    	
		GameRegistry.registerItem(caseBrass556, "caseBrass556");
    	GameRegistry.registerItem(caseSteel556, "caseSteel556");
    	GameRegistry.registerItem(caseAluminium556, "caseAluminium556");
    	GameRegistry.registerItem(casePolymer556, "casePolymer556");
    	
    	GameRegistry.registerItem(caseBrass762, "caseBrass762");
    	GameRegistry.registerItem(caseSteel762, "caseSteel762");
    	GameRegistry.registerItem(caseAluminium762, "caseAluminium762");
    	GameRegistry.registerItem(casePolymer762, "casePolymer762");
    	
    	GameRegistry.registerItem(caseBrass900, "caseBrass900");
    	GameRegistry.registerItem(caseSteel900, "caseSteel900");
    	GameRegistry.registerItem(caseAluminium900, "caseAluminium900");
    	GameRegistry.registerItem(casePolymer900, "casePolymer900");
    	
    	GameRegistry.registerItem(caseBrass127, "caseBrass127");
    	GameRegistry.registerItem(caseSteel127, "caseSteel127");
    	GameRegistry.registerItem(caseAluminium127, "caseAluminium127");
    	GameRegistry.registerItem(casePolymer127, "casePolymer127");
    	
    	GameRegistry.registerItem(bulletLead, "bulletLead");
    	GameRegistry.registerItem(bulletFMJ, "bulletFMJ");
    	GameRegistry.registerItem(bulletCopper, "bulletCopper");
    	GameRegistry.registerItem(bulletSteel, "bulletSteel");
    	GameRegistry.registerItem(bulletIncendiary, "bulletIncendiary");
    	GameRegistry.registerItem(bulletExploding, "bulletExploding");
    	GameRegistry.registerItem(bulletPoison, "bulletPoison");
    	GameRegistry.registerItem(bulletTracer, "bulletTracer");
    	GameRegistry.registerItem(bulletUranium, "bulletUranium");
    	GameRegistry.registerItem(bulletArmourPiercing, "bulletArmourPiercing");
    	GameRegistry.registerItem(bulletPolymer, "bulletPolymer");
    	
    	GameRegistry.registerItem(itemPartBolt, "itemPartBolt");
    	GameRegistry.registerItem(itemPartGear, "itemPartGear");
    	GameRegistry.registerItem(itemPartNut, "itemPartNut");
    	GameRegistry.registerItem(itemPartScrew, "itemPartScrew");
    	GameRegistry.registerItem(itemPartWasher, "itemPartWasher");
	}
}
