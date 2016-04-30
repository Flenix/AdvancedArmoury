package co.uk.silvania.advancedarmoury.client.renderers;

import org.lwjgl.opengl.GL11;

import co.uk.silvania.advancedarmoury.AdvancedArmoury;
import co.uk.silvania.advancedarmoury.config.MaterialStats;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;

public class PartRenderComponent implements IItemRenderer {
	
	public ResourceLocation modelLoc;
	public ResourceLocation texture;
	public IModelCustom model;
	
	String material;
	
	public PartRenderComponent(String model, String material) {
		this.modelLoc = new ResourceLocation(AdvancedArmoury.modid, "models/" + model + ".obj");
		this.texture = new ResourceLocation(AdvancedArmoury.modid, "models/white.png");

		this.material = material;
		
		this.model = AdvancedModelLoader.loadModel(modelLoc);
	}

	@Override
	public boolean handleRenderType(ItemStack item, ItemRenderType type) {
		if (type == ItemRenderType.ENTITY) {
			return true;
		}
		return false;
	}

	@Override
	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {
		return true;
	}

	@Override
	public void renderItem(ItemRenderType type, ItemStack item, Object... data) {
		Minecraft.getMinecraft().renderEngine.bindTexture(texture);
		GL11.glPushMatrix();
		applyColor(MaterialStats.getRGB(material));
		GL11.glScalef(1.5F, 1.5F, 1.5F);
		model.renderAll();
		GL11.glColor3f(1, 1, 1);
		GL11.glPopMatrix();
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
