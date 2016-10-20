package com.silvaniastudios.advancedarmoury.client.renderers.blocks;

import org.lwjgl.opengl.GL11;

import com.silvaniastudios.advancedarmoury.blocks.storage.lootcrates.LootCrateEntity;
import com.silvaniastudios.advancedarmoury.client.ClientProxy;
import com.silvaniastudios.advancedarmoury.client.renderers.models.TinyLootCrateModel;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.IBlockAccess;

public class TinyLootCrateRenderer extends TileEntitySpecialRenderer implements ISimpleBlockRenderingHandler{
	
	private final RenderItem renderItem;
	private final RenderBlocks renderBlock;
	
	private TinyLootCrateModel model;
	
	ResourceLocation texture = new ResourceLocation("advancedarmoury", "textures/model/tinylootcrate.png");
	 
	public TinyLootCrateRenderer() {
		this.renderItem = new RenderItem();
		this.renderBlock = new RenderBlocks();
		renderItem.setRenderManager(RenderManager.instance);
		this.model = new TinyLootCrateModel();
	}

	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer) {
		// TODO render inventory block stuff here
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
		int meta = world.getBlockMetadata(x, y, z);
		if (world.getTileEntity(x, y, z) instanceof LootCrateEntity) {
			LootCrateEntity te = (LootCrateEntity) world.getTileEntity(x, y, z);
					   															 //When facing NORTH:
			RenderBlockAA.renderBlock(world, 0.25,   0,      0.25,   0.75,   0.0625, 0.75,   true, block, x, y, z, meta); //Bottom
			RenderBlockAA.renderBlock(world, 0.25,   0.0625, 0.25,   0.3125, 0.4375, 0.75,   true, block, x, y, z, meta); //Left
			RenderBlockAA.renderBlock(world, 0.6875, 0.0625, 0.25,   0.75,   0.4375, 0.75,   true, block, x, y, z, meta); //Right
			RenderBlockAA.renderBlock(world, 0.3125, 0.0625, 0.25,   0.6875, 0.5,    0.3125, true, block, x, y, z, meta); //Back
			RenderBlockAA.renderBlock(world, 0.25,   0.4375, 0.25,   0.75,   0.5,    0.75,   true, block, x, y, z, meta); //Top*/
		}
		return true;
	}

	@Override
	public boolean shouldRender3DInInventory(int modelId) {
		return true;
	}

	@Override
	public int getRenderId() {
		return ClientProxy.tinyLootCrateRenderID;
	}

	@Override
	public void renderTileEntityAt(TileEntity tileEntity, double x, double y, double z, float scale) {
		LootCrateEntity te = (LootCrateEntity) tileEntity;
		ItemStack item = te.getStackInSlot(1);
		Block block = te.getWorldObj().getBlock(te.xCoord, te.yCoord, te.zCoord);
		int meta = te.blockMetadata;
		
		GL11.glPushMatrix();
		//GL11.glRotatef(te.rotation*45, 0.0F, 1.0F, 0.0F);
		
		this.bindTexture(TextureMap.locationBlocksTexture);
		/*RenderBlocks renderer = new RenderBlocks(te.getWorldObj());
		renderer.setRenderBounds(0.25, 0.0, 0.25, 0.75, 0.5, 0.75);
		renderer.renderStandardBlock(block, te.xCoord, te.yCoord, te.zCoord);*/

		String uuid = Minecraft.getMinecraft().thePlayer.getUniqueID().toString();
		
		boolean locked = true;
		boolean open = te.playerList.contains(uuid);
		float angle = 0.0F;
		
		if (open) {
			int id = te.playerList.indexOf(uuid);
			locked = te.playerLock.get(id);

			if (item != null && !locked) {
				if (!te.playerInv.isEmpty() && id < te.playerInv.size()) {
					te.playerInv.ensureCapacity(id);
					angle = 135;
					
					
					if (te.playerInv.get(id)[0] == 0) {
						GL11.glPushMatrix();
						EntityItem entity = new EntityItem(null, 0.0D, 0.0D, 0.0D, item);
						entity.hoverStart = 0.0F;
						GL11.glTranslated(x + 0.5, y+0.0625, z + 0.5);
						entity.setEntityItemStack(item);
						renderItem.doRender(entity, 0.0D, 0.0D, 0.0D, 0.0F, 0.0F);
						GL11.glPopMatrix();
					}
				}
			}

		} else {
			locked = te.getStackInSlot(0) != null;
			if (!locked) {
				angle = 10;
			}
		}
		
		renderDoor(te, x, y, z, angle, open);
		GL11.glPopMatrix();
	}

	public void renderDoor(LootCrateEntity te, double x, double y, double z, float rotation, boolean open) {
		Tessellator tess = Tessellator.instance;
		
		boolean locked = rotation == 0;
		
		GL11.glPushMatrix();
        GL11.glTranslated(x, y, z);
        
        Block block = te.getWorldObj().getBlock(te.xCoord, te.yCoord, te.zCoord);

        if (te.oldRotation < rotation) {
        	if (te.openDelay >= 50) {
        		te.oldRotation += 2;
        	} else {
        		te.openDelay++;
        	}
        } else {
        	te.oldRotation = rotation;
        	te.openDelay = 0;
        }

        GL11.glTranslatef( 0.72F, 0,  0.77F);
        GL11.glRotatef(te.oldRotation, 0.0f, 1.0f, 0.0f); //<- Rotate our object around the y axis
        GL11.glTranslatef(-0.72F, 0, -0.77F);

        tess.setBrightness(block.getMixedBrightnessForBlock(te.getWorldObj(), te.xCoord, te.yCoord, te.zCoord));        
        IIcon icon = te.blockType.getIcon(0, 0);
        IIcon iconOutside = te.blockType.getIcon(8, 0);
        IIcon iconInside = te.blockType.getIcon(8, 5);
        IIcon iconSides = te.blockType.getIcon(8, 4);
        
        IIcon iconOverlay = te.blockType.getIcon(8, 1);
        if (open) { iconOverlay = te.blockType.getIcon(8, 2); }
        if (locked) { iconOverlay = te.blockType.getIcon(8, 3); }
		
		double u0 = (double) icon.getMinU();
		double u1 = (double) icon.getMaxU();
		double v0 = (double) icon.getMinV();
		double v1 = (double) icon.getMaxV();
		
		double iu0 = (double) iconInside.getMinU(); //INSIDE textures
		double iu1 = (double) iconInside.getMaxU();
		double iv0 = (double) iconInside.getMinV();
		double iv1 = (double) iconInside.getMaxV();
		
		double su0 = (double) iconSides.getMinU(); //SIDE textures
		double su1 = (double) iconSides.getMaxU();
		double sv0 = (double) iconSides.getMinV();
		double sv1 = (double) iconSides.getMaxV();
		
		double ou0 = (double) iconOutside.getMinU(); //OUTSIDE texture
		double ou1 = (double) iconOutside.getMaxU();
		double ov0 = (double) iconOutside.getMinV();
		double ov1 = (double) iconOutside.getMaxV();
		
		double oou0 = (double) iconOverlay.getMinU(); //OUTSIDE OVERLAY texture
		double oou1 = (double) iconOverlay.getMaxU();
		double oov0 = (double) iconOverlay.getMinV();
		double oov1 = (double) iconOverlay.getMaxV();
        
		tess.startDrawingQuads();
		tess.addVertexWithUV(0.25, 0.499, 0.75,   su1, sv1);
		tess.addVertexWithUV(0.25, 0.499, 0.8125, su1, sv0);
		tess.addVertexWithUV(0.75, 0.499, 0.8125, su0, sv0);
		tess.addVertexWithUV(0.75, 0.499, 0.75,   su0, sv1);
		
		tess.setColorOpaque(204, 204, 204);		//NORTH (inside)
		tess.addVertexWithUV(0.75, 0.5, 0.75, iu1, iv1);
		tess.addVertexWithUV(0.75, 0,   0.75, iu1, iv0);
		tess.addVertexWithUV(0.25, 0,   0.75, iu0, iv0);
		tess.addVertexWithUV(0.25, 0.5, 0.75, iu0, iv1);
		
		tess.setColorOpaque(153, 153, 153);		//EAST (right side)
		tess.addVertexWithUV(0.75, 0.5, 0.8125, su1, sv1);
		tess.addVertexWithUV(0.75, 0,   0.8125, su1, sv0);
		tess.addVertexWithUV(0.75, 0,   0.75,   su0, sv0);
		tess.addVertexWithUV(0.75, 0.5, 0.75,   su0, sv1);
		
		tess.setColorOpaque(204, 204, 204);		//SOUTH (outside)
		tess.addVertexWithUV(0.25, 0.5, 0.8125, ou1, ov1);
		tess.addVertexWithUV(0.25, 0,   0.8125, ou1, ov0);
		tess.addVertexWithUV(0.75, 0,   0.8125, ou0, ov0);
		tess.addVertexWithUV(0.75, 0.5, 0.8125, ou0, ov1);

		tess.setColorOpaque(153, 153, 153);		//WEST (left side)
		tess.addVertexWithUV(0.25, 0.5, 0.75,   su1, sv1);
		tess.addVertexWithUV(0.25, 0,   0.75,   su1, sv0);
		tess.addVertexWithUV(0.25, 0,   0.8125, su0, sv0);
		tess.addVertexWithUV(0.25, 0.5, 0.8125, su0, sv1);

		tess.setColorOpaque(128, 128, 128);		//BOTTOM
		tess.addVertexWithUV(0.25, 0, 0.8125, su1, sv1);
		tess.addVertexWithUV(0.25, 0, 0.75,   su1, sv0);
		tess.addVertexWithUV(0.75, 0, 0.75,   su0, sv0);
		tess.addVertexWithUV(0.75, 0, 0.8125, su0, sv1);
		tess.draw();
		
		tess.startDrawingQuads();
		tess.setBrightness(15728880);
		tess.setColorOpaque(255, 255, 255); 	//SOUTH (outside overlay)
		tess.addVertexWithUV(0.25, 0.5,  0.81255, oou1, oov1);
		tess.addVertexWithUV(0.25, 0, 0.81255, oou1, oov0);
		tess.addVertexWithUV(0.75, 0, 0.81255, oou0, oov0);
		tess.addVertexWithUV(0.75, 0.5,  0.81255, oou0, oov1);        
        tess.draw();
        GL11.glPopMatrix();
	}
}
