package co.uk.silvania.advancedarmoury.blocks.machines.assemblytable.assault;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import co.uk.silvania.advancedarmoury.AdvancedArmoury;
import co.uk.silvania.advancedarmoury.items.components.generic.ItemBarrel;
import co.uk.silvania.advancedarmoury.items.components.generic.ItemComponent;
import co.uk.silvania.advancedarmoury.items.components.generic.assault.ItemAssaultChamber;
import co.uk.silvania.advancedarmoury.items.components.generic.assault.ItemAssaultFiringPart;
import co.uk.silvania.advancedarmoury.items.components.generic.assault.ItemAssaultGasFeed;
import co.uk.silvania.advancedarmoury.items.components.generic.assault.ItemAssaultPiston;
import co.uk.silvania.advancedarmoury.items.components.generic.assault.ItemAssaultPistonChamber;
import co.uk.silvania.advancedarmoury.items.components.generic.assault.ItemAssaultSpring;
import co.uk.silvania.advancedarmoury.network.GunBuildPacket;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.ResourceLocation;

public class AssaultAssemblyTableGui extends GuiContainer {

	private AssaultAssemblyTableEntity te;
	public GuiButton button;
	private GuiTextField text;
	
	int x;
	int y;
	int z;
	
	public int buildProgress;
	
	public AssaultAssemblyTableGui(InventoryPlayer player, AssaultAssemblyTableEntity tile) {
		super(new AssaultAssemblyTableContainer(player, tile));
		
		te = tile;
		
		xSize = 176;
		ySize = 241;
		
		x = te.xCoord;
		y = te.yCoord;
		z = te.zCoord;
		buildProgress = te.buildProgress;
	}
	
	private static final ResourceLocation texture = new ResourceLocation("advancedarmoury", "textures/gui/assemblytable.png");
	
	@Override
	public void initGui() {
		super.initGui();
		button = new GuiButton(1, guiLeft + 101, guiTop + 20, 68, 20, "Build Gun");
		text = new GuiTextField(this.fontRendererObj, 9, 143, 138, 10);
		text.setMaxStringLength(50);
		text.setEnableBackgroundDrawing(false);
		text.setTextColor(Color.WHITE.getRGB());
		if (te != null) {
			if (!te.gunName.isEmpty()) {
				text.setText(te.gunName);
			} else {
				text.setText("Assault Rifle");
			}
		} else {
			text.setText("Assault Rifle");
		}
		text.setFocused(true);
		
		buttonList.add(button);
	}
	
	@Override
	protected void keyTyped(char par1, int keyId) {
		text.textboxKeyTyped(par1, keyId);
		if (keyId == Keyboard.KEY_ESCAPE) {
			AdvancedArmoury.network.sendToServer(new GunBuildPacket(text.getText(), "false", x, y, z)); //Sync gun's name to server when closing GUI.
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
				AdvancedArmoury.network.sendToServer(new GunBuildPacket(text.getText(), "true", x, y, z));
				text.setText("Assault Rifle");
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
		
		boolean drawProgressTooltip = false;
		
		if (mouseX >= guiLeft + 8 && mouseZ >= guiTop + 142 && mouseX <= guiLeft + 147 && mouseZ <= guiTop + 152) {
			String[] text = {"Rename your gun here!", 
					"Supports colour formatting via the & symbol.", 
					"\u00A70&0   \u00A71&1   \u00A72&2   \u00A73&3   \u00A74&4   \u00A75&5   \u00A76&6   \u00A77&7", 
					"\u00A78&8   \u00A79&9   \u00A7a&a   \u00A7b&b   \u00A7c&c   \u00A7d&d   \u00A7e&e   \u00A7f&f",
					"\u00A7r&k (\u00A7kKKK\u00A7r)   \u00A7l&l (Bold)\u00A7r   \u00A7m&m (Strike)",
					"\u00A7r\u00A7n&n (Underlined)\u00A7r   \u00A7o&o (Italics)",
					"Formats should go \u00A7oafter\u00A7r colours. &r will reset."};
			List temp = Arrays.asList(text);
			drawHoveringText(temp, mouseX, mouseZ, fontRendererObj); // makes all that nice default tool tip box from vanilla minecraft :)
		}
		
		if (mouseX >= guiLeft + 8 && mouseZ >= guiTop + 128 && mouseX <= guiLeft + 12 && mouseZ <= guiTop + 135) { 	drawProgressTooltip = true;}
		if (mouseX >= guiLeft + 13 && mouseZ >= guiTop + 133 && mouseX <= guiLeft + 38 && mouseZ <= guiTop + 135) {	drawProgressTooltip = true;}
		if (mouseX >= guiLeft + 39 && mouseZ >= guiTop + 128 && mouseX <= guiLeft + 147 && mouseZ <= guiTop + 135) {drawProgressTooltip = true;}
		
		if (drawProgressTooltip == true) {
			if (te.clientBuildProgress > 0) {
				List progressList = Arrays.asList("Progress: " + te.clientBuildProgress + "/" + totalBuildTime());
				drawHoveringText(progressList, mouseX, mouseZ, fontRendererObj); // makes all that nice default tool tip box from vanilla minecraft :)
			}
		}
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int par1, int par2) {
		text.drawTextBox();
		fontRendererObj.drawString("Assault Rifle Assembly Table", 7, 6, 4210752);
		fontRendererObj.drawString("Build Time: " + totalBuildTime(), 89, 47, 4210752);
		fontRendererObj.drawString("Accuracy: " + totalAccuracy(), 89, 59, 4210752);
		fontRendererObj.drawString("Fire Rate: " + fireRate(), 89, 71, 4210752);
		fontRendererObj.drawString("Power: " + power(), 89, 83, 4210752);
		
		fontRendererObj.drawString("Parts Cost: ", 89, 107, 4210752);
		fontRendererObj.drawString("" + partsCost(), 89, 118, 4210752);
		
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
		int val = (int) Math.round((progress / buildTime) * 140);
		if (val > 140) { val = 140; }
		drawTexturedModalRect(8, 128, 0, 241, val, 8);
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
		if (te.getStackInSlot(0) != null) { drawTexturedModalRect(guiLeft + 13, guiTop + 107, 176, 36, 26, 26); }
		
		if (te.getStackInSlot(3) != null) { drawTexturedModalRect(guiLeft + 7, guiTop + 67, 176, 0, 18, 18); }
		if (te.getStackInSlot(4) != null) { drawTexturedModalRect(guiLeft + 27, guiTop + 67, 176, 0, 18, 18); }
		if (te.getStackInSlot(5) != null) { drawTexturedModalRect(guiLeft + 47, guiTop + 67, 176, 0, 18, 18); }
		if (te.getStackInSlot(6) != null) { drawTexturedModalRect(guiLeft + 67, guiTop + 67, 176, 0, 18, 18); }
		if (te.getStackInSlot(7) != null) { drawTexturedModalRect(guiLeft + 7, guiTop + 47, 176, 0, 18, 18); }
		if (te.getStackInSlot(8) != null) { drawTexturedModalRect(guiLeft + 27, guiTop + 47, 176, 0, 18, 18); }
		if (te.getStackInSlot(9) != null) { drawTexturedModalRect(guiLeft + 47, guiTop + 47, 176, 0, 18, 18); }
		if (te.getStackInSlot(10) != null) { drawTexturedModalRect(guiLeft + 67, guiTop + 47, 176, 0, 18, 18); }
		
		if (te.getStackInSlot(11) != null) { drawTexturedModalRect(guiLeft + 27, guiTop + 87, 176, 0, 18, 18); }
		if (te.getStackInSlot(12) != null) { drawTexturedModalRect(guiLeft + 47, guiTop + 87, 176, 0, 18, 18); }
		if (te.getStackInSlot(13) != null) { drawTexturedModalRect(guiLeft + 67, guiTop + 87, 176, 0, 18, 18); }
		
		if (te.getStackInSlot(14) != null) { drawTexturedModalRect(guiLeft + 7, guiTop + 87, 176, 0, 18, 18); }
		
		if (te.getStackInSlot(15) != null) { drawTexturedModalRect(guiLeft + 47, guiTop + 107, 176, 0, 18, 18); }
		if (te.getStackInSlot(16) != null) { drawTexturedModalRect(guiLeft + 67, guiTop + 107, 176, 0, 18, 18); }
		
		boolean internalsValid = false;
		
		if (te.getStackInSlot(11) != null && te.getStackInSlot(12) != null) {
			if (te.getStackInSlot(11).getItem() instanceof ItemAssaultFiringPart && te.getStackInSlot(12).getItem() instanceof ItemAssaultGasFeed) {
				if (te.getStackInSlot(13) == null) {
					internalsValid = true;
				}
			} else {
				if (te.getStackInSlot(13) != null) {
					if (te.getStackInSlot(11).getItem() instanceof ItemAssaultPistonChamber && te.getStackInSlot(12).getItem() instanceof ItemAssaultSpring && te.getStackInSlot(13).getItem() instanceof ItemAssaultPiston) {
						internalsValid = true;
					}
				}
			}
		}
		if (internalsValid) {
			drawTexturedModalRect(guiLeft + 27, guiTop + 87, 176, 0, 18, 18);
			drawTexturedModalRect(guiLeft + 47, guiTop + 87, 176, 0, 18, 18);
			drawTexturedModalRect(guiLeft + 67, guiTop + 87, 176, 0, 18, 18);
		} else {
			if (te.getStackInSlot(11) != null) { drawTexturedModalRect(guiLeft + 27, guiTop + 87, 176, 18, 18, 18); } else { drawTexturedModalRect(guiLeft + 27, guiTop + 87, 27, 87, 18, 18); }
			if (te.getStackInSlot(12) != null) { drawTexturedModalRect(guiLeft + 47, guiTop + 87, 176, 18, 18, 18); } else { drawTexturedModalRect(guiLeft + 47, guiTop + 87, 47, 87, 18, 18); }
			if (te.getStackInSlot(13) != null) { drawTexturedModalRect(guiLeft + 67, guiTop + 87, 176, 18, 18, 18); } else { drawTexturedModalRect(guiLeft + 67, guiTop + 87, 67, 87, 18, 18); }
		}
		
		//Check barrel and chamber match on calibre.
		ItemStack chamberStack = te.getStackInSlot(3);
		ItemStack barrelStack = te.getStackInSlot(14);
		
		if (chamberStack != null && barrelStack != null && !calibre()) {
			drawTexturedModalRect(guiLeft + 7, guiTop + 67, 176, 18, 18, 18);
			drawTexturedModalRect(guiLeft + 7, guiTop + 87, 176, 18, 18, 18);
		}
		
		if (chamberStack != null && barrelStack == null) {
			
		}
	}
	
	private int totalBuildTime() {
		int total = 0;
		for (int i = 3; i <= 16; i++) {
			ItemStack itemComponent = te.getStackInSlot(i);
			if (itemComponent != null) {
				if (itemComponent.getItem() instanceof ItemComponent) {
					ItemComponent component = (ItemComponent) itemComponent.getItem();
					total = total + component.buildTime;
				}
			}
		}
		return total;		
	}
	
	private float totalAccuracy() {
		float total = 0.0F;
		for (int i = 3; i <= 16; i++) {
			ItemStack itemComponent = te.getStackInSlot(i);
			if (itemComponent != null) {
				if (itemComponent.getItem() instanceof ItemComponent) {
					ItemComponent component = (ItemComponent) itemComponent.getItem();
					total = total + component.accuracy;
				}
			}
		}
		return total;
	}
	
	private int fireRate() {
		int rate = 1;
		return rate;
	}
	
	private int power() {
		int power = 0;
		for (int i = 3; i <= 16; i++) {
			ItemStack itemComponent = te.getStackInSlot(i);
			if (itemComponent != null) {
				if (itemComponent.getItem() instanceof ItemComponent) {
					ItemComponent component = (ItemComponent) itemComponent.getItem();
					power = power + component.power;
				}
			}
		}
		return power;
	}

	
	private int partsCost() {
		int cost = 0;
		for (int i = 3; i <= 16; i++) {
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
	
	
	
	private boolean bolt() {
		boolean bo1 = te.getStackInSlot(4) != null;
		boolean bo2 = te.getStackInSlot(5) != null;
		boolean bo3 = te.getStackInSlot(6) != null;
		boolean bo4 = te.getStackInSlot(7) != null;
		boolean bo5 = te.getStackInSlot(8) != null;
		boolean bo6 = te.getStackInSlot(9) != null;
		boolean bo7 = te.getStackInSlot(10) != null;
		
		if (bo1 && bo2 && bo3 && bo4 && bo5 && bo6 && bo7) {
			return true;
		}
		return false;
	}
	
	private boolean barrel() {
		boolean ba1 = te.getStackInSlot(3) != null;
		boolean ba2 = te.getStackInSlot(14) != null;
		
		if (ba1 && ba2) {
			if (te.getStackInSlot(14).stackTagCompound != null) {
				int length = te.getStackInSlot(14).stackTagCompound.getInteger("length");
				if (length >= 8 && length <= 22) {
					return true;
				}
			}
		}
		
		return false;
	}
	
	private boolean calibre() {
		if (te.getStackInSlot(3) != null && te.getStackInSlot(14) != null) {
			if (te.getStackInSlot(3).getItem() instanceof ItemAssaultChamber && te.getStackInSlot(14).getItem() instanceof ItemBarrel) {				
				if (te.getStackInSlot(3).stackTagCompound != null && te.getStackInSlot(14).stackTagCompound != null) {
					if (te.getStackInSlot(3).stackTagCompound.getDouble("calibre") == te.getStackInSlot(14).stackTagCompound.getDouble("calibre")) {
						return true;
					}
				}
			}
		}
		return false;
	}
	
	private boolean firingSystem() {
		boolean fm1 = te.getStackInSlot(15) != null;
		boolean fm2 = te.getStackInSlot(16) != null;
		if (fm1 && fm2) {
			return true;
		}
		return false;
	}
	
	private boolean isGunValid() {
		boolean i1 = te.getStackInSlot(11) != null;
		boolean i2 = te.getStackInSlot(12) != null;
		boolean i3 = te.getStackInSlot(13) != null;
		boolean internals = false;
		
		ItemStack item1 = te.getStackInSlot(11);
		ItemStack item2 = te.getStackInSlot(12);
		ItemStack item3 = te.getStackInSlot(13);
		
		boolean internalsDI = false;
		boolean internalsPiston = false;
		
		if (i1 && i2) {
			if (item1.stackTagCompound != null && item2.stackTagCompound != null) {
				if (item1.stackTagCompound.getString("partName").equalsIgnoreCase("gasChamber") && item2.stackTagCompound.getString("partName").equalsIgnoreCase("gasFeed")) {
					internalsDI = true;
					if (i3) {
						internals = false; //Should be EMPTY in the third slot.
					} else {
						internals = true;
					}
				} else if (item1.stackTagCompound.getString("partName").equalsIgnoreCase("pistonChamber") && item2.stackTagCompound.getString("partName").equalsIgnoreCase("spring")) {
					if (i3) {
						if (item3.stackTagCompound != null) {
							if (item3.stackTagCompound.getString("partName").equalsIgnoreCase("piston")) {
								internalsPiston = true;
								internals = true;
							}
						}
					}
				}
			}
		}
		
		boolean frame = te.getStackInSlot(0) != null;
		
		boolean afford = false;
		if (te.partsValue >= partsCost()) {
			afford = true;
		}
		
		if (bolt() && internals && barrel() && calibre() && firingSystem() && frame && afford) {
			return true;
		}
		return false;
	}
	
	private List gunStatReport() {
		boolean i1 = te.getStackInSlot(11) != null;
		boolean i2 = te.getStackInSlot(12) != null;
		boolean i3 = te.getStackInSlot(13) != null;
		boolean internals = false;
		
		ItemStack item1 = te.getStackInSlot(11);
		ItemStack item2 = te.getStackInSlot(12);
		ItemStack item3 = te.getStackInSlot(13);
		
		boolean internalsDI = false;
		boolean internalsPiston = false;
		
		if (i1 && i2) {
			if (item1.stackTagCompound != null && item2.stackTagCompound != null) {
				if (item1.stackTagCompound.getString("partName").equalsIgnoreCase("gasChamber") && item2.stackTagCompound.getString("partName").equalsIgnoreCase("gasFeed")) {
					internalsDI = true;
					if (i3) {
						internals = false; //Should be EMPTY in the third slot.
					} else {
						internals = true;
					}
				} else if (item1.stackTagCompound.getString("partName").equalsIgnoreCase("pistonChamber") && item2.stackTagCompound.getString("partName").equalsIgnoreCase("spring")) {
					if (i3) {
						if (item3.stackTagCompound != null) {
							if (item3.stackTagCompound.getString("partName").equalsIgnoreCase("piston")) {
								internalsPiston = true;
								internals = true;
							}
						}
					}
				}
			}
		}
		boolean validLength = true;
		String lengthError = "";
		
		if (te.getStackInSlot(14) != null) {
			if (te.getStackInSlot(14).stackTagCompound != null) {
				int length = te.getStackInSlot(14).stackTagCompound.getInteger("length");
				if (length < 8) {
					lengthError = "Configured barrel is too short.";
					validLength = false;
				} else if (length > 22) {
					lengthError = "Configured barrel is too long.";
					validLength = false;
				}
			}
		}
				
		boolean frame = te.getStackInSlot(0) != null;
		boolean modifier = te.getStackInSlot(2) != null;
		boolean afford = te.partsValue >= partsCost() ? true : false;

		List result = new ArrayList();

		EnumChatFormatting b = EnumChatFormatting.BLACK;
		result.add("Listing Weapon Report:");
		result.add("");
		result.add("Bolt Status:             " + b + ":l" + parseBool(bolt()));
		
		result.add("Internals Status:      " + b + ":l" + parseBool(internals));	
		if (!internals) {
			if (internalsDI) {
				result.add(EnumChatFormatting.BLUE + "Slot " + EnumChatFormatting.DARK_RED + "C" + EnumChatFormatting.BLUE + " must be EMPTY when using gas mechanics!");
				result.add("");
			}
		}
		
		result.add("Barrel Status:          " + b + "l" + parseBool(barrel()));
		if (!barrel() || !validLength || !calibre()) {
			boolean barrelIssue = false;
			if (!calibre() && barrel()) {
				result.add(EnumChatFormatting.AQUA + "Barrel and Chamber calibre does not match.");
				barrelIssue = true;
			}
			if (!validLength) {
				result.add(EnumChatFormatting.AQUA + lengthError);
				barrelIssue = true;
			}
			if (barrelIssue) {
				result.add("");
			}
		}
		
		result.add("Firing Systems Status: " + parseBool(firingSystem()));
		result.add("Frame Status:           " + b + ":" + parseBool(frame));
		
		if (!afford) {
			result.add("");
			result.add(EnumChatFormatting.DARK_RED + "You need more Parts to build this weapon.");
		}
		
		if (!modifier) {
			result.add("");
			result.add(EnumChatFormatting.ITALIC + "There is no Modifier Core installed.");
			result.add(EnumChatFormatting.ITALIC + "This is informational only; Cores are not required.");
		} else {
			result.add("");
			result.add(EnumChatFormatting.ITALIC + "Modifier Core ready to install.");
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
