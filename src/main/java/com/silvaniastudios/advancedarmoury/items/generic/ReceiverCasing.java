package com.silvaniastudios.advancedarmoury.items.generic;

import java.util.List;

import org.lwjgl.input.Keyboard;

import com.silvaniastudios.advancedarmoury.AAUtils;
import com.silvaniastudios.advancedarmoury.RarityRegistry;
import com.silvaniastudios.advancedarmoury.items.EnumRarity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class ReceiverCasing extends ItemComponent {
	
	public float barrelX;
	public float barrelY;
	public float barrelZ;
	public float stockX;
	public float stockY;
	public float stockZ;
	public float magX;
	public float magY;
	public float magZ;
	public float attachmentX;
	public float attachmentY;
	public float attachmentZ;
	
	public int magId = 0;
	public boolean topRail = false;
	
	static double size = 19*0.75;

	public ReceiverCasing(String componentName, String displayName) {
		super(componentName, displayName, "Receiver Component H", 15, true, true, true, true, true, true);
	}
	
	public ReceiverCasing(String componentName, String displayName, String gunType,
		float xSize, float ySize, float zSize, 
		float barrelX, float barrelY, float barrelZ,
		float stockX, float stockY, float stockZ,
		float magX, float magY, float magZ,
		float attachmentX, float attachmentY, float attachmentZ,
		int magId, boolean topRail,
		String modelName, String modelTexture) {
		super(componentName, displayName, "Receiver Component H", 15, true, true, true, true, true, true);
		this.modelName = modelName;
		this.modelTexture = modelTexture;
		
		this.ySize = ySize;
		this.zSize = zSize;
		
		this.barrelX = barrelX;
		this.barrelY = barrelY;
		this.barrelZ = barrelZ;
		this.stockX = stockX;
		this.stockY = stockY;
		this.stockZ = stockZ;
		this.magX = magX;
		this.magY = magY;
		this.magZ = magZ;
		this.attachmentX = attachmentX;
		this.attachmentY = attachmentY;
		this.attachmentZ = attachmentZ;
		
		this.magId = magId;
		this.topRail = topRail;
	}
	
	@Override
    public String getItemStackDisplayName(ItemStack item) {
		if (item.stackTagCompound != null) {
			String name = item.stackTagCompound.getString("name");
			if (!name.isEmpty()) {
				return AAUtils.formatText(name);
			}
		}
        return displayName;
    }

	@SideOnly(Side.CLIENT)
    public void addInformation(ItemStack item, EntityPlayer player, List list, boolean p_77624_4_) {
		if (item.stackTagCompound != null) {
			if (item.stackTagCompound.getString("tag").length() > 0) {
				list.add(AAUtils.formatText(item.stackTagCompound.getString("tag")));
				list.add("");
			}
			if (item.stackTagCompound.getString("rarity").length() > 0 && item.stackTagCompound.getBoolean("generated")) {
				EnumRarity rarity = RarityRegistry.getEnumRarity(item.stackTagCompound.getString("rarity"));
				list.add(RarityRegistry.getRarityTag(rarity));
				list.add("");
			}
			if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)) {
				if (identifier.length() > 0) {
					list.add(identifier);
					list.add("");
				}
				list.add(item.stackTagCompound.getString("textCol") + "Material: " + getMaterial(item));
				list.add("");
				list.add(accDisplay(getAccuracy(item)));
				list.add(fireRateDisplay(item, getFireRate(item)));
				list.add(powerDisplay(item, getPower(item)));
				list.add(weightDisplay(item, getWeight(item)));
				list.add(durabilityDisplay(item, getDurability(item)));
				list.add("");
				list.add("Cost (Parts): " + getCost(item));
				list.add("Build Time: " + getBuildTime(item));
				list.add("");
				if (item.stackTagCompound != null) {
					if (item.stackTagCompound.getInteger("length") > 0) {
						list.add("Length: " + item.stackTagCompound.getInteger("length") + "\"");
					}
					if (item.stackTagCompound.getDouble("calibre") > 0) {
						list.add("Calibre: " + item.stackTagCompound.getDouble("calibre") + "mm");
					}
				}
				if (item.stackTagCompound.getString("creator").length() > 0) {
					list.add("Crafted by: " + item.stackTagCompound.getString("creator"));
				}
			} else {
				if (item.stackTagCompound != null) {
					if (item.stackTagCompound.getInteger("length") > 0) {
						list.add("Length: " + item.stackTagCompound.getInteger("length") + "\"");
					}
					if (item.stackTagCompound.getDouble("calibre") > 0) {
						list.add("Calibre: " + item.stackTagCompound.getDouble("calibre") + "mm");
					}
				}
				if (item.stackTagCompound.getString("creator").length() > 0) {
					list.add("Crafted by: " + item.stackTagCompound.getString("creator"));
				}
				list.add("Hold shift for more information");
			}
		} else {
			list.add("ITEM GENERATION HAS FAILED.");
			list.add("This item is useless. I suggest bathing it in lava.");
			list.add("This can only happen via bad item spawning methods;");
			list.add("Use the creative menu or NEI.");
			list.add("");
			list.add("If you somehow got this in survival,");
			list.add("Please tell Flenix EXACTLY what you did.");
		}
	}
}
