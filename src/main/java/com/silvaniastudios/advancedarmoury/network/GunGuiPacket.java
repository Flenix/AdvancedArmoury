package com.silvaniastudios.advancedarmoury.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

import com.silvaniastudios.advancedarmoury.AdvancedArmoury;
import com.silvaniastudios.advancedarmoury.items.assets.AssetReceiver;
import com.silvaniastudios.advancedarmoury.items.components.generic.GunFrame;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class GunGuiPacket implements IMessage {
	
	public GunGuiPacket() {}
	
	public GunGuiPacket(String s1) {
	}
	
	@Override public void fromBytes(ByteBuf buf) {}
	@Override public void toBytes(ByteBuf buf) {}
	
	public static class Handler implements IMessageHandler<GunGuiPacket, IMessage> {
		
		@Override
		public IMessage onMessage(GunGuiPacket message, MessageContext ctx) {
			
			EntityPlayer player = ctx.getServerHandler().playerEntity;
			World world = player.worldObj;
			
			if (player.getHeldItem().getItem() instanceof GunFrame) {
				player.openGui(AdvancedArmoury.instance, 0, world, (int) player.posX, (int) player.posY, (int) player.posZ);
			}
			return null;
		}
	}

}
