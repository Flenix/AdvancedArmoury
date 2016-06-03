package co.uk.silvania.advancedarmoury.client.renderers;

import org.lwjgl.opengl.GL11;

import co.uk.silvania.advancedarmoury.AdvancedArmoury;
import co.uk.silvania.advancedarmoury.config.MaterialStats;
import co.uk.silvania.advancedarmoury.items_old.components.generic.ItemBarrel;
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
	String material;
	
	public PartRenderBarrel(String material) {
		modelLoc = new ResourceLocation(AdvancedArmoury.modid, "models/barrelSegmant.obj");
		texture = new ResourceLocation(AdvancedArmoury.modid, "models/white.png");

		model = AdvancedModelLoader.loadModel(modelLoc);
		
		this.material = material;
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
					applyColor(MaterialStats.getRGB(material));
					GL11.glRotatef(rotXAngle, 0F, 1F, 0F);
					GL11.glRotatef(rotYAngle, 1F, 0F, 0F);
					GL11.glScalef(scale, scale, scale);
					GL11.glTranslatef(moveX, moveY, moveZ + offset);
					model.renderAll();
					GL11.glColor3f(1, 1, 1);
					GL11.glPopMatrix();
				}
			}
		}
	}
	
	public void applyColor(int rgb) {
		if ((rgb & -67108864) == 0) {
			rgb |= -16777216;
        }
		
		float red = (float)(rgb >> 16 & 255) / 255.0F;
		float blue = (float)(rgb >> 8 & 255) / 255.0F;
		float green = (float)(rgb & 255) / 255.0F;
		
		GL11.glColor3f(red, blue, green);
	}
}
