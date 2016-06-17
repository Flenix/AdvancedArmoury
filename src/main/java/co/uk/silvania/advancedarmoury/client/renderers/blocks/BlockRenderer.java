package co.uk.silvania.advancedarmoury.client.renderers.blocks;

import org.lwjgl.opengl.GL11;

import co.uk.silvania.advancedarmoury.blocks.decorative.WalkwayFence;
import co.uk.silvania.advancedarmoury.blocks.decorative.WalkwayStairs;
import co.uk.silvania.advancedarmoury.client.ClientProxy;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.init.Blocks;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.util.ForgeDirection;

public class BlockRenderer extends BlockRenderCore implements ISimpleBlockRenderingHandler {

	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer) {
		IIcon icon = block.getIcon(0, 0);
		
		double u0 = (double) icon.getMinU();
		double u1 = (double) icon.getMaxU();
		
		double v0 = (double) icon.getMinV();
		double v1 = (double) icon.getMaxV();
		
		renderBlock(null, 0, 0, 0, renderer, block, metadata, false);
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
		int meta = world.getBlockMetadata(x, y, z);
		
		IIcon icon = prepBlockAndGetIcon(block, world, x, y, z, renderer);
		
		double u0 = (double) icon.getMinU();
		double u1 = (double) icon.getMaxU();
		
		double v0 = (double) icon.getMinV();
		double v1 = (double) icon.getMaxV();

		renderBlock(world, x, y, z, renderer, block, meta, true);
		return true;
	}

	public void renderBlock(IBlockAccess world, int x, int y, int z, RenderBlocks renderer, Block block, int meta, boolean inWorld) {
		if (meta < 0 && inWorld) {
			meta = world.getBlockMetadata(x, y, z);
		}
		
		if (meta == 0) { renderBlock(0.0,  0.0, 0.0,  1.0,  1.0, 1.0,  inWorld, renderer, block, x, y, z, meta); }
		if (meta == 1) { renderBlock(0.0,  0.0, 0.0,  1.0,  0.5, 1.0,  inWorld, renderer, block, x, y, z, meta); }
		if (meta == 2) { renderBlock(0.0,  0.5, 0.0,  1.0,  1.0, 1.0,  inWorld, renderer, block, x, y, z, meta); }
		if (meta == 3) { renderBlock(0.25, 0.0, 0.25, 0.75, 1.0, 0.75, inWorld, renderer, block, x, y, z, meta); }
	}

	@Override
	public int getRenderId() {
		return ClientProxy.aaBlockRenderID;
	}
}