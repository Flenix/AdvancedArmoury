package com.silvaniastudios.advancedarmoury;

import com.silvaniastudios.advancedarmoury.worldgen.ChestGenerator;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.ChestGenHooks;

public class DungeonLootRegistry {
	
	public static void init() {
		ChestGenHooks cgh = ChestGenHooks.getInfo(ChestGenHooks.BONUS_CHEST);
		
		//100 is 3 in 10 chance
		cgh.addItem(new ChestGenerator(new ItemStack(Items.bone, 1), 100, 1, 1));
	}

}
