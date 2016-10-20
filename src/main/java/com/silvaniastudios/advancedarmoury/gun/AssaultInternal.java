package com.silvaniastudios.advancedarmoury.gun;

import java.util.List;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import com.silvaniastudios.advancedarmoury.AdvancedArmoury;
import com.silvaniastudios.advancedarmoury.IMagazine;
import com.silvaniastudios.advancedarmoury.gun.inventory.ItemIInventory;
import com.silvaniastudios.advancedarmoury.gun.inventory.assault.AssaultContainer;
import com.silvaniastudios.advancedarmoury.gun.inventory.assault.AssaultIInventory;
import com.silvaniastudios.advancedarmoury.items.assets.AssetReceiver;
import com.silvaniastudios.advancedarmoury.items.assets.ComponentReceiver;
import com.silvaniastudios.advancedarmoury.items.attachment.magazine.MagazineInventory;
import com.silvaniastudios.advancedarmoury.network.GunEventPacket;
import com.silvaniastudios.advancedarmoury.network.GunGuiPacket;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class AssaultInternal extends Item {
	
	public ComponentReceiver partType;
	public double range = 250.0D;
	public int fireRate = 10;
	public String displayName;
	private Entity pointedEntity;
	
	public AssaultInternal() {
		maxStackSize = 1;
		GameRegistry.registerItem(this, partType.unlocalizedName, AdvancedArmoury.modid);
		setCreativeTab(AdvancedArmoury.tabGeneric);
	}
	
	@Override
	public void onUpdate(ItemStack item, World world, Entity entity, int par4, boolean isCurrentItem) {
		boolean firing = false;
		
		if (item.stackTagCompound != null) {
			firing = item.stackTagCompound.getBoolean("firing");
		}

		if (world.isRemote && entity instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) entity;
			if (item.stackTagCompound != null) {
				if (Mouse.isButtonDown(1) && player.getHeldItem() != null && player.getHeldItem().getItem() instanceof AssetReceiver) {
					if (!(item.stackTagCompound.getBoolean("firing"))) {
						AdvancedArmoury.network.sendToServer(new GunEventPacket("fire", "true"));
					}
				} else {
					if (item.stackTagCompound.getBoolean("firing")) {
						AdvancedArmoury.network.sendToServer(new GunEventPacket("fire", "false"));
					}
				}
				
				if (Keyboard.isKeyDown(Keyboard.KEY_I) && player.getHeldItem() != null && player.getHeldItem().getItem() instanceof AssetReceiver) {
					AdvancedArmoury.network.sendToServer(new GunGuiPacket());
				}
			}
		}
		
		if (!world.isRemote && entity instanceof EntityPlayer) {
			if (item.stackTagCompound == null) {
				item.stackTagCompound = new NBTTagCompound();
				item.stackTagCompound.setInteger("fireRateTicker", 0);
				item.stackTagCompound.setInteger("fireRate", fireRate);
				item.stackTagCompound.setBoolean("firing", false);
				item.stackTagCompound.setInteger("damage", 0);
				item.stackTagCompound.setInteger("durability", 15);
				item.stackTagCompound.setBoolean("broken", false);
			}

			EntityPlayer player = (EntityPlayer) entity;
			int frTicker = item.stackTagCompound.getInteger("fireRateTicker");
			
			if (frTicker > 0) {
				item.stackTagCompound.setInteger("fireRateTicker", frTicker - 1);
			} else {
				if (firing) {
					if (item.stackTagCompound.getBoolean("broken")) {
						ItemIInventory inventory = new AssaultIInventory(item);
						if (inventory.getStackInSlot(0) != null) {
							if (inventory.getStackInSlot(0).getItem() instanceof IMagazine) {
								MagazineInventory magInv = new MagazineInventory(inventory.getStackInSlot(0));
								if (magInv.getStackInSlot(0) != null) {
									damageGun(item, 1);
									shoot(world, player);
								}
							}
						} else {
							item.stackTagCompound.setInteger("fireRateTicker", item.stackTagCompound.getInteger("fireRate"));
							damageGun(item, 3);
							player.addChatComponentMessage(new ChatComponentText(EnumChatFormatting.RED + "Insert a magazine!"));
						}
					} else {
						player.addChatComponentMessage(new ChatComponentText("Gun dun broked. Repairs are WIP."));
					}
				}

			}
			
			if (player.openContainer != null & player.openContainer instanceof AssaultContainer && ((AssaultContainer) player.openContainer).needsUpdate) {
				((AssaultContainer) player.openContainer).writeToNBT();
				((AssaultContainer) player.openContainer).needsUpdate = false;
			}
		}
	}
	
	public int calculateDurability(ItemStack item) {
		return 0;
	}
	
	public float calculateAccuracy(ItemStack item) {
		return 0.0F;
	}
	
	public int calculateFireRate(ItemStack item) {
		return 0;
	}
	
	
	public void damageGun(ItemStack item, int dmg) {
		if (item.stackTagCompound != null) {
			int maxDurability = item.stackTagCompound.getInteger("durability");
			int newDmg = dmg + item.stackTagCompound.getInteger("damage");
			item.stackTagCompound.setInteger("damage", newDmg);
			if (newDmg > maxDurability) {
				item.stackTagCompound.setBoolean("broken", true);
			}
		}
	}
	
	
	@SideOnly(Side.CLIENT)
    public void addInformation(ItemStack item, EntityPlayer player, List list, boolean p_77624_4_) {
		if (item.stackTagCompound == null) {
			item.stackTagCompound = new NBTTagCompound();
			item.stackTagCompound.setInteger("fireRateTicker", 0);
			item.stackTagCompound.setInteger("fireRate", fireRate);
			item.stackTagCompound.setBoolean("firing", false);
			item.stackTagCompound.setInteger("damage", 0);
			item.stackTagCompound.setInteger("durability", 15);
			item.stackTagCompound.setBoolean("broken", false);
			item.stackTagCompound.setInteger("attachmentSlots", 0);
		} else {
			ItemIInventory inventory = new AssaultIInventory(item);
			String magName = "None";
			if (inventory.getStackInSlot(0) != null) {
				magName = inventory.getStackInSlot(0).getDisplayName();
			}
			
			list.add("Range: " + range);
			list.add("Fire Rate: " + item.stackTagCompound.getInteger("fireRate"));
			list.add("Magazine: " + magName);
			list.add("Damage: " + item.stackTagCompound.getInteger("damage") + "/" + item.stackTagCompound.getInteger("durability"));

			//if (player.isSneaking) {
				list.add("Receiver: ");
				list.add("Barrel: ");
				list.add("Stock: ");
				list.add("Firing System: ");
				list.add("Foreend: ");
				list.add("Attachment slots: ");
				list.add(" ");

				int attachments = item.stackTagCompound.getInteger("attachmentSlots");
				if (attachments < 0) {
					list.add("Attachments:");

					for(int i = 0; i < attachments; i++) {
						list.add("Attachment " + i);
					}
				}
			//s}
		}
	}
	
	public void shoot(World world, EntityPlayer player) {
		if (!world.isRemote) {
			player.addChatComponentMessage(new ChatComponentText(EnumChatFormatting.GOLD + "PEW PEW PEW!"));

			Vec3 vec3 = Vec3.createVectorHelper(player.posX, player.posY + player.getEyeHeight(), player.posZ);
			Vec3 lookVec = player.getLook(1.0F);
			System.out.println("Looking: " + lookVec);
			Vec3 addedVector = vec3.addVector(lookVec.xCoord * range, lookVec.yCoord * range, lookVec.zCoord * range);

            double d1 = range;
			
	        this.pointedEntity = null;
	        Vec3 vec33 = null;
	        float f1 = 1.0F;
	        List list = world.getEntitiesWithinAABBExcludingEntity(player, player.boundingBox.addCoord(lookVec.xCoord * 50.0D, lookVec.yCoord * 50.0D, lookVec.zCoord * 50.0D).expand((double)f1, (double)f1, (double)f1));
	        double d2 = d1 + 1;

	        for (int i = 0; i < list.size(); ++i) {
	            Entity entity = (Entity)list.get(i);

	            if (entity.canBeCollidedWith()) {
	                float f2 = entity.getCollisionBorderSize();
	                AxisAlignedBB axisalignedbb = entity.boundingBox.expand((double)f2, (double)f2, (double)f2);
	                MovingObjectPosition movingobjectposition = axisalignedbb.calculateIntercept(vec3, addedVector);

	                if (axisalignedbb.isVecInside(vec3)) {
	                    if (0.0D < d2 || d2 == 0.0D) {
	                        this.pointedEntity = entity;
	                        System.out.println("Target aquired! " + entity + " (Stage 1)");
	                        player.addChatComponentMessage(new ChatComponentText(EnumChatFormatting.GOLD + "Nice shot, son!"));
	                        entity.attackEntityFrom(DamageSource.causePlayerDamage(player), 10.0F);
	                        vec33 = movingobjectposition == null ? vec3 : movingobjectposition.hitVec;
	                        d2 = 0.0D;
	                    }
	                } else if (movingobjectposition != null) {
	                    double d3 = vec3.distanceTo(movingobjectposition.hitVec);

	                    if (d3 < d2 || d2 == 0.0D) {
	                        if (entity == player.ridingEntity && !entity.canRiderInteract()) {
	                            if (d2 == 0.0D) {
	                                this.pointedEntity = entity;
	                                player.addChatComponentMessage(new ChatComponentText(EnumChatFormatting.GOLD + "Tell Flenix what you did. Quote 'Stage 2'"));
	                                player.addChatComponentMessage(new ChatComponentText(EnumChatFormatting.GOLD + "Theoretically you tried to shoot what you were riding (Horse, dirtbike etc?)"));
	                                System.out.println("Target aquired! " + entity + " (Stage 2)");
	                                vec33 = movingobjectposition.hitVec;
	                            }
	                        } else {
	                            this.pointedEntity = entity;
		                        System.out.println("Target aquired! " + entity + " (Stage 3)");
		                        entity.attackEntityFrom(DamageSource.causePlayerDamage(player), 10.0F);
		                        player.addChatComponentMessage(new ChatComponentText(EnumChatFormatting.GOLD + "Nice shot, son!"));
	                            vec33 = movingobjectposition.hitVec;
	                            d2 = d3;
	                        }
	                    }
	                }
	            }
	        }
		}
	}

}
