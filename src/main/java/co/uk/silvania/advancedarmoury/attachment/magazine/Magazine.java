package co.uk.silvania.advancedarmoury.attachment.magazine;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import co.uk.silvania.advancedarmoury.AdvancedArmoury;
import co.uk.silvania.advancedarmoury.IMagazine;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class Magazine extends Item implements IMagazine {
	
	public Magazine() {
		super();
		this.setMaxStackSize(1);
		this.setCreativeTab(CreativeTabs.tabCombat);
	}
	
	//Temporary until we add custom rendering
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister iconRegister) {
		itemIcon = iconRegister.registerIcon(AdvancedArmoury.modid + ":" + (this.getUnlocalizedName().substring(5)));
	}

}
