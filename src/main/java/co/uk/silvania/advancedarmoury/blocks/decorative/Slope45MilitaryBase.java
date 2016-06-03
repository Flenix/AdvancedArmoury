package co.uk.silvania.advancedarmoury.blocks.decorative;

import co.uk.silvania.advancedarmoury.client.ClientProxy;
import net.minecraft.block.Block;

public class Slope45MilitaryBase extends StairsMilitaryBase {

	public Slope45MilitaryBase(Block block, String textureName) {
		super(block, textureName);
	}
	
	@Override
	public int getRenderType() {
		return ClientProxy.slope45RenderID;
	}
}
