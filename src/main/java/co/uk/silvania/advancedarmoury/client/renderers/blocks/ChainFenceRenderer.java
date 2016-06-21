//CLONED FROM FLENIXCITIES Walkway Renderer, then modified.
//Taken with permission, as it's my own damn mod. :D
package co.uk.silvania.advancedarmoury.client.renderers.blocks;

import co.uk.silvania.advancedarmoury.AABlocks;
import co.uk.silvania.advancedarmoury.blocks.decorative.CornerPost;
import co.uk.silvania.advancedarmoury.blocks.decorative.WalkwayFence;
import co.uk.silvania.advancedarmoury.blocks.decorative.WalkwayStairs;
import co.uk.silvania.advancedarmoury.client.ClientProxy;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.init.Blocks;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.util.ForgeDirection;

public class ChainFenceRenderer extends BlockRenderCore implements ISimpleBlockRenderingHandler {

	CornerPostRenderer cornerPostRenderer = new CornerPostRenderer();

	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer) {
		if (metadata <= 3) {
			renderBlock(0.0D,   0.875D, 0.0D,   0.125D, 1.0D, 1.0D,   false, renderer, block, 0, 0, 0, 0); //Left top
			renderBlock(0.03175D, 0.0D, 0.0D, 0.09325D, 1.0D, 1.0D, false, renderer, block, 0, 0, 0, 0); //Left 1
		} else if (metadata <= 7) {
			renderBlock(0.0D,   0.875D, 0.0D,   0.125D, 1.0D, 1.0D,   false, renderer, block, 0, 0, 0, 0); //Left top
			renderBlock(0.0D,   0.875D, 0.0D,   1.0D,   1.0D, 0.125D, false, renderer, block, 0, 0, 0, 0); //Back top
			
			renderBlock(0.03175D, 0.0D, 0.0D, 0.09325D, 1.0D, 1.0D, false, renderer, block, 0, 0, 0, 0); //Left 1
			renderBlock(0.0D, 0.0D, 0.03175D, 1.0D, 1.0D, 0.09325D, false, renderer, block, 0, 0, 0, 0); //Back 1
		} else if (metadata <= 11) {
			renderBlock(0.0D,   0.875D, 0.0D,   0.125D, 1.0D, 1.0D,   false, renderer, block, 0, 0, 0, 0); //Left top
			renderBlock(0.0D,   0.875D, 0.0D,   1.0D,   1.0D, 0.125D, false, renderer, block, 0, 0, 0, 0); //Back top
			renderBlock(0.875D, 0.875D, 0.0D,   1.0D,   1.0D, 1.0D,   false, renderer, block, 0, 0, 0, 0); //Right top
			
			renderBlock(0.03175D, 0.0D, 0.0D, 0.09325D, 1.0D, 1.0D, false, renderer, block, 0, 0, 0, 0); //Left 1
			renderBlock(0.0D, 0.0D, 0.03175D, 1.0D, 1.0D, 0.09325D, false, renderer, block, 0, 0, 0, 0); //Back 1
			renderBlock(0.90675D, 0.0D, 0.0D, 0.96825D, 1.0D, 1.0D, false, renderer, block, 0, 0, 0, 0); //Right 1
		} else if (metadata <= 13) {
			renderBlock(0.0D,   0.875D, 0.0D,   0.125D, 1.0D, 1.0D,   false, renderer, block, 0, 0, 0, 0); //Left top
			renderBlock(0.875D, 0.875D, 0.0D,   1.0D,   1.0D, 1.0D,   false, renderer, block, 0, 0, 0, 0); //Right top
			
			renderBlock(0.03175D, 0.0D, 0.0D, 0.09325D, 1.0D, 1.0D, false, renderer, block, 0, 0, 0, 0); //Left 1
			renderBlock(0.90675D, 0.0D, 0.0D, 0.96825D, 1.0D, 1.0D, false, renderer, block, 0, 0, 0, 0); //Right 1
		} else {
			renderBlock(0.0D,   0.875D, 0.0D,   0.125D, 1.0D, 1.0D,   false, renderer, block, 0, 0, 0, 0); //Left top
			renderBlock(0.0D,   0.875D, 0.0D,   1.0D,   1.0D, 0.125D, false, renderer, block, 0, 0, 0, 0); //Back top
			renderBlock(0.875D, 0.875D, 0.0D,   1.0D,   1.0D, 1.0D,   false, renderer, block, 0, 0, 0, 0); //Right top
			renderBlock(0.0D,   0.875D, 0.875D, 1.0D,   1.0D, 1.0D,   false, renderer, block, 0, 0, 0, 0); //Front top
			
			renderBlock(0.03175D, 0.0D, 0.0D, 0.09325D, 1.0D, 1.0D, false, renderer, block, 0, 0, 0, 0); //Left 1
			renderBlock(0.0D, 0.0D, 0.03175D, 1.0D, 1.0D, 0.09325D, false, renderer, block, 0, 0, 0, 0); //Back 1
			renderBlock(0.90675D, 0.0D, 0.0D, 0.96825D, 1.0D, 1.0D, false, renderer, block, 0, 0, 0, 0); //Right 1
			renderBlock(0.0D, 0.0D, 0.90675D, 1.0D, 1.0D, 0.96825D, false, renderer, block, 0, 0, 0, 0); //Front 1
		}
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
		int meta = world.getBlockMetadata(x, y, z);
		
		boolean connectNorth = checkConnections(world, x, y, z-1, 0, meta, false);
		boolean connectEast =  checkConnections(world, x+1, y, z, 1, meta, false);
		boolean connectSouth = checkConnections(world, x, y, z+1, 0, meta, false);
		boolean connectWest =  checkConnections(world, x-1, y, z, 1, meta, false);
		
		boolean walkwayAbove = world.getBlock(x, y+1, z) instanceof WalkwayFence;
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
		
		boolean renderNorthEast = (renderNorth && renderEast) ? true : false;
		boolean renderNorthWest = (renderNorth && renderWest) ? true : false;
		boolean renderSouthEast = (renderSouth && renderEast) ? true : false;
		boolean renderSouthWest = (renderSouth && renderWest) ? true : false;
		
		double barHeight = walkwayAbove ? 1.0D : 0.9325D;

		if (renderNorth) {
			if (!walkwayAbove) {
				//Alter sizes based on connections.
				double cnct1 = 0;
				double cnct2 = 0;
				if (renderEast) { cnct1 = 0.125D; }
				if (renderWest) { cnct2 = 0.125D; }
				
				renderOtherBlock(0.0D + cnct2, 0.875D, 0.0D, 1.0D - cnct1, 1.0D, 0.125D, true, renderer, block, x, y, z, meta, block.getIcon(6, 0));
			}
			renderBlock(0.0D, 0.0D, 0.0675D, 1.0D, barHeight, 0.0675D, true, renderer, block, x, y, z, meta);
			if (!connectEast) { renderNorthEast = true; }
			if (!connectWest) { renderNorthWest = true; }
		}
		
		if (renderSouth) {
			if (!walkwayAbove) {
				double cnct1 = 0;
				double cnct2 = 0;
				if (renderEast) { cnct1 = 0.125D; }
				if (renderWest) { cnct2 = 0.125D; }
				
				renderOtherBlock(0.0D + cnct2, 0.875D, 0.875D, 1.0D - cnct1, 1.0D, 1.0D, true, renderer, block, x, y, z, meta, block.getIcon(6, 0));
			}
			renderBlock(0.0D, 0.0D, 0.90675D, 1.0D, barHeight, 0.90675D, true, renderer, block, x, y, z, meta);
			if (!connectEast) { renderSouthEast = true; }
			if (!connectWest) { renderSouthWest = true; }
		}
		
		if (renderEast) {
			if (!walkwayAbove) {
				double cnct1 = 0;
				double cnct2 = 0;
				if (renderNorth) { cnct1 = 0.125D; }
				if (renderSouth) { cnct2 = 0.125D; }
				
				renderOtherBlock(0.875D, 0.875D, 0.0D + cnct1, 1.0D, 1.0D, 1.0D - cnct2, true, renderer, block, x, y, z, meta, block.getIcon(6, 0));
			}
			renderBlock(0.9325D, 0.0D, 0.0D, 0.9325D, barHeight, 1.0D, true, renderer, block, x, y, z, meta);
			if (!connectNorth) { renderNorthEast = true; }
			if (!connectSouth) { renderSouthEast = true; }
		}
		
		if (renderWest) { //-0.625D 1.0625D
			if (!walkwayAbove) {
				double cnct1 = 0;
				double cnct2 = 0;
				if (renderNorth) { cnct1 = 0.125D; }
				if (renderSouth) { cnct2 = 0.125D; }
				
				renderOtherBlock(0.0D, 0.875D, 0.0D + cnct1, 0.125D, 1.0D, 1.0D - cnct2, true, renderer, block, x, y, z, meta, block.getIcon(6, 0));
			}
			renderBlock(0.0675D, 0.0D, 0.0D, 0.0675D, barHeight, 1.0D, true, renderer, block, x, y, z, meta);
			if (!connectNorth) { renderNorthWest = true; }
			if (!connectSouth) { renderSouthWest = true; }
		}
		
		//Notches in the corners.
		if (renderNorthEast) {
			renderOtherBlock(0.875D, 0.0D, 0.0D, 1.0D, 1.0D, 0.125D, true, renderer, block, x, y, z, meta, block.getIcon(6, 0));
		}
		
		if (renderNorthWest) {
			renderOtherBlock(0.0D, 0.0D, 0.0D, 0.125D, 1.0D, 0.125D, true, renderer, block, x, y, z, meta, block.getIcon(6, 0));
		}
		
		if (renderSouthEast) {
			renderOtherBlock(0.875D, 0.0D, 0.875D, 1.0D, 1.0D, 1.0D, true, renderer, block, x, y, z, meta, block.getIcon(6, 0));
		}
		
		if (renderSouthWest) {
			renderOtherBlock(0.0D, 0.0D, 0.875D, 0.125D, 1.0D, 1.0D, true, renderer, block, x, y, z, meta, block.getIcon(6, 0));
		}
		return true;
	}

	@Override
	public int getRenderId() {
		return ClientProxy.chainFenceRenderID;
	}
}