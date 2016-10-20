package com.silvaniastudios.advancedarmoury.items;

import java.util.List;

import com.silvaniastudios.advancedarmoury.AdvancedArmoury;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class ItemParts extends Item {
	
	int baseValue;

	public ItemParts(int baseValue) {
		super();
		this.setHasSubtypes(true);
		this.setMaxDamage(0);
		this.setCreativeTab(AdvancedArmoury.tabGeneric);
		this.baseValue = baseValue;
	}
	
	@SideOnly(Side.CLIENT)
	private IIcon[] icons;
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister icon) {
		icons = new IIcon[4];
		for (int i = 0; i < icons.length; i++) { 
			this.icons[i] = icon.registerIcon(AdvancedArmoury.modid + ":" + this.getUnlocalizedName().substring(5) + "_" + i);
		}
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIconFromDamage(int meta) {
		if (meta > (icons.length - 1)) {
			meta = 0;
		}
		return this.icons[meta];
	}
	
	@Override
	public void getSubItems(Item item, CreativeTabs tab, List list) {
		for (int i = 0; i < icons.length; i++) {
			list.add(new ItemStack(item, 1, i));
		}
	}
	
	@Override
	public void onUpdate(ItemStack item, World world, Entity player, int p_77663_4_, boolean isCurrentItem) {
		int meta = item.getItemDamage();
		if (item.stackTagCompound != null) {
			
		} else {
			item.stackTagCompound = new NBTTagCompound();
			item.stackTagCompound.setString("material", getMaterial(meta));
			item.stackTagCompound.setInteger("value", getValue(meta));
		}
	}
	
	public String getMaterial(int meta) {
		if (meta == 0) {
			return "iron";
		} else if (meta == 1) {
			return "gold";
		} else if (meta == 2) {
			return "promethium";
		} else if (meta == 3) {
			return "rusty";
		}
		return "null";
	}
	
	public int valueModifier(int meta) {
		if (meta == 0) {
			return 4;
		} else if (meta == 1) {
			return 10;
		} else if (meta == 2) {
			return 45;
		} else if (meta == 3) {
			return 1;
		}
		return 1;
	}
	
	public int getValue(int meta) {
		return baseValue * valueModifier(meta);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
    public void addInformation(ItemStack item, EntityPlayer player, List list, boolean isInHand) {
		list.add("Value: " + getValue(item.getItemDamage()));
	}
	
	@Override
	public String getUnlocalizedName(ItemStack item) {
		return this.getUnlocalizedName() + "_" + item.getItemDamage();
	}
}
