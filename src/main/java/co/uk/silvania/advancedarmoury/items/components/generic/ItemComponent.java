package co.uk.silvania.advancedarmoury.items.components.generic;

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
import net.minecraft.world.World;

public class ItemComponent extends Item {

	public String partName;
	public String material;
	public int cost;
	public int buildTime;
	public int power;
	public double calibre;
	public double size;
	
	public int durability;
	public int weight;
	public float accuracy;
	public String textColour;
	public int itemRGB;
	public int fireRate;
	public String oreDict;
	
	public String textureName;
	public String displayName;
	
	public ItemComponent(String weaponType, String displayName, String partName, String materialName, double size, int cost, int buildTime, int power, double durability, int weight, float acc, String textCol, int rgb, int fireRate, String oreDict) {
		super();
		this.partName = partName;
		this.material = materialName;
		this.durability = (int) Math.round(200 * size);
		this.size = size;
		this.cost = cost;
		this.buildTime = buildTime / 4;
		this.power = power;
		this.weight = (int)Math.round(weight * size);
		this.accuracy = acc;
		this.setMaxDamage((int)Math.round(durability * this.durability));
		this.setCreativeTab(AdvancedArmoury.tabComponentsGeneric);
		this.textColour = textCol;
		this.itemRGB = rgb;
		this.fireRate = fireRate;
		this.oreDict = oreDict;
		this.textureName = weaponType + partName;
		this.displayName = material + " " + displayName;
	}
	
	public ItemComponent(String weaponType, String displayName, String partName, String materialName, double size, int cost, int buildTime, int power) {
		super();
		MaterialStats stats = new MaterialStats();
		this.partName = partName;
		this.material = materialName;
		this.durability = (int) Math.round(200 * size);
		this.size = size;
		this.cost = cost;
		this.buildTime = buildTime / 4;
		this.power = power;
		this.weight = (int)Math.round(stats.getWeight(materialName) * size);
		this.accuracy = stats.getAccuracry(materialName);
		this.setMaxDamage((int)Math.round(stats.getDurability(materialName) * this.durability));
		this.setCreativeTab(AdvancedArmoury.tabComponentsGeneric);
		this.textColour = stats.getTextCol(materialName);
		this.itemRGB = stats.getRGB(materialName);
		this.fireRate = stats.getFireRate(materialName);
		this.oreDict = stats.getOreDict(materialName);
		this.textureName = weaponType + partName;
		this.displayName = material + " " + displayName;
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
	
	public int getPartsCost() { 	return cost; 		}
	public double getBuildTime() { 	return buildTime; 	}
	public double getPower() { 		return power; 		}

	public int fireRate() { 		return fireRate;	}
	public float getAccuracy() { 	return accuracy; 	}
	public double calibre() {		return calibre;		}
}
