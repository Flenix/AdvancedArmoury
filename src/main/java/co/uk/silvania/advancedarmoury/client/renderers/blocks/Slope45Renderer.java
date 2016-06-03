//CLONED FROM FLENIXCITIES Walkway Renderer, then modified.
//Taken with permission, as it's my own damn mod. :D
package co.uk.silvania.advancedarmoury.client.renderers.blocks;

import org.lwjgl.opengl.GL11;

import co.uk.silvania.advancedarmoury.blocks.decorative.WalkwayFenceMilitaryBase;
import co.uk.silvania.advancedarmoury.blocks.decorative.WalkwayStairsMilitaryBase;
import co.uk.silvania.advancedarmoury.client.ClientProxy;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.init.Blocks;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.util.ForgeDirection;

public class Slope45Renderer extends BlockRenderCore implements ISimpleBlockRenderingHandler {
	
	boolean renderSnow;
	
	public Slope45Renderer(boolean snowy) {
		renderSnow = snowy;
	}

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
		tess.addVertexWithUV(x + 1, y + 0, z + 1, u1, v1);
		tess.addVertexWithUV(x + 1, y + 1, z,     u1, v0);
		tess.addVertexWithUV(x,     y + 1, z,     u0, v0);
		tess.addVertexWithUV(x,     y + 0, z + 1, u0, v1);
		tess.draw();
		
		//Back
		tess.startDrawingQuads();
		tess.setColorOpaque_F(0.8F, 0.8F, 0.8F);
		tess.addVertexWithUV(x,     y + 0, z, u1, v1);
		tess.addVertexWithUV(x,     y + 1, z, u1, v0);
		tess.addVertexWithUV(x + 1, y + 1, z, u0, v0);
		tess.addVertexWithUV(x + 1, y + 0, z, u0, v1);
		tess.draw();
		
		//Left
		tess.startDrawingQuads();
		tess.setColorOpaque_F(0.6F, 0.6F, 0.6F);
		tess.addVertexWithUV(x, y + 0, z + 1, u1, v1);
		tess.addVertexWithUV(x, y + 0, z + 1, u1, v0);
		tess.addVertexWithUV(x, y + 1, z + 0, u0, v0);
		tess.addVertexWithUV(x, y + 0, z + 0, u0, v1);
		tess.draw();
		
		//Right
		tess.startDrawingQuads();
		tess.setColorOpaque_F(0.6F, 0.6F, 0.6F);
		tess.addVertexWithUV(x + 1, y + 0, z + 0, u1, v1);
		tess.addVertexWithUV(x + 1, y + 1, z + 0, u1, v0);
		tess.addVertexWithUV(x + 1, y + 0, z + 1, u0, v0);
		tess.addVertexWithUV(x + 1, y + 0, z + 1, u0, v1);
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
		
		if (meta == 0)  {
			tess.draw();
			//Front
			tess.startDrawingQuads();
			tess.setColorOpaque_F(0.8F, 0.8F, 0.8F);
			tess.addVertexWithUV(x + 1, y + 0, z + 1, u1, v1);
			tess.addVertexWithUV(x + 1, y + 1, z + 1, u1, v0);
			tess.addVertexWithUV(x,     y + 0, z + 1, u0, v0);
			tess.addVertexWithUV(x,     y + 0, z + 1, u0, v1);
			tess.draw();
			
			//Back
			tess.startDrawingQuads();
			tess.setColorOpaque_F(0.8F, 0.8F, 0.8F);
			tess.addVertexWithUV(x,     y + 0, z, u1, v1);
			tess.addVertexWithUV(x,     y + 0, z, u1, v0);
			tess.addVertexWithUV(x + 1, y + 1, z, u0, v0);
			tess.addVertexWithUV(x + 1, y + 0, z, u0, v1);
			tess.draw();
			
			//Left
			tess.startDrawingQuads();
			tess.setColorOpaque_F(0.6F, 0.6F, 0.6F);
			tess.addVertexWithUV(x,     y + 0, z + 1, u1, v1);
			tess.addVertexWithUV(x + 1, y + 1, z + 1, u1, v0);
			tess.addVertexWithUV(x + 1, y + 1, z + 0, u0, v0);
			tess.addVertexWithUV(x,     y + 0, z + 0, u0, v1);
			tess.draw();
			
			//Right
			tess.startDrawingQuads();
			tess.setColorOpaque_F(0.6F, 0.6F, 0.6F);
			tess.addVertexWithUV(x + 1, y + 0, z + 0, u1, v1);
			tess.addVertexWithUV(x + 1, y + 1, z + 0, u1, v0);
			tess.addVertexWithUV(x + 1, y + 1, z + 1, u0, v0);
			tess.addVertexWithUV(x + 1, y + 0, z + 1, u0, v1);
			tess.draw();
			
			//Bottom
			tess.startDrawingQuads();
			tess.setColorOpaque_F(0.5F, 0.5F, 0.5F);
			tess.addVertexWithUV(x + 1, y, z + 0, u1, v1);
			tess.addVertexWithUV(x + 1, y, z + 1, u1, v0);
			tess.addVertexWithUV(x,     y, z + 1, u0, v0);
			tess.addVertexWithUV(x,     y, z + 0, u0, v1);
			tess.draw();
			
			tess.startDrawingQuads();
		}
		if (meta == 1)  {
			tess.draw();			
			//Front
			tess.startDrawingQuads();
			tess.setColorOpaque_F(0.8F, 0.8F, 0.8F);
			tess.addVertexWithUV(x + 1, y + 0, z + 1, u1, v1);
			tess.addVertexWithUV(x,     y + 1, z + 1, u1, v0);
			tess.addVertexWithUV(x,     y + 1, z + 1, u0, v0);
			tess.addVertexWithUV(x,     y + 0, z + 1, u0, v1);
			tess.draw();
			
			//Back
			tess.startDrawingQuads();
			tess.setColorOpaque_F(0.8F, 0.8F, 0.8F);
			tess.addVertexWithUV(x,     y + 0, z, u1, v1);
			tess.addVertexWithUV(x,     y + 1, z, u1, v0);
			tess.addVertexWithUV(x,     y + 1, z, u0, v0);
			tess.addVertexWithUV(x + 1, y + 0, z, u0, v1);
			tess.draw();
			
			//Left
			tess.startDrawingQuads();
			tess.setColorOpaque_F(0.6F, 0.6F, 0.6F);
			tess.addVertexWithUV(x, y + 0, z + 1, u1, v1);
			tess.addVertexWithUV(x, y + 1, z + 1, u1, v0);
			tess.addVertexWithUV(x, y + 1, z + 0, u0, v0);
			tess.addVertexWithUV(x, y + 0, z + 0, u0, v1);
			tess.draw();
			
			//Right
			tess.startDrawingQuads();
			tess.setColorOpaque_F(0.6F, 0.6F, 0.6F);
			tess.addVertexWithUV(x + 1, y + 0, z + 0, u1, v1);
			tess.addVertexWithUV(x,     y + 1, z + 0, u1, v0);
			tess.addVertexWithUV(x,     y + 1, z + 1, u0, v0);
			tess.addVertexWithUV(x + 1, y + 0, z + 1, u0, v1);
			tess.draw();
			
			//Bottom
			tess.startDrawingQuads();
			tess.setColorOpaque_F(0.5F, 0.5F, 0.5F);
			tess.addVertexWithUV(x + 1, y, z + 0, u1, v1);
			tess.addVertexWithUV(x + 1, y, z + 1, u1, v0);
			tess.addVertexWithUV(x,     y, z + 1, u0, v0);
			tess.addVertexWithUV(x,     y, z + 0, u0, v1);
			tess.draw();
			
			tess.startDrawingQuads();
		}
		if (meta == 2)  {
			tess.draw();
			
			//Front
			tess.startDrawingQuads();
			tess.setColorOpaque_F(0.8F, 0.8F, 0.8F);
			tess.addVertexWithUV(x + 1, y + 1, z + 1, u1, v1);
			tess.addVertexWithUV(x + 1, y + 0, z,     u1, v0);
			tess.addVertexWithUV(x,     y + 0, z,     u0, v0);
			tess.addVertexWithUV(x,     y + 1, z + 1, u0, v1);
			tess.draw();
			
			//Back
			tess.startDrawingQuads();
			tess.setColorOpaque_F(0.8F, 0.8F, 0.8F);
			tess.addVertexWithUV(x + 1, y + 0, z + 1, u1, v1);
			tess.addVertexWithUV(x + 1, y + 1, z + 1, u1, v0);
			tess.addVertexWithUV(x,     y + 1, z + 1, u0, v0);
			tess.addVertexWithUV(x,     y + 0, z + 1, u0, v1);
			tess.draw();
			
			//Left
			tess.startDrawingQuads();
			tess.setColorOpaque_F(0.6F, 0.6F, 0.6F);
			tess.addVertexWithUV(x, y + 0, z + 1, u1, v1);
			tess.addVertexWithUV(x, y + 1, z + 1, u1, v0);
			tess.addVertexWithUV(x, y + 0, z + 0, u0, v0);
			tess.addVertexWithUV(x, y + 0, z + 0, u0, v1);
			tess.draw();
			
			//Right
			tess.startDrawingQuads();
			tess.setColorOpaque_F(0.6F, 0.6F, 0.6F);
			tess.addVertexWithUV(x + 1, y + 0, z + 0, u1, v1);
			tess.addVertexWithUV(x + 1, y + 0, z + 0, u1, v0);
			tess.addVertexWithUV(x + 1, y + 1, z + 1, u0, v0);
			tess.addVertexWithUV(x + 1, y + 0, z + 1, u0, v1);
			tess.draw();
			
			//Bottom
			tess.startDrawingQuads();
			tess.setColorOpaque_F(0.5F, 0.5F, 0.5F);
			tess.addVertexWithUV(x + 1, y, z + 0, u1, v1);
			tess.addVertexWithUV(x + 1, y, z + 1, u1, v0);
			tess.addVertexWithUV(x,     y, z + 1, u0, v0);
			tess.addVertexWithUV(x,     y, z + 0, u0, v1);
			tess.draw();
			
			tess.startDrawingQuads();
		}
		if (meta == 3)  {
			tess.draw();
			
			//Front
			tess.startDrawingQuads();
			tess.setColorOpaque_F(0.8F, 0.8F, 0.8F);
			tess.addVertexWithUV(x + 1, y + 0, z + 1, u1, v1);
			tess.addVertexWithUV(x + 1, y + 1, z,     u1, v0);
			tess.addVertexWithUV(x,     y + 1, z,     u0, v0);
			tess.addVertexWithUV(x,     y + 0, z + 1, u0, v1);
			tess.draw();
			
			//Back
			tess.startDrawingQuads();
			tess.setColorOpaque_F(0.8F, 0.8F, 0.8F);
			tess.addVertexWithUV(x,     y + 0, z, u1, v1);
			tess.addVertexWithUV(x,     y + 1, z, u1, v0);
			tess.addVertexWithUV(x + 1, y + 1, z, u0, v0);
			tess.addVertexWithUV(x + 1, y + 0, z, u0, v1);
			tess.draw();
			
			//Left
			tess.startDrawingQuads();
			tess.setColorOpaque_F(0.6F, 0.6F, 0.6F);
			tess.addVertexWithUV(x, y + 0, z + 1, u1, v1);
			tess.addVertexWithUV(x, y + 0, z + 1, u1, v0);
			tess.addVertexWithUV(x, y + 1, z + 0, u0, v0);
			tess.addVertexWithUV(x, y + 0, z + 0, u0, v1);
			tess.draw();
			
			//Right
			tess.startDrawingQuads();
			tess.setColorOpaque_F(0.6F, 0.6F, 0.6F);
			tess.addVertexWithUV(x + 1, y + 0, z + 0, u1, v1);
			tess.addVertexWithUV(x + 1, y + 1, z + 0, u1, v0);
			tess.addVertexWithUV(x + 1, y + 0, z + 1, u0, v0);
			tess.addVertexWithUV(x + 1, y + 0, z + 1, u0, v1);
			tess.draw();
			
			//Bottom
			tess.startDrawingQuads();
			tess.setColorOpaque_F(0.5F, 0.5F, 0.5F);
			tess.addVertexWithUV(x + 1, y, z + 0, u1, v1);
			tess.addVertexWithUV(x + 1, y, z + 1, u1, v0);
			tess.addVertexWithUV(x,     y, z + 1, u0, v0);
			tess.addVertexWithUV(x,     y, z + 0, u0, v1);
			tess.draw();
			
			tess.startDrawingQuads();
		}
		if (meta == 4)  {
			tess.draw();
			
			//Top
			tess.startDrawingQuads();
			tess.setColorOpaque_F(1.0F, 1.0F, 1.0F);
			tess.addVertexWithUV(x,     y + 1, z,     u1, v1);
			tess.addVertexWithUV(x,     y + 1, z + 1, u1, v0);
			tess.addVertexWithUV(x + 1, y + 1, z + 1, u0, v0);
			tess.addVertexWithUV(x + 1, y + 1, z,     u0, v1);
			tess.draw();
			
			//Front
			tess.startDrawingQuads();
			tess.setColorOpaque_F(0.8F, 0.8F, 0.8F);
			tess.addVertexWithUV(x + 1, y + 0, z + 1, u1, v1);
			tess.addVertexWithUV(x + 1, y + 1, z + 1, u1, v0);
			tess.addVertexWithUV(x,     y + 1, z + 1, u0, v0);
			tess.addVertexWithUV(x,     y + 1, z + 1, u0, v1);
			tess.draw();
			
			//Back
			tess.startDrawingQuads();
			tess.setColorOpaque_F(0.8F, 0.8F, 0.8F);
			tess.addVertexWithUV(x,     y + 1, z, u1, v1);
			tess.addVertexWithUV(x,     y + 1, z, u1, v0);
			tess.addVertexWithUV(x + 1, y + 1, z, u0, v0);
			tess.addVertexWithUV(x + 1, y + 0, z, u0, v1);
			tess.draw();
			
			//Right
			tess.startDrawingQuads();
			tess.setColorOpaque_F(0.6F, 0.6F, 0.6F);
			tess.addVertexWithUV(x + 1, y + 0, z + 0, u1, v1);
			tess.addVertexWithUV(x + 1, y + 1, z + 0, u1, v0);
			tess.addVertexWithUV(x + 1, y + 1, z + 1, u0, v0);
			tess.addVertexWithUV(x + 1, y + 0, z + 1, u0, v1);
			tess.draw();
			
			//Bottom
			tess.startDrawingQuads();
			tess.setColorOpaque_F(0.5F, 0.5F, 0.5F);
			tess.addVertexWithUV(x + 1, y,     z + 0, u1, v1);
			tess.addVertexWithUV(x + 1, y,     z + 1, u1, v0);
			tess.addVertexWithUV(x,     y + 1, z + 1, u0, v0);
			tess.addVertexWithUV(x,     y + 1, z + 0, u0, v1);
			tess.draw();
			
			tess.startDrawingQuads();
		}
		if (meta == 5)  {
			tess.draw();
			
			//Top
			tess.startDrawingQuads();
			tess.setColorOpaque_F(1.0F, 1.0F, 1.0F);
			tess.addVertexWithUV(x,     y + 1, z,     u1, v1);
			tess.addVertexWithUV(x,     y + 1, z + 1, u1, v0);
			tess.addVertexWithUV(x + 1, y + 1, z + 1, u0, v0);
			tess.addVertexWithUV(x + 1, y + 1, z,     u0, v1);
			tess.draw();
			
			//Front
			tess.startDrawingQuads();
			tess.setColorOpaque_F(0.8F, 0.8F, 0.8F);
			tess.addVertexWithUV(x + 1, y + 1, z + 1, u1, v1);
			tess.addVertexWithUV(x + 1, y + 1, z + 1, u1, v0);
			tess.addVertexWithUV(x,     y + 1, z + 1, u0, v0);
			tess.addVertexWithUV(x,     y + 0, z + 1, u0, v1);
			tess.draw();
			
			//Back
			tess.startDrawingQuads();
			tess.setColorOpaque_F(0.8F, 0.8F, 0.8F);
			tess.addVertexWithUV(x,     y + 0, z, u1, v1);
			tess.addVertexWithUV(x,     y + 1, z, u1, v0);
			tess.addVertexWithUV(x + 1, y + 1, z, u0, v0);
			tess.addVertexWithUV(x + 1, y + 1, z, u0, v1);
			tess.draw();
			
			//Left
			tess.startDrawingQuads();
			tess.setColorOpaque_F(0.6F, 0.6F, 0.6F);
			tess.addVertexWithUV(x, y + 0, z + 1, u1, v1);
			tess.addVertexWithUV(x, y + 1, z + 1, u1, v0);
			tess.addVertexWithUV(x, y + 1, z + 0, u0, v0);
			tess.addVertexWithUV(x, y + 0, z + 0, u0, v1);
			tess.draw();
			
			//Bottom
			tess.startDrawingQuads();
			tess.setColorOpaque_F(0.5F, 0.5F, 0.5F);
			tess.addVertexWithUV(x + 1, y + 1, z + 0, u1, v1);
			tess.addVertexWithUV(x + 1, y + 1, z + 1, u1, v0);
			tess.addVertexWithUV(x,     y, z + 1, u0, v0);
			tess.addVertexWithUV(x,     y, z + 0, u0, v1);
			tess.draw();
			
			tess.startDrawingQuads();
		}
		if (meta == 6)  {
			tess.draw();
			
			//Top
			tess.startDrawingQuads();
			tess.setColorOpaque_F(1.0F, 1.0F, 1.0F);
			tess.addVertexWithUV(x,     y + 1, z,     u1, v1);
			tess.addVertexWithUV(x,     y + 1, z + 1, u1, v0);
			tess.addVertexWithUV(x + 1, y + 1, z + 1, u0, v0);
			tess.addVertexWithUV(x + 1, y + 1, z,     u0, v1);
			tess.draw();
			
			//Front
			tess.startDrawingQuads();
			tess.setColorOpaque_F(0.8F, 0.8F, 0.8F);
			tess.addVertexWithUV(x + 1, y + 0, z + 1, u1, v1);
			tess.addVertexWithUV(x + 1, y + 1, z + 1, u1, v0);
			tess.addVertexWithUV(x,     y + 1, z + 1, u0, v0);
			tess.addVertexWithUV(x,     y + 0, z + 1, u0, v1);
			tess.draw();
			
			//Left
			tess.startDrawingQuads();
			tess.setColorOpaque_F(0.6F, 0.6F, 0.6F);
			tess.addVertexWithUV(x, y + 0, z + 1, u1, v1);
			tess.addVertexWithUV(x, y + 1, z + 1, u1, v0);
			tess.addVertexWithUV(x, y + 1, z + 0, u0, v0);
			tess.addVertexWithUV(x, y + 1, z + 0, u0, v1);
			tess.draw();
			
			//Right
			tess.startDrawingQuads();
			tess.setColorOpaque_F(0.6F, 0.6F, 0.6F);
			tess.addVertexWithUV(x + 1, y + 1, z + 0, u1, v1);
			tess.addVertexWithUV(x + 1, y + 1, z + 0, u1, v0);
			tess.addVertexWithUV(x + 1, y + 1, z + 1, u0, v0);
			tess.addVertexWithUV(x + 1, y + 0, z + 1, u0, v1);
			tess.draw();
			
			//Bottom
			tess.startDrawingQuads();
			tess.setColorOpaque_F(0.5F, 0.5F, 0.5F);
			tess.addVertexWithUV(x + 1, y + 1, z + 0, u1, v1);
			tess.addVertexWithUV(x + 1, y,     z + 1, u1, v0);
			tess.addVertexWithUV(x,     y,     z + 1, u0, v0);
			tess.addVertexWithUV(x,     y + 1, z + 0, u0, v1);
			tess.draw();
			
			tess.startDrawingQuads();
		}
		if (meta == 7)  {
			tess.draw();
			
			//Top
			tess.startDrawingQuads();
			tess.setColorOpaque_F(1.0F, 1.0F, 1.0F);
			tess.addVertexWithUV(x,     y + 1, z,     u1, v1);
			tess.addVertexWithUV(x,     y + 1, z + 1, u1, v0);
			tess.addVertexWithUV(x + 1, y + 1, z + 1, u0, v0);
			tess.addVertexWithUV(x + 1, y + 1, z,     u0, v1);
			tess.draw();
			
			//Back
			tess.startDrawingQuads();
			tess.setColorOpaque_F(0.8F, 0.8F, 0.8F);
			tess.addVertexWithUV(x,     y + 0, z, u1, v1);
			tess.addVertexWithUV(x,     y + 1, z, u1, v0);
			tess.addVertexWithUV(x + 1, y + 1, z, u0, v0);
			tess.addVertexWithUV(x + 1, y + 0, z, u0, v1);
			tess.draw();
			
			//Left
			tess.startDrawingQuads();
			tess.setColorOpaque_F(0.6F, 0.6F, 0.6F);
			tess.addVertexWithUV(x, y + 1, z + 1, u1, v1);
			tess.addVertexWithUV(x, y + 1, z + 1, u1, v0);
			tess.addVertexWithUV(x, y + 1, z + 0, u0, v0);
			tess.addVertexWithUV(x, y + 0, z + 0, u0, v1);
			tess.draw();
			
			//Right
			tess.startDrawingQuads();
			tess.setColorOpaque_F(0.6F, 0.6F, 0.6F);
			tess.addVertexWithUV(x + 1, y + 0, z + 0, u1, v1);
			tess.addVertexWithUV(x + 1, y + 1, z + 0, u1, v0);
			tess.addVertexWithUV(x + 1, y + 1, z + 1, u0, v0);
			tess.addVertexWithUV(x + 1, y + 1, z + 1, u0, v1);
			tess.draw();
			
			//Bottom
			tess.startDrawingQuads();
			tess.setColorOpaque_F(0.5F, 0.5F, 0.5F);
			tess.addVertexWithUV(x + 1, y,     z + 0, u1, v1);
			tess.addVertexWithUV(x + 1, y + 1, z + 1, u1, v0);
			tess.addVertexWithUV(x,     y + 1, z + 1, u0, v0);
			tess.addVertexWithUV(x,     y,     z + 0, u0, v1);
			tess.draw();
			
			tess.startDrawingQuads();
		}
		if (meta == 15)  {
			tess.draw();
			
			//Top
			tess.startDrawingQuads();
			tess.setColorOpaque_F(1.0F, 1.0F, 1.0F);
			tess.addVertexWithUV(x,     y + 1, z,     u1, v1);
			tess.addVertexWithUV(x,     y + 1, z + 1, u1, v0);
			tess.addVertexWithUV(x + 1, y + 1, z + 1, u0, v0);
			tess.addVertexWithUV(x + 1, y + 1, z,     u0, v1);
			tess.draw();
			
			//Front
			tess.startDrawingQuads();
			tess.setColorOpaque_F(0.8F, 0.8F, 0.8F);
			tess.addVertexWithUV(x + 1, y + 0, z + 1, u1, v1);
			tess.addVertexWithUV(x + 1, y + 1, z + 1, u1, v0);
			tess.addVertexWithUV(x,     y + 1, z + 1, u0, v0);
			tess.addVertexWithUV(x,     y + 0, z + 1, u0, v1);
			tess.draw();
			
			//Back
			tess.startDrawingQuads();
			tess.setColorOpaque_F(0.8F, 0.8F, 0.8F);
			tess.addVertexWithUV(x,     y + 0, z, u1, v1);
			tess.addVertexWithUV(x,     y + 1, z, u1, v0);
			tess.addVertexWithUV(x + 1, y + 1, z, u0, v0);
			tess.addVertexWithUV(x + 1, y + 0, z, u0, v1);
			tess.draw();
			
			//Left
			tess.startDrawingQuads();
			tess.setColorOpaque_F(0.6F, 0.6F, 0.6F);
			tess.addVertexWithUV(x, y + 0, z + 1, u1, v1);
			tess.addVertexWithUV(x, y + 1, z + 1, u1, v0);
			tess.addVertexWithUV(x, y + 1, z + 0, u0, v0);
			tess.addVertexWithUV(x, y + 0, z + 0, u0, v1);
			tess.draw();
			
			//Right
			tess.startDrawingQuads();
			tess.setColorOpaque_F(0.6F, 0.6F, 0.6F);
			tess.addVertexWithUV(x + 1, y + 0, z + 0, u1, v1);
			tess.addVertexWithUV(x + 1, y + 1, z + 0, u1, v0);
			tess.addVertexWithUV(x + 1, y + 1, z + 1, u0, v0);
			tess.addVertexWithUV(x + 1, y + 0, z + 1, u0, v1);
			tess.draw();
			
			//Bottom
			tess.startDrawingQuads();
			tess.setColorOpaque_F(0.5F, 0.5F, 0.5F);
			tess.addVertexWithUV(x + 1, y, z + 0, u1, v1);
			tess.addVertexWithUV(x + 1, y, z + 1, u1, v0);
			tess.addVertexWithUV(x,     y, z + 1, u0, v0);
			tess.addVertexWithUV(x,     y, z + 0, u0, v1);
			tess.draw();
			
			tess.startDrawingQuads();
		}
		return true;
	}

	@Override
	public int getRenderId() {
		return ClientProxy.slope45RenderID;
	}
}