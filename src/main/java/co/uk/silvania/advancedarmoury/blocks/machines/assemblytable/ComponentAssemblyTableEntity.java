package co.uk.silvania.advancedarmoury.blocks.machines.assemblytable;

import java.util.ArrayList;

import co.uk.silvania.advancedarmoury.AdvancedArmoury;
import co.uk.silvania.advancedarmoury.blocks.machines.MachineEntity;
import co.uk.silvania.advancedarmoury.items.ItemParts;
import co.uk.silvania.advancedarmoury.items.components.assault.AssaultBolt;
import co.uk.silvania.advancedarmoury.items.components.assault.AssaultFiringMechanism;
import co.uk.silvania.advancedarmoury.items.components.assault.AssaultFiringPin;
import co.uk.silvania.advancedarmoury.items.components.assault.AssaultReceiverCasing;
import co.uk.silvania.advancedarmoury.items.generic.Chamber;
import co.uk.silvania.advancedarmoury.items.generic.ChargingHandle;
import co.uk.silvania.advancedarmoury.items.generic.FireSelector;
import co.uk.silvania.advancedarmoury.items.generic.ReceiverCasing;
import co.uk.silvania.advancedarmoury.items.generic.ReceiverFrame;
import co.uk.silvania.advancedarmoury.items.generic.Trigger;
import co.uk.silvania.advancedarmoury.skills.SkillAssaultCraft;
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

public class ComponentAssemblyTableEntity extends MachineEntity implements IInventory {

	public String componentName = "";
	public String componentTag = "";
	
	public ComponentAssemblyTableEntity() {
		super(20);
	}
	
	@Override
	public void writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		nbt.setString("componentName", componentName);
		nbt.setString("componentTag", componentTag);
	}
	
	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		this.componentName = nbt.getString("componentName");
		this.componentTag = nbt.getString("componentTag");
	}
	
	@Override
	public Packet getDescriptionPacket() {
		NBTTagCompound nbt = new NBTTagCompound();
		nbt.setInteger("partsValue", partsValue);
		nbt.setInteger("buildProgress", buildProgress);
		nbt.setString("componentName", componentName);
		nbt.setString("componentTag", componentTag);
		
		this.worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
		markDirty();
		
		return new S35PacketUpdateTileEntity(xCoord, yCoord, zCoord, 1, nbt);
	}
	
	@Override
	public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
		NBTTagCompound nbt = pkt.func_148857_g();
		
		this.partsValue = nbt.getInteger("partsValue");
		this.buildProgress = nbt.getInteger("buildProgress");
		this.componentName = nbt.getString("componentName");
		this.componentTag = nbt.getString("componentTag");
		
		this.worldObj.updateLightByType(EnumSkyBlock.Block, this.xCoord, this.yCoord, this.zCoord);
	}
	
	@Override
	public void updateEntity() {
		ItemStack parts = this.getStackInSlot(0);
		if (parts != null) {
			if (parts.getItem() instanceof ItemParts) {
				ItemParts partsItem = (ItemParts) parts.getItem();
				partsValue = partsValue + partsItem.getValue(parts.getItemDamage());
				if (parts.stackSize > 1) {
					this.setInventorySlotContents(0, new ItemStack(parts.getItem(), parts.stackSize - 1, parts.getItemDamage()));
				} else {
					this.setInventorySlotContents(0, null);
				}
			}
		}
		
		if (isComponentValid()) {
			buildTime = gunBuildTime(2, 9);
		}
		
		if (building) {
			if (partsValue >= gunCost(2, 9)) {
				if (buildProgress < buildTime) {
					buildProgress++;
				} else {
					ArrayList<EntityPlayer> players = (ArrayList<EntityPlayer>) MinecraftServer.getServer().getConfigurationManager().playerEntityList;
					for (int i = 0; i < players.size(); i++) {
						if (players.get(i).getDisplayName().equalsIgnoreCase(initiator)) {
							buildComponent(players.get(i));
							buildProgress = 0;
							gunName = "";
							gunTag = "";
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
	
	public boolean isComponentValid() {
		if (this.getStackInSlot(1) != null) {
			ItemStack itemFrame = this.getStackInSlot(1);
			if (itemFrame.getItem() instanceof ReceiverFrame) {
				ReceiverFrame receiver = (ReceiverFrame) itemFrame.getItem();
				if (receiver.gunType.equalsIgnoreCase("assault")) {
					boolean b1 = this.getStackInSlot(1) != null;
					boolean b2 = this.getStackInSlot(2) != null;
					boolean b3 = this.getStackInSlot(3) != null;
					boolean b4 = this.getStackInSlot(4) != null;
					boolean b5 = this.getStackInSlot(5) != null;
					boolean b6 = this.getStackInSlot(7) != null;
					boolean b7 = this.getStackInSlot(8) != null;
					boolean b8 = this.getStackInSlot(9) != null;
					return b1 && b2 && b3 && b4 && b5 && b6 && b7 && b8;
				}
			}
		}
		return false;
	}
	
	public void buildComponent(EntityPlayer player) {
		System.out.println("Building!");
		if (isComponentValid()) {
			System.out.println("Component is valid");
			ItemStack frame =	 			this.getStackInSlot(1);
			ItemStack itemBolt = 			this.getStackInSlot(2);
			ItemStack itemChamber = 		this.getStackInSlot(3);
			ItemStack itemFiringPin = 		this.getStackInSlot(4);
			ItemStack itemFiringMechanism = this.getStackInSlot(5);
			ItemStack itemFireSelector = 	this.getStackInSlot(6);
			ItemStack itemChargingHandle = 	this.getStackInSlot(7);
			ItemStack itemTrigger = 		this.getStackInSlot(8);
			ItemStack itemCasing = 			this.getStackInSlot(9);
			
			System.out.println("gunCost: " + gunCost(2, 9));
			partsValue -= gunCost(2, 9);
			getDescriptionPacket();
			
			if (itemCasing != null) {
				System.out.println("Frame isn't null");
				if (itemCasing.getItem() instanceof ReceiverCasing) {
					if (itemCasing.stackTagCompound == null) {
						itemCasing.stackTagCompound = new NBTTagCompound();
					}
					System.out.println("Setting values");
					AssaultBolt bolt = (AssaultBolt) itemBolt.getItem();
					Chamber chamber = (Chamber) itemChamber.getItem();
					AssaultFiringPin firingPin = (AssaultFiringPin) itemFiringPin.getItem();
					AssaultFiringMechanism firingMechanism = (itemFiringMechanism != null) ? (AssaultFiringMechanism) itemFiringMechanism.getItem() : null;
					FireSelector fireSelector = (FireSelector) itemFireSelector.getItem();
					ChargingHandle chargingHandle = (ChargingHandle) itemChargingHandle.getItem();
					Trigger trigger = (Trigger) itemTrigger.getItem();
					AssaultReceiverCasing casing = (AssaultReceiverCasing) itemCasing.getItem();
					
					itemCasing.stackTagCompound.setBoolean("hasInternals", true);
					
					itemCasing.stackTagCompound.setInteger("weight", calculateWeight());
					itemCasing.stackTagCompound.setFloat("accuracy", calculateAccuracy());
					itemCasing.stackTagCompound.setInteger("fireRate", calculateFireRate());
					itemCasing.stackTagCompound.setInteger("power", calculatePower());
					itemCasing.stackTagCompound.setInteger("durability", calculateDurability());
					
					itemCasing.stackTagCompound.setInteger("boltTextureColour", itemBolt.stackTagCompound.getInteger("itemCol"));
					itemCasing.stackTagCompound.setInteger("fireSelectorTextureColour", itemFireSelector.stackTagCompound.getInteger("itemCol"));
					itemCasing.stackTagCompound.setInteger("chargingHandleTextureColour", itemChargingHandle.stackTagCompound.getInteger("itemCol"));
					itemCasing.stackTagCompound.setInteger("triggerTextureColour", itemTrigger.stackTagCompound.getInteger("itemCol"));
					itemCasing.stackTagCompound.setDouble("calibre", chamber.getCalibre(itemChamber));
					itemCasing.stackTagCompound.setString("creator", player.getDisplayName());
					
					System.out.println("Adding XP to player!");
					SkillAssaultCraft skillAssaultCraft = (SkillAssaultCraft) SkillLevelBase.get(player, SkillAssaultCraft.staticSkillId);
					skillAssaultCraft.addXPWithUpdate(20, player);
					
					this.setInventorySlotContents(1, itemCasing);
					this.setInventorySlotContents(2, null);
					this.setInventorySlotContents(3, null);
					this.setInventorySlotContents(4, null);
					this.setInventorySlotContents(5, null);
					this.setInventorySlotContents(6, null);
					this.setInventorySlotContents(7, null);
					this.setInventorySlotContents(8, null);
					this.setInventorySlotContents(9, null);
				}
			}
		}
	}
	
	public int calculateWeight() {
		int weight = 0;
		for (int i = 2; i < 9; i++) {
			ItemStack item = this.getStackInSlot(i);
			if (item != null && item.stackTagCompound != null) {
				weight += item.stackTagCompound.getInteger("weight");
			}
		}
		return weight;
	}
	
	public float calculateAccuracy() {
		float acc = 0;
		for (int i = 2; i < 9; i++) {
			ItemStack item = this.getStackInSlot(i);
			if (item != null && item.stackTagCompound != null) {
				acc += item.stackTagCompound.getFloat("accuracy");
			}
		}
		return acc;
	}
	
	public int calculateFireRate() {
		double fireRate = 0;
		double count = 0.0;
		for (int i = 2; i < 9; i++) {
			ItemStack item = this.getStackInSlot(i);
			if (item != null && item.stackTagCompound != null) {
				if (item.stackTagCompound.getInteger("fireRate") > 0) {
					fireRate += item.stackTagCompound.getInteger("fireRate");
					count++;
				}
			}
		}
		return (int) Math.round(fireRate/count);
	}
	
	public int calculatePower() {
		System.out.println("Checking power");
		int power = 0;
		for (int i = 2; i < 9; i++) {
			ItemStack item = this.getStackInSlot(i);
			System.out.println("Slot: " + i);
			if (item != null && item.stackTagCompound != null) {
				System.out.println("Power: " + power + " + " + item.stackTagCompound.getInteger("power"));
				power += item.stackTagCompound.getInteger("power");
			}
		}
		return power;
	}
	
	public int calculateDurability() {
		int durability = 0;
		for (int i = 2; i < 9; i++) {
			ItemStack item = this.getStackInSlot(i);
			if (item != null && item.stackTagCompound != null) {
				durability += item.stackTagCompound.getInteger("durability");
			}
		}
		return durability;
	}
}
