package co.uk.silvania.advancedarmoury;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public interface IRound {
	
	public void onShotBlock(ItemStack item, World world, Block block, int x, int y, int z, int sideHit);
	public void onShotEntity(ItemStack item, EntityLivingBase entity);

	//TODO welcome back! EntityPlayerMP != EntityLiving. Check EVERYWHERE it's EntityLivingBASE.
}
