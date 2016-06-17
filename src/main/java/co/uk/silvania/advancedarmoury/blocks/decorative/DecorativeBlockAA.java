package co.uk.silvania.advancedarmoury.blocks.decorative;

import co.uk.silvania.advancedarmoury.AdvancedArmoury;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.world.IBlockAccess;

public class DecorativeBlockAA extends BlockAA {
	
	String blockType;
	String texture;

	public DecorativeBlockAA(String textureName, String blockType) {
		super(textureName);
		this.blockType = blockType;
		this.texture = textureName;
	}
	
	@Override public boolean renderAsNormalBlock() { return false; }
	@Override public boolean isOpaqueCube() { return false; }
	
	@SideOnly(Side.CLIENT)
    public boolean shouldSideBeRendered(IBlockAccess world, int x, int y, int z, int side) {
        return true;
    }
	
	@SideOnly(Side.CLIENT)
	@Override
	public void registerBlockIcons(IIconRegister iconRegister) {
		blockIcon = iconRegister.registerIcon(AdvancedArmoury.modid + ":" + texture);
	}
	
	public String getBlockNameFromMeta(int meta) {
		return "";
	}
	
	public float getExplosionResistance(Entity p_149638_1_) {
        return 50F;
    }
}
