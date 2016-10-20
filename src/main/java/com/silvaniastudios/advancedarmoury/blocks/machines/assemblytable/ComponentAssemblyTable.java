package com.silvaniastudios.advancedarmoury.blocks.machines.assemblytable;

import com.silvaniastudios.advancedarmoury.AAUtils;
import com.silvaniastudios.advancedarmoury.AdvancedArmoury;
import com.silvaniastudios.advancedarmoury.GuiHandler;
import com.silvaniastudios.advancedarmoury.blocks.machines.BlockMachine;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;

public class ComponentAssemblyTable extends BlockMachine {
	
	public ComponentAssemblyTable() {
		super();
	}
	
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int metadata, float what, float these, float are) {
		System.out.println("onBlockActivated");
		TileEntity tileEntity = world.getTileEntity(x, y, z);
		if (tileEntity == null || player.isSneaking() || AAUtils.isHoldingWeapon(player)) {
			System.out.println("TE null/player sneaking/player holding weapon.");
			return false;
		}
		System.out.println("Attempt GUI open.");
		player.openGui(AdvancedArmoury.instance, GuiHandler.componentAssemblyGuiId, world, x, y, z);
		return true;
	}
	
	@Override
	public TileEntity createNewTileEntity(World world, int p_149915_2_) {
		return new ComponentAssemblyTableEntity();
	}

}
