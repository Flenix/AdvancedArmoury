package co.uk.silvania.advancedarmoury.client.renderers;

import org.lwjgl.opengl.GL11;

import co.uk.silvania.advancedarmoury.AdvancedArmoury;
import co.uk.silvania.advancedarmoury.items.components.generic.ItemBarrel;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;

public class PartRenderBarrel implements IItemRenderer {
	
	public ResourceLocation modelLoc;
	public ResourceLocation texture;
	public IModelCustom model;
	
	public PartRenderBarrel(String tex) {
		modelLoc = new ResourceLocation(AdvancedArmoury.modid, "models/barrelSegmant.obj");
		texture = new ResourceLocation(AdvancedArmoury.modid, "models/texture" + tex + ".png");

		model = AdvancedModelLoader.loadModel(modelLoc);
	}

	@Override
	public boolean handleRenderType(ItemStack item, ItemRenderType type) {
		return type == ItemRenderType.ENTITY;
	}

	@Override
	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {
		return true;
	}

	@Override
	public void renderItem(ItemRenderType type, ItemStack item, Object... data) {
		if (type == ItemRenderType.ENTITY) {
			renderPart(0, 0, 1.5F, 0.0F, 0.0F, 0.0F, item);
			return;
		}
	}
		
	public void renderPart(int rotXAngle, int rotYAngle, float scale, float moveX, float moveY, float moveZ, ItemStack item) {
		Minecraft.getMinecraft().renderEngine.bindTexture(texture);
		if (item.getItem() instanceof ItemBarrel) {
			if (item.stackTagCompound != null) {
				int length = item.stackTagCompound.getInteger("length");
				for (int i = 0; i < length; i++) {
					float offset = i * 0.05F;
					GL11.glPushMatrix();
					GL11.glRotatef(rotXAngle, 0F, 1F, 0F);
					GL11.glRotatef(rotYAngle, 1F, 0F, 0F);
					GL11.glScalef(scale, scale, scale);
					GL11.glTranslatef(moveX, moveY, moveZ + offset);
					model.renderAll();
					GL11.glPopMatrix();
				}
			}
		}
	}
}
