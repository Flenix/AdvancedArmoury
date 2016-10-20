package com.silvaniastudios.advancedarmoury.items_old.rounds;

import java.util.List;

import com.silvaniastudios.advancedarmoury.AdvancedArmoury;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemCase extends Item {
	
	double calibre;
	
	public ItemCase(double calibre) {
		this.setCreativeTab(AdvancedArmoury.tabRounds);
		this.calibre = calibre;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister icon) {
		itemIcon = icon.registerIcon(AdvancedArmoury.modid + ":" + this.getUnlocalizedName().substring(5));
	}
	
	@Override
	@SideOnly(Side.CLIENT)
    public void addInformation(ItemStack item, EntityPlayer player, List list, boolean p_77624_4_) {
		if (calibre == 9.0D) {
			list.add("Calibre: 9mm");
		} else {
			list.add("Calibre: " + calibre + "mm");
		}
	}

}
