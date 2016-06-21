package co.uk.silvania.advancedarmoury.client.renderers.blocks;

import org.lwjgl.opengl.GL11;

import co.uk.silvania.advancedarmoury.client.ClientProxy;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.client.IItemRenderer;

public class ItemBlockRenderer implements IItemRenderer {
	
	@Override
	public boolean handleRenderType(ItemStack item, ItemRenderType type) {
		return true;
	}

	@Override
	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {
		return true;
	}

	@Override
	public void renderItem(ItemRenderType type, ItemStack item, Object... data) {
		renderBlock(new RenderBlocks(), item, item.getItemDamage());
		
	}

	public void renderBlock(RenderBlocks renderer, ItemStack item, int meta) {
		if (meta == 0) { renderItemBlock(0.0,  0.0, 0.0,  1.0,  1.0, 1.0,  renderer, item, meta); }
		if (meta == 1) { renderItemBlock(0.0,  0.0, 0.0,  1.0,  0.5, 1.0,  renderer, item, meta); }
		if (meta == 2) { renderItemBlock(0.0,  0.5, 0.0,  1.0,  1.0, 1.0,  renderer, item, meta); }
		if (meta == 3) { renderItemBlock(0.25, 0.0, 0.25, 0.75, 1.0, 0.75, renderer, item, meta); }
	}
	
	public void renderItemBlock(double minX, double minY, double minZ, double maxX, double maxY, double maxZ, RenderBlocks renderer, ItemStack item,int meta) {
		renderer.setRenderBounds(minX, minY, minZ, maxX, maxY, maxZ);
		
		Block block = Block.getBlockFromItem(item.getItem());

		Tessellator tess = Tessellator.instance;
		GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
		
		tess.startDrawingQuads();
		tess.setNormal(0.0F, -1.0F, 0.0F);
		renderer.renderFaceYNeg(block, 0.0D, 0.0D, 0.0D, block.getIcon(0, 0));
		tess.draw();
		
		tess.startDrawingQuads();
		tess.setNormal(0.0F, 1.0F, 0.0F);
		renderer.renderFaceYPos(block, 0.0D, 0.0D, 0.0D, block.getIcon(1, 0));
		tess.draw();
		
		tess.startDrawingQuads();
		tess.setNormal(0.0F, 0.0F, -1.0F);
		renderer.renderFaceZNeg(block, 0.0D, 0.0D, 0.0D, block.getIcon(2, 0));
		tess.draw();
		
		tess.startDrawingQuads();
		tess.setNormal(0.0F, 0.0F, 1.0F);
		renderer.renderFaceZPos(block, 0.0D, 0.0D, 0.0D, block.getIcon(3, 0));
		tess.draw();
		
		tess.startDrawingQuads();
		tess.setNormal(-1.0F, 0.0F, 0.0F);
		renderer.renderFaceXNeg(block, 0.0D, 0.0D, 0.0D, block.getIcon(4, 0));
		tess.draw();
		
		tess.startDrawingQuads();
		tess.setNormal(1.0F, 0.0F, 0.0F);
		renderer.renderFaceXPos(block, 0.0D, 0.0D, 0.0D, block.getIcon(5, 0));
		tess.draw();
		
		GL11.glTranslatef(0.5F, 0.5F, 0.5F);
	}
}