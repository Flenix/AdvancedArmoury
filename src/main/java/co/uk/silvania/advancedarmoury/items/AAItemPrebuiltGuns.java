package co.uk.silvania.advancedarmoury.items;

import co.uk.silvania.advancedarmoury.gun.prebuilt.*;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;

public class AAItemPrebuiltGuns {
	
	public static Item m4CarbinePolymer;
	public static Item m4CarbineBling;
	
	public static void init() {
		assaultRifles();
		registerItems();
	}
	
	public static void assaultRifles() {
		m4CarbinePolymer = new M4CarbinePolymer().setUnlocalizedName("m4CarbinePolymer");
		m4CarbineBling = new M4CarbineBling().setUnlocalizedName("m4CarbineBling");
	}
	
	public static void registerItems() {
		GameRegistry.registerItem(m4CarbinePolymer, "m4CarbinePolymer");
		GameRegistry.registerItem(m4CarbineBling, "m4CarbineBling");
	}

}
