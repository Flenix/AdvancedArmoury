package co.uk.silvania.advancedarmoury.items.components.generic.assault;

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

public class ItemAssaultChamber extends ItemAssaultComponent {

	public ItemAssaultChamber(EnumMaterial mat) {
		super("chamber", 1.76, mat);
		this.setCreativeTab(AdvancedArmoury.tabComponentsCalibre);
	}
	
	@SideOnly(Side.CLIENT)
    public void addInformation(ItemStack item, EntityPlayer player, List list, boolean p_77624_4_) {
		list.add("\u00A7bPart Identifier: B");
		list.add("");
		list.add("Material: " + material.getString());
		list.add("Accuracy: N/A");
		list.add("Fire Rate: N/A");
		list.add("Cost (Parts): " + cost);
		list.add("Build Time: " + buildTime);
		list.add("Power Modifier: " + power);
		list.add("Weight: " + weight);
		list.add("Damage: " + this.getDamage(item) + "/" + this.getMaxDamage());
		if (item.stackTagCompound != null) {
			list.add("Calibre: " + item.stackTagCompound.getDouble("calibre"));
		} else {
			list.add(EnumChatFormatting.DARK_RED + "Something has broken horribly. You should throw this away and tell Flenix.");
		}
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
		} else {
			setMaxDamage((int)Math.round(material.durability * (this.durability * Math.round(item.stackTagCompound.getDouble("size")))));
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
		double[] cal = {5.56, 7.62, 9.00, 12.7};
		
		for (int i = 0; i < cal.length; i++) {
			ItemStack itemChamber = new ItemStack(item);
			
			itemChamber.stackTagCompound = new NBTTagCompound();
			itemChamber.stackTagCompound.setDouble("calibre", cal[i]);
			itemChamber.stackTagCompound.setString("partName", partName);
			list.add(itemChamber);
		}
	}
}
