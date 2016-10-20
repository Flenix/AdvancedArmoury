package com.silvaniastudios.advancedarmoury.blocks.decorative;

import com.silvaniastudios.advancedarmoury.AdvancedArmoury;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockFence;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.util.StatCollector;

public class FenceAA extends BlockFence {
	
	String texture;

	public FenceAA(String textureName) {
		super(textureName, Material.rock);
		this.texture = textureName;
		this.setHardness(1.0F);
		this.setCreativeTab(AdvancedArmoury.tabGeneric);
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void registerBlockIcons(IIconRegister iconRegister) {
		blockIcon = iconRegister.registerIcon(AdvancedArmoury.modid + ":" + this.texture);
	}
	
	public float getExplosionResistance(Entity p_149638_1_) {
        return 50F;
    }
}
