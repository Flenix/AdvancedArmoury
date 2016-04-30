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
	double dura;
	float acc;
	
	public ItemBarrel(String name, double size, String materialName, double durability, int weight, float acc, String textCol, int rgb, int fireRate, String oreDict, boolean rifled) {
		super("", "Barrel", name, materialName, size, (int) Math.round((size*weight)/4), (int) Math.round((size*durability)*100), (int) Math.round(weight / size), durability, weight, acc, textCol, rgb, fireRate, oreDict);
		this.accuracy = acc;
		this.durability = (int) Math.round(200 * size);
		this.material = materialName;
		this.col = textCol;
		this.dura = durability;
		this.setCreativeTab(AdvancedArmoury.tabComponentsCalibre);
		//this.setMaxDamage((int)Math.round(getDurability(mat, this.durability, weight)));
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
			setMaxDamage((int)Math.round(dura * (this.durability * Math.round(item.stackTagCompound.getDouble("size")))));
		}
	}
	
	@SideOnly(Side.CLIENT)
    public void addInformation(ItemStack item, EntityPlayer player, List list, boolean p_77624_4_) {
		list.add("\u00A7b" + "Part Identifier: " + "A");
		list.add("");
		list.add(col + "Material: " + material);
		list.add("");
		list.add("Accuracy: " + accuracy);
		list.add("\u00A78" + "Fire Rate: N/A");
		list.add("");
		list.add("Cost (Parts): " + cost);
		list.add("Build Time: " + buildTime);
		list.add("");
		list.add("Power Modifier: " + power);
		if (item.stackTagCompound != null) {
			list.add("Weight: " + weight * Math.round(item.stackTagCompound.getInteger("length")));
			list.add("");
			list.add("Barrel Length: " + item.stackTagCompound.getInteger("length") + "\"");
			list.add("Calibre: " + item.stackTagCompound.getDouble("calibre"));
			list.add("");
			list.add("Damage: " + this.getDamage(item) + "/" + (int)Math.round(dura * (this.durability * Math.round(item.stackTagCompound.getDouble("size")))));
		} else {
			list.add(EnumChatFormatting.DARK_RED + "Something has broken horribly. You should throw this away and tell Flenix.");
		}
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(Item item, CreativeTabs tab, List list) {
		int[] length = {4, 6, 8, 10, 15, 18, 20, 22, 24, 27, 30};
		double[] cal = {5.56, 7.62, 9.00, 12.7};
		
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
}
