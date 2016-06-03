package co.uk.silvania.advancedarmoury.items_old.components.generic.assault;

import java.util.List;

import co.uk.silvania.advancedarmoury.AdvancedArmoury;
import co.uk.silvania.advancedarmoury.config.ComponentGenerator;
import co.uk.silvania.advancedarmoury.config.ComponentGeneratorConfig;
import co.uk.silvania.advancedarmoury.items_old.components.generic.ItemGenericComponent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class ItemAssaultComponent extends ItemGenericComponent {
	
	//MaterialName durability weight acc textcol itemcol firerate oredict power range rarity
	public ItemAssaultComponent(String componentName, String displayName, String identifier, double size, boolean acc, boolean fireRate, boolean power, boolean range) {
		super(componentName, displayName, identifier, size, acc, fireRate, power, range);

		this.setCreativeTab(AdvancedArmoury.tabComponentsAssault);
	}
}