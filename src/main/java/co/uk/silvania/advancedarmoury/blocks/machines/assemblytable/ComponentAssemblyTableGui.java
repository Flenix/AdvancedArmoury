package co.uk.silvania.advancedarmoury.blocks.machines.assemblytable;

import java.awt.Color;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import co.uk.silvania.advancedarmoury.AdvancedArmoury;
import co.uk.silvania.advancedarmoury.items.components.assault.AssaultReceiverFrame;
import co.uk.silvania.advancedarmoury.items_old.components.generic.assault.ItemAssaultComponent;
import co.uk.silvania.advancedarmoury.network.GunBuildPacket;
import co.uk.silvania.rpgcore.client.skillgui.MultiLineButton;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

public class ComponentAssemblyTableGui extends GuiContainer {
	
	private ComponentAssemblyTableEntity te;
	private static final ResourceLocation texture = new ResourceLocation("advancedarmoury", "textures/gui/componenttable.png");
	
	int x;
	int y;
	int z;
	
	public int buildProgress;
	public MultiLineButton button;
	private GuiTextField name;
	private GuiTextField tag;
	
	public ComponentAssemblyTableGui(InventoryPlayer player, ComponentAssemblyTableEntity tile) {
		super(new ComponentAssemblyTableContainer(player, tile));
		te = tile;
		
		xSize = 194;
		ySize = 241;
		
		x = te.xCoord;
		y = te.yCoord;
		z = te.zCoord;
		buildProgress = te.buildProgress;
	}

	@Override
	public void initGui() {
		super.initGui();
		button = new MultiLineButton(1, guiLeft + 95, guiTop + 93, 92, 16, "Build Gun");
		name = new GuiTextField(this.fontRendererObj, 9, 129, 158, 10);
		tag = new GuiTextField(this.fontRendererObj, 9, 144, 158, 10);
		
		name.setMaxStringLength(80);
		name.setEnableBackgroundDrawing(false);
		name.setTextColor(Color.WHITE.getRGB());
		
		tag.setMaxStringLength(80);
		tag.setEnableBackgroundDrawing(false);
		tag.setTextColor(Color.WHITE.getRGB());
		
		if (te != null) {
			if (!te.componentName.isEmpty()) {
				name.setText(te.componentName);
			} else {
				name.setText("Receiver");
			}
			if (!te.componentTag.isEmpty()) {
				tag.setText(te.componentTag);
			} else {
				tag.setText("");
			}
		} else {
			name.setText("Receiver");
			tag.setText("");
		}
		name.setFocused(true);
		
		buttonList.add(button);
	}
	
	@Override
	protected void keyTyped(char par1, int keyId) {
		if (name.isFocused()) {
			name.textboxKeyTyped(par1, keyId);
		} else if (tag.isFocused()) {
			tag.textboxKeyTyped(par1, keyId);
		}
		
		if (keyId == Keyboard.KEY_ESCAPE) {
			AdvancedArmoury.network.sendToServer(new GunBuildPacket(name.getText(), tag.getText(), "false", mc.thePlayer.getDisplayName(), x, y, z)); //Sync gun's name to server when closing GUI.
		}
		if (!(keyId == Keyboard.KEY_E && (name.isFocused() || tag.isFocused()))) {
			super.keyTyped(par1, keyId); //Don't pass super if key is E, because it closes the container.
		}
	}
	
	public void updateScreen() {
		super.updateScreen();
		name.updateCursorCounter();
		tag.updateCursorCounter();
	}
	
	public void actionPerformed(GuiButton button) {
		switch(button.id) {
		case 1:
			if (isComponentValid()) {
				AdvancedArmoury.network.sendToServer(new GunBuildPacket(name.getText(), tag.getText(), "true", mc.thePlayer.getDisplayName(), x, y, z));
				name.setText("Receiver");
				tag.setText("");
			}
			break;
		}
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int par1, int par2) {
		name.drawTextBox();
		tag.drawTextBox();
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int p_146976_2_, int p_146976_3_) {
		GL11.glColor4f(1, 1, 1, 1);
		Minecraft.getMinecraft().renderEngine.bindTexture(texture);
		drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
		
		if (te.getStackInSlot(1) != null) {
			drawTexturedModalRect(guiLeft + 36, guiTop + 49, 230, 230, 26, 26);
			
			drawTexturedModalRect(guiLeft +  7, guiTop + 49, 194, 238, 18, 18);
			drawTexturedModalRect(guiLeft +  7, guiTop + 71, 194, 238, 18, 18);
			drawTexturedModalRect(guiLeft +  7, guiTop + 93, 194, 238, 18, 18);
			drawTexturedModalRect(guiLeft + 29, guiTop + 93, 194, 238, 18, 18);
			drawTexturedModalRect(guiLeft + 51, guiTop + 93, 212, 238, 18, 18);
			drawTexturedModalRect(guiLeft + 73, guiTop + 93, 194, 238, 18, 18);
			drawTexturedModalRect(guiLeft + 73, guiTop + 71, 194, 238, 18, 18);
			drawTexturedModalRect(guiLeft + 73, guiTop + 49, 194, 238, 18, 18);
		}
		
		if (te.getStackInSlot(2) != null) { drawTexturedModalRect(guiLeft +  7, guiTop + 49, 212, 238, 18, 18); }
		if (te.getStackInSlot(3) != null) { drawTexturedModalRect(guiLeft +  7, guiTop + 71, 212, 238, 18, 18); }
		if (te.getStackInSlot(4) != null) { drawTexturedModalRect(guiLeft +  7, guiTop + 93, 212, 238, 18, 18); }
		if (te.getStackInSlot(5) != null) { drawTexturedModalRect(guiLeft + 29, guiTop + 93, 212, 238, 18, 18); }
		if (te.getStackInSlot(7) != null) { drawTexturedModalRect(guiLeft + 73, guiTop + 93, 212, 238, 18, 18); }
		if (te.getStackInSlot(8) != null) { drawTexturedModalRect(guiLeft + 73, guiTop + 71, 212, 238, 18, 18); }
		if (te.getStackInSlot(9) != null) { drawTexturedModalRect(guiLeft + 73, guiTop + 49, 212, 238, 18, 18); }
	}
	
	public boolean isComponentValid() {
		boolean s2 = false;
		boolean s3 = false;
		boolean s4 = false;
		boolean s5 = false;
		boolean s7 = false;
		boolean s8 = false;
		boolean s9 = false;
		
		if (te.getStackInSlot(1).getItem() instanceof AssaultReceiverFrame) {
			if (te.getStackInSlot(2).getItem() instanceof ItemAssaultComponent) { s2 = true; }
			if (te.getStackInSlot(3).getItem() instanceof ItemAssaultComponent) { s3 = true; }
			if (te.getStackInSlot(4).getItem() instanceof ItemAssaultComponent) { s4 = true; }
			if (te.getStackInSlot(5).getItem() instanceof ItemAssaultComponent) { s5 = true; }
			if (te.getStackInSlot(7).getItem() instanceof ItemAssaultComponent) { s7 = true; }
			if (te.getStackInSlot(8).getItem() instanceof ItemAssaultComponent) { s8 = true; }
			if (te.getStackInSlot(9).getItem() instanceof ItemAssaultComponent) { s9 = true; }
		}
		return s2 && s3 && s4 && s5 && s7 && s8 && s9;
	}
}