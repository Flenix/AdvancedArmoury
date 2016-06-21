package co.uk.silvania.advancedarmoury.blocks.decorative;

import net.minecraft.init.Blocks;
import net.minecraft.util.IIcon;

public class BlockHesco extends BlockAA {

	public BlockHesco(String textureName) {
		super(textureName);
	}
	
	public IIcon getIcon(int side, int meta) {
		if (side == 1) {
			return Blocks.dirt.getIcon(0, 0);
		}
		return blockIcon;
	}
}
