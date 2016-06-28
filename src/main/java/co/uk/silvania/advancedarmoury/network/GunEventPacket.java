package co.uk.silvania.advancedarmoury.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import co.uk.silvania.advancedarmoury.items.assets.AssetReceiver;
import co.uk.silvania.advancedarmoury.items.generic.GunFrame;
import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class GunEventPacket implements IMessage {
	
	public static String eventId;
	public static String status;
	
	public GunEventPacket() {}
	
	public GunEventPacket(String s1, String s2) {
		eventId = s1;
		status = s2;
	}
	
	@Override
	public void fromBytes(ByteBuf buf) {
		eventId = ByteBufUtils.readUTF8String(buf);
		status = ByteBufUtils.readUTF8String(buf);
	}
	
	@Override
	public void toBytes(ByteBuf buf) {
		ByteBufUtils.writeUTF8String(buf, eventId);
		ByteBufUtils.writeUTF8String(buf, status);
	}
	
	public static class Handler implements IMessageHandler<GunEventPacket, IMessage> {
		
		@Override
		public IMessage onMessage(GunEventPacket message, MessageContext ctx) {
			
			EntityPlayer player = ctx.getServerHandler().playerEntity;
			World world = player.worldObj;
			
			if (player.getHeldItem().getItem() instanceof GunFrame) {
				ItemStack gunItem = player.getHeldItem();
			
				if (eventId.equalsIgnoreCase("fire")) {
					boolean b = false;
					if (status.equalsIgnoreCase("true")) { b = true; }
					if (!b) { gunItem.stackTagCompound.setInteger("burstCount", 0); }
					gunItem.stackTagCompound.setBoolean("firing", b);
				}
				if (eventId.equalsIgnoreCase("aim")) {
					boolean b = false;
					if (status.equalsIgnoreCase("true")) { b = true; }
					gunItem.stackTagCompound.setBoolean("aiming", b);
				}
				if (eventId.equalsIgnoreCase("melee")) {
					boolean b = false;
					if (status.equalsIgnoreCase("true")) { b = true; }
					gunItem.stackTagCompound.setBoolean("melee", b);
					if (!b) {
						gunItem.stackTagCompound.setInteger("meleeCooldown", 0);
					}
				}
			}
			
			return null;
		}
	}

}
