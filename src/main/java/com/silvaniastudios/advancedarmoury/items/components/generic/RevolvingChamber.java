package com.silvaniastudios.advancedarmoury.items.components.generic;

import java.util.List;

import com.silvaniastudios.advancedarmoury.AdvancedArmoury;
import com.silvaniastudios.advancedarmoury.RarityRegistry;
import com.silvaniastudios.advancedarmoury.items.MaterialRegistry;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class RevolvingChamber extends ItemComponent {
	
	public static double[] cal = {5.56, 7.62, 9.00, 12.7};
	public static int[] capacity = {4, 5, 6, 7, 8};

	public RevolvingChamber(String componentName, String displayName) {
		super(componentName, displayName, "Revolver Component X", 4.425, false, true, true);
		this.setCreativeTab(AdvancedArmoury.tabComponentsInternal);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(Item item, CreativeTabs tab, List list) {
		for (int i = 0; i < MaterialRegistry.materials.size(); i++) {
			for (int j = 0; j < cal.length; j++) {
				for (int k = 0; k < capacity.length; k++) {
					ItemStack itemComponent = new ItemStack(item);
					
					String[] splitter = MaterialRegistry.materials.get(i).split(",");
					String materialName = splitter[0].trim();
					double durability = compGen.parseDouble(splitter[1].trim());
					int weight = compGen.parseInt(splitter[2].trim());
					float accuracy = compGen.parseFloat(splitter[3].trim());
					String textCol = splitter[4].trim();
					int rgb = compGen.parseInt(splitter[5].trim());
					String oreDict = splitter[6].trim();
					String rarity = splitter[7].trim();
					
					String cosmetic = materialName;
					
					volume = (((cal[j]*cal[j]) - ((cal[j]-0.1)*(cal[j]-0.1))) * 2.2) * capacity[k];
						
					itemComponent.stackTagCompound = new NBTTagCompound();
					
					itemComponent.stackTagCompound.setString("componentName", componentName);
					itemComponent.stackTagCompound.setString("materialName", materialName);
					itemComponent.stackTagCompound.setString("textCol", textCol);
					itemComponent.stackTagCompound.setInteger("itemCol", rgb);
					itemComponent.stackTagCompound.setString("oreDict", oreDict);
					
					itemComponent.stackTagCompound.setInteger("durability", (int) (durability*volume*10));
					itemComponent.stackTagCompound.setInteger("weight", (int) Math.ceil((weight*volume)/1000));
					
					itemComponent.stackTagCompound.setInteger("cost", (int) Math.round((volume*getWeight(itemComponent))/20));
					itemComponent.stackTagCompound.setInteger("buildTime", (int) Math.round(((volume*getDurability(itemComponent))*3)/20));
					
					if (RarityRegistry.getEnumRarity(rarity) != null) {
						itemComponent.stackTagCompound.setString("rarity", rarity);
					}
					
					if (!this.accuracyEnabled)	{ accuracy = 0; }
					if (!this.cosmeticEnabled)	{ cosmetic = ""; }
					
					itemComponent.stackTagCompound.setFloat("accuracy", accuracy);
					itemComponent.stackTagCompound.setString("cosmetic", cosmetic);
					
					itemComponent.stackTagCompound.setDouble("calibre", cal[j]);
					itemComponent.stackTagCompound.setInteger("capacity", capacity[k]);
					
					itemComponent.stackTagCompound.setString("gunType", "Revolver");
		
					list.add(itemComponent);
				}
			}
		}
	}
}
