package co.uk.silvania.advancedarmoury.items.components.generic;

import co.uk.silvania.advancedarmoury.AdvancedArmoury;
import co.uk.silvania.advancedarmoury.items.EnumMaterial;
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

	public EnumMaterial material;
	public String partName;
	public int cost;
	public int buildTime;
	public int power;
	public int weight;
	public int fireRate;
	public int durability;
	public float accuracy;
	public double calibre;
	public double size;
	
	public ItemComponent(String partName, double size, int cost, int buildTime, int power, EnumMaterial mat) {
		super();
		this.partName = partName;
		this.durability = (int) Math.round(200 * size);
		this.size = size;
		this.material = mat;
		this.cost = cost;
		this.buildTime = buildTime / 4;
		this.power = power;
		this.weight = (int)Math.round(mat.weight * size);
		this.setMaxDamage((int)Math.round(mat.durability * this.durability));
		this.setCreativeTab(AdvancedArmoury.tabComponentsGeneric);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister icon) {
		itemIcon = icon.registerIcon(AdvancedArmoury.modid + ":" + this.getUnlocalizedName().substring(5));
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
