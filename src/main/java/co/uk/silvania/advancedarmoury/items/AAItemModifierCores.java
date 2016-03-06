package co.uk.silvania.advancedarmoury.items;

import co.uk.silvania.advancedarmoury.items.components.cores.CoreSimpleChamberNet;
import co.uk.silvania.advancedarmoury.items.components.cores.EnumRarity;
import co.uk.silvania.advancedarmoury.items.components.cores.ModifierCoreBase;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;

public class AAItemModifierCores {
	
	public static Item coreSimpleChamberNet;
	
	public static Item coreVeryCommonBlank;
	public static Item coreCommonBlank;
	public static Item coreUncommonBlank;
	public static Item coreRareBlank;
	public static Item coreVeryRareBlank;
	public static Item coreLegendaryBlank;
	public static Item coreMythicBlank;
	public static Item coreUltimateBlank;
	
	public static void init() {
		modifierCores();
		registerItems();
	}
	
	public static void modifierCores() {
		coreSimpleChamberNet = new CoreSimpleChamberNet(EnumRarity.VERY_COMMON).setUnlocalizedName("coreSimpleChamberNet");
		
		coreVeryCommonBlank = new ModifierCoreBase(EnumRarity.VERY_COMMON).setUnlocalizedName("coreVeryCommonBlank");
		coreCommonBlank = new ModifierCoreBase(EnumRarity.COMMON).setUnlocalizedName("coreCommonBlank");
		coreUncommonBlank = new ModifierCoreBase(EnumRarity.UNCOMMON).setUnlocalizedName("coreUncommonBlank");
		coreRareBlank = new ModifierCoreBase(EnumRarity.RARE).setUnlocalizedName("coreRareBlank");
		coreVeryRareBlank = new ModifierCoreBase(EnumRarity.VERY_RARE).setUnlocalizedName("coreVeryRareBlank");
		coreLegendaryBlank = new ModifierCoreBase(EnumRarity.LEGENDARY).setUnlocalizedName("coreLegendaryBlank");
		coreMythicBlank = new ModifierCoreBase(EnumRarity.MYTHIC).setUnlocalizedName("coreMythicBlank");
		coreUltimateBlank = new ModifierCoreBase(EnumRarity.ULTIMATE).setUnlocalizedName("coreUltimateBlank");
	}
	
	public static void registerItems() {
		GameRegistry.registerItem(coreSimpleChamberNet, "coreSimpleChamberNet");
    	
    	GameRegistry.registerItem(coreVeryCommonBlank, "coreVeryCommonBlank");
    	GameRegistry.registerItem(coreCommonBlank, "coreCommonBlank");
    	GameRegistry.registerItem(coreUncommonBlank, "coreUncommonBlank");
    	GameRegistry.registerItem(coreRareBlank, "coreRareBlank");
    	GameRegistry.registerItem(coreVeryRareBlank, "coreVeryRareBlank");
    	GameRegistry.registerItem(coreLegendaryBlank, "coreLegendaryBlank");
    	GameRegistry.registerItem(coreMythicBlank, "coreMythicBlank");
    	GameRegistry.registerItem(coreUltimateBlank, "coreUltimateBlank");
	}
	

}
