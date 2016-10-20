package com.silvaniastudios.advancedarmoury.gun.inventory;

import com.silvaniastudios.advancedarmoury.AAUtils;
import com.silvaniastudios.advancedarmoury.AdvancedArmoury;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;

public class ItemInventory extends Item {
	
	public int invSize;
	
	public ItemInventory() {
		maxStackSize = 1;
		setCreativeTab(AdvancedArmoury.tabGeneric);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister icon) {
		itemIcon = icon.registerIcon(AdvancedArmoury.modid + ":" + this.getUnlocalizedName().substring(5));
	}
	
	@Override
	public int getMaxItemUseDuration(ItemStack item) {
		return 1;
	}

}
