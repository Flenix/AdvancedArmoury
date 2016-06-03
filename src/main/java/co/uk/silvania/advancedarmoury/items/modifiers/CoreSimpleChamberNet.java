package co.uk.silvania.advancedarmoury.items.modifiers;

import java.util.List;
import java.util.Random;

import co.uk.silvania.advancedarmoury.AAUtils;
import co.uk.silvania.advancedarmoury.AdvancedArmoury;
import co.uk.silvania.advancedarmoury.IRound;
import co.uk.silvania.advancedarmoury.gun.inventory.ItemIInventory;
import co.uk.silvania.advancedarmoury.gun.inventory.assault.AssaultIInventory;
import co.uk.silvania.advancedarmoury.items.EnumRarity;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class CoreSimpleChamberNet extends ModifierCoreBase {
	
	public CoreSimpleChamberNet(EnumRarity rarity) {
		super(rarity);
	}
	
	@Override
	public void onFireWeapon(ItemStack item, EntityPlayer player) {
		Random rand = new Random();
		int random = rand.nextInt(5);
		if (random == 3) {
			ItemIInventory inventory = new AssaultIInventory(item);
			ItemStack round = inventory.getStackInSlot(1);
			if (round != null && round.getItem() instanceof IRound) {
				//Null check on round in case of creative. Some modifiers should work in creative - this one shouldn't though.
				if (round.stackTagCompound != null) {
					String casing = round.stackTagCompound.getString("case");
					int calId = round.stackTagCompound.getInteger("calibreId");
					ItemStack itemCasing = new ItemStack(GameRegistry.findItem(AdvancedArmoury.modid, "case" + casing + calId));
					if (itemCasing != null) {
						if (!player.inventory.addItemStackToInventory(itemCasing)) {
							player.worldObj.spawnEntityInWorld(new EntityItem(player.worldObj, player.posX, player.posY, player.posZ, itemCasing));
						}
					}
				}
			}
		}
	}
	
	@SideOnly(Side.CLIENT)
	@Override
    public void addInformation(ItemStack item, EntityPlayer player, List list, boolean holding) {
		super.addInformation(item, player, list, holding);
		list.add("");
		list.add("1 in 5 chance to catch an ejected shell.");
	}
}
