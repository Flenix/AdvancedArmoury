package co.uk.silvania.advancedarmoury;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;

public interface IRound {
	
	public void onShotBlock(ItemStack item, Block block);
	public void onShotEntity(ItemStack item, EntityLivingBase entity);

	//TODO welcome back! EntityPlayerMP != EntityLiving. Check EVERYWHERE it's EntityLivingBASE.
}
