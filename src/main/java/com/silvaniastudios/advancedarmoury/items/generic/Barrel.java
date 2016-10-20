package com.silvaniastudios.advancedarmoury.items.generic;

import java.util.List;

import com.silvaniastudios.advancedarmoury.AdvancedArmoury;
import com.silvaniastudios.advancedarmoury.RarityRegistry;
import com.silvaniastudios.advancedarmoury.items.MaterialRegistry;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;

public class Barrel extends ItemComponent {
	
	public static int[] length = {4, 6, 8, 10, 15, 18, 20, 22, 24, 27, 30};
	public static double[] cal = {5.56, 7.62, 9.00, 12.7};

	public Barrel(String componentName, String displayName) {
		super(componentName, displayName, "Barrel", 0.3, true, false, true, true, false, true);
		this.setCreativeTab(AdvancedArmoury.tabComponentsCalibre);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(Item item, CreativeTabs tab, List list) {
		for (int i = 0; i < MaterialRegistry.materials.size(); i++) {
			for (int j = 0; j < cal.length; j++) {
				for (int k = 0; k < length.length; k++) {
					ItemStack itemComponent = new ItemStack(item);
					
					String[] splitter = MaterialRegistry.materials.get(i).split(",");
					String materialName = splitter[0].trim();
					double durability = compGen.parseDouble(splitter[1].trim());
					int weight = compGen.parseInt(splitter[2].trim());
					float accuracy = compGen.parseFloat(splitter[3].trim());
					String textCol = splitter[4].trim();
					int rgb = compGen.parseInt(splitter[5].trim());
					int fireRate = compGen.parseInt(splitter[6].trim());
					String oreDict = splitter[7].trim();
					int power = compGen.parseInt(splitter[8].trim());
					int range = compGen.parseInt(splitter[9].trim());
					String rarity = splitter[10].trim();
					
					String cosmetic = materialName;
					
					size = size*length[k];
						
					itemComponent.stackTagCompound = new NBTTagCompound();
					
					itemComponent.stackTagCompound.setString("componentName", componentName);
					itemComponent.stackTagCompound.setString("materialName", materialName);
					itemComponent.stackTagCompound.setString("textCol", textCol);
					itemComponent.stackTagCompound.setInteger("itemCol", rgb);
					itemComponent.stackTagCompound.setString("oreDict", oreDict);
					
					itemComponent.stackTagCompound.setDouble("durability", durability*size*50);
					itemComponent.stackTagCompound.setInteger("weight", (int) Math.round(weight*size));
					
					itemComponent.stackTagCompound.setInteger("cost", (int) Math.round((size*getWeight(itemComponent))/20));
					itemComponent.stackTagCompound.setInteger("buildTime", (int) Math.round(((size*getDurability(itemComponent))*3)/20));
					
					if (RarityRegistry.getEnumRarity(rarity) != null) {
						itemComponent.stackTagCompound.setString("rarity", rarity);
					}
					
					if (!this.accuracyEnabled)	{ accuracy = 0; }
					if (!this.powerEnabled)		{ power = 0; }
					if (!this.rangeEnabled)	 	{ range = 0; }
					if (!this.cosmeticEnabled)	{ cosmetic = ""; }
					if (!this.fireRateEnabled)	{ fireRate = 0; }
					
					itemComponent.stackTagCompound.setFloat("accuracy", accuracy);
					itemComponent.stackTagCompound.setInteger("power", power);
					itemComponent.stackTagCompound.setInteger("range", range);
					itemComponent.stackTagCompound.setString("cosmetic", cosmetic);
					itemComponent.stackTagCompound.setInteger("fireRate", fireRate);
					
					itemComponent.stackTagCompound.setInteger("length", length[k]);
					itemComponent.stackTagCompound.setDouble("calibre", cal[j]);
					
					if (componentName.toLowerCase().contains("assault")) { itemComponent.stackTagCompound.setString("gunType", "Assault"); }
					if (componentName.toLowerCase().contains("rifle"))	 { itemComponent.stackTagCompound.setString("gunType", "Rifle"); }
					if (componentName.toLowerCase().contains("lmg"))	 { itemComponent.stackTagCompound.setString("gunType", "LMG"); }
					if (componentName.toLowerCase().contains("smg")) 	 { itemComponent.stackTagCompound.setString("gunType", "SMG"); }
					if (componentName.toLowerCase().contains("pistol"))  { itemComponent.stackTagCompound.setString("gunType", "Pistol"); }
					if (componentName.toLowerCase().contains("shotgun")) { itemComponent.stackTagCompound.setString("gunType", "Shotgun"); }
		
					list.add(itemComponent);
				}
			}
		}
	}

}
