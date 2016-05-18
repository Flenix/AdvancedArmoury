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
	public String weaponType;
	public float xSize = 0;
	public float ySize = 0;
	public float zSize = 0;
	
	String identColour;
	String identId;
	String mat;
	String textCol;
	
	double size;
	int weight;
	double durability;
	
	public ItemAsset(String weaponType, String identColour, String identId, String componentName, double size, String material, String textColour) {
		super(/*displayName*/"", componentName, /*identifier*/ "", size);
		this.mat = material;
		this.textCol = textColour;
		this.identColour = identColour;
		this.identId = identId;
		
		this.weaponType = weaponType;
		
		this.size = size;
		this.weight = MaterialStats.getWeight(material);
		this.durability = MaterialStats.getDurability(material);
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
			list.add("Part Cost: " + cost(item));
			list.add("Build Time: " + buildTime(item));
			if (item.stackTagCompound.getBoolean("broken")) {
				list.add(EnumChatFormatting.ITALIC + "" + EnumChatFormatting.RED + "Broken");
			}
		}
	}
	
	public void createNBTData(ItemStack item) {
		item.stackTagCompound = new NBTTagCompound();
		item.stackTagCompound.setString("componentName", componentName);

		item.stackTagCompound.setInteger("damage", 0);
		item.stackTagCompound.setBoolean("broken", false);
		
		//Data
		item.stackTagCompound.setString("modelName", modelName);
		item.stackTagCompound.setString("modelTexture", modelTexture);
		item.stackTagCompound.setString("iconTexture", iconTexture);
	}

	public double size(ItemStack item) { return size; }
	public int weight(ItemStack item) { return (int) (size*weight); }
	public double durability(ItemStack item) { return Math.round((size*durability)*100); }
	public float accuracy(ItemStack item) { return -1; }
	public int fireRate(ItemStack item) { return -1; }
	public int power(ItemStack item) { return -1; }

}
