package co.uk.silvania.advancedarmoury.blocks.decorative;

import java.util.List;

import co.uk.silvania.advancedarmoury.AdvancedArmoury;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockStairs;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class StairsAA extends BlockStairs {
	
	String texture;
	
	public StairsAA(Block block, String textureName) {
		super(block, 0);
		this.texture = textureName;
		this.setHardness(1.0F);
		this.setCreativeTab(AdvancedArmoury.tabGeneric);
		this.useNeighborBrightness = true;
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
