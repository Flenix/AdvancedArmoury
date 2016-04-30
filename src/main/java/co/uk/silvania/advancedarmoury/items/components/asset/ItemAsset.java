package co.uk.silvania.advancedarmoury.items.components.asset;

import java.util.List;

import co.uk.silvania.advancedarmoury.config.MaterialStats;
import co.uk.silvania.advancedarmoury.items.components.generic.ItemComponent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumChatFormatting;

public class ItemAsset extends ItemComponent {
	
	public String displayName = "Default Name";
	public String modelName = "m4receiver";
	public String modelTexture = "m4receiver";
	public String iconTexture = "m4receiver";
	public String gunType = "assault";
	public float xSize = 0;
	public float ySize = 0;
	public float zSize = 0;
	
	String identColour;
	String identId;
	String mat;
	String textCol;
	
	public ItemAsset(String identColour, String identId, String partName, double size, int cost, int buildTime, int power, String material, String textColour) {
		super("", "", partName, material, size, cost, buildTime / 2, power);
		this.mat = material;
		this.textCol = textColour;
		this.identColour = identColour;
		this.identId = identId;
	}
	
	@Override
    public String getItemStackDisplayName(ItemStack item) {
        return displayName;
    }
		
	@SideOnly(Side.CLIENT)
    public void addInformation(ItemStack item, EntityPlayer player, List list, boolean p_77624_4_) {
		if (item.stackTagCompound == null) {
			createNBTData(item);
		} else {
			list.add(identColour + "Part Identifier: " + identId);
			list.add("");
			list.add(textCol + "Material: " + mat);
			list.add("Build Time: " + buildTime);
			list.add("Part Cost: " + cost);
			if (item.stackTagCompound.getBoolean("broken")) {
				list.add(EnumChatFormatting.ITALIC + "" + EnumChatFormatting.RED + "Broken");
			}
		}
	}
	
	public void createNBTData(ItemStack item) {
		item.stackTagCompound = new NBTTagCompound();
		item.stackTagCompound.setString("partName", partName);

		item.stackTagCompound.setInteger("damage", 0);
		item.stackTagCompound.setBoolean("broken", false);
		
		//Data
		item.stackTagCompound.setString("modelName", modelName);
		item.stackTagCompound.setString("modelTexture", modelTexture);
		item.stackTagCompound.setString("iconTexture", iconTexture);
	}

}
