package co.uk.silvania.advancedarmoury.blocks.machines.encasementtable.assault;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import co.uk.silvania.advancedarmoury.AdvancedArmoury;
import co.uk.silvania.advancedarmoury.items.components.generic.GunFrame;
import co.uk.silvania.advancedarmoury.items.components.generic.ItemComponent;
import co.uk.silvania.advancedarmoury.network.GunBuildPacket;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.ResourceLocation;

public class AssaultEncasementTableGui extends GuiContainer {

	private AssaultEncasementTableEntity te;
	public GuiButton button;
	private GuiTextField text;
	
	int x;
	int y;
	int z;
	
	public int buildProgress;
	
	public AssaultEncasementTableGui(InventoryPlayer player, AssaultEncasementTableEntity tile) {
		super(new AssaultEncasementTableContainer(player, tile));
		
		te = tile;
		
		xSize = 176;
		ySize = 210;
		
		x = te.xCoord;
		y = te.yCoord;
		z = te.zCoord;
		buildProgress = te.buildProgress;
	}
	
	private static final ResourceLocation texture = new ResourceLocation("advancedarmoury", "textures/gui/encasementtable.png");
	
	@Override
	public void initGui() {
		super.initGui();
		button = new GuiButton(1, guiLeft + 101, guiTop + 20, 68, 20, "Build Gun");
		text = new GuiTextField(this.fontRendererObj, 9, 97, 138, 10);
		text.setMaxStringLength(80);
		text.setEnableBackgroundDrawing(false);
		text.setTextColor(Color.WHITE.getRGB());
		if (te != null) {
			if (te.gunTag != null) {
				if (!te.gunTag.isEmpty()) {
					text.setText(te.gunTag);
				}
			}
		}
		text.setFocused(true);
		
		buttonList.add(button);
	}
	
	protected void keyTyped(char par1, int keyId) {
		text.textboxKeyTyped(par1, keyId);
		if (keyId == Keyboard.KEY_ESCAPE) {
			AdvancedArmoury.network.sendToServer(new GunBuildPacket(text.getText(), "false", mc.thePlayer.getDisplayName(), x, y, z)); //Sync gun's name to server when closing GUI.
		}
		if (!(keyId == Keyboard.KEY_E && this.text.isFocused())) {
			super.keyTyped(par1, keyId); //Don't pass super if key is E, because it closes the container.
		}
	}
	
	public void updateScreen() {
		super.updateScreen();
		text.updateCursorCounter();
	}
	
	public void actionPerformed(GuiButton button) {
		switch(button.id) {
		case 1:
			if (isGunValid()) {
				AdvancedArmoury.network.sendToServer(new GunBuildPacket(text.getText(), "true", mc.thePlayer.getDisplayName(), x, y, z));
				text.setText("");
			}
			break;
		}
	}
	
	@Override
	public void drawScreen(int mouseX, int mouseZ, float par3) {
		super.drawScreen(mouseX, mouseZ, par3);

		if (button.func_146115_a()) {
			drawHoveringText(gunStatReport(), mouseX, mouseZ, fontRendererObj);
		}
		
		if (mouseX >= guiLeft + 8 && mouseZ >= guiTop + 96 && mouseX <= guiLeft + 167 && mouseZ <= guiTop + 106) {
			String[] text = {"Add a tag to your gun here!", "Tags can say anything you like.",
					"Supports colour formatting via the & symbol.", 
					"\u00A70&0   \u00A71&1   \u00A72&2   \u00A73&3   \u00A74&4   \u00A75&5   \u00A76&6   \u00A77&7", 
					"\u00A78&8   \u00A79&9   \u00A7a&a   \u00A7b&b   \u00A7c&c   \u00A7d&d   \u00A7e&e   \u00A7f&f",
					"\u00A7r&k (\u00A7kKKK\u00A7r)   \u00A7l&l (Bold)\u00A7r   \u00A7m&m (Strike)",
					"\u00A7r\u00A7n&n (Underlined)\u00A7r   \u00A7o&o (Italics)",
					"Formats should go \u00A7oafter\u00A7r colours. &r will reset."};
			List temp = Arrays.asList(text);
			drawHoveringText(temp, mouseX, mouseZ, fontRendererObj);
		}
			
		if (mouseX >= guiLeft + 8 && mouseZ >= guiTop + 114 && mouseX <= guiLeft + 167 && mouseZ <= guiTop + 121) {
			if (te.clientBuildProgress > 0) {
				List progressList = Arrays.asList("Progress: " + te.clientBuildProgress + "/" + totalBuildTime());
				drawHoveringText(progressList, mouseX, mouseZ, fontRendererObj);
			}
		}
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int par1, int par2) {
		text.drawTextBox();
		fontRendererObj.drawString("Assault Rifle Encasement Table", 7, 6, 4210752);
		fontRendererObj.drawString("Build Time: " + totalBuildTime(), 8, 76, 4210752);
		fontRendererObj.drawString("Parts Cost: " + partsCost(), 8, 85, 4210752);
		
		fontRendererObj.drawString("Parts: ", 59, 21, 4210752);
		fontRendererObj.drawString("" + te.partsValue, 59, 32, 4210752);
		

		GL11.glPushMatrix();
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		GL11.glDisable(GL11.GL_LIGHTING);
		Minecraft.getMinecraft().renderEngine.bindTexture(texture);
		double buildTime = te.buildTime;
		double progress = te.clientBuildProgress;
		if (progress > buildTime) { progress = buildTime; }
		if (buildTime == 0) { buildTime = 1; } //Prevent crashes when build time is zero.
		int val = (int) Math.round((progress / buildTime) * 160);
		if (val > 160) { val = 160; }
		drawTexturedModalRect(8, 114, 0, 210, val, 8);
		GL11.glPopMatrix();
		
		if (isGunValid()) {
			button.enabled = true;
		} else {
			button.enabled = false;
		}
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3) {
		GL11.glColor4f(1, 1, 1, 1);
		Minecraft.getMinecraft().renderEngine.bindTexture(texture);
		drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
		
		//Mask letters when items are in the slots
		if (te.getStackInSlot(0) != null) {
			if (te.getStackInSlot(0).getItem() instanceof GunFrame) {
				GunFrame gunFrame = (GunFrame) te.getStackInSlot(0).getItem();
				if (gunFrame.hasInternals(te.getStackInSlot(0))) {
					drawTexturedModalRect(guiLeft + 97, guiTop + 46, 176, 36, 26, 26);
				}
			}
		}
		
		if (te.getStackInSlot(2) != null) { drawTexturedModalRect(guiLeft + 31, guiTop + 50, 176, 0, 18, 18); }
		if (te.getStackInSlot(3) != null) { drawTexturedModalRect(guiLeft + 53, guiTop + 50, 176, 0, 18, 18); }
		if (te.getStackInSlot(4) != null) { drawTexturedModalRect(guiLeft + 75, guiTop + 50, 176, 0, 18, 18); }
	}
	
	private int totalBuildTime() {
		int total = 0;
		for (int i = 2; i <= 5; i++) {
			ItemStack itemPart = te.getStackInSlot(i);
			if (itemPart != null) {
				if (itemPart.getItem() instanceof ItemComponent) {
					ItemComponent component = (ItemComponent) itemPart.getItem();
					total = total + component.buildTime;
				}
			}
		}
		return total;		
	}
	
	private int partsCost() {
		int cost = 0;
		for (int i = 2; i <= 5; i++) {
			ItemStack itemComponent = te.getStackInSlot(i);
			if (itemComponent != null) {
				if (itemComponent.getItem() instanceof ItemComponent) {
					ItemComponent component = (ItemComponent) itemComponent.getItem();
					cost = cost + component.cost;
				}
			}
		}
		return cost;
	}
	
	public boolean afford() {
		if (te.partsValue >= partsCost()) {
			return true;
		}
		return false;
	}

	private boolean isGunValid() {
		boolean slot1 = te.getStackInSlot(2) != null;
		boolean slot2 = te.getStackInSlot(3) != null;
		boolean slot3 = te.getStackInSlot(4) != null;
		
		boolean frame = te.getStackInSlot(0) != null;
		
		if (frame) {
			if (te.getStackInSlot(0).getItem() instanceof GunFrame) {
				GunFrame gunFrame = (GunFrame) te.getStackInSlot(0).getItem();
				if (!(gunFrame.hasInternals(te.getStackInSlot(0)))) {
					frame = false;
				}
			}
		}
	
		if (slot1 && slot2 && slot3 && frame && afford()) {
			return true;
		}
		return false;
	}
	
	private List gunStatReport() {
		boolean slot1 = te.getStackInSlot(2) != null;
		boolean slot2 = te.getStackInSlot(3) != null;
		boolean slot3 = te.getStackInSlot(4) != null;
		
		boolean frame = false;

		if (te.getStackInSlot(0) != null) {
			if (te.getStackInSlot(0).getItem() instanceof GunFrame) {
				GunFrame gunFrame = (GunFrame) te.getStackInSlot(0).getItem();
				if (gunFrame.hasInternals(te.getStackInSlot(0))) {
					frame = true;
				}
			}
		}

		List result = new ArrayList();

		EnumChatFormatting b = EnumChatFormatting.BLACK;
		result.add("Listing Weapon Report:");
		result.add("");
		result.add("Receiver Status: " + b + "ll" + parseBool(slot1));
		result.add("Stock Status:      " + b + "l" + parseBool(slot2));	
		result.add("Front End Status: " + parseBool(slot3));
		result.add("Frame Status:      " + parseBool(frame));
		if (!frame && te.getStackInSlot(0) != null) {
			result.add("");
			result.add("Frame has no internals. Please use the Assembly Table first.");
		}
		
		if (!afford()) {
			result.add("");
			result.add(EnumChatFormatting.DARK_RED + "You need more Parts to build this weapon.");
		}

		return result;
	}
	
	private String parseBool(boolean bool) {
		if (bool) {
			return EnumChatFormatting.DARK_GREEN + "Ready to Install";
		} else 
			return EnumChatFormatting.DARK_RED + "Missing";
	}
}
