package co.uk.silvania.advancedarmoury.blocks.machines;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import co.uk.silvania.advancedarmoury.blocks.machines.assemblytable.assault.AssaultAssemblyTableContainer;
import co.uk.silvania.advancedarmoury.items.generic.ItemComponent;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import scala.actors.threadpool.Arrays;

public class MachineGui extends GuiContainer {

	public MachineGui(MachineContainer cntr) {
		super(cntr);
	}

	//Modified version of the vanilla drawHoveringList, allowing a more table-style layout with two columns
	//ListA is left-side, ListB is right-side.
	//check is which rows to check length. Any row not listed will be ignored (therefore allowing column "merging")
	protected void drawCustomHoveringText(List listA, List listB, int posX, int posZ, FontRenderer font, Integer[] check) {
        if (!listA.isEmpty()) {
            GL11.glDisable(GL12.GL_RESCALE_NORMAL);
            RenderHelper.disableStandardItemLighting();
            GL11.glDisable(GL11.GL_LIGHTING);
            GL11.glDisable(GL11.GL_DEPTH_TEST);
            int wdt = 0;
            int wdtA = 0;
            int wdtB = 0;
            List<Integer> checkList = Arrays.asList(check);
            
            for (int i = 0; i < listA.size(); i++) {
            	String s = listA.get(i).toString();
            	if (checkList.contains(i)) {
            		if (font.getStringWidth(s) > wdtA) {
            			wdtA = font.getStringWidth(s);
            		}
            	}
            }
            if (wdtA == 0) {
	            Iterator iterator = listA.iterator();
	
	            while (iterator.hasNext()) {
	                String s = (String)iterator.next();
	                int l = font.getStringWidth(s);
	
	                if (l > wdtA) { wdtA = l; }
	            }
            }
            
            for (int i = 0; i < listB.size(); i++) {
            	String s = listB.get(i).toString();
            	if (checkList.contains(i)) {
            		if (font.getStringWidth(s) > wdtB) {
            			wdtB = font.getStringWidth(s);
            		}
            	}
            }
            if (wdtB == 0) {
	            Iterator iterator = listB.iterator();
	
	            while (iterator.hasNext()) {
	                String s = (String)iterator.next();
	                int l = font.getStringWidth(s);
	
	                if (l > wdtB) { wdtB = l; }
	            }
            }
            
            wdt = wdtA + wdtB + 12;
            
            Iterator iterator = listA.iterator();
        	
            while (iterator.hasNext()) {
                String s = (String)iterator.next();
                int l = font.getStringWidth(s);

                if (l > wdt) { wdt = l; }
            }

            int j2 = posX + 12;
            int k2 = posZ - 12;
            int i1 = 8;

            if (listA.size() > 1) { i1 += 2 + (listA.size() - 1) * 10; }
            if (j2 + wdt > this.width) { j2 -= 28 + wdt; }
            if (k2 + i1 + 6 > this.height) { k2 = this.height - i1 - 6; }

            this.zLevel = 300.0F;
            itemRender.zLevel = 300.0F;
            int j1 = -267386864;
            this.drawGradientRect(j2 - 3, k2 - 4, j2 + wdt + 3, k2 - 3, j1, j1);
            this.drawGradientRect(j2 - 3, k2 + i1 + 3, j2 + wdt + 3, k2 + i1 + 4, j1, j1);
            this.drawGradientRect(j2 - 3, k2 - 3, j2 + wdt + 3, k2 + i1 + 3, j1, j1);
            this.drawGradientRect(j2 - 4, k2 - 3, j2 - 3, k2 + i1 + 3, j1, j1);
            this.drawGradientRect(j2 + wdt + 3, k2 - 3, j2 + wdt + 4, k2 + i1 + 3, j1, j1);
            int k1 = 1347420415;
            int l1 = (k1 & 16711422) >> 1 | k1 & -16777216;
            this.drawGradientRect(j2 - 3, k2 - 3 + 1, j2 - 3 + 1, k2 + i1 + 3 - 1, k1, l1);
            this.drawGradientRect(j2 + wdt + 2, k2 - 3 + 1, j2 + wdt + 3, k2 + i1 + 3 - 1, k1, l1);
            this.drawGradientRect(j2 - 3, k2 - 3, j2 + wdt + 3, k2 - 3 + 1, k1, k1);
            this.drawGradientRect(j2 - 3, k2 + i1 + 2, j2 + wdt + 3, k2 + i1 + 3, l1, l1);

            for (int i = 0; i < listA.size(); i++) {
                String s1 = (String)listA.get(i);
                font.drawStringWithShadow(s1, j2, k2, -1);

                if (i == 0) { k2 += 2; }
                k2 += 10;
            }
            
            k2 = posZ - 12;
            if (k2 + i1 + 6 > this.height) { k2 = this.height - i1 - 6; }
            
            for (int i = 0; i < listB.size(); i++) {
                String s1 = (String)listB.get(i);
                font.drawStringWithShadow(s1, j2 + wdtA + 12, k2, -1);

                if (i == 0) { k2 += 2; }
                k2 += 10;
            }

            this.zLevel = 0.0F;
            itemRender.zLevel = 0.0F;
            GL11.glEnable(GL11.GL_LIGHTING);
            GL11.glEnable(GL11.GL_DEPTH_TEST);
            RenderHelper.enableStandardItemLighting();
            GL11.glEnable(GL12.GL_RESCALE_NORMAL);
        }
    }
	
	
	@Override protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int p_146976_2_, int p_146976_3_) {}
	
	protected int totalBuildTime(int low, int high, MachineEntity te) {
		int total = 0;
		for (int i = low; i <= high; i++) {
			ItemStack itemComponent = te.getStackInSlot(i);
			if (itemComponent != null) {
				if (itemComponent.getItem() instanceof ItemComponent) {
					if (itemComponent.stackTagCompound != null) {
						total += itemComponent.stackTagCompound.getInteger("buildTime");
					}
				}
			}
		}
		return total;		
	}
	
	protected double totalDurability(int low, int high, MachineEntity te) {
		double total = 0.0;
		for (int i = low; i <= high; i++) {
			ItemStack itemComponent = te.getStackInSlot(i);
			if (itemComponent != null) {
				if (itemComponent.getItem() instanceof ItemComponent) {
					ItemComponent component = (ItemComponent) itemComponent.getItem();
					total = total + component.getDurability(itemComponent);
				}
			}
		}
		return total;
	}
	
	protected int totalWeight(int low, int high, MachineEntity te) {
		int total = 0;
		for (int i = low; i <= high; i++) {
			ItemStack itemComponent = te.getStackInSlot(i);
			if (itemComponent != null) {
				if (itemComponent.getItem() instanceof ItemComponent) {
					ItemComponent component = (ItemComponent) itemComponent.getItem();
					total = total + component.getWeight(itemComponent);
				}
			}
		}
		return total;
	}
	
	protected float totalAccuracy(int low, int high, MachineEntity te) {
		float total = 0.0F;
		for (int i = low; i <= high; i++) {
			ItemStack itemComponent = te.getStackInSlot(i);
			if (itemComponent != null) {
				if (itemComponent.getItem() instanceof ItemComponent) {
					ItemComponent component = (ItemComponent) itemComponent.getItem();
					total = total + component.getAccuracy(itemComponent);
				}
			}
		}
		return total;
	}
	
	protected int fireRate(int low, int high, MachineEntity te) {
		int rate = 0;
		for (int i = low; i <= high; i++) {
			ItemStack itemComponent = te.getStackInSlot(i);
			if (itemComponent != null) {
				if (itemComponent.getItem() instanceof ItemComponent) {
					ItemComponent component = (ItemComponent) itemComponent.getItem();
					rate = rate + component.getFireRate(itemComponent);
				}
			}
		}
		return rate;
	}
	
	protected int power(int low, int high, MachineEntity te) {
		int power = 0;
		for (int i = low; i <= high; i++) {
			ItemStack itemComponent = te.getStackInSlot(i);
			if (itemComponent != null) {
				if (itemComponent.getItem() instanceof ItemComponent) {
					ItemComponent component = (ItemComponent) itemComponent.getItem();
					power = power + component.getPower(itemComponent);
				}
			}
		}
		return power;
	}
	
	protected int partsCost(int low, int high, MachineEntity te) {
		int cost = 0;
		for (int i = low; i <= high; i++) {
			ItemStack itemComponent = te.getStackInSlot(i);
			if (itemComponent != null) {
				if (itemComponent.getItem() instanceof ItemComponent) {
					if (itemComponent.stackTagCompound != null) {
						cost += itemComponent.stackTagCompound.getInteger("cost");
					}
				}
			}
		}
		return cost;
	}

	protected String parseBool(boolean bool) {
		if (bool) {
			return EnumChatFormatting.DARK_GREEN + "Ready to Install";
		} else 
			return EnumChatFormatting.DARK_RED + "Missing";
	}
}
