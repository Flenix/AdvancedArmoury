package co.uk.silvania.advancedarmoury.blocks.machines.assemblytable.assault;

import java.util.ArrayList;

import co.uk.silvania.advancedarmoury.AdvancedArmoury;
import co.uk.silvania.advancedarmoury.blocks.machines.MachineEntity;
import co.uk.silvania.advancedarmoury.gun.inventory.ItemIInventory;
import co.uk.silvania.advancedarmoury.gun.inventory.assault.AssaultIInventory;
import co.uk.silvania.advancedarmoury.items.ItemParts;
import co.uk.silvania.advancedarmoury.items.components.generic.GunFrame;
import co.uk.silvania.advancedarmoury.items.components.generic.ItemBarrel;
import co.uk.silvania.advancedarmoury.items.components.generic.ItemComponent;
import co.uk.silvania.advancedarmoury.items.components.generic.assault.ItemAssaultChamber;
import co.uk.silvania.advancedarmoury.skills.SkillAssaultCraft;
import co.uk.silvania.advancedarmoury.skills.SkillAssaultRifles;
import co.uk.silvania.rpgcore.skills.SkillLevelBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.EnumSkyBlock;

public class AssaultAssemblyTableEntity extends MachineEntity implements IInventory {
	
	public String gunName = "";
	
	public AssaultAssemblyTableEntity() {
		super(17);
	}
	
	@Override
	public void writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		nbt.setString("gunName", gunName);
	}
	
	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		this.gunName = nbt.getString("gunName");
	}
	
	@Override
	public Packet getDescriptionPacket() {
		NBTTagCompound nbt = new NBTTagCompound();
		nbt.setInteger("partsValue", partsValue);
		nbt.setInteger("buildProgress", buildProgress);
		nbt.setString("gunName", gunName);
		
		this.worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
		markDirty();
		
		return new S35PacketUpdateTileEntity(xCoord, yCoord, zCoord, 1, nbt);
	}
	
	@Override
	public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
		NBTTagCompound nbt = pkt.func_148857_g();
		
		this.partsValue = nbt.getInteger("partsValue");
		this.buildProgress = nbt.getInteger("buildProgress");
		this.gunName = nbt.getString("gunName");
		
		this.worldObj.updateLightByType(EnumSkyBlock.Block, this.xCoord, this.yCoord, this.zCoord);
	}
	
	@Override
	public void updateEntity() {
		ItemStack parts = this.getStackInSlot(1);
		if (parts != null) {
			if (parts.getItem() instanceof ItemParts) {
				ItemParts partsItem = (ItemParts) parts.getItem();
				partsValue = partsValue + partsItem.getValue(parts.getItemDamage());
				if (parts.stackSize > 1) {
					this.setInventorySlotContents(1, new ItemStack(parts.getItem(), parts.stackSize - 1, parts.getItemDamage()));
				} else {
					this.setInventorySlotContents(1, null);
				}
			}
		}
		
		if (isGunValid()) {
			int time = 0;
			for (int i = 3; i <= 16; i++) {
				ItemStack itemComponent = this.getStackInSlot(i);
				if (itemComponent != null) {
					if (itemComponent.getItem() instanceof ItemComponent) {
						ItemComponent component = (ItemComponent) itemComponent.getItem();
						time = time + component.buildTime(itemComponent);
					}
				}
			}
			buildTime = time;
		}
		
		if (building) {
			if (partsValue >= gunCost(3, 16)) {
				if (buildProgress < buildTime) {
					buildProgress++;
				} else {
					ArrayList<EntityPlayer> players = (ArrayList<EntityPlayer>) MinecraftServer.getServer().getConfigurationManager().playerEntityList;
					for (int i = 0; i < players.size(); i++) {
						if (players.get(i).getDisplayName().equalsIgnoreCase(initiator)) {
							buildGun(players.get(i));
							buildProgress = 0;
							gunName = "";
							building = false;
							return;
						}
					}
					AdvancedArmoury.println("Assault Table failed to find creating player. Gun will not be created.");
				}
			} else {
				building = false;
			}
		} else {
			if (buildProgress > 0) {
				buildProgress = 0;
			}
		}
	}
	
	public boolean buildGun(EntityPlayer player) {
		ItemStack frame = 			this.getStackInSlot(0);
		
		ItemStack bolt = 			this.getStackInSlot(4);
		ItemStack boltCarrier = 	this.getStackInSlot(5);
		ItemStack chargingHandle = 	this.getStackInSlot(6);
		ItemStack ejector = 		this.getStackInSlot(7);
		ItemStack extractor = 		this.getStackInSlot(8);
		ItemStack firingPin = 		this.getStackInSlot(9);
		ItemStack fpRetainerPin = 	this.getStackInSlot(10);
		
		ItemStack pistonGasChamber= this.getStackInSlot(11);
		ItemStack springGasFeed = 	this.getStackInSlot(12);
		ItemStack piston = 			this.getStackInSlot(13);
		
		ItemStack chamber = 		this.getStackInSlot(3);
		ItemStack barrel = 			this.getStackInSlot(14);
		
		ItemStack trigger = 		this.getStackInSlot(15);
		ItemStack fireSelector = 	this.getStackInSlot(16);
		
		ItemStack modifierCore = 	this.getStackInSlot(2);
		
		if (isGunValid()) {
			boolean isGasStyle = this.getStackInSlot(13) == null;
			boolean error = false;
			
			partsValue = partsValue - gunCost(3, 16);
			getDescriptionPacket();
			
			if (frame != null) {
				if (frame.getItem() instanceof GunFrame) {
					if (frame.stackTagCompound == null) {
						frame.stackTagCompound = new NBTTagCompound();
					}
					
					frame.stackTagCompound.setBoolean("hasInternals", true);
					
					ItemIInventory inventory = new AssaultIInventory(frame);
					
					inventory.setInventorySlotContents(2, bolt);
					inventory.setInventorySlotContents(3, boltCarrier);
					inventory.setInventorySlotContents(4, chargingHandle);
					inventory.setInventorySlotContents(5, ejector);
					inventory.setInventorySlotContents(6, extractor);
					inventory.setInventorySlotContents(7, firingPin);
					inventory.setInventorySlotContents(8, fpRetainerPin);
					
					this.setInventorySlotContents(3, null);
					this.setInventorySlotContents(4, null);
					this.setInventorySlotContents(5, null);
					this.setInventorySlotContents(6, null);
					this.setInventorySlotContents(7, null);
					this.setInventorySlotContents(8, null);
					this.setInventorySlotContents(9, null);
					this.setInventorySlotContents(10, null);
					
					inventory.setInventorySlotContents(9, pistonGasChamber);
					inventory.setInventorySlotContents(10, springGasFeed);
					this.setInventorySlotContents(11, null);
					this.setInventorySlotContents(12, null);
					
					if (piston != null) {
						inventory.setInventorySlotContents(11, piston);
						this.setInventorySlotContents(13, null);
					}
					
					inventory.setInventorySlotContents(12, chamber);
					inventory.setInventorySlotContents(13, barrel);
					inventory.setInventorySlotContents(14, trigger);
					inventory.setInventorySlotContents(15, fireSelector);
					this.setInventorySlotContents(14, null);
					this.setInventorySlotContents(15, null);
					this.setInventorySlotContents(16, null);
					
					if (modifierCore != null) {
						inventory.setInventorySlotContents(16, modifierCore);
						this.setInventorySlotContents(2, null);
					}
					
					frame.stackTagCompound.setInteger("weight", 0);
					frame.stackTagCompound.setFloat("accuracy", 0);
					frame.stackTagCompound.setInteger("fireRate", 0);
					frame.stackTagCompound.setInteger("power", 0);
					frame.stackTagCompound.setInteger("durability", 0);
					frame.stackTagCompound.setString("creator", player.getDisplayName());
					
					System.out.println("Adding XP to player!");
					SkillAssaultCraft skillAssaultCraft = (SkillAssaultCraft) SkillLevelBase.get(player, SkillAssaultCraft.staticSkillId);
					skillAssaultCraft.addXPWithUpdate(20, player);
				}
			}
			return true;
		}
		return false;
	}
	
	public boolean isGunValid() {
		boolean bo1 = getStackInSlot(4) != null;
		boolean bo2 = getStackInSlot(5) != null;
		boolean bo3 = getStackInSlot(6) != null;
		boolean bo4 = getStackInSlot(7) != null;
		boolean bo5 = getStackInSlot(8) != null;
		boolean bo6 = getStackInSlot(9) != null;
		boolean bo7 = getStackInSlot(10) != null;
		boolean bolt = false;
		if (bo1 && bo2 && bo3 && bo4 && bo5 && bo6 && bo7) {
			bolt = true;
		}
		
		boolean i1 = getStackInSlot(11) != null;
		boolean i2 = getStackInSlot(12) != null;
		boolean i3 = getStackInSlot(13) != null;
		boolean internals = false;
		
		ItemStack item1 = getStackInSlot(11);
		ItemStack item2 = getStackInSlot(12);
		ItemStack item3 = getStackInSlot(13);
		
		boolean internalsDI = false;
		boolean internalsPiston = false;
		
		if (i1 && i2) {
			if (item1.stackTagCompound != null && item2.stackTagCompound != null) {
				if (item1.stackTagCompound.getString("partName").equalsIgnoreCase("gasChamber") && item2.stackTagCompound.getString("partName").equalsIgnoreCase("gasFeed")) {
					internalsDI = true;
					if (i3) {
						internals = false; //Should be EMPTY in the third slot.
					} else {
						internals = true;
					}
				} else if (item1.stackTagCompound.getString("partName").equalsIgnoreCase("pistonChamber") && item2.stackTagCompound.getString("partName").equalsIgnoreCase("spring")) {
					if (i3) {
						if (item3.stackTagCompound != null) {
							if (item3.stackTagCompound.getString("partName").equalsIgnoreCase("piston")) {
								internalsPiston = true;
								internals = true;
							}
						}
					}
				}
			}
		}

		boolean ba1 = getStackInSlot(3) != null;
		boolean ba2 = getStackInSlot(14) != null;
		boolean barrel = false;
		
		if (ba1 && ba2) {
			if (getStackInSlot(14).stackTagCompound != null) {
				int length = getStackInSlot(14).stackTagCompound.getInteger("length");
				if (length >= 8 && length <= 22) {
					barrel =  true;
				}
			}
		}
		
		boolean calibre = false;
		if (getStackInSlot(3) != null && getStackInSlot(14) != null) {
			if (getStackInSlot(3).getItem() instanceof ItemAssaultChamber && getStackInSlot(14).getItem() instanceof ItemBarrel) {
				if (getStackInSlot(3).stackTagCompound != null && getStackInSlot(14).stackTagCompound != null) {
					if (getStackInSlot(3).stackTagCompound.getDouble("calibre") == getStackInSlot(14).stackTagCompound.getDouble("calibre")) {
						calibre = true;
					}
				}
			}
		}

		boolean fm1 = getStackInSlot(15) != null;
		boolean fm2 = getStackInSlot(16) != null;
		boolean firingSystem = false;
		if (fm1 && fm2) {
			firingSystem = true;
		}

		if (bolt && internals && barrel && calibre && firingSystem) {
			return true;
		}
		return false;
	}
}
