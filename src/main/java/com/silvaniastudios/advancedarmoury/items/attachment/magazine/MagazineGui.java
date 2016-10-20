package com.silvaniastudios.advancedarmoury.items.attachment.magazine;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import com.silvaniastudios.advancedarmoury.AdvancedArmoury;

public class MagazineGui extends GuiContainer {
	
	private static final ResourceLocation iconLoc = new ResourceLocation(AdvancedArmoury.modid, "textures/gui/mag.png");
	private final MagazineInventory inventory;

	public MagazineGui(MagazineContainer containerMag) {
		super(containerMag);
		this.inventory = containerMag.inventory;
	}
	
	protected int xSize = 176;
	protected int ySize = 221;
	
	private float xSize_lo;
	private float ySize_lo;
	
	@Override
	public void drawScreen(int par1, int par2, float par3) {
		super.drawScreen(par1, par2, par3);
		this.xSize_lo = (float) par1;
		this.ySize_lo = (float) par2;
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int par1, int par2) {
		String s = this.inventory.getInventoryName();
		
		this.fontRendererObj.drawString(s, 10, 10, 4210752);
		//this.fontRendererObj.drawString(I18n.format("container.inventory"), 26, this.ySize - 96 + 4, 4210752);
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
