package com.silvaniastudios.advancedarmoury;

import com.silvaniastudios.advancedarmoury.blocks.machines.assemblytable.ComponentAssemblyTableContainer;
import com.silvaniastudios.advancedarmoury.blocks.machines.assemblytable.ComponentAssemblyTableEntity;
import com.silvaniastudios.advancedarmoury.blocks.machines.assemblytable.ComponentAssemblyTableGui;
import com.silvaniastudios.advancedarmoury.blocks.machines.assemblytable.assault.AssaultAssemblyTableContainer;
import com.silvaniastudios.advancedarmoury.blocks.machines.assemblytable.assault.AssaultAssemblyTableEntity;
import com.silvaniastudios.advancedarmoury.blocks.machines.assemblytable.assault.AssaultAssemblyTableGui;
import com.silvaniastudios.advancedarmoury.blocks.storage.lootcrates.LootCrateContainer;
import com.silvaniastudios.advancedarmoury.blocks.storage.lootcrates.LootCrateEntity;
import com.silvaniastudios.advancedarmoury.blocks.storage.lootcrates.LootCrateGui;
import com.silvaniastudios.advancedarmoury.gun.inventory.assault.AssaultContainer;
import com.silvaniastudios.advancedarmoury.gun.inventory.assault.AssaultGui;
import com.silvaniastudios.advancedarmoury.gun.inventory.assault.AssaultIInventory;
import com.silvaniastudios.advancedarmoury.gun.inventory.assault.AssaultMagazineContainer;
import com.silvaniastudios.advancedarmoury.gun.inventory.assault.AssaultMagazineGui;
import com.silvaniastudios.advancedarmoury.gun.inventory.assault.AssaultMagazineIInventory;

import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class GuiHandler implements IGuiHandler {
	
	public GuiHandler() {}
	
	public static int componentAssemblyGuiId = 1;
	public static int assaultAssemblyGuiId = 2;
	
	@Override
	public Object getServerGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
		TileEntity tileEntity = world.getTileEntity(x, y, z);
		InventoryPlayer inv = player.inventory;
		
		if (id == componentAssemblyGuiId && tileEntity instanceof ComponentAssemblyTableEntity) {
			return new ComponentAssemblyTableContainer(inv, (ComponentAssemblyTableEntity) tileEntity);
		}
		
		if (id == assaultAssemblyGuiId && tileEntity instanceof AssaultAssemblyTableEntity) {
			return new AssaultAssemblyTableContainer(inv, (AssaultAssemblyTableEntity) tileEntity);
		}

		switch(id) {
			case 0: { return new AssaultContainer(player, inv, new AssaultIInventory(player.getHeldItem())); }
			case 3: { return new AssaultMagazineContainer(player, inv, new AssaultMagazineIInventory(player.getHeldItem())); }
			case 4: {
				if (tileEntity instanceof LootCrateEntity) {
					return new LootCrateContainer(inv, (LootCrateEntity) tileEntity);
				}
			}
		}
		return null;
	}
	
	@Override
	public Object getClientGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
		TileEntity tileEntity = world.getTileEntity(x, y, z);
		InventoryPlayer inv = player.inventory;
		
		if (id == componentAssemblyGuiId && tileEntity instanceof ComponentAssemblyTableEntity) {
			return new ComponentAssemblyTableGui(inv, (ComponentAssemblyTableEntity) tileEntity);
		}
		
		if (id == assaultAssemblyGuiId && tileEntity instanceof AssaultAssemblyTableEntity) {
			return new AssaultAssemblyTableGui(inv, (AssaultAssemblyTableEntity) tileEntity);
		}
		
		
		switch(id) {
			case 0: {
				return new AssaultGui((AssaultContainer) new AssaultContainer(player, player.inventory, new AssaultIInventory(player.getHeldItem())));
			}
			case 3: {
				return new AssaultMagazineGui((AssaultMagazineContainer) new AssaultMagazineContainer(player, player.inventory, new AssaultMagazineIInventory(player.getHeldItem())));
			}
			case 4: {
				if (tileEntity instanceof LootCrateEntity) {
					return new LootCrateGui(inv, (LootCrateEntity) tileEntity);
				}
			}
		}
		return null;
	}

}
