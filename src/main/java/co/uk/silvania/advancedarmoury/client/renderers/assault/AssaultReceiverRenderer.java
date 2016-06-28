package co.uk.silvania.advancedarmoury.client.renderers.assault;

import org.lwjgl.opengl.GL11;

import co.uk.silvania.advancedarmoury.AdvancedArmoury;
import co.uk.silvania.advancedarmoury.config.MaterialStats;
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
	public ResourceLocation texturePart = new ResourceLocation(AdvancedArmoury.modid, "models/white.png");

	public IModelCustom mdlReceiver;
	public IModelCustom mdlBolt;
	public IModelCustom mdlChargingHandle;
	public IModelCustom mdlFireSelector;
	
	boolean useRGB = false;
	int rgbRec;
	int rgbBolt;
	int rgbCharge;
	int rgbFireSel;
	

	public AssaultReceiverRenderer(String model, String texture, boolean customTextured) {
		modelReceiver = new ResourceLocation(AdvancedArmoury.modid, "models/" + model + ".obj");
		modelBolt = new ResourceLocation(AdvancedArmoury.modid, "models/" + model + "_bolt.obj");
		modelChargingHandle = new ResourceLocation(AdvancedArmoury.modid, "models/" + model + "_charginghandle.obj");
		modelFireSelector = new ResourceLocation(AdvancedArmoury.modid, "models/" + model + "_fireselector.obj");
		
		if (customTextured) {
			useRGB = false;
			textureReceiver = new ResourceLocation(AdvancedArmoury.modid, "models/" + texture + ".png");
		} else {
			useRGB = true;
			textureReceiver = new ResourceLocation(AdvancedArmoury.modid, "models/white.png");
		}

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
			rgbRec = item.stackTagCompound.getInteger("itemCol");
			rgbBolt = item.stackTagCompound.getInteger("boltTextureColour");
			rgbCharge = item.stackTagCompound.getInteger("chargingHandleTextureColour");
			rgbFireSel = item.stackTagCompound.getInteger("fireSelectorTextureColour");
		} else {
			rgbRec = MaterialStats.getRGB("Polymer");
			rgbBolt = MaterialStats.getRGB("Polymer");
			rgbCharge = MaterialStats.getRGB("Polymer");
			rgbFireSel = MaterialStats.getRGB("Polymer");
		}
		switch (type) {
			case ENTITY: {
				doRender(textureReceiver, mdlReceiver, 0, 0, 1.5F, 0, 0, 0);
				doRender(texturePart, mdlBolt, 0, 0, 1.5F, 0, 0, 0);
				doRender(texturePart, mdlChargingHandle, 0, 0, 1.5F, 0, 0, 0);
				doRender(texturePart, mdlFireSelector, 0, 0, 1.5F, 0, 0, 0);
				return;
			}
			case EQUIPPED: {
				doRender(textureReceiver, mdlReceiver, 0, 0, 1.5F, 0, 0, 0);
				doRender(texturePart, mdlBolt, 0, 0, 1.5F, 0, 0, 0);
				doRender(texturePart, mdlChargingHandle, 0, 0, 1.5F, 0, 0, 0);
				doRender(texturePart, mdlFireSelector, 0, 0, 1.5F, 0, 0, 0);
				return;
			}
			case EQUIPPED_FIRST_PERSON: {
				doRender(textureReceiver, mdlReceiver, 0, 0, 1.5F, 0, 0, 0);
				doRender(texturePart, mdlBolt, 0, 0, 1.5F, 0, 0, 0);
				doRender(texturePart, mdlChargingHandle, 0, 0, 1.5F, 0, 0, 0);
				doRender(texturePart, mdlFireSelector, 0, 0, 1.5F, 0, 0, 0);
				return;
			}
			case INVENTORY: {
				doRender(textureReceiver, mdlReceiver, 0, 0, 2F, 0, 0, 0);
				doRender(texturePart, mdlBolt, 0, 0, 2F, 0, 0, 0);
				doRender(texturePart, mdlChargingHandle, 0, 0, 2F, 0, 0, 0);
				doRender(texturePart, mdlFireSelector, 0, 0, 2F, 0, 0, 0);
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
		if (useRGB && model == mdlReceiver)  { applyColor(rgbRec); }
		else if (model == mdlBolt) 			 { applyColor(rgbBolt); }
		else if (model == mdlChargingHandle) { applyColor(rgbCharge); }
		else if (model == mdlFireSelector) 	 { applyColor(rgbFireSel); }
		GL11.glScalef(scale, scale, scale);
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
