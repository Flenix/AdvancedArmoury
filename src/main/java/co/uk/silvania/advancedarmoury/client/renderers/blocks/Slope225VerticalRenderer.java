package co.uk.silvania.advancedarmoury.client.renderers.blocks;

import org.lwjgl.opengl.GL11;

import co.uk.silvania.advancedarmoury.blocks.decorative.CornerPost;
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

public class Slope225VerticalRenderer extends BlockRenderCore implements ISimpleBlockRenderingHandler {
	
	CornerPostRenderer cornerPostRenderer = new CornerPostRenderer();

	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer) {
		IIcon icon = block.getIcon(0, 0);
		
		double u0 = (double) icon.getMinU();
		double u1 = (double) icon.getMaxU();
		
		double v0 = (double) icon.getMinV();
		double v1 = (double) icon.getMaxV();
		
		int x = 0, y = 0, z = 0;
		tess.startDrawingQuads();
		tess.setColorOpaque_F(0.8F, 0.8F, 0.8F);
		tess.addVertexWithUV(x + 1, y + 0,   z + 1, u1, v1);
		tess.addVertexWithUV(x + 1, y + 0.5, z,     u1, v0);
		tess.addVertexWithUV(x,     y + 0.5, z,     u0, v0);
		tess.addVertexWithUV(x,     y + 0,   z + 1, u0, v1);
		tess.draw();
		
		//Back
		tess.startDrawingQuads();
		tess.setColorOpaque_F(0.8F, 0.8F, 0.8F);
		tess.addVertexWithUV(x,     y + 0,   z, u1, v1);
		tess.addVertexWithUV(x,     y + 0.5, z, u1, v0);
		tess.addVertexWithUV(x + 1, y + 0.5, z, u0, v0);
		tess.addVertexWithUV(x + 1, y + 0,   z, u0, v1);
		tess.draw();
		
		//Left
		tess.startDrawingQuads();
		tess.setColorOpaque_F(0.6F, 0.6F, 0.6F);
		tess.addVertexWithUV(x, y + 0,   z + 1, u1, v1);
		tess.addVertexWithUV(x, y + 0,   z + 1, u1, v0);
		tess.addVertexWithUV(x, y + 0.5, z + 0, u0, v0);
		tess.addVertexWithUV(x, y + 0,   z + 0, u0, v1);
		tess.draw();
		
		//Right
		tess.startDrawingQuads();
		tess.setColorOpaque_F(0.6F, 0.6F, 0.6F);
		tess.addVertexWithUV(x + 1, y + 0,   z + 0, u1, v1);
		tess.addVertexWithUV(x + 1, y + 0.5, z + 0, u1, v0);
		tess.addVertexWithUV(x + 1, y + 0,   z + 1, u0, v0);
		tess.addVertexWithUV(x + 1, y + 0,   z + 1, u0, v1);
		tess.draw();
		
		//Bottom
		tess.startDrawingQuads();
		tess.setColorOpaque_F(0.5F, 0.5F, 0.5F);
		tess.addVertexWithUV(x + 1, y, z + 0, u1, v1);
		tess.addVertexWithUV(x + 1, y, z + 1, u1, v0);
		tess.addVertexWithUV(x,     y, z + 1, u0, v0);
		tess.addVertexWithUV(x,     y, z + 0, u0, v1);
		tess.draw();
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
		int meta = world.getBlockMetadata(x, y, z);
		
		IIcon icon = prepBlockAndGetIcon(block, world, x, y, z, renderer);
		
		double u0 = (double) icon.getMinU();
		double u1 = (double) icon.getMaxU();
		
		double v0 = (double) icon.getMinV();
		double v1 = (double) icon.getMaxV();
		
		if (world.getBlock(x, y+1, z) instanceof CornerPost) { cornerPostRenderer.renderCornerPosts(world, x, y, z,  1, renderer, world.getBlock(x, y+1, z), -1, true); }
		if (world.getBlock(x, y-1, z) instanceof CornerPost) { cornerPostRenderer.renderCornerPosts(world, x, y, z, -1, renderer, world.getBlock(x, y-1, z), -1, true); }
		
		tess.draw();
		if (meta == 0)  { //Player facing EAST, Wide base narrow top, low-style
			//EAST
			tess.startDrawingQuads();
			tess.setColorOpaque_F(0.8F, 0.8F, 0.8F);
			tess.addVertexWithUV(x + 1, y + 1, z + 1, u1, v1);
			tess.addVertexWithUV(x + 1, y + 0, z + 1, u1, v0);
			tess.addVertexWithUV(x + 1, y + 0, z + 0, u0, v0);
			tess.addVertexWithUV(x + 1, y + 1, z + 0, u0, v1);
			tess.draw();
			
			//WEST
			tess.startDrawingQuads();
			tess.setColorOpaque_F(1.0F, 1.0F, 1.0F);
			tess.addVertexWithUV(x + 1,   y + 1, z + 0, u1, v1);
			tess.addVertexWithUV(x + 0.5, y + 0, z + 0, u1, v0);
			tess.addVertexWithUV(x + 0.5, y + 0, z + 1, u0, v0);
			tess.addVertexWithUV(x + 1,   y + 1, z + 1, u0, v1);
			tess.draw();
			
			//BOTTOM
			tess.startDrawingQuads();
			tess.setColorOpaque_F(0.6F, 0.6F, 0.6F);
			tess.addVertexWithUV(x + 0.5, y + 0, z + 1, u1, v1);
			tess.addVertexWithUV(x + 0.5, y + 0, z + 0, u1, v0);
			tess.addVertexWithUV(x + 1,   y + 0, z + 0, u0, v0);
			tess.addVertexWithUV(x + 1,   y + 0, z + 1, u0, v1);
			tess.draw();
			
			//SOUTH
			tess.startDrawingQuads();
			tess.setColorOpaque_F(0.6F, 0.6F, 0.6F);
			tess.addVertexWithUV(x + 1,   y + 1, z + 1, u1, v1);
			tess.addVertexWithUV(x + 0.5, y + 0, z + 1, u1, v0);
			tess.addVertexWithUV(x + 1,   y + 0, z + 1, u0, v0);
			tess.addVertexWithUV(x + 1,   y + 1, z + 1, u0, v1);
			tess.draw();
			
			//NORTH
			tess.startDrawingQuads();
			tess.setColorOpaque_F(0.6F, 0.6F, 0.6F);
			tess.addVertexWithUV(x + 1,   y + 1, z + 0, u1, v1);
			tess.addVertexWithUV(x + 1,   y + 0, z + 0, u1, v0);
			tess.addVertexWithUV(x + 0.5, y + 0, z + 0, u0, v0);
			tess.addVertexWithUV(x + 1,   y + 1, z + 0, u0, v1);
			tess.draw();
		}
		if (meta == 1)  { //Player facing WEST, Wide base narrow top, low-style
			//EAST
			tess.startDrawingQuads();
			tess.setColorOpaque_F(0.8F, 0.8F, 0.8F);
			tess.addVertexWithUV(x + 0,   y + 1, z + 1, u1, v1);
			tess.addVertexWithUV(x + 0.5, y + 0, z + 1, u1, v0);
			tess.addVertexWithUV(x + 0.5, y + 0, z + 0, u0, v0);
			tess.addVertexWithUV(x + 0,   y + 1, z + 0, u0, v1);
			tess.draw();
			
			//WEST
			tess.startDrawingQuads();
			tess.setColorOpaque_F(0.8F, 0.8F, 0.8F);
			tess.addVertexWithUV(x + 0, y + 1, z + 0, u1, v1);
			tess.addVertexWithUV(x + 0, y + 0, z + 0, u1, v0);
			tess.addVertexWithUV(x + 0, y + 0, z + 1, u0, v0);
			tess.addVertexWithUV(x + 0, y + 1, z + 1, u0, v1);
			tess.draw();
			
			//BOTTOM
			tess.startDrawingQuads();
			tess.setColorOpaque_F(0.6F, 0.6F, 0.6F);
			tess.addVertexWithUV(x + 0,   y + 0, z + 1, u1, v1);
			tess.addVertexWithUV(x + 0,   y + 0, z + 0, u1, v0);
			tess.addVertexWithUV(x + 0.5, y + 0, z + 0, u0, v0);
			tess.addVertexWithUV(x + 0.5, y + 0, z + 1, u0, v1);
			tess.draw();
			
			//SOUTH
			tess.startDrawingQuads();
			tess.setColorOpaque_F(0.6F, 0.6F, 0.6F);
			tess.addVertexWithUV(x + 0,   y + 1, z + 1, u1, v1);
			tess.addVertexWithUV(x + 0,   y + 0, z + 1, u1, v0);
			tess.addVertexWithUV(x + 0.5, y + 0, z + 1, u0, v0);
			tess.addVertexWithUV(x + 0,   y + 1, z + 1, u0, v1);
			tess.draw();
			
			//NORTH
			tess.startDrawingQuads();
			tess.setColorOpaque_F(0.6F, 0.6F, 0.6F);
			tess.addVertexWithUV(x + 0,   y + 1, z + 0, u1, v1);
			tess.addVertexWithUV(x + 0.5, y + 0, z + 0, u1, v0);
			tess.addVertexWithUV(x + 0,   y + 0, z + 0, u0, v0);
			tess.addVertexWithUV(x + 0,   y + 1, z + 0, u0, v1);
			tess.draw();
		}
		if (meta == 2)  { //Player facing SOUTH, Wide base narrow top, low-style
			//EAST
			tess.startDrawingQuads();
			tess.setColorOpaque_F(0.8F, 0.8F, 0.8F);
			tess.addVertexWithUV(x + 1, y + 1, z + 1,   u1, v1);
			tess.addVertexWithUV(x + 1, y + 0, z + 1,   u1, v0);
			tess.addVertexWithUV(x + 1, y + 0, z + 0.5, u0, v0);
			tess.addVertexWithUV(x + 1, y + 1, z + 1,   u0, v1);
			tess.draw();
			
			//WEST
			tess.startDrawingQuads();
			tess.setColorOpaque_F(0.8F, 0.8F, 0.8F);
			tess.addVertexWithUV(x + 0, y + 1, z + 1,   u1, v1);
			tess.addVertexWithUV(x + 0, y + 0, z + 0.5, u1, v0);
			tess.addVertexWithUV(x + 0, y + 0, z + 1,   u0, v0);
			tess.addVertexWithUV(x + 0, y + 1, z + 1,   u0, v1);
			tess.draw();
			
			//BOTTOM
			tess.startDrawingQuads();
			tess.setColorOpaque_F(0.6F, 0.6F, 0.6F);
			tess.addVertexWithUV(x + 0, y + 0, z + 1,   u1, v1);
			tess.addVertexWithUV(x + 0, y + 0, z + 0.5, u1, v0);
			tess.addVertexWithUV(x + 1, y + 0, z + 0.5, u0, v0);
			tess.addVertexWithUV(x + 1, y + 0, z + 1,   u0, v1);
			tess.draw();
			
			//SOUTH
			tess.startDrawingQuads();
			tess.setColorOpaque_F(0.6F, 0.6F, 0.6F);
			tess.addVertexWithUV(x + 0, y + 1, z + 1, u1, v1);
			tess.addVertexWithUV(x + 0, y + 0, z + 1, u1, v0);
			tess.addVertexWithUV(x + 1, y + 0, z + 1, u0, v0);
			tess.addVertexWithUV(x + 1, y + 1, z + 1, u0, v1);
			tess.draw();
			
			//NORTH
			tess.startDrawingQuads();
			tess.setColorOpaque_F(0.6F, 0.6F, 0.6F);
			tess.addVertexWithUV(x + 1, y + 1, z + 1,   u1, v1);
			tess.addVertexWithUV(x + 1, y + 0, z + 0.5, u1, v0);
			tess.addVertexWithUV(x + 0, y + 0, z + 0.5, u0, v0);
			tess.addVertexWithUV(x + 0, y + 1, z + 1,   u0, v1);
			tess.draw();
		}
		if (meta == 3)  { //Player facing NORTH, Wide base narrow top, low-style
			//EAST
			tess.startDrawingQuads();
			tess.setColorOpaque_F(0.8F, 0.8F, 0.8F);
			tess.addVertexWithUV(x + 1, y + 1, z + 0,   u1, v1);
			tess.addVertexWithUV(x + 1, y + 0, z + 0.5, u1, v0);
			tess.addVertexWithUV(x + 1, y + 0, z + 0,   u0, v0);
			tess.addVertexWithUV(x + 1, y + 1, z + 0,   u0, v1);
			tess.draw();
			
			//WEST
			tess.startDrawingQuads();
			tess.setColorOpaque_F(0.8F, 0.8F, 0.8F);
			tess.addVertexWithUV(x + 0, y + 1, z + 0,   u1, v1);
			tess.addVertexWithUV(x + 0, y + 0, z + 0,   u1, v0);
			tess.addVertexWithUV(x + 0, y + 0, z + 0.5, u0, v0);
			tess.addVertexWithUV(x + 0, y + 1, z + 0,   u0, v1);
			tess.draw();
			
			//BOTTOM
			tess.startDrawingQuads();
			tess.setColorOpaque_F(0.6F, 0.6F, 0.6F);
			tess.addVertexWithUV(x + 0, y + 0, z + 0.5, u1, v1);
			tess.addVertexWithUV(x + 0, y + 0, z + 0,   u1, v0);
			tess.addVertexWithUV(x + 1, y + 0, z + 0,   u0, v0);
			tess.addVertexWithUV(x + 1, y + 0, z + 0.5, u0, v1);
			tess.draw();
			
			//SOUTH
			tess.startDrawingQuads();
			tess.setColorOpaque_F(0.6F, 0.6F, 0.6F);
			tess.addVertexWithUV(x + 0, y + 1, z + 0,   u1, v1);
			tess.addVertexWithUV(x + 0, y + 0, z + 0.5, u1, v0);
			tess.addVertexWithUV(x + 1, y + 0, z + 0.5, u0, v0);
			tess.addVertexWithUV(x + 1, y + 1, z + 0,   u0, v1);
			tess.draw();
			
			//NORTH
			tess.startDrawingQuads();
			tess.setColorOpaque_F(0.6F, 0.6F, 0.6F);
			tess.addVertexWithUV(x + 1, y + 1, z + 0, u1, v1);
			tess.addVertexWithUV(x + 1, y + 0, z + 0, u1, v0);
			tess.addVertexWithUV(x + 0, y + 0, z + 0, u0, v0);
			tess.addVertexWithUV(x + 0, y + 1, z + 0, u0, v1);
			tess.draw();
		}
		if (meta == 4)  { //Player facing EAST, Wide top narrow base, low-style
			//EAST
			tess.startDrawingQuads();
			tess.setColorOpaque_F(0.8F, 0.8F, 0.8F);
			tess.addVertexWithUV(x + 1, y + 1, z + 1, u1, v1);
			tess.addVertexWithUV(x + 1, y + 0, z + 1, u1, v0);
			tess.addVertexWithUV(x + 1, y + 0, z + 0, u0, v0);
			tess.addVertexWithUV(x + 1, y + 1, z + 0, u0, v1);
			tess.draw();
			
			//WEST
			tess.startDrawingQuads();
			tess.setColorOpaque_F(0.8F, 0.8F, 0.8F);
			tess.addVertexWithUV(x + 0.5, y + 1, z + 0, u1, v1);
			tess.addVertexWithUV(x + 1,   y + 0, z + 0, u1, v0);
			tess.addVertexWithUV(x + 1,   y + 0, z + 1, u0, v0);
			tess.addVertexWithUV(x + 0.5, y + 1, z + 1, u0, v1);
			tess.draw();
			
			//Top
			tess.startDrawingQuads();
			tess.setColorOpaque_F(1.0F, 1.0F, 1.0F);
			tess.addVertexWithUV(x + 0.5, y + 1, z + 0, u1, v1);
			tess.addVertexWithUV(x + 0.5, y + 1, z + 1, u1, v0);
			tess.addVertexWithUV(x + 1,   y + 1, z + 1, u0, v0);
			tess.addVertexWithUV(x + 1,   y + 1, z + 0, u0, v1);
			tess.draw();
			
			//SOUTH
			tess.startDrawingQuads();
			tess.setColorOpaque_F(0.6F, 0.6F, 0.6F);
			tess.addVertexWithUV(x + 0.5, y + 1, z + 1, u1, v1);
			tess.addVertexWithUV(x + 1,   y + 0, z + 1, u1, v0);
			tess.addVertexWithUV(x + 1,   y + 0, z + 1, u0, v0);
			tess.addVertexWithUV(x + 1,   y + 1, z + 1, u0, v1);
			tess.draw();
			
			//NORTH
			tess.startDrawingQuads();
			tess.setColorOpaque_F(0.6F, 0.6F, 0.6F);
			tess.addVertexWithUV(x + 1,   y + 1, z + 0, u1, v1);
			tess.addVertexWithUV(x + 1,   y + 0, z + 0, u1, v0);
			tess.addVertexWithUV(x + 1,   y + 0, z + 0, u0, v0);
			tess.addVertexWithUV(x + 0.5, y + 1, z + 0, u0, v1);
			tess.draw();
		}
		if (meta == 5)  { //Player facing WEST, Wide top narrow base, low-style
			//EAST
			tess.startDrawingQuads();
			tess.setColorOpaque_F(0.8F, 0.8F, 0.8F);
			tess.addVertexWithUV(x + 0.5, y + 1, z + 1, u1, v1);
			tess.addVertexWithUV(x + 0,   y + 0, z + 1, u1, v0);
			tess.addVertexWithUV(x + 0,   y + 0, z + 0, u0, v0);
			tess.addVertexWithUV(x + 0.5, y + 1, z + 0, u0, v1);
			tess.draw();
			
			//WEST
			tess.startDrawingQuads();
			tess.setColorOpaque_F(0.8F, 0.8F, 0.8F);
			tess.addVertexWithUV(x + 0, y + 1, z + 0, u1, v1);
			tess.addVertexWithUV(x + 0, y + 0, z + 0, u1, v0);
			tess.addVertexWithUV(x + 0, y + 0, z + 1, u0, v0);
			tess.addVertexWithUV(x + 0, y + 1, z + 1, u0, v1);
			tess.draw();
			
			//Top
			tess.startDrawingQuads();
			tess.setColorOpaque_F(1.0F, 1.0F, 1.0F);
			tess.addVertexWithUV(x + 0,   y + 1, z + 0, u1, v1);
			tess.addVertexWithUV(x + 0,   y + 1, z + 1, u1, v0);
			tess.addVertexWithUV(x + 0.5, y + 1, z + 1, u0, v0);
			tess.addVertexWithUV(x + 0.5, y + 1, z + 0, u0, v1);
			tess.draw();

			//SOUTH
			tess.startDrawingQuads();
			tess.setColorOpaque_F(0.6F, 0.6F, 0.6F);
			tess.addVertexWithUV(x + 0,   y + 1, z + 1, u1, v1);
			tess.addVertexWithUV(x + 0,   y + 0, z + 1, u1, v0);
			tess.addVertexWithUV(x + 0,   y + 0, z + 1, u0, v0);
			tess.addVertexWithUV(x + 0.5, y + 1, z + 1, u0, v1);
			tess.draw();
			
			//NORTH
			tess.startDrawingQuads();
			tess.setColorOpaque_F(0.6F, 0.6F, 0.6F);
			tess.addVertexWithUV(x + 0.5, y + 1, z + 0, u1, v1);
			tess.addVertexWithUV(x + 0,   y + 0, z + 0, u1, v0);
			tess.addVertexWithUV(x + 0,   y + 0, z + 0, u0, v0);
			tess.addVertexWithUV(x + 0,   y + 1, z + 0, u0, v1);
			tess.draw();
		}
		if (meta == 6)  { //Player facing SOUTH, Wide top narrow base, low-style
			//EAST
			tess.startDrawingQuads();
			tess.setColorOpaque_F(0.8F, 0.8F, 0.8F);
			tess.addVertexWithUV(x + 1, y + 1, z + 1,   u1, v1);
			tess.addVertexWithUV(x + 1, y + 0, z + 1,   u1, v0);
			tess.addVertexWithUV(x + 1, y + 0, z + 1,   u0, v0);
			tess.addVertexWithUV(x + 1, y + 1, z + 0.5, u0, v1);
			tess.draw();
			
			//WEST
			tess.startDrawingQuads();
			tess.setColorOpaque_F(0.8F, 0.8F, 0.8F);
			tess.addVertexWithUV(x + 0, y + 1, z + 0.5, u1, v1);
			tess.addVertexWithUV(x + 0, y + 0, z + 1,   u1, v0);
			tess.addVertexWithUV(x + 0, y + 0, z + 1,   u0, v0);
			tess.addVertexWithUV(x + 0, y + 1, z + 1,   u0, v1);
			tess.draw();
			
			//Top
			tess.startDrawingQuads();
			tess.setColorOpaque_F(1.0F, 1.0F, 1.0F);
			tess.addVertexWithUV(x + 0, y + 1, z + 0.5, u1, v1);
			tess.addVertexWithUV(x + 0, y + 1, z + 1,   u1, v0);
			tess.addVertexWithUV(x + 1, y + 1, z + 1,   u0, v0);
			tess.addVertexWithUV(x + 1, y + 1, z + 0.5, u0, v1);
			tess.draw();
			
			//SOUTH
			tess.startDrawingQuads();
			tess.setColorOpaque_F(0.6F, 0.6F, 0.6F);
			tess.addVertexWithUV(x + 0, y + 1, z + 1, u1, v1);
			tess.addVertexWithUV(x + 0, y + 0, z + 1, u1, v0);
			tess.addVertexWithUV(x + 1, y + 0, z + 1, u0, v0);
			tess.addVertexWithUV(x + 1, y + 1, z + 1, u0, v1);
			tess.draw();
			
			//NORTH
			tess.startDrawingQuads();
			tess.setColorOpaque_F(0.6F, 0.6F, 0.6F);
			tess.addVertexWithUV(x + 1, y + 1, z + 0.5, u1, v1);
			tess.addVertexWithUV(x + 1, y + 0, z + 1,   u1, v0);
			tess.addVertexWithUV(x + 0, y + 0, z + 1,   u0, v0);
			tess.addVertexWithUV(x + 0, y + 1, z + 0.5, u0, v1);
			tess.draw();
		}
		if (meta == 7)  { //Player facing NORTH, Wide top narrow base, low-style
			//EAST
			tess.startDrawingQuads();
			tess.setColorOpaque_F(0.8F, 0.8F, 0.8F);
			tess.addVertexWithUV(x + 1, y + 1, z + 0.5, u1, v1);
			tess.addVertexWithUV(x + 1, y + 0, z + 0,   u1, v0);
			tess.addVertexWithUV(x + 1, y + 0, z + 0,   u0, v0);
			tess.addVertexWithUV(x + 1, y + 1, z + 0,   u0, v1);
			tess.draw();
			
			//WEST
			tess.startDrawingQuads();
			tess.setColorOpaque_F(0.8F, 0.8F, 0.8F);
			tess.addVertexWithUV(x + 0, y + 1, z + 0,   u1, v1);
			tess.addVertexWithUV(x + 0, y + 0, z + 0,   u1, v0);
			tess.addVertexWithUV(x + 0, y + 0, z + 0,   u0, v0);
			tess.addVertexWithUV(x + 0, y + 1, z + 0.5, u0, v1);
			tess.draw();
			
			//Top
			tess.startDrawingQuads();
			tess.setColorOpaque_F(1.0F, 1.0F, 1.0F);
			tess.addVertexWithUV(x + 0, y + 1, z + 0,   u1, v1);
			tess.addVertexWithUV(x + 0, y + 1, z + 0.5, u1, v0);
			tess.addVertexWithUV(x + 1, y + 1, z + 0.5, u0, v0);
			tess.addVertexWithUV(x + 1, y + 1, z + 0,   u0, v1);
			tess.draw();
			
			//SOUTH
			tess.startDrawingQuads();
			tess.setColorOpaque_F(0.6F, 0.6F, 0.6F);
			tess.addVertexWithUV(x + 0, y + 1, z + 0.5, u1, v1);
			tess.addVertexWithUV(x + 0, y + 0, z + 0,   u1, v0);
			tess.addVertexWithUV(x + 1, y + 0, z + 0,   u0, v0);
			tess.addVertexWithUV(x + 1, y + 1, z + 0.5, u0, v1);
			tess.draw();
			
			//NORTH
			tess.startDrawingQuads();
			tess.setColorOpaque_F(0.6F, 0.6F, 0.6F);
			tess.addVertexWithUV(x + 1, y + 1, z + 0, u1, v1);
			tess.addVertexWithUV(x + 1, y + 0, z + 0, u1, v0);
			tess.addVertexWithUV(x + 0, y + 0, z + 0, u0, v0);
			tess.addVertexWithUV(x + 0, y + 1, z + 0, u0, v1);
			tess.draw();
		}
		if (meta == 8)  { //Player facing EAST, Wide base narrow top, high-style
			//EAST
			tess.startDrawingQuads();
			tess.setColorOpaque_F(0.8F, 0.8F, 0.8F);
			tess.addVertexWithUV(x + 1, y + 1, z + 1, u1, v1);
			tess.addVertexWithUV(x + 1, y + 0, z + 1, u1, v0);
			tess.addVertexWithUV(x + 1, y + 0, z + 0, u0, v0);
			tess.addVertexWithUV(x + 1, y + 1, z + 0, u0, v1);
			tess.draw();
			
			//WEST
			tess.startDrawingQuads();
			tess.setColorOpaque_F(0.8F, 0.8F, 0.8F);
			tess.addVertexWithUV(x + 0.5, y + 1, z + 0, u1, v1);
			tess.addVertexWithUV(x + 0,   y + 0, z + 0, u1, v0);
			tess.addVertexWithUV(x + 0,   y + 0, z + 1, u0, v0);
			tess.addVertexWithUV(x + 0.5, y + 1, z + 1, u0, v1);
			tess.draw();
			
			//Top
			tess.startDrawingQuads();
			tess.setColorOpaque_F(1.0F, 1.0F, 1.0F);
			tess.addVertexWithUV(x + 0.5, y + 1, z + 0, u1, v1);
			tess.addVertexWithUV(x + 0.5, y + 1, z + 1, u1, v0);
			tess.addVertexWithUV(x + 1,   y + 1, z + 1, u0, v0);
			tess.addVertexWithUV(x + 1,   y + 1, z + 0, u0, v1);
			tess.draw();
			
			//BOTTOM
			tess.startDrawingQuads();
			tess.setColorOpaque_F(0.6F, 0.6F, 0.6F);
			tess.addVertexWithUV(x + 0, y + 0, z + 1, u1, v1);
			tess.addVertexWithUV(x + 0, y + 0, z + 0, u1, v0);
			tess.addVertexWithUV(x + 1, y + 0, z + 0, u0, v0);
			tess.addVertexWithUV(x + 1, y + 0, z + 1, u0, v1);
			tess.draw();
			
			//SOUTH
			tess.startDrawingQuads();
			tess.setColorOpaque_F(0.6F, 0.6F, 0.6F);
			tess.addVertexWithUV(x + 0.5, y + 1, z + 1, u1, v1);
			tess.addVertexWithUV(x + 0,   y + 0, z + 1, u1, v0);
			tess.addVertexWithUV(x + 1,   y + 0, z + 1, u0, v0);
			tess.addVertexWithUV(x + 1,   y + 1, z + 1, u0, v1);
			tess.draw();
			
			//NORTH
			tess.startDrawingQuads();
			tess.setColorOpaque_F(0.6F, 0.6F, 0.6F);
			tess.addVertexWithUV(x + 1,   y + 1, z + 0, u1, v1);
			tess.addVertexWithUV(x + 1,   y + 0, z + 0, u1, v0);
			tess.addVertexWithUV(x + 0,   y + 0, z + 0, u0, v0);
			tess.addVertexWithUV(x + 0.5, y + 1, z + 0, u0, v1);
			tess.draw();
		}
		if (meta == 9)  { //Player facing WEST, Wide base narrow top, high-style
			//EAST
			tess.startDrawingQuads();
			tess.setColorOpaque_F(0.8F, 0.8F, 0.8F);
			tess.addVertexWithUV(x + 0.5, y + 1, z + 1, u1, v1);
			tess.addVertexWithUV(x + 1,   y + 0, z + 1, u1, v0);
			tess.addVertexWithUV(x + 1,   y + 0, z + 0, u0, v0);
			tess.addVertexWithUV(x + 0.5, y + 1, z + 0, u0, v1);
			tess.draw();
			
			//WEST
			tess.startDrawingQuads();
			tess.setColorOpaque_F(0.8F, 0.8F, 0.8F);
			tess.addVertexWithUV(x + 0, y + 1, z + 0, u1, v1);
			tess.addVertexWithUV(x + 0, y + 0, z + 0, u1, v0);
			tess.addVertexWithUV(x + 0, y + 0, z + 1, u0, v0);
			tess.addVertexWithUV(x + 0, y + 1, z + 1, u0, v1);
			tess.draw();
			
			//Top
			tess.startDrawingQuads();
			tess.setColorOpaque_F(1.0F, 1.0F, 1.0F);
			tess.addVertexWithUV(x + 0,   y + 1, z + 0, u1, v1);
			tess.addVertexWithUV(x + 0,   y + 1, z + 1, u1, v0);
			tess.addVertexWithUV(x + 0.5, y + 1, z + 1, u0, v0);
			tess.addVertexWithUV(x + 0.5, y + 1, z + 0, u0, v1);
			tess.draw();
			
			//BOTTOM
			tess.startDrawingQuads();
			tess.setColorOpaque_F(0.6F, 0.6F, 0.6F);
			tess.addVertexWithUV(x + 0, y + 0, z + 1, u1, v1);
			tess.addVertexWithUV(x + 0, y + 0, z + 0, u1, v0);
			tess.addVertexWithUV(x + 1, y + 0, z + 0, u0, v0);
			tess.addVertexWithUV(x + 1, y + 0, z + 1, u0, v1);
			tess.draw();
			
			//SOUTH
			tess.startDrawingQuads();
			tess.setColorOpaque_F(0.6F, 0.6F, 0.6F);
			tess.addVertexWithUV(x + 0, y + 1, z + 1, u1, v1);
			tess.addVertexWithUV(x + 0, y + 0, z + 1, u1, v0);
			tess.addVertexWithUV(x + 1, y + 0, z + 1, u0, v0);
			tess.addVertexWithUV(x + 0.5, y + 1, z + 1, u0, v1);
			tess.draw();
			
			//NORTH
			tess.startDrawingQuads();
			tess.setColorOpaque_F(0.6F, 0.6F, 0.6F);
			tess.addVertexWithUV(x + 0.5, y + 1, z + 0, u1, v1);
			tess.addVertexWithUV(x + 1, y + 0, z + 0, u1, v0);
			tess.addVertexWithUV(x + 0, y + 0, z + 0, u0, v0);
			tess.addVertexWithUV(x + 0, y + 1, z + 0, u0, v1);
			tess.draw();
		}
		if (meta == 10)  { //Player facing SOUTH, Wide base narrow top, high-style
			//EAST
			tess.startDrawingQuads();
			tess.setColorOpaque_F(0.8F, 0.8F, 0.8F);
			tess.addVertexWithUV(x + 1, y + 1, z + 1,   u1, v1);
			tess.addVertexWithUV(x + 1, y + 0, z + 1,   u1, v0);
			tess.addVertexWithUV(x + 1, y + 0, z + 0,   u0, v0);
			tess.addVertexWithUV(x + 1, y + 1, z + 0.5, u0, v1);
			tess.draw();
			
			//WEST
			tess.startDrawingQuads();
			tess.setColorOpaque_F(0.8F, 0.8F, 0.8F);
			tess.addVertexWithUV(x + 0, y + 1, z + 0.5, u1, v1);
			tess.addVertexWithUV(x + 0, y + 0, z + 0,   u1, v0);
			tess.addVertexWithUV(x + 0, y + 0, z + 1,   u0, v0);
			tess.addVertexWithUV(x + 0, y + 1, z + 1,   u0, v1);
			tess.draw();
			
			//Top
			tess.startDrawingQuads();
			tess.setColorOpaque_F(1.0F, 1.0F, 1.0F);
			tess.addVertexWithUV(x + 0, y + 1, z + 0.5, u1, v1);
			tess.addVertexWithUV(x + 0, y + 1, z + 1,   u1, v0);
			tess.addVertexWithUV(x + 1, y + 1, z + 1,   u0, v0);
			tess.addVertexWithUV(x + 1, y + 1, z + 0.5, u0, v1);
			tess.draw();
			
			//BOTTOM
			tess.startDrawingQuads();
			tess.setColorOpaque_F(0.6F, 0.6F, 0.6F);
			tess.addVertexWithUV(x + 0, y + 0, z + 1, u1, v1);
			tess.addVertexWithUV(x + 0, y + 0, z + 0, u1, v0);
			tess.addVertexWithUV(x + 1, y + 0, z + 0, u0, v0);
			tess.addVertexWithUV(x + 1, y + 0, z + 1, u0, v1);
			tess.draw();
			
			//SOUTH
			tess.startDrawingQuads();
			tess.setColorOpaque_F(0.6F, 0.6F, 0.6F);
			tess.addVertexWithUV(x + 0, y + 1, z + 1, u1, v1);
			tess.addVertexWithUV(x + 0, y + 0, z + 1, u1, v0);
			tess.addVertexWithUV(x + 1, y + 0, z + 1, u0, v0);
			tess.addVertexWithUV(x + 1, y + 1, z + 1, u0, v1);
			tess.draw();
			
			//NORTH
			tess.startDrawingQuads();
			tess.setColorOpaque_F(0.6F, 0.6F, 0.6F);
			tess.addVertexWithUV(x + 1, y + 1, z + 0.5, u1, v1);
			tess.addVertexWithUV(x + 1, y + 0, z + 0,   u1, v0);
			tess.addVertexWithUV(x + 0, y + 0, z + 0,   u0, v0);
			tess.addVertexWithUV(x + 0, y + 1, z + 0.5, u0, v1);
			tess.draw();
		}
		if (meta == 11)  { //Player facing NORTH, Wide base narrow top, high-style
			//EAST
			tess.startDrawingQuads();
			tess.setColorOpaque_F(0.8F, 0.8F, 0.8F);
			tess.addVertexWithUV(x + 1, y + 1, z + 0.5, u1, v1);
			tess.addVertexWithUV(x + 1, y + 0, z + 1,   u1, v0);
			tess.addVertexWithUV(x + 1, y + 0, z + 0,   u0, v0);
			tess.addVertexWithUV(x + 1, y + 1, z + 0,   u0, v1);
			tess.draw();
			
			//WEST
			tess.startDrawingQuads();
			tess.setColorOpaque_F(0.8F, 0.8F, 0.8F);
			tess.addVertexWithUV(x + 0, y + 1, z + 0,   u1, v1);
			tess.addVertexWithUV(x + 0, y + 0, z + 0,   u1, v0);
			tess.addVertexWithUV(x + 0, y + 0, z + 1,   u0, v0);
			tess.addVertexWithUV(x + 0, y + 1, z + 0.5, u0, v1);
			tess.draw();
			
			//Top
			tess.startDrawingQuads();
			tess.setColorOpaque_F(1.0F, 1.0F, 1.0F);
			tess.addVertexWithUV(x + 0, y + 1, z + 0,   u1, v1);
			tess.addVertexWithUV(x + 0, y + 1, z + 0.5, u1, v0);
			tess.addVertexWithUV(x + 1, y + 1, z + 0.5, u0, v0);
			tess.addVertexWithUV(x + 1, y + 1, z + 0,   u0, v1);
			tess.draw();
			
			//BOTTOM
			tess.startDrawingQuads();
			tess.setColorOpaque_F(0.6F, 0.6F, 0.6F);
			tess.addVertexWithUV(x + 0, y + 0, z + 1, u1, v1);
			tess.addVertexWithUV(x + 0, y + 0, z + 0, u1, v0);
			tess.addVertexWithUV(x + 1, y + 0, z + 0, u0, v0);
			tess.addVertexWithUV(x + 1, y + 0, z + 1, u0, v1);
			tess.draw();
			
			//SOUTH
			tess.startDrawingQuads();
			tess.setColorOpaque_F(0.6F, 0.6F, 0.6F);
			tess.addVertexWithUV(x + 0, y + 1, z + 0.5, u1, v1);
			tess.addVertexWithUV(x + 0, y + 0, z + 1,   u1, v0);
			tess.addVertexWithUV(x + 1, y + 0, z + 1,   u0, v0);
			tess.addVertexWithUV(x + 1, y + 1, z + 0.5, u0, v1);
			tess.draw();
			
			//NORTH
			tess.startDrawingQuads();
			tess.setColorOpaque_F(0.6F, 0.6F, 0.6F);
			tess.addVertexWithUV(x + 1, y + 1, z + 0, u1, v1);
			tess.addVertexWithUV(x + 1, y + 0, z + 0, u1, v0);
			tess.addVertexWithUV(x + 0, y + 0, z + 0, u0, v0);
			tess.addVertexWithUV(x + 0, y + 1, z + 0, u0, v1);
			tess.draw();
		}
		if (meta == 12)  { //Player facing EAST, Wide top narrow base, high-style
			//EAST
			tess.startDrawingQuads();
			tess.setColorOpaque_F(0.8F, 0.8F, 0.8F);
			tess.addVertexWithUV(x + 1, y + 1, z + 1, u1, v1);
			tess.addVertexWithUV(x + 1, y + 0, z + 1, u1, v0);
			tess.addVertexWithUV(x + 1, y + 0, z + 0, u0, v0);
			tess.addVertexWithUV(x + 1, y + 1, z + 0, u0, v1);
			tess.draw();
			
			//WEST
			tess.startDrawingQuads();
			tess.setColorOpaque_F(0.8F, 0.8F, 0.8F);
			tess.addVertexWithUV(x + 0,   y + 1, z + 0, u1, v1);
			tess.addVertexWithUV(x + 0.5, y + 0, z + 0, u1, v0);
			tess.addVertexWithUV(x + 0.5, y + 0, z + 1, u0, v0);
			tess.addVertexWithUV(x + 0,   y + 1, z + 1, u0, v1);
			tess.draw();
			
			//Top
			tess.startDrawingQuads();
			tess.setColorOpaque_F(1.0F, 1.0F, 1.0F);
			tess.addVertexWithUV(x + 0, y + 1, z + 0, u1, v1);
			tess.addVertexWithUV(x + 0, y + 1, z + 1, u1, v0);
			tess.addVertexWithUV(x + 1, y + 1, z + 1, u0, v0);
			tess.addVertexWithUV(x + 1, y + 1, z + 0, u0, v1);
			tess.draw();
			
			//BOTTOM
			tess.startDrawingQuads();
			tess.setColorOpaque_F(0.6F, 0.6F, 0.6F);
			tess.addVertexWithUV(x + 0.5, y + 0, z + 1, u1, v1);
			tess.addVertexWithUV(x + 0.5, y + 0, z + 0, u1, v0);
			tess.addVertexWithUV(x + 1,   y + 0, z + 0, u0, v0);
			tess.addVertexWithUV(x + 1,   y + 0, z + 1, u0, v1);
			tess.draw();
			
			//SOUTH
			tess.startDrawingQuads();
			tess.setColorOpaque_F(0.6F, 0.6F, 0.6F);
			tess.addVertexWithUV(x + 0,   y + 1, z + 1, u1, v1);
			tess.addVertexWithUV(x + 0.5, y + 0, z + 1, u1, v0);
			tess.addVertexWithUV(x + 1,   y + 0, z + 1, u0, v0);
			tess.addVertexWithUV(x + 1,   y + 1, z + 1, u0, v1);
			tess.draw();
			
			//NORTH
			tess.startDrawingQuads();
			tess.setColorOpaque_F(0.6F, 0.6F, 0.6F);
			tess.addVertexWithUV(x + 1,   y + 1, z + 0, u1, v1);
			tess.addVertexWithUV(x + 1,   y + 0, z + 0, u1, v0);
			tess.addVertexWithUV(x + 0.5, y + 0, z + 0, u0, v0);
			tess.addVertexWithUV(x + 0,   y + 1, z + 0, u0, v1);
			tess.draw();
		}
		if (meta == 13)  { //Player facing WEST, Wide top narrow base, high-style
			//EAST
			tess.startDrawingQuads();
			tess.setColorOpaque_F(0.8F, 0.8F, 0.8F);
			tess.addVertexWithUV(x + 1,   y + 1, z + 1, u1, v1);
			tess.addVertexWithUV(x + 0.5, y + 0, z + 1, u1, v0);
			tess.addVertexWithUV(x + 0.5, y + 0, z + 0, u0, v0);
			tess.addVertexWithUV(x + 1,   y + 1, z + 0, u0, v1);
			tess.draw();
			
			//WEST
			tess.startDrawingQuads();
			tess.setColorOpaque_F(0.8F, 0.8F, 0.8F);
			tess.addVertexWithUV(x + 0, y + 1, z + 0, u1, v1);
			tess.addVertexWithUV(x + 0, y + 0, z + 0, u1, v0);
			tess.addVertexWithUV(x + 0, y + 0, z + 1, u0, v0);
			tess.addVertexWithUV(x + 0, y + 1, z + 1, u0, v1);
			tess.draw();
			
			//Top
			tess.startDrawingQuads();
			tess.setColorOpaque_F(1.0F, 1.0F, 1.0F);
			tess.addVertexWithUV(x + 0, y + 1, z + 0, u1, v1);
			tess.addVertexWithUV(x + 0, y + 1, z + 1, u1, v0);
			tess.addVertexWithUV(x + 1, y + 1, z + 1, u0, v0);
			tess.addVertexWithUV(x + 1, y + 1, z + 0, u0, v1);
			tess.draw();
			
			//BOTTOM
			tess.startDrawingQuads();
			tess.setColorOpaque_F(0.6F, 0.6F, 0.6F);
			tess.addVertexWithUV(x + 0, y + 0, z + 1, u1, v1);
			tess.addVertexWithUV(x + 0, y + 0, z + 0, u1, v0);
			tess.addVertexWithUV(x + 0.5,   y + 0, z + 0, u0, v0);
			tess.addVertexWithUV(x + 0.5,   y + 0, z + 1, u0, v1);
			tess.draw();
			
			//SOUTH
			tess.startDrawingQuads();
			tess.setColorOpaque_F(0.6F, 0.6F, 0.6F);
			tess.addVertexWithUV(x + 0,   y + 1, z + 1, u1, v1);
			tess.addVertexWithUV(x + 0,   y + 0, z + 1, u1, v0);
			tess.addVertexWithUV(x + 0.5, y + 0, z + 1, u0, v0);
			tess.addVertexWithUV(x + 1,   y + 1, z + 1, u0, v1);
			tess.draw();
			
			//NORTH
			tess.startDrawingQuads();
			tess.setColorOpaque_F(0.6F, 0.6F, 0.6F);
			tess.addVertexWithUV(x + 1,   y + 1, z + 0, u1, v1);
			tess.addVertexWithUV(x + 0.5, y + 0, z + 0, u1, v0);
			tess.addVertexWithUV(x + 0,   y + 0, z + 0, u0, v0);
			tess.addVertexWithUV(x + 0,   y + 1, z + 0, u0, v1);
			tess.draw();
		}
		if (meta == 14)  { //Player facing SOUTH, Wide top narrow base, high-style
			//EAST
			tess.startDrawingQuads();
			tess.setColorOpaque_F(0.8F, 0.8F, 0.8F);
			tess.addVertexWithUV(x + 1, y + 1, z + 1,   u1, v1);
			tess.addVertexWithUV(x + 1, y + 0, z + 1,   u1, v0);
			tess.addVertexWithUV(x + 1, y + 0, z + 0.5, u0, v0);
			tess.addVertexWithUV(x + 1, y + 1, z + 0,   u0, v1);
			tess.draw();
			
			//WEST
			tess.startDrawingQuads();
			tess.setColorOpaque_F(0.8F, 0.8F, 0.8F);
			tess.addVertexWithUV(x + 0, y + 1, z + 0,   u1, v1);
			tess.addVertexWithUV(x + 0, y + 0, z + 0.5, u1, v0);
			tess.addVertexWithUV(x + 0, y + 0, z + 1,   u0, v0);
			tess.addVertexWithUV(x + 0, y + 1, z + 1,   u0, v1);
			tess.draw();
			
			//Top
			tess.startDrawingQuads();
			tess.setColorOpaque_F(1.0F, 1.0F, 1.0F);
			tess.addVertexWithUV(x + 0, y + 1, z + 0, u1, v1);
			tess.addVertexWithUV(x + 0, y + 1, z + 1, u1, v0);
			tess.addVertexWithUV(x + 1, y + 1, z + 1, u0, v0);
			tess.addVertexWithUV(x + 1, y + 1, z + 0, u0, v1);
			tess.draw();
			
			//BOTTOM
			tess.startDrawingQuads();
			tess.setColorOpaque_F(0.6F, 0.6F, 0.6F);
			tess.addVertexWithUV(x + 0, y + 0, z + 1,   u1, v1);
			tess.addVertexWithUV(x + 0, y + 0, z + 0.5, u1, v0);
			tess.addVertexWithUV(x + 1, y + 0, z + 0.5, u0, v0);
			tess.addVertexWithUV(x + 1, y + 0, z + 1,   u0, v1);
			tess.draw();
			
			//SOUTH
			tess.startDrawingQuads();
			tess.setColorOpaque_F(0.6F, 0.6F, 0.6F);
			tess.addVertexWithUV(x + 0, y + 1, z + 1, u1, v1);
			tess.addVertexWithUV(x + 0, y + 0, z + 1, u1, v0);
			tess.addVertexWithUV(x + 1, y + 0, z + 1, u0, v0);
			tess.addVertexWithUV(x + 1, y + 1, z + 1, u0, v1);
			tess.draw();
			
			//NORTH
			tess.startDrawingQuads();
			tess.setColorOpaque_F(0.6F, 0.6F, 0.6F);
			tess.addVertexWithUV(x + 1, y + 1, z + 0,   u1, v1);
			tess.addVertexWithUV(x + 1, y + 0, z + 0.5, u1, v0);
			tess.addVertexWithUV(x + 0, y + 0, z + 0.5, u0, v0);
			tess.addVertexWithUV(x + 0, y + 1, z + 0,   u0, v1);
			tess.draw();
		}
		if (meta == 15)  { //Player facing NORTH, Wide top narrow base, high-style
			//EAST
			tess.startDrawingQuads();
			tess.setColorOpaque_F(0.8F, 0.8F, 0.8F);
			tess.addVertexWithUV(x + 1, y + 1, z + 1,   u1, v1);
			tess.addVertexWithUV(x + 1, y + 0, z + 0.5, u1, v0);
			tess.addVertexWithUV(x + 1, y + 0, z + 0,   u0, v0);
			tess.addVertexWithUV(x + 1, y + 1, z + 0,   u0, v1);
			tess.draw();
			
			//WEST
			tess.startDrawingQuads();
			tess.setColorOpaque_F(0.8F, 0.8F, 0.8F);
			tess.addVertexWithUV(x + 0, y + 1, z + 0,   u1, v1);
			tess.addVertexWithUV(x + 0, y + 0, z + 0,   u1, v0);
			tess.addVertexWithUV(x + 0, y + 0, z + 0.5, u0, v0);
			tess.addVertexWithUV(x + 0, y + 1, z + 1,   u0, v1);
			tess.draw();
			
			//Top
			tess.startDrawingQuads();
			tess.setColorOpaque_F(1.0F, 1.0F, 1.0F);
			tess.addVertexWithUV(x + 0, y + 1, z + 0, u1, v1);
			tess.addVertexWithUV(x + 0, y + 1, z + 1, u1, v0);
			tess.addVertexWithUV(x + 1, y + 1, z + 1, u0, v0);
			tess.addVertexWithUV(x + 1, y + 1, z + 0, u0, v1);
			tess.draw();
			
			//BOTTOM
			tess.startDrawingQuads();
			tess.setColorOpaque_F(0.6F, 0.6F, 0.6F);
			tess.addVertexWithUV(x + 0, y + 0, z + 0.5, u1, v1);
			tess.addVertexWithUV(x + 0, y + 0, z + 0,   u1, v0);
			tess.addVertexWithUV(x + 1, y + 0, z + 0,   u0, v0);
			tess.addVertexWithUV(x + 1, y + 0, z + 0.5, u0, v1);
			tess.draw();
			
			//SOUTH
			tess.startDrawingQuads();
			tess.setColorOpaque_F(0.6F, 0.6F, 0.6F);
			tess.addVertexWithUV(x + 0, y + 1, z + 1, u1, v1);
			tess.addVertexWithUV(x + 0, y + 0, z + 0.5, u1, v0);
			tess.addVertexWithUV(x + 1, y + 0, z + 0.5, u0, v0);
			tess.addVertexWithUV(x + 1, y + 1, z + 1, u0, v1);
			tess.draw();
			
			//NORTH
			tess.startDrawingQuads();
			tess.setColorOpaque_F(0.6F, 0.6F, 0.6F);
			tess.addVertexWithUV(x + 1, y + 1, z + 0, u1, v1);
			tess.addVertexWithUV(x + 1, y + 0, z + 0, u1, v0);
			tess.addVertexWithUV(x + 0, y + 0, z + 0, u0, v0);
			tess.addVertexWithUV(x + 0, y + 1, z + 0, u0, v1);
			tess.draw();
		}
		tess.startDrawingQuads();
		return true;
	}

	@Override
	public int getRenderId() {
		return ClientProxy.slope45RenderID;
	}
}