package co.uk.silvania.advancedarmoury.items.modifiers;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public interface IModifierCore {
	
	public void onBlockShot(ItemStack item, EntityPlayer player, Block block);
	public void onEntityShot(ItemStack item, EntityPlayer player, Entity entity);
	public void onEntityKilled(ItemStack item, EntityPlayer player, Entity entity);
	public void onFireWeapon(ItemStack item, EntityPlayer player);
	public void onReload(ItemStack item, EntityPlayer player);
	public void passiveModifier(ItemStack item, EntityPlayer player);

}
