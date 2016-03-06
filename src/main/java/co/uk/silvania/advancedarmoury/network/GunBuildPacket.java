package co.uk.silvania.advancedarmoury.network;

import co.uk.silvania.advancedarmoury.blocks.machines.assemblytable.assault.AssaultAssemblyTableContainer;
import co.uk.silvania.advancedarmoury.blocks.machines.assemblytable.assault.AssaultAssemblyTableEntity;
import co.uk.silvania.advancedarmoury.blocks.machines.encasementtable.assault.AssaultEncasementTableContainer;
import co.uk.silvania.advancedarmoury.blocks.machines.encasementtable.assault.AssaultEncasementTableEntity;
import co.uk.silvania.advancedarmoury.items.components.asset.AssetReceiver;
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
	public static String buildGun;
	
	public GunBuildPacket() {}
	
	public GunBuildPacket(String gunName, String build, int xPos, int yPos, int zPos) {
		name = gunName;
		x = xPos;
		y = yPos;
		z = zPos;
		buildGun = build;
	}
	
	@Override public void fromBytes(ByteBuf buf) {
		name = ByteBufUtils.readUTF8String(buf);
		buildGun = ByteBufUtils.readUTF8String(buf);
	}
	
	@Override public void toBytes(ByteBuf buf) {
		ByteBufUtils.writeUTF8String(buf, name);
		ByteBufUtils.writeUTF8String(buf, buildGun);
	}
	
	public static class Handler implements IMessageHandler<GunBuildPacket, IMessage> {
		
		@Override
		public IMessage onMessage(GunBuildPacket message, MessageContext ctx) {
			int x = message.x;
			int y = message.y;
			int z = message.z;
			String buildGun = message.buildGun;
			String name = message.name;
			
			EntityPlayer player = ctx.getServerHandler().playerEntity;
			World world = player.worldObj;
			
			if (player.openContainer instanceof AssaultAssemblyTableContainer) {
				AssaultAssemblyTableContainer container = (AssaultAssemblyTableContainer) player.openContainer;
				
				AssaultAssemblyTableEntity tileEntity = container.tileEntity;
				
				if (tileEntity != null) {
					if (buildGun.equalsIgnoreCase("true")) {
						tileEntity.building = true;
						ItemStack gun = tileEntity.getStackInSlot(0);
						if (gun != null) {
							if (gun.stackTagCompound == null) {
								gun.stackTagCompound = new NBTTagCompound();
							}
							gun.stackTagCompound.setString("name", name);
						}
					} else {
						tileEntity.gunName = name;
					}
				}
				tileEntity.getDescriptionPacket();
			} else if (player.openContainer instanceof AssaultEncasementTableContainer) {
				AssaultEncasementTableContainer container = (AssaultEncasementTableContainer) player.openContainer;
				
				AssaultEncasementTableEntity tileEntity = container.tileEntity;
				
				if (tileEntity != null) {
					if (buildGun.equalsIgnoreCase("true")) {
						tileEntity.building = true;
						ItemStack gun = tileEntity.getStackInSlot(0);
						if (gun != null) {
							if (gun.stackTagCompound == null) {
								gun.stackTagCompound = new NBTTagCompound();
							}
							gun.stackTagCompound.setString("tag", name);
						}
					} else {
						tileEntity.gunTag = name;
					}
				}
				tileEntity.getDescriptionPacket();
			}
			return null;
		}
	}

}
