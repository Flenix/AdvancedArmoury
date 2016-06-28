package co.uk.silvania.advancedarmoury.blocks.machines.assemblytable.assault;

import java.util.ArrayList;

import co.uk.silvania.advancedarmoury.AdvancedArmoury;
import co.uk.silvania.advancedarmoury.blocks.machines.MachineEntity;
import co.uk.silvania.advancedarmoury.gun.inventory.ItemIInventory;
import co.uk.silvania.advancedarmoury.gun.inventory.assault.AssaultIInventory;
import co.uk.silvania.advancedarmoury.items.ItemParts;
import co.uk.silvania.advancedarmoury.items.generic.Barrel;
import co.uk.silvania.advancedarmoury.items.generic.GunFrame;
import co.uk.silvania.advancedarmoury.items.generic.ReceiverCasing;
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

public class AssaultAssemblyTableEntity extends MachineEntity implements IInventory {
	
	public String gunName = "";
	public String gunTag = "";
	
	public AssaultAssemblyTableEntity() {
		super(17);
	}
	
	@Override
	public void writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		nbt.setString("gunName", gunName);
		nbt.setString("gunTag", gunTag);
	}
	
	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		this.gunName = nbt.getString("gunName");
		this.gunTag = nbt.getString("gunTag");
	}
	
	@Override
	public Packet getDescriptionPacket() {
		NBTTagCompound nbt = new NBTTagCompound();
		nbt.setInteger("partsValue", partsValue);
		nbt.setInteger("buildProgress", buildProgress);
		nbt.setString("gunName", gunName);
		nbt.setString("gunTag", gunTag);
		
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
		this.gunTag = nbt.getString("gunTag");
		
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
		
		if (isGunValid()) {
			buildTime = gunBuildTime(2, 5);
		}
		
		if (building) {
			if (partsValue >= gunCost(2, 5)) {
				if (buildProgress < buildTime) {
					buildProgress++;
				} else {
					ArrayList<EntityPlayer> players = (ArrayList<EntityPlayer>) MinecraftServer.getServer().getConfigurationManager().playerEntityList;
					for (int i = 0; i < players.size(); i++) {
						if (players.get(i).getDisplayName().equalsIgnoreCase(initiator)) {
							buildGun(players.get(i));
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
	
	public boolean buildGun(EntityPlayer player) {
		ItemStack frame = 		this.getStackInSlot(1);
		
		ItemStack barrel = 		this.getStackInSlot(2);
		ItemStack frontEnd = 	this.getStackInSlot(3);
		ItemStack receiver = 	this.getStackInSlot(4);
		ItemStack stock = 		this.getStackInSlot(5);
		
		ItemStack modifierCore = this.getStackInSlot(6);
		
		if (isGunValid()) {
			partsValue = partsValue - gunCost(2, 5);
			getDescriptionPacket();
			
			if (frame != null) {
				if (frame.getItem() instanceof GunFrame) {
					if (frame.stackTagCompound == null) {
						frame.stackTagCompound = new NBTTagCompound();
					}
					
					frame.stackTagCompound.setBoolean("hasInternals", true);
					
					ItemIInventory inventory = new AssaultIInventory(frame);
					
					inventory.setInventorySlotContents(2, barrel);
					inventory.setInventorySlotContents(3, frontEnd);
					inventory.setInventorySlotContents(4, receiver);
					inventory.setInventorySlotContents(5, stock);
					
					this.setInventorySlotContents(2, null);
					this.setInventorySlotContents(3, null);
					this.setInventorySlotContents(4, null);
					this.setInventorySlotContents(5, null);

					if (modifierCore != null) {
						inventory.setInventorySlotContents(6, modifierCore);
						this.setInventorySlotContents(6, null);
					}
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
		boolean b1 = getStackInSlot(1) != null;
		boolean b2 = getStackInSlot(2) != null;
		boolean b3 = getStackInSlot(3) != null;
		boolean b4 = getStackInSlot(4) != null;
		boolean b5 = getStackInSlot(5) != null;
		boolean barrel = false;

		if (b2) {
			int length = getStackInSlot(2).stackTagCompound.getInteger("length");
			if (length >= 8 && length <= 22) {
				barrel =  true;
			}
		}
		
		boolean calibre = false;
		if (b2 && b4) {
			if (getStackInSlot(4).getItem() instanceof ReceiverCasing && getStackInSlot(2).getItem() instanceof Barrel) {
				if (getStackInSlot(4).stackTagCompound != null && getStackInSlot(2).stackTagCompound != null) {
					if (getStackInSlot(4).stackTagCompound.getDouble("calibre") == getStackInSlot(2).stackTagCompound.getDouble("calibre")) {
						calibre = true;
					}
				}
			}
		}
		return b1 && b2 && b3 && b4 && b5 && barrel && calibre;
	}
}
