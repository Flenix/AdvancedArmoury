package com.silvaniastudios.advancedarmoury.items.modifiers;

import java.util.ArrayList;
import java.util.Random;

import com.silvaniastudios.advancedarmoury.AdvancedArmoury;
import com.silvaniastudios.advancedarmoury.items.EnumRarity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;

public class CoreWhatchaSay extends ModifierCoreBase {

	public CoreWhatchaSay(EnumRarity rarity) {
		super(rarity);
	}
	
	public boolean onItemUse(ItemStack p_77648_1_, EntityPlayer player, World p_77648_3_, int p_77648_4_, int p_77648_5_, int p_77648_6_, int p_77648_7_, float p_77648_8_, float p_77648_9_, float p_77648_10_)
    {
		System.out.println("Right click!");
		player.playSound(AdvancedArmoury.modid + ":whatcha_say", 1, 1.0F);
        return false;
    }
	
	@Override
	public void onEntityKilled(ItemStack item, EntityPlayer shooter, Entity target) {
		Random rand = new Random();
		int random = rand.nextInt(200);
		if (!shooter.worldObj.isRemote && target instanceof EntityPlayer) {
			//1 in 20 chance that it will play the sound at the fallen players location
			if (random <= 20) {
				target.worldObj.playSoundAtEntity(target, AdvancedArmoury.modid + ":whatcha_say", 5, 1.0F);
			//1 in 200 chance it will play the sound to all online players.
			} else if (random == 100) {
				ArrayList<EntityPlayer> players = (ArrayList<EntityPlayer>) MinecraftServer.getServer().getConfigurationManager().playerEntityList;
				for (int i = 0; i < players.size(); i++) {
					players.get(i).playSound(AdvancedArmoury.modid + ":whatcha_say", 1, 1.0F);
				}
			}
		}
	}
}
