package co.uk.silvania.advancedarmoury.client.renderers;

import org.lwjgl.opengl.GL11;

import co.uk.silvania.advancedarmoury.AdvancedArmoury;
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
	
	public PartRenderComponent(String model, String material) {
		this.modelLoc = new ResourceLocation(AdvancedArmoury.modid, "models/" + model + ".obj");
		this.texture = new ResourceLocation(AdvancedArmoury.modid, "models/texture" + material + ".png");

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
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void renderItem(ItemRenderType type, ItemStack item, Object... data) {
		Minecraft.getMinecraft().renderEngine.bindTexture(texture);
		GL11.glPushMatrix();
		GL11.glScalef(1.5F, 1.5F, 1.5F);
		model.renderAll();
		GL11.glPopMatrix();
	}

}
