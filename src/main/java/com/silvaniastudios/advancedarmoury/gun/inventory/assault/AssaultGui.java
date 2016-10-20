package com.silvaniastudios.advancedarmoury.gun.inventory.assault;

import org.lwjgl.opengl.GL11;

import com.silvaniastudios.advancedarmoury.AdvancedArmoury;
import com.silvaniastudios.advancedarmoury.gun.inventory.ItemIInventory;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class AssaultGui extends GuiContainer {
	
	private static final ResourceLocation iconLoc = new ResourceLocation(AdvancedArmoury.modid, "textures/gui/gun.png");
	private ItemIInventory inventory;

	public AssaultGui(AssaultContainer containerGun) {
		super(containerGun);
		this.inventory = containerGun.inventory;
		
		xSize = 256;
		ySize = 256;
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
		drawGunModel();
		//From tutorial, adds the player model. Modify and use to add gun model when guns have models.
		//drawPlayerModel(k + 51, l + 75, 30, (float)(k + 51) - this.xSize_lo, (float)(l + 75 - 50) - this.ySize_lo, this.mc.thePlayer);
	}
	
	public void drawGunModel() {
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glPushMatrix();
		GL11.glTranslatef(guiLeft + (256 / 2), guiTop + 97, 100);
		RenderManager.instance.playerViewY = 180F;
		GL11.glScalef(5.0F, 5.0F, 5.0F);
		GL11.glPopMatrix();
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
	}
}
