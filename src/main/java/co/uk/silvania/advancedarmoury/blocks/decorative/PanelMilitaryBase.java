package co.uk.silvania.advancedarmoury.blocks.decorative;

import net.minecraft.block.Block;
import net.minecraft.util.StatCollector;
import net.minecraft.world.IBlockAccess;

public class PanelMilitaryBase extends SlabMilitaryBase {

	public PanelMilitaryBase(Block block, String textureName) {
		super(block, textureName);
		this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.125F, 1.0F);
	}
	
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
