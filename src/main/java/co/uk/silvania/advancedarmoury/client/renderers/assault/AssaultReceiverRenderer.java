package co.uk.silvania.advancedarmoury.client.renderers.assault;

import org.lwjgl.opengl.GL11;

import co.uk.silvania.advancedarmoury.AdvancedArmoury;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;

public class AssaultReceiverRenderer implements IItemRenderer {

	public ResourceLocation modelReceiver;
	public ResourceLocation modelBolt;
	public ResourceLocation modelChargingHandle;
	public ResourceLocation modelFireSelector;

	public ResourceLocation textureReceiver;
	public ResourceLocation textureBolt;
	public ResourceLocation textureChargingHandle;
	public ResourceLocation textureFireSelector;

	public IModelCustom mdlReceiver;
	public IModelCustom mdlBolt;
	public IModelCustom mdlChargingHandle;
	public IModelCustom mdlFireSelector;

	public AssaultReceiverRenderer(String model, String texture) {
		modelReceiver = new ResourceLocation(AdvancedArmoury.modid, "models/" + model + ".obj");
		modelBolt = new ResourceLocation(AdvancedArmoury.modid, "models/" + model + "_bolt.obj");
		modelChargingHandle = new ResourceLocation(AdvancedArmoury.modid, "models/" + model + "_charginghandle.obj");
		modelFireSelector = new ResourceLocation(AdvancedArmoury.modid, "models/" + model + "_fireselector.obj");

		textureReceiver = new ResourceLocation(AdvancedArmoury.modid, "models/" + texture + ".png");

		mdlReceiver = AdvancedModelLoader.loadModel(modelReceiver);
		mdlBolt = AdvancedModelLoader.loadModel(modelBolt);
		mdlChargingHandle = AdvancedModelLoader.loadModel(modelChargingHandle);
		mdlFireSelector = AdvancedModelLoader.loadModel(modelFireSelector);
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
		if (item.stackTagCompound != null) {
			textureBolt = new ResourceLocation(AdvancedArmoury.modid, "models/texture" + item.stackTagCompound.getString("boltMaterial") + ".png");
			textureChargingHandle = new ResourceLocation(AdvancedArmoury.modid, "models/texture" + item.stackTagCompound.getString("chargingHandleMaterial") + ".png");
			textureFireSelector = new ResourceLocation(AdvancedArmoury.modid, "models/texture" + item.stackTagCompound.getString("fireSelectorMaterial") + ".png");
		} else {
			textureBolt = new ResourceLocation(AdvancedArmoury.modid, "models/texturePolymer.png");
			textureChargingHandle = new ResourceLocation(AdvancedArmoury.modid, "models/texturePolymer.png");
			textureFireSelector = new ResourceLocation(AdvancedArmoury.modid, "models/texturePolymer.png");
		}
		switch (type) {
			case ENTITY: {
				doRender(textureReceiver, mdlReceiver, 0, 0, 1.5F, 0, 0, 0);
				doRender(textureBolt, mdlBolt, 0, 0, 1.5F, 0, 0, 0);
				doRender(textureChargingHandle, mdlChargingHandle, 0, 0, 1.5F, 0, 0, 0);
				doRender(textureFireSelector, mdlFireSelector, 0, 0, 1.5F, 0, 0, 0);
				return;
			}
			case EQUIPPED: {
				doRender(textureReceiver, mdlReceiver, 0, 0, 1.5F, 0, 0, 0);
				doRender(textureBolt, mdlBolt, 0, 0, 1.5F, 0, 0, 0);
				doRender(textureChargingHandle, mdlChargingHandle, 0, 0, 1.5F, 0, 0, 0);
				doRender(textureFireSelector, mdlFireSelector, 0, 0, 1.5F, 0, 0, 0);
				return;
			}
			case EQUIPPED_FIRST_PERSON: {
				doRender(textureReceiver, mdlReceiver, 0, 0, 1.5F, 0, 0, 0);
				doRender(textureBolt, mdlBolt, 0, 0, 1.5F, 0, 0, 0);
				doRender(textureChargingHandle, mdlChargingHandle, 0, 0, 1.5F, 0, 0, 0);
				doRender(textureFireSelector, mdlFireSelector, 0, 0, 1.5F, 0, 0, 0);
				return;
			}
			case INVENTORY: {
				doRender(textureReceiver, mdlReceiver, 0, 0, 2F, 0, 0, 0);
				doRender(textureBolt, mdlBolt, 0, 0, 2F, 0, 0, 0);
				doRender(textureChargingHandle, mdlChargingHandle, 0, 0, 2F, 0, 0, 0);
				doRender(textureFireSelector, mdlFireSelector, 0, 0, 2F, 0, 0, 0);
				return;
			}
			default:
				return;
		}
	}
	
	//doRender instead of renderPart because it renders multiple parts, and I don't want to confuse myself.
	public void doRender(ResourceLocation texture, IModelCustom model, int rotX, int rotY, float scale, float moveX, float moveY, float moveZ) {
		Minecraft.getMinecraft().renderEngine.bindTexture(texture);
		GL11.glPushMatrix();
		GL11.glScalef(scale, scale, scale);
		model.renderAll();
		GL11.glPopMatrix();
	}

}
