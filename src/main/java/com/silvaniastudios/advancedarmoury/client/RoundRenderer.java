package com.silvaniastudios.advancedarmoury.client;

import java.util.Random;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import com.silvaniastudios.advancedarmoury.AdvancedArmoury;
import com.silvaniastudios.advancedarmoury.items_old.rounds.ItemRound;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.IItemRenderer.ItemRenderType;

public class RoundRenderer implements IItemRenderer {
	
	private static RenderItem renderItem = new RenderItem();
	private Random random = new Random();
	
	public RoundRenderer() {
		
	}

	@Override
	public boolean handleRenderType(ItemStack item, ItemRenderType type) {
		return true;
	}

	@Override
	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {
		return false;
	}

	@Override
	public void renderItem(ItemRenderType type, ItemStack item, Object... data) {				
		if (item.getItem() instanceof ItemRound && item.stackTagCompound != null) {
			ItemRound itemRound = (ItemRound) item.getItem();
			
			String casing = item.stackTagCompound.getString("case");
			String bullet = item.stackTagCompound.getString("bullet");
			int calibre = item.stackTagCompound.getInteger("calibreId");
			
			IIcon bulletIcon = itemRound.getBulletIcon(bullet, calibre);
			IIcon caseIcon = itemRound.getCaseIcon(casing, calibre);
			
			if (type == ItemRenderType.INVENTORY) {
				renderInventoryItem(item, bulletIcon, caseIcon);
			}
			
			if (type == ItemRenderType.ENTITY) {
            	float u0 = (float) caseIcon.getMinU();
				float u1 = (float) caseIcon.getMaxU();
				
				float v0 = (float) caseIcon.getMinV();
				float v1 = (float) caseIcon.getMaxV();
				
				float u2 = (float) bulletIcon.getMinU();
				float u3 = (float) bulletIcon.getMaxU();
				
				float v2 = (float) bulletIcon.getMinV();
				float v3 = (float) bulletIcon.getMaxV();
            	
            	
				//ItemRenderer.renderItemIn2D(tess, u0, u1, v0, v1, caseIcon.getIconWidth(), caseIcon.getIconHeight(), 0.0625F);
				//ItemRenderer.renderItemIn2D(tess, u2, u3, v2, v3, bulletIcon.getIconWidth(), bulletIcon.getIconHeight(), 0.0625F);
            	
            	renderDroppedItem(new EntityItem(Minecraft.getMinecraft().theWorld, 0D, 0D, 0D, item), caseIcon, 1, 0, item);
            	renderDroppedItem(new EntityItem(Minecraft.getMinecraft().theWorld, 0D, 0D, 0D, item), bulletIcon, 1, 0, item);
            } else {
            	
            }
		}
	}
		
	public void renderInventoryItem(ItemStack item, IIcon bulletIcon, IIcon caseIcon) {
		if (item.stackTagCompound != null) {			
			GL11.glPushMatrix();
			GL11.glEnable(GL12.GL_RESCALE_NORMAL);
			GL11.glDisable(GL11.GL_LIGHTING);
            GL11.glEnable(GL11.GL_ALPHA_TEST);
            GL11.glDisable(GL11.GL_BLEND);
            Tessellator tess = Tessellator.instance;
			
            renderItem.renderIcon(0, 0, caseIcon, 16, 16);
            renderItem.renderIcon(0, 0, bulletIcon, 16, 16);
            
			GL11.glEnable(GL11.GL_LIGHTING);
            GL11.glDisable(GL11.GL_ALPHA_TEST);
            GL11.glEnable(GL11.GL_BLEND);
            GL11.glDisable(GL12.GL_RESCALE_NORMAL);
            GL11.glPopMatrix();
		}
	}
	
	//Taken from RenderItem's class, with various unimportat parts removed.
	//For reference, original pars were EntityItem item, IIcon icon, int stacksize, float rotateSpeed?, float colourR, float colourG, float colourB, int pass.
	//MCP doesn't seem to know this, and as the class is depreciated in 1.8 there's no point submitting it.
	private void renderDroppedItem(EntityItem item, IIcon icon, int stackSize, int pass, ItemStack stack) {
        Tessellator tessellator = Tessellator.instance;
        item.hoverStart = 1.0F;

        if (icon == null) {
            TextureManager texturemanager = Minecraft.getMinecraft().getTextureManager();
            ResourceLocation resourcelocation = texturemanager.getResourceLocation(item.getEntityItem().getItemSpriteNumber());
            icon = ((TextureMap)texturemanager.getTexture(resourcelocation)).getAtlasSprite("missingno");
        }

        float f14 = ((IIcon)icon).getMinU();
        float f15 = ((IIcon)icon).getMaxU();
        float f4 = ((IIcon)icon).getMinV();
        float f5 = ((IIcon)icon).getMaxV();
        float f6 = 1.0F;
        float f7 = 0.5F;
        float f8 = 0.25F;
        float f10;

        GL11.glPushMatrix();
        GL11.glRotatef((((float)item.age) / 20.0F + item.hoverStart) * (180F / (float)Math.PI), 0.0F, 1.0F, 0.0F);

        float f9 = 0.0625F;
        f10 = 0.021875F;
        ItemStack itemstack = item.getEntityItem();
        int j = itemstack.stackSize;
        byte b0;

        if (j < 2) {
            b0 = 1;
        } else if (j < 16) {
            b0 = 2;
        } else if (j < 32) {
            b0 = 3;
        } else {
            b0 = 4;
        }

        GL11.glTranslatef(-f7, -f8, -((f9 + f10) * (float)b0 / 2.0F));

        for (int k = 0; k < b0; ++k) {
            // Makes items offset when in 3D, like when in 2D, looks much better. Considered a vanilla bug...
            if (k > 0) {
                float x = (random.nextFloat() * 2.0F - 1.0F) * 0.3F / 0.5F;
                float y = (random.nextFloat() * 2.0F - 1.0F) * 0.3F / 0.5F;
                float z = (random.nextFloat() * 2.0F - 1.0F) * 0.3F / 0.5F;
                GL11.glTranslatef(x, y, f9 + f10);
            } else {
                GL11.glTranslatef(0f, 0f, f9 + f10);
            }

            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            ItemRenderer.renderItemIn2D(tessellator, f15, f4, f14, f5, ((IIcon)icon).getIconWidth(), ((IIcon)icon).getIconHeight(), f9);

        }

        GL11.glPopMatrix();
    }
}
