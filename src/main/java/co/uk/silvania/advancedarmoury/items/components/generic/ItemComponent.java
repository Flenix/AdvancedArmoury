package co.uk.silvania.advancedarmoury.items.components.generic;

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

public abstract class ItemComponent extends Item {

	MaterialStats stats = new MaterialStats();
	
	public String partName;
	public String material;
	public double calibre;
	public double size;
	
	public String textColour;
	public int itemRGB;
	public int fireRate;
	public String oreDict;
	
	public String textureName;
	public String displayName;
	
	public String identCol;
	public String identName;
	
	public ItemComponent(String weaponType, String displayName, String componentName, String materialName, String textCol, int rgb, String oreDict, String identCol, String identId) {
		super();
		this.partName = componentName;
		this.material = materialName;
		this.setCreativeTab(AdvancedArmoury.tabComponentsGeneric);
		this.textColour = textCol;
		this.itemRGB = rgb;
		this.oreDict = oreDict;
		this.textureName = weaponType + partName;
		this.displayName = material + " " + displayName;
		this.identCol = identCol;
		this.identName = identId;
	}
	
	public ItemComponent(String weaponType, String displayName, String componentName, String materialName, double size) {
		super();
		MaterialStats stats = new MaterialStats();
		this.partName = componentName;
		this.material = materialName;
		this.size = size;
		this.setCreativeTab(AdvancedArmoury.tabComponentsGeneric);
		this.textColour = stats.getTextCol(materialName);
		this.itemRGB = stats.getRGB(materialName);
		this.fireRate = stats.getFireRate(materialName);
		this.oreDict = stats.getOreDict(materialName);
		this.textureName = weaponType + partName;
		this.displayName = material + " " + displayName;
	}
	
	public abstract double size(ItemStack item);
	public abstract int weight(ItemStack item);
	public abstract double durability(ItemStack item);
	
	/**
	 * Return less than zero if this component doesn't affect accuracy.
	 * Lower values = more accurate
	 * @return
	 */
	public abstract float accuracy(ItemStack item);
	
	/**
	 * Return less than zero if this component doesn't affect fire rate.
	 * RETURNS DIFFERENT THINGS DEPENDING ON GUN SYSTEM! 
	 * Rounds per second on full auto. Cooldown in ticks on semi auto.
	 * @return
	 */
	public abstract int fireRate(ItemStack item);
	
	/**
	 * Return less than zero if this component doesn't affect power.
	 * Defines damage, range etc
	 * @return
	 */
	public abstract int power(ItemStack item);
	
	public int cost(ItemStack item) {
		return (int) Math.round((size(item)*weight(item))/20);
	}
	
	public int buildTime(ItemStack item) {
		return (int) Math.round(((size(item)*durability(item))*3)/100);
	}
	
	@SideOnly(Side.CLIENT)
    public void addInformation(ItemStack item, EntityPlayer player, List list, boolean p_77624_4_) {
		list.add(identCol + "Part Identifier: " + identName);
		list.add("");
		list.add(textColour + "Material: " + material);
		list.add("");
		list.add(accDisplay(accuracy(item)));
		list.add(fireRateDisplay(fireRate));
		list.add(powerDisplay(power(item)));
		list.add(weightDisplay(weight(item)));
		list.add(durabilityDisplay(durability(item)));
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
	
	public String powerDisplay(int power) {
		//return "vulgar"; ;)
		if (power > 0) {
			int matWeight = stats.getWeight(material);
			if (matWeight >= 1601) { return "\u00A72" + "Power: " + power; }
			else if (matWeight >= 1201) { return "\u00A7a" + "Power: " + power; }
			else if (matWeight >= 801) { return "\u00A7e" + "Power: " + power; }
			else if (matWeight >= 401) { return "\u00A7c" + "Power: " + power; }
			else return "\u00A74" + "Power: " + power;
		}
		return "\u00A78" + "Power: N/A";
	}
	
	public String weightDisplay(int weight) {
		int matWeight = stats.getWeight(material);
		if (matWeight >= 1601) { return "\u00A74" + "Weight: " + weight; }
		else if (matWeight >= 1201) { return "\u00A7c" + "Weight: " + weight; }
		else if (matWeight >= 801) { return "\u00A7e" + "Weight: " + weight; }
		else if (matWeight >= 401) { return "\u00A7e" + "Weight: " + weight; }
		else return "\u00A72" + "Weight: " + weight;

	}
	
	public String durabilityDisplay(double durability) {
		double dura = stats.getDurability(material);
		if (dura <= 0.6) { return "\u00A74" + "Durability: " + (int) durability; }
		else if (dura <= 2.0) { return "\u00A7c" + "Durability: " + (int) durability; }
		else if (dura <= 3.5) { return "\u00A7e" + "Durability: " + (int) durability; }
		else if (dura <= 6.5) { return "\u00A7a" + "Durability: " + (int) durability; }
		else return "\u00A72" + "Durability: " + (int) durability;
	}
	
	
	@Override
    public String getItemStackDisplayName(ItemStack item) {
        return displayName;
    }

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister icon) {
		itemIcon = icon.registerIcon(AdvancedArmoury.modid + ":" + textureName);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public int getColorFromItemStack(ItemStack item, int par2) {
		return itemRGB;
	}
	
	public void damagePart(ItemStack item, int dmg) {
		this.setDamage(item, this.getDamage(item) + dmg);
	}
	
	@Override
	public void onUpdate(ItemStack item, World world, Entity entity, int par4, boolean par5) {
		int meta = item.getItemDamage();
		if (item.stackTagCompound == null) {
			item.stackTagCompound = new NBTTagCompound();
			createTagCompound(item, meta);
		}
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
		
	public void createTagCompound(ItemStack item, int meta) {
		item.stackTagCompound.setString("partName", partName);
	}
}
