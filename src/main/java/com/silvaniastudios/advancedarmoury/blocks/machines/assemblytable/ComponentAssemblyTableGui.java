package com.silvaniastudios.advancedarmoury.blocks.machines.assemblytable;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

import com.silvaniastudios.advancedarmoury.AdvancedArmoury;
import com.silvaniastudios.advancedarmoury.blocks.machines.MachineGui;
import com.silvaniastudios.advancedarmoury.items.components.assault.AssaultReceiverCasing;
import com.silvaniastudios.advancedarmoury.items.components.generic.ItemComponent;
import com.silvaniastudios.advancedarmoury.items.components.generic.ReceiverCasing;
import com.silvaniastudios.advancedarmoury.items.components.lmg.LMGReceiverCasing;
import com.silvaniastudios.advancedarmoury.items.components.rifle.RifleReceiverCasing;
import com.silvaniastudios.advancedarmoury.items.components.smg.SMGReceiverCasing;
import com.silvaniastudios.advancedarmoury.network.GunBuildPacket;

import co.uk.silvania.rpgcore.RPGCore;
import co.uk.silvania.rpgcore.RPGUtils;
import co.uk.silvania.rpgcore.client.skillgui.MultiLineButton;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.ResourceLocation;

public class ComponentAssemblyTableGui extends MachineGui {
	
	private ComponentAssemblyTableEntity te;
	private static final ResourceLocation texture = new ResourceLocation(AdvancedArmoury.modid, "textures/gui/componenttable.png");
	
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
		button = new MultiLineButton(1, guiLeft + 95, guiTop + 93, 92, 16, "Build Component");
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
		System.out.println("Action performed");
		switch(button.id) {
		case 1:
			if (isComponentValid()) {
				System.out.println("Button pressed!");
				AdvancedArmoury.network.sendToServer(new GunBuildPacket(name.getText(), tag.getText(), "true", mc.thePlayer.getDisplayName(), x, y, z));
				name.setText("Receiver");
				tag.setText("");
			}
			break;
		}
	}
	
	@Override
	public void drawScreen(int mouseX, int mouseZ, float par3) {
		super.drawScreen(mouseX, mouseZ, par3);

		if (button.func_146115_a()) {
			Integer[] check = {2, 4, 5, 6, 7, 8, 9, 10, 11, 13};
			drawCustomHoveringText(componentStatReportA(), componentStatReportB(), mouseX, mouseZ, fontRendererObj, check);
		}
		List text = new ArrayList();
		
		if (mouseX >= guiLeft + 7 && mouseZ >= guiTop + 127 && mouseX <= guiLeft + 186 && mouseZ <= guiTop + 139) {
			text.add("Rename your component here!");
			
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
			if (Mouse.isButtonDown(0)) {
				name.setFocused(true);
				tag.setFocused(false);
			}
		}
		
		if (mouseX >= guiLeft + 7 && mouseZ >= guiTop + 142 && mouseX <= guiLeft + 186 && mouseZ <= guiTop + 154) {
			text.add("Add a custom tagline to your component here!");
			
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
			if (Mouse.isButtonDown(0)) {
				name.setFocused(false);
				tag.setFocused(true);
			}
		}
		
		if (te.getStackInSlot(1) == null) {
			if (mouseX >= guiLeft + 36 && mouseZ >= guiTop + 49 && mouseX <= guiLeft + 61 && mouseZ <= guiTop + 74) {
				text.add("Insert a Receiver Casing to begin!");
			}
		} else {
			if (te.getStackInSlot(1).getItem() instanceof AssaultReceiverCasing) {
				if (te.getStackInSlot(2) == null && mouseX >= guiLeft + 7  && mouseZ >= guiTop + 49 && mouseX <= guiLeft + 25 && mouseZ <= guiTop + 67)  { text.add("A: Large Bolt"); }
				if (te.getStackInSlot(3) == null && mouseX >= guiLeft + 7  && mouseZ >= guiTop + 71 && mouseX <= guiLeft + 25 && mouseZ <= guiTop + 89)  { text.add("B: Large Chamber"); }
				if (te.getStackInSlot(4) == null && mouseX >= guiLeft + 7  && mouseZ >= guiTop + 93 && mouseX <= guiLeft + 25 && mouseZ <= guiTop + 111) { text.add("C: Large Firing Pin"); }
				if (te.getStackInSlot(5) == null && mouseX >= guiLeft + 29 && mouseZ >= guiTop + 93 && mouseX <= guiLeft + 47 && mouseZ <= guiTop + 111) { text.add("D: Large Firing Mechanic"); }
				if (te.getStackInSlot(6) == null && mouseX >= guiLeft + 51 && mouseZ >= guiTop + 93 && mouseX <= guiLeft + 69 && mouseZ <= guiTop + 111) { text.add("E: Fire Selector \u00A7o(Optional)"); }
				if (te.getStackInSlot(7) == null && mouseX >= guiLeft + 73 && mouseZ >= guiTop + 93 && mouseX <= guiLeft + 91 && mouseZ <= guiTop + 111) { text.add("F: Charging Handle"); }
				if (te.getStackInSlot(8) == null && mouseX >= guiLeft + 73 && mouseZ >= guiTop + 71 && mouseX <= guiLeft + 91 && mouseZ <= guiTop + 89)  { text.add("G: Trigger"); }
			} else if (te.getStackInSlot(1).getItem() instanceof RifleReceiverCasing) {
				if (te.getStackInSlot(2) == null && mouseX >= guiLeft + 7  && mouseZ >= guiTop + 49 && mouseX <= guiLeft + 25 && mouseZ <= guiTop + 67)  { text.add("A: Large Bolt"); }
				if (te.getStackInSlot(3) == null && mouseX >= guiLeft + 7  && mouseZ >= guiTop + 71 && mouseX <= guiLeft + 25 && mouseZ <= guiTop + 89)  { text.add("B: Large Chamber"); }
				if (te.getStackInSlot(4) == null && mouseX >= guiLeft + 7  && mouseZ >= guiTop + 93 && mouseX <= guiLeft + 25 && mouseZ <= guiTop + 111) { text.add("C: Large Firing Pin"); }
				if (te.getStackInSlot(7) == null && mouseX >= guiLeft + 73 && mouseZ >= guiTop + 93 && mouseX <= guiLeft + 91 && mouseZ <= guiTop + 111) { text.add("F: Bolt Arm"); }
				if (te.getStackInSlot(8) == null && mouseX >= guiLeft + 73 && mouseZ >= guiTop + 71 && mouseX <= guiLeft + 91 && mouseZ <= guiTop + 89)  { text.add("G: Trigger"); }
			} else if (te.getStackInSlot(1).getItem() instanceof LMGReceiverCasing) {
				if (te.getStackInSlot(2) == null && mouseX >= guiLeft + 7  && mouseZ >= guiTop + 49 && mouseX <= guiLeft + 25 && mouseZ <= guiTop + 67)  { text.add("A: Large Bolt"); }
				if (te.getStackInSlot(3) == null && mouseX >= guiLeft + 7  && mouseZ >= guiTop + 71 && mouseX <= guiLeft + 25 && mouseZ <= guiTop + 89)  { text.add("B: Large Chamber"); }
				if (te.getStackInSlot(4) == null && mouseX >= guiLeft + 7  && mouseZ >= guiTop + 93 && mouseX <= guiLeft + 25 && mouseZ <= guiTop + 111) { text.add("C: Large Firing Pin"); }
				if (te.getStackInSlot(5) == null && mouseX >= guiLeft + 29 && mouseZ >= guiTop + 93 && mouseX <= guiLeft + 47 && mouseZ <= guiTop + 111) { text.add("D: Large Firing Mechanic"); }
				if (te.getStackInSlot(7) == null && mouseX >= guiLeft + 73 && mouseZ >= guiTop + 93 && mouseX <= guiLeft + 91 && mouseZ <= guiTop + 111) { text.add("F: Charging Handle"); }
				if (te.getStackInSlot(8) == null && mouseX >= guiLeft + 73 && mouseZ >= guiTop + 71 && mouseX <= guiLeft + 91 && mouseZ <= guiTop + 89)  { text.add("G: Trigger"); }
			} else if (te.getStackInSlot(1).getItem() instanceof SMGReceiverCasing) {
				if (te.getStackInSlot(2) == null && mouseX >= guiLeft + 7  && mouseZ >= guiTop + 49 && mouseX <= guiLeft + 25 && mouseZ <= guiTop + 67)  { text.add("A: Small Bolt"); }
				if (te.getStackInSlot(3) == null && mouseX >= guiLeft + 7  && mouseZ >= guiTop + 71 && mouseX <= guiLeft + 25 && mouseZ <= guiTop + 89)  { text.add("B: Small Chamber"); }
				if (te.getStackInSlot(4) == null && mouseX >= guiLeft + 7  && mouseZ >= guiTop + 93 && mouseX <= guiLeft + 25 && mouseZ <= guiTop + 111) { text.add("C: Small Firing Pin"); }
				if (te.getStackInSlot(5) == null && mouseX >= guiLeft + 29 && mouseZ >= guiTop + 93 && mouseX <= guiLeft + 47 && mouseZ <= guiTop + 111) { text.add("D: Small Firing Mechanic"); }
				if (te.getStackInSlot(6) == null && mouseX >= guiLeft + 51 && mouseZ >= guiTop + 93 && mouseX <= guiLeft + 69 && mouseZ <= guiTop + 111) { text.add("E: Fire Selector \u00A7o(Optional)"); }
				if (te.getStackInSlot(7) == null && mouseX >= guiLeft + 73 && mouseZ >= guiTop + 93 && mouseX <= guiLeft + 91 && mouseZ <= guiTop + 111) { text.add("F: Charging Handle"); }
				if (te.getStackInSlot(8) == null && mouseX >= guiLeft + 73 && mouseZ >= guiTop + 71 && mouseX <= guiLeft + 91 && mouseZ <= guiTop + 89)  { text.add("G: Trigger"); }
			}
		}

		if (mouseX >= guiLeft + 7 && mouseZ >= guiTop + 115 && mouseX <= guiLeft + 186 && mouseZ <= guiTop + 124) {
			if (te.clientBuildProgress > 0) {
				List progressList = Arrays.asList("Progress: " + te.clientBuildProgress + "/" + buildTime(te.getStackInSlot(1)));
				drawHoveringText(progressList, mouseX, mouseZ, fontRendererObj);
			}
		}
		if (text.size() > 0) {
			drawHoveringText(text, mouseX, mouseZ, fontRendererObj);
		}
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int par1, int par2) {
		name.drawTextBox();
		tag.drawTextBox();
		
		fontRendererObj.drawString("Parts: ", 55, 8, 4210752);
		fontRendererObj.drawString("" + te.partsValue, 55, 19, 4210752);
		
		if (te.getStackInSlot(1) != null) {			
			if (te.getStackInSlot(1).getItem() instanceof AssaultReceiverCasing) {
				String cost = "" + partCost(te.getStackInSlot(1));
				String time = "" + buildTime(te.getStackInSlot(1));
				fontRendererObj.drawString("Part Cost:", 95, 8, 4210752);
				fontRendererObj.drawString(cost, 189-fontRendererObj.getStringWidth(cost), 8, 4210752);
				fontRendererObj.drawString("Build Time:", 95, 19, 4210752);
				fontRendererObj.drawString(time, 189-fontRendererObj.getStringWidth(time), 19, 4210752);
			} else if (te.getStackInSlot(1).getItem() instanceof RifleReceiverCasing) {
				
			} else if (te.getStackInSlot(1).getItem() instanceof SMGReceiverCasing) {
				
			} else if (te.getStackInSlot(1).getItem() instanceof LMGReceiverCasing) {
				
			}
		}
		
		fontRendererObj.drawString("Durability: ", 95, 49, 4210752);
		fontRendererObj.drawString("Weight: ",     95, 60, 4210752);
		fontRendererObj.drawString("RPS Base: ",   95, 71, 4210752);
		fontRendererObj.drawString("Power Base: ", 95, 82, 4210752);
		
		String dura = "" + te.calculateDurability();
		String weight = "" + te.calculateWeight();
		String rps = "" + te.calculateFireRate();
		String power = "" + te.calculatePower();
		
		fontRendererObj.drawString(dura,   189-fontRendererObj.getStringWidth(dura),   49, 4210752);
		fontRendererObj.drawString(weight, 189-fontRendererObj.getStringWidth(weight), 60, 4210752);
		fontRendererObj.drawString(rps,    189-fontRendererObj.getStringWidth(rps),    71, 4210752);
		fontRendererObj.drawString(power,  189-fontRendererObj.getStringWidth(power),  82, 4210752);
		
		fontRendererObj.getStringWidth("");
		
		//TODO bar does not fill all the way. Test and fix.
		GL11.glPushMatrix();
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		GL11.glDisable(GL11.GL_LIGHTING);
		Minecraft.getMinecraft().renderEngine.bindTexture(texture);
		double buildTime = buildTime(te.getStackInSlot(1));
		double progress = te.clientBuildProgress;
		if (progress > buildTime) { progress = buildTime; }
		if (buildTime == 0) { buildTime = 1; } //Prevent crashes when build time is zero.
		int val = (int) Math.round((progress / buildTime) * 140);
		if (val > 140) { val = 140; }
		drawTexturedModalRect(8, 116, 0, 241, val, 8);
		GL11.glPopMatrix();
		
		if (isComponentValid()) {
			button.enabled = true;
		} else {
			button.enabled = false;
		}
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int p_146976_2_, int p_146976_3_) {
		GL11.glColor4f(1, 1, 1, 1);
		Minecraft.getMinecraft().renderEngine.bindTexture(texture);
		drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
		
		if (te.getStackInSlot(1) != null) {
			drawTexturedModalRect(guiLeft + 36, guiTop + 49, 230, 230, 26, 26);
			
			if (te.getStackInSlot(1).getItem() instanceof AssaultReceiverCasing) {
				drawTexturedModalRect(guiLeft +  7, guiTop + 49, 194, 238, 18, 18);
				drawTexturedModalRect(guiLeft +  7, guiTop + 71, 194, 238, 18, 18);
				drawTexturedModalRect(guiLeft +  7, guiTop + 93, 194, 238, 18, 18);
				drawTexturedModalRect(guiLeft + 29, guiTop + 93, 194, 238, 18, 18);
				drawTexturedModalRect(guiLeft + 51, guiTop + 93, 212, 238, 18, 18);
				drawTexturedModalRect(guiLeft + 73, guiTop + 93, 194, 238, 18, 18);
				drawTexturedModalRect(guiLeft + 73, guiTop + 71, 194, 238, 18, 18);
			} else if (te.getStackInSlot(1).getItem() instanceof RifleReceiverCasing) {
				drawTexturedModalRect(guiLeft +  7, guiTop + 49, 194, 238, 18, 18);
				drawTexturedModalRect(guiLeft +  7, guiTop + 71, 194, 238, 18, 18);
				drawTexturedModalRect(guiLeft +  7, guiTop + 93, 194, 238, 18, 18);
				drawTexturedModalRect(guiLeft + 29, guiTop + 93, 29,  93,  18, 18);
				drawTexturedModalRect(guiLeft + 51, guiTop + 93, 29,  93,  18, 18);
				drawTexturedModalRect(guiLeft + 73, guiTop + 93, 194, 238, 18, 18);
				drawTexturedModalRect(guiLeft + 73, guiTop + 71, 194, 238, 18, 18);
			} else if (te.getStackInSlot(1).getItem() instanceof LMGReceiverCasing) {
				drawTexturedModalRect(guiLeft +  7, guiTop + 49, 194, 238, 18, 18);
				drawTexturedModalRect(guiLeft +  7, guiTop + 71, 194, 238, 18, 18);
				drawTexturedModalRect(guiLeft +  7, guiTop + 93, 194, 238, 18, 18);
				drawTexturedModalRect(guiLeft + 29, guiTop + 93, 194, 238, 18, 18);
				drawTexturedModalRect(guiLeft + 51, guiTop + 93, 29,  93,  18, 18);
				drawTexturedModalRect(guiLeft + 73, guiTop + 93, 194, 238, 18, 18);
				drawTexturedModalRect(guiLeft + 73, guiTop + 71, 194, 238, 18, 18);
			} else if (te.getStackInSlot(1).getItem() instanceof SMGReceiverCasing) {
				drawTexturedModalRect(guiLeft +  7, guiTop + 49, 194, 238, 18, 18);
				drawTexturedModalRect(guiLeft +  7, guiTop + 71, 194, 238, 18, 18);
				drawTexturedModalRect(guiLeft +  7, guiTop + 93, 194, 238, 18, 18);
				drawTexturedModalRect(guiLeft + 29, guiTop + 93, 194, 238, 18, 18);
				drawTexturedModalRect(guiLeft + 51, guiTop + 93, 212, 238, 18, 18);
				drawTexturedModalRect(guiLeft + 73, guiTop + 93, 194, 238, 18, 18);
				drawTexturedModalRect(guiLeft + 73, guiTop + 71, 194, 238, 18, 18);
			}
		}
		
		if (te.getStackInSlot(2) != null) { drawTexturedModalRect(guiLeft +  7, guiTop + 49, 212, 238, 18, 18); }
		if (te.getStackInSlot(3) != null) { drawTexturedModalRect(guiLeft +  7, guiTop + 71, 212, 238, 18, 18); }
		if (te.getStackInSlot(4) != null) { drawTexturedModalRect(guiLeft +  7, guiTop + 93, 212, 238, 18, 18); }
		if (te.getStackInSlot(5) != null) { drawTexturedModalRect(guiLeft + 29, guiTop + 93, 212, 238, 18, 18); }
		if (te.getStackInSlot(7) != null) { drawTexturedModalRect(guiLeft + 73, guiTop + 93, 212, 238, 18, 18); }
		if (te.getStackInSlot(8) != null) { drawTexturedModalRect(guiLeft + 73, guiTop + 71, 212, 238, 18, 18); }
	}
	
	public boolean isComponentValid() {
		boolean afford = te.partsValue >= partCost(te.getStackInSlot(1)) && te.partsValue > 0 ? true : false;
		boolean s2 = false;
		boolean s3 = false;
		boolean s4 = false;
		boolean s5 = false;
		boolean s7 = false;
		boolean s8 = false;
		
		if (te.getStackInSlot(1) != null && te.getStackInSlot(1).getItem() instanceof ReceiverCasing) {
			if (te.getStackInSlot(2) != null) { if (te.getStackInSlot(2).getItem() instanceof ItemComponent) { s2 = true; }}
			if (te.getStackInSlot(3) != null) { if (te.getStackInSlot(3).getItem() instanceof ItemComponent) { s3 = true; }}
			if (te.getStackInSlot(4) != null) { if (te.getStackInSlot(4).getItem() instanceof ItemComponent) { s4 = true; }}
			if (te.getStackInSlot(5) != null) { if (te.getStackInSlot(5).getItem() instanceof ItemComponent) { s5 = true; }}
			if (te.getStackInSlot(7) != null) { if (te.getStackInSlot(7).getItem() instanceof ItemComponent) { s7 = true; }}
			if (te.getStackInSlot(8) != null) { if (te.getStackInSlot(8).getItem() instanceof ItemComponent) { s8 = true; }}
		}
		return s2 && s3 && s4 && s5 && s7 && s8 && afford;
	}
	
	private List componentStatReportA() {
		boolean afford = te.partsValue >= partCost(te.getStackInSlot(1)) && te.partsValue > 0 ? true : false;

		List result = new ArrayList();

		EnumChatFormatting b = EnumChatFormatting.BLACK;
		result.add("Listing Component Report:");
		result.add("");
		result.add("Casing Status:");
		result.add("");
		result.add("Bolt Status:");
		result.add("Chamber Status:");
		result.add("Firing Pin Status:");
		result.add("Firing Mechanism Status:");
		result.add("Fire Selector Status:");
		result.add("Charging Handle Status:");
		result.add("Trigger Status:");
		result.add("");
		result.add("Parts:");
		if (!afford) {
			result.add(EnumChatFormatting.DARK_RED + "You need more Parts to build this component.");
		} else {
			result.add(EnumChatFormatting.DARK_GREEN + "You can afford to build this component!");
		}

		return result;
	}
	
	private List componentStatReportB() {
		List result = new ArrayList();

		EnumChatFormatting b = EnumChatFormatting.BLACK;
		result.add("");
		result.add("");
		result.add(parseBool(te.getStackInSlot(1) != null ? true : false));
		result.add("");
		result.add(parseBool(te.getStackInSlot(2) != null ? true : false));
		result.add(parseBool(te.getStackInSlot(3) != null ? true : false));
		result.add(parseBool(te.getStackInSlot(4) != null ? true : false));
		result.add(parseBool(te.getStackInSlot(5) != null ? true : false));
		result.add(parseBool(te.getStackInSlot(6) != null ? true : false) + " \u00A77(Optional)");
		result.add(parseBool(te.getStackInSlot(7) != null ? true : false));
		result.add(parseBool(te.getStackInSlot(8) != null ? true : false));
		result.add("");
		result.add(te.partsValue + "/" + partCost(te.getStackInSlot(1)));
		result.add(EnumChatFormatting.DARK_RED + "");

		return result;
	}
}