package com.silvaniastudios.advancedarmoury.items_old.rounds;

import com.silvaniastudios.advancedarmoury.AdvancedArmoury;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;

public class ItemPropellant extends Item {
	
	public ItemPropellant() {
		this.setCreativeTab(AdvancedArmoury.tabRounds);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister icon) {
		itemIcon = icon.registerIcon(AdvancedArmoury.modid + ":" + this.getUnlocalizedName().substring(5));
	}

}
