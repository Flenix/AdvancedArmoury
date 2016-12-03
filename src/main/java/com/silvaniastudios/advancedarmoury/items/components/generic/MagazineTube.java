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

public class MagazineTube extends ItemComponent {
	
	public static int[] length = {12, 15, 18, 21, 24};

	public MagazineTube(String componentName, String displayName) {
		super(componentName, displayName, "Pump Action Shotgun Component", 0.3, false, true, false);
		this.setCreativeTab(AdvancedArmoury.tabComponentsExternal);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(Item item, CreativeTabs tab, List list) {
		for (int i = 0; i < MaterialRegistry.materials.size(); i++) {
			for (int k = 0; k < length.length; k++) {
				ItemStack itemComponent = new ItemStack(item);
					
				volume = 7.38 * length[k]; //(18.5*18.5) - (18.4*18.4) * 2 = 7.38 - Hardcoded math as there's only one "calibre" here.
					
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
						
				itemComponent.stackTagCompound = new NBTTagCompound();
					
				itemComponent.stackTagCompound.setString("componentName", componentName);
				itemComponent.stackTagCompound.setString("materialName", materialName);
				itemComponent.stackTagCompound.setString("textCol", textCol);
				itemComponent.stackTagCompound.setInteger("itemCol", rgb);
				itemComponent.stackTagCompound.setString("oreDict", oreDict);
					
				itemComponent.stackTagCompound.setInteger("durability", (int) ((durability*volume*10)*length[k]));
				itemComponent.stackTagCompound.setInteger("weight", (int) Math.ceil(((weight*volume)/1000))*length[k]);
					
				if (RarityRegistry.getEnumRarity(rarity) != null) {
					itemComponent.stackTagCompound.setString("rarity", rarity);
				}
				
				itemComponent.stackTagCompound.setFloat("accuracy", accuracy);
				itemComponent.stackTagCompound.setString("cosmetic", cosmetic);
				
				itemComponent.stackTagCompound.setInteger("length", length[k]);
				itemComponent.stackTagCompound.setInteger("capacity", length[k]/3);
		
				list.add(itemComponent);
			}
		}
	}
}
