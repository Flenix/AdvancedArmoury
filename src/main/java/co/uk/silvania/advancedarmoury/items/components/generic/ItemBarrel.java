package co.uk.silvania.advancedarmoury.items.components.generic;

import java.util.List;

import co.uk.silvania.advancedarmoury.AdvancedArmoury;
import co.uk.silvania.advancedarmoury.items.EnumMaterial;
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

	static double size = 0.3;
	EnumMaterial material;
	
	public ItemBarrel(EnumMaterial mat, boolean rifled) {
		super("barrel", size, (int) Math.round((size*mat.weight)/4), (int) Math.round((size*mat.durability)*100), (int) Math.round(mat.weight / size), mat);
		this.accuracy = mat.accuracy;
		this.durability = (int) Math.round(200 * size);
		this.material = mat;
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
			item.stackTagCompound.setFloat("accuracy", material.accuracy);
		} else {
			setMaxDamage((int)Math.round(material.durability * (this.durability * Math.round(item.stackTagCompound.getDouble("size")))));
		}
	}
	
	@SideOnly(Side.CLIENT)
    public void addInformation(ItemStack item, EntityPlayer player, List list, boolean p_77624_4_) {
		list.add("\u00A7bPart Identifier: A");
		list.add("");
		list.add("Material: " + material.getString());
		list.add("Accuracy: " + material.accuracy);
		list.add("Fire Rate: N/A");
		list.add("Cost (Parts): " + cost);
		list.add("Build Time: " + buildTime);
		list.add("Power Modifier: " + power);
		if (item.stackTagCompound != null) {
			list.add("Weight: " + weight * Math.round(item.stackTagCompound.getInteger("length")));
			list.add("Damage: " + this.getDamage(item) + "/" + (int)Math.round(material.durability * (this.durability * Math.round(item.stackTagCompound.getDouble("size")))));
			list.add("Barrel Length: " + item.stackTagCompound.getInteger("length") + "\"");
			list.add("Calibre: " + item.stackTagCompound.getDouble("calibre"));
		} else {
			list.add(EnumChatFormatting.DARK_RED + "Something has broken horribly. You should throw this away and tell Flenix.");
		}
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister icon) {
		itemIcon = icon.registerIcon(AdvancedArmoury.modid + ":" + this.getUnlocalizedName().substring(5));
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
				itemBarrel.stackTagCompound.setFloat("accuracy", material.accuracy);
				list.add(itemBarrel);
			}
		}
	}
}
