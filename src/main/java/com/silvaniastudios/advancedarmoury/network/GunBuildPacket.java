package com.silvaniastudios.advancedarmoury.network;

import com.silvaniastudios.advancedarmoury.blocks.machines.assemblytable.ComponentAssemblyTableContainer;
import com.silvaniastudios.advancedarmoury.blocks.machines.assemblytable.ComponentAssemblyTableEntity;
import com.silvaniastudios.advancedarmoury.blocks.machines.assemblytable.assault.AssaultAssemblyTableContainer;
import com.silvaniastudios.advancedarmoury.blocks.machines.assemblytable.assault.AssaultAssemblyTableEntity;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class GunBuildPacket implements IMessage {

	public static int x;
	public static int y;
	public static int z;
	public static String name;
	public static String tag;
	public static String buildGun;
	public static String userName;
	
	public GunBuildPacket() {}
	
	public GunBuildPacket(String gunName, String gunTag, String build, String username, int xPos, int yPos, int zPos) {
		name = gunName;
		tag = gunTag;
		x = xPos;
		y = yPos;
		z = zPos;
		buildGun = build;
		userName = username;
	}
	
	@Override public void fromBytes(ByteBuf buf) {
		name = ByteBufUtils.readUTF8String(buf);
		tag = ByteBufUtils.readUTF8String(buf);
		buildGun = ByteBufUtils.readUTF8String(buf);
		userName = ByteBufUtils.readUTF8String(buf);
	}
	
	@Override public void toBytes(ByteBuf buf) {
		ByteBufUtils.writeUTF8String(buf, name);
		ByteBufUtils.writeUTF8String(buf, tag);
		ByteBufUtils.writeUTF8String(buf, buildGun);
		ByteBufUtils.writeUTF8String(buf, userName);
	}
	
	public static class Handler implements IMessageHandler<GunBuildPacket, IMessage> {
		
		@Override
		public IMessage onMessage(GunBuildPacket message, MessageContext ctx) {
			int x = message.x;
			int y = message.y;
			int z = message.z;
			String buildGun = message.buildGun;
			String name = message.name;
			String tag = message.tag;
			String userName = message.userName;
			
			EntityPlayer player = ctx.getServerHandler().playerEntity;
			World world = player.worldObj;
			
			if (player.openContainer instanceof AssaultAssemblyTableContainer) {
				AssaultAssemblyTableContainer container = (AssaultAssemblyTableContainer) player.openContainer;
				
				AssaultAssemblyTableEntity tileEntity = container.tileEntity;
				
				if (tileEntity != null) {
					if (buildGun.equalsIgnoreCase("true")) {
						tileEntity.building = true;
						tileEntity.initiator = userName;
						ItemStack gun = tileEntity.getStackInSlot(1);
						if (gun != null) {
							if (gun.stackTagCompound == null) {
								gun.stackTagCompound = new NBTTagCompound();
							}
							gun.stackTagCompound.setString("name", name);
							gun.stackTagCompound.setString("tag", tag);
						}
					} else {
						tileEntity.gunName = name;
						tileEntity.gunTag = tag;
					}
				}
				tileEntity.getDescriptionPacket();
			} else if (player.openContainer instanceof ComponentAssemblyTableContainer) {
				ComponentAssemblyTableContainer container = (ComponentAssemblyTableContainer) player.openContainer;
				
				ComponentAssemblyTableEntity tileEntity = container.tileEntity;
				
				if (tileEntity != null) {
					if (buildGun.equalsIgnoreCase("true")) {
						tileEntity.building = true;
						tileEntity.initiator = userName;
						ItemStack component = tileEntity.getStackInSlot(9);
						if (component != null) {
							if (component.stackTagCompound == null) {
								component.stackTagCompound = new NBTTagCompound();
							}
							component.stackTagCompound.setString("name", name);
							component.stackTagCompound.setString("tag", tag);
						}
					} else {
						tileEntity.gunName = name;
						tileEntity.gunTag = tag;
					}
				}
				tileEntity.getDescriptionPacket();
			}
			return null;
		}
	}

}
