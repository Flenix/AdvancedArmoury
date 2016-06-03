package co.uk.silvania.advancedarmoury.items_old;

import co.uk.silvania.advancedarmoury.gun.prebuilt.*;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;

public class AAItemPrebuiltGuns {
	
	public static Item m4CarbineStandard;
	public static Item m4CarbineBling;
	
	public static void init() {
		assaultRifles();
		registerItems();
	}
	
	public static void assaultRifles() {
		m4CarbineStandard = new M4CarbinePolymer().setUnlocalizedName("m4CarbineStandard");
		m4CarbineBling = new M4CarbineBling().setUnlocalizedName("m4CarbineBling");
	}
	
	public static void registerItems() {
		GameRegistry.registerItem(m4CarbineStandard, "m4CarbineStandard");
		GameRegistry.registerItem(m4CarbineBling, "m4CarbineBling");
	}

}
