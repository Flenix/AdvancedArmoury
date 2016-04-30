package co.uk.silvania.advancedarmoury.blocks.storage.lootcrates;

import co.uk.silvania.advancedarmoury.AdvancedArmoury;
import co.uk.silvania.advancedarmoury.client.ClientProxy;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class TinyLootCrate extends BlockContainer {
	
	private int size;
	boolean open = false;

	public TinyLootCrate() {
		super(Material.rock);
		this.setCreativeTab(AdvancedArmoury.tabMachines);
		this.setHardness(50.0F);
		this.setResistance(2000.0F);
		this.setBlockBounds(0.25F, 0.0F, 0.25F, 0.75F, 0.5F, 0.75F);
		this.size = 1;
	}
	
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int i, float j, float k, float l) {
		LootCrateEntity tileEntity = (LootCrateEntity) world.getTileEntity(x, y, z);
		ItemStack key = tileEntity.getStackInSlot(0);
		ItemStack held = player.getHeldItem();
		boolean locked = key != null; //If key exists, locked. Else, not locked.

		if (player.capabilities.isCreativeMode) {
			player.openGui(AdvancedArmoury.instance, 4, world, x, y, z);
		} else {
			if (!world.isRemote) {
				System.out.println("List size: " + tileEntity.playerList.size());
				System.out.println("List: " + tileEntity.playerList);
				if (tileEntity.playerList.contains(player.getUniqueID().toString())) {
					int id = tileEntity.playerList.indexOf(player.getUniqueID().toString());
					if (locked && tileEntity.playerLock.get(id)) {
						System.out.println("LOCKED.");
						if (held != null && held.isItemEqual(key) && ItemStack.areItemStackTagsEqual(key, held)) {
							System.out.println("Unlocking...");
							held.stackSize--;
							if (held.stackSize <= 0) { held = null; }
							tileEntity.playerLock.ensureCapacity(id);
							tileEntity.playerLock.set(id, false);
							world.markBlockForUpdate(x, y, z);
							tileEntity.markDirty();
						}
					} else {
						System.out.println("UNLOCKED! Giving player item.");
						for (int a = 0; a < tileEntity.getSizeInventory()-1; a++) {
							ItemStack item = tileEntity.getStackInSlot(a+1);
							if (item != null && item.stackSize > 0) {
								if (!tileEntity.playerInv.isEmpty() && id < tileEntity.playerInv.size()) {
									tileEntity.playerInv.ensureCapacity(id);
									if (tileEntity.playerInv.get(id)[a] == 0) {
										
										player.inventory.addItemStackToInventory(item.copy());
										player.openContainer.detectAndSendChanges();
										tileEntity.playerInv.get(id)[a] = 1;
										world.markBlockForUpdate(x, y, z);
										tileEntity.markDirty();
									}
								}
							}
						}
					}
				} else {
					System.out.println("Added player to list. Hello player!");
					System.out.println("Setting lock status to " + locked);
					int[] playerInvArray = {0,0,0,0,0,0,0,0,0,0,0,0};
					
					if (locked) {
						if (held != null && held.isItemEqual(key) && ItemStack.areItemStackTagsEqual(key, held)) {
							locked = false;
							System.out.println("They were holding the key already! Unlocking.");
						}
					}
					
					tileEntity.playerList.add(player.getUniqueID().toString());
					tileEntity.playerInv.add(playerInvArray);
					tileEntity.playerLock.add(locked);
				
					world.markBlockForUpdate(x, y, z);
					tileEntity.markDirty();
				}
			}
		}
		return true;
	}
	
	@SideOnly(Side.CLIENT) public IIcon iconDoorFrontClosed;
	@SideOnly(Side.CLIENT) public IIcon iconDoorFrontOpen;
	@SideOnly(Side.CLIENT) public IIcon iconDoorFrontOverlayClosed;
	@SideOnly(Side.CLIENT) public IIcon iconDoorFrontOverlayOpen;
	@SideOnly(Side.CLIENT) public IIcon iconDoorFrontOverlayLocked;
	@SideOnly(Side.CLIENT) public IIcon iconDoorBack;
	@SideOnly(Side.CLIENT) public IIcon iconDoorSides;
	
	@Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister icon) {
        this.blockIcon = icon.registerIcon(AdvancedArmoury.modid + ":lootcratestandard");
        iconDoorFrontClosed = icon.registerIcon(AdvancedArmoury.modid + ":tinyLootCrateDoorClosed");
        iconDoorFrontOpen = icon.registerIcon(AdvancedArmoury.modid + ":tinyLootCrateDoorOpen");
    	iconDoorFrontOverlayClosed = icon.registerIcon(AdvancedArmoury.modid + ":tinyLootCrateDoorClosedOverlay");
    	iconDoorFrontOverlayOpen = icon.registerIcon(AdvancedArmoury.modid + ":tinyLootCrateDoorOpenOverlay");
    	iconDoorFrontOverlayLocked = icon.registerIcon(AdvancedArmoury.modid + ":tinyLootCrateDoorLockedOverlay");
    	iconDoorBack = icon.registerIcon(AdvancedArmoury.modid + ":tinyLootCrateDoorBack");
    	iconDoorSides = icon.registerIcon(AdvancedArmoury.modid + ":tinyLootCrateDoorSide");
    }
	
	@Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta) {
		//At no point does this method actually require LEGIT side and meta.
		//So, we use non-existing ones to help my custom renderer pick Icons.
		//Minecraft is stupid, but I r smartz.
		//Side 8 = door
		if (side == 8) {
			if (meta == 0) { return iconDoorFrontClosed; }
			if (meta == 1) { return iconDoorFrontOverlayClosed; }
			if (meta == 2) { return iconDoorFrontOverlayOpen; }
			if (meta == 3) { return iconDoorFrontOverlayLocked; }
			if (meta == 4) { return iconDoorSides; }
			if (meta == 5) { return iconDoorBack; }
		}
        return iconDoorBack;
    }
	
	@Override
	@SideOnly(Side.CLIENT)
	public int getRenderType() {
		return ClientProxy.tinyLootCrateRenderID;
	}
	
	@Override
	public boolean isOpaqueCube() {
		return false;
	}
	
	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}
	
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entity, ItemStack item) {
		LootCrateEntity te = (LootCrateEntity) world.getTileEntity(x, y, z);
		int direction = Math.round(entity.rotationYaw);
		if (direction > 360) { direction = direction-360; }
		if (direction < 0) { direction = direction+360; } 
		int div = (direction+22)/45;
		if (div == 8) { div = 0; } else if (div < 0) { div = 0; }
		te.rotation = div;
		if (!world.isRemote) {
			System.out.println("Direction: " + direction);
			System.out.println("Divided direction: " + div);
		}
	}

	@Override
	public TileEntity createNewTileEntity(World world, int p_149915_2_) {
		return new LootCrateEntity();
	}
}
