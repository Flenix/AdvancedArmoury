package com.silvaniastudios.advancedarmoury.blocks.machines.assemblytable.assault;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

import com.silvaniastudios.advancedarmoury.AdvancedArmoury;
import com.silvaniastudios.advancedarmoury.blocks.machines.MachineGui;
import com.silvaniastudios.advancedarmoury.items.components.generic.Barrel;
import com.silvaniastudios.advancedarmoury.items.components.generic.ReceiverCasing;
import com.silvaniastudios.advancedarmoury.network.GunBuildPacket;

import co.uk.silvania.rpgcore.client.skillgui.MultiLineButton;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.ResourceLocation;

public class AssaultAssemblyTableGui extends MachineGui {

	private AssaultAssemblyTableEntity te;
	public MultiLineButton button;
	private GuiTextField name;
	private GuiTextField tag;
	
	int x;
	int y;
	int z;
	
	public int buildProgress;
	
	public AssaultAssemblyTableGui(InventoryPlayer player, AssaultAssemblyTableEntity tile) {
		super(new AssaultAssemblyTableContainer(player, tile));
		te = tile;
		
		xSize = 194;
		ySize = 241;
		
		x = te.xCoord;
		y = te.yCoord;
		z = te.zCoord;
		buildProgress = te.buildProgress;
	}
	
	private static final ResourceLocation texture = new ResourceLocation(AdvancedArmoury.modid, "textures/gui/assemblytable.png");
	
	@Override
	public void initGui() {
		super.initGui();
		button = new MultiLineButton(1, guiLeft + 89, guiTop + 95, 98, 16, "Build Gun");
		name = new GuiTextField(this.fontRendererObj, 9, 129, 158, 10);
		tag = new GuiTextField(this.fontRendererObj, 9, 144, 158, 10);
		
		name.setMaxStringLength(80);
		name.setEnableBackgroundDrawing(false);
		name.setTextColor(Color.WHITE.getRGB());
		
		tag.setMaxStringLength(80);
		tag.setEnableBackgroundDrawing(false);
		tag.setTextColor(Color.WHITE.getRGB());
		
		if (te != null) {
			if (!te.gunName.isEmpty()) {
				name.setText(te.gunName);
			} else {
				name.setText("Assault Rifle");
			}
		} else {
			name.setText("Assault Rifle");
		}
		tag.setText("");
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
			if (isGunValid()) {
				AdvancedArmoury.network.sendToServer(new GunBuildPacket(name.getText(), tag.getText(), "true", mc.thePlayer.getDisplayName(), x, y, z));
				name.setText("Assault Rifle");
				tag.setText("");
			}
			break;
		}
	}
	
	@Override
	public void drawScreen(int mouseX, int mouseZ, float par3) {
		super.drawScreen(mouseX, mouseZ, par3);

		if (button.func_146115_a()) {
			Integer[] check = {2, 3, 7, 8, 9, 10};
			drawCustomHoveringText(gunStatReportA(), gunStatReportB(), mouseX, mouseZ, fontRendererObj, check);
		}
		
		boolean drawProgressTooltip = false;
		
		if (mouseX >= guiLeft + 7 && mouseZ >= guiTop + 127 && mouseX <= guiLeft + 186 && mouseZ <= guiTop + 139) {
			List text = new ArrayList();
			text.add("Rename your gun here!");
			
			if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT)) {
				text.add("Supports colour formatting via the & symbol.");
				text.add("\u00A70&0   \u00A71&1   \u00A72&2   \u00A73&3   \u00A74&4   \u00A75&5   \u00A76&6   \u00A77&7");
				text.add("\u00A78&8   \u00A79&9   \u00A7a&a   \u00A7b&b   \u00A7c&c   \u00A7d&d   \u00A7e&e   \u00A7f&f");
				text.add("\u00A7r&k (\u00A7kKKK\u00A7r)   \u00A7l&l (Bold)\u00A7r   \u00A7m&m (Strike)");
				text.add("\u00A7r\u00A7n&n (Underlined)\u00A7r   \u00A7o&o (Italics)");
				text.add("Formats should go \u00A7oafter\u00A7r colours. &r will reset.");
			} else {
				text.add("\u00A7oHold Shift to see formatting help!");
			}
			drawHoveringText(text, mouseX, mouseZ, fontRendererObj);
			if (Mouse.isButtonDown(0)) {
				name.setFocused(true);
				tag.setFocused(false);
			}
		}
		
		if (mouseX >= guiLeft + 7 && mouseZ >= guiTop + 142 && mouseX <= guiLeft + 186 && mouseZ <= guiTop + 154) {
			List text = new ArrayList();
			text.add("Add a custom tagline to your gun here!");
			
			if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT)) {
				text.add("Supports colour formatting via the & symbol.");
				text.add("\u00A70&0   \u00A71&1   \u00A72&2   \u00A73&3   \u00A74&4   \u00A75&5   \u00A76&6   \u00A77&7");
				text.add("\u00A78&8   \u00A79&9   \u00A7a&a   \u00A7b&b   \u00A7c&c   \u00A7d&d   \u00A7e&e   \u00A7f&f");
				text.add("\u00A7r&k (\u00A7kKKK\u00A7r)   \u00A7l&l (Bold)\u00A7r   \u00A7m&m (Strike)");
				text.add("\u00A7r\u00A7n&n (Underlined)\u00A7r   \u00A7o&o (Italics)");
				text.add("Formats should go \u00A7oafter\u00A7r colours. &r will reset.");
			} else {
				text.add("\u00A7oHold Shift to see formatting help!");
			}
			drawHoveringText(text, mouseX, mouseZ, fontRendererObj);
			if (Mouse.isButtonDown(0)) {
				name.setFocused(false);
				tag.setFocused(true);
			}
		}
		
		if (mouseX >= guiLeft + 7 && mouseZ >= guiTop + 115 && mouseX <= guiLeft + 12 && mouseZ <= guiTop + 124) { 	drawProgressTooltip = true;}
		if (mouseX >= guiLeft + 13 && mouseZ >= guiTop + 121 && mouseX <= guiLeft + 38 && mouseZ <= guiTop + 124) {	drawProgressTooltip = true;}
		if (mouseX >= guiLeft + 39 && mouseZ >= guiTop + 115 && mouseX <= guiLeft + 186 && mouseZ <= guiTop + 124) {drawProgressTooltip = true;}
		
		if (drawProgressTooltip) {
			if (te.clientBuildProgress > 0) {
				List progressList = Arrays.asList("Progress: " + te.clientBuildProgress + "/" + buildTime(te.getStackInSlot(1)));
				drawHoveringText(progressList, mouseX, mouseZ, fontRendererObj);
			}
		}
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int par1, int par2) {
		name.drawTextBox();
		tag.drawTextBox();
		int width = fontRendererObj.getStringWidth("Assault Rifle Assembly Table");
		
		String title = "Assault Rifle Assembly Table";
		String bt = "Build Time: " + buildTime(te.getStackInSlot(1));
		String cost = "Cost: " + partCost(te.getStackInSlot(1));
		
		String dura = "DUR: " + totalDurability(2, 5, te);
		String weight = "WGT: " + totalWeight(2, 5, te);
		String acc = "ACC: " + totalAccuracy(2, 5, te);
		//TODO String frate = "FRT: " + fireRate(2, 5, te);
		//TODO String power = "PWR: " + power(2, 5, te);
		
		fontRendererObj.drawString(title, pos(title), 6, 4210752);
		fontRendererObj.drawString(bt, 89, 20, 4210752);
		fontRendererObj.drawString(cost, 89, 30, 4210752);	
		
		fontRendererObj.drawString(dura, 194, 20, 4210752);
		fontRendererObj.drawString(weight, 194, 30, 4210752);
		fontRendererObj.drawString(acc, 194, 40, 4210752);
		//TODO fontRendererObj.drawString(frate, 194, 50, 4210752);
		//TODO fontRendererObj.drawString(power, 194, 60, 4210752);
		
		fontRendererObj.drawString("Parts: ", 55, 8, 4210752);
		fontRendererObj.drawString("" + te.partsValue, 55, 17, 4210752);
		
		GL11.glPushMatrix();
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		GL11.glDisable(GL11.GL_LIGHTING);
		Minecraft.getMinecraft().renderEngine.bindTexture(texture);
		double buildTime = te.buildTime;
		double progress = te.clientBuildProgress;
		if (progress > buildTime) { progress = buildTime; }
		if (buildTime == 0) { buildTime = 1; } //Prevent crashes when build time is zero.
		int val = (int) Math.round((progress / buildTime) * 140);
		if (val > 140) { val = 140; }
		drawTexturedModalRect(8, 116, 0, 241, val, 8);
		GL11.glPopMatrix();
		
		if (isGunValid()) {
			button.enabled = true;
		} else {
			button.enabled = false;
		}
	}
	
	private int pos(String str) {
		return 249 - fontRendererObj.getStringWidth(str);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3) {
		GL11.glColor4f(1, 1, 1, 1);
		Minecraft.getMinecraft().renderEngine.bindTexture(texture);
		drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
		drawTexturedModalRect(guiLeft+xSize, guiTop, 194, 0, 62, 111);
		
		//Mask letters when items are in the slots
		if (te.getStackInSlot(1) != null) { drawTexturedModalRect(guiLeft + 13, guiTop + 95, 230, 230, 26, 26); }
		
		if (te.getStackInSlot(2) != null) { drawTexturedModalRect(guiLeft + 7,  guiTop + 75, 212, 238, 18, 18); }
		if (te.getStackInSlot(3) != null) { drawTexturedModalRect(guiLeft + 27, guiTop + 75, 212, 238, 18, 18); }
		if (te.getStackInSlot(4) != null) { drawTexturedModalRect(guiLeft + 47, guiTop + 75, 212, 238, 18, 18); }
		if (te.getStackInSlot(5) != null) { drawTexturedModalRect(guiLeft + 67, guiTop + 75, 212, 238, 18, 18); }

		//Check barrel and chamber match on calibre.
		ItemStack chamberStack = te.getStackInSlot(4);
		ItemStack barrelStack = te.getStackInSlot(2);
		
		if (chamberStack != null && barrelStack != null && !calibre()) {
			drawTexturedModalRect(guiLeft + 7,  guiTop + 75, 194, 238, 18, 18);
			drawTexturedModalRect(guiLeft + 47, guiTop + 75, 194, 238, 18, 18);
		}
	}
	
	private boolean frame()    { return te.getStackInSlot(1) != null; }
	private boolean frontEnd() { return te.getStackInSlot(3) != null; }
	private boolean stock()    { return te.getStackInSlot(5) != null; }
	
	private boolean barrel() {
		if (te.getStackInSlot(2) != null) {
			if (te.getStackInSlot(2).stackTagCompound != null) {
				int length = te.getStackInSlot(2).stackTagCompound.getInteger("length");
				if (length >= 8 && length <= 22) {
					return true;
				}
			}
		}
		return false;
	}
	
	private boolean receiver() {
		if (te.getStackInSlot(4) != null && te.getStackInSlot(4).stackTagCompound != null) {
			if (te.getStackInSlot(4).stackTagCompound.getBoolean("hasInternals")) {
				return true;
			}
		}
		return false;
	}
	
	
	
	private boolean calibre() {
		if (te.getStackInSlot(2) != null && te.getStackInSlot(4) != null) {
			ItemStack barrel = te.getStackInSlot(2);
			ItemStack chamber = te.getStackInSlot(4);
			if (chamber.getItem() instanceof ReceiverCasing && barrel.getItem() instanceof Barrel) {
				if (chamber != null && barrel.stackTagCompound != null) {
					if (chamber.stackTagCompound.getDouble("calibre") == barrel.stackTagCompound.getDouble("calibre")) {
						return true;
					}
				}
			}
		}
		return false;
	}
	
	private boolean isGunValid() {
		boolean frame = te.getStackInSlot(1) != null;
		boolean afford = te.partsValue >= partCost(te.getStackInSlot(1)) ? true : false;
		return frame() && barrel() && frontEnd() && receiver() && stock() && calibre() && afford;
	}
	
	private List gunStatReportA() {
		boolean validLength = true;
		String lengthError = "";
		
		if (te.getStackInSlot(2) != null) {
			if (te.getStackInSlot(2).stackTagCompound != null) {
				int length = te.getStackInSlot(2).stackTagCompound.getInteger("length");
				if (length < 8) {
					lengthError = "Configured barrel is too short.";
					validLength = false;
				} else if (length > 22) {
					lengthError = "Configured barrel is too long.";
					validLength = false;
				}
			}
		}
				
		boolean frame = te.getStackInSlot(1) != null;
		boolean modifier = te.getStackInSlot(6) != null;
		boolean afford = te.partsValue >= partCost(te.getStackInSlot(1)) ? true : false;

		List result = new ArrayList();

		EnumChatFormatting b = EnumChatFormatting.BLACK;
		result.add("Listing Weapon Report:");
		result.add("");
		result.add("Frame Status:");
		result.add("Barrel Status:");
		if (!calibre() && barrel()) {
			result.add(EnumChatFormatting.DARK_RED + "Barrel and Chamber calibre does not match.");
		} else {
			result.add(EnumChatFormatting.AQUA + "Calibres match.");
		}
		if (!validLength) {
			result.add(EnumChatFormatting.DARK_RED + lengthError);
		} else {
			result.add(EnumChatFormatting.AQUA + "Barrel length OK.");
		}
		result.add("");
		
		result.add("Front End Status:");
		result.add("Receiver Status:");
		result.add("Stock Status:");
		result.add("Modifier Core:");
		
		result.add("");
		if (!afford) {
			result.add(EnumChatFormatting.DARK_RED + "You need more Parts to build this weapon.");
		} else {
			result.add(EnumChatFormatting.DARK_GREEN + "You have enough Parts to build this weapon.");
		}

		return result;
	}
	
	private List gunStatReportB() {
		boolean validLength = true;
		String lengthError = "";
				
		boolean frame = te.getStackInSlot(1) != null;
		boolean modifier = te.getStackInSlot(6) != null;
		boolean afford = te.partsValue >= partCost(te.getStackInSlot(1)) ? true : false;

		List result = new ArrayList();

		EnumChatFormatting b = EnumChatFormatting.BLACK;
		result.add("");
		result.add("");
		result.add(parseBool(frame()));
		result.add(parseBool(barrel()));
		result.add("");
		result.add("");
		result.add("");
		
		result.add(parseBool(frontEnd()));
		result.add(parseBool(receiver()));
		result.add(parseBool(stock()));
		result.add(parseBool(modifier) + " \u00A77(Optional)");
		result.add("");

		return result;
	}
}
