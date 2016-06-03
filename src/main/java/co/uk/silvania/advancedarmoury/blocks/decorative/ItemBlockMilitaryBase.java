package co.uk.silvania.advancedarmoury.blocks.decorative;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;

public class ItemBlockMilitaryBase extends ItemBlock {
	
	public ItemBlockMilitaryBase(Block block) {
		super(block);
	}
	
	@Override
    public String getItemStackDisplayName(ItemStack item) {
		Block block = Block.getBlockFromItem(item.getItem());
		
		
		if (block instanceof  FenceMilitaryBase) { return StatCollector.translateToLocal("material." + ((FenceMilitaryBase) block).texture) + " " +  StatCollector.translateToLocal("blockType.fence"); }
		if (block instanceof Slope45MilitaryBase){ return StatCollector.translateToLocal("material." + ((Slope45MilitaryBase) block).texture) + " " +  StatCollector.translateToLocal("blockType.slope45"); }
		if (block instanceof StairsMilitaryBase) { return StatCollector.translateToLocal("material." + ((StairsMilitaryBase) block).texture) + " " +  StatCollector.translateToLocal("blockType.stairs"); }
		if (block instanceof  PanelMilitaryBase) { return StatCollector.translateToLocal("material." + ((PanelMilitaryBase) block).texture) + " " +  StatCollector.translateToLocal("blockType.panel"); }
		if (block instanceof  CoverMilitaryBase) { return StatCollector.translateToLocal("material." + ((CoverMilitaryBase) block).texture) + " " +  StatCollector.translateToLocal("blockType.cover"); }
		
		if (block instanceof WalkwayFenceMilitaryBase) {
			return StatCollector.translateToLocal("material." + ((DecorativeMilitaryBase) block).texture) + " " + StatCollector.translateToLocal("blockType." + ((WalkwayFenceMilitaryBase) block).getBlockNameFromMeta(item.getItemDamage()));
		}
		
		if (block instanceof DecorativeMilitaryBase) { return StatCollector.translateToLocal("material." + ((DecorativeMilitaryBase) block).texture) + " " + StatCollector.translateToLocal("blockType." + ((DecorativeMilitaryBase) block).blockType); }
		
		//Check these last, as other stuff extends these.
		if (block instanceof   SlabMilitaryBase) { return StatCollector.translateToLocal("material." + ((SlabMilitaryBase) block).texture) + " " +  StatCollector.translateToLocal("blockType.slab"); }
		if (block instanceof  BlockMilitaryBase) { return StatCollector.translateToLocal("material." + ((BlockMilitaryBase) block).texture) + " " + StatCollector.translateToLocal("blockType.block"); }
		
		if (block instanceof WalkwayFenceMilitaryBase) {
			((WalkwayFenceMilitaryBase) block).getBlockNameFromMeta(item.getItemDamage());
		}
		
		return super.getItemStackDisplayName(item);
    }
}
