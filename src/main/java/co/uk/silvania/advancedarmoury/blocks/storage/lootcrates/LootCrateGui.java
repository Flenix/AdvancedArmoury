package co.uk.silvania.advancedarmoury.blocks.storage.lootcrates;

import java.awt.Color;
import java.util.Arrays;
import java.util.List;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import co.uk.silvania.advancedarmoury.AdvancedArmoury;
import co.uk.silvania.advancedarmoury.blocks.machines.SlotFrame;
import co.uk.silvania.advancedarmoury.network.GunBuildPacket;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.util.ResourceLocation;

public class LootCrateGui extends GuiContainer {

	private LootCrateEntity te;
	//TODO private GuiTextField text;
	private static final ResourceLocation texture = new ResourceLocation("advancedarmoury", "textures/gui/lootcrate.png");
	
	public LootCrateGui(InventoryPlayer player, LootCrateEntity tileEntity) {
		super(new LootCrateContainer(player, tileEntity));
		te = tileEntity;
		
		xSize = 176;
		ySize = 160;
	}
	
	@Override
	public void initGui() {
		super.initGui();/* TODO
		text = new GuiTextField(this.fontRendererObj, 91, 62, 76, 9);
		text.setMaxStringLength(8);
		text.setEnableBackgroundDrawing(false);
		text.setTextColor(Color.WHITE.getRGB());
		text.setFocused(true);*/
	}
	
	@Override
	protected void keyTyped(char par1, int keyId) {
		System.out.println("Key ID: " + keyId);
		if (keyId >= 2 && keyId <= 11) { //KEY_1 - KEY_0
			//TODO text.textboxKeyTyped(par1, keyId);
		}
		if (keyId == Keyboard.KEY_ESCAPE || keyId == Keyboard.KEY_E) {
			//TODO new packet AdvancedArmoury.network.sendToServer(new GunBuildPacket(text.getText(), "false", x, y, z)); //send data to server when closing GUI.
		}
		super.keyTyped(par1, keyId);
	}
	
	public void updateScreen() {
		super.updateScreen();
		//TODO text.updateCursorCounter();
	}
	
	@Override
	public void drawScreen(int mouseX, int mouseZ, float par3) {
		super.drawScreen(mouseX, mouseZ, par3);
		
		if (mouseX >= guiLeft + 151 && mouseZ >= guiTop + 15 && mouseX <= guiLeft + 168 && mouseZ <= guiTop + 32) {
			String[] text = {"Crate Lock",
							"Place any item in here to lock the Crate.",
							"When looting, it must be unlocked and requires", 
							"a key in order to access it. The key must be an", 
							"identical clone. Use middle click to clone", 
							"your key, place one copy here, and another",
							"wherever you want to hide it (IE, an unlocked",
							"Loot Crate elsewhere)"};
			List temp = Arrays.asList(text);
			drawHoveringText(temp, mouseX, mouseZ, fontRendererObj);
		}
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int par1, int par2) {
		//TODO text.drawTextBox();
		fontRendererObj.drawString(te.getInventoryName(), 7, 6, 4210752);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3) {
		GL11.glColor4f(1, 1, 1, 1);
		Minecraft.getMinecraft().renderEngine.bindTexture(texture);
		drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
		
		int a = te.getSizeInventory();
		
		drawTexturedModalRect(guiLeft + 151, guiTop + 15, 176, 44, 18, 18);
		
		for (int i = 0; i < a-1; i++) {
			if (i == 0)  { drawTexturedModalRect(guiLeft + 7,  guiTop + 15, 176, 44, 18, 18); };
			if (i == 1)  { drawTexturedModalRect(guiLeft + 27, guiTop + 15, 176, 44, 18, 18); };
			if (i == 2)  { drawTexturedModalRect(guiLeft + 47, guiTop + 15, 176, 44, 18, 18); };
			if (i == 3)  { drawTexturedModalRect(guiLeft + 67, guiTop + 15, 176, 44, 18, 18); };
			if (i == 4)  { drawTexturedModalRect(guiLeft + 7,  guiTop + 35, 176, 44, 18, 18); };
			if (i == 5)  { drawTexturedModalRect(guiLeft + 27, guiTop + 35, 176, 44, 18, 18); };
			if (i == 6)  { drawTexturedModalRect(guiLeft + 47, guiTop + 35, 176, 44, 18, 18); };
			if (i == 7)  { drawTexturedModalRect(guiLeft + 67, guiTop + 35, 176, 44, 18, 18); };
			if (i == 8)  { drawTexturedModalRect(guiLeft + 7,  guiTop + 55, 176, 44, 18, 18); };
			if (i == 9)  { drawTexturedModalRect(guiLeft + 27, guiTop + 55, 176, 44, 18, 18); };
			if (i == 10) { drawTexturedModalRect(guiLeft + 47, guiTop + 55, 176, 44, 18, 18); };
			if (i == 11) { drawTexturedModalRect(guiLeft + 67, guiTop + 55, 176, 44, 18, 18); };
		}
	}

}
