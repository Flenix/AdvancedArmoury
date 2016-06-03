package co.uk.silvania.advancedarmoury.blocks.decorative;

import co.uk.silvania.advancedarmoury.client.ClientProxy;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;

public class CornerPostMilitaryBase extends DecorativeMilitaryBase {

	public CornerPostMilitaryBase(String textureName, String blockType) {
		super(textureName, blockType);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public int getRenderType() {
		return ClientProxy.tinyLootCrateRenderID;
	}

}
