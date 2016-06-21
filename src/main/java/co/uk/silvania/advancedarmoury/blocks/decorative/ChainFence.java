package co.uk.silvania.advancedarmoury.blocks.decorative;

import co.uk.silvania.advancedarmoury.AdvancedArmoury;
import co.uk.silvania.advancedarmoury.client.ClientProxy;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;

public class ChainFence extends WalkwayFence {

	public ChainFence(String textureName) {
		super(textureName, "chainFence");
	}

	@SideOnly(Side.CLIENT)
	@Override
	public int getRenderType() {
		return ClientProxy.chainFenceRenderID;
	}
	
	@Override
	public String getBlockNameFromMeta(int meta) {
		if (meta <= 3) {
			return "oneChainFence";
		} else if (meta <= 7) {
			return "rightAngleChainFence";
		} else if (meta <= 11) {
			return "threeChainFence";
		} else if (meta <= 13) {
			return "parallelChainFence";
		} else if (meta == 14) {
			return "squareChainFence";
		} else
			return "connectedChainFence";
	}
	
	@SideOnly(Side.CLIENT)
	private IIcon poleIcon;
	
	@SideOnly(Side.CLIENT)
	@Override
	public void registerBlockIcons(IIconRegister iconRegister) {
		blockIcon = iconRegister.registerIcon(AdvancedArmoury.modid + ":" + texture);
		poleIcon = iconRegister.registerIcon(AdvancedArmoury.modid + ":chainPole");
	}
	
	public IIcon getIcon(int side, int meta) {
		if (side > 5) {
			return poleIcon;
		}
		return blockIcon;
	}
}
