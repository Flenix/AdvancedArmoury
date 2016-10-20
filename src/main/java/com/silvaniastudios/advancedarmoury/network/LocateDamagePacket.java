package com.silvaniastudios.advancedarmoury.network;

import com.silvaniastudios.advancedarmoury.AAUtils;
import com.silvaniastudios.advancedarmoury.ClientEventHandler;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;

public class LocateDamagePacket implements IMessage {
	
	public static String angle;
	private static int ticker;
	
	public LocateDamagePacket() {}
	
	public LocateDamagePacket(double angle) {
		this.angle = "" + angle;
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		
	}

	@Override
	public void toBytes(ByteBuf buf) {
		ByteBufUtils.writeUTF8String(buf, angle);
	}
	
	public static class Handler implements IMessageHandler<LocateDamagePacket, IMessage> {
		
		public static boolean renderHit = false;
		public static double angle;

		@Override
		public IMessage onMessage(LocateDamagePacket message, MessageContext ctx) {
			renderHit = true;
			angle = AAUtils.parseDouble(message.angle);
			ClientEventHandler.timeHit = Minecraft.getMinecraft().theWorld.getTotalWorldTime();
			return null;
		}
	}

}
