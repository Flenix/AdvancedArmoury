package co.uk.silvania.advancedarmoury;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.event.HoverEvent;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.IChatComponent;

public class DamageSourceShot extends EntityDamageSource {

	public DamageSourceShot(Entity player) {
		super("player", player);
		// TODO Auto-generated constructor stub
	}
	
	@Override
    public IChatComponent func_151519_b(EntityLivingBase entity) {
		ItemStack itemstack = ((EntityLivingBase) this.damageSourceEntity).getHeldItem();

		if (itemstack != null) {
			return new ChatComponentText(entity.getCommandSenderName() + " was shot by " + this.damageSourceEntity.getCommandSenderName() + " using ").appendSibling(hoverText(itemstack));
		}
		
		return new ChatComponentText(entity.getCommandSenderName() + " was shot by " + this.damageSourceEntity.getCommandSenderName());
	}
	
    public IChatComponent hoverText(ItemStack item) {
        IChatComponent ichatcomponent = (new ChatComponentText(item.getDisplayName()));

        if (item.stackTagCompound != null) {
        	NBTTagCompound nbttagcompound = new NBTTagCompound();
            item.writeToNBT(nbttagcompound);
            ichatcomponent.getChatStyle().setChatHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_ITEM, new ChatComponentText(nbttagcompound.toString())));
            ichatcomponent.getChatStyle().setColor(item.getRarity().rarityColor);
        }

        return ichatcomponent;
    }
}
