package co.uk.silvania.advancedarmoury.items.components.generic.assault;

import java.util.List;

import co.uk.silvania.advancedarmoury.AdvancedArmoury;
import co.uk.silvania.advancedarmoury.config.ComponentGenerator;
import co.uk.silvania.advancedarmoury.config.ComponentGeneratorConfig;
import co.uk.silvania.advancedarmoury.items.components.generic.ItemComponent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class ItemAssaultComponent extends ItemComponent {
	
	String displayName;
	String identifier;
	
	double size;
	boolean acc;
	boolean fireRate;
	boolean power;
	boolean range;
	
	//MaterialName durability weight acc textcol itemcol firerate oredict power range rarity
	public ItemAssaultComponent(String componentName, String displayName, String identifier, double size, boolean acc, boolean fireRate, boolean power, boolean range) {
		super(displayName, componentName, identifier, size);
		this.componentName = componentName;
		this.displayName = displayName;
		this.identifier = identifier;
		this.size = size;		
		this.acc = acc;
		this.fireRate = fireRate;
		this.power = power;
		this.range = range;
		
		this.setCreativeTab(AdvancedArmoury.tabComponentsAssault);
	}
	
	String[] mats = ComponentGeneratorConfig.materials;
	ComponentGenerator compGen;
	
	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(Item item, CreativeTabs tab, List list) {
		for (int i = 0; i < mats.length; i++) {
			ItemStack itemComponent = new ItemStack(item);
			
			String[] splitter = mats[i].split(",");
			String materialName = splitter[0].trim();
			double durability = compGen.parseDouble(splitter[1].trim());
			int weight = compGen.parseInt(splitter[2].trim());
			float accuracy = compGen.parseFloat(splitter[3].trim());
			String textCol = splitter[4].trim();
			int rgb = compGen.parseInt(splitter[5].trim());
			int fireRate = compGen.parseInt(splitter[6].trim());
			String oreDict = splitter[7].trim();
			//int power = compGen.parseInt(splitter[8].trim());
			//int range = compGen.parseInt(splitter[9].trim());
				
			itemComponent.stackTagCompound = new NBTTagCompound();
			
			if (!this.acc) 		{ accuracy = 0; }
			if (!this.fireRate) { fireRate = 0; }
			//if (!this.power)	{ power = 0; }
			//if (!this.range)	{ range = 0;}
			
			itemComponent.stackTagCompound.setString("componentName", componentName);
			itemComponent.stackTagCompound.setString("materialName", materialName);
			itemComponent.stackTagCompound.setDouble("durability", durability);
			itemComponent.stackTagCompound.setInteger("weight", weight);
			itemComponent.stackTagCompound.setFloat("accuracy", accuracy);
			itemComponent.stackTagCompound.setString("textCol", textCol);
			itemComponent.stackTagCompound.setInteger("itemCol", rgb);
			itemComponent.stackTagCompound.setInteger("fireRate", fireRate);
			itemComponent.stackTagCompound.setString("oreDict", oreDict);
			//itemComponent.stackTagCompound.setInteger("power", power);
			//itemComponent.stackTagCompound.setInteger("range", range);

			list.add(itemComponent);
		}
	}
}
