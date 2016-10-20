package com.silvaniastudios.advancedarmoury.blocks.decorative;

import com.silvaniastudios.advancedarmoury.AdvancedArmoury;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.util.StatCollector;
import net.minecraft.world.IBlockAccess;

public class Panel extends Block {

	String texture;
	
	public Panel(String textureName) {
		super(Material.rock);
		this.texture = textureName;
		this.setHardness(1.0F);
		this.setCreativeTab(AdvancedArmoury.tabGeneric);
		this.useNeighborBrightness = true;
		this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.125F, 1.0F);
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void registerBlockIcons(IIconRegister iconRegister) {
		blockIcon = iconRegister.registerIcon(AdvancedArmoury.modid + ":" + this.texture);
	}
	
    public float getExplosionResistance(Entity p_149638_1_) {
        return 50F;
    }
    
    @Override public boolean renderAsNormalBlock() { return false; }
	@Override public boolean isOpaqueCube() { return false; }
	
	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess block, int x, int y, int z) {
        boolean flag = (block.getBlockMetadata(x, y, z) & 8) != 0;

        if (flag) {
            this.setBlockBounds(0.0F, 0.75F, 0.0F, 1.0F, 1.0F, 1.0F);
        } else {
            this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.25F, 1.0F);
        }
    }
	
	@Override
	public void setBlockBoundsForItemRender() {
		this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.25F, 1.0F);
    }
}
