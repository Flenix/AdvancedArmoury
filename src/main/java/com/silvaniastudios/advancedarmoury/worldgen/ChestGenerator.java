package com.silvaniastudios.advancedarmoury.worldgen;

import java.util.ArrayList;
import java.util.Random;

import com.silvaniastudios.advancedarmoury.RarityRegistry;
import com.silvaniastudios.advancedarmoury.items.EnumRarity;

import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraftforge.common.ChestGenHooks;

public class ChestGenerator extends WeightedRandomChestContent {

	public ChestGenerator(ItemStack item, int chance, int min, int max) {
		super(item, chance, min, max);
	}
	
	@Override
	protected ItemStack[] generateChestContent(Random random, IInventory newInventory) {
		System.out.println("Calling generateChestContent");
		System.out.println("Inv: " + newInventory.getInventoryName());
		//Random is 0 to x-1, so +1 makes 1 to x instead.
		int rarityChance = random.nextInt(5000) + 1;
		
		EnumRarity rarity;
		
		if (rarityChance <= 2450) { rarity = EnumRarity.VERY_COMMON; } 		//49%
		else if (rarityChance <= 3900) { rarity = EnumRarity.COMMON; } 		//29%
		else if (rarityChance <= 4909) { rarity = EnumRarity.UNCOMMON; } 	//20%
		else if (rarityChance <= 4959) { rarity = EnumRarity.RARE; } 		//1% 	/ 50/5000
		else if (rarityChance <= 4984) { rarity = EnumRarity.VERY_RARE; } 	//0.5%	/ 25/5000
		else if (rarityChance <= 4994) { rarity = EnumRarity.LEGENDARY; } 	//0.2% 	/ 10/5000
		else if (rarityChance <= 4999) { rarity = EnumRarity.MYTHIC; } 		//0.1% 	/  5/5000
		else { rarity = EnumRarity.ULTIMATE; } 								//0.02% /  1/5000
		
		ArrayList<String> items = RarityRegistry.getList(rarity);
		String material;
		
		if (items.size() > 0) {
			material = items.get(random.nextInt(items.size()));
		} else {
			material = "Iron";
		}
		
        return ChestGenHooks.generateStacks(random, new ItemStack(Items.baked_potato, 1), 1, 1);
    }

}
