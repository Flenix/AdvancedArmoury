package com.silvaniastudios.advancedarmoury.gun.inventory.assault;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import com.silvaniastudios.advancedarmoury.AdvancedArmoury;
import com.silvaniastudios.advancedarmoury.gun.inventory.ItemIInventory;

public class AssaultMagazineGui extends GuiContainer {

	private static final ResourceLocation iconLoc = new ResourceLocation(AdvancedArmoury.modid, "textures/gui/m4mag.png");
	private final ItemIInventory inventory;

	public AssaultMagazineGui(AssaultMagazineContainer containerGun) {
		super(containerGun);
		this.inventory = containerGun.inventory;
		
		xSize = 220;
		ySize = 193;
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int par1, int par2) {
		String s = this.inventory.getInventoryName();
		
		this.fontRendererObj.drawString(s, 7, 6, 4210752);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int p_146976_2_, int p_146976_3_) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		GL11.glEnable(GL11.GL_BLEND);
		this.mc.getTextureManager().bindTexture(iconLoc);
		int k = (this.width - this.xSize) / 2;
		int l = (this.height - this.ySize) / 2;
		this.drawTexturedModalRect(k, l, 0, 0,  this.xSize, this.ySize);
	}
}
