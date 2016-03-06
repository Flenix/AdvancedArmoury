package co.uk.silvania.advancedarmoury.client.renderers.blocks;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.world.IBlockAccess;

public class RenderBlockAA {
	
	public static void renderBlock(IBlockAccess world, double minX, double minY, double minZ, double maxX, double maxY, double maxZ, boolean existsInWorld, Block block, int x, int y, int z, int meta) {
		RenderBlocks renderer = new RenderBlocks(world);
		renderer.setOverrideBlockTexture(block.getIcon(0, 0));
		renderer.setRenderBounds(minX, minY, minZ, maxX, maxY, maxZ);
		if (existsInWorld) {
			renderer.renderStandardBlock(block, x, y, z);
		}
		renderer.clearOverrideBlockTexture();
	}
}
