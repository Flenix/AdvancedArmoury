package co.uk.silvania.advancedarmoury.items.magazines;

import java.util.List;

import org.lwjgl.input.Mouse;

import co.uk.silvania.advancedarmoury.AdvancedArmoury;
import co.uk.silvania.advancedarmoury.IMagazine;
import co.uk.silvania.advancedarmoury.gun.inventory.ItemInventory;
import co.uk.silvania.advancedarmoury.gun.inventory.assault.AssaultMagazineIInventory;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemMagazine extends ItemInventory implements IMagazine {
	
	public String magType;
	public int magId;
	
	public ItemMagazine(String type, int id, int magSize) {
		super();
		magType = type;
		magId = id;
		invSize = magSize;
	}
	
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister icon) {
		itemIcon = icon.registerIcon(AdvancedArmoury.modid + ":" + this.getUnlocalizedName().substring(5));
	}
	
	@Override
	public ItemStack onItemRightClick(ItemStack item, World world, EntityPlayer player) {
		player.openGui(AdvancedArmoury.instance, 3, world, (int) player.posX, (int) player.posY, (int) player.posZ);
		return item;
	}
	
	public void onUpdate(ItemStack item, World world, Entity entity, int par4, boolean par5) {
		if (entity instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) entity;
			
			if (player.getHeldItem() != null) {
				if (player.getHeldItem().getItem() instanceof ItemMagazine) {
					AssaultMagazineIInventory inv = new AssaultMagazineIInventory(player.getHeldItem());
					pushUpInventory(inv);
				}
			}
		}
	}
	
	@SideOnly(Side.CLIENT)
    public void addInformation(ItemStack item, EntityPlayer player, List list, boolean isInHand) {
		if (item != null) {
			if (item.getItem() instanceof ItemMagazine) {
				AssaultMagazineIInventory inv = new AssaultMagazineIInventory(item);
				int rounds = 0;
				for (int i = 0; i < inv.getSizeInventory(); i++) {
					if (inv.getStackInSlot(i) != null) {
						rounds++;
					}
				}
				
				list.add(rounds + "/" + inv.getSizeInventory());
				
			}
		}
	}
	
	
	public static void pushUpInventory(AssaultMagazineIInventory inv) {
		for (int i = 0; i < inv.getSizeInventory() - 1; i++) {
			if (inv.getStackInSlot(i) == null) {
				boolean stackFound = false;
				for (int j = i+1; j < inv.getSizeInventory(); j++) {
					if (inv.getStackInSlot(j) != null) {
						if (!stackFound) {
							inv.setInventorySlotContents(i, inv.getStackInSlot(j));
							inv.setInventorySlotContents(j, null);
							stackFound = true;
							continue;
						}
					}
				}
			}
		}
	}
}
