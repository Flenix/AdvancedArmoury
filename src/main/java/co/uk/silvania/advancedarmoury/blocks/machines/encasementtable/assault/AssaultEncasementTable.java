package co.uk.silvania.advancedarmoury.blocks.machines.encasementtable.assault;

import java.util.Random;

import co.uk.silvania.advancedarmoury.AAUtils;
import co.uk.silvania.advancedarmoury.AdvancedArmoury;
import co.uk.silvania.advancedarmoury.blocks.machines.BlockMachine;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;

public class AssaultEncasementTable extends BlockMachine {

	public AssaultEncasementTable() {
		super();
		this.setCreativeTab(AdvancedArmoury.tabMachines);
		this.setHardness(1.5F);
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int metadata, float what, float these, float are) {
		TileEntity tileEntity = world.getTileEntity(x, y, z);
		if (tileEntity == null || player.isSneaking() || AAUtils.isHoldingWeapon(player)) {
			return false;
		}
		if (world.getBlock(x + 1, y, z) == this) {
			player.openGui(AdvancedArmoury.instance, 2, world, x, y, z);
		} else if (world.getBlock(x - 1, y, z) == this) {
			player.openGui(AdvancedArmoury.instance, 2, world, x - 1, y, z);
		} else if (world.getBlock(x, y, z + 1) == this) {
			player.openGui(AdvancedArmoury.instance, 2, world, x, y, z);
		} else if (world.getBlock(x, y, z - 1) == this) {
			player.openGui(AdvancedArmoury.instance, 2, world, x, y, z - 1);
		} else {
			if (!world.isRemote) {
				player.addChatComponentMessage(new ChatComponentText("You must have two blocks side by side for this machine to function."));
			}
		}
		return true;
	}

	@Override
	public TileEntity createNewTileEntity(World world, int p_149915_2_) {
		return new AssaultEncasementTableEntity();
	}

}
