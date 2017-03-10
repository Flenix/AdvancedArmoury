package com.silvaniastudios.advancedarmoury.blocks.machines.assemblytable;

import java.util.ArrayList;

import com.silvaniastudios.advancedarmoury.AdvancedArmoury;
import com.silvaniastudios.advancedarmoury.blocks.machines.MachineEntity;
import com.silvaniastudios.advancedarmoury.config.AAConfig;
import com.silvaniastudios.advancedarmoury.items.ItemParts;
import com.silvaniastudios.advancedarmoury.items.components.assault.AssaultReceiverCasing;
import com.silvaniastudios.advancedarmoury.items.components.generic.ChamberLarge;
import com.silvaniastudios.advancedarmoury.items.components.generic.ItemComponent;
import com.silvaniastudios.advancedarmoury.items.components.generic.ReceiverCasing;
import com.silvaniastudios.advancedarmoury.items.components.lmg.LMGReceiverCasing;
import com.silvaniastudios.advancedarmoury.items.components.rifle.RifleReceiverCasing;
import com.silvaniastudios.advancedarmoury.items.components.smg.SMGReceiverCasing;
import com.silvaniastudios.advancedarmoury.skills.SkillFirearmCrafting;

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
			buildTime = buildTime(this.getStackInSlot(1));
		}
		
		if (building) {
			if (partsValue >= partCost(this.getStackInSlot(1))) {
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
			ItemStack itemCasing = this.getStackInSlot(1);
			if (itemCasing.getItem() instanceof ReceiverCasing) {
				ReceiverCasing receiver = (ReceiverCasing) itemCasing.getItem();
				if (receiver.gunType.equalsIgnoreCase("assault")) {
					boolean b1 = this.getStackInSlot(1) != null;
					boolean b2 = this.getStackInSlot(2) != null;
					boolean b3 = this.getStackInSlot(3) != null;
					boolean b4 = this.getStackInSlot(4) != null;
					boolean b5 = this.getStackInSlot(5) != null;
					boolean b6 = this.getStackInSlot(7) != null;
					boolean b7 = this.getStackInSlot(8) != null;
					return b1 && b2 && b3 && b4 && b5 && b6 && b7;
				}
			}
		}
		return false;
	}
	
	public void buildComponent(EntityPlayer player) {
		System.out.println("Building!");
		if (isComponentValid()) {
			System.out.println("Component is valid");
			ItemStack casing =	 			this.getStackInSlot(1);
			ItemStack itemBolt = 			this.getStackInSlot(2);
			ItemStack itemChamber = 		this.getStackInSlot(3);
			ItemStack itemFiringPin = 		this.getStackInSlot(4);
			ItemStack itemFiringMechanism = this.getStackInSlot(5);
			ItemStack itemFireSelector = 	this.getStackInSlot(6);
			ItemStack itemChargingHandle = 	this.getStackInSlot(7);
			ItemStack itemTrigger = 		this.getStackInSlot(8);
			
			System.out.println("gunCost: " + partCost(this.getStackInSlot(1)));
			partsValue -= partCost(this.getStackInSlot(1));
			getDescriptionPacket();
			
			if (casing != null) {
				System.out.println("Frame isn't null");
				if (casing.getItem() instanceof ReceiverCasing) {
					if (casing.stackTagCompound == null) {
						casing.stackTagCompound = new NBTTagCompound();
					}
					System.out.println("Setting values");
					ChamberLarge chamber = (ChamberLarge) itemChamber.getItem();
					
					casing.stackTagCompound.setBoolean("hasInternals", true);
					
					casing.stackTagCompound.setInteger("weight", calculateWeight());
					casing.stackTagCompound.setFloat("accuracy", calculateAccuracy());
					calculateFireRate(casing); //Set the fire rate, and semi auto lock boolean.
					casing.stackTagCompound.setInteger("power", calculatePower());
					casing.stackTagCompound.setInteger("durability", calculateDurability());
					
					casing.stackTagCompound.setInteger("boltTextureColour", itemBolt.stackTagCompound.getInteger("itemCol"));
					casing.stackTagCompound.setInteger("fireSelectorTextureColour", itemFireSelector.stackTagCompound.getInteger("itemCol"));
					casing.stackTagCompound.setInteger("chargingHandleTextureColour", itemChargingHandle.stackTagCompound.getInteger("itemCol"));
					casing.stackTagCompound.setInteger("triggerTextureColour", itemTrigger.stackTagCompound.getInteger("itemCol"));
					casing.stackTagCompound.setDouble("calibre", chamber.getCalibre(itemChamber));
					casing.stackTagCompound.setString("creator", player.getDisplayName());
					
					System.out.println("Adding " + (Math.ceil(calculateWeight()/5)) + " (" + calculateWeight() + " / 5) XP to player!");
					SkillFirearmCrafting skillFirearmCrafting = (SkillFirearmCrafting) SkillLevelBase.get(player, SkillFirearmCrafting.staticSkillId);
					skillFirearmCrafting.addXPWithUpdate((float) Math.ceil(calculateWeight()/5), player);
					
					this.setInventorySlotContents(1, casing);
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
		for (int i = 1; i < 9; i++) {
			ItemStack item = this.getStackInSlot(i);
			if (item != null && item.stackTagCompound != null) {
				weight += item.stackTagCompound.getInteger("weight");
			}
		}
		return weight;
	}
	
	public float calculateAccuracy() {
		float acc = 0;
		for (int i = 1; i < 9; i++) {
			ItemStack item = this.getStackInSlot(i);
			if (item != null && item.stackTagCompound != null) {
				acc += item.stackTagCompound.getFloat("accuracy");
			}
		}
		return acc;
	}
	
	public void calculateFireRate(ItemStack casing) {
		//Calculate semi and full-auto fire rates
		//Value is rounds per second - use chart to see rounds per tick and how many ticks between.
		//Semi auto is capped at 20RPS - ie as fast as you can right click
		//Bolt arm (Any rifle) adds 10 ticks
		
		ItemStack bolt = this.getStackInSlot(2);
		ItemStack mech = this.getStackInSlot(4);
		if (bolt != null && mech != null && bolt.stackTagCompound != null && mech.stackTagCompound != null) {
			int boltWeight = bolt.stackTagCompound.getInteger("weight");
			int mechWeight = mech.stackTagCompound.getInteger("weight");
			double boltIntegrity = stats.getIntegrity(getMaterial(bolt));
			double mechIntegrity = stats.getIntegrity(getMaterial(mech));
			double negMod = 0;
			
			if (mechIntegrity < boltIntegrity) {
				negMod = (boltIntegrity - mechIntegrity)/10;
			}
			
			double frBase = (boltWeight + mechWeight + 1)- (((boltWeight + mechWeight + 1)/100.0)*negMod) / 50;
		}
		
		casing.stackTagCompound.setInteger("fireRate", 0);
		casing.stackTagCompound.setBoolean("semiLocked", false);
	}
	
	public int calculateFireRate() {
		ItemStack bolt = this.getStackInSlot(2);
		ItemStack mech = this.getStackInSlot(5);
		if (bolt != null && mech != null && bolt.stackTagCompound != null && mech.stackTagCompound != null) {
			int boltWeight = bolt.stackTagCompound.getInteger("weight");
			int mechWeight = mech.stackTagCompound.getInteger("weight");
			double boltIntegrity = stats.getIntegrity(getMaterial(bolt));
			double mechIntegrity = stats.getIntegrity(getMaterial(mech));
			double negMod = 0;
			int weight = boltWeight + mechWeight;
			
			if (mechIntegrity < boltIntegrity) {
				negMod = (boltIntegrity - mechIntegrity)/10;
				return (int) Math.ceil((weight - ((weight/100.0)*negMod)) / 350.0);
			}
			
			return (int) Math.ceil(weight / 350.0);
		}
		return 0;
	}
	
	public String fireRateString() {
		return "";
	}
	
	public int calculatePower() {
		ItemStack bolt = this.getStackInSlot(2);
		ItemStack pin = this.getStackInSlot(4);
		double typeMod = 1;
		if (this.getStackInSlot(1) != null) {
			if (this.getStackInSlot(1).getItem() instanceof AssaultReceiverCasing) {    typeMod = 1.3;  }
			else if (this.getStackInSlot(1).getItem() instanceof RifleReceiverCasing) { typeMod = 2.0;  }
			else if (this.getStackInSlot(1).getItem() instanceof SMGReceiverCasing) {   typeMod = 0.8;  }
			else if (this.getStackInSlot(1).getItem() instanceof LMGReceiverCasing) {   typeMod = 1.65; }
		}
		
		if (bolt != null && pin != null && bolt.stackTagCompound != null && pin.stackTagCompound != null) {
			int boltWeight = bolt.stackTagCompound.getInteger("weight");
			int pinWeight = pin.stackTagCompound.getInteger("weight");
			double boltIntegrity = stats.getIntegrity(getMaterial(bolt));
			double pinIntegrity = stats.getIntegrity(getMaterial(pin));
			double negMod = 0;
			
			if (pinIntegrity < boltIntegrity) {
				negMod = (boltIntegrity - pinIntegrity)/10;
			}
			
			double powerBase = ((boltWeight + pinWeight) / AAConfig.damageModifier) + 1;
			return (int) (Math.floor((powerBase - ((powerBase/100.0)*negMod)))*typeMod);
		}
		return 0;
	}
	
	public int calculateDurability() {
		int durability = 0;
		for (int i = 1; i < 9; i++) {
			ItemStack item = this.getStackInSlot(i);
			if (item != null && item.stackTagCompound != null) {
				durability += item.stackTagCompound.getInteger("durability");
			}
		}
		return durability;
	}
}
