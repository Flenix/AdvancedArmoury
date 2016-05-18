package co.uk.silvania.advancedarmoury;

import co.uk.silvania.advancedarmoury.blocks.machines.assemblytable.assault.AssaultAssemblyTableContainer;
import co.uk.silvania.advancedarmoury.blocks.machines.assemblytable.assault.AssaultAssemblyTableEntity;
import co.uk.silvania.advancedarmoury.blocks.machines.assemblytable.assault.AssaultAssemblyTableGui;
import co.uk.silvania.advancedarmoury.blocks.storage.lootcrates.LootCrateContainer;
import co.uk.silvania.advancedarmoury.blocks.storage.lootcrates.LootCrateEntity;
import co.uk.silvania.advancedarmoury.blocks.storage.lootcrates.LootCrateGui;
import co.uk.silvania.advancedarmoury.gun.inventory.assault.AssaultContainer;
import co.uk.silvania.advancedarmoury.gun.inventory.assault.AssaultGui;
import co.uk.silvania.advancedarmoury.gun.inventory.assault.AssaultIInventory;
import co.uk.silvania.advancedarmoury.gun.inventory.assault.AssaultMagazineContainer;
import co.uk.silvania.advancedarmoury.gun.inventory.assault.AssaultMagazineGui;
import co.uk.silvania.advancedarmoury.gun.inventory.assault.AssaultMagazineIInventory;
import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class GuiHandler implements IGuiHandler {
	
	public GuiHandler() {}
	
	@Override
	public Object getServerGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
		switch(id) {
			case 0: {
				return new AssaultContainer(player, player.inventory, new AssaultIInventory(player.getHeldItem()));
			}
			case 1: {
				TileEntity tileEntity = world.getTileEntity(x, y, z);
				if (tileEntity instanceof AssaultAssemblyTableEntity) {
					return new AssaultAssemblyTableContainer(player.inventory, (AssaultAssemblyTableEntity) tileEntity);
				}
			}

			case 3: {
				return new AssaultMagazineContainer(player, player.inventory, new AssaultMagazineIInventory(player.getHeldItem()));
			}
			case 4: {
				TileEntity tileEntity = world.getTileEntity(x, y, z);
				if (tileEntity instanceof LootCrateEntity) {
					return new LootCrateContainer(player.inventory, (LootCrateEntity) tileEntity);
				}
			}
		}
		return null;
	}
	
	@Override
	public Object getClientGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
		switch(id) {
			case 0: {
				return new AssaultGui((AssaultContainer) new AssaultContainer(player, player.inventory, new AssaultIInventory(player.getHeldItem())));
			}
			case 1: {
				TileEntity tileEntity = world.getTileEntity(x, y, z);
				if (tileEntity instanceof AssaultAssemblyTableEntity) {
					return new AssaultAssemblyTableGui(player.inventory, (AssaultAssemblyTableEntity) tileEntity);
				}
			}

			case 3: {
				return new AssaultMagazineGui((AssaultMagazineContainer) new AssaultMagazineContainer(player, player.inventory, new AssaultMagazineIInventory(player.getHeldItem())));
			}
			case 4: {
				TileEntity tileEntity = world.getTileEntity(x, y, z);
				if (tileEntity instanceof LootCrateEntity) {
					return new LootCrateGui(player.inventory, (LootCrateEntity) tileEntity);
				}
			}
		}
		return null;
	}

}
