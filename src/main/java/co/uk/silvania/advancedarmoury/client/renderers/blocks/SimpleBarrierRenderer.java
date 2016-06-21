//CLONED FROM FLENIXCITIES Walkway Renderer, then modified.
//Taken with permission, as it's my own damn mod. :D
package co.uk.silvania.advancedarmoury.client.renderers.blocks;

import co.uk.silvania.advancedarmoury.blocks.decorative.CornerPost;
import co.uk.silvania.advancedarmoury.blocks.decorative.WalkwayFence;
import co.uk.silvania.advancedarmoury.blocks.decorative.WalkwayStairs;
import co.uk.silvania.advancedarmoury.client.ClientProxy;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.util.ForgeDirection;

public class SimpleBarrierRenderer extends BlockRenderCore implements ISimpleBlockRenderingHandler {
	
	CornerPostRenderer cornerPostRenderer = new CornerPostRenderer();

	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer) {
		if (metadata <= 3) {
			renderBlock(0.0D,   0.0D, 0.0D,   0.125D, 1.0D, 1.0D,   false, renderer, block, 0, 0, 0, 0); //Left top
		} else if (metadata <= 7) {
			renderBlock(0.0D,   0.0D, 0.0D,   0.125D, 1.0D, 1.0D,   false, renderer, block, 0, 0, 0, 0); //Left top
			renderBlock(0.0D,   0.0D, 0.0D,   1.0D,   1.0D, 0.125D, false, renderer, block, 0, 0, 0, 0); //Back top

		} else if (metadata <= 11) {
			renderBlock(0.0D,   0.0D, 0.0D,   0.125D, 1.0D, 1.0D,   false, renderer, block, 0, 0, 0, 0); //Left top
			renderBlock(0.0D,   0.0D, 0.0D,   1.0D,   1.0D, 0.125D, false, renderer, block, 0, 0, 0, 0); //Back top
			renderBlock(0.875D, 0.0D, 0.0D,   1.0D,   1.0D, 1.0D,   false, renderer, block, 0, 0, 0, 0); //Right top
		} else if (metadata <= 13) {
			renderBlock(0.0D,   0.0D, 0.0D,   0.125D, 1.0D, 1.0D,   false, renderer, block, 0, 0, 0, 0); //Left top
			renderBlock(0.875D, 0.0D, 0.0D,   1.0D,   1.0D, 1.0D,   false, renderer, block, 0, 0, 0, 0); //Right top
		} else {
			renderBlock(0.0D,   0.0D, 0.0D,   0.125D, 1.0D, 1.0D,   false, renderer, block, 0, 0, 0, 0); //Left top
			renderBlock(0.0D,   0.0D, 0.0D,   1.0D,   1.0D, 0.125D, false, renderer, block, 0, 0, 0, 0); //Back top
			renderBlock(0.875D, 0.0D, 0.0D,   1.0D,   1.0D, 1.0D,   false, renderer, block, 0, 0, 0, 0); //Right top
			renderBlock(0.0D,   0.0D, 0.875D, 1.0D,   1.0D, 1.0D,   false, renderer, block, 0, 0, 0, 0); //Front top
		}
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
		int meta = world.getBlockMetadata(x, y, z);
		
		boolean connectNorth = checkConnections(world, x, y, z-1, 0, meta, true);
		boolean connectEast =  checkConnections(world, x+1, y, z, 1, meta, true);
		boolean connectSouth = checkConnections(world, x, y, z+1, 0, meta, true);
		boolean connectWest =  checkConnections(world, x-1, y, z, 1, meta, true);
		
		boolean cornerPostAbove = world.getBlock(x, y+1, z) instanceof CornerPost;
		boolean cornerPostBelow = world.getBlock(x, y-1, z) instanceof CornerPost;
		
		if (world.getBlock(x, y+1, z) instanceof CornerPost) { cornerPostRenderer.renderCornerPosts(world, x, y, z,  1, renderer, world.getBlock(x, y+1, z), -1, true); }
		if (world.getBlock(x, y-1, z) instanceof CornerPost) { cornerPostRenderer.renderCornerPosts(world, x, y, z, -1, renderer, world.getBlock(x, y-1, z), -1, true); }
		
		boolean renderNorth = false; //X 0-1,  Z 0
		boolean renderEast  = false; //X 1,    Z 0-1
		boolean renderSouth = false; //X 0-1,  Z 1
		boolean renderWest  = false; //X 0,    Z 0-1
		
		if (meta == 0)  { renderNorth = true; }
		if (meta == 1)  { renderEast  = true; }
		if (meta == 2)  { renderSouth = true; }
		if (meta == 3)  { renderWest  = true; }
		if (meta == 4)  { renderNorth = renderEast  = true; }
		if (meta == 5)  { renderSouth = renderEast  = true; }
		if (meta == 6)  { renderSouth = renderWest  = true; }
		if (meta == 7)  { renderNorth = renderWest  = true; }
		if (meta == 8)  { renderNorth = renderEast  = renderSouth = true;}
		if (meta == 9)  { renderEast  = renderSouth = renderWest  = true;}
		if (meta == 10) { renderSouth = renderWest  = renderNorth = true;}
		if (meta == 11) { renderWest  = renderNorth = renderEast  = true;}
		if (meta == 12) { renderNorth = renderSouth = true; }
		if (meta == 13) { renderEast  = renderWest  = true; }
		if (meta == 14) { renderNorth = renderEast  = renderSouth = renderWest = true; }
		if (meta == 15) {
			if (!connectEast)  { renderEast = true; }
			if (!connectWest)  { renderWest = true; }
			if (!connectNorth) { renderNorth = true; }
			if (!connectSouth) { renderSouth = true; }
		}

		if (renderNorth) {
			renderBlock(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 0.125D, true, renderer, block, x, y, z, meta);
		}
		
		if (renderSouth) {
			renderBlock(0.0, 0.0D, 0.875D, 1.0D, 1.0D, 1.0D, true, renderer, block, x, y, z, meta);
		}
		
		if (renderEast) {
			renderBlock(0.875D, 0.0D, 0.125D, 1.0D, 1.0D, 0.875D, true, renderer, block, x, y, z, meta);
		}
		
		if (renderWest) {
			renderBlock(0.0D, 0.0D, 0.125D, 0.125D, 1.0D, 0.875D, true, renderer, block, x, y, z, meta);
		}
		
		//Notches in the corners.
		if (!renderNorth && renderEast) {
			renderBlock(0.875D, 0.0D, 0.0D, 1.0D, 1.0D, 0.125D, true, renderer, block, x, y, z, meta);
		}
		
		if (!renderNorth && renderWest) {
			renderBlock(0.0D, 0.0D, 0.0D, 0.125D, 1.0D, 0.125D, true, renderer, block, x, y, z, meta);
		}
		
		if (!renderSouth && renderEast) {
			renderBlock(0.875D, 0.0D, 0.875D, 1.0D, 1.0D, 1.0D, true, renderer, block, x, y, z, meta);
		}
		
		if (!renderSouth && renderWest) {
			renderBlock(0.0D, 0.0D, 0.875D, 0.125D, 1.0D, 1.0D, true, renderer, block, x, y, z, meta);
		}
		
		return true;
	}

	@Override
	public int getRenderId() {
		return ClientProxy.walkwayFenceRenderID;
	}
}