package co.uk.silvania.advancedarmoury.items.components.generic.assault;

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

public class ItemAssaultChamber extends ItemAssaultComponent {
	
	String matName;
	String col;

	public ItemAssaultChamber(String identCol, String identName, String displayName, float acc, String col, int rgb, int rate, boolean powerBool, String oredict) {
		super("chamber", "Chamber", 1.76, displayName, acc, col, rgb, rate, powerBool, oredict, identCol, identName);
		this.setCreativeTab(AdvancedArmoury.tabComponentsCalibre);
		
		this.matName = displayName;
		this.col = col;
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
			//setMaxDamage((int)Math.round(dura * (this.durability * Math.round(item.stackTagCompound.getDouble("size")))));
		}
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister icon) {
		itemIcon = icon.registerIcon(AdvancedArmoury.modid + ":chamber");
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
