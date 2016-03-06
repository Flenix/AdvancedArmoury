package co.uk.silvania.advancedarmoury.blocks.machines;

import java.util.Random;

import co.uk.silvania.advancedarmoury.AAUtils;
import co.uk.silvania.advancedarmoury.AdvancedArmoury;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockMachine extends BlockContainer {

	public BlockMachine() {
		super(Material.iron);
		this.setCreativeTab(AdvancedArmoury.tabMachines);
		this.setHardness(1.5F);
	}

	@Override
	public void breakBlock(World world, int x, int y, int z, Block block, int meta) {
		dropItems(world, x, y, z);
		super.breakBlock(world, x, y, z, block, meta);
	}

	private void dropItems(World world, int x, int y, int z) {
		Random rand = new Random();

		TileEntity tileEntity = world.getTileEntity(x, y, z);
		if (!(tileEntity instanceof IInventory)) {
			return;
		}
		IInventory inventory = (IInventory) tileEntity;

		for (int i = 0; i < inventory.getSizeInventory(); i++) {
			ItemStack item = inventory.getStackInSlot(i);

			if (item != null && item.stackSize > 0) {
				float rx = rand.nextFloat() * 0.8F + 0.1F;
				float ry = rand.nextFloat() * 0.8F + 0.1F;
				float rz = rand.nextFloat() * 0.8F + 0.1F;

				EntityItem entityItem = new EntityItem(world, x + rx, y + ry, z + rz, new ItemStack(item.getItem(), item.stackSize, item.getItemDamage()));

				if (item.hasTagCompound()) {
					entityItem.getEntityItem().setTagCompound((NBTTagCompound) item.getTagCompound().copy());
				}

				float factor = 0.05F;
				entityItem.motionX = rand.nextGaussian() * factor;
				entityItem.motionY = rand.nextGaussian() * factor + 0.2F;
				entityItem.motionZ = rand.nextGaussian() * factor;
				world.spawnEntityInWorld(entityItem);
				item.stackSize = 0;
			}
		}
	}

	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		return null;
	}
	
	@SideOnly(Side.CLIENT) private IIcon error;
	@SideOnly(Side.CLIENT) private IIcon errorTop;
	@SideOnly(Side.CLIENT) private IIcon errorBase;
	@SideOnly(Side.CLIENT) private IIcon base1;
	@SideOnly(Side.CLIENT) private IIcon base2;
	@SideOnly(Side.CLIENT) private IIcon base3;
	@SideOnly(Side.CLIENT) private IIcon base4;
	@SideOnly(Side.CLIENT) private IIcon front1;
	@SideOnly(Side.CLIENT) private IIcon front2;
	@SideOnly(Side.CLIENT) private IIcon top1;
	@SideOnly(Side.CLIENT) private IIcon top2;
	@SideOnly(Side.CLIENT) private IIcon top3;
	@SideOnly(Side.CLIENT) private IIcon top4;
	@SideOnly(Side.CLIENT) private IIcon side;
	
	@SideOnly(Side.CLIENT) @Override
	public void registerBlockIcons(IIconRegister iconRegister) {
		error = iconRegister.registerIcon(AdvancedArmoury.modid + ":" + (this.getUnlocalizedName().substring(5)) + "_error");
		errorTop = iconRegister.registerIcon(AdvancedArmoury.modid + ":" + (this.getUnlocalizedName().substring(5)) + "_errorTop");
		errorBase = iconRegister.registerIcon(AdvancedArmoury.modid + ":" + (this.getUnlocalizedName().substring(5)) + "_errorBase");
		base1 = iconRegister.registerIcon(AdvancedArmoury.modid + ":" + (this.getUnlocalizedName().substring(5)) + "_base1");
		base2 = iconRegister.registerIcon(AdvancedArmoury.modid + ":" + (this.getUnlocalizedName().substring(5)) + "_base2");
		base3 = iconRegister.registerIcon(AdvancedArmoury.modid + ":" + (this.getUnlocalizedName().substring(5)) + "_base3");
		base4 = iconRegister.registerIcon(AdvancedArmoury.modid + ":" + (this.getUnlocalizedName().substring(5)) + "_base4");
		front1 = iconRegister.registerIcon(AdvancedArmoury.modid + ":" + (this.getUnlocalizedName().substring(5)) + "_front1");
		front2 = iconRegister.registerIcon(AdvancedArmoury.modid + ":" + (this.getUnlocalizedName().substring(5)) + "_front2");
		top1 = iconRegister.registerIcon(AdvancedArmoury.modid + ":" + (this.getUnlocalizedName().substring(5)) + "_top1");
		top2 = iconRegister.registerIcon(AdvancedArmoury.modid + ":" + (this.getUnlocalizedName().substring(5)) + "_top2");
		top3 = iconRegister.registerIcon(AdvancedArmoury.modid + ":" + (this.getUnlocalizedName().substring(5)) + "_top3");
		top4 = iconRegister.registerIcon(AdvancedArmoury.modid + ":" + (this.getUnlocalizedName().substring(5)) + "_top4");
		side = iconRegister.registerIcon(AdvancedArmoury.modid + ":" + (this.getUnlocalizedName().substring(5)) + "_side");
	}
	
	@Override
	public IIcon getIcon(int side, int meta) {
		if (side == 1) {
			return errorTop;
		} else if (side == 0) {
			return errorBase;
		} else {
			return error;
		}
	}
	
	@Override
	public IIcon getIcon(IBlockAccess block, int x, int y, int z, int side) {
		boolean connectNorth = false;
		boolean connectSouth = false;
		boolean connectEast = false;
		boolean connectWest = false;
		if (block.getBlock(x + 1, y, z) == this) { connectEast = true; }
		if (block.getBlock(x - 1, y, z) == this) { connectWest = true; }
		if (block.getBlock(x, y, z + 1) == this) { connectSouth = true; }
		if (block.getBlock(x, y, z - 1) == this) { connectNorth = true; }
		
		if (connectEast && connectWest) { return error; }
		if (connectNorth && connectSouth) { return error; }
		
		if (connectEast) {
			if (side == 2) { 				return front2; }
			if (side == 3) { 				return front1; }
			if (side == 4 || side == 5) { 	return this.side; }
			if (side == 1) { 				return top1; }
			if (side == 0) {				return base1; }
		}
		if (connectWest) {
			if (side == 2) { 				return front1; }
			if (side == 3) {				return front2; }
			if (side == 4 || side == 5) { 	return this.side; }
			if (side == 1) { 				return top2; }
			if (side == 0) {				return base2; }
		}
		
		if (connectNorth) {
			if (side == 4) { 				return front2; }
			if (side == 5) { 				return front1; }
			if (side == 2 || side == 3) { 	return this.side; }
			if (side == 1) { 				return top3; }
			if (side == 0) {				return base3; }
		}
		if (connectSouth) {
			if (side == 4) { 				return front1; }
			if (side == 5) {				return front2; }
			if (side == 2 || side == 3) { 	return this.side; }
			if (side == 1) { 				return top4; }
			if (side == 0) {				return base4; }
		}
		
		if (!connectNorth && !connectSouth && !connectEast && !connectWest) {
			if (side == 1) {	return errorTop; }
			if (side == 0) { 	return errorBase; }
			return error;
		}
		
		//2 faces north, 3 faces south, 4 faces west, 5 faces east, 1 faces up (top)
		return this.side;
	}
}
