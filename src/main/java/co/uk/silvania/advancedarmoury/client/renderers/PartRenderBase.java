package co.uk.silvania.advancedarmoury.client.renderers;

import org.lwjgl.opengl.GL11;

import co.uk.silvania.advancedarmoury.AdvancedArmoury;
import co.uk.silvania.advancedarmoury.config.MaterialStats;
import co.uk.silvania.advancedarmoury.items.assets.ComponentType;
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
	boolean customTexture;
	
	public PartRenderBase(String modelName, String modelTexture, boolean customTexture) {
		modelLoc = new ResourceLocation(AdvancedArmoury.modid, "models/" + modelName + ".obj");
		System.out.println("model: " + AdvancedArmoury.modid + "/models/" + modelName + ".obj");
		if (customTexture) {
			texture = new ResourceLocation(AdvancedArmoury.modid, "models/" + modelTexture + ".png");
		} else {
			texture = new ResourceLocation(AdvancedArmoury.modid, "models/white.png");
			customTexture = true;
		}

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
		if (!customTexture) {
			if (item.stackTagCompound != null) {
				applyColor(item.stackTagCompound.getInteger("itemCol"));
			}
		}
		GL11.glRotatef(rotX, 0F, 1F, 0F);
		GL11.glRotatef(rotY, 1F, 0F, 0F);
		GL11.glScalef(scale, scale, scale);
		GL11.glTranslatef(moveX, moveY, moveZ);
		model.renderAll();
		GL11.glColor3f(1,1,1);
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
