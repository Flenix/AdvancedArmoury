package co.uk.silvania.advancedarmoury.client.renderers;

import org.lwjgl.opengl.GL11;

import co.uk.silvania.advancedarmoury.AdvancedArmoury;
import co.uk.silvania.advancedarmoury.items.components.ComponentType;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;

public class PartRenderBase implements IItemRenderer {
	
	ResourceLocation safeModelLoc;
	
	public ResourceLocation modelLoc;
	public ResourceLocation texture;
	public IModelCustom model;
	
	public PartRenderBase(String modelName, String modelTexture) {
		modelLoc = new ResourceLocation(AdvancedArmoury.modid, "models/" + modelName + ".obj");
		texture = new ResourceLocation(AdvancedArmoury.modid, "models/" + modelTexture + ".png");

		model = AdvancedModelLoader.loadModel(modelLoc);
	}
	
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
		switch (type) {
			case ENTITY: {
				renderPart(0, 0, 1.5F, 0.0F, 0.0F, 0.0F, item);
				return;
			}
			case EQUIPPED: {
				renderPart(40, 285, 2F, 0.0F, 0.0F, -0.5F, item);
				return;
			}
			case EQUIPPED_FIRST_PERSON: {
				renderPart(140, 0, 10F, 0.0F, 1.6F, -0.3F, item);
				return;
			}
			case INVENTORY: {
				renderPart(0, 0, 2F, 0.0F, 0.0F, 0.0F, item);
				return;
			}
			default:
				return;
		}
	}	
	
	public void renderPart(int rotX, int rotY, float scale, float moveX, float moveY, float moveZ, ItemStack item) {
		Minecraft.getMinecraft().renderEngine.bindTexture(texture);
		GL11.glPushMatrix();
		GL11.glPushMatrix();
		GL11.glRotatef(rotX, 0F, 1F, 0F);
		GL11.glRotatef(rotY, 1F, 0F, 0F);
		GL11.glScalef(scale, scale, scale);
		GL11.glTranslatef(moveX, moveY, moveZ);
		model.renderAll();
		GL11.glPopMatrix();
		GL11.glPopMatrix();
	}
}
