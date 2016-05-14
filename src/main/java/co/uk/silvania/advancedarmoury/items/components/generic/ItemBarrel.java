package co.uk.silvania.advancedarmoury.items.components.generic;

import java.util.List;

import co.uk.silvania.advancedarmoury.AdvancedArmoury;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;

public class ItemBarrel extends ItemComponent {

	String material;
	String col;
	
	double size;
	int weight;
	double durability;
	
	float accuracy;
	int fireRate;
	int power;
	
	public static int[] length = {4, 6, 8, 10, 15, 18, 20, 22, 24, 27, 30};
	public static double[] cal = {5.56, 7.62, 9.00, 12.7};
	
	public ItemBarrel(String name, double size, String materialName, double durability, int weight, float accuracy, String textCol, int rgb, int fireRate, String oreDict, boolean rifled) {
		super("", "Barrel", name, materialName, textCol, rgb, oreDict, "\u00A7b", "A");
		this.material = materialName;
		this.col = textCol;
		
		this.size = size;
		this.weight = weight;
		this.durability = durability;
		
		this.accuracy = accuracy;
		this.fireRate = fireRate;
		this.power = (int) Math.round(weight / size);
		
		this.setCreativeTab(AdvancedArmoury.tabComponentsCalibre);
	}

	public float getAccuracy(ItemStack item) {
		item.stackTagCompound.getInteger("length");
		return 0F;
	}
	
	@Override
	public void onUpdate(ItemStack item, World world, Entity entity, int par4, boolean par5) {
		int meta = item.getItemDamage();
		if (item.stackTagCompound == null) {
			item.stackTagCompound = new NBTTagCompound();
			if (entity instanceof EntityPlayer) {
				if (!world.isRemote) {
					EntityPlayer player = (EntityPlayer) entity;
					player.addChatComponentMessage(new ChatComponentText("Bad item spawning detected for a Barrel - defaulting to 15\" and 5.56mm"));
					player.addChatComponentMessage(new ChatComponentText("Next time, use the creative menu or NEI instead of cheat mods or commands."));
				}
			}
			item.stackTagCompound.setInteger("length", 15);
			item.stackTagCompound.setDouble("calibre", 5.56);
			item.stackTagCompound.setString("partName", partName);
			item.stackTagCompound.setFloat("accuracy", accuracy);
		} else {
			setMaxDamage((int)Math.round(this.durability * Math.round(item.stackTagCompound.getDouble("size"))));
		}
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(Item item, CreativeTabs tab, List list) {
		for (int i = 0; i < cal.length; i++) {
			for (int j = 0; j < length.length; j++) {
				ItemStack itemBarrel = new ItemStack(item);
				
				itemBarrel.stackTagCompound = new NBTTagCompound();
				itemBarrel.stackTagCompound.setInteger("length", length[j]);
				itemBarrel.stackTagCompound.setDouble("calibre", cal[i]);
				itemBarrel.stackTagCompound.setDouble("size", size * length[j]);
				itemBarrel.stackTagCompound.setString("partName", partName);
				itemBarrel.stackTagCompound.setFloat("accuracy", accuracy);
				list.add(itemBarrel);
			}
		}
	}

	@Override public double size(ItemStack item) { return size; }
	@Override public int weight(ItemStack item) { return (int) (weight*(item.stackTagCompound.getInteger("length")*size)); }
	@Override public double durability(ItemStack item) { return Math.round(((item.stackTagCompound.getInteger("length")*size)*stats.getDurability(material))*100); }
	@Override public float accuracy(ItemStack item) { return accuracy; }
	@Override public int fireRate(ItemStack item) { return fireRate; }
	@Override public int power(ItemStack item) { return power; }
}
