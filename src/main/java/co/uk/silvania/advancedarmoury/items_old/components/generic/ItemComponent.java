package co.uk.silvania.advancedarmoury.items_old.components.generic;

import java.util.List;

import co.uk.silvania.advancedarmoury.AdvancedArmoury;
import co.uk.silvania.advancedarmoury.config.MaterialStats;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;

public class ItemComponent extends Item {

	MaterialStats stats = new MaterialStats();
	
	public double calibre;
	double size;
	
	public String componentName;
	public String textureName;
	public String displayName;
	public String identifier;
	
	public ItemComponent(String componentName, String displayName, String materialName, String textCol, int rgb, String oreDict, String identCol, String identId) {
		super();
		this.displayName = displayName;
	}
	
	public ItemComponent(String componentName, String displayName, String identifier, double size) {
		this.identifier = identifier;
		this.textureName = componentName;
		this.displayName = displayName;
		this.size = size;
	}

	public int cost(ItemStack item) {
		return (int) Math.round((size*getWeight(item))/20);
	}
	
	public int buildTime(ItemStack item) {
		return (int) Math.round(((size*getDurability(item))*3)/100);
	}
	
	@SideOnly(Side.CLIENT)
    public void addInformation(ItemStack item, EntityPlayer player, List list, boolean p_77624_4_) {
		if (item.stackTagCompound != null) {
			list.add(identifier);//identCol + "Part Identifier: " + identName);
			list.add("");
			list.add(item.stackTagCompound.getString("textCol") + "Material: " + getMaterial(item));
			list.add("");
			list.add(accDisplay(getAccuracy(item)));
			list.add(fireRateDisplay(getFireRate(item)));
			list.add(powerDisplay(item, getPower(item)));
			list.add(weightDisplay(item, getWeight(item)));
			list.add(durabilityDisplay(item, getDurability(item)));
			list.add("");
			list.add("Cost (Parts): " + cost(item));
			list.add("Build Time: " + buildTime(item));
			list.add("");
			if (item.stackTagCompound != null) {
				if (item.stackTagCompound.getInteger("length") > 0) {
					list.add("Length: " + item.stackTagCompound.getInteger("length") + "\"");
				}
				if (item.stackTagCompound.getDouble("calibre") > 0) {
					list.add("Calibre: " + item.stackTagCompound.getDouble("calibre") + "mm");
				}
			}
		} else {
			list.add("ITEM ISSUE DETECTED! OH NO D:");
		}
	}
	
	public String accDisplay(float acc) {
		if (acc >= 0) {
			if (acc >= 0.6) { return "\u00A74" + "Accuracy: " + acc; }
			else if (acc >= 0.5) { return "\u00A7c" + "Accuracy: " + acc; }
			else if (acc >= 0.4) { return "\u00A7e" + "Accuracy: " + acc; }
			else if (acc >= 0.35) { return "\u00A7a" + "Accuracy: " + acc; }
			else return "\u00A72" + "Accuracy: " + acc;
		}
		return "\u00A78" + "Accuracy: N/A";
	}
	
	public String fireRateDisplay(int fireRate) {
		if (fireRate > 0) {
			return "Fire Rate: " + fireRate;
		}
		return "\u00A78" + "Fire Rate: N/A";
	}
	
	public String powerDisplay(ItemStack item, int power) {
		//return "vulgar"; ;)
		if (power > 0) {
			int matWeight = stats.getWeight(getMaterial(item)); //TODO make better
			if (matWeight >= 1601) { return "\u00A72" + "Power: " + power; }
			else if (matWeight >= 1201) { return "\u00A7a" + "Power: " + power; }
			else if (matWeight >= 801) { return "\u00A7e" + "Power: " + power; }
			else if (matWeight >= 401) { return "\u00A7c" + "Power: " + power; }
			else return "\u00A74" + "Power: " + power;
		}
		return "\u00A78" + "Power: N/A";
	}
	
	public String weightDisplay(ItemStack item, int weight) {
		int matWeight = stats.getWeight(getMaterial(item)); //TODO make better
		if (matWeight >= 1601) { return "\u00A74" + "Weight: " + weight; }
		else if (matWeight >= 1201) { return "\u00A7c" + "Weight: " + weight; }
		else if (matWeight >= 801) { return "\u00A7e" + "Weight: " + weight; }
		else if (matWeight >= 401) { return "\u00A7e" + "Weight: " + weight; }
		else return "\u00A72" + "Weight: " + weight;

	}
	
	public String durabilityDisplay(ItemStack item, double durability) {
		double dura = stats.getDurability(getMaterial(item)); //TODO make better
		if (dura <= 0.6) { return "\u00A74" + "Durability: " + (int) durability; }
		else if (dura <= 2.0) { return "\u00A7c" + "Durability: " + (int) durability; }
		else if (dura <= 3.5) { return "\u00A7e" + "Durability: " + (int) durability; }
		else if (dura <= 6.5) { return "\u00A7a" + "Durability: " + (int) durability; }
		else return "\u00A72" + "Durability: " + (int) durability;
	}
	
	
	@Override
    public String getItemStackDisplayName(ItemStack item) {
        return getMaterial(item) + " " + displayName;
    }

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister icon) {
		itemIcon = icon.registerIcon(AdvancedArmoury.modid + ":" + textureName);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public int getColorFromItemStack(ItemStack item, int par2) {
		if (item.stackTagCompound != null) {
			return item.stackTagCompound.getInteger("itemCol");
		}
		return 0;
	}
	
	public void damagePart(ItemStack item, int dmg) {
		this.setDamage(item, this.getDamage(item) + dmg);
	}
	
    public ItemStack onItemRightClick(ItemStack item, World p_77659_2_, EntityPlayer p_77659_3_)
    {
        return getContainerItem(item);
    }
    
    @Override
    public ItemStack getContainerItem(ItemStack item) {
    	System.out.println("Calling weird thing");
    	return new ItemStack(item.getItem(), item.stackSize, item.getItemDamage() + 1);
    }
	
	public boolean tag(ItemStack item) {
		if (item.stackTagCompound != null) {
			return true;
		}
		return false;
	}
	
	//Get stuff from NBT quickly and easily.
	public String getComponentName(ItemStack item) 	{ return tag(item) ? item.stackTagCompound.getString("componentName") : null; }
	public String getMaterial(ItemStack item) 		{ return tag(item) ? item.stackTagCompound.getString("materialName") : null; }
	
	public double getDurability(ItemStack item) 	{ return tag(item) ? item.stackTagCompound.getDouble("durability") : null; }
	public int getWeight(ItemStack item) 			{ return tag(item) ? item.stackTagCompound.getInteger("weight") : null; }
	public float getAccuracy(ItemStack item) 		{ return tag(item) ? item.stackTagCompound.getFloat("accuracy") : null; }
	
	public String getTextCol(ItemStack item) 		{ return tag(item) ? item.stackTagCompound.getString("textCol") : null; }
	public int getItemCol(ItemStack item) 			{ return tag(item) ? item.stackTagCompound.getInteger("itemCol") : null; }
	
	public int getFireRate(ItemStack item) 			{ return tag(item) ? item.stackTagCompound.getInteger("fireRate") : null; }
	
	public String getOreDict(ItemStack item) 		{ return tag(item) ? item.stackTagCompound.getString("oreDict") : null; }
	
	public int getPower(ItemStack item) 			{ return tag(item) ? item.stackTagCompound.getInteger("power") : null; }
	public int getRange(ItemStack item) 			{ return tag(item) ? item.stackTagCompound.getInteger("range") : null; }
}
